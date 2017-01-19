package ru.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = { Application.class })
public class PersistTests {

	@Autowired
	private PersistOrder persist;

	@Autowired
	private PersistTicket tickets;

	@Test
	public void testUpdate1() {
		persist.change(1, "PROCESS");
		tickets.change(2, "T_PROCESS");
	}

}