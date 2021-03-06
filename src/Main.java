import java.io.IOException;
import java.util.ArrayList;

/**
 * @author xiaofei
 * @date 2017/11/01
 */
public class Main {

    /**
     * 程序文件
     */
    private static final String PARAM_FILE_NAME = "src/file/LabelledProgram.txt";
    /**
     * 标记文件
     */
    private static final String FUNCTION_FILE_NAME = "src/file/LabellingFunction.txt";
    /**
     * 结果
     */
    private static final String FIRST_ORDER_LOGIC_FILE_NAME = "src/file/FirstOrderLogic.txt";

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //label the program
        LabelObj lo = new LabelObj();
        Boolean labelResult = lo.labellingProgram(PARAM_FILE_NAME, FUNCTION_FILE_NAME);
        if (!labelResult) {
            return;
        }
        KeyHandler kh = new KeyHandler();
        //store all the labelled statement in an array list
        ArrayList<Statement> statementList = kh.statementListGenerator(FUNCTION_FILE_NAME);
        FirstOrderLogicBuilder folb = new FirstOrderLogicBuilder();
        //build first order logic(boolean formulae) from the program
        folb.buildFirstOrderLogic(statementList);
        //print out first order logic of the program
        folb.printer(FIRST_ORDER_LOGIC_FILE_NAME);
    }
}
