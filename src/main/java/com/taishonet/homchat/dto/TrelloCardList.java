package com.taishonet.homchat.dto;

import java.util.List;

import lombok.Data;

@Data
public class TrelloCardList {
    private String id;
    private String name;
    private List<TrelloCard> cards;
}
