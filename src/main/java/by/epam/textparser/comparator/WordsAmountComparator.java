package by.epam.textparser.comparator;

import by.epam.textparser.composite.Component;
import by.epam.textparser.composite.Composite;

import java.util.Comparator;

public class WordsAmountComparator implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        return Comparator
                .comparingInt(o -> ((Composite)o).getTypeComponents(Composite.TextPart.WORD).size())
                .compare(o1, o2);
    }
}
