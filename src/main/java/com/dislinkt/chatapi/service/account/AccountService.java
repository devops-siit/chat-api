package com.dislinkt.chatapi.service.account;

import com.dislinkt.chatapi.domain.account.Account;
import com.dislinkt.chatapi.event.AccountCreatedEvent;
import com.dislinkt.chatapi.exception.types.EntityAlreadyExistsException;
import com.dislinkt.chatapi.exception.types.EntityNotFoundException;
import com.dislinkt.chatapi.repository.AccountRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableBinding(Sink.class)
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @StreamListener(target = Sink.INPUT)
    public void insertAccount(AccountCreatedEvent accountCreatedEvent) {

        Optional<Account> accountOrEmpty = accountRepository.findOneByUsername(accountCreatedEvent.getUsername());

        if (accountOrEmpty.isPresent()) {
            throw new EntityAlreadyExistsException("Account username already exists");
        }

        Account account = new Account();

        account.setName(accountCreatedEvent.getName());
        account.setUsername(accountCreatedEvent.getUsername());
        account.setUuid(accountCreatedEvent.getUuid());

        accountRepository.save(account);
    }

    public Account findOneByUuidOrElseThrowException(String uuid) {
        return accountRepository.findOneByUuid(uuid).orElseThrow(() ->
                new EntityNotFoundException("Account not found"));
    }

    public Account findOneByUsernameOrElseThrowException(String username) {
        return accountRepository.findOneByUsername(username).orElseThrow(() ->
                new EntityNotFoundException("Account not found"));
    }

    public Optional<Account> findOneByUsername(String username) {
        return accountRepository.findOneByUsername(username);
    }
}
