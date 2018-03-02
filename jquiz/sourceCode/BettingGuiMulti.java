import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by nikos on 17/1/2017.
 */
public class BettingGuiMulti extends MultiPlayerMode{



    private JFrame rightAnswerGuiMultiFrame;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;

    private JButton button250a;
    private JButton button250b;
    private JButton button500a;
    private JButton button500b;
    private JButton button750a;
    private JButton button750b;
    private JButton button1000a;
    private JButton button1000b;

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
    private boolean betActive;



    private String currentQuestionAnswer;
    private String betText;
    private int betChoice1;
    private int betChoice2;
    private int firstBetPicker;
    private int firstPicker;


    private int questionCounter;

        BettingGuiMulti() {                     //ο ιδιος περιπου κατασκευαστης με αυτον του αντιστοιχου mode στο μονο παιχνιδι

            questionCounter = 1;

            scoreString1 = String.valueOf(score1);
            scoreString2 = String.valueOf(score2);
            betActive= true;                        //εδω πρεπει να ελεγχυμε αν ειναι ενεργη η διαδικασια του πονταρισματος για να μην μπλεκονται
                                                    // οι εισοδοι απο το πληκτρολογιο αναμεσα σε απαντησεις και πονταρισματα
            $$$setupUI$$$();
            setFrame();
            setBetButtons();
            setButtonInputActionMaps();
            setButtonActionListeners();
            setLanguage();
            swapBasicButtonsToBetButtons1(true);
            swapBasicButtonsToBetButtons2(true);


            currentQuestionAnswer = getRandomQuestionAnswer();
            setTextLabel(scoreString1, scoreString2, betText + getQuestionCategory(), "@" + getQuestionCategory());

            rightAnswerGuiMultiFrame.setVisible(true);
            rightAnswerGuiMultiFrame.setContentPane(GameGuiMainPanel);
        }


        private void setFrame(){                                        //καλειται απο κατασκευαστη για να φτιαξει το παραθυρο
            rightAnswerGuiMultiFrame = new JFrame("APORTHITO");
            rightAnswerGuiMultiFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            rightAnswerGuiMultiFrame.setSize(1300, 600);
            rightAnswerGuiMultiFrame.setLocationRelativeTo(null);

        }


        private void setLanguage() {                    //καλειται στον κατασκευαστη για να σεταρει στη σωστη γλωσσα
            switch (Language.languageChoice) {
                case "greek": {
                    GameGuiLeftLabel.setText("ΠΑΙΚΤΗΣ 1");
                    GameGuiRightLabel.setText("ΠΑΙΚΤΗΣ 2");
                    gameModeLabel.setText("ΓΥΡΟΣ ΠΟΝΤΑΡΙΣΜΑΤΟΣ");
                    betText = "ΠΟΣΟΥΣ ΠΟΝΤΟΥΣ ΘΑ ΘΕΛΑΤΕ ΝΑ ΣΤΟΙΧΗΜΑΤΗΣΕΤΕ ΣΤΗΝ ΕΠΟΜΕΝΗ ΕΡΩΤΗΣΗ?\n ΚΑΤΗΓΟΡΙΑ ΕΡΩΤΗΣΗΣ : ";
                    break;
                }
                case "english": {
                    GameGuiLeftLabel.setText("PLAYER 1");
                    GameGuiRightLabel.setText("PLAYER 2");
                    gameModeLabel.setText("BETTING MODE");
                    betText = "HOW MANY POINTS WOULD YOU LIKE TO BET FOR THE NEXT QUESTION?\n QUESTION CATEGORY : ";
                    break;
                }
            }
        }

        private void setTextLabel(String score1, String score2, String questionAnswerText, String category) {

            scoreLabel1.setText(score1);                                    //καλειται στον κατασκευαστη αλλα και οποτε θελουμε να εμφανισουμε καινουρια ερωτηση
            scoreLabel2.setText(score2);                                    //εμφανιζει τη νεα ερωτηση,σκορ,εικονα,κατηγορια
            QuestionAnswersText.setText(questionAnswerText);
            categoryLabel.setText(category);
        }


        private void questionLooper() {                 //η μεθοδος που οπως ειπαμε υπαρχει σε ολα τα modes

            //Αυτη τη φορα λογω της υπαρξης 2 παικτων δεν ελεγχουμε εδω τις σωστες απαντησεις
            //αλλα μονο τον αριθμο των ερωτησεων που εχουμε παιξει

            if (questionCounter < 5) {
                scoreString1 = String.valueOf(score1);
                scoreString2 = String.valueOf(score2);
                questionCounter++;
            } else {
                rightAnswerGuiMultiFrame.dispose();
                questionCounter = 0;
                randomMultiModePicker();

            }
        }

