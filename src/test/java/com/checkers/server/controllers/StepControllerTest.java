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
 * Tests for {@link StepController}
 *
 * @author Pavel Kuchin
 */

@RunWith( HttpJUnitRunner.class )
public class StepControllerTest {

    @Rule
    public com.eclipsesource.restfuse.Destination description = getDestination();

    @Context
    private Response response;

    private Destination getDestination() {
        Destination destination = new Destination( this, "http://localhost:8080/step" );
        destination.getRequestContext().addHeader( "Content-Type", "application/json" );
        return destination;
    }

    @HttpTest(  method = Method.POST,
            path = "/",
            content = "{\n" +
                    "  \"suid\"  : null,\n" +
                    "  \"game\"  : 1,\n" +
                    "  \"user\"  : 1,\n" +
                    "  \"step\"  : \"1-3\",\n" +
                    "  \"created\": 1369311377195\n" +
                    "}",
            authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void newGame(){
        assertCreated( response );
    }

    @HttpTest(  method = Method.GET,
                path = "/1",
                authentications = {
                    @Authentication( type = AuthenticationType.BASIC, user = "admin", password = "admin" ) }
    )
    public void getStep(){
        assertOk( response );
    }
}
