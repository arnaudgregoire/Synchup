package com.example.arnaud.synch_up;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by Ariane on 06/04/2017
 */

public class Application {
    private ArrayList<Utilisateur> l_user = new ArrayList<Utilisateur>(); //on crée la liste d'utilisateur
    private Utilisateur currentUser;
    private Projet currentProjet;


    public void launch(){ //ici c'est un peu notre main, c'est d'ailleur ce qu'on appelle depuis main
        initialisation();
        Connexion();
    }

    private void initialisation(){ //à efault de pouvoir sauvegarder , on crée des trucs préenregistrés
        Utilisateur ariane = new Utilisateur("zezertiou","dedede",new ArrayList<Projet>()); //on crée le projet et sa liste de projet
        Projet synchup = new Projet("Synchup","Une application permettant de gérer les tâches",60,0, new ArrayList<Task>()); // on crée le projet et sa liste de tâche
        Task escp = new Task("Faire marcher le bordel", "Et y passer un temps fou");//on crée une tache
        ArrayList<Task> l_tache_synchup = new ArrayList<Task>(); // on crée la liste de tache du projet synchup
        ArrayList<Projet> l_projet_ariane = new ArrayList<Projet>();//on crée la liste de projet de l'utilisateur Ariane
        l_tache_synchup.add(escp);//on ajoute la tache escp a la liste de tache du projet synchup
        synchup.setListTaches(l_tache_synchup);//la liste tache synchup devient l'attribut de l'objet ptojet synchup
        l_projet_ariane.add(synchup);//on ajoute le projet synchup a la liste de projet d'ariane
        ariane.setListeDeProjets(l_projet_ariane);// la liste de projet devient l'attribut de l'utilisateur Ariane
        l_user.add(ariane);//on ajoute l'utilisateur Ariane à la liste d'utilisateurs
    }

    private void Connexion() { // on se connecte bref
        Scanner sc = new Scanner(System.in);
        System.out.println("Avez-vous déjà un compte ? Possibilités de réponse : 'Oui' et 'Non'.");
        String rep = sc.nextLine();

        if (rep.equals("Non")){
            register();
            login();
            }
        if (rep.equals("Oui")){
            login();
        }
        else{
            erreurInstruction();
            Connexion();
        }
    }

    private void Quefaire() {//la fameuse méthode qu'on va appeler sans arret
        DispCurrentProjet();
        Scanner Su = new Scanner(System.in);
        System.out.println("Que souhaitez-vous faire à présent?");
        System.out.println("Vous pouvez choisir : ");
        System.out.println(" - 0) Afficher mes projets ");
        System.out.println(" - 1) Ouvrir un projet ");
        System.out.println(" - 2) Créer un nouveau projet ");
        System.out.println(" - 3) Afficher mes tâches ");
        System.out.println(" - 4) Créer des tâches ");
        System.out.println(" - 5) Cocher une tâche ");
        System.out.println(" - 6) Vérifier la deadline d'un projet ");
        System.out.println(" - 7) Vérifier la deadline d'une tâche ");
        System.out.println(" - 8) Me déconnecter");

        String rep = Su.nextLine();//suit un long switch qui à chawque fois appelle une méthode spé, puis la méthode quefaire
        switch (rep) {
            case "0":
                System.out.println("");
                System.out.println("Vous avez choisi d'afficher vos projets");
                afficherProjets();
                Quefaire();
                break;

            case "1":
                System.out.println("");
                System.out.println("Vous avez choisi d'ouvrir un de vos projets");
                currentProjet = ouvrirProjets();
                Quefaire();
                break;

            case "2":
                System.out.println("");
                System.out.println("Vous avez choisi de créer un nouveau projet, veuillez renseigner ses caractéristiques");
                creerNouveauProjet();
                Quefaire();
                break;

            case "3":
                System.out.println("");
                System.out.println("Vous avez choisi d'afficher les taches du projet sélectionné");
                Boolean projet_exist = DispCurrentProjet();//méthode qui détecte si un projet existe et si c'est le cas affiche le nom du projet
                if(projet_exist){
                    afficherTask();
                    Quefaire();
                }
                else {
                    Quefaire();
                }
                break;

            case "4":
                System.out.println("");//on aére
                System.out.println("Vous avez choisi de créér une tache");
                Boolean projet_exist2 = DispCurrentProjet();
                if(projet_exist2){
                    creerTask();
                    Quefaire();
                }
                else {
                    Quefaire();
                }
                break;

            case "5":
                System.out.println("");
                System.out.println("Vous avez choisi de cocher une tache");
                Boolean projet_exist3 = DispCurrentProjet();
                if(projet_exist3){
                    cocherTask();
                    Quefaire();
                }
                else {
                    Quefaire();
                }
                break;

            case "6":
                System.out.println("");
                System.out.println("Vous avez choisi de cocher une tache");
                Boolean projet_exist4 = DispCurrentProjet();
                if(projet_exist4){
                    deadlineProjet();
                    Quefaire();
                }
                else {
                    Quefaire();
                }
                break;

            case "7":
                deadlineTask();
                Quefaire();
                break;

            case "8":
                logout();
                break;

            default:
                erreurInstruction();
                Quefaire();
                break;
        }
    }

