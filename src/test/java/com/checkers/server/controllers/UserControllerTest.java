package com.checkers.server.controllers;

import static com.eclipsesource.restfuse.Assert.assertOk;
import static com.eclipsesource.restfuse.Assert.assertUnauthorized;
import static com.eclipsesource.restfuse.Assert.assertCreated;

import com.eclipsesource.restfuse.*;
import com.eclipsesource.restfuse.annotation.Authentication;
import org.junit.Rule;
import org.junit.runner.RunWith;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;

/**
 * Tests for {@link UserController}
 *
 * @author Pavel Kuchin
 */

@RunWith( HttpJUnitRunner.class )
public class UserControllerTest {

    @Rule
    public com.eclipsesource.restfuse.Destination description = getDestination();

    @Context
    private Response response;

    private Destination getDestination() {
        Destination destination = new Destination( this, "http://localhost:8080/user" );
        destination.getRequestContext().addHeader( "Content-Type", "application/json" );
            return destination;
    }

    @HttpTest(  method = Method.GET,
                path = "/")
    public void getUsersUnauthorized(){
        assertUnauthorized( response );
    }

    @HttpTest(  method = Method.GET,
                path = "/",
                authentications = {
                        @Authentication( type = AuthenticationType.BASIC, user = "invalid", password = "invalid" )}
    )
    public void getUsersWithInvalidCredentials(){
        assertUnauthorized( response );
    }

    @HttpTest(  method = Method.GET,
                path = "/",
                authentications = {
                        @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getUsers(){
        assertOk( response );
    }

    @HttpTest(  method = Method.GET,
                path = "/1",
                authentications = {
                        @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getUser(){
        assertOk( response );
    }

    @HttpTest(  method = Method.GET,
            path = "/1/game",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getUserGames(){
        assertOk( response );
    }

    @HttpTest(  method = Method.POST,
            path = "/",
            content = "{\n" +
                    "    \"uuid\"\t\t: null,\n" +
                    "    \"login\"\t\t: \"testLogin\",\n" +
                    "    \"firstName\"\t: \"testFirstName\",\n" +
                    "    \"lastName\"\t: \"testLastName\",\n" +
                    "    \"email\"\t\t: \"test@checkers.com\",\n" +
                    "    \"password\"\t: \"password\",\n" +
                    "    \"enabled\"\t: true,\n" +
                    "    \"created\"\t: 1369311377195,\n" +
                    "    \"modified\"\t: 1369311377195,\n" +
                    "    \"lastLogin\"\t: 1369311377195\n" +
                    "}",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void newUser(){
        assertCreated( response );
    }
}
