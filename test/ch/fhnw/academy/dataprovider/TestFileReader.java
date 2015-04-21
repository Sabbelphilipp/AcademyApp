package ch.fhnw.academy.dataprovider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Reto Giger on 28.02.2015.
 */
public class TestFileReader {

    //-------Reading Data from CSV File
    public ArrayList<ArrayList> readData(String fileName) {
        ArrayList<ArrayList> list = new ArrayList<ArrayList>();
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader data = new BufferedReader(file);
            while ((zeile = data.readLine()) != null) {
                split = zeile.split(";");
                for (int i = 0; i < split.length; i++) {
                    if (!(split[i].equals(""))) {
                        //list.add(split[i].trim());
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("File not Found");
        }catch(IOException e){
            System.out.println("IO Exception");

        }
        return list;

    }

    //----- Attributes -----
    private String zeile;
    private String[] split = null;
    private ArrayList list = new ArrayList();
}
