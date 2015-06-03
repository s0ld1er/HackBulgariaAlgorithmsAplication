import java.util.HashMap;
import java.util.Map;

public class EncryptedMessageImpl {
    private Map<Character, Integer> indexesOfAlphabet = new HashMap<Character, Integer>();
    private Map<Character, Integer> mapOfMessage = new HashMap<Character, Integer>();
    private Map<Character, Integer> mapOfKey = new HashMap<Character, Integer>();

    public EncryptedMessageImpl() {

    }

    String decryptEncryptedMessage(String input) {
        StringBuilder key = new StringBuilder();
        StringBuilder alphabet = new StringBuilder();
        StringBuilder encrMessage = new StringBuilder();
        StringBuilder finalResult = new StringBuilder();
        StringBuilder keyWithEncrLen = new StringBuilder();
        StringBuilder encryptedBeforeSplit = new StringBuilder().append(input.substring((input.length() >> 1), input.length())).append(
                input.substring(0, input.length() >> 1));

        // get lengths of the key, alphabet and the lenght of the String key
        int lenOfKey = Integer.parseInt(encryptedBeforeSplit.substring(encryptedBeforeSplit.lastIndexOf("~") + 1));
        int lenOfAlpahabet = Integer.parseInt(encryptedBeforeSplit.substring(0, encryptedBeforeSplit.indexOf("~")));
        int lenOfKeyString = String.valueOf(lenOfKey).length();

        // find key and enrypted message
        key.append(encryptedBeforeSplit.substring(encryptedBeforeSplit.lastIndexOf("~") - lenOfKey, encryptedBeforeSplit.lastIndexOf("~")));
        encrMessage.append(encryptedBeforeSplit.substring(encryptedBeforeSplit.indexOf("~") + lenOfAlpahabet + 1,
                input.length() - lenOfKey - 1 - lenOfKeyString).toString());

        // find alphabet
        for (int i = 0; i < lenOfAlpahabet; i++) {
            alphabet.append(encryptedBeforeSplit.charAt(encryptedBeforeSplit.indexOf("~") + 1 + i));
        }

        // put all indexes of the alphabet in a map
        for (int i = 0; i < lenOfAlpahabet; i++) {
            char c = alphabet.toString().charAt(i);
            indexesOfAlphabet.put(c, i);
        }

        // find the key with the size of the message
        for (int i = 0; i < encrMessage.length(); i++) {
            keyWithEncrLen.append(key.charAt(i % lenOfKey));
        }

        // fill map of indexes of the key
        for (int i = 0; i < key.length(); i++) {
            mapOfKey.put(key.charAt(i), indexesOfAlphabet.get(key.charAt(i)));
        }

        // fill map of the indexes of the encripted message
        for (int i = 0; i < indexesOfAlphabet.size(); i++) {
            mapOfMessage.put(encrMessage.charAt(i), indexesOfAlphabet.get(encrMessage.charAt(i)));
        }

        // put all indexes in arrays to find the indexes of the original message
        int[] keyIndArr = new int[encrMessage.length()];
        int[] originalMessageIndArr = new int[encrMessage.length()];
        int[] encrMessageIndArr = new int[encrMessage.length()];

        // decrypt the indexes of the original message
        for (int i = 0; i < encrMessageIndArr.length; i++) {
            keyIndArr[i] = mapOfKey.get(keyWithEncrLen.charAt(i % key.length()));
            encrMessageIndArr[i] = indexesOfAlphabet.get(encrMessage.charAt(i));
            if (encrMessageIndArr[i] >= keyIndArr[i]) {
                originalMessageIndArr[i] = encrMessageIndArr[i] - keyIndArr[i];
            } else {
                originalMessageIndArr[i] = alphabet.length() + encrMessageIndArr[i] - keyIndArr[i];
            }
        }

        // find the final/starting message by mapping the calculated indexes to
        // the chars from the alphabet
        for (int i = 0; i < originalMessageIndArr.length; i++) {
            for (char k : indexesOfAlphabet.keySet()) {
                if (indexesOfAlphabet.get(k) == originalMessageIndArr[i]) {
                    finalResult.append(k);
                    break;
                }
            }
        }
        return finalResult.toString();
    }

}
