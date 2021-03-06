/**
 * Created by Alexander Lomat on 21.05.19
 * Version 0.0.1
 */

package by.epam.textparser.chain;

import by.epam.textparser.composite.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public enum LexemeParser implements Parser {

    INSTANCE;
    private static final String LEXEME_REGEX_PATTERN = "\\s";
    private final Logger LOGGER = LogManager.getLogger();

    public Composite parseText(String textLine) {
        Composite composite = new Composite(Composite.TextPart.LEXEME);
        Arrays.stream(textLine.split(LEXEME_REGEX_PATTERN))
                .filter(s -> !s.isEmpty())
                .map(WordParser.INSTANCE::parseText)
                .peek(s -> LOGGER.debug("'" + s + "'" + " was added to " + composite.getType()))
                .forEach(composite::add);
        return composite;
    }
}
