package com.dislinkt.chatapi.web.rest.message.payload;

import com.dislinkt.chatapi.service.account.payload.AccountDTO;
import com.dislinkt.chatapi.web.rest.base.BaseDTO;
import lombok.Data;

@Data
public class MessageDTO extends BaseDTO {

    private String text;

    private AccountDTO from;
}
