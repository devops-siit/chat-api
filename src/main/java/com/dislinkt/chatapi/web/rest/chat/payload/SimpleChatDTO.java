package com.dislinkt.chatapi.web.rest.chat.payload;

import com.dislinkt.chatapi.web.rest.account.payload.AccountDTO;
import com.dislinkt.chatapi.web.rest.base.BaseDTO;
import lombok.Data;

@Data
public class SimpleChatDTO extends BaseDTO {

    private AccountDTO account;
}
