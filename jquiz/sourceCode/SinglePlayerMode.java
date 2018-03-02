import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;



class SinglePlayerMode extends  QuestionAnswer {


    private static Random random;
    private static int randomMode;
    static int score=0;
    static String scoreString;
    private static int numberOfRounds=0;
    private static String tmpScore;
    private static int tmpRand;



    SinglePlayerMode(){

    }

    void startSinglePlayerMode(){randomSingleModePicker();} //Η μεθοδος που καλειται οταν επιλεγει το κουμπι του μονου
                                                        //και καλει απλα την επομενη μεθοδο για τυχαι επιλογη παιχνιδιου

    void randomSingleModePicker(){
    //σε αυτη την μεθοδο επιλεγουμε τυχαια τον τυπο παιχνιδιου
    //και τερματιζουμε το παιχνιδι αν εχουν παιχτει αρκετοι γυροι

        while(tmpRand == randomMode){                   //μεχρι να μην επιλεγει ο ιδιος τυπος παιχνιδιου με τον πρηγουμενο γυρο

            random = new Random();
            tmpRand = random.nextInt(3) + 1;    //δημιουργουμε μια μεταβλητη με τυχαια τιμη 1 εως 3.
        }

        randomMode = tmpRand;





        if (numberOfRounds<5) {        //αν δεν εχουν παιχτει αρκετοι γυροι (5 στην προκειμενη)

            System.out.println("ROUND : " + numberOfRounds);

            switch (randomMode) {            //επιλεγουμε εναν τυπο παιχνιδιου

                case 1:
                    new RightAnswerGuiSingle();
                    break;
                case 2:
                    new BettingGuiSingle();
                    break;
                case 3:
                    new TimerGuiSingle();
                    break;

            }

            numberOfRounds++;     // και αυξανουμε τον αριθμο τον γυρων

        }else {
                                    //αν εχουν παιχτει αρκετοι γυροι

            highscoreFileCheck();           //καλουμε τη μεθοδο που ελεγχει για high score
            numberOfRounds=0;               //μηδενιζουμε τους γυρους και το σκορ
            score=0;
            QuestionAnswer qa = new QuestionAnswer();
            qa.deleteAllTheContentFromLists();
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


    private void highscoreFileCheck(){

        String s = getHighscore();    //εκχωρουμε το highscore σε μια μεταβλητη μεσω αλλης μεθοδου

        if (score > Integer.parseInt(s)) {          //αν το σκορ μας ειναι μεγαλυτερο απο το hiscore το αντικαθιστουμε στο αρχειο
            try (
                    FileWriter writer = new FileWriter("scores.txt")) {
                BufferedWriter textWriter = new BufferedWriter(writer);
                textWriter.write(Integer.toString(score));
                textWriter.close();


            } catch (java.io.IOException e) {
                System.out.println("Didnt Find The File");
            }
        }
    }

    static String getHighscore(){                  // μεθοδος που επιστρεφει το highscore
        try (FileReader fileReader = new FileReader("scores.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            tmpScore = bufferedReader.readLine();
            fileReader.close();




        } catch (java.io.IOException e) {
            System.out.println("Didnt Find The File");
        }
        return tmpScore;
    }
}
