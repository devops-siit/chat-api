package com.dislinkt.chatapi.repository;

import com.dislinkt.chatapi.domain.chat.message.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findByChatId(Long chatId, Pageable pageable);
}