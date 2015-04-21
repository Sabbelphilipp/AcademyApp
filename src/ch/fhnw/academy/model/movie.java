package ch.fhnw.academy.model;

import com.sun.javafx.binding.StringFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Reto Giger on 23.02.2015.
 */
public class movie {
    private int id;
    private  int year;
    private String title;
    private List<String>  director;
    private List<String> mainActors;
    private String titleEng;
    private String genre; //Dropdownmenü?
    private int fsk;
    private int yearOfProduction;
    private int duration;
    private Date startDate;
    private String country;
    private int oscars;

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    // ----- Constructor for Jtable -----
    public movie(int year, String title, List<String> director, List<String> mainActors){
        this.year = year;
        this.title = title;
        this.director = director;
        this.mainActors = mainActors;
    }

    //-------------Constructor light-----
    public movie(int id,String title,int year){
        this.id = id;
        this.year = year;
        this.title = title;
    }

    //--------------Constructor------------------
    public movie(int id, String title,int year,List<String> director,List<String> mainActors, String titleEng,
                 int yearOfProduction, String country, int duration, int fsk, String genre, Date startDate, int oscars){
        this.year = year;
        this.title = title;
        this.titleEng = titleEng;
        this.genre = genre;
        this.fsk = fsk;
        this.yearOfProduction = yearOfProduction;
        this.duration= duration;
        this.startDate = startDate;
        this.director = director;
        this.mainActors = mainActors;
        this.country = country;
        this.id = id;
        this.oscars = oscars;
    }

    //------- To String Methode ------
    @Override
    public String toString(){
        return "Der Film mit der ID "+ id + " und dem Titel "+ title + " aus dem Jahre " + year+"!";
    }

    public String createCSVLine(){

        return this.id+";"+this.title+";"+this.year+";"+this.director+";"+this.mainActors+";"+this.titleEng+";"+this.yearOfProduction+";"+this.country+";"+this.duration+";"+this.fsk+";"+this.genre+";"+formatter.format(this.startDate)+";"+this.oscars+"\r\n";
    }

    public String getMainActorsAsString(){
        StringBuilder sb = new StringBuilder();
        for(int x = 0; x<mainActors.size(); x++){
            sb.append(mainActors.get(x));
            sb.append(", ");
        }
        sb.delete(sb.length()-2,sb.length());
        return sb.toString();
    }

    public String getDirectorsAsString(){
        StringBuilder sb = new StringBuilder();
        for(int x = 0; x<director.size(); x++){
            sb.append(director.get(x));
            sb.append(", ");
        }
        sb.delete(sb.length()-2,sb.length());
        return sb.toString();
    }

    public String getStartDateAsString(){
        return formatter.format(this.startDate);
    }

    //-------------Getter & Setter--------------


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDirector() {
        return director;
    }

    public int getOscars() {
        return oscars;
    }

    public void setOscars(int oscars) {
        this.oscars = oscars;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<String> getMainActors() {
        return mainActors;
    }

    public void setMainActors(List<String> mainActors) {
        this.mainActors = mainActors;
    }

    public String getTitleEng() {
        return titleEng;
    }

    public void setTitleEng(String titleEng) {
        this.titleEng = titleEng;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getFsk() {
        return fsk;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
