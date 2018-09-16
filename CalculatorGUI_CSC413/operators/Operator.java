package operators;
import Evaluator.Operand;
import java.util.HashMap;

public abstract class Operator {
  static final HashMap<String,Operator> OPERATORS = new HashMap<String,Operator>();
  //populate the abstract hashmap using a static block
  static {
  OPERATORS.put("+", new AddOperator());
  OPERATORS.put("-", new SubOperator());
  OPERATORS.put("*", new MulOperator());
  OPERATORS.put("/", new DivOperator());
  OPERATORS.put("^", new PowOperator());
  OPERATORS.put("(", new ParOperator());
  }
  
  public abstract int priority();
  public abstract Operand execute( Operand op1, Operand op2 );
  public static Operator getOperator(String token){
  return OPERATORS.get(token);
  }
  
  public static boolean check( String token ) {
        return OPERATORS.containsKey(token);
  }
}
