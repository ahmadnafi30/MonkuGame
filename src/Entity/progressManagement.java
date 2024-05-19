package Entity;

import java.io.*;
public class ProgressManagement{

    private String savePath;
    private boolean appendToFile;

    public ProgressManagement(String path){
        savePath = path;
    }
    
    public ProgressManagement(String path, boolean appendValue){
        savePath = path;
        appendToFile = appendValue;
    }

    public void savingProgress(String textLine) throws IOException{
        FileWriter write = new FileWriter(savePath, appendToFile);
        PrintWriter printProgress = new PrintWriter(write);

        printProgress.println(textLine);
        printProgress.close();
    }
}
