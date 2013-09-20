package com.checkers.server.services;

import com.checkers.server.beans.Message;
import com.checkers.server.exceptions.ApplicationException;

import java.util.List;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface MessageService {
    Message newMessage(Message message) throws ApplicationException;
    List<Message> getMyMessages() throws ApplicationException;
}
