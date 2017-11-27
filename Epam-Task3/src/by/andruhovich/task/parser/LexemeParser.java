package by.andruhovich.task.parser;

import by.andruhovich.task.entity.Component;
import by.andruhovich.task.entity.CompositeText;
import by.andruhovich.task.type.TextType;

import java.util.regex.Pattern;

public class LexemeParser implements BaseParser{
    private WordParser wordParser;
    private SymbolParser symbolParser;
    private ExpressionParser expressionParser;
    private static final String REGEX_EXPRESSION = "[^a-hk-zA-ZА-Яа-я?!,;:]+";
    private static final String REGEX_PUNCTUATION_MARK = ".*[?!.,;:-]";
    private static final String REGEX_PUNCTUATION_MARK_WITHOUT_REMOVING = "(?<=[?!.,;:-])|(?=[?!.,;:-])";

    public LexemeParser() {
        wordParser = new WordParser();
        expressionParser = new ExpressionParser();
        symbolParser = new SymbolParser();
    }

    @Override
    public Component parseData(String data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        CompositeText compositeText = new CompositeText(TextType.LEXEME);

        if (data.contains("-") && data.length() == 1) {
            compositeText.addComponent(symbolParser.parseData(data));
        }
        else if (Pattern.matches(REGEX_EXPRESSION, data)) {
            compositeText.addComponent(expressionParser.parseData(data));
        } else if (Pattern.matches(REGEX_PUNCTUATION_MARK, data)) {
            String[] resultSplit = data.split(REGEX_PUNCTUATION_MARK_WITHOUT_REMOVING);
            compositeText.addComponent(wordParser.parseData(resultSplit[0]));
            int j = 1;
            while (j < resultSplit.length) {
                if (resultSplit[j] != null) {
                    compositeText.addComponent(symbolParser.parseData(resultSplit[j]));
                }
                j++;
            }
        } else {
            compositeText.addComponent(wordParser.parseData(data));
        }

        return compositeText;
    }


}
