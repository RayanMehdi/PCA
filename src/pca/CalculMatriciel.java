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
    private Matrice mvp;
    private Vecteur v;
    private double pourcentageTrace;
    private ArrayList<Double> tabValPropre;
    private ArrayList<Vecteur> tabVectPropre;
    
    //initialise m et v ainsi q'une variable arret
    
    public CalculMatriciel(Matrice m, Vecteur v, double pourcentageTrace){
        this.m = m;
        this.v = v;
        this.pourcentageTrace = pourcentageTrace;
        tabValPropre = new ArrayList();
        tabVectPropre = new ArrayList<>();
    }
    
    /* commence l'algo
    -prendre une matrice
    -prendre un vecteur (commencé par 1...1)
    -while |v1 - v2| > arret
    -multiplié matrice avec vecteur
    -v = v/norm(v) 
    -retourné au while
    */
    
    //pour deflation 
    
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
        while( sumTab() <= trace*pourcentageTrace){
           valeur_propre = valeur_propre_2;
           v2 = norme_vecteur(v2); //rpz le vecteur B dans lalgo
           v = multiplicate(v2, this.m);// rpz le vecteur x dans l'algo
           valeur_propre_2 = transposition(v2,v);
           tabValPropre.add(valeur_propre_2);
           tabVectPropre.add(v2);
           deflation();
        }
        this.mvp=cree_matrice_vect_propre();
        
    }

    public Matrice getMvp() {
        return mvp;
    }
    
    public void deflation(){
        //
        Matrice m;
        m = sub(this.m, multiplicate(tabVectPropre.get(tabVectPropre.size()-1)));
        this.m = m;
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
    
    public Matrice sub(Matrice m, Matrice m2){
        System.out.println(m2.getLigne() + " c " + m2.getColonnes() + " m " + m.getLigne() + " c " + m.getColonnes());
        Matrice z = new Matrice(m.getLigne(),m.getColonnes());
        for (int i = 0; i < m.getLigne(); i++) {
            for (int j = 0; j < m.getColonnes(); j++) {
                z.setElement(i, j,m.getElement(i, j) - m2.getElement(i, j));
            }
        }
        return z;
    }
    
    public Matrice multiplicate (Vecteur v){
        System.out.println(v.getTaille()+ " taille ");
        Matrice m = new Matrice(v.getTaille(), v.getTaille());
         Vecteur test = new Vecteur(v.getTaille());
        double value = 0;
        for( int i = 0; i < test.getTaille(); i++){
            for (int j = 0; j < test.getTaille(); j++) {
                m.setElement(i, j, v.getElement(i) * v.getElement(j));
            }
        }
        return m;
    }
    
    public Vecteur multiplicate(Vecteur v, Matrice m){
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
    
    public Matrice cree_matrice_vect_propre(){
        Matrice mvp = new Matrice(this.tabVectPropre.get(0).getTaille(),this.tabVectPropre.get(0).getTaille());
        for(int i=0;i<mvp.getColonnes();i++){
            for(int j=0;j<mvp.getLigne();j++){
                mvp.setElement(j, i, tabVectPropre.get(i).getElement(j));
                
            }
        }
        mvp.aff_matrice();
        
        return mvp;
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
        double valPropre = 0;
        for(int i = 0; i < v.getTaille() ; i++){
            valPropre += v.getElement(i)*v2.getElement(i);
        }
        return valPropre;
    }
    
    public void affTab(){
        for (Vecteur v : tabVectPropre) {
            v.aff_vecteur();
        }
    }
}
