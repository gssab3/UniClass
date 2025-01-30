package com.example.integration;

import it.unisa.uniclass.utenti.controller.LoginServlet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@ExtendWith(ArquillianExtension.class)
public class LoginServletIT {

    @ArquillianResource
    private URL deploymentUrl;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(LoginServlet.class)
                .addPackage(it.unisa.uniclass.utenti.model.Accademico.class.getPackage())
                .addPackage(it.unisa.uniclass.utenti.service.AccademicoService.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testSuccessfulLogin() {
        given()
                .baseUri(deploymentUrl.toString())
                .formParam("email", "test@example.com")
                .formParam("password", "password")
                .when()
                .post("/Login")
                .then()
                .statusCode(302)
                .header("Location", containsString("/Home"));
    }

    @Test
    public void testFailedLogin() {
        given()
                .baseUri(deploymentUrl.toString())
                .formParam("email", "test@example.com")
                .formParam("password", "wrongpassword")
                .when()
                .post("/Login")
                .then()
                .statusCode(302)
                .header("Location", containsString("/Login.jsp?action=error"));
    }
}