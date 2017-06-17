package com.taishonet.homchat.service;

import com.linecorp.bot.model.message.TextMessage;
import com.taishonet.homchat.enums.MessageCommand;
import com.taishonet.homchat.repository.TrelloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MessageService {

    private TrelloRepository trelloRepository;
    private MenstruationService menstruationService;

    @Autowired
    public MessageService(TrelloRepository trelloRepository, MenstruationService menstruationService) {
        this.trelloRepository = trelloRepository;
        this.menstruationService = menstruationService;
    }

    private TextMessage executeAddTrelloCard(MessageCommand messageCommand, String receiveMessage) {
        Map<String, String> params = messageCommand.getParseArgumentsFunction().apply(receiveMessage);
        if (params.isEmpty()) {
            return new TextMessage("パラメータが足りないきゅい！");
        }
        String registeredUrl = trelloRepository.registerNewDateCardToTrello(params);
        return new TextMessage("きゅいきゅい！ つ" + registeredUrl);
    }

    private TextMessage executeReferTrelloList() {
        String message = trelloRepository.refererTrelloList();
        if (StringUtils.isEmpty(message)) {
            return null;
        }
        return new TextMessage(message);
    }

    private TextMessage executeRegisterMenstruation(MessageCommand messageCommand, String receiveMessage) {
        log.warn("生理りまいんどはいったよ");
        //TODO 共通化したい。糞。
        Map<String, String> params = messageCommand.getParseArgumentsFunction().apply(receiveMessage);
        if (params.isEmpty()) {
            return new TextMessage("パラメータが足りないきゅい！");
        }
        //TODO 糞。あとで直す
        String message = menstruationService.registerMenstruation(params.get("weight"));
        log.warn(message);
        return new TextMessage(message);
    }

    private MessageCommand parseMessageCommand(String receiveMessage) {
        return Arrays.stream(MessageCommand.values())
                .filter(val -> receiveMessage.contains(val.getCommand()))
                .findFirst().orElse(MessageCommand.NO_COMMAND);
    }

    public TextMessage dispatchMessageCommand(String receiveMessage) {
        MessageCommand command = parseMessageCommand(receiveMessage);
        log.warn(command.getCommand());
        switch (command) {
        case ADD_TRELLO:
            return executeAddTrelloCard(command, receiveMessage);
        case LOOK_TRELLO:
            return executeReferTrelloList();
        case REGISTER_MENSTRUATION:
            return executeRegisterMenstruation(command, receiveMessage);
        case NO_COMMAND: //コマンド時以外は応答しない
        default:
            return null;
        }
    }
}
