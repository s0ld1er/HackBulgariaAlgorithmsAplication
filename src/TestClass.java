import static org.junit.Assert.*;

import org.junit.Test;

public class TestClass {
    
    @Test
    public void testPalindromes() {
        assertEquals("alabala\r\n", new Palindrome().allRotations("labalaa"));
        assertEquals("akawwaka\r\nwakaakaw\r\n", new Palindrome().allRotations("akawwaka"));
        assertEquals("aa\r\naa\r\n", new Palindrome().allRotations("aa"));
        assertEquals("NONE", new Palindrome().allRotations("thunderstorm"));
    }

    @Test
    public void testEncrypted() {
        long start = System.nanoTime();
        assertEquals("I love algorithms", new EncryptedMessageImpl().decryptEncryptedMessage("vrItommseIal vihack~416~Ilocveakgrithms he"));
        assertEquals("this is a secret.", new EncryptedMessageImpl().decryptEncryptedMessage("rc hscesi tcrest~410~thisaecr .rcese"));
        assertEquals("I love freckles.", new EncryptedMessageImpl().decryptEncryptedMessage("fl k.ccfsIolskv.~312~ .Ifrckslovelvo"));
        assertEquals("is this fun or what?", new EncryptedMessageImpl().decryptEncryptedMessage("o?uin uw?stutnfwat?~413~orwa? thfuisnnrsiu"));
        System.out.println(System.nanoTime() - start);
    }

    @Test
    public void testBrackets() {
        fail();
    }

}
