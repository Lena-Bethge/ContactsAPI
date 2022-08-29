package com.telran.contacts.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.telran.contacts.dto.AuthRequestDto;
import com.telran.contacts.dto.ErrorDto;
import com.telran.contacts.dto.LoginRegResponseDto;
import com.telran.contacts.dto.RegistrRequestDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ContactAPITests {

    @Test
    public void loginHttpTest() throws IOException {
        String email = "lee@gm.com";
        String password = "Lusy_987654";

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString("{\n" +
                        "  \"email\": \"" + email +"\",\n" +
                        "  \"password\": \"" + password + "\"\n" +
                        "}", ContentType.APPLICATION_JSON)
                .execute();
        System.out.println(response);
        System.out.println("***************************************");

        String responseJson = response.returnContent().asString();

        System.out.println(responseJson);

        System.out.println("**************************************");
        JsonElement element = JsonParser.parseString(responseJson);
        JsonElement token = element.getAsJsonObject().get("token");
        System.out.println(token.getAsString());
    }

    @Test
    public void loginHttpTest1() throws IOException {

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("lee@gm.com")
                .password("Lusy_987654").build();

        Gson gson = new Gson();

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString(gson.toJson(requestDto),ContentType.APPLICATION_JSON)
                .execute();

        String responseJson = response.returnContent().asString();

        LoginRegResponseDto responseDto = gson.fromJson(responseJson,LoginRegResponseDto.class);
        System.out.println(responseDto);



    }



    @Test
    public void loginHttpNegativeTest2WithInvalidPassword() throws IOException {

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("lee@gm.com")
                .password("Lusy987654").build(); //удаление _ в пароле

        Gson gson = new Gson();

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/login")
                .bodyString(gson.toJson(requestDto), ContentType.APPLICATION_JSON)
                .execute();

        HttpResponse httpResponse = response.returnResponse();
        //System.out.println(httpResponse);
        System.out.println(httpResponse.getStatusLine().getStatusCode());

        InputStream is = httpResponse.getEntity().getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);

        }
    }


    @Test
    public void registrHttpNegativeTest() throws IOException {

        RegistrRequestDto requestDto = RegistrRequestDto.builder()
                .email("lee@gm.com")
                .password("Lusy_987654").build(); //удаление _ в пароле

        Gson gson = new Gson();

        Response response = Request.Post("https://contacts-telran.herokuapp.com/api/registration")
                .bodyString(gson.toJson(requestDto),ContentType.APPLICATION_JSON)
                .execute();

        HttpResponse httpResponse = response.returnResponse();
        //System.out.println(httpResponse);
        System.out.println(httpResponse.getStatusLine().getStatusCode());

    }

}
