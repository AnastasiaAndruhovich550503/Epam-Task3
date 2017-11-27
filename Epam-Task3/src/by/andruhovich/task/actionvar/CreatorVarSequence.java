package by.andruhovich.task.actionvar;

import by.andruhovich.task.var.VarSequence;

public class CreatorVarSequence {
    public void createSequence(String expression) {
        String string = replaceOperation(expression);

        createSequenceI(string);
        createSequenceJ(string);
    }

    private void createSequenceI(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case 'm':
                    VarSequence.sequenceI.add(-1);
                    break;
                case 'p':
                    VarSequence.sequenceI.add(1);
                    break;
                case 'i':
                    VarSequence.sequenceI.add(0);
                    break;
            }
        }
    }

    private void createSequenceJ(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case 'm':
                    VarSequence.sequenceJ.add(-1);
                    break;
                case 'p':
                    VarSequence.sequenceJ.add(1);
                    break;
                case 'j':
                    VarSequence.sequenceJ.add(0);
                    break;
            }
        }
    }

    private String replaceOperation(String expression) {
        final String REGEX_DECREMENT = "--j|j--|--i|i--";
        final String REGEX_INCREMENT = "[+]{2}j|j[+]{2}|[+]{2}i|i[+]{2}";

        final String MINUS = "m";
        final String PLUS = "p";

        expression = expression.replaceAll(REGEX_DECREMENT, MINUS);
        expression = expression.replaceAll(REGEX_INCREMENT, PLUS);

        return expression;
    }
}
