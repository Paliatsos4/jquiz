import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

class QuestionAnswer {

    private Random r;

    private String rightAnswerCheck ;
    private String choice;
    private String tempQA;
    private String questionCategory;

    private int imageFileCounter ;
    private int rand;

    private static List<String> randomSequenceAnswer ;
    private static List<String> questionGR;
    private static List<String> answer1GR;
    private static List<String> answer2GR;
    private static List<String> answer3GR;
    private static List<String> rightAnswerGR;
    private static List<String> categoryGR;
    private static List<String> questionEN;
    private static List<String> answer1EN;
    private static List<String> answer2EN;
    private static List<String> answer3EN;
    private static List<String> rightAnswerEN;
    private static List<String> categoryEN;
    private ImageIcon icon1;
    private ImageIcon icon2;
    private ImageIcon icon3;
    private ImageIcon icon4;
    private ImageIcon icon5;
    private ImageIcon icon6;
    private ImageIcon icon7;
    private ImageIcon icon8;
    private ImageIcon icon9;
    private ImageIcon icon10;
    private ImageIcon icon11;
    private ImageIcon icon12;
    private ImageIcon icon13;
    private ImageIcon icon14;
    private ImageIcon icon15;


    private ImageIcon tempImageIcon;



    private static List<ImageIcon> images;
    private Charset charset ;
    private Path questions_path;
    private Path answer1_path;
    private Path answer2_path;
    private Path answer3_path ;
    private Path category_path ;
    private Path rightAnswer_path;




