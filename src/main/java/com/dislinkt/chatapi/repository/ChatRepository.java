package com.dislinkt.chatapi.repository;

import com.dislinkt.chatapi.domain.account.Account;
import com.dislinkt.chatapi.domain.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findOneByUuid(String uuid);

    Optional<Chat> findOneByAccountsIn(List<Account> accounts);
}
