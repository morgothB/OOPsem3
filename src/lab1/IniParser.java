package lab1;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IniParser {

    private Scanner in;

    private HashMap <Pair <String, String>, Integer> intMap;

    private HashMap <Pair <String, String>, Double> doubleMap;

    private HashMap <Pair <String, String>, String> stringMap;


    private static final Pattern trueSection = Pattern.compile("^\\[[A-Z0-9_]+\\]$");
    private static final Pattern trueParam = Pattern.compile("^\\w*[a-zA-Z0-9]+\\w$");

    private IniParser(){}

    public IniParser(String fileName) throws Exception {
        File inputFile = new File(fileName);
        this.in = new Scanner(inputFile);
        intMap = new HashMap<>();
        doubleMap = new HashMap<>();
        stringMap = new HashMap<>();
        parseFile();
    }

    public void parseFile() throws Exception{
        Pattern section = Pattern.compile("^\\[.*\\]$");
        Pattern param = Pattern.compile("^[^\\s&&^\\h&&\\w]+$");
        Integer intNum;
        Double doubleNum;
        String curLine = in.next();
        String curSectionName = "WRONG_SECTION";
        String curParamName = "WRONG_PARAM";
        while (in.hasNext()){
            if (curLine.equals(";")){
                in.nextLine();
                curLine = in.next();
                continue;
            }
            Matcher m = section.matcher(curLine);
            if (m.matches()){
                System.out.println(curLine);
                curSectionName = curLine;
                m = trueSection.matcher(curSectionName);
                if (!m.matches()) throw new Exception("WRONG SECTION NAME");
                curLine = in.nextLine();

                continue;
            }
            m = param.matcher(curLine);
            if(m.matches()){
                System.out.print(curLine + " is param ");
                curParamName = curLine;
                m = trueParam.matcher(curParamName);
                if (!m.matches()) throw new Exception("WRONG PARAM NAME");
                Pair<String, String> sectionParamPair = new Pair<>(curSectionName, curParamName);
                if (!in.next().equals("=")) throw new Exception("WRONG PARAM NAME");
                curLine = in.next();
                try {
                    intNum = Integer.parseInt(curLine);
                    intMap.put(sectionParamPair, intNum);
                    System.out.println("\"" + intNum + "\" of INTEGER");
                }
                catch (NumberFormatException e){
                    try {
                        doubleNum = Double.parseDouble(curLine);
                        doubleMap.put(sectionParamPair, doubleNum);
                        System.out.println("\"" + doubleNum + "\" of DOUBLE");
                    }
                    catch (NumberFormatException d){
                        stringMap.put(sectionParamPair, curLine);
                        System.out.println("\"" + curLine + "\" of STRING");
                    }
                }
            }
            try {
                curLine = in.next();
            }
            catch (NoSuchElementException e){

            }
        }
    }

    public <T> T getValue(Pair<String, String> key){
        T res;
        res = (T)intMap.get(key);
        if (res != null) return res;
        res = (T)doubleMap.get(key);
        if (res != null) return res;
        res = (T)stringMap.get(key);
        if (res != null) return res;
        throw new NoSuchElementException("THERE NO NO SUCH PAIR OF SECTION AND PARAMETER IN FILE");
    }

}
