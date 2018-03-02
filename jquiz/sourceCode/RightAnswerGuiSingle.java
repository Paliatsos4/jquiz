import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by vatomouro1 on 2/1/2017.
 */
class RightAnswerGuiSingle extends SinglePlayerMode {

    private JFrame rightAnswerGuiSingleFrame;
    private JPanel GameGuiLeftPanel;
    private JButton button1;
    private JButton button4;
    private JButton button3;
    private JButton button2;
    private JLabel GameGuiLeftLabel;
    private JPanel ImagePanel;
    private JPanel QuestionPanel;
    private JTextArea QuestionAnswersText;
    private JLabel scoreLabel;
    private JPanel RightAnswerGuiSingleMainPanel;
    private JLabel gameGuiModeLabel;
    private JLabel imageLabel;
    private JLabel categoryLabel;

    private int questionCounter;


    RightAnswerGuiSingle() {
                                                        //Στον κατσκευαστη της κλασσης για τον γυρο σωστης απαντησης
                                                        // δημιουργουμε το γραφικο περιβαλλον
        questionCounter = 1;                          // αρχικοποιουμε τον μετρητη των ερωτησεων
        rightAnswerGuiSingleFrame = new JFrame("APORTHITO");
        scoreString = String.valueOf(score);            //ενημερωνουμε το σκορ απο τους προηγουμενους γυρους

        $$$setupUI$$$();
        setLanguage();
        setButtonInputActionMaps();
        setButtonActionListeners();


        rightAnswerGuiSingleFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rightAnswerGuiSingleFrame.setSize(1300, 600);
        rightAnswerGuiSingleFrame.setLocationRelativeTo(null);

        setTextLabel(scoreString, getRandomQuestionAnswer(), getImageIfExists(), "@" + getQuestionCategory());
        //καλουνται εδω οπως και καθε φορα που ξεκιναει καινουρια ερωτηση σε ολα τα modes οι getRandomQuestionAnswer, getImageIfExists,
        //getQuestionCategory απο της κλασης QuestionAnswer την οποια κληρωνομουν ολες οι κλασεις που εχουν να κανουν με τα modes
        //την κληρωνουν ως δευτερη γενια μεσω των κλασεων μονου και διπλου παιχνιδου.

        rightAnswerGuiSingleFrame.setVisible(true);
        rightAnswerGuiSingleFrame.setContentPane(RightAnswerGuiSingleMainPanel);




    }

    private void setLanguage() {    //μεθοδος που καλειται απο τον κατασκευαστη για να χρησιμοποιησει τη σωστη γλωσσα
        switch (Language.languageChoice) {
            case "greek": {
                GameGuiLeftLabel.setText("ΠΑΙΚΤΗΣ 1   ΣΚΟΡ :");
                gameGuiModeLabel.setText("ΓΥΡΟΣ ΣΩΣΤΗΣ ΑΠΑΝΤΗΣΗΣ");
                break;
            }
            case "english": {
                GameGuiLeftLabel.setText("PLAYER 1   SCORE :");
                gameGuiModeLabel.setText("RIGHT ANSWER MODE");
                break;
            }
        }
    }

    private void setTextLabel(String score, String questionAnswerText, ImageIcon icon, String category) {
        scoreLabel.setText(score);                    //επισης μεθοδος που καλειται απο τον κατασκευαστη
        QuestionAnswersText.setText(questionAnswerText); //για να δωσει κειμενο ερωτησης,κατηγοριας,σκορ
        imageLabel.setIcon(icon);                       //και να εμφανισει εικονα,αν υπαρχει
        categoryLabel.setText(category);
    }


    private void questionLooper(String choice) {  //Για καθε τυπο παιχνιδιου υπαρχει ενας ξεχωριστος questionlooper
                                                 // που καλειται μετα την επιλογη της απαντησης με ορισμα την απαντηση που επελεγη
        if (answerChecker(choice)) {
            score += 1000;          //αν ειναι σωστη η απαντηση αυξανει το σκορ για 1000
        }

        if (questionCounter < 5) {           //αν δεν εχουμε παιξει ακομα 5 ερωτησεις(που αναλογουν σε καθε mode)
            scoreString = String.valueOf(score);     //εμφανιζει τις νεες πληροφοριες σε σχεση με σκορ, κατηγορια νεας ερωτηση και πιθανη εικονα
            setTextLabel(scoreString, getRandomQuestionAnswer(), getImageIfExists(), "@" + getQuestionCategory());
            questionCounter++;                  //αυξανει τον μετρητη ερωτησεων
        } else {
            rightAnswerGuiSingleFrame.dispose();   //Αν εχουμε παιξει 5 ερωτησει
                                                    //μηδενιζει τον μετρητη και καλη τη μεθοδο
            questionCounter = 0;                    //τυχαιας επιλογης mode
            randomSingleModePicker();

        }
    }

