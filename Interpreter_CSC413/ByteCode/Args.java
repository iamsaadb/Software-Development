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
public class Args extends ByteCode {
    //number of arguments
    int nArgs;
   
    @Override
    public void init(String vars[]){
    nArgs = Integer.parseInt(vars[0]);
    }
    
    @Override
    public void execute (VirtualMachine vm){
        vm.newFrame(nArgs);
    
    }
    @Override
    public String toString(){
        return "ARGS  " + nArgs +"\n"; 
    }
}
