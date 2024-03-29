package com.dislinkt.chatapi.domain.chat;

import com.dislinkt.chatapi.domain.account.Account;
import com.dislinkt.chatapi.domain.base.BaseEntity;
import com.dislinkt.chatapi.domain.chat.message.Message;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
public class Chat extends BaseEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "chat_accounts",
            uniqueConstraints = @UniqueConstraint(name = "UniqueChatAccount",
                    columnNames = {"chat_id", "account_id"}),
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private Set<Message> messages;
}