    private void setButtonInputActionMaps() {   //συγχρονιζουμε τα κουμπια με τα <1,2,3,4> του πληκτρολογιου
        button1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), "pressed");
        button1.getActionMap().put("pressed", button1Action);
        button2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"), "pressed");
        button2.getActionMap().put("pressed", button2Action);
        button3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"), "pressed");
        button3.getActionMap().put("pressed", button3Action);
        button4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("4"), "pressed");
        button4.getActionMap().put("pressed", button4Action);
    }

    private void setButtonActionListeners() { //βαζουμε listeners για καθε κουμπι
        button1.addActionListener(button1Action);
        button2.addActionListener(button2Action);
        button3.addActionListener(button3Action);
        button4.addActionListener(button4Action);
    }


    private Action button1Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("PATHSES TO KOUMPI 1");
            questionLooper("a");                     //οταν πατιεται ενα κοουμπι καλειται ο questionLooper για την επιλογη μας
        }
    };
    private Action button2Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("PATHSES TO KOUMPI 2");
            questionLooper("b");
        }
    };
    private Action button3Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("PATHSES TO KOUMPI 3");
            questionLooper("c");
        }
    };
    private Action button4Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("PATHSES TO KOUMPI 4");
            questionLooper("d");
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
        RightAnswerGuiSingleMainPanel = new JPanel();
        RightAnswerGuiSingleMainPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        RightAnswerGuiSingleMainPanel.setBackground(new Color(-10328710));
        GameGuiLeftPanel = new JPanel();
        GameGuiLeftPanel.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        GameGuiLeftPanel.setBackground(new Color(-10328710));
        RightAnswerGuiSingleMainPanel.add(GameGuiLeftPanel, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        button1 = new JButton();
        button1.setBackground(new Color(-15332589));
        button1.setFont(new Font("Corbel", Font.BOLD, 26));
        button1.setForeground(new Color(-16551924));
        button1.setText("1");
        GameGuiLeftPanel.add(button1, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button4 = new JButton();
        button4.setBackground(new Color(-15332589));
        button4.setFont(new Font("Corbel", Font.BOLD, 26));
        button4.setForeground(new Color(-16551924));
        button4.setText("4");
        GameGuiLeftPanel.add(button4, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button3 = new JButton();
        button3.setBackground(new Color(-15332589));
        button3.setFont(new Font("Corbel", Font.BOLD, 26));
        button3.setForeground(new Color(-16551924));
        button3.setText("3");
        GameGuiLeftPanel.add(button3, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button2 = new JButton();
        button2.setBackground(new Color(-15332589));
        button2.setFont(new Font("Corbel", Font.BOLD, 26));
        button2.setForeground(new Color(-16551924));
        button2.setText("2");
        GameGuiLeftPanel.add(button2, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GameGuiLeftLabel = new JLabel();
        GameGuiLeftLabel.setEnabled(true);
        GameGuiLeftLabel.setFont(new Font("Corbel", Font.BOLD, 18));
        GameGuiLeftLabel.setForeground(new Color(-15332589));
        GameGuiLeftLabel.setText("Player 1     SCORE :");
        GameGuiLeftPanel.add(GameGuiLeftLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Corbel", Font.BOLD, 28));
        scoreLabel.setForeground(new Color(-3551925));
        scoreLabel.setText("15000");
        GameGuiLeftPanel.add(scoreLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gameGuiModeLabel = new JLabel();
        gameGuiModeLabel.setFont(new Font("Corbel", Font.BOLD, 18));
        gameGuiModeLabel.setForeground(new Color(-15332589));
        gameGuiModeLabel.setText("Label");
        GameGuiLeftPanel.add(gameGuiModeLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        categoryLabel = new JLabel();
        categoryLabel.setFont(new Font("Corbel", Font.BOLD, 18));
        categoryLabel.setForeground(new Color(-15332589));
        categoryLabel.setText("Label");
        GameGuiLeftPanel.add(categoryLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ImagePanel = new JPanel();
        ImagePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        ImagePanel.setBackground(new Color(-15332589));
        RightAnswerGuiSingleMainPanel.add(ImagePanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        imageLabel = new JLabel();
        imageLabel.setEnabled(true);
        imageLabel.setForeground(new Color(-15));
        imageLabel.setText("");
        ImagePanel.add(imageLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        QuestionPanel = new JPanel();
        QuestionPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        RightAnswerGuiSingleMainPanel.add(QuestionPanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        QuestionAnswersText = new JTextArea();
        QuestionAnswersText.setBackground(new Color(-15332589));
        QuestionAnswersText.setEditable(false);
        QuestionAnswersText.setFont(new Font("Corbel", Font.PLAIN, 22));
        QuestionAnswersText.setForeground(new Color(-16551924));
        QuestionAnswersText.setText("");
        QuestionPanel.add(QuestionAnswersText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return RightAnswerGuiSingleMainPanel;
    }
}
