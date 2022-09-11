package com.dislinkt.chatapi.web.rest.chat;

import com.dislinkt.chatapi.service.chat.ChatService;
import com.dislinkt.chatapi.util.ReturnResponse;
import com.dislinkt.chatapi.web.rest.chat.payload.SimpleChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/chats")
public class ChatResource {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public ResponseEntity<Set<SimpleChatDTO>> findByAccount() {
        return ReturnResponse.entityGet(chatService.findByAccount());
    }

    @PostMapping
    public ResponseEntity<SimpleChatDTO> insertChat(@RequestParam String accountUuid) {
        return chatService.insertChat(accountUuid);
    }
}
