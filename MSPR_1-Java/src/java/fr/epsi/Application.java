package fr.epsi;

import java.io.*;
import java.util.ArrayList;

import static fr.epsi.http.httpGetStaff.httpGetStaff;

public class Application {

    public static void main(String[] args) throws IOException {
        String file_name = "staff.txt";
        ArrayList<String> list = httpGetStaff(file_name);
        System.out.println(list);
        String new_index_html = read_index_html(list);
        System.out.println("index" + new_index_html);
        write_in_new_index_html(new_index_html);
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        user(list.get(finalI));
                    } catch (IOException e) {
                        try {
                            user_error(list.get(finalI));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    private static void user_error(String user) throws IOException {
        BufferedReader rd = new BufferedReader(new FileReader("C:/Users/valgm/OneDrive/Bureau/MSPR_1-Java/src/html/errorAgent.html"));
        String line;
        String error_html = "";
        while ((line = rd.readLine()) != null) {
            error_html += line;
        }
        rd.close();
        System.out.println("ERROR" + error_html);

        File file = new File("C:/Users/valgm/OneDrive/Bureau/MSPR_1-Java/src/html/new" + user + ".html");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(error_html);
        bw.close();
    }

    private static void user(String user) throws IOException {
        String file_user = user + ".txt";
        ArrayList<String> user_staff = httpGetStaff(file_user);
        System.out.println(user_staff);

        BufferedReader rd = new BufferedReader(new FileReader("C:/Users/valgm/OneDrive/Bureau/MSPR_1-Java/src/html/fiche.html"));
        String line;
        String index_html = "";
        while ((line = rd.readLine()) != null) {
            index_html += line;
        }
        rd.close();
        String row_name = "<h1>var_nom</h1>";
        String fiche_name = "";
        String row = row_name;
        row = row.replace("var_nom", user_staff.get(0) + "\t" + user_staff.get(1));
        fiche_name = fiche_name + row;

        String row_mission = "<div>var_mission</div>";
        String fiche_mission = "";
        String row_m = row_mission;
        row_m = row_m.replace("var_mission", user_staff.get(2));
        fiche_mission = fiche_mission + row_m;

        String row_password = "<div>var_password</div>";
        String fiche_password = "";
        String row_p = row_password;
        row_p = row_p.replace("var_password", user_staff.get(3));
        fiche_password = fiche_password + row_p;

        String row_materiel = "<div>var_materiel</div>";
        String fiche_materiel = "";
        String row_mat = row_materiel;
        row_mat = row_mat.replace("var_materiel", "<br>" + user_staff.get(4));
        fiche_materiel = fiche_materiel + row_mat;

        String new_fiche_html = index_html.
                replace("var_mission", fiche_mission).
                replace("var_nom", fiche_name).
                replace("var_password", fiche_password).
                replace("var_materiel", fiche_materiel).
                replace("var_image", user + ".jpg");

        File file = new File("C:/Users/valgm/OneDrive/Bureau/MSPR_1-Java/src/html/new/" + user + ".html");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(new_fiche_html);
        bw.close();
    }

    private static String read_index_html(ArrayList<String> list) throws IOException {
        BufferedReader rd = new BufferedReader(new FileReader("C:/Users/valgm/OneDrive/Bureau/MSPR_1-Java/src/html/index.html"));
        String line;
        String index_html = "";
        while ((line = rd.readLine()) != null) {
            index_html += line;
        }
        rd.close();
        String row_template = "<tr><td>var_name</td><td><a href=var_link>var_name</a></td></tr>";
        String tableau = "";
        for (int i = 0; i < list.size(); i++) {
            String row = row_template;
            row = row.replace("var_name", list.get(i));
            row = row.replace("var_link", list.get(i) + ".html");
            tableau = tableau + row;
        }
        String new_index_html = index_html.replace("var_tableau", tableau);
        return new_index_html;
    }

    private static void write_in_new_index_html(String new_index_html) throws IOException {
        File file = new File("C:/Users/valgm/OneDrive/Bureau/MSPR_1-Java/src/html/index.html");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(new_index_html);
        bw.close();
    }
}
