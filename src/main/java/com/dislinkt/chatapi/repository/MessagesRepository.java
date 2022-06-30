package com.dislinkt.chatapi.repository;

import com.dislinkt.chatapi.domain.chat.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagesRepository extends JpaRepository<Message, Long> {

}