package org.sash.getting.started.async;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/sash")
                .then()
                .statusCode(200)
                .body(is("hello Sash "));
    }

    @Test
    public void testGreetingEndpoint() {
        String uuid = UUID.randomUUID().toString();
        given()
                .pathParam("name", uuid)
                .when().get("/sash/async/{name}")
                .then()
                .statusCode(200)
                .body(is("Greetings from Quarkus " + uuid));
    }

}
