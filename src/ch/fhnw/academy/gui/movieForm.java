package ch.fhnw.academy.gui;
import ch.fhnw.academy.businesslogic.movieLogic;
import ch.fhnw.academy.dataprovider.movieDataProvider;
import ch.fhnw.academy.model.*;
import net.miginfocom.swing.MigLayout;
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
import java.io.File;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import java.text.ParseException;

/**
 * Created by Reto Giger on 24.02.2015.
 */
public class movieForm extends JFrame {
    public movieForm(){
//        try {
//            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
//        } catch (UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

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
        ml = new movieLogic(movieList);

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
        lblMainActor = new JLabel("*ACTORS*");
        panelOscars = new JPanel();
        lblDirector = new JLabel("*REGISSEUR*");
        lblTitle = new JLabel("*TITEL*");
        lblYear = new JLabel("*JAHR*");


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
                ml.saveData(movieList.getAllMovies());
                System.out.println("Saved File");
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ml.addMovie();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                //int dialogResult = JOptionPane.showConfirmDialog(null, "'" + blabla.getSelectedPlayer().getName() + "' entfernen?", "Warnung!", dialogButton);
//                if(dialogResult == 0){
//                    ml.removeMovie();
//                }
            }
        });
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ml.undo();
                //ml.setNewOscars(4);
            }
        });
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ml.redo();
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
                ml.searchMovie(searchTextField.getText());
            }
        });
    }

    public void addEvents(){
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel selectionModel = (ListSelectionModel) e.getSource();
                boolean isAdjusting = e.getValueIsAdjusting();
                if (!isAdjusting) {
                    int rowIndexView = selectionModel.getMinSelectionIndex();
                    int rowIndexModel;
                    if (rowIndexView != 1) {
                        rowIndexModel = jt.convertRowIndexToModel(rowIndexView);
                    } else {
                        rowIndexModel = 1;
                    }
                    movieList.setSelectedMovie(movieList.getAllMovies().get(rowIndexModel));
                }
            }
        });
        {
            jtfTitle.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ml.setNewTitle(jtfTitle.getText());
                }
            });
        }
        {
            jtfYear.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ml.setNewYear(Integer.parseInt(jtfYear.getText()));
                }
            });
        }
        {
            jtfDirector.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ml.setNewDirector(jtfDirector.getText());
                }
            });
        }
        {
            jtfMainActor.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ml.setNewMainActors(jtfMainActor.getText());
                }
            });
        }
        {
            spinnerOscars.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    ml.setNewOscars(Integer.parseInt(spinnerOscars.getValue().toString()));
                }
            });
        }
        {
            jtfStartDate.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    ml.setNewStartDate(jtfStartDate.getText());
                }
            });
        }
        {
            jtfTitleInEnglish.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ml.setNewTitleEnglish(jtfTitleInEnglish.getText());
                }
            });
        }
        {
            jtfGenre.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ml.setNewGenre(jtfGenre.getText());
                }
            });
        }
        {
            jtfCountry.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ml.setNewCountry(jtfCountry.getText());
                }
            });
        }
        {
            jtfFsk.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ml.setNewFSK(Integer.parseInt(jtfFsk.getText()));
                }
            });
        }
        {
            jtfYearOfProduction.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ml.setNewYearOfProduction(Integer.parseInt(jtfYearOfProduction.getText()));
                }
            });
        }
        {
            jtfDuration.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    ml.setNewDuration(Integer.parseInt(jtfDuration.getText()));
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
                    jtfTitle.setText(m.getTitle());
                    lblTitle.setText(m.getTitle());
                    lblImage.setIcon(ml.setImage(m.getId()));
                    lblYear.setText(Integer.toString(m.getYear()));
                    jtfYear.setText(Integer.toString(m.getYear()));
                    lblDirector.setText(m.getDirectorsAsString());
                    jtfDirector.setText(m.getDirectorsAsString());
                    lblMainActor.setText(m.getMainActorsAsString());
                    jtfMainActor.setText(m.getMainActorsAsString());
                    panelOscars.removeAll();
                    System.out.println("Oscars updatet: " + m.getOscars());
                    //panelOscars.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new BevelBorder(BevelBorder.LOWERED)));
                    panelOscars.add(ml.setOscars(m.getOscars()));
                    jtfTitleInEnglish.setText(m.getTitleEng());
                    jtfGenre.setText(m.getGenre());
                    jtfCountry.setText(m.getCountry());
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

        //----- Format Table -----
        TableColumn col0 = jt.getColumnModel().getColumn(0);
        col0.setWidth(25);
        col0.setMaxWidth(40);        //max Spaltenbreite von 40 px
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
        saveButton.setIcon(ml.buildImage(new File("lib/icons/Save.png"),25,25));
        saveButton.setPressedIcon(ml.buildImage(new File("lib/icons/Save_Pressed.png"),25,25));
        //saveButton.setToolTipText("DAS IST EIN TEST");

        // ----- ADD Button -----
        addButton = new JButton();
        addButton.setIcon(ml.buildImage(new File("lib/icons/Plus.png"),25,25));
        addButton.setPressedIcon(ml.buildImage(new File("lib/icons/Plus_Pressed.png"),25,25));

        // ----- REMOVE Button -----
        removeButton = new JButton();
        removeButton.setIcon(ml.buildImage(new File("lib/icons/Minus.png"),25,25));
        removeButton.setPressedIcon(ml.buildImage(new File("lib/icons/Minus_Pressed.png"),25,25));

        // ----- UNDO Button -----
        undoButton = new JButton();
        undoButton.setIcon(ml.buildImage(new File("lib/icons/Undo.png"),25,25));
        undoButton.setPressedIcon(ml.buildImage(new File("lib/icons/Undo_Pressed.png"),25,25));

        // ----- REDO Button -----
        redoButton = new JButton();
        redoButton.setIcon(ml.buildImage(new File("lib/icons/Redo.png"),25,25));
        redoButton.setPressedIcon(ml.buildImage(new File("lib/icons/Redo_Pressed.png"),25,25));

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
        //To do: Upper Right layout
        upperRightPanel.setLayout(new MigLayout("wrap 5", "[10:30:35][grow, fill][][][160:160:160]",""));
        upperRightPanel.setPreferredSize(RightPanelDimension);
        upperRightPanel.setMaximumSize(RightPanelDimension);
        upperRightPanel.setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new BevelBorder(BevelBorder.LOWERED)));
        // Load default Image and Flag
        lblImage.setIcon(ml.buildImage(new File("lib/poster/no_poster.gif"),160,230));
        lblCountry.setIcon(ml.setFlag("us"));

        upperRightPanel.add(lblYear, "span 3");
        upperRightPanel.add(lblCountry);
        upperRightPanel.add(lblImage, "span 1 5");
        upperRightPanel.add(lblTitle, "span 4");
        upperRightPanel.add(new JLabel("von:"));
        upperRightPanel.add(lblDirector, "span 3");
        upperRightPanel.add(new JLabel("mit"));
        upperRightPanel.add(lblMainActor, "span 3");
        upperRightPanel.add(panelOscars, "span 4");

        return upperRightPanel;
    }


    //*********ATTRIBUTES*******
    private JFrame mainframe;

    private JTable jt;
    private movieDataProvider mdp;
    private movieLogic ml;
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
