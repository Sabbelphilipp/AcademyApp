package ch.fhnw.academy.businesslogic;

import ch.fhnw.academy.model.movie;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Reto Giger on 14.05.2015.
 */
public abstract class UndoRedoHandler {

    public UndoRedoHandler(movie movie){
        this.movie = movie;
        this.id = movie.getId();
        this.year = movie.getYear();
        this.title= movie.getTitle();
        this.director = movie.getDirector();
        this.mainActors = movie.getMainActors();
        this.title = movie.getTitle();
        this.titleEng = movie.getTitleEng();
        this.fsk = movie.getFsk();
        this.yearOfProduction = movie.getYearOfProduction();
        this.duration = movie.getDuration();
        this.startDate = movie.getStartDate();
        this.country = movie.getCountry();
        this.oscars = movie.getOscars();
    }

    public String getMainActorsAsString(){
        StringBuilder sb = new StringBuilder();
        try{
            for(int x = 0; x<mainActors.size(); x++){
                sb.append(mainActors.get(x));
                sb.append(", ");
            }
            sb.delete(sb.length()-2,sb.length());
            return sb.toString();
        }catch (NullPointerException e){
            System.out.println("Null Pointer Exception");
        }
        return sb.toString();
    }

    public String getDirectorsAsString(){
        StringBuilder sb = new StringBuilder();
        try{
            for(int x = 0; x<director.size(); x++){
                sb.append(director.get(x));
                sb.append(", ");
            }
            sb.delete(sb.length()-2,sb.length());
        }catch (NullPointerException e){
            System.out.println("Null Pointer Exception");
        }
        return sb.toString();
    }

    public movie getMovie() {
        return movie;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getDirector() {
        return director;
    }

    public List<String> getMainActors() {
        return mainActors;
    }

    public String getTitleEng() {
        return titleEng;
    }

    public String getGenre() {
        return genre;
    }

    public int getFsk() {
        return fsk;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public int getDuration() {
        return duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getCountry() {
        return country;
    }

    public int getOscars() {
        return oscars;
    }

    //*** Attributes ***
    movie movie;

    private int id;
    private int year;
    private String title;
    private List<String> director;
    private List<String> mainActors;
    private String titleEng;
    private String genre; //Dropdownmenü?
    private int fsk;
    private int yearOfProduction;
    private int duration;
    private Date startDate;
    private String country;
    private int oscars;

}
