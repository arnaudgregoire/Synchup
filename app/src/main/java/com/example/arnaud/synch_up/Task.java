
package com.example.arnaud.synch_up;

/**
 * Created by Ariane on 06/04/2017.
 */

public class Task {

    //Attributs =paramètres de la classe
    private String nom;
    private String description;
    private Boolean faitNonFait;

    //méthodes (fonctions)
    //setters et getters
    public String getDescription() {return description;}
    public String getNom() {return nom;}
    public void setDescription(String description) {this.description = description;}
    public void setNom(String nom) {this.nom = nom;}
    public void setFaitNonFait(Boolean faitNonFait) {this.faitNonFait = faitNonFait;}
    public Boolean getFaitNonFait() {return faitNonFait;}

    //autre

    public void taskFaite(){faitNonFait=true;}//on s'en sert dans Projet


    //constrcuteur (initialisation)
    public Task(String nom_input, String description_input){
        nom=nom_input;
        description=description_input;
        faitNonFait=false;
    }
}