                                                        //Φτιαχνουμε τα κουμπια για το πονταρισμα των 2 παικτων
        private void setBetButtons(){

            button250a = new JButton();
            button250a.setBackground(new Color(-15332589));
            button250a.setFont(new Font("Corbel", Font.PLAIN, 20));
            button250a.setForeground(new Color(-16551924));
            button250a.setText("250");
            GameGuiLeftPanel.add(button250a, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


            button250b = new JButton();
            button250b.setBackground(new Color(-15332589));
            button250b.setFont(new Font("Corbel", Font.PLAIN, 20));
            button250b.setForeground(new Color(-16551924));
            button250b.setText("250");
            GameGuiRightPanel.add(button250b, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


            button500a = new JButton();
            button500a.setBackground(new Color(-15332589));
            button500a.setFont(new Font("Corbel", Font.PLAIN, 20));
            button500a.setForeground(new Color(-16551924));
            button500a.setText("500");
            GameGuiLeftPanel.add(button500a, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


            button500b = new JButton();
            button500b.setBackground(new Color(-15332589));
            button500b.setFont(new Font("Corbel", Font.PLAIN, 20));
            button500b.setForeground(new Color(-16551924));
            button500b.setText("500");
            GameGuiRightPanel.add(button500b, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

            button750a = new JButton();
            button750a.setBackground(new Color(-15332589));
            button750a.setFont(new Font("Corbel", Font.PLAIN, 20));
            button750a.setForeground(new Color(-16551924));
            button750a.setText("750");
            GameGuiLeftPanel.add(button750a, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


            button750b = new JButton();
            button750b.setBackground(new Color(-15332589));
            button750b.setFont(new Font("Corbel", Font.PLAIN, 20));
            button750b.setForeground(new Color(-16551924));
            button750b.setText("750");
            GameGuiRightPanel.add(button750b, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


            button1000a = new JButton();
            button1000a.setBackground(new Color(-15332589));
            button1000a.setFont(new Font("Corbel", Font.PLAIN, 20));
            button1000a.setForeground(new Color(-16551924));
            button1000a.setText("1000");
            GameGuiLeftPanel.add(button1000a, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


            button1000b = new JButton();
            button1000b.setBackground(new Color(-15332589));
            button1000b.setFont(new Font("Corbel", Font.PLAIN, 20));
            button1000b.setForeground(new Color(-16551924));
            button1000b.setText("1000");
            GameGuiRightPanel.add(button1000b, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


        }

    private void swapBasicButtonsToBetButtons1(boolean bet) {
        button1.setVisible(!bet);                                               //Μεθοδος που εναλασσει τα κουμπια πονταρισματος με τα κουμπια απαντησης
        button2.setVisible(!bet);
        button3.setVisible(!bet);
        button4.setVisible(!bet);
        button250a.setVisible(bet);
        button500a.setVisible(bet);
        button750a.setVisible(bet);
        button1000a.setVisible(bet);
    }


    private void swapBasicButtonsToBetButtons2(boolean bet) {
        button6.setVisible(!bet);
        button7.setVisible(!bet);
        button8.setVisible(!bet);                                   //Μεθοδος που εναλασσει τα κουμπια πονταρισματος με τα κουμπια απαντησης
        button9.setVisible(!bet);
        button250b.setVisible(bet);
        button500b.setVisible(bet);
        button750b.setVisible(bet);
        button1000b.setVisible(bet);
    }


                                               //συγχρονιζουμε και τα κουμπια πονταρισματος και αυτα της απαντησης(τα οποια εναλλασσονται)
                                               //με τα αντιστοιχα <1,2,3,4> για τον παικτη1 και <6,7,8,9> για τον παικτη2
    private void setButtonInputActionMaps() {
        button1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), "pressed");
        button1.getActionMap().put("pressed", button1Action);
        button2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"), "pressed");
        button2.getActionMap().put("pressed", button2Action);
        button3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"), "pressed");
        button3.getActionMap().put("pressed", button3Action);
        button4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("4"), "pressed");
        button4.getActionMap().put("pressed", button4Action);

        button6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("6"), "pressed");
        button6.getActionMap().put("pressed", button6Action);
        button7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("7"), "pressed");
        button7.getActionMap().put("pressed", button7Action);
        button8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("8"), "pressed");
        button8.getActionMap().put("pressed", button8Action);
        button9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("9"), "pressed");
        button9.getActionMap().put("pressed", button9Action);

        button250a.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), "pressed");
        button250a.getActionMap().put("pressed", button250aAction);
        button500a.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"), "pressed");
        button500a.getActionMap().put("pressed", button500aAction);
        button750a.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"), "pressed");
        button750a.getActionMap().put("pressed", button750aAction);
        button1000a.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("4"), "pressed");
        button1000a.getActionMap().put("pressed", button1000aAction);

        button250b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("6"), "pressed");
        button250b.getActionMap().put("pressed", button250bAction);
        button500b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("7"), "pressed");
        button500b.getActionMap().put("pressed", button500bAction);
        button750b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("8"), "pressed");
        button750b.getActionMap().put("pressed", button750bAction);
        button1000b.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("9"), "pressed");
        button1000b.getActionMap().put("pressed", button1000bAction);
    }

    private void setButtonActionListeners() {               //φτιαχνουμε listeners για τα κουμπια μας
        button1.addActionListener(button1Action);
        button2.addActionListener(button2Action);
        button3.addActionListener(button3Action);
        button4.addActionListener(button4Action);

        button6.addActionListener(button6Action);
        button7.addActionListener(button7Action);
        button8.addActionListener(button8Action);
        button9.addActionListener(button9Action);

        button250a.addActionListener(button250aAction);
        button500a.addActionListener(button500aAction);
        button750a.addActionListener(button750aAction);
        button1000a.addActionListener(button1000aAction);

        button250b.addActionListener(button250bAction);
        button500b.addActionListener(button500bAction);
        button750b.addActionListener(button750bAction);
        button1000b.addActionListener(button1000bAction);
    }

    private void pickingPlayer1(String ch) {

        //Αυτη η μεθοδος υπαρχει σε ολα τα modes του διπλου και ειναι αλλη αλλα σχεδον ιδια για καθε παιτκη
        //δεχεται ως ορισμα την επιλογη του παικτη1(η pickingPlayer2 του παικτη2)
        //καλειται οποτε ο παικτης δινει μια απαντηση

            switch (firstPicker) {
            case 0:
                if (answerChecker(ch)) {       //λειτουργει οπως αυτη του παιχνιδιου ΣωστηςΑπαντησης στο διπλο
                    score1 += betChoice1;       //αλλα δινει ποντους αναλογα με το πονταρισμα
                    thermometro(1);
                } else {
                    setPlayer1ButtonColor("red");
                }

                firstPicker = 1;

                break;

            case 2:
                if (answerChecker(ch)) {
                    score1 += betChoice1;
                    thermometro(1);
                }
                betActive = true;
                questionLooper();
                swapBasicButtonsToBetButtons1(true);  //και εδω εναλλασει τα κουμπια με αυτα του πονταρισματος
                swapBasicButtonsToBetButtons2(true);

                currentQuestionAnswer = getRandomQuestionAnswer();
                setTextLabel(scoreString1,scoreString2, betText + getQuestionCategory(), "@" + getQuestionCategory());
                imageLabel.setIcon(null);

                setPlayer2ButtonColor("green");
                firstPicker = 0;
                break;
        }
    }

    private void pickingPlayer2(String ch) {
        switch (firstPicker) {
            case 0:
                if (answerChecker(ch)) {
                    score2 += betChoice2;
                    thermometro(2);
                } else {
                    setPlayer2ButtonColor("red");
                }

                firstPicker = 2;

                break;

            case 1:
                if (answerChecker(ch)) {
                    score2 += betChoice2;
                    thermometro(2);
                }
                betActive = true;
                questionLooper();
                swapBasicButtonsToBetButtons1(true);
                swapBasicButtonsToBetButtons2(true);

                currentQuestionAnswer = getRandomQuestionAnswer();
                setTextLabel(scoreString1,scoreString2, betText + getQuestionCategory(), "@" + getQuestionCategory());
                imageLabel.setIcon(null);

                setPlayer1ButtonColor("green");
                firstPicker = 0;
                break;
        }
    }

    private void pickingBetPlayer1(int bet){

            //Αυτη η μεθοδος εξυπηρετει παρομοιους σκοπους με την απο πανω
            //με τη διαφορα πως καλειται οταν καποιος δωσει ενα πονταρισμα.
            //Φυσικηα σε αυτην την περιπτωση δεν μας ενδιαφερει ποιος απαντησε πρωτος αλλα θελουμε να ελεγχουμε αν απαντησαν και οι δυο για να προχωρησουμε

            switch (firstBetPicker){
                case 0:                      //αν δεν εχει απαντησει κανεις
                    betChoice1= bet;         //αποθηκευσε το πονταρισμα
                    firstBetPicker=1;       //και ενημερωσε πως ο παικτης1 εχει απαντησει
                    break;
                case 2:                     //αν εχει απαντησει ο αλλος παικτης
                    betActive = false;      //ενημερωσε πως θα κλεισει το πονταρισμα
                    betChoice1= bet;        // αποθηκευσε και παλι το πονταρισμα
                    swapBasicButtonsToBetButtons1(false);       //αλλαξε τα κουμπια σε αυτα της απαντησης
                    swapBasicButtonsToBetButtons2(false);
                    setTextLabel(scoreString1,scoreString2, currentQuestionAnswer, "@" + getQuestionCategory());
                    imageLabel.setIcon(getImageIfExists());             //εμφανισε την καινουρια ερωτηση και την εικονα αν υπαρχει
                    break;

            }

    }

    private void pickingBetPlayer2(int bet){
        switch (firstBetPicker){
            case 0:
                betChoice2= bet;
                firstBetPicker=2;
                break;
            case 1:
                betActive = false;
                betChoice2= bet;
                swapBasicButtonsToBetButtons1(false);
                swapBasicButtonsToBetButtons2(false);
                setTextLabel(scoreString1,scoreString2, currentQuestionAnswer, "@" + getQuestionCategory());
                imageLabel.setIcon(getImageIfExists());
                break;

        }

    }

                        //Ακολουθουν μεθοδη για την εναλλαγη του χρωματος των κουμπιων

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

                                        //Η αλλαγη που υπαρχει οταν ο χρηστης παταει καποιο πληκτρο σαν απαντηση ειναι πως
                                        //ελεγχεται αν ειναι ενεργη η φαση του πονταρισματος ή της ερωτησης
                                        // καιλειται η αναλογη "picking" μεθοδος αν επελεγε το υψος του πονταρισματος ή καποια πιθανη σωστη απαντηση
            if (!betActive) {
                pickingPlayer1("a");

                System.out.println("PATHSES TO KOUMPI 1");
            }


        }
    };
    private Action button2Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (!betActive) {
                pickingPlayer1("b");


                System.out.println("PATHSES TO KOUMPI 2");
            }

        }
    };
    private Action button3Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (!betActive) {
                pickingPlayer1("c");


                System.out.println("PATHSES TO KOUMPI 3");
            }

        }
    };
    private Action button4Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (!betActive) {
                pickingPlayer1("d");


                System.out.println("PATHSES TO KOUMPI 4");
            }

        }
    };


    private Action button6Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (!betActive) {
                pickingPlayer2("a");

                System.out.println("PATHSES TO KOUMPI 6");
            }


        }
    };
    private Action button7Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (!betActive) {
                pickingPlayer2("b");

                System.out.println("PATHSES TO KOUMPI 7");
            }

        }
    };
    private Action button8Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (!betActive) {
                pickingPlayer2("c");

                System.out.println("PATHSES TO KOUMPI 8");
            }

        }
    };
    private Action button9Action = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (!betActive) {
                pickingPlayer2("d");

                System.out.println("PATHSES TO KOUMPI 9");
            }

        }
    };


    private Action button250aAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (betActive) {
                pickingBetPlayer1(250);
            }
        }
    };

    private Action button250bAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (betActive) {
                pickingBetPlayer2(250);
            }
        }
    };
    private Action button500aAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (betActive) {
                pickingBetPlayer1(500);
            }

        }
    };

    private Action button500bAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (betActive) {
                pickingBetPlayer2(500);
            }
        }
    };


    private Action button750aAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (betActive) {
                pickingBetPlayer1(750);
            }
        }
    };

    private Action button750bAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (betActive) {
                pickingBetPlayer2(750);
            }
        }
    };
    private Action button1000aAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (betActive) {
                pickingBetPlayer1(1000);
            }
        }
    };

    private Action button1000bAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (betActive) {
                pickingBetPlayer2(1000);
            }
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
