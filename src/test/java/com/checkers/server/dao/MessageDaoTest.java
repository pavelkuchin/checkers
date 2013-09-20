package com.checkers.server.dao;

import com.checkers.server.beans.Message;
import com.checkers.server.exceptions.ApplicationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests for {@link MessageDaoImpl}
 *
 * @author Pavel Kuchin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-context.xml")
public class MessageDaoTest {
    @Autowired
    MessageDao messageDao;

    @Test
    public void newMessage() throws ApplicationException {
        Message msg = new Message();

        msg.setMessage("Test message");
        msg.setToUuid(2L);
        msg.setFromUuid(1L);

        messageDao.newMessage(msg.getMessage(), msg.getFromUuid(), msg.getToUuid());
        messageDao.newMessage(msg.getMessage(), msg.getFromUuid(), msg.getToUuid());
        messageDao.newMessage(msg.getMessage(), msg.getFromUuid(), msg.getToUuid());

        Assert.assertEquals("Not all messages has been received.", 3L, messageDao.getMessagesByToWithRemoving(2L).size());
    }
}
