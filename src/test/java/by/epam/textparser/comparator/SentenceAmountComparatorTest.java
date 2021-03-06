/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.comparator;

import by.epam.textparser.chain.ParagraphParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


public class SentenceAmountComparatorTest {

    SentenceAmountComparator comparator = new SentenceAmountComparator();

    @DataProvider(name = "data")
    public Object[][] provideData() {
        return new Object[][] {
                {"One, two, three.\n", "One, two. Three, four.\n", -1},
                {"One. Two.", "One.", 1},
                {"One.", "One.", 0}
        };
    }

    @Test(dataProvider = "data")
    public void testCompare(String s1, String s2, int expected) {
        int actual = comparator.compare(ParagraphParser.INSTANCE.parseText(s1), ParagraphParser.INSTANCE.parseText(s2));
        assertEquals(actual, expected);

    }

}