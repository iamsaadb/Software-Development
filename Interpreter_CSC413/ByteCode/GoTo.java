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
public class GoTo extends ByteCode{
    public int address;
    public String label;
    
    
    public void setAddress(int ad){
    address = ad; 
    }
    
    public String getLabel(){
    return label;
    }
    @Override
    public void init(String vars[]){
    label = vars[0];
    }
    @Override
    public void execute(VirtualMachine vm){
    vm.setPc(address);
    }
    @Override
    public String toString(){
    return "GOTO" + label;
    }
}
