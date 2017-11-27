package by.andruhovich.task.parser;

import by.andruhovich.task.entity.Component;
import by.andruhovich.task.entity.CompositeText;
import by.andruhovich.task.type.TextType;

public class WordParser implements BaseParser {
    private SymbolParser symbolParser;

    public WordParser() {
        symbolParser = new SymbolParser();
    }

    @Override
    public Component parseData(String data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        CompositeText compositeText = new CompositeText(TextType.WORD);

        int i = 0;
        while (i < data.length()) {
            compositeText.addComponent(symbolParser.parseData(String.valueOf(data.charAt(i))));
            i++;
        }

        return compositeText;
    }
}
