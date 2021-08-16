import java.util.LinkedHashMap;
import java.util.*;

public class Select {
   private Criteria criteria = new Criteria();
   private Row r;
   private String[] columns;
   private String tablename ;
   public Select(String tablename){
          this.tablename = tablename;
   }

   public Criteria columns(String... columns) throws ColumnNotFoundException{
       checkIfColumnsAreValid(columns);
       this.columns = columns;
       return criteria;
   }

   /**
    * Iterate over the columns from the GetTableDetails cache and throw if columns mismatched in given columns
    * @param columns
    * @throws ColumnNotFoundException
    */ 
   private  void checkIfColumnsAreValid(String[] columns) throws ColumnNotFoundException {
       
   }

}

class Criteria{
    private List<> criterias = new LinkedList<>();
    private boolean SWITCH = false;
    public Criteria where(String key,Object value) throws IllegalStateException{
        if(SWITCH) throw new IllegalStateException("Criteria  <where> is called more than once");
        SWITCH = true;
        return this;
    }

    public Criteria and(String key,Object value) throws IllegalStateException{
        checkSwitchAndThrowException("and");

        return this;
    }

    public Criteria or(String key,Object value) throws IllegalStateException{
        checkSwitchAndThrowException("or");
        return this;
    }


    private void checkSwitchAndThrowException(String caller) throws IllegalStateException{
        if(!SWITCH){
            throw new IllegalStateException("Criteria <"+caller+"> is called before where conditon");
        }
    }
}


class Expression{  
    private Operator operator;
    private Comparable LHS ;
    private Comparable RHS;

    public Expression(Operator operator,Comparable LHS,Comparable RHS){
        this.operator = operator;
        this.LHS = LHS;
        this.RHS = RHS;
    }

    public boolean evaluate(){
         switch(operator){
             case GT :
                   return LHS.compareTo(RHS) == 1 ;
             case EQU :
                   return LHS.compareTo(RHS) == 0;
             case GTE :
                   return LHS.compareTo(RHS) >= 0;
             case LTE :
                   return LHS.compareTo(RHS) <= 0;
             case LT :
                   return LHS.compareTo(RHS) < 0; 
             default :
                   return false;   
        }
    }
} 

class ReducerUtil {
    public static boolean parseAllCriterasAndReturnFinalBoolean(){
        while
     }
 
     public static boolean reduce(boolean LHS,WrappedCondition RHS){
         switch(RHS.getExpressionName()){
            case AND :
                 return LHS &&  RHS.getExpression().evaluate();
            case OR :
                 return LHS ||  RHS.getExpression().evaluate();
            default :
                 return false;
         }
     }
}

class WrappedCondition {
     private Expression expression;
     private ExpressionName expressionName; //AND OR 
    
     public WrappedCondition(Expression expression,ExpressionName expressionName){
         this.expression = expression;
         this.expressionName = expressionName;
     }

     public ExpressionName getExpressionName(){
         return expressionName;
     }

     public Expression getExpression(){
        return expression;
    }
}

enum ExpressionName{
    AND,
    OR
}

enum Operator {
    GT,
    GTE,
    LT,
    LTE,
    EQU
}
// Select s = new Select("User").columns("Id","Name","Password").where("Id",20).and("Name","Ajay").or("Name","Chella");