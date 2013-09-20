package com.checkers.server.dao;

import com.checkers.server.beans.Message;
import com.checkers.server.exceptions.ApplicationException;

import java.util.List;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */
public interface MessageDao {
    Message newMessage(String message, Long from, Long to);
    Message getMessage(Long muid);
    List<Message> getMessagesByToWithRemoving(Long to) throws ApplicationException;
    Boolean existMessages(Long to);
}
