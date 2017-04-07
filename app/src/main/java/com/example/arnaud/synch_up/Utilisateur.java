package com.example.arnaud.synch_up;
import java.util.ArrayList;

/**
 * Created by Ariane on 06/04/2017.
 */



public class Utilisateur {
    //attributs
    private String nom;
    private String mdp;
    private ArrayList<Projet> listeDeProjets;

    //méthodes

    public void addProjet(Projet p){
        listeDeProjets.add(p);
    }

    //setters et getters

    public ArrayList<Projet> getListeProjets(){
        return listeDeProjets;
    }
    public void setMdp(String mdp) {this.mdp = mdp;}
    public void setNom(String nom) {this.nom = nom;}
    public void setListeDeProjets( ArrayList<Projet> listeDeProjets) {this.listeDeProjets = listeDeProjets;}
    public String getNom() {return nom;}
    public String getMdp() {return mdp;}



    //constructeur
    public Utilisateur(String name, String password, ArrayList<Projet> l_projet ){
        nom=name;
        mdp=password;
        listeDeProjets=l_projet;
    }
}