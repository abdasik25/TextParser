/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.chain;

import org.testng.Assert;
import org.testng.annotations.Test;


public class WordParserTest {

    Parser parser = WordParser.INSTANCE;

    @Test
    public void testParse() {
        String text = "It has, asd, asd Bye";
        int actualWords = parser.parseText(text).size();
        int expectedWords = 5;
        Assert.assertEquals(actualWords, expectedWords);
    }

}