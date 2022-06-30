package com.dislinkt.chatapi.domain.chat.message;

import com.dislinkt.chatapi.domain.account.Account;
import com.dislinkt.chatapi.domain.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Message extends BaseEntity {

    @NotNull
    @Size(max = 256)
    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id", referencedColumnName = "id", nullable = false)
    private Account from;
}
