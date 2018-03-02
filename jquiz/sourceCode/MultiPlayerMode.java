import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;


public class MultiPlayerMode extends  QuestionAnswer {


    private static Random random;
    private static int randomMode;
    static int score1=0;
    static int score2=0;
    static String scoreString1;
    static String scoreString2;
    private static int numberOfRounds=0;
    private static String tmpScore;
    private static int tmpRand;
    private static int thermometroCounter1 = 0;
    private static int thermometroCounter2 = 0;
    private static boolean thermometroDone = false ;



    MultiPlayerMode(){

    }


    void srartMultiPlayerMode(){ randomMultiModePicker();}//Η μεθοδος που καλειται οταν επιλεγει το κουμπι του μονου
                                                             //και καλει απλα την επομενη μεθοδο για τυχαι επιλογη παιχνιδιου



    void randomMultiModePicker() {
        //σε αυτη την μεθοδο επιλεγουμε τυχαια τον τυπο παιχνιδιου
        //και τερματιζουμε το παιχνιδι αν εχουν παιχτει αρκετοι γυροι

        while (tmpRand == randomMode) {             //μεχρι να μην επιλεγει ο ιδιος τυπος παιχνιδιου με τον πρηγουμενο γυρο

            random = new Random();
            tmpRand = random.nextInt(4) + 1;  //δημιουργουμε μια μεταβλητη με τυχαι τιμη 1εως4.
        }

        randomMode = tmpRand;


        if (numberOfRounds < 5) {                //αν δεν εχουν παιχτει αρκετοι γυροι (5 στην προκειμενη)

            System.out.println("ROUND : " + numberOfRounds);

            switch (randomMode) {            //επιλεγουμε εναν τυπο παιχνιδιου

                case 1:
                    new RightAnswerGuiMulti();
                    break;

                case 2:
                    new BettingGuiMulti();
                    break;
                case 3:
                    new QuickAnswerGuiMulti();
                    break;
                case 4:
                    new TimerGuiMulti();
                    break;
            }

            numberOfRounds++;            // και αυξανουμε τον αριθμο τον γυρων

        } else {
                                        //αν εχουν παιχτει αρκετοι γυροι

            highscoreFileCheck(score1);  //καλουμε τη μεθοδο που ελεγχει για high score και για τους δυο παικτες
            highscoreFileCheck(score2);
            numberOfRounds = 0;             //μηδενιζουμε τους γυρους και το σκορ
            score1 = 0;
            score2 = 0;
            QuestionAnswer qa = new QuestionAnswer();
            qa.setupListsFromTexts();

            switch (Language.languageChoice) {  //επιστρεφουμε στο μενου
                case "greek":

                    Language.setMenuGR();

                    break;
                case "english":

                    Language.setMenuEN();

                    break;
            }


        }
    }


    private void highscoreFileCheck(int mscore){

        String s = getHighscore();    //εκχωρουμε το highscore σε μια μεταβλητη μεσω αλλης μεθοδου

        if (mscore > Integer.parseInt(s)) {         //αν το σκορ μας ειναι μεγαλυτερο απο το hiscore το αντικαθιστουμε στο αρχειο
            try (
                    FileWriter writer = new FileWriter("scoresMulti.txt")) {
                BufferedWriter textWriter = new BufferedWriter(writer);
                textWriter.write(Integer.toString(mscore));
                textWriter.close();


            } catch (java.io.IOException e) {
                System.out.println("Didnt Find The File");
            }
        }
    }

    static String getHighscore(){                                       // μεθοδος που επιστρεφει το highscore
        try (FileReader fileReader = new FileReader("scoresMulti.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            tmpScore = bufferedReader.readLine();
            fileReader.close();




        } catch (java.io.IOException e) {
            System.out.println("Didnt Find The File");
        }
        return tmpScore;
    }


    void thermometro(int player){
                                    //Αυτη η μεθοδος καλειται σε ολα τα modes για να ελεγξει την πορεια του παραλληλου mode
                                    //που τρεχει συνεχεια, με την ονομασια θερμομετρο
                                    //με ορισμα μια int που συμβολιζει τον παικτη που απαντησε και εξεταζουμε αν δικαιουται το μπονους



        if (!thermometroDone) {         //Αν το θερμομετρο δεν εχει τελειωσει..
            switch (player) {
                case 1:                    //αν ηταν ο παικτης1
                    thermometroCounter1 += 1;           //αυξησε τον μετρητη σωστων απαντησεων του παικτη 1
                    if ((thermometroCounter1 == 5) && (thermometroCounter2 < 5)) { //αν εχει 5 σωστες αλλα δεν εχει ο αντιπαλος του(αποφυγη μπονους και για τους δυο παικτες στον ιδιογυρο)
                        score1 += 5000;             //τοτε δωσε το μπονους
                        thermometroDone = true;     //και ενημερωσε πως το παιχνιδι του θερμομετρου τελειωσε
                    }
                    break;
                case 2:                            //αντιστοιχα για τον παικτη 2
                    thermometroCounter2 += 1;
                    if ((thermometroCounter2 == 5) && (thermometroCounter1 < 5)) {
                        score2 += 5000;
                        thermometroDone = true;

                    }
                    break;
            }

        }
    }


}













