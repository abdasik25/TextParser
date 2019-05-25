package by.epam.textparser.sorter;

import by.epam.textparser.composite.Component;
import by.epam.textparser.composite.Composite;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TextPartSorter implements Sorter {

    private Composite.TextPart whereType;
    private Composite.TextPart whatType;
    private Comparator<Component> howComparator;

    public TextPartSorter(Composite.TextPart whereType, Composite.TextPart whatType, Comparator<Component> howComparator) {
        this.whereType = whereType;
        this.whatType = whatType;
        this.howComparator = howComparator;
    }

    @Override
    public List<Component> sort(Component textComponent) throws IllegalArgumentException{
        if (whereType == null || whatType == null || howComparator == null) {
            throw new IllegalArgumentException("Parameters should not be null");
        }
        if (whatType.ordinal() > whereType.ordinal()) {
            throw new IllegalArgumentException(whatType + " should be deeper than " + whereType);
        }
        return whatType != whereType ?
                textComponent.getTypeComponents(whereType).stream()
                        .flatMap(tc -> tc.getTypeComponents(whatType).stream()
                                .sorted(howComparator))
                        .collect(Collectors.toList()) :
                textComponent.getTypeComponents(whereType).stream()
                        .sorted(howComparator)
                        .collect(Collectors.toList());
    }
}
