package TP_1;

public class Menu {

    /**
     * Cette methode affiche le menu principal de la manière suivante :
     *      • Menu principal :
     *          1. Afficher une note
     *          2. Afficher une gamme
     *          3. Quitter
     *
     * @autor :
     */
    public static void menuPrincipal(){

        System.out.println(  Constante.MENU_PRINCIPAL );

    }

    /**
     * Cette methode affiche le menu des 12 notes allant de DO à SI de la manière suivante :
     *      • Menu note
     *          1. Do                                   7.  Fa dièse
     *          2. Do dièse                             8.  Sol
     *          3. Ré                                   9.  Sol dièse
     *          4. Ré dièse                             10. La
     *          5. Mi                                   11. La dièse
     *          6. Fa                                   12. Si
     *                          13. Quitter
     *
     * @autor :
     */
    public static void menuNote(){

        System.out.println( Constante.MENU_NOTE );
    }

    /**
     * Cette methode affiche le menu principal de la manière suivante :
     *      • Menu Gamme :
     *          1. Majeure
     *          2. Mineure
     *          3. Quitter
     *
     * @autor :
     */
    public static void menuGamme(){

        System.out.println( Constante.MENU_GAMME );
    }

    /**
     * Cette méthode presente une briève description du logiciel sous la forme suivante :
     *      ---------------------------------------------------------------
     *      | logiciel permettant à des apprentis guitaristes d’apprendre |
     *      | leurs gammes ainsi que la position des notes sur un  manche |
     *      |                        de guitare.                          |
     *      ---------------------------------------------------------------
     *
     * @autor :
     */
    public static void presentationLogiciel(){
        System.out.println(Constante.MSG_PRESENTATION);
    }

    /**
     * Cette methode permet de faire la gestion de l'affichage d'une note sur le manche. Elle reçoit en paramètre le
     * choix de l'utilisateur et en fonction de ce choix elle affiche les différents emplacements de cette note sur le
     * manche.
     * @param choix : le choix de l'utilisateur
     *
     * @autor :
     */
    public static void gestionAffichageNote(int choix){

        while (choix != Constante.CHOIX_QUITTER_NOTE){
            menuNote();
            choix = verifierAnnulation(Constante.MSG_SOL_CHOIX, Constante.MIN, Constante.CHOIX_QUITTER_NOTE);

            if (choix != Constante.CHOIX_QUITTER_NOTE){
                Manche.placerNoteSurManche(Manche.manche, choix);
                Manche.afficherNoteSurManche(Manche.manche);
                UtilitaireValidation.pause();
            }
            Manche.initialiserManche();
        }
    }

    /**
     * Cette methode permet de faire la gestion de l'affichage des notes d'une gamme sur le manche. Elle reçoit en
     * paramètre le choix de l'utilisateur et en fonction de ce choix elle place et affiche les différents emplacements
     * des notes de cette gamme sur le manche.
     * NB : juste les gammes majeures et mineures sont prises en charge par cette méthode
     * @param choix : le choix de l'utilisateur
     *
     * @autor :
     */
    public static void gestionAffichageGamme(int choix){

        while (choix != Constante.CHOIX_QUITTER_NOTE){

            int choixGamme;
            menuNote();
            choix = verifierAnnulation(Constante.MSG_SOL_CHOIX, Constante.MIN, Constante.CHOIX_QUITTER_NOTE);

            if (choix != Constante.CHOIX_QUITTER_NOTE){

                menuGamme();
                choixGamme = verifierAnnulation(Constante.MSG_SOL_CHOIX, Constante.MIN, Constante.CHOIX_QUITTER_GAMME);

                if (choixGamme != Constante.CHOIX_QUITTER_GAMME){
                    Manche.placerGammeSurManche(Manche.manche, choix, choixGamme);
                    Manche.afficherNoteSurManche(Manche.manche);
                    UtilitaireValidation.pause();
                }
            }
            Manche.initialiserManche();
        }
    }

    /**
     * Cette méthode permet de faire un validation des saisie de l'utilisateur. Si l'utilisateur fait <Enter> sans
     * rien saisir au préalable, la méthode évite que le système plante et redemande à l'utilisateur de faire une
     * saisie
     *
     * @param msg : Le message de sollicitation
     * @param min : la valeur minimum des valeurs possibles
     * @param max : La valer max des valeurs possibles
     * @return : le choix valide de l'utilisateur.
     *
     * @autor :
     */
    public static int verifierAnnulation( String msg, int min, int max){

        int choix;

        do {
            UtilitaireValidation.reinitialise();
            choix = UtilitaireValidation.lireInt(msg, min, max);
        }

        while (UtilitaireValidation.utilisateurAnnule());

        return choix;
    }

    public static void main(String[] args) {

        int choix = 0;

        presentationLogiciel();

        while (choix != Constante.CHOIX_QUITTER){

            Manche.initialiserManche();
            menuPrincipal();
            choix = verifierAnnulation(Constante.MSG_SOL_CHOIX, Constante.MIN, Constante.MAX);

            if (choix != Constante.CHOIX_QUITTER){
                if (choix == Constante.CHOIX_MENU_NOTE){
                    gestionAffichageNote(choix);
                }
                else {
                    gestionAffichageGamme(choix);
                }
            }
        }
        System.out.println(Constante.MSG_FIN);
    }

}
