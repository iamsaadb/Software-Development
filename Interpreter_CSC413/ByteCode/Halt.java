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
public class Halt extends ByteCode {
   
    @Override
    public void init(String[] args){
    
    }
    
    @Override
    public String toString(){
    return "HALT"; 
    }

    @Override
    public void execute(VirtualMachine vm) {
    vm.halt();
    }

   
}
