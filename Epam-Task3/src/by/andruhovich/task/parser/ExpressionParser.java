package by.andruhovich.task.parser;

import by.andruhovich.task.entity.Component;
import by.andruhovich.task.entity.CompositeText;
import by.andruhovich.task.interpreter.Client;
import by.andruhovich.task.polishnotaion.CreatorPolishNotation;
import by.andruhovich.task.type.TextType;

public class ExpressionParser implements BaseParser{
    private SymbolParser symbolParser;

    public ExpressionParser() {
        symbolParser = new SymbolParser();
    }

    @Override
    public Component parseData(String data) {
        CompositeText compositeText = new CompositeText(TextType.EXPRESSION);

        CreatorPolishNotation creatorPolishNotation = new CreatorPolishNotation();
        String reversePolishNotation = creatorPolishNotation.createReversePolishNotation(data);
        Client interpreter = new Client(reversePolishNotation);
        String expression = interpreter.calculate();

        int i = 0;
        while (i < expression.length()) {
            compositeText.addComponent(symbolParser.parseData(String.valueOf(expression.charAt(i))));
            i++;
        }

        return compositeText;
    }

}
