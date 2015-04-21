package ch.fhnw.academy.model;

import ch.fhnw.academy.dataprovider.movieDataProvider;

import java.util.*;

/**
 * Created by Reto Giger on 06.03.2015.
 * Includes all the functions for Observing the Changes on the MovieForm
 *
 * To do: addPlayer(), removePLayer(), undo und redo, notifyadded, notifyremoved
 */
public class MovieList implements Observable {

    public MovieList(){
        this.allMovies = mdp.readData();
        //undos and redos
    }

    //returns the state of the List
    // *WORKS*
    public ArrayList<movie> getAllMovies(){
        return allMovies;
    }

    // returns the Row of the active Item
    // *WORKS*
    public int getSelectedIndex(){
        return allMovies.indexOf(selectedMovie);
    }

    // returns a specified movie
    // *WORKS*
    public movie getMovie(int index){
        return allMovies.get(index);
    }


    // ----- Override the Methods from the Interface -----
    @Override
    public void addObserver(ch.fhnw.academy.model.Observer observer) {
        allObservers.add(observer);
    }

    @Override
    public void removeObserver(ch.fhnw.academy.model.Observer observer) {
        allObservers.remove(observer);
    }

    // Notifies all Observers
    // *WORKS*
    public void notifyObserver(){
        for(Observer observer:allObservers){
            observer.update(this);
        }
    }

    public void notifyAdding(){

    }

    public void notifyRemoving(){

    }

    // ----- Setting new Values -----
    public void setTitleOfSelectedMovie(String title){
        this.selectedMovie.setTitle(title);
        notifyObserver();
    }

    public void setYearOfSelectedMovie(Integer year){
        this.selectedMovie.setYear(year);
        notifyObserver();
    }

    public void setDirectorOfSelectedMovie(String director){
        String[] items = director.split(";");
        List<String> directors = new ArrayList<String>();
        for (String add : items) {
            directors.add(add);
        }
        this.selectedMovie.setDirector(directors);
        notifyObserver();
    }

    public void setMainActorsOfSelectedMovie(String mainActors){
        String[] items = mainActors.split(";");
        List<String> list = new ArrayList<String>();
        for (String add : items) {
            list.add(add);
        }
        this.selectedMovie.setMainActors(list);
        notifyObserver();
    }

    public void setOscarsOfSelectedMovie(Integer number){
        this.selectedMovie.setOscars(number);
        notifyObserver();
    }

    // Methods without Observers

    public void setTitleEnglishOfSelectedMovie(String titleeng){
        this.selectedMovie.setTitleEng(titleeng);
    }
    public void setGenreOfSelecteMovie(String genre){
        this.selectedMovie.setGenre(genre);
    }
    public void setCountryOfSelectedMovie(String country){
        this.selectedMovie.setCountry(country);
    }
    public void setFSKOfSelectedMovie(Integer FSK){ this.selectedMovie.setFsk(FSK);}
    public void setYearOfProductionOfSelectedMovie(int year){this.selectedMovie.setYearOfProduction(year);}
    public void setDurationOfSelectedMovie(Integer duration){
        this.selectedMovie.setDuration(duration);
    }
    public void setStartDateOfSelectedMovie(Date date){this.selectedMovie.setStartDate(date);}


    // ----- Getter & Setter -----
    public movie getSelectedMovie(){
        return selectedMovie;
    }

    public void setSelectedMovie(movie selectedMovie){
        if(this.selectedMovie == selectedMovie){
            return ;
        }
        this.selectedMovie = selectedMovie;
        notifyObserver();
    }

    public void setFirstEntry(movie firstEntry) {
        this.firstEntry = firstEntry;
        notifyObserver();
    }

    public movie getFirstEntry() {
        return firstEntry;
    }

    // ----- ATTRIBUTES -----
    private ArrayList<movie> allMovies = new ArrayList<movie>();
    private final Set<Observer> allObservers = new HashSet<>();
    private movie selectedMovie;
    private movie firstEntry;
    movieDataProvider mdp = new movieDataProvider();

}
