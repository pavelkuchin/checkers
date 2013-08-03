package com.checkers.server.controllers;

import com.eclipsesource.restfuse.*;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static com.eclipsesource.restfuse.Assert.*;

/**
 * Tests for {@link GameController}
 *
 * @author Pavel Kuchin
 */

@RunWith( HttpJUnitRunner.class )
public class GameControllerTest {

    @Rule
    public com.eclipsesource.restfuse.Destination description = getDestination();

    @Context
    private Response response;

    private Destination getDestination() {
        Destination destination = new Destination( this, "http://localhost:8080/games" );
        destination.getRequestContext().addHeader( "Content-Type", "application/json" );
        return destination;
    }

    /**
     * Test for /games
     * Method: GET
     */
    @HttpTest(  method = Method.GET,
            path = "",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getGames(){
        assertOk( response );
    }

    /**
     * Test for /games?field={field}&value={value}
     * Method: GET
     */
    @HttpTest(  method = Method.GET,
            path = "?field=state&value=open",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getGamesFiltered(){
        assertOk( response );
    }

    /**
     * Test for /games/{gauid}
     * Method: GET
     */
    @HttpTest(  method = Method.GET,
            path = "/1",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getGame(){
        assertOk( response );
    }

    /**
     * Test for /games
     * Method: POST
     */
    @HttpTest(  method = Method.POST,
            path = "",
            content = "{\n" +
                    "    \"name\": \"Just for test\",\n" +
                    "    \"description\": \"Testing game\",\n" +
                    "    \"type\": \"offline\",\n" +
                    "    \"board\": \"10x10\",\n" +
                    "    \"state\": \"open\"\n" +
                    "}",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void newGame(){
        assertCreated( response );
    }

    /**
     * Test for /games/{gauid}
     * Method: PUT
     */
    @HttpTest(  method = Method.PUT,
            path = "/1",
            content = "{\n" +
                    "    \"name\": \"Changed name\",\n" +
                    "    \"description\": \"Testing game\"" +
                    "}",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void modGame(){
        assertOk( response );
    }

    /**
     * Test for /games/{gauid}?action=join
     * Method: PUT
     */
    @HttpTest(  method = Method.PUT,
            path = "/1?action=join",
            content = "{}",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "user", password = "user" ) }
    )
    public void joinGame(){
        assertOk( response );
    }

    /**
     * Test for /games/{gauid}?action=close
     * Method: PUT
     */
    @HttpTest(  method = Method.PUT,
            path = "/1?action=close",
            content = "{}",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void closeGame(){
        assertOk( response );
    }

    /**
     * Test for /games/{gauid}/steps
     * Method: GET
     */
    @HttpTest(  method = Method.GET,
            path = "/1/steps",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getGameSteps(){
        assertOk( response );
    }

    /**
     * Test for /games/{gauid}/steps
     * Method: POST
     */
    @HttpTest(  method = Method.POST,
            path = "/1/steps",
            content = "{\n" +
                    "  \"step\"  : \"a1-b3\"\n" +
                    "}",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void newStepInGame(){
        assertBadRequest(response);
    }

    /**
     * Test for /games/{gauid}/steps?mode=opponentStepAsync
     * Method: GET
     */
    //TODO Async doesn't supported by servlet than involved in testing
    /*
    @HttpTest(  method = Method.GET,
            path = "/1/steps?mode=opponentStepAsync",

            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getGameLastStepAsync(){
        assertOk( response );
    }
    */

    /**
     * Test for /games/{gauid}/steps?mode=opponentStep
     * Method: GET
     */
    @HttpTest(  method = Method.GET,
            path = "/1/steps?mode=opponentStep",

            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getGameLastStep(){
        assertBadRequest(response);
    }
}
