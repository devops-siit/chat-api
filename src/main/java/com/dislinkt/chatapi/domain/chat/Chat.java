package com.dislinkt.chatapi.domain.chat;

import com.dislinkt.chatapi.domain.account.Account;
import com.dislinkt.chatapi.domain.base.BaseEntity;
import com.dislinkt.chatapi.domain.chat.message.Message;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Chat extends BaseEntity {

    @ManyToMany
    @JoinTable(
            name = "chat_accounts",
            uniqueConstraints = @UniqueConstraint(name = "UniqueChatAccount",
                    columnNames = {"chat_id", "account_id"}),
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts;

    @OneToMany
    @JoinColumn(name = "chat_id")
    private Set<Message> messages;
}
