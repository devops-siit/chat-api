package com.dislinkt.chatapi.service;

import static com.dislinkt.chatapi.constants.ChatConstants.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.dislinkt.chatapi.domain.chat.Chat;
import com.dislinkt.chatapi.service.chat.ChatService;
import com.dislinkt.chatapi.web.rest.chat.payload.SimpleChatDTO;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestExecutionListeners(
	    listeners = {TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class},
	    inheritListeners = false
	    
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChatServiceIntegrationTest {

	@Autowired
	private ChatService service;
	
	@BeforeAll
	public void init() {
		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
	
		Set<? extends GrantedAuthority> auth = new HashSet<>(); 
		UserDetails principal = new User(DB_CHAT_ACCOUNT_USERNAME_1, "aPassword", auth);
		Mockito.when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(principal);	
		
	}
	
	@Test
	@Transactional
	public void testFindByAccount() {
		Set<SimpleChatDTO> found = service.findByAccount(); 
		assertEquals(1, found.size());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testInsertChat() {
		ResponseEntity<SimpleChatDTO> response = service.insertChat(DB_CHAT_ACCOUNT_UUID_3);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testFindOneByUuidOrElseThrowException() {
		
		Chat chat = service.findOneByUuidOrElseThrowException(DB_CHAT_UUID_1);
		assertEquals(DB_CHAT_UUID_1, chat.getUuid());	
	}
}
