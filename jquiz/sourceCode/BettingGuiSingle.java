import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


class BettingGuiSingle extends SinglePlayerMode {

    private JFrame bettingGuiSingleFrame;
    private JPanel BettingGuiSingleMainPanel;
    private JPanel GameGuiLeftPanelb;

    private JButton button1b;
    private JButton button4b;
    private JButton button3b;
    private JButton button2b;
    private JButton button250;
    private JButton button500;
    private JButton button750;
    private JButton button1000;

    private JLabel GameGuiLeftLabelb;
    private JLabel scoreLabelb;
    private JLabel gameGuiModeLabelb;
    private JLabel categoryLabelb;
    private JPanel ImagePanelb;
    private JLabel imageLabelb;
    private JPanel QuestionPanelb;
    private JTextArea textArea;

    private int questionCounter;
    private String betText;
    private int betChoice;
    private String currentQuestionAnswer;
    private boolean betActive;


    BettingGuiSingle() {


        $$$setupUI$$$();
        setFrame();
        setButtonInputActionMaps();
        setBetButtons();
        setButtonActionListeners();
        setLanguage();
        swapBasicButtonsToBetButtons(true); //η μονη διαφορα με τους κατασκευαστες τη παιχνιδιου ΣωστηςΑπαντησης
        //ειναι πως εχουμε μεσω αυτης της μεθοδου εναλαγη των κουμπιων για να πονταρει ο χρηστης

        scoreString = String.valueOf(score);
        questionCounter = 1;
        currentQuestionAnswer = getRandomQuestionAnswer();
        setTextLabel(scoreString, betText + getQuestionCategory(), "@" + getQuestionCategory());
        //και πως πρωτα εμφανιζουμε μια ερωτηση σε σχεση με το ποσο θελει να πονταρει ο χρηστης

        bettingGuiSingleFrame.setContentPane(BettingGuiSingleMainPanel);
        bettingGuiSingleFrame.setVisible(true);


    }

    private void setFrame() {                                  //καλειται απο κατασκευαστη για να φτιαξει το παραθυρο
        bettingGuiSingleFrame = new JFrame("APORTHITO");
        bettingGuiSingleFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        bettingGuiSingleFrame.setSize(1300, 600);
        bettingGuiSingleFrame.setLocationRelativeTo(null);
    }

    private void setLanguage() {      //καλειται στον κατασκευαστη για να σεταρει στη σωστη γλωσσα
        switch (Language.languageChoice) {
            case "greek": {
                GameGuiLeftLabelb.setText("ΣΚΟΡ :");
                gameGuiModeLabelb.setText(" ΓΥΡΟΣ ΠΟΝΤΑΡΙΣΜΑΤΟΣ");
                betText = "ΠΟΣΟΥΣ ΠΟΝΤΟΥΣ ΘΑ ΘΕΛΑΤΕ ΝΑ ΣΤΟΙΧΗΜΑΤΗΣΕΤΕ ΣΤΗΝ ΕΠΟΜΕΝΗ ΕΡΩΤΗΣΗ?\n ΚΑΤΗΓΟΡΙΑ ΕΡΩΤΗΣΗΣ : ";
                break;
            }
            case "english": {
                GameGuiLeftLabelb.setText("SCORE :");
                gameGuiModeLabelb.setText(" BETTING MODE");
                betText = "HOW MANY POINTS WOULD YOU LIKE TO BET FOR THE NEXT QUESTION?\n QUESTION CATEGORY : ";
                break;
            }
        }
    }

    private void setTextLabel(String score, String questionAnswerText, String category) {
        scoreLabelb.setText(score);      //καλειται στον κατασκευαστη αλλα και οποτε θελουμε να εμφανισουμε καινουρια ερωτηση
        textArea.setText(questionAnswerText);       //ΠΡΟΣΟΧΗ την πρωτη φορα παντα στο δευτερο ορισμα δινουμε την ερωτηση σε σχεση με το πονταρισμα
        categoryLabelb.setText(category);
    }

