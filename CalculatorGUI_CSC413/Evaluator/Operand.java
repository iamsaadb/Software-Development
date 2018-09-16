package Evaluator;

public class Operand {
private int value;
private String token;

  //constructors 
  public Operand( String token ) {
  this.value=Integer.parseInt(token);
  }
  public Operand( int value ) {
  this.value=value;
  }
  //getter
  public int getValue() {
      return value;
  }
  //if token cant be parsed then throw exception
  public static boolean check( String token ) {
          try{
            Integer.parseInt(token);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }
}

