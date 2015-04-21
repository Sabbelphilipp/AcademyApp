package ch.fhnw.academy.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by Reto Giger on 01.03.2015.
 */
public class MovieTableModel extends AbstractTableModel {

    private MovieList movieList;
    private String[] columns;

    public MovieTableModel(final MovieList movieList){
        super();
        this.movieList = movieList;
        movieList.addObserver(new Observer() {
            @Override
            public void update(Observable model) {
                int toUpdate = movieList.getSelectedIndex();
                fireTableRowsUpdated(toUpdate,toUpdate);
            }

            @Override
            public void addNewRow(int index) {
                fireTableRowsInserted(index,index);
            }

            @Override
            public void removeRow(int index) {
                fireTableRowsDeleted(index,index);
            }
        });
        //columns = new String[]{"ID","Title","yearOfAward","director","mainActor","titleEnglish","yearOfProduction","country","duration","fsk","genre","startDate"};
        columns = new String[]{"Jahr","Titel","Regisseur","Hauptdarsteller"};
    }

    @Override
    public int getRowCount() {
        return movieList.getAllMovies().size();
    }

    @Override
    public int getColumnCount() {
        //return 12;
        return 4;
    }

    public String getColumnName(int col){
        return columns[col];
    }

    @Override
    public Object getValueAt(int row, int column) {
        movie m = movieList.getAllMovies().get(row);
        switch (column){
            case 0: return m.getYear();
            case 1: return m.getTitle();
            case 2: return m.getDirector();
            case 3: return m.getMainActors();
            default: return null;
//            case 0: return m.getId();
//            case 1: return m.getTitle();
//            case 2: return m.getYear();
//            case 3: return m.getDirector();
//            case 4: return m.getMainActors();
//            case 5: return m.getTitleEng();
//            case 6: return m.getYearOfProduction();
//            case 7: return m.getCountry();
//            case 8: return m.getDuration();
//            case 9: return m.getFsk();
//            case 10: return m.getGenre();
//            case 11: return m.getStartDate();
//            default: return null;
        }
    }
}
