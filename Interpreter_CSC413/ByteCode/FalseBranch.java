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
public class FalseBranch extends ByteCode{
    
    public int address;
    public String label;
    
    public void init (String vars[]){
    label = vars [0];
    }
    public void execute (VirtualMachine vm){
    if(vm.popRunStack() == 0){
    vm.setPc(address);
    }
    }
    @Override
    public String toString(){
    
    return "FALSEBRANCH" + label;
    
    }
    
    public String getLabel(){
    
    return label;
    
    }
    
    public void setAddress(int ad){
    address = ad; 
    
    }
}
