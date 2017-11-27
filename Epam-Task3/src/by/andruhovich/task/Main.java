package by.andruhovich.task;

import by.andruhovich.task.entity.CompositeText;
import by.andruhovich.task.parser.TextParser;
import by.andruhovich.task.reader.ReadFileData;

import java.util.Locale;


public class Main {

    public static void main(String[] args) {
        ReadFileData readFileData = new ReadFileData();
        String data = readFileData.readData("data\\data.txt", new Locale("ru", "ru"));
        TextParser textParser = new TextParser();

        CompositeText compositeText = (CompositeText) textParser.parseData(data);
        System.out.println(compositeText.toString());
    }
}