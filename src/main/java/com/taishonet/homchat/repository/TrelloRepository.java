package com.taishonet.homchat.repository;

import com.google.gson.Gson;
import com.taishonet.homchat.dto.TrelloCard;
import com.taishonet.homchat.utils.RequestUtils;

import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URISyntaxException;
import java.util.Map;

@Repository
public class TrelloRepository {
    //TODO: 本当はサーバ環境変数にするべきだが面倒なのであとで
    private static final String TRELLO_KEY = "8b4c424f78a2695f0f51fd97cc513a56";
    private static final String TRELLO_TOKEN = "100a24d0bcc73036ff180cade8007a373115ceb605225b948f4cd24d43c2db44";
    private static final String TRELLO_TARGET_LIST_ID = "591af9ccf8b507be793d546c";

    private static final String TRELLO_ADD_CARD_API = "https://api.trello.com/1/cards/";

    public String registerNewDateCardToTrello(Map<String, String> addParamMap) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("key", TRELLO_KEY);
        params.set("token", TRELLO_TOKEN);
        params.set("idList", TRELLO_TARGET_LIST_ID);
        params.set("pos", "top");
        addParamMap.keySet().forEach(key -> params.set(key, addParamMap.get(key)));
        try {
            String body = RequestUtils.post(TRELLO_ADD_CARD_API, null, params);
            TrelloCard trelloCard = new Gson().fromJson(body, TrelloCard.class);
            return trelloCard.getUrl();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }
}
