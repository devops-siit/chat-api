package com.dislinkt.chatapi.web.rest.message.payload.request;

import lombok.Data;

@Data
public class NewMessageRequest {

    private String text;

    private String accountUuid;
}