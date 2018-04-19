package testingfiles.io;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class DirList{

    private List<Path> pathList;


    public DirList(String rawPath) {
        File path = new File(rawPath);
        String[] list;
        String regEx = ".+\\.in";
        list = path.list(new DirFilter(regEx));//path.list();

    }
}