/**
 * This is a simple program to find all palindrome rotations of given string
 * 
 * @author Jivko Todorov
 * @see TestClass.java for unit tests
 * @return String with the answer of all rotations or "NONE" if there are none
 *         existent
 */

public class Palindrome implements PalindromeImpl {
    public Palindrome() {
    }

    /*
     * The method isPalindrome checks if a given string is a lexicographical
     * palindrome or not
     */

    public final boolean isPalindrome(String argument) {
        for (int i = 0; i < argument.length() >> 1; i++) {
            if (argument.charAt(i) != argument.charAt(argument.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public final String allRotations(final String inputWord) {
        StringBuilder finalAnswer = new StringBuilder();
        StringBuilder currentWord = new StringBuilder();

        // initial check if the given word is a palindrome - add it to the
        // answer string
        if (isPalindrome(inputWord)) {
            finalAnswer.append(inputWord).append(System.lineSeparator());
        }

        // i have observated that if we rotate the string and check first and
        // last chars we are actually checking the i-th and the i+1 -th char
        // which can help us reduce our complexity
        for (int i = inputWord.length() - 1; i > 0; i--) {
            if (inputWord.charAt(i) == inputWord.charAt(i - 1)) {
                currentWord.append(inputWord.substring(i)).append(inputWord.substring(0, i));
                if (isPalindrome(currentWord.toString())) {
                    finalAnswer.append(currentWord).append(System.lineSeparator());
                }
                currentWord.setLength(0);
            }
        }

        return finalAnswer.length() > 0 ? finalAnswer.toString() : "NONE";
    }
}
