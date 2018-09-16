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
public class Call extends ByteCode{
    public String funcname;
    public int address;

    
    @Override
    public void init(String vars[]){
    funcname = vars[1];
    }
    
    @Override
    public void execute (VirtualMachine vm){
    
    vm.pushReturnAd(vm.getPc());
    vm.setPc(address-1);
    }
    
    public String toString(){
    return "CALL " + funcname;
    }
    
    public void setAddress(int Ad){
    address = Ad;
    
    }
    
    public String getFuncName(){
    return funcname;
    }
}
