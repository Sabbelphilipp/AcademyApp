package ch.fhnw.academy.dataprovider;

import ch.fhnw.academy.model.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Reto Giger on 24.02.2015.
 */
public class movieDataProvider{

    //-------Reading Data from CSV File
    public ArrayList<movie> readData() {
        String strLine;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            reader.readLine(); // Jump Header
            while ( (strLine = reader.readLine()) != null )
            {
                String[] items = strLine.split(";");
                List<String> directors = new ArrayList<String>();
                List<String> mainActors = new ArrayList<String>();
                String[] tmp = items[3].split(", ");
                for (String s : tmp) {
                    directors.add(s.toString());
                }
                tmp = items[4].split(", ");
                for (String add : tmp) {
                    mainActors.add(add);
                }
                try{
                    movie m1 = new movie(Integer.parseInt(items[0]),items[1],Integer.parseInt(items[2]),directors, mainActors,items[5],Integer.parseInt(items[6]),items[7],Integer.parseInt(items[8]),Integer.parseInt(items[9]),items[10], formatter.parse(items[11]), Integer.parseInt(items[12]));
                    allMovies.add(m1);
                }catch (ParseException e){
                    e.printStackTrace();
                }
            }
            }catch(FileNotFoundException e){
                System.out.println("File not Found");
            }catch(IOException e) {
                System.out.println("IO Exception");
            }
        return allMovies;
    }

    //--------Writing Data to CSV File
    public void writeData(ArrayList<movie> al){
        FileWriter fw = null;
        try{
            fw = new FileWriter(path);
            fw.append(FILE_HEADER.toString());
            fw.append(NEW_LINE_SEPERATOR);
            for(int x = 0; x<al.size(); x++){
                fw.append(al.get(x).createCSVLine());
                //fw.append(COMMA_DELIMITER);
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e){
            System.out.println("IO Exception");
        } finally {
            try{
                fw.flush();
                fw.close();
            } catch (IOException e){
                System.out.println("IO Exception when flushing / closing Filewriter");
            }
        }
    }

    //----- Attributes -----
    private static final String COMMA_DELIMITER = ";";
    private static final String NEW_LINE_SEPERATOR = "\n";

    private ArrayList<movie> allMovies = new ArrayList<movie>();
    final String path = "movies.csv";
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    // ----- CSV file header -----
    private static final String FILE_HEADER = "#id;Title;yearOfAward;director;mainActor;titleEnglish;yearOfProduction;country;duration;fsk;genre;startDate;Oscars;";



}
