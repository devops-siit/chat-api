package com.dislinkt.chatapi.web.rest.chat.payload.request;

import lombok.Data;

@Data
public class NewChatRequest {

    private String loggedAccountUuid;

    private String accountUuid;
}
