
public class ProgramCounter {
    private String entry;
    private String exit;
    private String content;

    public ProgramCounter() {
        entry = null;
        exit = null;
        content = null;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void updateContent() {
        boolean flag = true;
        if (content.contains("¬")) {
            flag = false;
        }
        if (content.contains("(") && content.contains(")")) {
            content = content.substring(content.indexOf("(") + 1, content.indexOf(")"));
        }
        while (true) {
            if (content.contains(" ")) {
                content = content.substring(content.indexOf(" ") + 1, content.length());
            } else {
                break;
            }
        }
        if (!flag) {
            content = "¬(" + content + ")";
        }

    }
}