    void setupListsFromTexts() {

        icon1 = new ImageIcon("images/image1.jpg");         //αποθηκευουμε τις εικονες απο τον φακελο σε αντικειμενα ImageIcon.
        icon2 = new ImageIcon("images/image2.jpg");
        icon3 = new ImageIcon("images/image3.jpg");
        icon4 = new ImageIcon("images/image4.jpg");
        icon5 = new ImageIcon("images/image5.jpg");
        icon6 = new ImageIcon("images/image6.jpg");
        icon7 = new ImageIcon("images/image7.jpg");         //αποθηκευουμε τις εικονες απο τον φακελο σε αντικειμενα ImageIcon.
        icon8 = new ImageIcon("images/image8.jpg");
        icon9 = new ImageIcon("images/image9.jpg");
        icon10 = new ImageIcon("images/image10.jpg");
        icon11 = new ImageIcon("images/image11.jpg");
        icon12 = new ImageIcon("images/image12.jpg");
        icon13 = new ImageIcon("images/image13.jpg");
        icon14 = new ImageIcon("images/image14.jpg");
        icon15 = new ImageIcon("images/image15.jpg");

        images = new ArrayList<>();                             //κανουμε λιστα
        images.add(0,icon1);
        images.add(1,icon2);
        images.add(2,icon3);
        images.add(3,icon4);
        images.add(4,icon5);
        images.add(5,icon6);
        images.add(6,icon7);
        images.add(7,icon8);
        images.add(8,icon9);
        images.add(9,icon10);
        images.add(10,icon11);
        images.add(11,icon12);
        images.add(12,icon13);
        images.add(13,icon14);
        images.add(14,icon15);




        switch (Language.languageChoice) {     //ελεγχουμε την επιλογη γλωσσας και....

            case "english": {
                Charset charset = Charset.forName("UTF-8");
                Path questions_path = Paths.get("english/question.txt");
                Path answer1_path = Paths.get("english/answer1.txt");
                Path answer2_path = Paths.get("english/answer2.txt");
                Path answer3_path = Paths.get("english/answer3.txt");
                Path category_path = Paths.get("english/category.txt");
                Path rightAnswer_path = Paths.get("english/rightAnswer.txt");

                try {
                    questionEN = Files.readAllLines(questions_path, charset);
                    answer1EN = Files.readAllLines(answer1_path, charset);            //φτιαχνουμε λιστες για τις ερωτησεις, τι πιθανες απαντησεις,
                    answer2EN = Files.readAllLines(answer2_path, charset);             //την κατηγορια και τη σωστη απαντηση
                    answer3EN = Files.readAllLines(answer3_path, charset);
                    rightAnswerEN = Files.readAllLines(rightAnswer_path, charset);
                    categoryEN = Files.readAllLines(category_path, charset);            //στα αγγλικα

                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            }
            case "greek": {
                charset = Charset.forName("UTF-8");
                questions_path = Paths.get("greek/erwthsh.txt");
                answer1_path = Paths.get("greek/apanthsh1.txt");
                answer2_path = Paths.get("greek/apanthsh2.txt");
                answer3_path = Paths.get("greek/apanthsh3.txt");
                category_path = Paths.get("greek/kathgoria.txt");
                rightAnswer_path = Paths.get("greek/swsthApanthsh.txt");

                try {
                    questionGR = Files.readAllLines(questions_path, charset);
                    answer1GR = Files.readAllLines(answer1_path, charset);              //στα ελληνικα
                    answer2GR = Files.readAllLines(answer2_path, charset);
                    answer3GR = Files.readAllLines(answer3_path, charset);
                    rightAnswerGR = Files.readAllLines(rightAnswer_path, charset);
                    categoryGR = Files.readAllLines(category_path, charset);



                } catch (IOException e) {
                    System.out.println(e);
                }

                break;
            }
        }
    }


    String getRandomQuestionAnswer (){                       //η συναρτηση η οποια επιστρεφει το String ,ερωτηση με τις 4 απαντησεις ανακατεμενες.


        r = new Random();
        switch(Language.languageChoice) {
            case "greek": {
                rand = r.nextInt(questionGR.size());                          //φτιαχνουμε μια λιστα με τις 4 απαντησεις και την σωστη απαντηση που θα εχει καθε φορα η καθε ερωτηση
                randomSequenceAnswer = new ArrayList<>();                       //την κανουμε shuffle
                randomSequenceAnswer.add(0, answer1GR.get(rand));           //κανουμε return το String, και κανουμε τη συγκεκριμενη ερωτηση remove απο ολες τις λιστες.
                randomSequenceAnswer.add(1, answer2GR.get(rand));
                randomSequenceAnswer.add(2, answer3GR.get(rand));
                randomSequenceAnswer.add(3, rightAnswerGR.get(rand));

                rightAnswerCheck = rightAnswerGR.get(rand);
                questionCategory = categoryGR.get(rand);

                Collections.shuffle(randomSequenceAnswer);

                tempQA = questionGR.get(rand) + "\nA)" + randomSequenceAnswer.get(0)+ "\nB)" + randomSequenceAnswer.get(1)+
                        "\nC)" + randomSequenceAnswer.get(2)+ "\nD)" + randomSequenceAnswer.get(3);



                return tempQA;

            }
            case "english":{                                                //same..

                rand = r.nextInt(questionEN.size());

                randomSequenceAnswer = new ArrayList<>();
                randomSequenceAnswer.add(0, answer1EN.get(rand));
                randomSequenceAnswer.add(1, answer2EN.get(rand));
                randomSequenceAnswer.add(2, answer3EN.get(rand));
                randomSequenceAnswer.add(3, rightAnswerEN.get(rand));

                rightAnswerCheck = rightAnswerEN.get(rand);
                questionCategory = categoryEN.get(rand);

                Collections.shuffle(randomSequenceAnswer);

                tempQA = questionEN.get(rand) + "\nA) " + randomSequenceAnswer.get(0)+ "\nB) " + randomSequenceAnswer.get(1)+
                        "\nC) " + randomSequenceAnswer.get(2)+ "\nD) " + randomSequenceAnswer.get(3);



                return tempQA;

            }
            default: return "ERROR 104";
        }
    }
    String getQuestionCategory(){                        //return κατηγορια συγκεκριμενης ερωτησης εξω απτην QuestionAnswer
        return questionCategory;
    }

    ImageIcon getImageIfExists(){
        imageFileCounter = images.size();                        //ελεγουμε εαν το index της random ερωτησης που επιλεχθικε ειναι μικροτερο απο το μεγεθος της λιστας των εικονων
                                                                //καθως εχουμε βαλει τις εικονες στις 15 πρωτες ερωτησεις.


        removeFromLists();                      //καλουμε εδω την συναρτηση οπου αφαιρει την ερωτηση και ολα τα στοιχεια της
                                                //εδω, καθως η getImageIfExists() τρεχει τελευταια.

        if (rand < imageFileCounter){

            tempImageIcon = images.get(rand);                       //εαν ναι την κανουμε return εαν οχι return null.

            images.remove(rand);

            return tempImageIcon;

        }
        else
            return null;
    }

  private void removeFromLists(){                                   //η συναρτηση που μετα απο καθε ερωτηση κανει remove
        switch (Language.languageChoice){                              //την ερωτηση, τις απαντησεις ,την σωστη απαντηση και την κατηγορια.
            case "greek":{
                questionGR.remove(rand);
                answer1GR.remove(rand);
                answer2GR.remove(rand);
                answer3GR.remove(rand);
                rightAnswerGR.remove(rand);
                categoryGR.remove(rand);
                break;
            }
            case "english":{
                questionEN.remove(rand);
                answer1EN.remove(rand);
                answer2EN.remove(rand);
                answer3EN.remove(rand);
                rightAnswerEN.remove(rand);
                categoryEN.remove(rand);
                break;
            }
        }
    }

    void deleteAllTheContentFromLists(){

      images.clear();

      switch (Language.languageChoice){                              //την ερωτηση, τις απαντησεις ,την σωστη απαντηση και την κατηγορια.
            case "greek":{
                questionGR.clear();
                answer1GR.clear();
                answer2GR.clear();
                answer3GR.clear();
                rightAnswerGR.clear();
                categoryGR.clear();
                break;
            }
            case "english":{
                questionEN.clear();
                answer1EN.clear();
                answer2EN.clear();
                answer3EN.clear();
                rightAnswerEN.clear();
                categoryEN.clear();
                break;
            }
        }
    }

    boolean answerChecker (String choicePar){                //ο ελεγχος σωστης απαντησης.

        choice = choicePar.toLowerCase();

        switch(choice){
            case "a":
                if(randomSequenceAnswer.get(0).equals(rightAnswerCheck)){       //Εαν ο παικτης επελεξε το γραμμα που αντιστοιχει στην αντιστοιχη απαντηση
                    return true;                                                //ελεγχουμε εαν αυτη η απαντηση ισουτε με την Σωστη απαντηση, εαν ναι γυρναμε true
                }                                                                 //εαν οχι false.
                else {return false;}

            case "b":
                if(randomSequenceAnswer.get(1).equals(rightAnswerCheck)){
                    return true;
                }
                else {return false;}

            case "c":
                if(randomSequenceAnswer.get(2).equals(rightAnswerCheck)){
                    return true;
                }
                else {return false;}

            case "d":
                if(randomSequenceAnswer.get(3).equals(rightAnswerCheck)){
                    return true;
                }
                else {return false;}

            default:
                return false;

        }
    }


}
