package by.andruhovich.task.parser;

import by.andruhovich.task.entity.Component;
import by.andruhovich.task.entity.CompositeText;
import by.andruhovich.task.type.TextType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements BaseParser {
    private LexemeParser lexemeParser;
    private static final String REGEX_LEXEME = "\\s+";
    private static final String REGEX_EXPRESSION = "[^a-hk-zA-ZА-Яа-я?!,;:]+";

    public SentenceParser() {
        lexemeParser = new LexemeParser();
    }

    @Override
    public Component parseData(String data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        CompositeText compositeText = new CompositeText(TextType.SENTENCE);
        String[] dataArray = data.split(REGEX_LEXEME);

        String[] result = concatExpression(dataArray);

        int i = 0;
        while (i < result.length) {
            if (!"".equals(result[i]) && result[i] != null) {
                compositeText.addComponent(lexemeParser.parseData(result[i]));
            }
            i++;
        }


        return compositeText;
    }

    private String[] concatExpression(String[] dataArray) {
        String[] data = new String[dataArray.length];
        Pattern pattern = Pattern.compile(REGEX_EXPRESSION);
        Matcher firstMatcher;
        Matcher secondMatcher;
        data[0] = dataArray[0];

        for (int i = 1, j = 1; i < data.length; i++) {
            if (dataArray[i] != null) {
                firstMatcher = pattern.matcher(dataArray[i]);
                secondMatcher = pattern.matcher(dataArray[i - 1]);
                if (dataArray[i].contains("-") && dataArray[i].length() == 1) {
                    data[j] = dataArray[i];
                    j++;
                }
                else if (firstMatcher.matches() && secondMatcher.matches()) {
                    data[j - 1] = data[j - 1].concat(dataArray[i]);
                } else {
                    data[j] = dataArray[i];
                    j++;
                }
            }
        }
        return data;
    }
}
