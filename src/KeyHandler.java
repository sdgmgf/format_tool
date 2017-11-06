import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class KeyHandler {

	public ArrayList<Statement> statementListGenerator(String filename) throws IOException{
		File file = new File(filename);
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(file));
        String tempString;
		ArrayList<Statement> statementList = new ArrayList<>();
        while ((tempString = reader.readLine()) != null) {
        	Statement stat = new Statement();
        	String[] temp = tempString.split(":");
        	stat.setLabel(temp[0]);
        	stat.setContent(temp[1]);
        	stat.setType();
			System.out.println(stat.getLabel() + " " + stat.getContent() + " " + stat.getType() + " " + stat.getExitLabel());
			statementList.add(stat);
		}
		statementExitLabelHandler(statementList);
		reader.close();
        return statementList;
	}

	public void statementExitLabelHandler(ArrayList<Statement> statementList){
		for (int i=0;i<statementList.size();i++) {
			Statement st = statementList.get(i);
			if("normal".equals(st.getType())){
				st.setExitLabel("L" + (Integer)(Integer.parseInt(st.getLabel().substring(1))+1));
			}else if("if".equals(st.getType())){
				int nmOfLB = 1;
				int nmOfRB = 0;
				for(int j = i + 1; j < statementList.size(); j++){
					if(statementList.get(j).getContent().contains("}")){
						nmOfRB++;
					}
					if(nmOfLB == nmOfRB){
						st.setExitLabel("L" + (int)(j+2));
						break;
					}
					if(statementList.get(j).getContent().contains("{")){
						nmOfLB++;
					}
				}
			}else if("else".equals(st.getType())){
				int nmOfLB = 1;
				int nmOfRB = 0;
				for(int j = i + 1; j < statementList.size(); j++){
					if(statementList.get(j).getContent().contains("{")){
						nmOfLB++;
					}
					if(statementList.get(j).getContent().contains("}")){
						nmOfRB++;
					}
					if(nmOfLB == nmOfRB){
						st.setExitLabel("L" + (int)(j+1));
						statementList.get(i-1).setExitLabel("L" + (int)(j+1));
						break;
					}
				}
			}else if("while".equals(st.getType())){
				int nmOfLB = 1;
				int nmOfRB = 0;
				for(int j = i + 1; j < statementList.size(); j++){
					if(statementList.get(j).getContent().contains("}")){
						nmOfRB++;
					}
					if(nmOfLB == nmOfRB){
						st.setExitLabel("L" + (int)(j+1));
						statementList.get(j-1).setExitLabel("L" + (int)(i + 1));
						break;
					}
					if(statementList.get(j).getContent().contains("{")){
						nmOfLB++;
					}
				}
			}
		}
	}

}
