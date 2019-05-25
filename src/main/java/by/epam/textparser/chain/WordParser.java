/**
 * Created by Alexander Lomat on 21.05.19
 * Version 0.0.1
 */

package by.epam.textparser.chain;

import by.epam.textparser.composite.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public enum WordParser implements Parser {

    INSTANCE;
    private final Logger logger = LogManager.getLogger();
    private static final String PUNCTUAL_REGEX_PATTERN = "(?<=\\p{Punct})|(?=\\p{Punct})(?!=$)";

    public Composite parseText(String textLine) {
        Composite composite = new Composite(Composite.TextPart.WORD);
        Arrays.stream(textLine.split(PUNCTUAL_REGEX_PATTERN))
                .map(SymbolParser.INSTANCE::parseText)
                .peek(l -> logger.debug(l + " was added to " + composite.getType()))
                .forEach(composite::add);
        return composite;
    }
}
