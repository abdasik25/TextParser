/**
 * Created by Alexander Lomat on 21.05.19
 * Version 0.0.1
 */

package by.epam.textparser.chain;

import by.epam.textparser.composite.Composite;

public interface Parser {
    Composite parseText(String textLine);
}
