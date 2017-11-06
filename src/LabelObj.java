import java.io.*;

public class LabelObj {

    public Boolean labellingProgram(String fileName, String labelFileName) {
        File oldFile = new File(fileName);
        if (!oldFile.exists()) {
            System.out.println("program file not found");
            return false;
        }
        File labellingFunctionFile = new File(labelFileName);
        BufferedReader reader = null;
        if (!labellingFunctionFile.exists()) {
            try {
                labellingFunctionFile.createNewFile();
            } catch (IOException ioe) {
                ioe.getMessage();
            }
        }
        try {
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(labellingFunctionFile));
            BufferedWriter writer = new BufferedWriter(write);
            reader = new BufferedReader(new FileReader(oldFile));
            String tempString;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                StringBuilder tempLabel = new StringBuilder();
                if (tempString.contains(";") && !tempString.contains("for")) {
                    String[] temp = tempString.split(";");
                    tempString = temp[0];
                }
                tempLabel.append("L").append(String.valueOf(line));
                String lineLabel = tempLabel + ":" + tempString + "\r\n";
                writer.write(lineLabel);
                line++;
            }
            reader.close();
            writer.flush();
            write.close();
            writer.close();
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
        return true;
    }

}