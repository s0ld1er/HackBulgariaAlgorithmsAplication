import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncryptedMessageImpl {

    String decryptEncryptedMessage(String input) {
        final Map<Character, Integer> indexesOfAlphabet = new HashMap<Character, Integer>();
        final Map<Character, Integer> mapOfMessage = new HashMap<Character, Integer>();
        final Map<Character, Integer> mapOfKey = new HashMap<Character, Integer>();
        final List<Character> chars = new ArrayList<Character>();

        final StringBuilder key = new StringBuilder();
        final StringBuilder alphabet = new StringBuilder();
        final StringBuilder encrMessage = new StringBuilder();
        final StringBuilder finalResult = new StringBuilder();
        final StringBuilder keyWithEncrLen = new StringBuilder();
        final StringBuilder encryptedBeforeSplit = new StringBuilder().append(input.substring(input.length() >> 1, input.length())).append(
                input.substring(0, input.length() >> 1));

        // get lengths of the key, alphabet and the lenght of the String key
        final int lenOfKey = Integer.parseInt(encryptedBeforeSplit.substring(encryptedBeforeSplit.lastIndexOf("~") + 1));
        final int lenOfAlpahabet = Integer.parseInt(encryptedBeforeSplit.substring(0, encryptedBeforeSplit.indexOf("~")));
        final int lenOfKeyString = String.valueOf(lenOfKey).length();

        // find key and enrypted message
        key.append(encryptedBeforeSplit.substring(encryptedBeforeSplit.lastIndexOf("~") - lenOfKey, encryptedBeforeSplit.lastIndexOf("~")));
        encrMessage.append(encryptedBeforeSplit.substring(encryptedBeforeSplit.indexOf("~") + lenOfAlpahabet + 1,
                input.length() - lenOfKey - 1 - lenOfKeyString).toString());
        final int lenOfEncMessage = encrMessage.length();

        // find alphabet and all indexes of letters and map of indexes of the
        // encrypted message
        for (int i = 0; i < lenOfAlpahabet; i++) {
            alphabet.append(encryptedBeforeSplit.charAt(encryptedBeforeSplit.indexOf("~") + 1 + i));
            final char curChar = alphabet.toString().charAt(i);
            indexesOfAlphabet.put(curChar, i);
            mapOfMessage.put(encrMessage.charAt(i), indexesOfAlphabet.get(encrMessage.charAt(i)));
            chars.add(curChar);
        }

        // find the key with the size of the message
        for (int i = 0; i < lenOfEncMessage; i++) {
            keyWithEncrLen.append(key.charAt(i % lenOfKey));
        }

        // fill map of indexes of the key
        for (int i = 0; i < lenOfKey; i++) {
            mapOfKey.put(key.charAt(i), indexesOfAlphabet.get(key.charAt(i)));
        }

        // put all indexes in arrays to find the indexes of the original message
        int[] originalMessageIndArr = new int[lenOfEncMessage];
        int[] encrMessageIndArr = new int[lenOfEncMessage];
        int[] keyIndArr = new int[lenOfEncMessage];

        // decrypt the indexes of the original message
        for (int i = 0; i < lenOfEncMessage; i++) {
            keyIndArr[i] = mapOfKey.get(keyWithEncrLen.charAt(i % lenOfKey));
            encrMessageIndArr[i] = indexesOfAlphabet.get(encrMessage.charAt(i));

            if (encrMessageIndArr[i] >= keyIndArr[i]) {
                originalMessageIndArr[i] = encrMessageIndArr[i] - keyIndArr[i];
            } else {
                originalMessageIndArr[i] = lenOfAlpahabet + encrMessageIndArr[i] - keyIndArr[i];
            }

            finalResult.append(chars.get(originalMessageIndArr[i]));
        }

        return finalResult.toString();
    }
}
