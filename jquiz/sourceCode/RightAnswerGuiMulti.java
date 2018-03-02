import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class RightAnswerGuiMulti extends MultiPlayerMode {

    private JFrame rightAnswerGuiMultiFrame;
    private JButton button6;
    private JButton button1;
    private JButton button9;
    private JButton button4;
    private JButton button8;
    private JButton button3;
    private JButton button7;
    private JButton button2;
    private JPanel GameGuiMainPanel;
    private JPanel GameGuiRightPanel;
    private JPanel GameGuiLeftPanel;
    private JLabel GameGuiLeftLabel;
    private JLabel GameGuiRightLabel;
    private JPanel QuestionPanel;
    private JPanel ImagePanel;
    private JTextArea QuestionAnswersText;
    private JLabel scoreLabel1;
    private JLabel scoreLabel2;
    private JLabel gameModeLabel;
    private JLabel categoryLabel;
    private JLabel imageLabel;


    private int firstPicker;

    private int questionCounter;

    RightAnswerGuiMulti() { //ο ιδιος περιπου κατασκευαστης με αυτον του αντιστοιχου mode στο μονο παιχνιδι

        questionCounter = 1;
        rightAnswerGuiMultiFrame = new JFrame("APORTHITO");
        scoreString1 = String.valueOf(score1);
        scoreString2 = String.valueOf(score2);

        $$$setupUI$$$();
        setLanguage();
        setButtonInputActionMaps();
        setButtonActionListeners();


        rightAnswerGuiMultiFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rightAnswerGuiMultiFrame.setSize(1300, 600);
        rightAnswerGuiMultiFrame.setLocationRelativeTo(null);

                                                //μονη διαφορα πως εχουμε δυο σκορ να εμφανισουμε
        setTextLabel(scoreString1, scoreString2, getRandomQuestionAnswer(), getImageIfExists(), "@" + getQuestionCategory());

        rightAnswerGuiMultiFrame.setVisible(true);
        rightAnswerGuiMultiFrame.setContentPane(GameGuiMainPanel);
    }


    private void setLanguage() {                //καλειται στον κατασκευαστη για να σεταρει στη σωστη γλωσσα
        switch (Language.languageChoice) {
            case "greek": {
                GameGuiLeftLabel.setText("ΠΑΙΚΤΗΣ 1");
                GameGuiRightLabel.setText("ΠΑΙΚΤΗΣ 2");
                gameModeLabel.setText("ΓΥΡΟΣ ΣΩΣΤΗΣ ΑΠΑΝΤΗΣΗΣ");
                break;
            }
            case "english": {
                GameGuiLeftLabel.setText("PLAYER 1");
                GameGuiRightLabel.setText("PLAYER 2");
                gameModeLabel.setText("RIGHT ANSWER MODE");
                break;
            }
        }
    }

    private void setTextLabel(String score1, String score2, String questionAnswerText, ImageIcon icon, String category) {

        scoreLabel1.setText(score1);       //καλειται στον κατασκευαστη αλλα και οποτε θελουμε να εμφανισουμε καινουρια ερωτηση
        scoreLabel2.setText(score2);        //εμφανιζει τη νεα ερωτηση,σκορ,εικονα,κατηγορια
        QuestionAnswersText.setText(questionAnswerText);
        imageLabel.setIcon(icon);
        categoryLabel.setText(category);
    }


    private void questionLooper() {             //η μεθοδος που οπως ειπαμε υπαρχει σε ολα τα modes


                                        //Αυτη τη φορα λογω της υπαρξης 2 παικτων δεν ελεγχουμε εδω τις σωστες απαντησεις
                                        //αλλα μονο τον αριθμο των ερωτησεων που εχουμε παιξει

        if (questionCounter < 5) {
            scoreString1 = String.valueOf(score1);
            scoreString2 = String.valueOf(score2);
            setTextLabel(scoreString1, scoreString2, getRandomQuestionAnswer(), getImageIfExists(), "@" + getQuestionCategory());
            questionCounter++;
        } else {
            rightAnswerGuiMultiFrame.dispose();
            questionCounter = 0;
            randomMultiModePicker();

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


                                        //για τον παικτη2 εχουμε τα <6,7,8,9>
        button6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("6"), "pressed");
        button6.getActionMap().put("pressed", button6Action);
        button7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("7"), "pressed");
        button7.getActionMap().put("pressed", button7Action);
        button8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("8"), "pressed");
        button8.getActionMap().put("pressed", button8Action);
        button9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("9"), "pressed");
        button9.getActionMap().put("pressed", button9Action);
    }

    private void setButtonActionListeners() {    //φτιαχνουμε listeners για τα κουμπια μας
        button1.addActionListener(button1Action);
        button2.addActionListener(button2Action);
        button3.addActionListener(button3Action);
        button4.addActionListener(button4Action);

        button6.addActionListener(button6Action);
        button7.addActionListener(button7Action);
        button8.addActionListener(button8Action);
        button9.addActionListener(button9Action);
    }

    private void pickingPlayer1(String ch) {   //Αυτη η μεθοδος υπαρχει σε ολα τα modes του διπλου και ειναι αλλη αλλα σχεδον ιδια για καθε παιτκη
                                                //δεχεται ως ορισμα την επιλογη του παικτη1(η pickingPlayer2 του παικτη2)
                                                //καλειται οποτε ο παικτης δινει μια απαντηση

        switch (firstPicker) {                  //η μεταβλητη firstPicker αποθηκευει τον παικτη που απαντησε πρωτος σε καθε ερωτηση

            case 0:                         //αν ακομα δεν εχει απαντησει κανεις
                if (answerChecker(ch)) {
                    score1 += 1000;             //αν ειναι σωστη η απαντησε δωσε τους ποντους και καλεσε το θερμομετρο για να δουμε αν δικαιουται μπνους
                    thermometro(1);
                } else {
                    setPlayer1ButtonColor("red");  //αλλιως κανε τα κουμπια του κοκκινα
                }

                firstPicker = 1;            //δηλωσε πως απαντησε πρωτος ο παικτης1

                break;

            case 2:
                if (answerChecker(ch)) {            //αν εχει ηδη απαντησει ο παικτης 2
                    score1 += 1000;                 //κανε παλι τη διαδικασια για το σκορ και το θερμομετρο
                    thermometro(1);
                }
                questionLooper();               //καλεσε τον questionLooper για να παμε στην επομενη ερωτηση
                setPlayer2ButtonColor("green"); //ξανακανε τα κουμπια του παικτη2(που πιθανον εγιναν κοκκινα οταν απαντησε) πρασινα
                firstPicker = 0;                //δηλωσε πως για την νεα ερωτηση δεν εχει απαντησει κανεις
                break;
        }                                 //ΠΡΟΣΟΧΗ δεν υπαρχει case 1 αφου δεν θελουμε να κανει τιποτα αν ο παικτης εχει ηδη δωσει καποια απαντηση
    }                                       //αντιστοιχα δεν υπαρχει case 2 στον παικτη2

    private void pickingPlayer2(String ch) {  //δουλευει προφανως με αντιστοιχο τροπο με την πρωτη
        switch (firstPicker) {
            case 0:
                if (answerChecker(ch)) {
                    score2 += 1000;
                    thermometro(2);
                } else {
                    setPlayer2ButtonColor("red");
                }

                firstPicker = 2;

                break;

            case 1:
                if (answerChecker(ch)) {
                    score2 += 1000;
                    thermometro(2);
                }
                questionLooper();
                setPlayer1ButtonColor("green");
                firstPicker = 0;
                break;
        }
    }
                                                         //ακολουθουν 2 μεθοδοι που καλουνται για να αλλαξουν χρωμα τα κουμπια των παικτων
    private void setPlayer1ButtonColor(String color) {
        if (color.equals("green")) {
            button1.setForeground(new Color(-16551924));
            button2.setForeground(new Color(-16551924));
            button3.setForeground(new Color(-16551924));
            button4.setForeground(new Color(-16551924));
        }
        if (color.equals("yellow")) {
            button1.setForeground(new Color(0xC9CD4B));
            button2.setForeground(new Color(0xC9CD4B));
            button3.setForeground(new Color(0xC9CD4B));
            button4.setForeground(new Color(0xC9CD4B));
        }
        if (color.equals("red")) {
            button1.setForeground(new Color(0xB70900));
            button2.setForeground(new Color(0xB70900));
            button3.setForeground(new Color(0xB70900));
            button4.setForeground(new Color(0xB70900));
        }
    }

    private void setPlayer2ButtonColor(String color) {
        if (color.equals("green")) {
            button6.setForeground(new Color(-16551924));
            button7.setForeground(new Color(-16551924));
            button8.setForeground(new Color(-16551924));
            button9.setForeground(new Color(-16551924));
        }
        if (color.equals("yellow")) {
            button6.setForeground(new Color(0xC9CD4B));
            button7.setForeground(new Color(0xC9CD4B));
            button8.setForeground(new Color(0xC9CD4B));
            button9.setForeground(new Color(0xC9CD4B));
        }
        if (color.equals("red")) {
            button6.setForeground(new Color(0xB70900));
            button7.setForeground(new Color(0xB70900));
            button8.setForeground(new Color(0xB70900));
            button9.setForeground(new Color(0xB70900));
        }
    }


    private Action button1Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pickingPlayer1("a");                        //οταν πατιεται καποιο κουμπι καλειται κατευθειαν η pickingPlayer 1 ή 2 αναλογα με το σε ποιον ανηκει το κουμπι


            System.out.println("PATHSES TO KOUMPI 1");


        }
    };
    private Action button2Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            pickingPlayer1("b");


            System.out.println("PATHSES TO KOUMPI 2");

        }
    };
    private Action button3Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            pickingPlayer1("c");


            System.out.println("PATHSES TO KOUMPI 3");

        }
    };
    private Action button4Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            pickingPlayer1("d");


            System.out.println("PATHSES TO KOUMPI 4");

        }
    };


    private Action button6Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            pickingPlayer2("a");

            System.out.println("PATHSES TO KOUMPI 6");


        }
    };
    private Action button7Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {


            pickingPlayer2("b");

            System.out.println("PATHSES TO KOUMPI 7");

        }
    };
    private Action button8Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            pickingPlayer2("c");

            System.out.println("PATHSES TO KOUMPI 8");

        }
    };
    private Action button9Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            pickingPlayer2("d");

            System.out.println("PATHSES TO KOUMPI 9");

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
        GameGuiMainPanel = new JPanel();
        GameGuiMainPanel.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        GameGuiMainPanel.setBackground(new Color(-10328710));
        GameGuiMainPanel.setEnabled(true);
        GameGuiMainPanel.setForeground(new Color(-10328710));
        GameGuiRightPanel = new JPanel();
        GameGuiRightPanel.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        GameGuiRightPanel.setBackground(new Color(-10328710));
        GameGuiRightPanel.setEnabled(true);
        GameGuiRightPanel.setForeground(new Color(-6250346));
        GameGuiMainPanel.add(GameGuiRightPanel, new GridConstraints(0, 3, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        button6 = new JButton();
        button6.setBackground(new Color(-15332589));
        button6.setFont(new Font("Corbel", Font.BOLD, 26));
        button6.setForeground(new Color(-16551924));
        button6.setText("6");
        GameGuiRightPanel.add(button6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button9 = new JButton();
        button9.setBackground(new Color(-15332589));
        button9.setEnabled(true);
        button9.setFont(new Font("Corbel", Font.BOLD, 26));
        button9.setForeground(new Color(-16551924));
        button9.setText("9");
        GameGuiRightPanel.add(button9, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button8 = new JButton();
        button8.setBackground(new Color(-15332589));
        button8.setFont(new Font("Corbel", Font.BOLD, 26));
        button8.setForeground(new Color(-16551924));
        button8.setText("8");
        GameGuiRightPanel.add(button8, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button7 = new JButton();
        button7.setBackground(new Color(-15332589));
        button7.setFont(new Font("Corbel", Font.BOLD, 26));
        button7.setForeground(new Color(-16551924));
        button7.setText("7");
        GameGuiRightPanel.add(button7, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GameGuiRightLabel = new JLabel();
        GameGuiRightLabel.setFont(new Font("Corbel", Font.PLAIN, 22));
        GameGuiRightLabel.setForeground(new Color(-15332589));
        GameGuiRightLabel.setText("Player 2");
        GameGuiRightPanel.add(GameGuiRightLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scoreLabel2 = new JLabel();
        scoreLabel2.setFont(new Font("Corbel", Font.BOLD, 48));
        scoreLabel2.setForeground(new Color(-3551925));
        scoreLabel2.setText("Label");
        GameGuiRightPanel.add(scoreLabel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GameGuiLeftPanel = new JPanel();
        GameGuiLeftPanel.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        GameGuiLeftPanel.setBackground(new Color(-10328710));
        GameGuiMainPanel.add(GameGuiLeftPanel, new GridConstraints(0, 0, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        button1 = new JButton();
        button1.setBackground(new Color(-15332589));
        button1.setEnabled(true);
        button1.setFont(new Font("Corbel", Font.BOLD, 26));
        button1.setForeground(new Color(-16551924));
        button1.setHideActionText(false);
        button1.setText("1");
        GameGuiLeftPanel.add(button1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button4 = new JButton();
        button4.setBackground(new Color(-15332589));
        button4.setFont(new Font("Corbel", Font.BOLD, 26));
        button4.setForeground(new Color(-16551924));
        button4.setText("4");
        GameGuiLeftPanel.add(button4, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button3 = new JButton();
        button3.setBackground(new Color(-15332589));
        button3.setFont(new Font("Corbel", Font.BOLD, 26));
        button3.setForeground(new Color(-16551924));
        button3.setText("3");
        GameGuiLeftPanel.add(button3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button2 = new JButton();
        button2.setBackground(new Color(-15332589));
        button2.setFont(new Font("Corbel", Font.BOLD, 26));
        button2.setForeground(new Color(-16551924));
        button2.setText("2");
        GameGuiLeftPanel.add(button2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        GameGuiLeftLabel = new JLabel();
        GameGuiLeftLabel.setEnabled(true);
        GameGuiLeftLabel.setFont(new Font("Corbel", Font.PLAIN, 22));
        GameGuiLeftLabel.setForeground(new Color(-15332589));
        GameGuiLeftLabel.setText("Player 1");
        GameGuiLeftPanel.add(GameGuiLeftLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scoreLabel1 = new JLabel();
        scoreLabel1.setFont(new Font("Corbel", Font.BOLD, 48));
        scoreLabel1.setForeground(new Color(-3551925));
        scoreLabel1.setText("Label");
        GameGuiLeftPanel.add(scoreLabel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ImagePanel = new JPanel();
        ImagePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        ImagePanel.setBackground(new Color(-15332589));
        ImagePanel.setForeground(new Color(-6250346));
        GameGuiMainPanel.add(ImagePanel, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        imageLabel = new JLabel();
        imageLabel.setText("");
        ImagePanel.add(imageLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        QuestionPanel = new JPanel();
        QuestionPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        GameGuiMainPanel.add(QuestionPanel, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        QuestionAnswersText = new JTextArea();
        QuestionAnswersText.setBackground(new Color(-15332589));
        QuestionAnswersText.setFont(new Font("Corbel", Font.PLAIN, 22));
        QuestionAnswersText.setForeground(new Color(-16551924));
        QuestionPanel.add(QuestionAnswersText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        gameModeLabel = new JLabel();
        gameModeLabel.setFont(new Font("Corbel", Font.BOLD, 22));
        gameModeLabel.setForeground(new Color(-15332589));
        gameModeLabel.setText("Label");
        GameGuiMainPanel.add(gameModeLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        categoryLabel = new JLabel();
        categoryLabel.setFont(new Font("Corbel", Font.BOLD, 22));
        categoryLabel.setForeground(new Color(-15332589));
        categoryLabel.setText("Label");
        GameGuiMainPanel.add(categoryLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return GameGuiMainPanel;
    }
}
