import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class FirstOrderLogicBuilder {
	private ArrayList<ProgramCounter> pcList = new ArrayList<>();
	public void buildFirstOrderLogic(ArrayList<Statement> statementList){
		for(Statement st : statementList){
			ProgramCounter pc = new ProgramCounter();
			ProgramCounter pc1 = new ProgramCounter();
			if("normal".equals(st.getType())){
				pc.setEntry(st.getLabel());
				pc.setExit(st.getExitLabel());
				pc.setContent(st.getContent());
			}
			if("if".equals(st.getType())){
				pc.setEntry(st.getLabel());
				pc.setExit("L" + (Integer)(Integer.parseInt(st.getLabel().substring(1))+1));
				pc.setContent(st.getContent());
				pc1.setEntry(st.getLabel());
				pc1.setExit(st.getExitLabel());
				pc1.setContent("¬" + st.getContent());
			}
			if("while".equals(st.getType())){
				pc.setEntry(st.getLabel());
				pc.setExit("L" + (Integer)(Integer.parseInt(st.getLabel().substring(1))+1));
				pc.setContent(st.getContent());
				pc1.setEntry(st.getLabel());
				pc1.setExit(st.getExitLabel());
				pc1.setContent("¬" + st.getContent());
			}
			if(pc.getEntry() != null){
				pcList.add(pc);
			}
			if(pc1.getEntry() != null){
				pcList.add(pc1);
			}
		}
		for(ProgramCounter pc : pcList){
			pc.updateContent();
			System.out.println("pc = " + pc.getEntry() + " ∧ pc' = " + pc.getExit() + " ∧ " + pc.getContent());
		}
	}
	public void printer(String filename) throws IOException{
		File f = new File(filename);
        if (!f.exists()) {
            f.createNewFile();
        }
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f));
        BufferedWriter writer = new BufferedWriter(write);
        for(ProgramCounter pc : pcList){
			String line = "pc = " + pc.getEntry() + " ∧ pc' = " + pc.getExit() + " ∧ " + pc.getContent() + "\r\n";
			writer.write(line);
        }
        writer.flush(); 
        write.close();
        writer.close();		
	}
}
