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
public class BOP extends ByteCode {
    public String binaryOp;
    
    @Override
    public void init(String vars[]){
    binaryOp = vars[0];
    }
    
    @Override
    public void execute (VirtualMachine vm){
    int a = vm.popRunStack();
    int b = vm.popRunStack();
    int ret = 0;
    
    if ("+".equals(binaryOp)){
    ret = a+b;
    }
    else if ("-".equals(binaryOp)){
    ret = b-a;
    }
    else if ("*".equals(ret)){
    ret = a*b;
    }
    else if ("/".equals(binaryOp)){
    ret = b/a;
    }
    else if ("!=".equals(binaryOp)){
    if (a != b) ret = 1;
    }
    else if ("==".equals(binaryOp)){
    if (a==b) ret = 1 ;
    }
    else if ("<=".equals(binaryOp)){
    if (a<=b) ret = 1 ;
    }
    else if (">=".equals(binaryOp)){
    if (a>=b) ret = 1 ;
    }
    else if ("<".equals(binaryOp)){
    if (a<b) ret = 1 ;
    }
    else if (">".equals(binaryOp)){
    if (a>b) ret = 1 ;
    }
    else if ("|".equals(binaryOp)){
    if (a==1 || b==1) ret = 1 ;
    }
    else if ("&".equals(binaryOp)){
    if (a==1 && b==1) ret = 1 ;
    }
    else {System.out.println("INVALID OPERATOR");
    }
    vm.pushRunStack(ret);
    }
    
    @Override
    public String toString(){
    
    return "BOP  " + binaryOp;
    
    }
   
    
}
