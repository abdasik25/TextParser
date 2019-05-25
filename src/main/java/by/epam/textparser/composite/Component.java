/**
 * Created by Alexander Lomat on 22.05.19
 * Version 0.0.1
 */

package by.epam.textparser.composite;

import java.util.List;

public interface Component {
    void add(Component textComponent);
    String buildText();
    List<Component> getLeaf();
    List<Component> getTypeComponents(Composite.TextPart part);
}
