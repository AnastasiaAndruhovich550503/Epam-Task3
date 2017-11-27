package by.andruhovich.task.interpreter;

import by.andruhovich.task.interpreter.AbstractMathExpression;
import by.andruhovich.task.interpreter.Context;

public class NonTerminalExpressionNumber implements AbstractMathExpression {
    private double number;

    public NonTerminalExpressionNumber(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
