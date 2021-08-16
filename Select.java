import java.util.*;
import java.io.*;
public class Select{

	public LinkedHashMap<String,Object> getData(String tableName, String ...args){

		String path=GetTableDetails.dataPath+"\\"+tableName+".txt";
		LinkedHashMap<String,Class> realOrder = GetTableDetails.tablesVsFieldDetails.get(tableName);

		try{
			File f = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(f));

			String line;
			String[] parts;
			LinkedHashMap<String,Object> outputData=new LinkedHashMap<>();
			Set<String> neededCols = new HashSet<String>(Arrays.asList(args));
			int index=0;

			while((line=br.readLine())!=null){
				parts=line.split(" ");
				for(Map.Entry<String,Class> entry:realOrder.entrySet()){
					if(neededCols.contains(entry.getKey())){
						if(entry.getValue().toString().equals("class java.lang.String")){
							parts[index]=parts[index].replaceAll("\\\\s"," ");
							outputData.put(entry.getKey(),parts[index]);
						}else if(entry.getValue().toString().equals("class java.lang.Integer")){
							outputData.put(entry.getKey(),Integer.valueOf(parts[index]));
						}else if(entry.getValue().toString().equals("class java.lang.Long")){
							outputData.put(entry.getKey(),Long.valueOf(parts[index]));
						}else if(entry.getValue().toString().equals("class java.lang.Double")){
							outputData.put(entry.getKey(),Double.valueOf(parts[index]));
						}else if(entry.getValue().toString().equals("class java.lang.Float")){
							outputData.put(entry.getKey(),Float.valueOf(parts[index]));
						}else if(entry.getValue().toString().equals("class java.lang.Boolean")){
							outputData.put(entry.getKey(),Boolean.valueOf(parts[index]));
						}
					}
					index++;
				}
				index=0;
			}
			br.close();
			return outputData;
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

}