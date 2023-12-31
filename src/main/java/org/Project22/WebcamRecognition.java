package org.Project22;

import org.Project22.GUI.MenuWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WebcamRecognition {

    static MenuWindow menuWindow = new MenuWindow();
    static ArrayList<String> nameList = menuWindow.getName_lists();


    public static void main(String[] args) throws IOException {

        System.out.println(nameList.size());
        String userName = nameList.get(nameList.size()-1);

        String[] source = {"python3", "java/org/Project22/FaceDetection/FaceRec.py", userName};
        Process process = Runtime.getRuntime().exec(source);

        System.out.println(nameList.size());

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output;
        while ((output = reader.readLine()) != null) {
            System.out.println(output);
        }
    }
}

