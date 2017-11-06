import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class LabelObj {

	public LinkedHashMap<String, String> labellingFunction(String fileName) {
        LinkedHashMap<String, String> codesMapping = new LinkedHashMap<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
            	StringBuilder tempLabel = new StringBuilder();
            	String[] temp = tempString.split(";");
            	tempString = temp[0];
            	tempLabel.append("L").append(String.valueOf(line));
            	codesMapping.put(tempLabel.toString(), tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return codesMapping;
    }
	
	public void printLabellingFunction(LinkedHashMap<String, String> codesMapping, String filename) throws IOException{
		File f = new File(filename);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ioe) {
                ioe.getMessage();
            }
        }
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f));
        BufferedWriter writer = new BufferedWriter(write);
        for(Map.Entry<String, String> entry : codesMapping.entrySet()){  
			String line = entry.getKey()+":"+entry.getValue()+"\r\n";
			writer.write(line);
        }
        writer.flush(); 
        write.close();
        writer.close();		
	}
}