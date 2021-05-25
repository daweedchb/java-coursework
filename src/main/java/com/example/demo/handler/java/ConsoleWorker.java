package com.example.demo.handler.java;

import com.example.demo.handler.PathConst;

import java.io.*;

public class ConsoleWorker {
    private String[] commands;
    private ProcessBuilder process;
    private Process isRun;

    ConsoleWorker(String name) throws IOException {
        commands = new String[]{"java", name+".java"};
        process = new ProcessBuilder(commands);
        //PUT HERE YOUR OWN PATH
        process.directory(new File(PathConst.TASK_PATH +name));
        try {
            isRun = process.start();
        } catch (IOException e) {
            System.out.println("Error: Process hasn`t been started");
        }
    }

    public String getTestData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(isRun.getInputStream()));
        String line;
        StringBuilder data = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            data.append(line);
        }
        return data.toString();
    }

    public void setTestData(String data) {
        OutputStream out = isRun.getOutputStream();
        PrintStream printStream = new PrintStream(out);
        printStream.println(data);
        printStream.flush();
    }
}