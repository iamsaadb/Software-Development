/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ByteCode;
import interpreter.*;

/**
 *
 * @author Saad
 */
public class Lit extends ByteCode {
    public String var = "";
    public int value; 
    
    @Override
    public void init(String vars[]){
    value = Integer.parseInt(vars[0]);
    if (vars.length>1) {
    var = vars[1];
    }
    }
    
    @Override
    public void execute(VirtualMachine vm){
    //unfinished
    }
    
    @Override
    public String toString(){
    return "LIT" + value + " " + var ;
    }
}
