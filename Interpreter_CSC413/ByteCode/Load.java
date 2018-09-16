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
public class Load extends ByteCode {
    String id;
    int offset;
    
    @Override
    public void init (String vars[]){
    offset = Integer.parseInt(vars[0]);
    id = vars[1];
    }
    
    @Override
    public void execute (VirtualMachine vm){
    vm.loadStack(offset);
   
    }
    
    @Override
    public String toString(){
    return "LOAD" + offset + " " + id + " " + "//UNFINISHED" ;}
}
