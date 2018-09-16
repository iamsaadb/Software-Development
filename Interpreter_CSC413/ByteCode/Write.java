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
public class Write extends ByteCode{
    
    @Override
    public void init(String vars[]){
    
    }
    
    @Override
    public void execute(VirtualMachine vm){
    System.out.println(vm.peekRunStack());
    }
    
    
    @Override
    public String toString(){
    return "WRITE";
    
    }
    
}
