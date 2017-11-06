import java.util.regex.Pattern;


public class Statement {
	private String label;
	private String content;
	private String type;
	private String exitLabel;
	public Statement(){
		label = null;
		content = null;
		type = null;
		exitLabel = null;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExitLabel() {
		return exitLabel;
	}
	public void setExitLabel(String exitLabel) {
		this.exitLabel = exitLabel;
	}
	public void setType(){
		if(!discoverWhile(this)){
			if(!discoverIf(this)){
				if(!discoverElse(this)){
					Pattern p = Pattern.compile("[a-z]");
					if(p.matcher(this.getContent()).find()){
						this.setType("normal");
					}else{
						this.setType("null");
					}
						
				}
			}
		}
	}
	public boolean discoverWhile(Statement stat){
		if(stat.getContent().contains("while")){
			stat.setType("while");
			return true;
		}else{
			return false;
		}
	}
	public boolean discoverIf(Statement stat){
		if(stat.getContent().contains("if")){
			stat.setType("if");
			return true;
		}else{
			return false;
		}
	}
	public boolean discoverElse(Statement stat){
		if(stat.getContent().contains("else")){
			stat.setType("else");
			return true;
		}else{
			return false;
		}
	}
	
}
