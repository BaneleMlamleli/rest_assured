package com.learn;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
    
    @Test
    public void fakerData() {
        Faker faker = new Faker();
        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton
        String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
        System.out.println("Name: " + name);
        System.out.println("Firstname: " + firstName);
        System.out.println("Lastname: " + lastName);
        System.out.println("Street address: " + streetAddress);
    }
}
