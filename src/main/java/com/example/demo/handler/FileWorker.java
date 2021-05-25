package com.example.demo.handler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWorker {

    public FileWorker(String fileName, String solution)
    {
        int start;
        int stop;
        String buffer;
        try {
            fileName = "solutionId_" + fileName;
            File file = new File(PathConst.TASK_PATH + fileName);
            file.mkdir();
            File wfile = new File(PathConst.TASK_PATH + fileName, fileName + ".java");
            wfile.setWritable(true);
            wfile.setReadable(true);
            wfile.setExecutable(true);
            FileWriter fileWriter = new FileWriter(wfile, true);
            if(solution.startsWith("package")){
                stop = solution.indexOf(";")+1;
                solution = solution.substring(stop);
            }
            start = solution.lastIndexOf("public class ")+13;
            stop = solution.indexOf("{")-1;
            buffer = solution.substring(start,stop);
            solution = solution.replaceFirst(buffer, fileName);
            fileWriter.write("package com.example.demo.handler.tasks." + fileName + ";\n");
            fileWriter.write(solution);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
