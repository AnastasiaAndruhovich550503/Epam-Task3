package by.andruhovich.task.interpreter;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque <Double> contextValues = new ArrayDeque<>();

    public Double popValue() {
        return contextValues.pop();
    }

    public void pushValue(Double value) {
        contextValues.push(value);
    }

    public boolean isEmpty() {
        return contextValues.isEmpty();
    }
}
