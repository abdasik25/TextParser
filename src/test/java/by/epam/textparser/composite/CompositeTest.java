package by.epam.textparser.composite;

import by.epam.textparser.chain.WordParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class CompositeTest {

    @Test
    public void testAdd() {
        Composite actualSentence = new Composite(Composite.TextPart.SENTENCE);
        Composite word = new Composite(Composite.TextPart.WORD, WordParser.INSTANCE.parseText("Word").getParts());
        Composite anotherWord = new Composite(Composite.TextPart.WORD, WordParser.INSTANCE.parseText("AnotherWord").getParts());
        actualSentence.add(word);
        actualSentence.add(anotherWord);
        int actual = actualSentence.size();
        int expected = 2;
        assertEquals(actual, expected);
    }

    @Test
    public void testGetTypeComponents() {
        Symbol i = new Symbol(Symbol.SymbolType.ALPHABETIC, 'i');
        Symbol t = new Symbol(Symbol.SymbolType.ALPHABETIC, 't');
        Symbol s = new Symbol(Symbol.SymbolType.ALPHABETIC, 's');
        Symbol m = new Symbol(Symbol.SymbolType.ALPHABETIC, 'm');
        Symbol e = new Symbol(Symbol.SymbolType.ALPHABETIC, 'e');
        Symbol point = new Symbol(Symbol.SymbolType.PUNCTUATION, '.');
        Composite its = new Composite(Composite.TextPart.WORD, Arrays.asList(i, t, s));
        Composite me  = new Composite(Composite.TextPart.WORD, Arrays.asList(m,e));
        Composite pointWord = new Composite(Composite.TextPart.WORD, Arrays.asList(point));

        Composite lexemeAny = new Composite(Composite.TextPart.LEXEME, Arrays.asList(its));
        Composite lexemeText = new Composite(Composite.TextPart.LEXEME, Arrays.asList(me, pointWord));
        Composite sentenceOne = new Composite(Composite.TextPart.SENTENCE, Arrays.asList(lexemeAny, lexemeText));
        Composite paragraphOne = new Composite(Composite.TextPart.PARAGRAPH, Arrays.asList(sentenceOne));

        List<Component> actual = paragraphOne.getTypeComponents(Composite.TextPart.PARAGRAPH);
        List<Composite> expected = Arrays.asList(sentenceOne);

        assertEquals(actual, expected);
    }

    @DataProvider(name = "data")
    public Object[][] provideData() {
        Composite symbolComposite = new Composite(Composite.TextPart.SYMBOL);
        Symbol symbolA = new Symbol(Symbol.SymbolType.ALPHABETIC, 'A');
        Symbol symbolL = new Symbol(Symbol.SymbolType.ALPHABETIC, 'L');
        Symbol symbolE = new Symbol(Symbol.SymbolType.ALPHABETIC, 'E');
        Symbol symbolComma = new Symbol(Symbol.SymbolType.PUNCTUATION, '.');
        symbolComposite.add(symbolA);
        symbolComposite.add(symbolL);
        symbolComposite.add(symbolE);
        symbolComposite.add(symbolComma);


        Composite wordComposite = new Composite(Composite.TextPart.WORD);
        wordComposite.add(new Symbol(Symbol.SymbolType.ALPHABETIC, 'L'));
        wordComposite.add(new Symbol(Symbol.SymbolType.ALPHABETIC, 'E'));
        wordComposite.add(new Symbol(Symbol.SymbolType.PUNCTUATION, '.'));

        Composite lexemeComposite = new Composite(Composite.TextPart.LEXEME);
        lexemeComposite.add(new Symbol(Symbol.SymbolType.ALPHABETIC, 'X'));
        lexemeComposite.add(new Symbol(Symbol.SymbolType.ALPHABETIC, 'A'));
        lexemeComposite.add(new Symbol(Symbol.SymbolType.PUNCTUATION, '.'));

        Composite sentenceCompositeA = new Composite(Composite.TextPart.SENTENCE);
        sentenceCompositeA.add(new Symbol(Symbol.SymbolType.ALPHABETIC, 'N'));
        sentenceCompositeA.add(new Symbol(Symbol.SymbolType.ALPHABETIC, 'D'));
        Composite sentenceCompositeB = new Composite(Composite.TextPart.SENTENCE);
        sentenceCompositeB.add(new Symbol(Symbol.SymbolType.ALPHABETIC, 'E'));
        sentenceCompositeB.add(new Symbol(Symbol.SymbolType.ALPHABETIC, 'R'));

        Composite paragraphComposite = new Composite(Composite.TextPart.PARAGRAPH);
        paragraphComposite.add(sentenceCompositeA);
        paragraphComposite.add(sentenceCompositeB);


        return new Object[][]{
                {symbolComposite, "ALE."},
                {lexemeComposite, "X A . "},
                {sentenceCompositeA, "ND"},
                {paragraphComposite, "ND\nER\n"}
        };
    }

    @Test(dataProvider = "data")
    public void testGetText(Component component, String expected) {
        String actual = component.getText();
        assertEquals(actual, expected);

    }

}