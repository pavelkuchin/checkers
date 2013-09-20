package com.checkers.server.services;

import com.checkers.server.Consts;
import com.checkers.server.Context;
import com.checkers.server.beans.Message;
import com.checkers.server.dao.MessageDao;
import com.checkers.server.dao.UserDao;
import com.checkers.server.events.MessageEvent;
import com.checkers.server.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageDao  messageDao;

    @Autowired
    private UserDao     userDao;

    @Autowired
    private Context     context;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public Message newMessage(Message message) throws ApplicationException {
        Message result;

        Long from = userDao.getUserByLogin(Context.getAuthLogin()).getUuid();

        //Check, does user exits?
        Long to = userDao.getUser(message.getToUuid()).getUuid();

        if(message.getMessage() == null){
            throw new ApplicationException(6L, "Message cannot be null");
        }
        if(message.getMessage().length() > Consts.MAX_MESSAGE_LENGTH){
            throw new ApplicationException(3L, "The message length exceeds allowed.");
        }

        result = messageDao.newMessage(message.getMessage(), from, to);

        context.getApplicationContext()
                .publishEvent(new MessageEvent(context.getApplicationContext(), result));

        return result;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
    @Override
    public List<Message> getMyMessages() throws ApplicationException {
        Long to = userDao.getUserByLogin(Context.getAuthLogin()).getUuid();

        return messageDao.getMessagesByToWithRemoving(to);
    }
}
