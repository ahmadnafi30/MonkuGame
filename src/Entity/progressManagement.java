package Entity;

import java.io.*;
public class progressManagement{

    private String savePath;
    private boolean appendToFile;

    public progressManagement(String path){
        savePath = path;
    }
    
    public progressManagement(String path, boolean appendValue){
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
