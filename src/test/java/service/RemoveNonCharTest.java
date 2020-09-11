package service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class RemoveNonCharTest {


    @Test
    void shouldCheckPositiveCharacterInput() {
        String testString = "Whatever848InHere";
        String expectedOutput = "WhateverInHere";

        String responseString = RemoveNonChar.removeNonAlphabetChars(testString);
        Assert.assertEquals(expectedOutput, responseString);
    }


    //should check if removes alphabetical letters from user input
    @Test
    void shouldCheckLankCharacterInput() {
        String testString = "221544862khnbgAA5521";
        String expectedOutput = "2215448625521";

        String responseString = RemoveNonChar.removeAlphabetChars(testString);
        Assert.assertEquals(expectedOutput, responseString);
    }

}