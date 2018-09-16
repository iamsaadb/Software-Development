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
public class Pop extends ByteCode{
    //count # of popping times
    int count;
    
    @Override
    public void init(String vars[]){
    count = Integer.parseInt(vars[0]);
    }
    
    @Override
    public void execute(VirtualMachine vm){
    for (int i = 0; i<count ;i++){
    vm.popRunStack();
    }    
    }

    
    @Override
    public String toString(){
    return "POP" + count;
    }
}
