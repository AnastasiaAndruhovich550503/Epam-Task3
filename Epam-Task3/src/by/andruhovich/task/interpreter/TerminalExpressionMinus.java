package by.andruhovich.task.interpreter;

import by.andruhovich.task.interpreter.AbstractMathExpression;
import by.andruhovich.task.interpreter.Context;

public class TerminalExpressionMinus implements AbstractMathExpression {
    @Override
    public void interpret(Context context) {
        double secondValue = context.popValue();
        double firstValue = context.isEmpty() ? 0 : context.popValue();

        context.pushValue((firstValue - secondValue));
    }
}
