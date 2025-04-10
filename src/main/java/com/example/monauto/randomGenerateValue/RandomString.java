package com.example.monauto.randomGenerateValue;

import com.example.monauto.enumFile.TypeCarburant;
import com.example.monauto.enumFile.TypeSeller;

import java.util.Random;

public class RandomString {
    private final TypeCarburant[] strings={TypeCarburant.ELECTRIQUE, TypeCarburant.ESSENCE, TypeCarburant.DIESEL};
    private final TypeSeller[] sellers={TypeSeller.ENTREPRISE, TypeSeller.PARTICULIER};

    public RandomString() {
    }

    public TypeCarburant RandomStringMethod() {
        Random random = new Random();

        // Generate a random index
        int randomIndex = random.nextInt(strings.length);

        // Get the random string
        return strings[randomIndex];
    }

    public TypeSeller RandomSellerMethod() {
        Random random = new Random();
        int randomIndex = random.nextInt(sellers.length);
        return sellers[randomIndex];
    }
}
