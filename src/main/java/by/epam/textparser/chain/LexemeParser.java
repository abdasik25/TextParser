package by.epam.textparser.chain;

import by.epam.textparser.composite.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public enum LexemeParser implements Parser {

    INSTANCE;

    private static final String LEXEME_SPLIT_PATTERN = "\\s";

    private final Logger LOGGER = LogManager.getLogger();

    public Composite parseText(String textLine) {

        Composite composite = new Composite(Composite.TextPart.LEXEME);

        Arrays.stream(textLine.split(LEXEME_SPLIT_PATTERN))
                .filter(s -> !s.isEmpty())
                .peek(s -> LOGGER.debug("'" + s + "'" + " *** added to " + composite.getType()))
                .map(WordParser.INSTANCE::parseText)
                .forEach(composite::add);
        return composite;
    }
}
