import java.util.ArrayDeque;
import java.util.Deque;

public class BracketProblemImpl {
    private Deque<Character> isValid = new ArrayDeque<Character>(4);

    /*
     * The method isBracket checks if a given char is a bracket
     */
    private boolean isBracket(char c) {
        switch (c) {
            case '(':
                return true;
            case ')':
                return true;
            case '[':
                return true;
            case ']':
                return true;
            case '{':
                return true;
            case '}':
                return true;
            default:
                return false;
        }
    }

    /*
     * The method isValidExpression first checks if the given expression is
     * valid by following all the rules defined in the description of the task
     */
    private boolean isValidExpression(String inputString) {
        int len = inputString.length();
        boolean firstAndFinalRound = inputString.charAt(0) == '(' && inputString.charAt(len - 1) == ')' && inputString.indexOf(')') == len - 1;
        boolean firstAndFinalSquare = inputString.charAt(0) == '[' && inputString.charAt(len - 1) == ']' && inputString.indexOf(']') == len - 1;
        boolean firstAndFinalCurly = inputString.charAt(0) == '{' && inputString.charAt(len - 1) == '}' && inputString.indexOf('}') == len - 1;
        boolean isValidOnFirstAndLast = firstAndFinalCurly || firstAndFinalRound || firstAndFinalSquare;

        if (!isValidOnFirstAndLast) {
            return false;
        }

        for (char c : inputString.toCharArray()) {
            int curInd = inputString.indexOf(c);
            if (isBracket(c)) {
                if (!isValid.isEmpty() && isValid.peek() == c)
                    return false;
                else if (c == '{' && curInd == 0 && inputString.charAt(len - 1) == '}' && isValid.isEmpty()) {
                    isValid.push(c);
                } else if (c == '[' && curInd == 0 && inputString.charAt(len - 1) == ']') {
                    isValid.push(c);
                } else if (c == '[' && curInd != 0 && !isValid.isEmpty() && isValid.peek() == '{' && isValid.peek() != '[') {
                    isValid.push(c);
                } else if (c == '(' && curInd == 0 && inputString.charAt(len - 1) == ')') {
                    isValid.push(c);
                } else if (c == '(' && curInd != 0 && !isValid.isEmpty() && isValid.peek() == '[') {
                    isValid.push(c);
                } else if (c == ')' && curInd != 0 && !isValid.isEmpty() && isValid.peek() == '(') {
                    isValid.pop();
                } else if (c == ']' && curInd != 0 && !isValid.isEmpty() && isValid.peek() == '[') {
                    isValid.pop();
                } else if (c == '}' && curInd == len - 1 && !isValid.isEmpty() && isValid.peek() == '{') {
                    isValid.pop();
                } else
                    return false;
            }

        }

        return true;

    }

    /*
     * Finally the method findSum gets a valid expression and finds the sum
     * following the task rules and restrictions.
     */
    String findSum(String inputString) {
        if (!isValidExpression(inputString)) {
            return "NO";
        } else {
            int sum = 0;
            int multiplier = 1;
            int counter = inputString.length() - 1;
            boolean inSquareBrackets = false;

            while (counter > 0) {
                counter--;
                char curChar = inputString.charAt(counter);

                if (Character.isDigit(curChar)) {
                    sum += (Character.getNumericValue(curChar) * multiplier);
                    multiplier *= 10;
                } else if (curChar == ']') {
                    inSquareBrackets = true;
                    multiplier = 2;
                } else if (curChar == '[') {
                    inSquareBrackets = false;
                    multiplier = 1;
                } else if (curChar == ')' && inSquareBrackets) {
                    multiplier = 4;
                } else if (curChar == ')' && !inSquareBrackets) {
                    multiplier = 2;
                } else if (curChar == '(' && !inSquareBrackets) {
                    multiplier = 1;
                } else if (curChar == '(' && inSquareBrackets) {
                    multiplier = 2;
                }
            }

            return String.valueOf(sum);
        }
    }
    /*
     * P.S. I know I could have made merged the two big loops (the one in
     * findSum and one in isValidExpression) since they both work for O(n)
     * complexity (where n is the lenghth of the input string) but I have
     * observed little to none performance gains on valid expressions and quite
     * bigger performance losses on non-valid once so I decided it's clearer for
     * the reader of the code and for me to have them both in different loops
     */

}
