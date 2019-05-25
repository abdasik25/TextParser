/**
 * Created by Alexander Lomat on 22.05.19
 * Version 0.0.1
 */

package by.epam.textparser.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Composite implements Component {

    private TextPart textPart;
    private List<Component> textComponents = new ArrayList<>();

    public Composite(TextPart textPart, List<Component> textComponents) {
        this.textPart = textPart;
        this.textComponents = textComponents;
    }

    public Composite(TextPart textPart) {
        this.textPart = textPart;
    }

    public Composite(List<Component> textComponents) {
        this.textComponents = textComponents;
    }

    public TextPart getType() {
        return textPart;
    }

    public void setType(TextPart textPart) {
        this.textPart = textPart;
    }

    public List<Component> getParts() {
        return textComponents;
    }

    public void setParts(List<Component> textComponents) {
        this.textComponents = textComponents;
    }

    public int size() {
        return textComponents.size();
    }

    public enum TextPart {
        SYMBOL,
        WORD,
        LEXEME,
        SENTENCE,
        PARAGRAPH,
    }

    @Override
    public void add(Component textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public String buildText() {
        StringBuilder builder = new StringBuilder();
        for (Component component : textComponents) {
            builder.append(component.buildText());
            switch (textPart) {
                case SYMBOL:
                case WORD:
                case SENTENCE:
                    break;
                case LEXEME:
                    builder.append(" ");
                    break;
                case PARAGRAPH:
                    builder.append("\n");
                    break;
            }
        }
        return builder.toString();
    }

    @Override
    public List<Component> getLeaf() {
        return textComponents.stream().
                flatMap(c -> ((Composite) c).textComponents.stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Component> getTypeComponents(TextPart anotherType) {
        TextPart[] types = TextPart.values();
        List<Component> result;
        result = anotherType == textPart ? textComponents
                : new Composite(types[textPart.ordinal() - 1], getLeaf()).getTypeComponents(anotherType);
        if (anotherType == TextPart.WORD) {
            result.removeIf(w -> ((Composite) w).getType() == TextPart.SYMBOL && ((Symbol) ((Composite) w).textComponents.get(0))
                    .getType() == Symbol.SymbolType.PUNCTUATION);
        }
        return result;
    }

}
