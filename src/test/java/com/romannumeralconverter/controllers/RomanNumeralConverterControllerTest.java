package com.romannumeralconverter.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RomanNumeralConverterControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testFromRomanNumeralWhenGivenValidRomanNum() {
        final String baseUrl = "http://localhost:" + port + "/romannum/fromromannumeral";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("romanNum", "I");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("{\"code\":\"OK\",\"msg\":null,\"data\":1}", response.getBody());
    }

    @Test
    public void testFromRomanNumeralWhenGivenInvalidRomanNum() {
        final String baseUrl = "http://localhost:" + port + "/romannum/fromromannumeral";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("romanNum", "IIX");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("{\"code\":\"BAD_REQUEST\",\"msg\":\"Error: invalid roman numeral. romanNumeral=IIX\",\"data\":null}", response.getBody());
    }

    @Test
    public void testToRomanNumeralWhenGivenValidNum() {
        final String baseUrl = "http://localhost:" + port + "/romannum/toromannumeral";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("numberStr", "1");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("{\"code\":\"OK\",\"msg\":null,\"data\":\"I\"}", response.getBody());
    }

    @Test
    public void testToRomanNumeralWhenGivenInvalidNum() {
        final String baseUrl = "http://localhost:" + port + "/romannum/toromannumeral";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("numberStr", "0");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("{\"code\":\"BAD_REQUEST\",\"msg\":\"Error: Roman numerals cannot be zero or negative. number=0\",\"data\":null}", response.getBody());
    }

}








