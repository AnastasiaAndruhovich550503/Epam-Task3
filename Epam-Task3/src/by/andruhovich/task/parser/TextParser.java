package by.andruhovich.task.parser;

import by.andruhovich.task.entity.Component;
import by.andruhovich.task.entity.CompositeText;
import by.andruhovich.task.type.TextType;

public class TextParser implements BaseParser {
    private ParagraphParser paragraphParser;
    private static final String REGEX_PARAGRAPH_WITH_LISTING = "\\t|\\s{4}";

    public TextParser() {
        paragraphParser = new ParagraphParser();
    }

    @Override
    public Component parseData(String data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        CompositeText compositeText = new CompositeText(TextType.TEXT);
        String[] dataArray = data.split(REGEX_PARAGRAPH_WITH_LISTING);

        int i = 0;
        while (i < dataArray.length) {
            if (!"".equals(dataArray[i])) {
                dataArray[i] = dataArray[i].replaceAll("\\t|\\n|\\s{4}", "");
                compositeText.addComponent(paragraphParser.parseData(dataArray[i]));
            }
            i++;
        }

        return compositeText;
    }
}