    private  Boolean check_mdp(String nom,String mdp){
        Boolean result = false;
        for(int i = 0; i < l_user.size(); i++)
        {
            if (l_user.get(i).getNom().equals(nom) &&  l_user.get(i).getMdp().equals(mdp) ){//cf cours d'OpenClassrooms sur les ArrayList
                result = true;
            }
        }
        return result;
    }

    private Integer get_id_user(String nom,String mdp){
        Integer result = -1;
        for(int i = 0; i < l_user.size(); i++)
        {
            if (l_user.get(i).getNom().equals(nom) &&  l_user.get(i).getMdp().equals(mdp) ){
                result = i;
            }
        }
        return result;
    }

    private void badlogin(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nous n'avons pas réussi à vous identifier, voulez-vous vous inscrire " +
                "(tapez register) ou voulez-vous réessayer (taper login)");
        String rep = sc.nextLine();
        if (rep.equals("login")){
            login();
        }
        if (rep.equals("register")){
            register();
            login();
        }
        else {
            erreurInstruction();
            badlogin();
        }
    }
    private void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Connectez-vous!");
        System.out.println("Quel est votre nom d'utilisateur ?");
        String name = sc.nextLine();// Lire le nom d'utilisateur entré
        System.out.println("Quel est votre mode de passe ?");
        String mdp = sc.nextLine();// Lire le mot de passe
        Boolean result = check_mdp(name,mdp);
        if (!result){
            badlogin();
        }
        else {
            System.out.println("Authentification réussi");
            currentUser = new Utilisateur(name,mdp,new ArrayList<Projet>());
            Integer id = get_id_user(name,mdp);
            currentUser.setListeDeProjets(l_user.get(id).getListeProjets());
            Quefaire();
        }
    }
    private void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel sera votre nom d'utilisateur ?");
        String name = sc.nextLine();

        System.out.println("Quel sera votre mot de passe ?");
        String mot1 = sc.nextLine();

        System.out.println("Veuillez confirmer votre mot de passe.");
        String mot2 = sc.nextLine();

        while (!mot1.equals(mot2)) { // saisie différente à mot de passe
            System.out.println("Votre saisie doit être identique au mot de passe renseigné précédemment.");
            System.out.println("Veuillez confirmer votre mot de passe.");
            mot2 = sc.nextLine();
        }
        l_user.add(new Utilisateur(name,mot2,new ArrayList<Projet>()));
    }

    private void erreurInstruction(){
        System.out.println("Nous n'avons pas compris votre instruction, veuillez prendre soin de respecter l'orthographe et les majuscules des choix possibles.");
    }

    private ArrayList<Projet> afficherProjets(){//lea méthode renvoie une Array liste de projets
        // pour tout ce qui est get(i), voir cours d'openclassrroms sur les collections d'objets

        ArrayList<Projet> l_projet =  currentUser.getListeProjets();//elle regarde si il y a des projets en cours
        if (l_projet.size() == 0){
            System.out.println("Vous n'avez aucun projet en cours");
        }
        else {//et affiche tous les projets
            for (int i = 0; i < l_projet.size(); i++) {
                System.out.println("projet n° " + l_projet.get(i).getNumero() + " :");
                System.out.println("Nom :" + l_projet.get(i).getNom());
                System.out.println("Description :" + l_projet.get(i).getDescription());
            }
        }
        System.out.println("");
    return l_projet;
    }

    private Boolean DispCurrentProjet(){
        Boolean result = false;
        if (currentProjet == null){//==null => est ce que l'objet existe ?
            System.out.println("Vous n'avez aucun projet en cours, vous pouvez en ouvrir un avec - 1) Ouvrir un projet ");
        }
        else{
            result = true;
            System.out.println("Projet sélectionné :" + currentProjet.getNom());
        }
        return result;
    }

    private Projet ouvrirProjets(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Projet> l_projet = afficherProjets();
        System.out.println("Tapez le numéro du projet pour le sélectionner : ");
        Integer id_projet = Integer.valueOf(sc.nextLine());//le scanner lit un String et on veut le transformer en int
        return l_projet.get(id_projet);
    }

    private void creerNouveauProjet(){
        Scanner sc = new Scanner(System.in);
        System.out.println("nom : ");
        String nom = sc.nextLine();
        System.out.println("Description : ");
        String description = sc.nextLine();
        System.out.println("temps : ");
        Integer temps = Integer.valueOf(sc.nextLine()); //le scanner lit un String et on veut le transformer en int
        currentUser.addProjet(new Projet(nom, description, temps, currentUser.getListeProjets().size(),new ArrayList<Task>()));//on crée tout et on ajoute ça a la liste de projet de k'user
        System.out.println("Votre projet a bien été ajouté");
        System.out.println("");
    }

    private ArrayList<Task> afficherTask(){//même démarche que pour afficherprojet
        ArrayList<Task> l_tache = currentProjet.getListTaches();
        if (l_tache.size() == 0){
            System.out.println("Vous n'avez aucune tache en cours");
        }
        else{
            for(int i = 0; i < l_tache.size(); i++)
            {
                System.out.println("");
                System.out.println("Tâche n° " + i);
                System.out.println("Nom : " + l_tache.get(i).getNom());
                System.out.println("Description : " + l_tache.get(i).getDescription());
                System.out.println("Fait : " + l_tache.get(i).getFaitNonFait());
            }
        }
        return l_tache;
    }

    private void creerTask(){
        Scanner sc = new Scanner(System.in);
        System.out.println("nom : ");
        String nom = sc.nextLine();
        System.out.println("Description : ");
        String description = sc.nextLine();
        currentProjet.addTache(new Task(nom, description));
        System.out.println("Votre tâche a bien été ajoutée");
        System.out.println("");
    }

    private void cocherTask(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> l_tache = afficherTask();
        System.out.println("Tapez le numéro de la tache à cocher : ");
        Integer id_tache = Integer.valueOf(sc.nextLine());//la encore on transforme un String en int
        l_tache.get(id_tache).setFaitNonFait(true);//la tache est cochée, on a changée son attribut fait non fait en fait
    }

    //tout ce qui est deadline n'est pas encore fait. cc Antoine

    private void deadlineProjet(){
        System.out.println("Il vous reste " + currentProjet.getTemps() + " jours pour finir le projet");
    }

    private void deadlineTask(){
    }

    private void logout(){
        System.out.println("Déconnexion ! Retour au portail de connexion");
        login();
    }
}
