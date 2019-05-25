/**
 * Created by Alexander Lomat on 21.05.19
 * Version 0.0.1
 */

package by.epam.textparser.chain;

import by.epam.textparser.composite.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public enum  ParagraphParser implements Parser {

    INSTANCE;

    private static final String PARAGRAPH_SPLIT_PATTERN = "\\n";

    private final Logger logger = LogManager.getLogger();

    public Composite parseText(String textLine) {
        Composite composite = new Composite(Composite.TextPart.PARAGRAPH);
        Arrays.stream(textLine.split(PARAGRAPH_SPLIT_PATTERN))
                .peek(s -> logger.debug(s + " added to " + composite.getType()))
                .map(SentenceParser.INSTANCE::parseText)
                .forEach(composite::add);
        return composite;
    }
}
