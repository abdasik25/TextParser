package by.epam.textparser.chain;

import by.epam.textparser.composite.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public enum  SentenceParser implements Parser {

    INSTANCE;

    private static final String SENTENCE_SPLIT_PATTERN = "(?<=\\.)";
    //(?<=[^.?!;\u2026])

    private final Logger logger = LogManager.getLogger();

    public Composite parseText(String textLine) {
        Composite composite = new Composite(Composite.TextPart.SENTENCE);
        Arrays.stream(textLine.split(SENTENCE_SPLIT_PATTERN))
                .peek(s -> logger.debug(s + " *** added to " + composite.getType()))
                .map(LexemeParser.INSTANCE::parseText)
                .forEach(composite::add);
        return composite;
    }
}
