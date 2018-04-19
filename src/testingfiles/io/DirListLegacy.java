package testingfiles.io;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class DirListLegacy {
    public static void main(String[] args) throws FileNotFoundException{
        File path = new File("src\\testingfiles\\testinput");
        String[] list;
        String regEx = ".+\\.in";
        if(args.length == 0)
            list = path.list(new DirFilter(regEx));//path.list();
        else
            list = path.list(new DirFilter(regEx));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        String curPath = new String("src\\testingfiles\\testinput\\" + list[0]);
        DataInputStream in = new DataInputStream(new FileInputStream(curPath));

        for(String dirItem : list)
            System.out.println(dirItem);
    }
}

/// D.*\.java

