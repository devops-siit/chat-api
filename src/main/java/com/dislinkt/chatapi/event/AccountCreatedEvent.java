package com.dislinkt.chatapi.event;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountCreatedEvent {

    private String uuid;

    private String username;

    private String name;
}