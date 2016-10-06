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
        Vecteur v2 = (Vecteur)v.clone();
        int i= 0;
        double valeur_propre = 1, valeur_propre_2 = 0;
        this.m.aff_matrice();
        System.out.println("GOOOOOOOOOOO");
        centrer_reduire();
        System.out.println("PROOOOOOOOOOOOOOOOOOOOOOOTTTT");
        this.m = multiplicate(this.m, transposition(this.m));
        this.m.aff_matrice();
        System.out.println("FIIIIIIIIIIIIN");
        //while( Math.abs(valeur_propre - valeur_propre_2)> arret){
        double trace = calcul_trace();
        System.out.println(trace);
        while( sumTab() <= trace*pourcentageTrace){
           valeur_propre = valeur_propre_2;
           v2 = norme_vecteur(v2); //rpz le vecteur B dans lalgo
           v = multiplicate(v2, this.m);// rpz le vecteur x dans l'algo
           valeur_propre_2 = transposition(v2,v);
           tabValPropre.add(Math.abs(valeur_propre_2));
           tabVectPropre.add(v2);
           deflation();
           i++;
            System.out.println("nb ite"+ i);
            System.out.println(valeur_propre_2);
        }
        //tabVectPropre.get(0).aff_vecteur();
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
        double trace = 0;
        for( int i = 0; i < m.getColonnes(); i++){
            for (int j = 0; j < m.getLigne(); j++) {
                if( i == j )
                    trace += Math.abs(m.getElement(i, j));
            }
        }
        return trace;
    }
    
    public Matrice sub(Matrice m, Matrice m2){
        Matrice z = new Matrice(m.getLigne(),m.getColonnes());
        for (int i = 0; i < m.getLigne(); i++) {
            for (int j = 0; j < m.getColonnes(); j++) {
                z.setElement(i, j,m.getElement(i, j) - m2.getElement(i, j));
            }
        }
        return z;
    }
    
    public Matrice multiplicate (Vecteur v){
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
    
    public Matrice multiplicate(Matrice m, Matrice m2){
        Matrice mReturn = new Matrice( m.getLigne(), m.getColonnes());
        double somme =0;
        for (int i = 0; i < m.getLigne(); i++) {
            for (int j = 0; j < m.getColonnes(); j++) {
                for (int k = 0; k < m.getColonnes(); k++) {
                    somme += m.getElement(i, k) * m2.getElement(k, i);
                }
                mReturn.setElement(j, i, somme);
                somme = 0;
            }
        }
        return mReturn;
    }
    
    public Matrice cree_matrice_vect_propre(){
        Matrice mvp = new Matrice(this.tabVectPropre.get(0).getTaille(),this.tabVectPropre.size());
        for(int j=0; j<mvp.getColonnes();j++){
            for (int k = 0; k < mvp.getLigne(); k++) {
               mvp.setElement(k, j, tabVectPropre.get(j).getElement(k));
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
    public Matrice transposition(Matrice m){
        int ligne = m.getLigne();
        int colonnes = m.getColonnes();
        Matrice transpose = new Matrice(ligne, colonnes);
        for(int i=0; i < ligne; i++){
            for(int j=0; j < colonnes ;j++){
               transpose.setElement(i,j,m.getElement(j, i));
            }
        }
        return transpose;
    }
    
    public void affTab(){
        for (Vecteur v : tabVectPropre) {
            v.aff_vecteur();
        }
    }
    public double moyenne(int col){
        double moy = 0.0;
        for(int i=0; i < this.m.getLigne(); i++){
            moy += this.m.getElement(i, col);
        }
        moy /= this.m.getLigne();
    
    return moy;
    }
    
    public double variance(int col){
        double var = 0.0;
        double moy = this.moyenne(col);
        for(int i=0; i < this.m.getLigne(); i++){
            var+=(this.m.getElement(i, col) - moy) * (this.m.getElement(i, col) - moy);
        }
        var /= this.m.getLigne();
        return var;
    }
    
    public double ecart_type(int col){
        return sqrt(this.variance(col));
    }
    
    public void centrer_reduire(){
        for(int i=0;i<this.m.getLigne();i++){
            for(int j=0;j<this.m.getColonnes();j++){
                double new_value=0.0;
                new_value =(this.m.getElement(i, j)-moyenne(j))/ecart_type(j);
                this.m.getElement(i, j);
            }
        }
        
    }
    
    
    

    public double covariance(Vecteur v1, Vecteur v2) {
        if (v1.getTaille() != v2.getTaille()) {
            System.out.println("Probleme, la taille des deux vecteurs pour le calcul de covariance est différent.");
            return 2;
        } else {
            double xm = moyenne(v1);
            double ym = moyenne(v2);
            double value1 = 0, value2 = 0;
            for (int i = 0; i < v1.getTaille(); i++) {
                value1 += v1.getElement(i) - xm;
                value2 += v2.getElement(i) - ym;
            }
            return value1 * value2 * (1 / v1.getTaille());
        }
    }

    public double moyenne(Vecteur v){
        double m = 0;
        for (int i = 0; i < v.getTaille(); i++) {
            m += v.getElement(i);
        }
        return m/v.getTaille();
    }
    
}
