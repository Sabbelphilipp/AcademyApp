package ch.fhnw.academy.businesslogic;
import ch.fhnw.academy.model.MovieList;
import ch.fhnw.academy.model.movie;
import ch.fhnw.academy.dataprovider.movieDataProvider;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Reto Giger on 24.02.2015.
 * The movieController contains all the important methods called by the GUI
 */
public class movieController {
    public movieDataProvider mDP = new movieDataProvider();
    private  MovieList allMovies;

    public movieController(MovieList movieList){
        this.allMovies = movieList;
    }

    public void saveData(ArrayList<movie> al){
        mDP.writeData(al);
    }

    // -------------ADDING MOVIES ----------
    public void addMovie(){
        movie newMovie = new movie();
        newMovie.setId(allMovies.getAllMovies().size());
        allMovies.addMovie(newMovie);
        allMovies.setSelectedMovie(newMovie);
    }
    // ------------REMOVING MOVIES------------
    public void removeMovie(){
        allMovies.removeMovie();
        setSelectedMovie(0);
    }

    public void setSelectedMovie(int index){
        if (index != -1){
            allMovies.setSelectedMovie(allMovies.getAllMovies().get(index));
        } else{
            allMovies.setSelectedMovie(null);
        }
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
        //JOptionPane.showMessageDialog(null,input);
        //TODO: Levenshtein Distance search
        simpleSearch(input);
    }

    public void simpleSearch(String input){
        movie result = null;
        for (movie m: allMovies.getAllMovies()){
            if (m.getTitle().toLowerCase().contains(input.toLowerCase())){
                result = m;
                break;
            }
        }
        allMovies.setSelectedMovie(result);
    }

    public void playMovie(String yearOfMovie){
        try {
            System.out.println(yearOfMovie);
            File trailerPath = new File("lib/trailers/"+yearOfMovie+".mp4");
            Desktop.getDesktop().open(trailerPath);
        } catch (Exception e){
            System.out.printf("Problem with playing the Trailer");
        }

    }

    //********Checking values and setting new values************
    public void setNewTitle(String title){
        if(!allMovies.getSelectedMovie().getTitle().equals(title)){
            allMovies.setTitleOfSelectedMovie(title);
        }
    }
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
