package com.telran.ilCarro.tests;

import com.jayway.restassured.RestAssured;
import com.telran.ilCarro.dto.PutUserBaseDto;
import com.telran.ilCarro.dto.RegResponseDto;
import com.telran.ilCarro.dto.RegistrationDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RestAssuredIlCarroTests {

    @BeforeMethod
    public void ensurePreconditions() {
        RestAssured.baseURI = "https://java-3-ilcarro-team-b.herokuapp.com";

    }


    @Test
    public void registrUserPositiveTest() {

        String token = "bW9ybGEzQGdtYWlsLmNvbTpBYmEyNDY4MTM1Nw==";
        RegistrationDto registrDto = RegistrationDto.builder()
                .first_name("Lusy")
                .second_name("Miau")
                .build();

        String date = given().header("Authorization", token)
                .contentType("application/json")
                .body(registrDto)
                .post("registration")
                .then()
                .assertThat().statusCode(200)
                .extract().path("registration_date");
        System.out.println(date);

    }
    @Test
    public void putUserPositiveTest() {

        String token = "bW9ybGEzQGdtYWlsLmNvbTpBYmEyNDY4MTM1Nw==";
        PutUserBaseDto userBaseDto = PutUserBaseDto.builder()
                .first_name("Lusy")
                .second_name("Miau")
                .build();

        int status = RestAssured.given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(userBaseDto)
                .post("/user")
                .then()
                .assertThat().statusCode(200)
                .extract().path("status");
        System.out.println(status);
    }


    @Test
    public void deleteUserTest() {

        String token = "bW9ybGEzQGdtYWlsLmNvbTpBYmEyNDY4MTM1Nw==";

        int status = given().header("Authorization",token)
                .delete("/user")
                .then()
                .assertThat().statusCode(200)
                .extract().statusCode();
        System.out.println(status);

    }
}
