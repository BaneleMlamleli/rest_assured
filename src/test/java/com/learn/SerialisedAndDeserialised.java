package com.learn;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerialisedAndDeserialised {
    
    @Test
    // In this method we're converting POJO to JSON. This is Serialization 
    public void serialisation() {
        String course[] = { "Java", "Python", "System Design" };
        // Created a Java object using POJO class
        Student student = new Student("Sam", "Cape Town", "0213456987", course);

        // Converting Java object to JSON object
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
            System.out.println(jsonData);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    // In this method we're converting JSON to POJO. This is De-Serialization
    public void deserialisation() {
        String jsonData = "{\r\n"
            + " \"name\" : \"Sam\","
            + " \"location\" : \"Cape Town\","
            + " \"phone\" : \"0213456987\","
            + " \"course\" : [ \"Java\", \"Python\", \"System Design\" ]\r\n"
            + "}";
        
        // Converting JSON data to POJO object
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Student stdnt = objectMapper.readValue(jsonData, Student.class);
            System.out.println(stdnt.toString());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
