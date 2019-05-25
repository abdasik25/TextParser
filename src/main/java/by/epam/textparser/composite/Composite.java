/**
 * Created by Alexander Lomat on 22.05.19
 * Version 0.0.1
 */

package by.epam.textparser.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Composite implements Component {

    private TextPart type;
    private List<Component> parts = new ArrayList<>();

    public Composite(TextPart type, List<Component> parts) {
        this.type = type;
        this.parts = parts;
    }

    public Composite(TextPart type) {
        this.type = type;
    }

    public Composite(List<Component> parts) {
        this.parts = parts;
    }

    public TextPart getType() {
        return type;
    }

    public void setType(TextPart type) {
        this.type = type;
    }

    public List<Component> getParts() {
        return parts;
    }

    public void setParts(List<Component> parts) {
        this.parts = parts;
    }

    public int size() {
        return parts.size();
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
        parts.add(textComponent);
    }

    @Override
    public String buildText() {
        StringBuilder builder = new StringBuilder();
        for (Component component : parts) {
            builder.append(component.buildText());
            switch (type) {
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
        return parts.stream().flatMap(tc -> ((Composite) tc).parts.stream()).collect(Collectors.toList());
    }

    @Override
    public List<Component> getTypeComponents(TextPart anotherType) {
        TextPart[] types = TextPart.values();
        List<Component> result;
        result = anotherType == type ? parts
                : new Composite(types[type.ordinal() - 1], getLeaf()).getTypeComponents(anotherType);
        if (anotherType == TextPart.WORD) {
            result.removeIf(w -> ((Composite) w).getType() == TextPart.SYMBOL && ((Symbol) ((Composite) w).parts.get(0))
                    .getType() == Symbol.SymbolType.PUNCTUATION);
        }
        return result;
    }

}
