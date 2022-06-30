package com.dislinkt.chatapi.repository;

import com.dislinkt.chatapi.domain.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatsRepository extends JpaRepository<Chat, Long> {

}
