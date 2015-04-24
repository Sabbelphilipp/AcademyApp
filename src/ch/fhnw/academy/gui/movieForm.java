package ch.fhnw.academy.gui;
import ch.fhnw.academy.businesslogic.movieController;
import ch.fhnw.academy.dataprovider.movieDataProvider;
import ch.fhnw.academy.model.*;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Reto Giger on 24.02.2015.
 */
public class movieForm extends JFrame {
    public movieForm(){
       /* try {
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        initializeComponent();
        addingActionListeners();
        addObservers();
        addEvents();
    }

    // Initilisieren der GUI Elemente
    public void initializeComponent(){
        this.setLayout(new BorderLayout());
        this.setTitle("Academy");

        // ----- Logics -----
        movieList = new MovieList();
        movieController = new movieController(movieList);

        // ----- Panels -----
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        upperRightPanel = new JPanel();
        underRightPanel = new JPanel();
        topPanel = new JPanel();

        //----- Textfield & Labels -----
        jtfTitle = new JTextField("");
        jtfDirector = new JTextField("");
        jtfMainActor = new JTextField("");
        jtfTitleInEnglish = new JTextField("");
        jtfGenre = new JTextField("");
        jtfCountry = new JTextField("");
        jtfFsk = new JTextField("");
        spinnerOscars = new JSpinner();
        jtfYearOfProduction = new JTextField("");
        jtfYear = new JTextField("");
        jtfDuration = new JTextField("");
        jtfStartDate = new JTextField("");
        lblCountry = new JLabel();
        lblImage = new JLabel();
        lblMainActor = new JLabel("");
        panelOscars = new JPanel();
        lblDirector = new JLabel("");
        lblTitle = new JLabel("");
        lblYear = new JLabel("");


        // ----- Top Panel -----
        this.add(layoutTopPanel(),BorderLayout.NORTH);


        // ----- Left Panel -----
        this.add(leftPanel, BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(500,600));
        leftPanel.add(layoutLeftPanel());
        leftPanel.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new BevelBorder(BevelBorder.LOWERED)));

        // ----- Right Panel -----
        this.add(layoutRightPanel(), BorderLayout.EAST);
        rightPanel.setSize(new Dimension(300,600));

    }

    public void addingActionListeners(){
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieController.saveData(movieList.getAllMovies());
                System.out.println("Saved File");
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieController.addMovie();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "'" + movieList.getSelectedMovie().getTitle() + "' entfernen?", "Warnung!", dialogButton);
                if(dialogResult == 0){
                    movieController.removeMovie();
                }
            }
        });
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieController.undo();
            }
        });
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieController.redo();
            }
        });
        searchTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(searchTextField.getText().equals("Suchen...")){
                    searchTextField.setText("");
                }
            }
        });
        searchTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(searchTextField.getText().equals("")){
                    searchTextField.setText("Suchen...");
                }
            }
        });
        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieController.searchMovie(searchTextField.getText());
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movieController.playMovie(jtfYear.getText());
            }
        });
    }

    public void addEvents(){
        // If a Movie gets selected, the selection Model gets set and the selected Movie gets set.
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel selectionModel = (ListSelectionModel) e.getSource();
                boolean isAdjusting = e.getValueIsAdjusting();
                if (!isAdjusting) {
                    int rowIndexView = selectionModel.getMinSelectionIndex();
                    //System.out.println(rowIndexView);
                    int rowIndexModel;
                    if(rowIndexView!=-1){
                        rowIndexModel = rowIndexView;
                    } else  {
                        rowIndexModel = 0;
                    }
                    movieController.setSelectedMovie(rowIndexModel);

                }
            }
        });
        {
            jtfTitle.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    movieController.setNewTitle(jtfTitle.getText());
                }
            });
        }
        {
            jtfYear.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    movieController.setNewYear(Integer.parseInt(jtfYear.getText()));
                }
            });
        }
        {
            jtfDirector.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    movieController.setNewDirector(jtfDirector.getText());
                }
            });
        }
        {
            jtfMainActor.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    movieController.setNewMainActors(jtfMainActor.getText());
                }
            });
        }
        {
            spinnerOscars.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    movieController.setNewOscars(Integer.parseInt(spinnerOscars.getValue().toString()));
                }
            });
        }
        {
            jtfCountry.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    movieController.setNewCountry(jtfCountry.getText());
                }
            });
        }
        {
            jtfStartDate.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    movieController.setNewStartDate(jtfStartDate.getText());
                }
            });
        }
        {
            jtfTitleInEnglish.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    movieController.setNewTitleEnglish(jtfTitleInEnglish.getText());
                }
            });
        }
        {
            jtfGenre.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    movieController.setNewGenre(jtfGenre.getText());
                }
            });
        }
        {
            jtfFsk.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    movieController.setNewFSK(Integer.parseInt(jtfFsk.getText()));
                }
            });
        }
        {
            jtfYearOfProduction.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    movieController.setNewYearOfProduction(Integer.parseInt(jtfYearOfProduction.getText()));
                }
            });
        }
        {
            jtfDuration.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    movieController.setNewDuration(Integer.parseInt(jtfDuration.getText()));
                }
            });
        }
    }

    public void addObservers(){
        movieList.addObserver(new Observer() {
            @Override
            public void update(Observable model) {
                movie m = movieList.getSelectedMovie();
                if (m!=null){
                    //Setting the focus when a movie gets added or deleted
                    jt.changeSelection(m.getId(),1,false,false);
                    jtfTitle.setText(m.getTitle());
                    lblTitle.setText(m.getTitle());
                    lblImage.setIcon(setImage(m.getId()));
                    lblYear.setText(Integer.toString(m.getYear()));
                    jtfYear.setText(Integer.toString(m.getYear()));
                    lblDirector.setText(m.getDirectorsAsString());
                    jtfDirector.setText(m.getDirectorsAsString());
                    lblMainActor.setText(m.getMainActorsAsString());
                    jtfMainActor.setText(m.getMainActorsAsString());
                    panelOscars.removeAll();
                    panelOscars.add(setOscars(m.getOscars()));
                    panelOscars.revalidate();
                    panelOscars.repaint();
                    jtfTitleInEnglish.setText(m.getTitleEng());
                    jtfGenre.setText(m.getGenre());
                    jtfCountry.setText(m.getCountry());
                    lblCountry.setIcon(setFlag(m.getCountry()));
                    jtfFsk.setText(Integer.toString(m.getFsk()));
                    spinnerOscars.setValue((m.getOscars()));
                    jtfYearOfProduction.setText(Integer.toString(m.getYearOfProduction()));
                    jtfDuration.setText(Integer.toString(m.getDuration()));
                    jtfStartDate.setText(m.getStartDateAsString());
                }
            }
            @Override
            public void addNewRow(int index) {
                //nothing to do here
            }
            @Override
            public void removeRow(int index) {
                //nothing to do here
            }
        });
    }

    // ----- Layout left Panel
    public Component layoutLeftPanel()
    {
        TableModel model = new MovieTableModel(movieList);
        jt = new JTable(model);
        styleTable();
        return new JScrollPane(jt);
    }
    // ------ Layout top Panel -----
    private JPanel layoutTopPanel(){
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.LINE_AXIS));
        topPanel.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new BevelBorder(BevelBorder.LOWERED)));

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // ----- SAVE Button -----
        saveButton = new JButton();
        saveButton.setIcon(buildImage(new File("lib/icons/Save.png"),25,25));
        saveButton.setPressedIcon(buildImage(new File("lib/icons/Save_Pressed.png"),25,25));
        //saveButton.setToolTipText("DAS IST EIN TEST");

        // ----- ADD Button -----
        addButton = new JButton();
        addButton.setIcon(buildImage(new File("lib/icons/Plus.png"),25,25));
        addButton.setPressedIcon(buildImage(new File("lib/icons/Plus_Pressed.png"),25,25));

        // ----- REMOVE Button -----
        removeButton = new JButton();
        removeButton.setIcon(buildImage(new File("lib/icons/Minus.png"),25,25));
        removeButton.setPressedIcon(buildImage(new File("lib/icons/Minus_Pressed.png"),25,25));

        // ----- UNDO Button -----
        undoButton = new JButton();
        undoButton.setIcon(buildImage(new File("lib/icons/Undo.png"),25,25));
        undoButton.setPressedIcon(buildImage(new File("lib/icons/Undo_Pressed.png"),25,25));

        // ----- REDO Button -----
        redoButton = new JButton();
        redoButton.setIcon(buildImage(new File("lib/icons/Redo.png"),25,25));
        redoButton.setPressedIcon(buildImage(new File("lib/icons/Redo_Pressed.png"),25,25));

        // ----- Search Text Field -----
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

        return topPanel;
    }

    //------- Layout right Panel --------
    private JPanel layoutRightPanel(){
       rightPanel.setLayout(new MigLayout("wrap 1", "[grow,fill]", "[grow,fill]"));
       //rightPanel.setLayout(new GridLayout(2,1));
       rightPanel.add(layoutUpperRightPanel());
       rightPanel.add(layoutLowerRightPanel());
       return rightPanel;
   }

    private JPanel layoutLowerRightPanel(){
        //Specify min, pref and max size when initialize the Layout -> min:preferred:max" (E.g. "10:20:40").
        underRightPanel.setLayout(new MigLayout("wrap 4", "[][grow,fill]10[grow,fill]", ""));
        underRightPanel.setPreferredSize(RightPanelDimension);
        underRightPanel.setMaximumSize(RightPanelDimension);
        underRightPanel.add(new JLabel("Jahr:"));
        underRightPanel.add(jtfYear, "wrap");
        underRightPanel.add(new JLabel("Titel:"));
        underRightPanel.add(jtfTitle, "span 3, ");
        underRightPanel.add(new JLabel("Regisseur:"));
        underRightPanel.add(jtfDirector, "span 3");
        underRightPanel.add(new JLabel("Hauptdarsteller:"));
        underRightPanel.add(jtfMainActor, "span 3");
        underRightPanel.add(new JLabel("Title(eng):"));
        underRightPanel.add(jtfTitleInEnglish, "span 3");
        underRightPanel.add(new JLabel("Genre:"));
        underRightPanel.add(jtfGenre);
        underRightPanel.add(new JLabel("Produktionsjahr:"));
        underRightPanel.add(jtfYearOfProduction);
        underRightPanel.add(new JLabel("Land:"));
        underRightPanel.add(jtfCountry);
        underRightPanel.add(new JLabel("Spieldauer:"));
        underRightPanel.add(jtfDuration);
        underRightPanel.add(new JLabel("FSK ab:"));
        underRightPanel.add(jtfFsk);
        underRightPanel.add(new JLabel("Erscheinungsdatum:"));
        underRightPanel.add(jtfStartDate);
        underRightPanel.add(new JLabel("Oscars insgesamt:"));
        underRightPanel.add(spinnerOscars);

        return underRightPanel;
    }

    private JPanel layoutUpperRightPanel(){
        //*************** MIG LAYOUT **************
        //
        upperRightPanel.setLayout(new MigLayout("wrap 5", "[10:30:35][grow, fill][][][160:160:160]",""));
        upperRightPanel.setPreferredSize(RightPanelDimension);
        upperRightPanel.setMaximumSize(RightPanelDimension);
        upperRightPanel.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new BevelBorder(BevelBorder.LOWERED)));
        // Load default Image and Flag
        lblImage.setIcon(buildImage(new File("lib/poster/no_poster.gif"),160,230));
        lblCountry.setIcon(setFlag("us"));
        playButton = new JButton("Trailer abspielen");
        playButton.setIcon(buildImage(new File("lib/icons/playIonic.png"),25,25));
        playButton.setPressedIcon(buildImage(new File("lib/icons/playIonicpressed.png"), 25, 25));

        upperRightPanel.add(lblYear, "span 3");
        upperRightPanel.add(lblCountry);
        upperRightPanel.add(lblImage, "span 1 6");
        upperRightPanel.add(lblTitle, "span 4");
        upperRightPanel.add(new JLabel("Von:"));
        upperRightPanel.add(lblDirector, "span 3");
        upperRightPanel.add(new JLabel("Mit:"));
        upperRightPanel.add(lblMainActor, "span 3");
        upperRightPanel.add(panelOscars, "span 4");
        upperRightPanel.add(playButton, "span 4");

        return upperRightPanel;
    }

    // ********* Methods to create Images on the GUI *************

    public JPanel setOscars(int value){
        JPanel Oscars = new JPanel();
        for (int x = 0; x<value; x++){
            JLabel lbl1 = new JLabel();
            lbl1.setIcon(buildImage(new File("lib/Oscar/oscar.jpg"),15,40));
            Oscars.add(lbl1);
        }
        return Oscars;
    }

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

    // ----------- CREATES THE FLAG FOR THE COUNTRY-----------------
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

    public ImageIcon setImage(int index){
        File path = new File("lib/poster/"+index+".jpg");
        if (path.exists()){
            return buildImage(new File("lib/poster/"+index+".jpg"),160,230 );
        } else{
            return buildImage(new File("lib/poster/no_poster.gif"),160,230 );
        }
    }

    //Method for styling the table by default values
    public void styleTable(){
        TableColumn col0 = jt.getColumnModel().getColumn(0);
        col0.setPreferredWidth(10);
        TableColumn col1 = jt.getColumnModel().getColumn(1);
        col0.setMinWidth(60);
        TableColumn col2 = jt.getColumnModel().getColumn(2);
        col0.setPreferredWidth(30);
        TableColumn col4 = jt.getColumnModel().getColumn(3);
        col4.setPreferredWidth(20);

    }

    //*********ATTRIBUTES*******
    private JFrame mainframe;

    private JTable jt;
    //private movieDataProvider mdp;
    private movieController movieController;
    private JToolBar toolBar;
    //ArrayList<movie> allMovies;
    private MovieList movieList;

    private JSplitPane jSplitPane;

    private JPanel rightPanel;
    private JPanel underRightPanel;
    private JPanel upperRightPanel;
    private JPanel leftPanel;
    private JPanel topPanel;
    private JPanel buttonPanel;
    private JPanel searchPanel;
    private JPanel panelOscars;

    private JButton saveButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton undoButton;
    private JButton redoButton;
    private JButton playButton;

    // ----- underRightPanel
    private JTextField jtfTitle;
    private JTextField jtfDirector;
    private JTextField jtfMainActor;
    private JTextField jtfTitleInEnglish;
    private JTextField jtfGenre;
    private JTextField jtfCountry;
    private JTextField jtfFsk;
    private JTextField jtfYearOfProduction;
    private JTextField jtfYear;
    private JTextField jtfDuration;
    private JTextField jtfStartDate;
    private JSpinner spinnerOscars;
    // ----- upperRightPanel -----
    private JLabel lblYear;
    private JLabel lblTitle;
    private JLabel lblDirector;
    private JLabel lblMainActor;
    private JLabel lblCountry;
    private JLabel lblImage;


    private JTextField searchTextField;



    private final int TEXT_FIELD_DIMENSION = 20;
    private final Dimension IMAGE_DIMENSION = new Dimension(150, 225);
    private Dimension RightPanelDimension = new Dimension(500,300);
}
