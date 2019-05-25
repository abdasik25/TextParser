package by.epam.textparser.chain;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LexemeParserTest {

    Parser parser = LexemeParser.INSTANCE;

    @Test
    public void testParse() {
        String text = "Hello. How are you? This is a perfect example for testing";
        int actualSpaces = parser.parseText(text).size();
        int expectedSpaces = 11;
        Assert.assertEquals(actualSpaces, expectedSpaces);
    }
}