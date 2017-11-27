package by.andruhovich.task.parser;

import by.andruhovich.task.entity.Component;
import by.andruhovich.task.entity.CompositeText;
import by.andruhovich.task.type.TextType;

import java.util.regex.Pattern;

public class ParagraphParser implements BaseParser {
    private SentenceParser sentenceParser;
    private static final String REGEX_SENTENCE = "((?<=[\\.]{3}|[.?!])|(?=[\\.]{3}|[.?!]))";

    public ParagraphParser() {
        sentenceParser = new SentenceParser();
    }

    @Override
    public Component parseData(String data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        CompositeText compositeText = new CompositeText(TextType.PARAGRAPH);
        String[] dataArray = data.split(REGEX_SENTENCE);

        String[] result = new String[dataArray.length];

        for (int i = 0, j = 0; i < dataArray.length; i++) {
            if (Pattern.matches("\\p{Punct}", dataArray[i])) {
                result[j - 1] = result[j - 1].concat(dataArray[i]);
            } else {
                result[j] = dataArray[i];
                j++;
            }
        }

        int i = 0;
        while (result[i] != null) {
            result[i] = result[i].replaceAll("^\\s", "");
            compositeText.addComponent(sentenceParser.parseData(result[i]));
            i++;
        }

        return compositeText;
    }
}
