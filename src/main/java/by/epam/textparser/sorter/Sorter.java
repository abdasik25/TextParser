/**
 * Created by Alexander Lomat on 25.05.19
 * Version 0.0.1
 */

package by.epam.textparser.sorter;

import by.epam.textparser.composite.Component;

import java.util.List;

public interface Sorter {
    List<Component> sort(Component textComponent);
}
