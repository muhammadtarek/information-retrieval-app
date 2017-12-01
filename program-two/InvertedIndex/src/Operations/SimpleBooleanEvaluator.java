
package Operations;
import com.fathzer.soft.javaluator.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
/**
 *
 * @author mohamed
 */
public class SimpleBooleanEvaluator extends AbstractEvaluator<String> {
    public final static Operator UNION = new Operator("|", 2, Operator.Associativity.LEFT, 1);
    public final static Operator INTERSECT = new Operator("&", 2, Operator.Associativity.LEFT, 1);
    public final static Operator DIFFERENCE = new Operator("~", 2, Operator.Associativity.LEFT, 1);
    private static final Parameters PARAMETERS;
    
    static{
        PARAMETERS = new Parameters();
        PARAMETERS.add(UNION);
        PARAMETERS.add(INTERSECT);
        PARAMETERS.add(DIFFERENCE);
        PARAMETERS.addExpressionBracket(BracketPair.PARENTHESES);
    }
    
    public SimpleBooleanEvaluator(){
        super(PARAMETERS);
    }

    @Override
    protected String toValue(String literal, Object evaluationContext) {
        return literal;
    }
    
    @Override
    protected String evaluate(Operator operator , Iterator<String> operands , Object evaluationContext){
        String result="";
        if(operator == INTERSECT){
            LinkedHashSet<String> o1 = new LinkedHashSet<>(Arrays.asList(operands.next().split(",")));
            System.out.println("o1 is : " + o1.toString());
            LinkedHashSet<String> o2 = new LinkedHashSet<>(Arrays.asList(operands.next().split(",")));
            System.out.println("o2 is : " + o2.toString());
            o1.retainAll(o2);
            System.out.println("op is intersect and result is " + o1.toString());
            return String.join("," , o1);
        }
        else if(operator == UNION){
            LinkedHashSet<String> o1 = new LinkedHashSet<>(Arrays.asList(operands.next().split(",")));
            System.out.println("o1 is : " + o1.toString());
            LinkedHashSet<String> o2 = new LinkedHashSet<>(Arrays.asList(operands.next().split(",")));
            System.out.println("o2 is : " + o2.toString());
            o1.addAll(o2);
            System.out.println("op is union and result is " + o1.toString());
            return String.join("," , o1);
        }
        else if(operator == DIFFERENCE){
            LinkedHashSet<String> o1 = new LinkedHashSet<>(Arrays.asList(operands.next().split(",")));
            System.out.println("o1 is : " + o1.toString());
            LinkedHashSet<String> o2 = new LinkedHashSet<>(Arrays.asList(operands.next().split(",")));
            System.out.println("o2 is : " + o2.toString());
            o1.removeAll(o2);
            System.out.println("op is diff and result is " + o1.toString());
            return String.join("," , o1);
        }
        else
            return "ops";
    }
    
    public static String booleanResult(String expression){
        SimpleBooleanEvaluator evaluator = new SimpleBooleanEvaluator();
        String result = evaluator.evaluate(expression);
        return result;
    }
}
