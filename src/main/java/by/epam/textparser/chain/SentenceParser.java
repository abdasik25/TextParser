/**
 * Created by Alexander Lomat on 21.05.19
 * Version 0.0.1
 */

package by.epam.textparser.chain;

import by.epam.textparser.composite.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public enum  SentenceParser implements Parser {

    INSTANCE;
    private static final String SENTENCE_REGEX_PATTERN = "(?<=[^.?!;\\u2026])";
    private final Logger logger = LogManager.getLogger();

    public Composite parseText(String textLine) {
        Composite composite = new Composite(Composite.TextPart.SENTENCE);
        Arrays.stream(textLine.split(SENTENCE_REGEX_PATTERN))
                .peek(s -> logger.debug(s + " was added to " + composite.getType()))
                .map(LexemeParser.INSTANCE::parseText)
                .forEach(composite::add);
        return composite;
    }
}
