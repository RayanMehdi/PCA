/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pca;


//Classe eprmettant d'effectuer les calculs
import static java.lang.Math.sqrt;
import java.util.ArrayList;

/**
 *
 * @author rayanmehdi1
 */
public class CalculMatriciel {
    //Attributs
    private Matrice m;
    private Vecteur v;
    private double pourcentageTrace;
    private ArrayList<Double> tabValPropre;
    
    //initialise m et v ainsi q'une variable arret
    
    public CalculMatriciel(Matrice m, Vecteur v, double pourcentageTrace){
        this.m = m;
        this.v = v;
        this.pourcentageTrace = pourcentageTrace;
        tabValPropre = new ArrayList();
    }
    
    /* commence l'algo
    -prendre une matrice
    -prendre un vecteur (commencé par 1...1)
    -while |v1 - v2| > arret
    -multiplié matrice avec vecteur
    -v = v/norm(v) 
    -retourné au while
    */
    
    /*
    A faire : prendre la fonction deflation
    utiliser la nouvelle matrice de deflation dans l'algo
    pour la valeur de while, calculer la trace et utilisé une aray list composé
    des valeurs propres. Si 90% de la somme de ces valeurs propres est atteinte 
    (trace = 100% des valeurs propres)
    */
    
    public void calcul_valeurpropre(){//permet de calculer le vecteur propre et la valeur propre de la matrice
        Vecteur v2 = v;
        double valeur_propre = 1, valeur_propre_2 = 0;
        double trace = calcul_trace();
        //while( Math.abs(valeur_propre - valeur_propre_2)> arret){
        while( sumTab() >= trace*pourcentageTrace){
           valeur_propre = valeur_propre_2;
           v2 = norme_vecteur(v2); //rpz le vecteur B dans lalgo
           v = multiplicate(v2);// rpz le vecteur x dans l'algo
           valeur_propre_2 = transposition(v2,v);
           v2.aff_vecteur();
            System.out.println(valeur_propre_2);
            tabValPropre.add(valeur_propre_2);
           
        }
    }
    
    public double sumTab(){
        double sum = 0;
        for(Double d : tabValPropre){
            sum += d;
        }
        return sum;
    }
    
    public double calcul_trace(){
        int trace = 0;
        for( int i = 0; i < m.getColonnes(); i++){
            for (int j = 0; j < m.getLigne(); j++) {
                if( i == j )
                    trace += m.getElement(i, j);
            }
        }
        return trace;
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
