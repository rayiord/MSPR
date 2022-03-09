package fr.epsi.http;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class httpGetStaff {

    public static ArrayList<String> httpGetStaff(String file_name) {
        try {
            //Get Response
            BufferedReader rd = new BufferedReader(new FileReader("C:/Users/valgm/OneDrive/Bureau/MSPR_1-Java/src/data_client/all_txt/" + file_name));
            String line;
            //Read line by line
            ArrayList<String> list = new ArrayList<>();
            while ((line = rd.readLine()) != null) {
                list.add(line);
            }
            rd.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}