package com.dislinkt.chatapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@SpringBootTest
@TestExecutionListeners(
	    listeners = TransactionalTestExecutionListener.class,
	    inheritListeners = false
	    
)
class ChatApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
