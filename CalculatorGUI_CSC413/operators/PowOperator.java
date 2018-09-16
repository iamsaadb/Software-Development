/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operators;
import Evaluator.*;

public class PowOperator extends Operator {
    //add has a prio 3
    @Override
    public int priority() {
        return 3;
    }
    // returns apowb
    @Override
    public Operand execute(Operand a, Operand b) {
        //cast to int
        return new Operand((int) Math.pow (a.getValue(),b.getValue()));
    }
}
