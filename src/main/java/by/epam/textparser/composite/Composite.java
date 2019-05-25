package by.epam.textparser.composite;

import javax.xml.soap.Text;
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
    public String getText() {
        StringBuilder builder = new StringBuilder();
        for (Component component : parts) {
            builder.append(component.getText());
            switch (type) {
                case PARAGRAPH:
                    builder.append("\n");
                    break;
                case SENTENCE:
                case WORD:
                case SYMBOL:
                    break;
                case LEXEME:
                    builder.append(" ");
                    break;
            }
        }
        return builder.toString();
    }

    @Override
    public List<Component> getLeaf() {
        return parts.stream().flatMap(tc -> ((Composite)tc).parts.stream()).collect(Collectors.toList());
    }

    @Override
    public List<Component> getTypeComponents(TextPart anotherType) {
        TextPart[] types = TextPart.values();
        List<Component> result;
        result = anotherType == type ? parts
                : new Composite(types[type.ordinal() - 1], getLeaf()).getTypeComponents(anotherType);
        if (anotherType == TextPart.WORD) {
            result.removeIf(w -> ((Composite)w).getType() == TextPart.SYMBOL && ((Symbol)((Composite) w).parts.get(0))
                    .getType() == Symbol.SymbolType.PUNCTUATION);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Composite composite = (Composite) o;

        if (type != composite.type) return false;
        return parts != null ? parts.equals(composite.parts) : composite.parts == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (parts != null ? parts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return type +
                "[" + parts +
                ']';
    }


}
