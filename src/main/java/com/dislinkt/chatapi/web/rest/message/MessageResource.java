package com.dislinkt.chatapi.web.rest.message;

import com.dislinkt.chatapi.service.message.MessageService;
import com.dislinkt.chatapi.util.ReturnResponse;
import com.dislinkt.chatapi.web.rest.message.payload.MessageDTO;
import com.dislinkt.chatapi.web.rest.message.payload.request.NewMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageResource {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{chatUuid}")
    public ResponseEntity<Page<MessageDTO>> findByChat(@PathVariable String chatUuid, Pageable pageable) {
        return ReturnResponse.entityGet(messageService.findByChat(chatUuid, pageable));
    }

    @PostMapping("/{chatUuid}")
    public ResponseEntity<MessageDTO> insertMessage(@PathVariable String chatUuid,
                                                    @RequestBody NewMessageRequest messageRequest) {
        return ReturnResponse.entityCreated(messageService.insertMessage(chatUuid, messageRequest));
    }
}
