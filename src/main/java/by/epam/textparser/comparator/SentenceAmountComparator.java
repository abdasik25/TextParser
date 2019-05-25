/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.comparator;

import by.epam.textparser.composite.Component;
import by.epam.textparser.composite.Composite;

import java.util.Comparator;

public class SentenceAmountComparator implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        return Comparator
                .comparingInt(tc -> ((Composite) tc).getTypeComponents(Composite.TextPart.SENTENCE).size())
                .reversed()
                .compare(o1, o2);
    }
}
