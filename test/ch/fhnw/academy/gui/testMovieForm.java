package ch.fhnw.academy.gui;
import ch.fhnw.academy.businesslogic.movieLogic;
import ch.fhnw.academy.dataprovider.movieDataProvider;
import ch.fhnw.academy.model.MovieList;
import ch.fhnw.academy.model.MovieTableModel;
import ch.fhnw.academy.model.movie;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Reto Giger on 24.02.2015.
 */
public class testMovieForm extends JFrame {
    public testMovieForm(){
        initializeComponent();
        //movieLogic ml = new movieLogic();

    }

    // Initilisieren der GUI Elemente
    public void initializeComponent(){
        this.setLayout(new BorderLayout());
        this.setTitle("Academy");




        leftPanel = new JPanel();
        rightPanel = new JPanel();
        topPanel = new JPanel();

        // ----- Top Panel -----
        this.add(layoutTopPanel(),BorderLayout.NORTH);

        // ----- Left Panel -----
        this.add(leftPanel, BorderLayout.WEST);
        leftPanel.add(LoadTableData());
        leftPanel.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new BevelBorder(BevelBorder.LOWERED)));

        // ----- Right Panel -----
        this.add(layoutRightPanel(), BorderLayout.EAST);
        rightPanel.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new BevelBorder(BevelBorder.LOWERED)));


    }
    // ------ Layout top Panel -----
    private JPanel layoutTopPanel(){
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.LINE_AXIS));
        topPanel.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new BevelBorder(BevelBorder.LOWERED)));

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel = new JPanel();

        saveButton = new JButton("SAVE");
        addButton = new JButton("ADD");
        removeButton = new JButton("REMOVE");
        undoButton = new JButton("Undo");
        redoButton = new JButton("Redo");
        searchTextField = new JTextField("Suchen...", TEXT_FIELD_DIMENSION);

        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(addButton);
        toolBar.add(removeButton);
        toolBar.addSeparator();
        toolBar.add(undoButton);
        toolBar.add(redoButton);
        buttonPanel.add(toolBar);

        searchPanel.add(searchTextField);

        topPanel.add(buttonPanel);
        topPanel.add(searchPanel);
//        addRemovePanel = new JPanel();
//        undoRedoPanel = new JPanel();
//        searchPanel = new JPanel();

//

//        addRemovePanel.add(addButton);
//        addRemovePanel.add(removeButton);
//        undoRedoPanel.add(undoButton);
//        undoRedoPanel.add(redoButton);
//        searchPanel.add(searchTextField);

//        topPanel.add(saveButton);
//        topPanel.add(addRemovePanel);
//        topPanel.add(undoRedoPanel);
//        topPanel.add(searchPanel);

        return topPanel;
    }

    //------- Right Side --------
    private JPanel layoutRightPanel(){
        rightPanel.setLayout(new MigLayout(""));

        return rightPanel;
    }

    // Laden der Jtable
    public Component LoadTableData()
    {
        mdp = new movieDataProvider();
        ArrayList<movie> al = new ArrayList<movie>(mdp.readData());
        MovieList ml = new MovieList();
        TableModel model = new MovieTableModel(ml);
        jt = new JTable(model);
        TableColumn col = jt.getColumnModel().getColumn(0);
        col.setMaxWidth(40);        //max Spaltenbreite von 40 px
        col.setResizable(false);
        return new JScrollPane(jt);
    }




    //*********ATTRIBUTES*******
    private JFrame mainframe;

    private JTable jt;
    private movieDataProvider mdp;
    private JDialog menueDialog;
    private JMenuBar menueBar;
    private JToolBar toolBar;

    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel topPanel;
    private JPanel buttonPanel;
    private JPanel searchPanel;

    private JButton saveButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton undoButton;
    private JButton redoButton;

    private JLabel lblYear;
    private JLabel lblTitle;
    private JLabel lblRegisseur;
    private JLabel lblMainActor;
    private JLabel lblTitleInEnglish;
    private JLabel lblGenre;
    private JLabel lblCountry;


    private JTextField searchTextField;


    private final int TEXT_FIELD_DIMENSION = 20;
}
