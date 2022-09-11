package com.dislinkt.chatapi.service;
import static com.dislinkt.chatapi.constants.MessageConstants.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.dislinkt.chatapi.service.message.MessageService;
import com.dislinkt.chatapi.web.rest.message.payload.MessageDTO;
import com.dislinkt.chatapi.web.rest.message.payload.request.NewMessageRequest;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestExecutionListeners(
	    listeners = {TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class},
	    inheritListeners = false
	    
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageServiceIntegrationTest {

	@Autowired
	private MessageService service;
	
	@Test
	@Transactional
	public void testFindByChat() {
		
		Pageable pageable = PageRequest.of(PAGEABLE_PAGE, PAGEABLE_SIZE);
		Page<MessageDTO> found = service.findByChat(DB_MESSAGE_CHAT_UUID_1, pageable);
		assertEquals(1, found.getNumberOfElements());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testInsertMessage() {
		NewMessageRequest req = new NewMessageRequest();
		req.setAccountUuid(NEW_MESSAGE_FROM_ACCOUNT_UUID_1);
		req.setText(NEW_MESSAGE_TEXT);
		
		MessageDTO message = service.insertMessage(NEW_MESSAGE_CHAT_UUID, req);
		
		assertEquals(NEW_MESSAGE_TEXT, message.getText());
	}
}
