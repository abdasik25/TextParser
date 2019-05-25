/**
 * Created by Alexander Lomat on 21.05.19
 * Version 0.0.1
 */

package by.epam.textparser.chain;

import by.epam.textparser.composite.Composite;
import by.epam.textparser.composite.Symbol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SymbolParser implements Parser {

    INSTANCE;
    private final Logger logger = LogManager.getLogger();
    private static final String SYMBOL_REGEX_PATTERN = "(\\w)|(\\p{Punct})";

    public Composite parseText(String textLine) {
        Composite composite = new Composite(Composite.TextPart.SYMBOL);
        Pattern pattern = Pattern.compile(SYMBOL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(textLine);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                Symbol symbol = new Symbol(Symbol.SymbolType.ALPHABETIC, matcher.group(1).charAt(0));
                logger.debug(symbol.getType() + " : " + symbol.getCharacter() + " was added to " + composite.getType());
                composite.add(symbol);
            }
            if (matcher.group(2) != null) {
                Symbol symbol = new Symbol(Symbol.SymbolType.PUNCTUATION, matcher.group(2).charAt(0));
                logger.debug(symbol.getType() + " : " + symbol.getCharacter() + " was added to " + composite.getType());
                composite.add(symbol);
            }
        }
        return composite;
    }
}
