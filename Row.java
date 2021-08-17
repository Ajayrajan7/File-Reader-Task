import java.util.LinkedHashMap;

public class Row {
    //Make the below fields private and make getters and setters.
    String tableName;
    int seekPos;
    LinkedHashMap rowDetails;


    double getDouble(String columnName) throws ClassCastException,NoSuchColumnException{
        try {
          return  (double)rowDetails.get(columnName);
        }
        catch(NullPointerException ex){
            throw NoSuchColumnException("Column "+columnName+" is not found in the table " +tableName);
        }
        
    }

    long getLong(String columnName) throws ClassCastException,NoSuchColumnException{

        
        try {
            return (long)rowDetails.get(columnName);
          }
          catch(NullPointerException ex){
              throw NoSuchColumnException("Column "+columnName+" is not found in the table " +tableName);
          }
    }

    int getInt(String columnName) throws ClassCastException,NoSuchColumnException{    
        try {
            return (int)rowDetails.get(columnName);
          }
          catch(NullPointerException ex){
              throw NoSuchColumnException("Column "+columnName+" is not found in the table " +tableName);
          }
    }

    float getFloat(String columnName) throws ClassCastException,NoSuchColumnException{
        try {
            return (float)rowDetails.get(columnName);
          }
          catch(NullPointerException ex){
              throw NoSuchColumnException("Column "+columnName+" is not found in the table " +tableName);
          }
    }

    String getString(String columnName) throws ClassCastException,NoSuchColumnException{
        //get the field
        String temp = (String)rowDetails.get(columnName);
        if(null == temp)  throw NoSuchColumnException("Column "+columnName+" is not found in the table " +tableName);
        return temp;
    }

}