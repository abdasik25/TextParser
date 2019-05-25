/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.sorter;

import by.epam.textparser.chain.ParagraphParser;
import by.epam.textparser.chain.Parser;
import by.epam.textparser.comparator.SentenceAmountComparator;
import by.epam.textparser.comparator.WordLengthComparator;
import by.epam.textparser.comparator.WordsAmountComparator;
import by.epam.textparser.composite.Composite;
import by.epam.textparser.filemanager.FileReader;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;


public class TextSorterTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String FILE_PATH = "data/scource_text";

    @Test
    public void testSort() throws FileNotFoundException {
        FileReader reader = new FileReader();
        Parser parser = ParagraphParser.INSTANCE;
        String inputText = reader.readFromFile(FILE_PATH);
        Composite composite = parser.parseText(inputText);

        LOGGER.info("Sorting paragraphs by amount of sentences: ");
        Sorter paragraphSorter = new TextSorter(Composite.TextPart.PARAGRAPH, Composite.TextPart.PARAGRAPH,
                new SentenceAmountComparator());
        paragraphSorter.sort(composite)
                .forEach(textComponent -> LOGGER.info(textComponent.buildText()));

        LOGGER.info("Sorting words by length: ");
        Sorter wordSorter = new TextSorter(Composite.TextPart.SENTENCE, Composite.TextPart.WORD,
                new WordLengthComparator());
        wordSorter.sort(composite)
                .forEach(textComponent -> LOGGER.info(textComponent.buildText()));

        LOGGER.info("Sorting sentences by amount of words: ");
        Sorter sentenceSorter = new TextSorter(Composite.TextPart.PARAGRAPH, Composite.TextPart.SENTENCE,
                new WordsAmountComparator());
        sentenceSorter.sort(composite)
                .forEach(textComponent -> LOGGER.info(textComponent.buildText()));


    }

}