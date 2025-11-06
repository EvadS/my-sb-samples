package com.se.sample.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Resources", description = "Resources management and search endpoints")

@RestController
@RequestMapping("/api/resource")
public class ResourceMemoryChecker {

    private final Map<String, float[]> wordVectors = new HashMap<>();
    private final Map<String, float[]> wordVectors2 = new HashMap<>();

    @GetMapping("/unclosed-file")
    @Operation(
            summary = "Check memory usage unclosed file",
            description = "Check memory usage unclosed file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed"),
    })
    @ResponseBody
    public ResponseEntity<Void> checkMemoryUsageForUnclosedFile() throws IOException {
        loadTextFileNotClosed("static/stopwords.txt");
        return ResponseEntity.ok().build();
    }


    @GetMapping("/unclosed-resource")
    @Operation(
            summary = "Check memory usage unclosed file",
            description = "Check memory usage unclosed file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed"),
    })
    @ResponseBody
    public ResponseEntity<Void> checkMemoryUsageForNonClosedResource() throws Exception {
        testRequest();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/closed-file")
    @Operation(
            summary = "Check memory usage file",
            description = "Check memory usage file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed"),
    })
    @ResponseBody
    public ResponseEntity<Void> checkMemoryUsageForClosedFile() throws IOException {
        loadTextFileClosed("static/stopwords.txt");
        return ResponseEntity.ok().build();
    }

    private void loadTextFileClosed(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new ClassPathResource(filePath).getInputStream())))
        {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                String word = values[0];
                float[] vector = new float[values.length - 1];
                for (int i = 1; i < values.length; i++) {
                    vector[i - 1] = Float.parseFloat(values[i]);
                }

                this.wordVectors.put(word, vector);
            }
        }

        System.out.println("wordVectors size: " + wordVectors.size() );
    }
    private void loadTextFileNotClosed(String filePath) throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(new ClassPathResource(filePath).getInputStream());
        BufferedReader br = new BufferedReader(inputStreamReader);

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                String word = values[0];
                float[] vector = new float[values.length - 1];
                for (int i = 1; i < values.length; i++) {
                    vector[i - 1] = Float.parseFloat(values[i]);
                }
                this.wordVectors2.put(word, vector);
            }


        System.out.println("wordVectors size: " + wordVectors2.size() );
    }


    public  void testRequest() throws Exception {
        for (int i = 0; i < 1000000; i++) {
            URL url = new URL("http://www.google.com");
            java.net.URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            // rest of the code goes here
        }
    }

}
