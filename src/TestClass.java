import static org.junit.Assert.*;

import org.junit.Test;

public class TestClass {

    @Test
    public void testPalindromes() {
        assertEquals("alabala\r\n", new PalindromeImpl().allRotations("labalaa"));
        assertEquals("akawwaka\r\nwakaakaw\r\n", new PalindromeImpl().allRotations("akawwaka"));
        assertEquals("aa\r\naa\r\n", new PalindromeImpl().allRotations("aa"));
        assertEquals("NONE", new PalindromeImpl().allRotations("thunderstorm"));
    }

    @Test
    public void testEncrypted() {
        assertEquals("I love algorithms", new EncryptedMessageImpl().decryptEncryptedMessage("vrItommseIal vihack~416~Ilocveakgrithms he"));
        assertEquals("this is a secret.", new EncryptedMessageImpl().decryptEncryptedMessage("rc hscesi tcrest~410~thisaecr .rcese"));
        assertEquals("I love freckles.", new EncryptedMessageImpl().decryptEncryptedMessage("fl k.ccfsIolskv.~312~ .Ifrckslovelvo"));
        assertEquals("is this fun or what?", new EncryptedMessageImpl().decryptEncryptedMessage("o?uin uw?stutnfwat?~413~orwa? thfuisnnrsiu"));
        assertEquals(
                "Send us the code you used to decode this message. Write a short text (at most one page) telling us why you decided to start programming. What is that you like and what motivates you to continue learning? We would also want to know what are your expectations for the Algorithms course. Let your thoughts flow and don't spare us any detail.",
                new EncryptedMessageImpl()
                        .decryptEncryptedMessage("TJKUMbUUJTIREuKOXD'HR.sTOVSWU!KSJ(O.sVYtWZTTZVULsNOBdYONXFsvEu(PgWJsRTSVsYKOfDZOJSNVWu(IU!yAaMs?OW.tYaVET.A IQXTMQURJ.HLs'VHa'OTYUSzCQ!SePzsuMTzYQ!SM!NOdOH SuPMa)yA!LsKOPEUM,VAaMs.SuKOkDJEcIIStHACKBULGARIA~1260~abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .',!?()rEPNtg,yTYMsJOFOtkZ sd EKsVYtXPIOUMs HK't(PYSROEAsq.JfPyAJ HVRVCUYaZsPITzMQ'UMZTEJXANEBCUYWRI!Os.Su(IkD!OdADLCKNFXDZOJ THRVCQdZMRRUMPNIDtISGTJQSz"));
    }

    @Test
    public void testBrackets() {
        assertEquals("1337", new BracketProblemImpl().findSum("[123(145)38(37)812]"));
        assertEquals("264", new BracketProblemImpl().findSum("{125[2][(1)][3]125}"));
        assertEquals("500", new BracketProblemImpl().findSum("[125()125()125()125]"));
        assertEquals("1870", new BracketProblemImpl().findSum("{123[123(123)123(123)]23[123]2}"));
        assertEquals("10", new BracketProblemImpl().findSum("{[2()3]}"));
        assertEquals("14", new BracketProblemImpl().findSum("{[(2)3]}"));
        assertEquals("NO", new BracketProblemImpl().findSum("{125()125}"));
        assertEquals("NO", new BracketProblemImpl().findSum("{125[12]{125}[12]125}"));
        assertEquals("NO", new BracketProblemImpl().findSum("{125[12(123]125}"));
    }

}
