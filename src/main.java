import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author xiaofei
 * @date 2017/11/01
 */
public class main {

    private static final String PARAM_FILE_NAME = "C:/Users/xiaofei/Desktop/labelledProgram.txt";
    private static final String FUNCTION_FILE_NAME = "C:/Users/xiaofei/Desktop/LabellingFunction.txt";
    private static final String FIRST_ORDER_LOGIC_FILE_NAME = "C:/Users/xiaofei/Desktop/FirstOrderLogic.txt";

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //label the program
        LabelObj lo = new LabelObj();
        LinkedHashMap<String, String> codesMapping = lo.labellingFunction(PARAM_FILE_NAME);
        //print out labelled program
        lo.printLabellingFunction(codesMapping, FUNCTION_FILE_NAME);
        KeyHandler kh = new KeyHandler();
        //store all the labelled statement in an array list
        ArrayList<Statement> statementList = kh.statementListGenerator(PARAM_FILE_NAME);
        FirstOrderLogicBuilder folb = new FirstOrderLogicBuilder();
        //build first order logic(boolean formulae) from the program
        folb.buildFirstOrderLogic(statementList);
        //print out first order logic of the program
        folb.printer(FIRST_ORDER_LOGIC_FILE_NAME);
    }
}
