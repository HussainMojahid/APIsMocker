package com.APIsMocker.mockserver.controller;

import java.io.IOException;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/xmlconvertor")
public class JsonComprator {

    // @GetMapping("/xmltojson")
    @PostMapping(path = "/xmltojson", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> convertor(@RequestBody String str)
            throws JsonMappingException, JsonProcessingException {
        JSONObject jsonObject = XML.toJSONObject(str);

        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }

    @GetMapping("/INVO")
    public ResponseEntity<String> convertor2()
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        ClassPathResource staticDataResource = new ClassPathResource("/Jsons/" + "test1" + ".json");
        ClassPathResource staticDataResource1 = new ClassPathResource("/Jsons/" + "test2" + ".json");

        // Read the first JSON file
        JsonNode exp = mapper.readTree(staticDataResource.getInputStream());

        // Read the second JSON file
        JsonNode act = mapper.readTree(staticDataResource1.getInputStream());

        // Compare the two JSON nodes and generate a report
        ArrayNode diffArray = JsonNodeFactory.instance.arrayNode();
        compareNodes(exp, act, diffArray, "");

        System.out.println("JSON comparison report:");
        System.out.println(diffArray);

        return ResponseEntity.status(HttpStatus.OK).body(diffArray.toString());
    }

    private static void compareNodes(JsonNode expectedNode, JsonNode actualNode, ArrayNode diffArray, String fieldNn) {

        if (expectedNode.equals(actualNode)) {

            return;
        }

        if (expectedNode.isObject()) {
            ObjectNode expectedObject = (ObjectNode) expectedNode;
            ObjectNode actualObject = (ObjectNode) actualNode;
            expectedObject.fieldNames().forEachRemaining(fieldName -> {
                JsonNode expectedValue = expectedObject.get(fieldName);
                JsonNode actualValue = actualObject.get(fieldName);
                if (expectedValue != null && actualValue != null) {
                    compareNodes(expectedValue, actualValue, diffArray, fieldName);
                } else {
                    String message = String.format("Field '%s' is missing or has a different value", fieldName);
                    diffArray.add(message);
                }
            });
        } else if (expectedNode.isArray()) {
            ArrayNode expectedArray = (ArrayNode) expectedNode;
            ArrayNode actualArray = (ArrayNode) actualNode;
            if (expectedArray.size() != actualArray.size()) {

                String message = String.format("Array size is different: expected=%d, actual=%d",
                        expectedArray.size(), actualArray.size());
                diffArray.add(message);
            } else {
                for (int i = 0; i < expectedArray.size(); i++) {
                    compareNodes(expectedArray.get(i), actualArray.get(i), diffArray, fieldNn);
                }
            }
        } else {
            String expectedValue = expectedNode.asText();
            String actualValue = actualNode.asText();
            System.out.println(expectedValue);
            if (!expectedValue.equals(actualValue)) {
                String message = String.format("Value is different: expected='%s', actual='%s'", expectedValue,
                        actualValue);
                diffArray.add(message);
            }
        }
    }
}
