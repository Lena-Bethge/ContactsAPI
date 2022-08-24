package com.telran.ilCarro.tests;

import com.jayway.restassured.RestAssured;
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
        RegistrationDto registrDto = RegistrationDto.builder()
                .first_name("Lusy")
                .second_name("Mia")
                .email("miau@gmail.com")
                .password("A_9876543Aa$")
                .build();

        RegResponseDto responseDto = given()
                .contentType("application/json")
                .body(registrDto)
                .post("registration")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(RegResponseDto.class);

        System.out.println(responseDto.getToken());

    }

        //given().header("Authorization")

}
