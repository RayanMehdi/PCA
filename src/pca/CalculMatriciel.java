/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pca;

/**
 *
 * @author rayanmehdi1
 */
public class CalculMatriciel {
    //Attributs
    private Matrice m;
    private Vecteur v;
    private double arret;
    
    public void initial(Matrice m, Vecteur v, double arret){
        this.m = m;
        this.v = v;
        this.arret = arret;
    }
    
    public void launch(){
        Vecteur v2 = v;
        double valeur_propre = 1, valeur_propre_2 = 0;
        while( Math.abs(valeur_propre - valeur_propre_2)> arret){
           v2 = multiplicate();
           v2.aff_vecteur();
        }
    }
    
    public Vecteur multiplicate(){
        Vecteur test = new Vecteur(this.v.getTaille());
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
}
