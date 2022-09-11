package com.dislinkt.chatapi.service.chat;

import com.dislinkt.chatapi.domain.account.Account;
import com.dislinkt.chatapi.domain.chat.Chat;
import com.dislinkt.chatapi.exception.types.EntityNotFoundException;
import com.dislinkt.chatapi.repository.ChatRepository;
import com.dislinkt.chatapi.service.account.AccountService;
import com.dislinkt.chatapi.util.ReturnResponse;
import com.dislinkt.chatapi.service.account.payload.AccountDTO;
import com.dislinkt.chatapi.web.rest.chat.payload.SimpleChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private AccountService accountService;

    public Set<SimpleChatDTO> findByAccount() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account account = accountService.findOneByUsernameOrElseThrowException(user.getUsername());

        Set<Chat> chats = account.getChats();

        return chats.stream().map((chat) -> {
            SimpleChatDTO dto = new SimpleChatDTO();

            dto.setUuid(chat.getUuid());

            Account targetAccount = chat.getAccounts().iterator().next();

            AccountDTO targetAccountDTO = new AccountDTO();
            targetAccountDTO.setName(targetAccount.getName());
            targetAccountDTO.setUsername(targetAccount.getUsername());
            targetAccountDTO.setUuid(targetAccount.getUuid());

            dto.setAccount(targetAccountDTO);

            return dto;
        }).collect(Collectors.toSet());
    }

    public ResponseEntity<SimpleChatDTO> insertChat(String accountUuid) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account loggedAccount = accountService.findOneByUsernameOrElseThrowException(user.getUsername());

        Account account = accountService.findOneByUuidOrElseThrowException(accountUuid);

        Optional<Chat> chatOrEmpty = chatRepository.findOneByAccountsIn(Arrays.asList(loggedAccount, account));

        if (chatOrEmpty.isPresent()) {
            Chat existingChat = chatOrEmpty.get();

            SimpleChatDTO dto = new SimpleChatDTO();

            dto.setUuid(existingChat.getUuid());

            AccountDTO targetAccountDTO = new AccountDTO();
            targetAccountDTO.setName(account.getName());
            targetAccountDTO.setUsername(account.getUsername());
            targetAccountDTO.setUuid(account.getUuid());

            dto.setAccount(targetAccountDTO);

            return ReturnResponse.entityGet(dto);
        }

        Chat chat = new Chat();

        chat.setAccounts(new HashSet<>(Arrays.asList(loggedAccount, account)));

        chatRepository.save(chat);

        SimpleChatDTO dto = new SimpleChatDTO();

        dto.setUuid(chat.getUuid());

        chat.getAccounts().remove(account);

        Account targetAccount = chat.getAccounts().iterator().next();

        AccountDTO targetAccountDTO = new AccountDTO();
        targetAccountDTO.setName(targetAccount.getName());
        targetAccountDTO.setUsername(targetAccount.getUsername());
        targetAccountDTO.setUuid(targetAccount.getUuid());

        dto.setAccount(targetAccountDTO);

        return ReturnResponse.entityCreated(dto);
    }

    public Chat findOneByUuidOrElseThrowException(String uuid) {
        return chatRepository.findOneByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Chat not found"));
    }
}
