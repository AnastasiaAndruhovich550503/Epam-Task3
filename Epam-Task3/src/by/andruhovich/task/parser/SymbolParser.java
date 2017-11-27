package by.andruhovich.task.parser;

import by.andruhovich.task.entity.Component;
import by.andruhovich.task.entity.ComponentSymbol;
import by.andruhovich.task.entity.CompositeText;
import by.andruhovich.task.type.SymbolType;

public class SymbolParser implements BaseParser {

    public SymbolParser() {
    }

    @Override
    public Component parseData(String data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        Character[] characters = data.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        ComponentSymbol componentSymbol;

        if (Character.isDigit(characters[0])) {
            componentSymbol = new ComponentSymbol(SymbolType.NUMBER, characters[0]);
        } else if (Character.isLetter(characters[0])) {
            componentSymbol = new ComponentSymbol(SymbolType.LETTER, characters[0]);
        } else {
            componentSymbol = new ComponentSymbol(SymbolType.PUNCTUATION_MARK, characters[0]);
        }

        return componentSymbol;
    }
}
