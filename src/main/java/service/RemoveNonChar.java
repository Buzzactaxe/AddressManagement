package service;

import org.apache.commons.lang3.StringUtils;

public class RemoveNonChar {

    /**
     * removes all non alphabetical characters from string
     *
     * @param userInput
     * @return
     */
    public static String removeNonAlphabetChars(String userInput) {
        String availableChars = "1234567890.-/_";
        StringBuilder output = new StringBuilder();
        for (char c : userInput.toCharArray()) {
            if (!availableChars.contains("" + c)) {
                output.append(c);
            }
        }
      return StringUtils.capitalize(output.toString());
    }

    //removes alphabetical letters from user input
    public static String removeAlphabetChars(String userInput) {
        String availableChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.-/_";
        StringBuilder output = new StringBuilder();
        for (char c : userInput.toCharArray()) {
            if (!availableChars.contains("" + c)) {
                output.append(c);
            }
        }
        return output.toString();
    }
}
