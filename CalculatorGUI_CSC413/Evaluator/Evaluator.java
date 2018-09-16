package Evaluator;
import operators.*;
import java.util.*;

public class Evaluator{
  private  Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/(";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int eval( String expression ) {
    String token;
    int count = 0;
    expression = expression.replaceAll("\\s+","");
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );
    count = tokenizer.countTokens();
    while (this.tokenizer.hasMoreTokens()){
    token = tokenizer.nextToken();
    
    
     if(token.equals("(")){
            operatorStack.push(new ParOperator());
            continue;
            }
     
    else if (token.equals(")")){
            while (!operatorStack.isEmpty() && operatorStack.peek().priority()>0){
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            Operator operator = operatorStack.pop();
            operandStack.push(operator.execute(op1,op2));
            }
            operatorStack.pop();
            continue;
    }
    else if(Operand.check(token)){
            operandStack.push(new Operand(token));
            continue;
    }
    else if (Operator.check(token)){
            if (operatorStack.isEmpty()){
            operatorStack.push(Operator.getOperator(token));
            continue;
            }
            else if (operatorStack.peek().priority()<= Operator.getOperator(token).priority()){
            operatorStack.push(Operator.getOperator(token));
            continue;
            }
            else {
            while(!operatorStack.isEmpty() && operatorStack.peek().priority()> Operator.getOperator(token).priority()){
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            Operator operator = operatorStack.pop();
            operandStack.push(operator.execute(op1, op2));
            continue;
            }
            operatorStack.push(Operator.getOperator(token));
            }
            }
    }
    while (!operatorStack.isEmpty()){
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            Operator operator = operatorStack.pop();
            operandStack.push(operator.execute(op1,op2));
    }
            return operandStack.pop().getValue();

    } 
}