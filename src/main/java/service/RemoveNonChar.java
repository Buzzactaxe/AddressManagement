package service;

import org.apache.commons.lang3.StringUtils;

public class RemoveNonChar {

    /**
     * removes all non alphabetical characters from string
     */
    public static String removeNonAlphabetChars(String userInput) {
        var availableChars = "1234567890.-/_";
        var output = new StringBuilder();
        char[] charArray = userInput.toCharArray();
        for (char c : charArray) {
            if (!availableChars.contains("" + c)) {
                output.append(c);
            }
        }
        return StringUtils.capitalize(output.toString());
    }

    //removes alphabetical letters from user input
    public static String removeAlphabetChars(String userInput) {
        var availableChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.-/_";
        StringBuilder output = new StringBuilder();
        char[] charArray = userInput.toCharArray();
        for (char c : charArray) {
            if (!availableChars.contains("" + c)) {
                output.append(c);
            }
        }
        return output.toString();
    }
}
