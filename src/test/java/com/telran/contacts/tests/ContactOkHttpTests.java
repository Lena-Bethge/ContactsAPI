package com.telran.contacts.tests;

import com.google.gson.Gson;
import com.telran.contacts.dto.AuthRequestDto;
import com.telran.contacts.dto.ErrorDto;
import com.telran.contacts.dto.LoginRegResponseDto;
import com.telran.contacts.dto.RegistrRequestDto;
import okhttp3.*;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ContactOkHttpTests {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Test
    public void loginNegativeTestWithInvalidEmail() throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("leegm.com")
                .password("Lusy_987654").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);
        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();

        ErrorDto errorDto = gson.fromJson(responseJson,ErrorDto.class);
        System.out.println(errorDto.getCode());
        System.out.println(errorDto.getMessage());

    }

    @Test
    public void loginTest() throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("leegm.com")
                .password("Lusy_987654").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);
        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();

        if (response.isSuccessful()) {
            LoginRegResponseDto responseDto = gson.fromJson(responseJson,LoginRegResponseDto.class);
            System.out.println(responseDto.getToken());
        }else{
            ErrorDto errorDto = gson.fromJson(responseJson, ErrorDto.class);
            System.out.println(errorDto.getCode());
            System.out.println(errorDto.getMessage());
        }


        ErrorDto errorDto = gson.fromJson(responseJson,ErrorDto.class);
        System.out.println(errorDto.getCode());
        System.out.println(errorDto.getMessage());

    }


    @Test
    public void RegistrNegativeTestWithInvalidEmail() throws IOException {

        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        RegistrRequestDto requestDto = RegistrRequestDto.builder()
                .email("lee@gmcom")
                .password("Lusy_987654").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);
        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/registration")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();

        ErrorDto errorDto = gson.fromJson(responseJson,ErrorDto.class);
        System.out.println(errorDto.getCode());
        System.out.println(errorDto.getMessage());

    }


}
