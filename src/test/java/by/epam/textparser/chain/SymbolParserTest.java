package by.epam.textparser.chain;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SymbolParserTest {

    Parser parser = SymbolParser.INSTANCE;

    @Test
    public void testParse() {
        int spaceAmount = 10;
        String text = "Hello. How are you? This is a perfect example for testing!";
        int actualSymbols = parser.parseText(text).size();
        int expectedSymbols = text.length() - spaceAmount;
        Assert.assertEquals(actualSymbols, expectedSymbols);
    }

}