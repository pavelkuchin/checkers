package com.checkers.server.controllers;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.Assert.assertCreated;

import com.eclipsesource.restfuse.*;
import com.eclipsesource.restfuse.annotation.Authentication;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import org.junit.Rule;
import org.junit.runner.RunWith;

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
        Destination destination = new Destination( this, "http://localhost:8080/game" );
        destination.getRequestContext().addHeader( "Content-Type", "application/json" );
        return destination;
    }

    @HttpTest(  method = Method.GET,
                path = "/1",
                authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getGame(){
        assertOk( response );
    }

    @HttpTest(  method = Method.GET,
            path = "/",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getGames(){
        assertOk( response );
    }

    @HttpTest(  method = Method.POST,
            path = "/",
            content = "{\n" +
                    "    \"gauid\": null,\n" +
                    "    \"name\": \"Just for test\",\n" +
                    "    \"description\": \"Testing game\",\n" +
                    "    \"type\": \"long\",\n" +
                    "    \"board\": \"10x10\",\n" +
                    "    \"state\": \"open\",\n" +
                    "    \"white\": 2,\n" +
                    "    \"black\": 1,\n" +
                    "    \"created\": 1369311377195,\n" +
                    "    \"modified\": 1369311377195,\n" +
                    "    \"lastLogin\": 1369311377195\n" +
                    "}",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void newGame(){
        assertCreated( response );
    }

    @HttpTest(  method = Method.GET,
            path = "/1/step",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getGameSteps(){
        assertOk( response );
    }

    @HttpTest(  method = Method.GET,
            path = "/1/laststep",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getGameLastStep(){
        assertOk( response );
    }

}
