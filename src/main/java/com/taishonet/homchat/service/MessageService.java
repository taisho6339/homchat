package com.taishonet.homchat.service;

import com.linecorp.bot.model.message.TextMessage;
import com.taishonet.homchat.enums.MessageCommand;
import com.taishonet.homchat.repository.TrelloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;

@Service
public class MessageService {

    private TrelloRepository trelloRepository;

    @Autowired
    public MessageService(TrelloRepository trelloRepository) {
        this.trelloRepository = trelloRepository;
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

    private MessageCommand parseMessageCommand(String receiveMessage) {
        return Arrays.stream(MessageCommand.values())
                .filter(val -> receiveMessage.contains(val.getCommand()))
                .findFirst().orElse(MessageCommand.NO_COMMAND);
    }

    public TextMessage dispatchMessageCommand(String receiveMessage) {
        MessageCommand command = parseMessageCommand(receiveMessage);
        switch (command) {
        case ADD_TRELLO:
            return executeAddTrelloCard(command, receiveMessage);
        case LOOK_TRELLO:
            return executeReferTrelloList();
        case NO_COMMAND: //コマンド時以外は応答しない
        default:
            return null;
        }
    }
}