    private void setBetButtons() {   //Φτιαχνουμε τα κουμπια για το πονταρισμα

        button250 = new JButton();
        button250.setBackground(new Color(-15332589));
        button250.setFont(new Font("Corbel", Font.PLAIN, 20));
        button250.setForeground(new Color(-16551924));
        button250.setText("250");
        GameGuiLeftPanelb.add(button250, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        button500 = new JButton();
        button500.setBackground(new Color(-15332589));
        button500.setFont(new Font("Corbel", Font.PLAIN, 20));
        button500.setForeground(new Color(-16551924));
        button500.setText("500");
        GameGuiLeftPanelb.add(button500, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        button750 = new JButton();
        button750.setBackground(new Color(-15332589));
        button750.setFont(new Font("Corbel", Font.PLAIN, 20));
        button750.setForeground(new Color(-16551924));
        button750.setText("750");
        GameGuiLeftPanelb.add(button750, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        button1000 = new JButton();
        button1000.setBackground(new Color(-15332589));
        button1000.setFont(new Font("Corbel", Font.PLAIN, 20));
        button1000.setForeground(new Color(-16551924));
        button1000.setText("1000");
        GameGuiLeftPanelb.add(button1000, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    private void swapBasicButtonsToBetButtons(boolean bet) { //Μεθοδος που εναλασσει τα κουμπια πονταρισματος με τα κουμπια απαντησης
        button1b.setVisible(!bet);
        button2b.setVisible(!bet);
        button3b.setVisible(!bet);
        button4b.setVisible(!bet);
        button250.setVisible(bet);
        button500.setVisible(bet);
        button750.setVisible(bet);
        button1000.setVisible(bet);
    }

    private void questionLooper(String choice) { //η μεθοδος που οπως ειπαμε υπαρχει σε ολα τα modes (βλ.RightAnswerGuiSingle)
        if (answerChecker(choice)) {
            score += betChoice;    //αν ειναι σωστη η λαθος η απαντηση μας την οποια τη δεχεται σαν ορισμα
        } else {                    //αυηξανει ή μειωνει το σκορ αναλογα με το ποσα πονταραμε
            score -= betChoice;
        }

        if (questionCounter < 5) {               //Αν εχουμε περασει τις 5 ερωτησεις κανει οτι και η αντιστοιχη μεθοδος
            //της RightAnswerGuiSingle
            scoreString = String.valueOf(score);

            questionCounter++;
        } else {

            bettingGuiSingleFrame.dispose();
            questionCounter = 0;
            randomSingleModePicker();

        }
    }


    private void setButtonInputActionMaps() {     //συγχρονιζουμε τα κουμπια με τα <1,2,3,4> του πληκτρολογιου
        button1b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), "pressed");
        button1b.getActionMap().put("pressed", button1Action);
        button2b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"), "pressed");
        button2b.getActionMap().put("pressed", button2Action);
        button3b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"), "pressed");
        button3b.getActionMap().put("pressed", button3Action);
        button4b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("4"), "pressed");
        button4b.getActionMap().put("pressed", button4Action);
    }

    private void setButtonActionListeners() { //φτιαχνουμε listeners για τα κουμπια μας
        button1b.addActionListener(button1Action);
        button2b.addActionListener(button2Action);
        button3b.addActionListener(button3Action);
        button4b.addActionListener(button4Action);
        button250.addActionListener(button250Action);
        button500.addActionListener(button500Action);
        button750.addActionListener(button750Action);
        button1000.addActionListener(button1000Action);
    }


    private Action button1Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("PATHSES TO KOUMPI 1");      //οταν επιλεγει κανεις την απαντηση του
            questionLooper("a");                   //καλειται ο questionLooper με ορισμα την απαντηση
            swapBasicButtonsToBetButtons(true);             //αλλαζουν τα κουμπια με αυτα του πονταρισματος
            currentQuestionAnswer = getRandomQuestionAnswer();
            setTextLabel(scoreString, betText + getQuestionCategory(), "@" + getQuestionCategory());//εμφανιζεις την ερωτηση για το ποσα θελει να πονταρει
            imageLabelb.setIcon(null);                                                                                  //ο χρηστης μαζι με πληροφοριες για κατηγορια και σκορ


        }
    };
    private Action button2Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {   //το ιδιο συμβαινει με καθε κουμπι απαντησης

            System.out.println("PATHSES TO KOUMPI 2");
            questionLooper("b");
            swapBasicButtonsToBetButtons(true);
            currentQuestionAnswer = getRandomQuestionAnswer();
            setTextLabel(scoreString, betText + getQuestionCategory(), "@" + getQuestionCategory());
            imageLabelb.setIcon(null);

        }
    };
    private Action button3Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("PATHSES TO KOUMPI 3");
            questionLooper("c");
            swapBasicButtonsToBetButtons(true);
            currentQuestionAnswer = getRandomQuestionAnswer();
            setTextLabel(scoreString, betText + getQuestionCategory(), "@" + getQuestionCategory());
            imageLabelb.setIcon(null);

        }
    };
    private Action button4Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("PATHSES TO KOUMPI 4");
            questionLooper("d");
            swapBasicButtonsToBetButtons(true);
            currentQuestionAnswer = getRandomQuestionAnswer();
            setTextLabel(scoreString, betText + getQuestionCategory(), "@" + getQuestionCategory());
            imageLabelb.setIcon(null);

        }
    };

    private Action button250Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {    //Οταν επιλεγει ο χρηστης το κουμπι με το ποσο που θελει να πονταρει

            swapBasicButtonsToBetButtons(false);   //αλλαζουν τα κουμπια
            betChoice = 250;                            //αποθηκευεται το ποσο που επελεξε σε μια μεταβλητη
            setTextLabel(scoreString, currentQuestionAnswer, "@" + getQuestionCategory()); //εμφανιζεται ερωτηση μαζι με κατηγορια και σκορ
            imageLabelb.setIcon(getImageIfExists());    ///εμφανιζεται εικονα
        }
    };

    private Action button500Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {  //το ιδιο συμβαινει με ολα τα κουμπια πονταρισματος

            swapBasicButtonsToBetButtons(false);
            betChoice = 500;
            setTextLabel(scoreString, currentQuestionAnswer, "@" + getQuestionCategory());
            imageLabelb.setIcon(getImageIfExists());
        }
    };

    private Action button750Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            swapBasicButtonsToBetButtons(false);
            betChoice = 750;
            setTextLabel(scoreString, currentQuestionAnswer, "@" + getQuestionCategory());
            imageLabelb.setIcon(getImageIfExists());
        }
    };

    private Action button1000Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            swapBasicButtonsToBetButtons(false);
            betChoice = 1000;
            setTextLabel(scoreString, currentQuestionAnswer, "@" + getQuestionCategory());
            imageLabelb.setIcon(getImageIfExists());

        }
    };


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        BettingGuiSingleMainPanel = new JPanel();
        BettingGuiSingleMainPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        BettingGuiSingleMainPanel.setBackground(new Color(-10328710));
        BettingGuiSingleMainPanel.setEnabled(true);
        GameGuiLeftPanelb = new JPanel();
        GameGuiLeftPanelb.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        GameGuiLeftPanelb.setBackground(new Color(-10328710));
        BettingGuiSingleMainPanel.add(GameGuiLeftPanelb, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button1b = new JButton();
        button1b.setBackground(new Color(-15332589));
        button1b.setFont(new Font("Corbel", Font.BOLD, 26));
        button1b.setForeground(new Color(-16551924));
        button1b.setText("1");
        GameGuiLeftPanelb.add(button1b, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button4b = new JButton();
        button4b.setBackground(new Color(-15332589));
        button4b.setFont(new Font("Corbel", Font.BOLD, 26));
        button4b.setForeground(new Color(-16551924));
        button4b.setText("4");
        GameGuiLeftPanelb.add(button4b, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button3b = new JButton();
        button3b.setBackground(new Color(-15332589));
        button3b.setFont(new Font("Corbel", Font.BOLD, 26));
        button3b.setForeground(new Color(-16551924));
        button3b.setText("3");
        GameGuiLeftPanelb.add(button3b, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button2b = new JButton();
        button2b.setBackground(new Color(-15332589));
        button2b.setFont(new Font("Corbel", Font.BOLD, 26));
        button2b.setForeground(new Color(-16551924));
        button2b.setText("2");
        GameGuiLeftPanelb.add(button2b, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GameGuiLeftLabelb = new JLabel();
        GameGuiLeftLabelb.setEnabled(true);
        GameGuiLeftLabelb.setFont(new Font("Corbel", Font.BOLD, 18));
        GameGuiLeftLabelb.setForeground(new Color(-15332589));
        GameGuiLeftLabelb.setText("Player 1     SCORE :");
        GameGuiLeftPanelb.add(GameGuiLeftLabelb, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scoreLabelb = new JLabel();
        scoreLabelb.setFont(new Font("Corbel", Font.BOLD, 28));
        scoreLabelb.setForeground(new Color(-3551925));
        scoreLabelb.setText("15000");
        GameGuiLeftPanelb.add(scoreLabelb, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gameGuiModeLabelb = new JLabel();
        gameGuiModeLabelb.setFont(new Font("Corbel", Font.BOLD, 18));
        gameGuiModeLabelb.setForeground(new Color(-15332589));
        gameGuiModeLabelb.setText("Label");
        GameGuiLeftPanelb.add(gameGuiModeLabelb, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        categoryLabelb = new JLabel();
        categoryLabelb.setFont(new Font("Corbel", Font.BOLD, 18));
        categoryLabelb.setForeground(new Color(-15332589));
        categoryLabelb.setText("Label");
        GameGuiLeftPanelb.add(categoryLabelb, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ImagePanelb = new JPanel();
        ImagePanelb.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        ImagePanelb.setBackground(new Color(-15332589));
        BettingGuiSingleMainPanel.add(ImagePanelb, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        imageLabelb = new JLabel();
        imageLabelb.setEnabled(true);
        imageLabelb.setForeground(new Color(-15));
        imageLabelb.setText("");
        ImagePanelb.add(imageLabelb, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        QuestionPanelb = new JPanel();
        QuestionPanelb.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        QuestionPanelb.setBackground(new Color(-6250346));
        QuestionPanelb.setEnabled(true);
        QuestionPanelb.setForeground(new Color(-6250346));
        BettingGuiSingleMainPanel.add(QuestionPanelb, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(-1, 150), null, null, 0, false));
        textArea = new JTextArea();
        textArea.setAlignmentY(0.5f);
        textArea.setBackground(new Color(-15332589));
        textArea.setEditable(false);
        textArea.setFont(new Font("Corbel", Font.PLAIN, 22));
        textArea.setForeground(new Color(-16551924));
        textArea.setMargin(new Insets(0, 0, 0, 0));
        textArea.setTabSize(8);
        textArea.setText("");
        QuestionPanelb.add(textArea, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return BettingGuiSingleMainPanel;
    }
}
