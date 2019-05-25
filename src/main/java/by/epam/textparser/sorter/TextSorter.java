/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.sorter;

import by.epam.textparser.composite.Component;
import by.epam.textparser.composite.Composite;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TextSorter implements Sorter {

    private Composite.TextPart whereTextPart;
    private Composite.TextPart whatTextPart;
    private Comparator<Component> comparator;

    public TextSorter(Composite.TextPart whereTextPart, Composite.TextPart whatTextPart, Comparator<Component> comparator) {
        this.whereTextPart = whereTextPart;
        this.whatTextPart = whatTextPart;
        this.comparator = comparator;
    }

    @Override
    public List<Component> sort(Component textComponent) throws IllegalArgumentException {
        if (whereTextPart == null || whatTextPart == null || comparator == null) {
            throw new IllegalArgumentException("Parameters should not be null");
        }
        if (whatTextPart.ordinal() > whereTextPart.ordinal()) {
            throw new IllegalArgumentException(whatTextPart + " should be smaller than " + whereTextPart);
        }
        return whatTextPart != whereTextPart ?
                textComponent.getTypeComponents(whereTextPart).stream()
                        .flatMap(tc -> tc.getTypeComponents(whatTextPart).stream()
                                .sorted(comparator))
                        .collect(Collectors.toList()) :
                textComponent.getTypeComponents(whereTextPart).stream()
                        .sorted(comparator)
                        .collect(Collectors.toList());
    }
}
