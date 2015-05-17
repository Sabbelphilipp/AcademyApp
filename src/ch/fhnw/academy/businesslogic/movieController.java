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
        UndoHandler undoHandler = allMovies.getLastUndo();
        allMovies.addRedo(new RedoHandler(undoHandler.getMovie()));
        allMovies.setSelectedMovie(undoHandler.getMovie());
        allMovies.setMainActorsOfSelectedMovie(undoHandler.getMainActorsAsString());
        allMovies.setDirectorOfSelectedMovie(undoHandler.getDirectorsAsString());
        allMovies.setTitleOfSelectedMovie(undoHandler.getTitle());
        allMovies.setCountryOfSelectedMovie(undoHandler.getCountry());
        allMovies.setDurationOfSelectedMovie(undoHandler.getDuration());
        allMovies.setFSKOfSelectedMovie(undoHandler.getFsk());
        allMovies.setTitleEnglishOfSelectedMovie(undoHandler.getTitleEng());
        allMovies.setGenreOfSelecteMovie(undoHandler.getGenre());
        allMovies.setYearOfSelectedMovie(undoHandler.getYear());
        allMovies.setOscarsOfSelectedMovie(undoHandler.getOscars());
        allMovies.setYearOfProductionOfSelectedMovie(undoHandler.getYearOfProduction());
        allMovies.setStartDateOfSelectedMovie(undoHandler.getStartDate());
    }

    public void redo(){
        RedoHandler redoHandler = allMovies.getLastRedo();
        allMovies.addUndo(new UndoHandler(redoHandler.getMovie()));
        allMovies.setSelectedMovie(redoHandler.getMovie());
        allMovies.setMainActorsOfSelectedMovie(redoHandler.getMainActorsAsString());
        allMovies.setDirectorOfSelectedMovie(redoHandler.getDirectorsAsString());
        allMovies.setTitleOfSelectedMovie(redoHandler.getTitle());
        allMovies.setCountryOfSelectedMovie(redoHandler.getCountry());
        allMovies.setDurationOfSelectedMovie(redoHandler.getDuration());
        allMovies.setFSKOfSelectedMovie(redoHandler.getFsk());
        allMovies.setTitleEnglishOfSelectedMovie(redoHandler.getTitleEng());
        allMovies.setGenreOfSelecteMovie(redoHandler.getGenre());
        allMovies.setYearOfSelectedMovie(redoHandler.getYear());
        allMovies.setOscarsOfSelectedMovie(redoHandler.getOscars());
        allMovies.setYearOfProductionOfSelectedMovie(redoHandler.getYearOfProduction());
        allMovies.setStartDateOfSelectedMovie(redoHandler.getStartDate());
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
            allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
            allMovies.clearRedo();
            allMovies.setTitleOfSelectedMovie(title);
        }
    }
    public void setNewYear(Integer year){
        if(allMovies.getSelectedMovie().getYear()!=year){
            allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
            allMovies.clearRedo();
            allMovies.setYearOfSelectedMovie(year);
        }
    }
    public void setNewDirector(String director){
        if (!allMovies.getSelectedMovie().getDirectorsAsString().equals(director)){
            allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
            allMovies.clearRedo();
            allMovies.setDirectorOfSelectedMovie(director);
        }
    }
    public void setNewMainActors(String actors){
        if (!allMovies.getSelectedMovie().getMainActorsAsString().equals(actors)){
            allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
            allMovies.clearRedo();
            allMovies.setMainActorsOfSelectedMovie(actors);
        }
    }
    public void setNewOscars(Integer number){
        if (allMovies.getSelectedMovie().getOscars() != number) {
            allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
            allMovies.clearRedo();
            allMovies.setOscarsOfSelectedMovie(number);
        }

    }
    public void setNewTitleEnglish(String titleEnglish){
        allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
        allMovies.clearRedo();
        allMovies.setTitleEnglishOfSelectedMovie(titleEnglish);
    }
    public void setNewGenre(String genre){
        allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
        allMovies.clearRedo();
        allMovies.setGenreOfSelecteMovie(genre);
    }
    public void setNewCountry(String country){
        allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
        allMovies.clearRedo();
        allMovies.setCountryOfSelectedMovie(country);
    }
    public void setNewFSK(Integer FSK){
        allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
        allMovies.clearRedo();
        allMovies.setFSKOfSelectedMovie(FSK);
    }
    public void setNewYearOfProduction(Integer yearOfProduction){
        allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
        allMovies.clearRedo();
        allMovies.setYearOfProductionOfSelectedMovie(yearOfProduction);
    }
    public void setNewDuration(Integer duration){
        allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
        allMovies.clearRedo();
        allMovies.setDurationOfSelectedMovie(duration);
    }
    public void setNewStartDate(String Date){
        try{
            allMovies.addUndo(new UndoHandler(allMovies.getSelectedMovie()));
            allMovies.clearRedo();
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            allMovies.setStartDateOfSelectedMovie(formatter.parse(Date));
        } catch (ParseException e){
            System.out.println("Parse Error when setting new Date");
        }

    }
}
