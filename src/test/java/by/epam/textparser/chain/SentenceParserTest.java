/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.chain;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SentenceParserTest {

    Parser parser = SentenceParser.INSTANCE;

    @Test
    public void testParse() {
        String text = "Hello. How are you? This is a perfect example for testing!";
        int actualSentences = parser.parseText(text).size();
        int expectedSentences = 3;
        Assert.assertEquals(expectedSentences, actualSentences);
    }

}