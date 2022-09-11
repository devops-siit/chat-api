package com.dislinkt.chatapi.domain.chat.message;

import com.dislinkt.chatapi.domain.account.Account;
import com.dislinkt.chatapi.domain.base.BaseEntity;
import com.dislinkt.chatapi.domain.chat.Chat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
public class Message extends BaseEntity {

    @NotNull
    @Size(max = 256)
    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id", referencedColumnName = "id", nullable = false)
    private Account from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
