package by.epam.textparser.chain;

import by.epam.textparser.composite.Composite;

public interface Parser {
    Composite parseText(String textLine);
}
