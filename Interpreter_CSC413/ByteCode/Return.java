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
public class Return extends ByteCode {
    public String funcname;
    
    @Override
    public void init (String vars[]){
    funcname = vars[0];
    }
    
    @Override
    public void execute (VirtualMachine vm){
    vm.popFrame();
    int save = vm.popReturnAd();
    vm.setPc(save);
    }
    
    @Override
    public String toString(){
    
    return "RETURN " + funcname;
    }
    
    public String getFuncName(){
    
    return funcname;
    }
}
