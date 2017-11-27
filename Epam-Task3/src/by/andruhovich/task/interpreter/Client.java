package by.andruhovich.task.interpreter;

import by.andruhovich.task.actionvar.ChangerVar;
import by.andruhovich.task.var.Var;

import java.util.ArrayList;

public class Client {
    private ArrayList <AbstractMathExpression> listExpression;

    public Client(String reversePolishNotation) {
        listExpression = new ArrayList<>();
        parse(reversePolishNotation);
    }

    private void parse(String reversePolishNotation) { // синтаксический анализ
        ChangerVar changerVar = new ChangerVar();
        for (String expression : reversePolishNotation.split("\\s")) {
            if (expression.isEmpty()) {
                continue;
            }
            switch (expression) {
                case "+":
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case "-":
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case "*":
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case "/":
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                case "--":
                    listExpression.add(new TerminalExpressionDecrement());
                    break;
                case "++":
                    listExpression.add(new TerminalExpressionIncrement());
                    break;
                case "i":
                    listExpression.add(new NonTerminalExpressionNumber(Var.i));
                    changerVar.changeI();
                    break;
                case "j":
                    listExpression.add(new NonTerminalExpressionNumber(Var.j));
                    changerVar.changeJ();
                    break;
                default:
                    listExpression.add(new NonTerminalExpressionNumber(Double.parseDouble(expression)));
            }
        }
    }
    public String calculate() {
        Context context = new Context();
        // выполнение простых задач и сборка результата
        for (AbstractMathExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue().toString();
    }
}
