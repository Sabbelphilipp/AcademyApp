package ch.fhnw.academy.main;
import ch.fhnw.academy.gui.movieForm;
import ch.fhnw.academy.dataprovider.movieDataProvider;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Reto Giger on 23.02.2015.
 */
public class academyapp {
    public static void main(String[] args) {
        movieForm mF = new movieForm();
        mF.setLocation(50,50);
        mF.setSize(1000, 620);
        // Size has to be fixed like this -> Image Size
        mF.setMinimumSize(new Dimension(1000,620));
        mF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mF.setVisible(true);
    }

}
