package com.checkers.server.services.referee;

import com.checkers.server.exceptions.ApplicationException;
import com.checkers.server.exceptions.CheckersException;

/**
 *
 *
 * @author Pavel Kuchin
 */
public interface RefereeCollection {
    Referee getRefereeByGuid(Long gauid) throws CheckersException, ApplicationException;
}
