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

    private Composite.TextPart mainPart;
    private Composite.TextPart includedPart;
    private Comparator<Component> comparator;

    public TextSorter(Composite.TextPart mainPart, Composite.TextPart includedPart, Comparator<Component> comparator) {
        this.mainPart = mainPart;
        this.includedPart = includedPart;
        this.comparator = comparator;
    }

    @Override
    public List<Component> sort(Component textComponent) throws IllegalArgumentException{
        if (mainPart == null || includedPart == null || comparator == null) {
            throw new IllegalArgumentException("Parameters should not be null");
        }

        if (mainPart.ordinal() > includedPart.ordinal()) {
            throw new IllegalArgumentException(includedPart + " should be deeper than " + mainPart);
        }

        return includedPart != mainPart ? textComponent.getTypeComponents(mainPart).stream()
                        .flatMap(tc -> tc.getTypeComponents(includedPart).stream()
                                .sorted(comparator))
                        .collect(Collectors.toList())
                                        : textComponent.getTypeComponents(mainPart).stream()
                        .sorted(comparator)
                        .collect(Collectors.toList());
    }
}
