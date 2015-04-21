package ch.fhnw.academy.main;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.academy.gui.movieForm;
import ch.fhnw.academy.gui.testMovieForm;
import ch.fhnw.academy.model.*;
import ch.fhnw.academy.dataprovider.movieDataProvider;

import javax.swing.*;

/**
 * Created by Reto Giger on 28.02.2015.
 */
public class testAcademyApp {
    public static void main(String[] args) {

        testMovieForm tmf = new testMovieForm();
        tmf.setLocation(50,50);
        tmf.setSize(1000,600);
        tmf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tmf.setVisible(true);
    }

}
