package by.andruhovich.task.entity;

import by.andruhovich.task.type.TextType;

import java.util.ArrayList;

public class CompositeText implements Component {
    private TextType textType;
    private ArrayList<Component> components;

    public CompositeText(TextType textType) {
        this.textType = textType;
        components = new ArrayList<>();
    }

    public TextType getTextType() {
        return textType;
    }

    public void setTextType(TextType textType) {
        this.textType = textType;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public int countTextItem() {
        int count = 0;
        for (Component component : components) {
            count += component.countTextItem();
        }

        return count;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Component component : components) {
            stringBuffer.append(component.toString());
        }
        switch(textType) {
            case LEXEME:
                return stringBuffer.toString() + " ";
            case PARAGRAPH:
                return "\t" + stringBuffer.toString() + "\n";
            default:
                return stringBuffer.toString();
        }
    }
}
