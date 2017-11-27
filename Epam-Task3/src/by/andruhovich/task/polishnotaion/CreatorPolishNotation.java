package by.andruhovich.task.polishnotaion;

import by.andruhovich.task.actionvar.CreatorVarSequence;
import by.andruhovich.task.var.VarSequence;

import java.util.*;
import java.util.regex.Pattern;

public class CreatorPolishNotation {
    public static final HashMap<String, Integer> OPERATION_PRIORITIES;

    static {
        OPERATION_PRIORITIES = new HashMap<String, Integer>();
        //декремент
        OPERATION_PRIORITIES.put("--", 1);
        //инкремент
        OPERATION_PRIORITIES.put("++", 1);
        OPERATION_PRIORITIES.put("*", 2);
        OPERATION_PRIORITIES.put("/", 2);
        OPERATION_PRIORITIES.put("-", 3);
        OPERATION_PRIORITIES.put("+", 3);
    }

    // преобразование выражения в обратную польскую запись
    public String createReversePolishNotation(String expression) {
        // задаём скобки
        final String LEFT_BRACKET = "(";
        final String RIGHT_BRACKET = ")";
        // список с результатом преобразования
        ArrayList<String> listResult = new ArrayList<String>();
        // стэк для хранения операций
        ArrayDeque<String> stackOperation = new ArrayDeque<>();
        // коллекция со всеми возможными операциями
        Set<String> operations = new HashSet<String>(OPERATION_PRIORITIES.keySet());
        // в том числе и скобками
        operations.add(LEFT_BRACKET);
        operations.add(RIGHT_BRACKET);

        // удаляем пробелы из выражения
        expression = expression.replace(" ", "");
        //создаем последовательность изменений i и j
        CreatorVarSequence creatorVarSequence = new CreatorVarSequence();
        creatorVarSequence.createSequence(expression);
        //замена инкремента и декремента
        expression = replaceIncrementAndDecrement(expression);

        // индекс символа выражения, на котором находится преобразование
        int currentIndex = 0;
        // флаг окончания преобразования
        boolean endFlag = true;
        while (endFlag) {
            // индекс следующей операции
            int nextOperationIndex = expression.length(); // количество символов в выражении
            // следующая операция
            String nextOperation = "";
            // Поиск следующей операции путём перебора всего списка операция и сравнения
            for (String operation : operations) {
                // i - последняя операциия в выражении, начиная с точки отсчёта index
                int lastOperationIndex = expression.indexOf(operation, currentIndex);
                // если такой оператор есть в выражении и его индекс меньше последнего найденного
                if (lastOperationIndex >= 0 && lastOperationIndex < nextOperationIndex) {
                    // то запоминаем этот оператор, как следующий используемый
                    nextOperation = operation;
                    nextOperationIndex = lastOperationIndex;
                }
            }
            // Если операций больше нет в выражении, выставляем флаг окончания преобразования
            if (nextOperationIndex == expression.length()) {
                endFlag = false;
            } else {
                // Если операции предшествует операнд, добавляем его в результирующий список в виде подстроки от точки отсчёта до индекса операции
                if (currentIndex != nextOperationIndex) {
                    listResult.add(expression.substring(currentIndex, nextOperationIndex));
                }
                // Если операци - открывающаяся скобка - помещаем её в стек операций
                if (nextOperation.equals(LEFT_BRACKET)) {
                    stackOperation.push(nextOperation);
                } else if (nextOperation.equals(RIGHT_BRACKET)) {
                    // Если операци - закрывающаяся скобка
                    // перебираем стек, пока верхний элемент не будет открывающейся скобкой
                    while (!stackOperation.peek().equals(LEFT_BRACKET)) {
                        // и добавляем операции из стека в результирующий список
                        listResult.add(stackOperation.pop());
                    }
                    // удаляем открвающуюся скобку
                    stackOperation.pop();
                } else {
                    // Если операция - не скобка, перебираем стек операций, и подходящие помещаем в результирующий список
                    while (!stackOperation.isEmpty() && !stackOperation.peek().equals(LEFT_BRACKET) &&
                            (OPERATION_PRIORITIES.get(nextOperation) >= OPERATION_PRIORITIES.get(stackOperation.peek()))) {
                        listResult.add(stackOperation.pop());
                    }
                    // помещаем операцию в стек
                    stackOperation.push(nextOperation);
                }
                // смещаем индекс начала отсчёта
                currentIndex = nextOperationIndex + nextOperation.length();
            }
        }

        // Добавление в результирующий список "остатков" выражения
        if (currentIndex != expression.length()) {
            listResult.add(expression.substring(currentIndex));
        }
        // Опустошаем стек операций, перенося их в результирующий список
        while (!stackOperation.isEmpty()) {
            listResult.add(stackOperation.pop());
        }
        // результирующая строка
        StringBuffer reversePolishNotation = new StringBuffer();
        // если результирующий список не пуст,
        if (!listResult.isEmpty()) {
            // переносим в результирующую строку первый элемент
            reversePolishNotation.append(listResult.remove(0));
        }
        // И все остальные элементы через пробел
        while (!listResult.isEmpty()) {
            reversePolishNotation.append(" ").append(listResult.remove(0));
        }

        return reversePolishNotation.toString();
    }

    private String replaceIncrementAndDecrement(String expression) {
        if (expression.length() <= 2) {
            return expression;
        }

        StringBuffer stringBuffer = new StringBuffer(expression);

        for (int i = 1; i < stringBuffer.length(); i++) {
            //если присутствует инкремент или декремент
            if (stringBuffer.charAt(i) == '-' && stringBuffer.charAt(i - 1) == '-' ||
                    stringBuffer.charAt(i) == '+' && stringBuffer.charAt(i - 1) == '+') {
                if (i + 1 < stringBuffer.length()) {
                    //если после инкремента или декремента идет не цифра
                    if (!Character.isDigit(stringBuffer.charAt(i + 1))) {
                        //то удаляем постинкремент (постдекремент)
                        stringBuffer.replace(i - 1, i + 1, "");
                    }
                //если постинкремент или постдекремент стоят в конце строки, то удаляем
                } else {
                    stringBuffer.replace(i - 1, i + 1, "");
                }
            }
        }

        return stringBuffer.toString();
    }

}
