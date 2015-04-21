package ch.fhnw.academy.businesslogic;
import ch.fhnw.academy.gui.movieForm;
import ch.fhnw.academy.model.MovieList;
import ch.fhnw.academy.model.movie;
import ch.fhnw.academy.dataprovider.movieDataProvider;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Reto Giger on 24.02.2015.
 */
public class movieLogic {
    public movieDataProvider mDP = new movieDataProvider();
    private  MovieList allMovies;

    public movieLogic(MovieList movieList){
        this.allMovies = movieList;
    }

    // ************************** IMAGE SHIZZLE ********************************
    //Sets the Image to a specified index number
    public ImageIcon setImage(int index){
        return buildImage(new File("lib/poster/"+index+".jpg"),160,230 );
    }

    // Resizes the Image to a specified size
    public ImageIcon buildImage(File path,int Height, int Width){
        BufferedImage img = null;
        try {
            img = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(Height,Width,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        return imageIcon;
    }

    public ImageIcon setFlag(String countryCode){
        final int height = 16;
        final int width = 16;
        ImageIcon finalIcon = null;
        StringBuilder sb = new StringBuilder();
        sb.append("lib/flags/16/");
        sb.append(countryCode);
        sb.append(".png");
        File f = new File(sb.toString());
        if (f.exists()) {
                 finalIcon = buildImage(f, height, width);
        } else {
            System.out.println("FLAG NOT FOUND");
        }
        return finalIcon;
    }

    public JPanel setOscars(int value){
        JPanel Oscars = new JPanel();
        for (int x = 0; x<value; x++){
            JLabel lbl1 = new JLabel();
            lbl1.setIcon(buildImage(new File("lib/Oscar/oscar.jpg"),20,60));
            Oscars.add(lbl1);
        }
        return Oscars;
    }

    // ************************** Rest ******************************************

    public void saveData(ArrayList<movie> al){
        mDP.writeData(al);
    }

    public void addMovie(){
        JOptionPane.showMessageDialog(null,"Adding Movie");
        //TO DO: Adding a new Movie
    }

    public void removeMovie(){
        JOptionPane.showMessageDialog(null,"Remove Movie");
        //TO DO: Remove a Movie
    }

    public void undo(){
        JOptionPane.showMessageDialog(null,"Undo last Step");
        // TO DO: Undo last step
    }

    public void redo(){
        JOptionPane.showMessageDialog(null,"Redo last Step");
        //TO DO: Redo last step
    }

    public void searchMovie(String input){
        JOptionPane.showMessageDialog(null,input);
    }

    // ----- Methods for Updating Fields -----
    public void setNewTitle(String title){
        if(!allMovies.getSelectedMovie().getTitle().equals(title)){
            allMovies.setTitleOfSelectedMovie(title);
        }
    }

    //********Checking values and setting new values************
    public void setNewYear(Integer year){
        if(year>3000||year<1830){
            // TO DO: INPUT NOT VALID
        } else{
            // TO DO: INPUT IS VALID
            if(allMovies.getSelectedMovie().getYear()!=year){
                allMovies.setYearOfSelectedMovie(year);
            }
        }
    }

    public void setNewDirector(String director){
        if (!allMovies.getSelectedMovie().getDirectorsAsString().equals(director)){
            allMovies.setDirectorOfSelectedMovie(director);
        }
    }

    public void setNewMainActors(String actors){
        if (!allMovies.getSelectedMovie().getMainActorsAsString().equals(actors)){
            allMovies.setMainActorsOfSelectedMovie(actors);
        }
    }

    public void setNewOscars(Integer number){
        if(number>=0 && number< 10){
            if (allMovies.getSelectedMovie().getOscars() != number) {
                allMovies.setOscarsOfSelectedMovie(number);
            }
        }
    }

    public void setNewTitleEnglish(String titleEnglish){
        //To Do: Check if valid input
        allMovies.setTitleEnglishOfSelectedMovie(titleEnglish);
    }

    public void setNewGenre(String genre){
        //To Do: Check if valid input
        allMovies.setGenreOfSelecteMovie(genre);
    }

    public void setNewCountry(String country){
        //To Do: Check if valid input
        allMovies.setCountryOfSelectedMovie(country);
    }

    public void setNewFSK(Integer FSK){
        //To Do: Check if valid input
        allMovies.setFSKOfSelectedMovie(FSK);
    }

    public void setNewYearOfProduction(Integer yearOfProduction){
        allMovies.setYearOfProductionOfSelectedMovie(yearOfProduction);
    }

    public void setNewDuration(Integer duration){
        allMovies.setDurationOfSelectedMovie(duration);
    }

    public void setNewStartDate(String Date){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            allMovies.setStartDateOfSelectedMovie(formatter.parse(Date));
        } catch (ParseException e){
            System.out.println("Parse Error when setting new Date");
        }

    }

}
