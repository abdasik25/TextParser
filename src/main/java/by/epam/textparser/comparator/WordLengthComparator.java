/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.comparator;

import by.epam.textparser.composite.Component;
import by.epam.textparser.composite.Composite;

import java.util.Comparator;

public class WordLengthComparator implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        return Comparator
                .comparingInt(o -> ((Composite) o).getTypeComponents(Composite.TextPart.SYMBOL).size())
                .compare(o1, o2);
    }

}
