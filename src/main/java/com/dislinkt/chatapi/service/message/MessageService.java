package com.dislinkt.chatapi.service.message;

import com.dislinkt.chatapi.domain.account.Account;
import com.dislinkt.chatapi.domain.chat.Chat;
import com.dislinkt.chatapi.domain.chat.message.Message;
import com.dislinkt.chatapi.repository.MessageRepository;
import com.dislinkt.chatapi.service.account.AccountService;
import com.dislinkt.chatapi.service.chat.ChatService;
import com.dislinkt.chatapi.service.account.payload.AccountDTO;
import com.dislinkt.chatapi.web.rest.message.payload.MessageDTO;
import com.dislinkt.chatapi.web.rest.message.payload.request.NewMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private AccountService accountService;

    public Page<MessageDTO> findByChat(String chatUuid, Pageable pageable) {

        Chat chat = chatService.findOneByUuidOrElseThrowException(chatUuid);

        Page<Message> messages = messageRepository.findByChatId(chat.getId(), pageable);

        return messages.map(message -> {
            MessageDTO dto = new MessageDTO();

            dto.setText(message.getText());
            dto.setUuid(message.getUuid());

            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setUsername(message.getFrom().getUsername());
            accountDTO.setName(message.getFrom().getName());
            accountDTO.setUuid(message.getFrom().getUuid());

            dto.setFrom(accountDTO);

            return dto;
        });
    }

    public MessageDTO insertMessage(String chatUuid, NewMessageRequest messageRequest) {

        Account account = accountService.findOneByUuidOrElseThrowException(messageRequest.getAccountUuid());

        Chat chat = chatService.findOneByUuidOrElseThrowException(chatUuid);

        Message message = new Message();
        message.setFrom(account);
        message.setText(messageRequest.getText());
        message.setChat(chat);

        messageRepository.save(message);

        MessageDTO dto = new MessageDTO();

        dto.setText(message.getText());
        dto.setUuid(message.getUuid());

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername(message.getFrom().getUsername());
        accountDTO.setName(message.getFrom().getName());
        accountDTO.setUuid(message.getFrom().getUuid());

        dto.setFrom(accountDTO);

        return dto;
    }
}
