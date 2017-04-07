package com.example.arnaud.synch_up;
import java.util.ArrayList;
/**
 * Created by Ariane on 06/04/2017.
 */



public class Projet {
    //Attributes
    private String nom;
    private String description;
    private int temps;
    private ArrayList<Task> listeDeTaches;
    private int numero;

    //méthodes

    public void addTache(Task t){listeDeTaches.add(t);}

    //setters et getters

    public String getDescription(){return description;}
    public String getNom(){return nom;}
    public int getTemps(){return temps;}
    public int getNumero() {return numero;}
    public void setDescription(String description_input){description =description_input;}
    public void setTemps(int temps_input){temps=temps_input;}
    public void setNom(String nom_input){nom=nom_input;}
    public void setNumero(int numero) {this.numero = numero;}
    public ArrayList<Task> getListTaches(){
        return listeDeTaches;
    }
    public void setListTaches(ArrayList<Task> listeDeTaches){this.listeDeTaches =listeDeTaches;}


    //constructeurs
    public Projet(String nom_input, String description_input, int temps_input,int numero_input,ArrayList<Task> l_tache){
        nom=nom_input;
        description=description_input;
        temps=temps_input;
        numero=numero_input;
        listeDeTaches= l_tache;
    }
}