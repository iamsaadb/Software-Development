/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operators;
import Evaluator.*;

public class DivOperator extends Operator {
    //add has a prio 2
    @Override
    public int priority() {
        return 2;
    }
    // returns a/b
    @Override
    public Operand execute(Operand a, Operand b) {
        return new Operand(a.getValue() / b.getValue());
    }

}
