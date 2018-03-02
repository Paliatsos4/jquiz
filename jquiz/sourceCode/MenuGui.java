import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;


class MenuGui {

    private JFrame menuGuiFrame;
    private JFrame helpFRAME;
    private JFrame highscoresFRAME;
    private JPanel menuGuiPanel;
    private JPanel helpPanel;
    private JPanel highscoresPanel;
    private JButton exitButton;
    private JButton helpButton;
    private JButton startButton;
    private JButton highScoresButton;
    private JLabel highScoresLabelSingle;
    private JLabel highScoresLabelMulti;
    private JLabel helpLabel;


    MenuGui() {


        $$$setupUI$$$();

        helpLabel = new JLabel();


        menuGuiFrame = new JFrame("APORTHITO");
        menuGuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         //Φτιαχνουμε το frame
        menuGuiFrame.setSize(800, 600);
        menuGuiFrame.setLocationRelativeTo(null);
        menuGuiFrame.setVisible(true);
        menuGuiFrame.setContentPane(menuGuiPanel);

        setStartButtonAction();
        setHelpButtonAction();                                       //Καλουμε μεθοδους για τα κουμπια μας
        setHighScoresButtonAction();
        setExitButtonAction();

    }

    public void setButtonsLanguage(String exit, String help, String start, String helpTEXT, String highscores) {
        exitButton.setText(exit);
        startButton.setText(start);
        helpButton.setText(help);                            //Η συναρτηση που κληθηκε απο τη Language με ορισματα ειτε στα αγγλικα ειτε στα ελληνικα
        helpLabel.setText(helpTEXT);                          //για να δωσει κειμενο στα κουμπια
        highScoresButton.setText(highscores);
    }

    private void setStartButtonAction() {

        startButton.addActionListener(ae -> {

            switch (Language.languageChoice) {
                case "greek":                                                    //Οταν επιλεγει το κουμπι εκκινησης
                    menuGuiFrame.setVisible(false);                              //εξαφανιζουμε το φρεημ
                    Language.setSingleMultiGR();                                 //σεταρουμε το επομενο μενου στα ελληνικα εφοσον εχουν επιλεγει
                                                                                 // και το καλουμε να τρεξει απο την κλαση Language



                    break;
                case "english":                                            //το ιδιο για αγγλικα
                    menuGuiFrame.setVisible(false);
                    Language.setSingleMultiEN();

                    break;
            }

        });
    }

    private void setHelpButtonAction() {

        helpButton.addActionListener(ae -> {

            helpFRAME = new JFrame("APORTHITO");
            helpFRAME.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);               //Αν επιλεγει το κουμπι της βοηθειας
            helpFRAME.setSize(500, 300);                            //Δημιουργειται καινουριο παραθυρο με πληροφοριες
            helpFRAME.setLocationRelativeTo(null);


            helpPanel = new JPanel();
            helpPanel.setBackground(new Color(0x222042));
            helpPanel.setEnabled(true);
            helpLabel.setForeground(new Color(0x756526));



            helpPanel.add(helpLabel);
            helpFRAME.setContentPane(helpPanel);
            helpFRAME.setVisible(true);


        });
    }

    private void setHighScoresButtonAction() {

        highScoresButton.addActionListener(ae -> {

            highscoresFRAME = new JFrame("APORTHITO HIGHSCORES");                //Αν επιλεγει το κουμπι των σκορ
            highscoresFRAME.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);           //Δημιουργειται καινουριο Frame
            highscoresFRAME.setSize(600, 200);
            highscoresFRAME.setLocationRelativeTo(null);


            highscoresPanel = new JPanel();
            highscoresPanel.setBackground(new Color(0x222042));
            highscoresPanel.setEnabled(true);



            highScoresLabelSingle = new JLabel();
            highScoresLabelSingle.setForeground(new Color(0x756526));
            highScoresLabelSingle.setFont(highScoresLabelSingle.getFont().deriveFont(30f));
            highScoresLabelSingle.setText("Single: " + SinglePlayerMode.getHighscore() + "      ");   //παιρνουμε τα σκορ απο μεθοδους των κλασεων για
                                                                                                      //το μονο και διπλο παιχνιδι αντιστοιχα
            highScoresLabelMulti = new JLabel();
            highScoresLabelMulti.setForeground(new Color(0x035A0C));
            highScoresLabelMulti.setFont(highScoresLabelMulti.getFont().deriveFont(30f));
            highScoresLabelMulti.setText("Multi: " + MultiPlayerMode.getHighscore());


            highscoresPanel.add(highScoresLabelSingle);
            highscoresPanel.add(highScoresLabelMulti);
            highscoresFRAME.setContentPane(highscoresPanel);
            highscoresFRAME.setVisible(true);


        });
    }


    private void setExitButtonAction() {

        exitButton.addActionListener(ae -> {

            menuGuiFrame.dispatchEvent(new WindowEvent(menuGuiFrame, WindowEvent.WINDOW_CLOSING));  //Αν επιλεγει η εξοδος τερματιζει το παιχνιδι

        });
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        menuGuiPanel = new JPanel();
        menuGuiPanel.setLayout(new GridBagLayout());
        menuGuiPanel.setBackground(new Color(-10328710));
        menuGuiPanel.setEnabled(true);
        menuGuiPanel.setForeground(new Color(-10328710));
        exitButton = new JButton();
        exitButton.setBackground(new Color(-15332589));
        exitButton.setEnabled(true);
        exitButton.setForeground(new Color(-16551924));
        exitButton.setText("Button");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 20, 10, 20);
        menuGuiPanel.add(exitButton, gbc);
        helpButton = new JButton();
        helpButton.setBackground(new Color(-15332589));
        helpButton.setForeground(new Color(-16551924));
        helpButton.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 20, 10, 20);
        menuGuiPanel.add(helpButton, gbc);
        startButton = new JButton();
        startButton.setBackground(new Color(-15332589));
        startButton.setForeground(new Color(-16551924));
        startButton.setText("Start");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 20, 10, 20);
        menuGuiPanel.add(startButton, gbc);
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-15332589));
        label1.setEnabled(true);
        label1.setFont(new Font("Corbel", Font.PLAIN, 48));
        label1.setForeground(new Color(-15332589));
        label1.setText("APORTHITO");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        menuGuiPanel.add(label1, gbc);
        highScoresButton = new JButton();
        highScoresButton.setBackground(new Color(-15332589));
        highScoresButton.setForeground(new Color(-16551924));
        highScoresButton.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 20, 10, 20);
        menuGuiPanel.add(highScoresButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return menuGuiPanel;
    }
}
