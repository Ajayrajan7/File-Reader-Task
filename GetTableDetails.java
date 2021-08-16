import java.util.*;
import java.io.*;
public class GetTableDetails {
    private static String[] FIELDS = new String[]{"STRING","INTEGER","LONG","DOUBLE","FLOAT","BOOLEAN"};
    private static final Set Fields = new HashSet<String>(Arrays.asList(FIELDS));
    public static HashMap<String,LinkedHashMap<String,Class>> tablesVsFieldDetails = new HashMap<>();
    static String dataPath="";
    public static void initialize(String path) throws IOException{
        Properties props = parseProps("C:\\Users\\AjaySandhya\\Desktop\\File DB\\conf\\Tables.props");
        Properties props2 = parseProps("C:\\Users\\AjaySandhya\\Desktop\\File DB\\conf\\dataconfig.props");
        dataPath = props2.getProperty("DataLocation");
        createTablesVsFields(props);

    }

    private static Properties parseProps(String path) throws IOException{
        Properties p = new Properties();
        FileReader f = new FileReader(path);
        p.load(f);
        return p;
    }
    private static void createFileIfNotExists(String key){
        File f = new File(dataPath+"\\"+key+".txt");
        if(!f.exists()){
            try{
                f.createNewFile();
                System.out.println("New file is created");
            }catch(Exception e){
                System.out.println(e);
            }
        }else{
            System.out.println("File exists already");
        }
    }

    public static LinkedHashMap<String,Class> getFieldVsTypes(String tableName){
        return tablesVsFieldDetails.get(tableName);
    }

    private static void createTablesVsFields(Properties properties){

        for(String key : properties.stringPropertyNames()) {
            createFileIfNotExists(key);
            String value = properties.getProperty(key);
            try {
                tablesVsFieldDetails.put(key,getFieldVsTypes_(value));
            }
            catch(ClassNotFoundException e){
                throw new RuntimeException(e);
            }
          }
    }

    private static LinkedHashMap<String,Class> getFieldVsTypes_(String tableBuffer)throws IllegalArgumentException,ClassNotFoundException{
        LinkedHashMap<String,Class> fieldVsTypes = new LinkedHashMap<String,Class>();
        String[] values = null ;
        for(String entry : tableBuffer.split(",")){
             values = entry.split(":");
             if(values!=null){
                if(values.length != 2 &&  !Fields.contains(values[1].toUpperCase())){
                    throw new IllegalArgumentException("Field "+values[0]+" has Invalid type "+values[1]);
                }
                else{
                    fieldVsTypes.put(values[0],getClassName(values[1])); 
                }
            }  
        }
        return fieldVsTypes;
    }

    private static Class getClassName(String str) throws ClassNotFoundException{
        String Capitalized = str.toLowerCase().substring(0, 1).toUpperCase() + str.substring(1);
        return Class.forName("java.lang."+Capitalized);
    }

   
}