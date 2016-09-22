/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pca;

import static java.lang.Math.sqrt;

/**
 *
 * @author rayanmehdi1
 */
public class CalculMatriciel {
    //Attributs
    private Matrice m;
    private Vecteur v;
    private double arret;
    
    //initialise m et v ainsi q'une variable arret
    
    public CalculMatriciel(Matrice m, Vecteur v, double arret){
        this.m = m;
        this.v = v;
        this.arret = arret;
    }
    
    /* commence l'algo
    -prendre une matrice
    -prendre un vecteur (commencé par 1...1)
    -while |v1 - v2| > arret
    -multiplié matrice avec vecteur
    -v = v/norm(v) 
    -retourné au while
    */
    
    public void launch(){
        Vecteur v2 = v;
        double valeur_propre = 1, valeur_propre_2 = 0;
        while( Math.abs(valeur_propre - valeur_propre_2)> arret){
           valeur_propre = valeur_propre_2;
           v2 = norme_vecteur(v2); //rpz le vecteur B dans lalgo
           v = multiplicate(v2);// rpz le vecteur x dans l'algo
           valeur_propre_2 = transposition(v2,v);
           v2.aff_vecteur();
            System.out.println(valeur_propre_2);
           
        }
    }
    
    public Vecteur multiplicate(Vecteur v){
        Vecteur test = new Vecteur(v.getTaille());
        double value = 0;
        for( int i = 0; i < test.getTaille(); i++){
                for( int k = 0; k < this.m.getColonnes(); k++){
                    value += v.getElement(i) * m.getElement(i, k);
                }
            test.setElement(i, value);
            value = 0;
        }
        return test;
    }
    
    public Vecteur norme_vecteur(Vecteur v){
        double norme = 0;
        Vecteur vNorme = new Vecteur(v.getTaille());
        for( int i = 0; i < v.getTaille() ; i++){
            norme += v.getElement(i) * v.getElement(i);
        }
        norme = sqrt(norme);
        
        for( int i = 0; i < v.getTaille() ; i++){
            vNorme.setElement(i, v.getElement(i)/norme);
        }
        return vNorme;
    }
    
    public double transposition(Vecteur v, Vecteur v2){
        double vPropre = 0;
        for(int i = 0; i < v.getTaille() ; i++){
            vPropre += v.getElement(i)*v2.getElement(i);
        }
        return vPropre;
    }
}
