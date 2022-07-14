package com.dislinkt.chatapi.service.account;

import com.dislinkt.chatapi.domain.account.Account;
import com.dislinkt.chatapi.exception.types.EntityAlreadyExistsException;
import com.dislinkt.chatapi.exception.types.EntityNotFoundException;
import com.dislinkt.chatapi.repository.AccountRepository;
import com.dislinkt.chatapi.web.rest.account.payload.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO insertAccount(AccountDTO accountDTO) {

        Optional<Account> accountOrEmpty = accountRepository.findOneByUsername(accountDTO.getUsername());

        if (accountOrEmpty.isPresent()) {
            throw new EntityAlreadyExistsException("Account username already exists");
        }

        Account account = new Account();

        account.setName(accountDTO.getName());
        account.setUsername(accountDTO.getUsername());
        account.setUuid(accountDTO.getUuid());

        accountRepository.save(account);

        accountDTO.setUuid(account.getUuid());

        return accountDTO;
    }

    public Account findOneByUuidOrElseThrowException(String uuid) {
        return accountRepository.findOneByUuid(uuid).orElseThrow(() ->
                new EntityNotFoundException("Account not found"));
    }
}
