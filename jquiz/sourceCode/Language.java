class Language{


    public static String languageChoice;

    public static void setMenuGR(){

      //σεταρουμε το γενικο Μενου στα ελληνικα και το τρεχουμε
      System.out.println("Greek MENU Setted");
      new MenuGui().setButtonsLanguage("ΕΞΟΔΟΣ","ΒΟΗΘΕΙΑ","ΑΡΧΗ","Δεν χρειαζεστε βοηθεια.", "ΥΨΗΛΟΤΕΡΑ ΣΚΟΡ");
    }

    public static  void setSingleMultiGR(){

        //Σεταρουμε το μενου του επιλογης μονου-διπλου στα ελληνικα και το τρεχουμε
        new SingleMultiGui().setButtonTextLanguage("ΑΤΟΜΙΚΟ ΠΑΙΧΝΙΔΙ","ΔΙΠΛΟ ΠΑΙΧΝΙΔΙ","Επιλεξτε Τυπο Παιχνιδιου");
    }



    public static void setMenuEN(){

        //σεταρουμε το γενικο Μενου στα αγγλικα και το τρεχουμε
        System.out.println("English MENU Setted");
        new MenuGui().setButtonsLanguage("EXIT","HELP","START","You dont need help.", "HIGH SCORES");

    }
    public static void setSingleMultiEN(){
        //Σεταρουμε το μενου του επιλογης μονου-διπλου στα αγγλικα και το τρεχουμε
        new SingleMultiGui().setButtonTextLanguage("SINGLE PLAYER","MULTI PLAYER","Select Game Mode");
    }




}
