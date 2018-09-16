/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operators;
import Evaluator.*;

public class ParOperator extends Operator {
    //Open Paranthesis has a prio 0
    @Override
    public int priority() {
        return 0;
    }

    @Override
    public Operand execute(Operand a, Operand b) {
    return null;
    }
   

}
