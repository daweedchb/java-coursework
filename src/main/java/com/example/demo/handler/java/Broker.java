package com.example.demo.handler.java;

import java.io.IOException;
import java.util.ArrayList;

public class Broker {
    private final DatabaseWorker db;
    private ConsoleWorker cw;
    private String studentID;

    public Broker(String studentID) throws IOException {
        db = new DatabaseWorker();
        this.studentID = studentID;
    }

    public boolean runTest(String numberTask, long solutionID) throws IOException {
        String name = "solutionId_" + String.valueOf(solutionID) + "d";
        ArrayList<String> listOutput = db.getOutputData(numberTask);
        ArrayList<String> listInput = db.getInputData(numberTask);
        Boolean check = true;
        for (int i = 0; i < listOutput.size(); i++) {
            cw = new ConsoleWorker(name);
            cw.setTestData(listInput.get(i));
            String a = listOutput.get(i);
            String b = cw.getTestData();
            if (a.equals(b)) {
            } else {
                check = false;
                break;
            }
        }
        return check;
    }
}
