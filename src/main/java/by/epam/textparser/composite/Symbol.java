package by.epam.textparser.composite;

import java.util.List;

public class Symbol implements Component {

    private SymbolType type;
    private char character;

    @Override
    public void add(Component textComponent) {

    }

    @Override
    public String getText() {
        return Character.toString(character);
    }

    @Override
    public List<Component> getLeaf() {
        return null;
    }

    @Override
    public List<Component> getTypeComponents(Composite.TextPart part) {
        return getLeaf();
    }

    public enum SymbolType {
        ALPHABETIC,
        PUNCTUATION
    }

    public Symbol(SymbolType type, char character) {
        this.type = type;
        this.character = character;
    }

    public SymbolType getType() {
        return type;
    }

    public void setType(SymbolType type) {
        this.type = type;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }


}
