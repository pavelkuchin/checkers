package com.checkers.server.controllers;

import com.checkers.server.beans.ExceptionMessage;
import com.checkers.server.beans.Game;
import com.checkers.server.beans.Message;
import com.checkers.server.exceptions.ApplicationException;
import com.checkers.server.services.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 *
 *
 *
 * @author Pavel Kuchin
 */

@Controller
@RequestMapping(value="/messages")
public class MessageController {
    static Logger log = Logger.getLogger(MessageController.class.getName());

    @Autowired
    MessageService messagesService;

    /**
     * <h3>/messages</h3>
     *
     * <b>Method:</b> GET
     * <b>Description:</b> Returns yours (authorized user) messages, and permanently removes them from server.
     * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
     */
    @RequestMapping(value="", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public @ResponseBody
    List<Message> getMessages() throws ApplicationException {
        log.info("Returning all messages for authorized user");

        return messagesService.getMyMessages();
    }

    /**
     * <h3>/messages</h3>
     *
     * <b>Method:</b> POST
     * <b>Description:</b> post a message to user
     * <b>Allowed roles:</b> ROLE_USER, ROLE_ADMIN
     */
    @RequestMapping(value="", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Message newMessage(@Valid @RequestBody Message message) throws ApplicationException {
        log.info("Message: \"" + message.getMessage() + "\" posted");

        return messagesService.newMessage(message);
    }


    /**
     *
     * EXCEPTION HANDLERS
     *
     */

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionMessage handleApplicationException(ApplicationException e){
        log.warn(e);

        return e.getExceptionMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionMessage internalException(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        log.error("Message: " + e.getMessage());
        log.error("StackTrace: " + sw.toString());

        ExceptionMessage em = new ExceptionMessage();

        em.setCode(4L);
        em.setMessage(e.getMessage());
        em.setDetailsURL("https://github.com/pavelkuchin/checkers/wiki/Errors#code-4");

        return em;
    }

}
