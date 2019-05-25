package by.epam.textparser.sorter;

import by.epam.textparser.chain.ParagraphParser;
import by.epam.textparser.chain.Parser;
import by.epam.textparser.chain.SentenceParser;
import by.epam.textparser.chain.WordParser;
import by.epam.textparser.comparator.SentenceAmountComparator;
import by.epam.textparser.comparator.WordLengthComparator;
import by.epam.textparser.comparator.WordsAmountComparator;
import by.epam.textparser.composite.Composite;
import by.epam.textparser.filemanager.FileReader;
import by.epam.textparser.filemanager.FileWriter;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;


public class TextPartSorterTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String  FILE_PATH = "data/scource_text";
    private static final String  WRITE_PATH = "data/output_data";



    @Test
    public void testSort() throws FileNotFoundException {
        FileReader reader = new FileReader();
        Parser parser = ParagraphParser.INSTANCE;
        String inputText = reader.readFromFile(FILE_PATH);
        Composite composite = parser.parseText(inputText);

        LOGGER.info("Sorting paragraphs by amount of sentences: ");
        Sorter paragraphSorter = new TextPartSorter(Composite.TextPart.PARAGRAPH, Composite.TextPart.PARAGRAPH,
                new SentenceAmountComparator());
        paragraphSorter.sort(composite)
                .forEach(textComponent -> LOGGER.info(textComponent.getText()));

        LOGGER.info("Sorting words by length: ");
        Sorter wordSorter = new TextPartSorter(Composite.TextPart.SENTENCE, Composite.TextPart.WORD,
                new WordLengthComparator());
        wordSorter.sort(composite)
                .forEach(textComponent -> LOGGER.info(textComponent.getText()));

        LOGGER.info("Sorting sentences by amount of words: ");
        Sorter sentenceSorter = new TextPartSorter(Composite.TextPart.PARAGRAPH, Composite.TextPart.SENTENCE,
                new WordsAmountComparator());
        sentenceSorter.sort(composite)
                .forEach(textComponent -> LOGGER.info(textComponent.getText()));


    }

}