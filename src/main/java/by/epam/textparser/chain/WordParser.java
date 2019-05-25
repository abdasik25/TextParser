package by.epam.textparser.chain;

import by.epam.textparser.composite.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public enum WordParser implements Parser {

    INSTANCE;

    private final Logger logger = LogManager.getLogger();

    private static final String PUNCT_SPLIT_PATTERN = "(?<=\\p{Punct})|(?=\\p{Punct})(?!=$)";
    //"(?<=\\p{Punct})|(\\p{Punct})(?!=$)"; //notWorking
    //(?<=\p{Punct})|(\p{Punct})

    public Composite parseText(String textLine) {
        Composite composite = new Composite(Composite.TextPart.WORD);
        Arrays.stream(textLine.split(PUNCT_SPLIT_PATTERN))
                .peek(l -> logger.debug(l + " *** added to " + composite.getType()))
                .map(SymbolParser.INSTANCE::parseText)
                .forEach(composite::add);
        return composite;
    }
}
