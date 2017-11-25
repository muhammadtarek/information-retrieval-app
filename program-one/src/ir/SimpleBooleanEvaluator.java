/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir;

import java.util.Iterator;

import com.fathzer.soft.javaluator.*;

/**
 * An example of how to implement an evaluator from scratch.
 */
public class SimpleBooleanEvaluator extends AbstractEvaluator<String> {

    /**
     * The negate unary operator.
     */
    public final static Operator NEGATE = new Operator("!", 1, Operator.Associativity.RIGHT, 3);
    /**
     * The logical AND operator.
     */
    private static final Operator AND = new Operator("&", 2, Operator.Associativity.LEFT, 2);
    /**
     * The logical OR operator.
     */
    public final static Operator OR = new Operator("|", 2, Operator.Associativity.LEFT, 1);

    private static final Parameters PARAMETERS;

    static {
        // Create the evaluator's parameters
        PARAMETERS = new Parameters();
        // Add the supported operators
        PARAMETERS.add(AND);
        PARAMETERS.add(OR);
        PARAMETERS.add(NEGATE);
        PARAMETERS.addExpressionBracket(BracketPair.PARENTHESES);
    }

    public SimpleBooleanEvaluator() {
        super(PARAMETERS);
    }

    @Override
    protected String toValue(String literal, Object evaluationContext) {
        return literal;
    }

    @Override
    protected String evaluate(Operator operator, Iterator<String> operands, Object evaluationContext) {
        String result = "";
        if (operator == NEGATE) {
            for (Character c : operands.next().toCharArray()) {
                if (c == '1') {
                    result = result + '0';
                }
                if (c == '0') {
                    result = result + '1';
                }
            }
            return result;
        } else if (operator == OR) {
            String o1 = operands.next();
            String o2 = operands.next();
            for (int i = 0; i < o1.length(); i++) {
                if (o1.charAt(i) == '1' || o2.charAt(i) == '1') {
                    result = result + '1';
                } else {
                    result = result + '0';
                }
            }
            return result;
        } else if (operator == AND) {
            String o1 = operands.next();
            String o2 = operands.next();
            for (int i = 0; i < o1.length(); i++) {
                if (o1.charAt(i) == '0' || o2.charAt(i) == '0') {
                    result = result + '0';
                } else {
                    result = result + '1';
                }
            }
            return result;
        } else {
            return "Ops";
        }
    }

    public static String booleanResult(String expression) {
        SimpleBooleanEvaluator evaluator = new SimpleBooleanEvaluator();
        return evaluator.evaluate(expression);
    }
}