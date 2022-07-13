package com.dislinkt.chatapi.web.rest.chat;

import com.dislinkt.chatapi.service.chat.ChatService;
import com.dislinkt.chatapi.util.ReturnResponse;
import com.dislinkt.chatapi.web.rest.chat.payload.SimpleChatDTO;
import com.dislinkt.chatapi.web.rest.chat.payload.request.NewChatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/chats")
public class ChatResource {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{loggedAccountUuid}")
    public ResponseEntity<Set<SimpleChatDTO>> findByAccount(@PathVariable String loggedAccountUuid) {
        return ReturnResponse.entityGet(chatService.findByAccount(loggedAccountUuid));
    }

    @PostMapping
    public ResponseEntity<SimpleChatDTO> insertChat(@RequestBody NewChatRequest chatRequest) {
        return chatService.insertChat(chatRequest);
    }
}
