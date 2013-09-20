package com.checkers.server.dao;

import com.checkers.server.beans.Message;
import com.checkers.server.exceptions.ApplicationException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * This implementation contains all messages in HashMap
 *
 * @author Pavel Kuchin
 */
@Repository("messageDao")
public class MessageDaoImpl implements MessageDao{

    private Map<Long, Message>          messages = new HashMap<Long, Message>();
    private Map<Long, List<Message>>    messagesByTo = new HashMap<Long, List<Message>>();

    private static Long lastMuid = 0L;

    @Override
    public Message newMessage(String message, Long from, Long to) {
        Message msg = new Message();

        msg.setMuid(lastMuid);
        msg.setMessage(message);
        msg.setFromUuid(from);
        msg.setToUuid(to);

        msg.setCreated(new Date());

        this.messages.put(lastMuid, msg);

        if(!this.messagesByTo.containsKey(to)){
            this.messagesByTo.put(to, new ArrayList());
        }

        this.messagesByTo.get(to).add(msg);

        lastMuid++;

        return msg;
    }

    @Override
    public Message getMessage(Long muid) {
        return messages.get(muid);
    }

    @Override
    public List<Message> getMessagesByToWithRemoving(Long to) throws ApplicationException {
        List<Message> result;

        result = messagesByTo.get(to);

        if(result == null){
            throw new ApplicationException(4L, "There are no new messages for the current user");
        }

        for(Message m : messagesByTo.get(to)){
            messages.remove(m.getMuid());
        }

        messagesByTo.remove(to);

        return result;
    }

    @Override
    public Boolean existMessages(Long to) {
        if(messagesByTo.containsKey(to) && !messagesByTo.get(to).isEmpty()){
            return true;
        } else {
            return false;
        }
    }
}
