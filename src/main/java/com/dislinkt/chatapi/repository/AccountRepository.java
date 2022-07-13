package com.dislinkt.chatapi.repository;

import com.dislinkt.chatapi.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findOneByUuid(String uuid);

    Optional<Account> findOneByUsername(String username);
}