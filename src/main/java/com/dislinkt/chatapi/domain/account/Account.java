package com.dislinkt.chatapi.domain.account;

import com.dislinkt.chatapi.domain.base.BaseEntity;
import com.dislinkt.chatapi.domain.chat.Chat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
public class Account extends BaseEntity {

    @NotNull
    @Size(max = 36)
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(max = 128)
    private String name;

    @ManyToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
    private Set<Chat> chats;
}
