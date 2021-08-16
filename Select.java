import java.util.LinkedHashMap;

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
    private LinkedHashMap<String,Object> criterias = new LinkedHashMap<>();
    private 
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
        if(! SWITCH){
            throw new IllegalStateException("Criteria <"+caller+"> is called before where conditon");
        }
    }
}


class Expression{  
    private ExpressionName expressionName;
    private String key ;
    private Object value;
    public Expression(ExpressionName expressionName,String key,String value){
        this.expressionName = expressionName;
        this.key = key;
        this.value = value;
    }

    public boolean evaluate(Expression another){
         
    }
}

enum ExpressionName{
    AND,
    OR
}
// Select s = new Select("User").columns("Id","Name","Password").where("Id",20).and("Name","Ajay").or("Name","Chella");