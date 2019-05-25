package by.epam.textparser.composite;

import java.util.List;

public interface Component {
    void add(Component textComponent);
    String getText();
    List<Component> getLeaf();
    List<Component> getTypeComponents(Composite.TextPart part);
}
