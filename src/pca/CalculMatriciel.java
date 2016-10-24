/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pca;

//Classe eprmettant d'effectuer les calculs
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rayanmehdi1
 */
public class CalculMatriciel {

    //Attributs
    private Matrice m;
    private Matrice mvp;
    private Vecteur v;
    private Vecteur vecteur_base;
    private double pourcentageTrace;
    private ArrayList<Double> tabValPropre;
    private ArrayList<Vecteur> tabVectPropre;
    private ArrayList<Double> tabMoyenne;
    private ArrayList<Double> tabEcartType;

    //initialise m et v ainsi q'une variable arret
    public CalculMatriciel(Matrice m, Vecteur v, double pourcentageTrace) {
        this.m = m;
        this.v = v;
        this.vecteur_base = v;
        this.pourcentageTrace = pourcentageTrace;
        tabValPropre = new ArrayList();
        tabVectPropre = new ArrayList<>();
        tabMoyenne = new ArrayList<>();
        tabEcartType = new ArrayList<>();
    }

    /* commence l'algo
     -prendre une matrice
     -prendre un vecteur (commencé par 1...1)
     -while |v1 - v2| > arret
     -multiplié matrice avec vecteur
     -v = v/norm(v) 
     -retourné au while
     */
    //pour deflation dsds
    /*
     A faire : prendre la fonction deflation
     utiliser la nouvelle matrice de deflation dans l'algo
     pour la valeur de while, calculer la trace et utilisé une aray list composé
     des valeurs propres. Si 90% de la somme de ces valeurs propres est atteinte 
     (trace = 100% des valeurs propres)
     */
    public void calcul_valeurpropre() {//permet de calculer le vecteur propre et la valeur propre de la matrice
        Vecteur v2 = (Vecteur) v.clone();
        int i = 0;
        //m.aff_matrice();
        centrer_reduire();
        this.m = multiplicate(this.m, transposition(this.m));
        double trace = calcul_trace(this.m);
        System.out.println("trace : " + trace);
        //m.aff_matrice();
        while (sumTab() <= trace * pourcentageTrace) {
            calcul_plus_grand_vecteur_propre(v2);
            deflation();
            i++;

        }
        System.out.println("Nombre vecteur = " + i);
        //tabVectPropre.get(0).aff_vecteur();
        this.mvp = cree_matrice_vect_propre();
        this.mvp = multiplicate(this.m, this.mvp);
        //matrice2image(ecart_type(), trace);
        this.mvp.creerImage();
        this.mvp.aff_matrice();
        //matrice2image(trace, trace);

    }

    public Matrice getMvp() {
        return mvp;
    }

    public void deflation() {
        Matrice m;
        m = sub(this.m, multiplicate(multiplicate(tabVectPropre.get(tabVectPropre.size() - 1)), tabValPropre.get(tabValPropre.size() - 1)));
        this.m = m;
    }

    public double sumTab() {
        double sum = 0;
        for (Double d : tabValPropre) {
            sum += d;
        }
        return sum;
    }

    public double calcul_trace(Matrice m) {
        double trace = 0;
        for (int i = 0; i < m.getColonnes(); i++) {
            System.out.println(m.getElement(i, i));
            trace += m.getElement(i, i);
        }
        return trace;
    }

    public Matrice sub(Matrice m, Matrice m2) {
        Matrice z = new Matrice(m.getLigne(), m.getColonnes());
        for (int i = 0; i < m.getLigne(); i++) {
            for (int j = 0; j < m.getColonnes(); j++) {
                z.setElement(i, j, m.getElement(i, j) - m2.getElement(i, j));
            }
        }
        return z;
    }
    public Matrice add(Matrice m,  double valeur) {
        Matrice z = new Matrice(m.getLigne(), m.getColonnes());
        for (int i = 0; i < m.getLigne(); i++) {
            for (int j = 0; j < m.getColonnes(); j++) {
                z.setElement(i, j, m.getElement(i, j) + valeur);
            }
        }
        return z;
    }
    public Matrice multiplicate(Matrice m, double val) {
        Matrice ret = new Matrice(m.getLigne(), m.getColonnes());
        for (int i = 0; i < m.getColonnes(); i++) {
            for (int j = 0; j < m.getLigne(); j++) {
                ret.setElement(i, j, val * m.getElement(i, j));
            }
        }
        return ret;
    }

    public Matrice multiplicate(Vecteur v) {
        Matrice m = new Matrice(v.getTaille(), v.getTaille());
        Vecteur test = new Vecteur(v.getTaille());
        double value = 0;
        for (int i = 0; i < test.getTaille(); i++) {
            for (int j = 0; j < test.getTaille(); j++) {
                m.setElement(i, j, v.getElement(i) * v.getElement(j));
            }
        }
        return m;
    }

    public Vecteur multiplicate(Vecteur v, Matrice m) {
        Vecteur test = new Vecteur(v.getTaille());
        double value = 0;
        for (int i = 0; i < test.getTaille(); i++) {
            value = 0;
            for (int k = 0; k < this.m.getColonnes(); k++) {
                value += v.getElement(k) * m.getElement(i, k);
            }
            //System.out.println("res="+value);
            test.setElement(i, value);
        }
        return test;
    }

    public Matrice multiplicate(Matrice m, Matrice m2) {
        Matrice mReturn = new Matrice(m.getLigne(), m.getColonnes());
        double somme = 0;
        for (int i = 0; i < m.getLigne(); i++) {
            for (int j = 0; j < m2.getColonnes(); j++) {
                somme = 0;
                for (int k = 0; k < m.getColonnes(); k++) {
                    mReturn.setElement(i, j, mReturn.getElement(i, j) + m.getElement(i, k) * m2.getElement(k, j));
                    //somme += m.getElement(i, k) * m2.getElement(k, j);
                    //System.out.println("SOMME   " + somme);
                }
                //System.out.println("PROUT " + mReturn.getElement(i, j));
                //mReturn.setElement(i, j, somme);
                somme = 0;
            }
        }
        return mReturn;
    }

    public Matrice cree_matrice_vect_propre() {
        Matrice mvp = new Matrice(this.tabVectPropre.get(0).getTaille(), this.tabVectPropre.size());
        for (int j = 0; j < mvp.getColonnes(); j++) {
            for (int k = 0; k < mvp.getLigne(); k++) {
                mvp.setElement(k, j, tabVectPropre.get(j).getElement(k));
            }
        }
        mvp.aff_matrice();
        return mvp;
    }

    public Vecteur normalise(Vecteur v) {
        double norme = 0;
        Vecteur vNorme = new Vecteur(v.getTaille());
        for (int i = 0; i < v.getTaille(); i++) {
            norme += v.getElement(i) * v.getElement(i);
        }
        norme = Math.sqrt(norme);
        for (int i = 0; i < v.getTaille(); i++) {
            vNorme.setElement(i, v.getElement(i) / norme);
        }
        return vNorme;
    }

    public double produitScalaire(Vecteur v, Vecteur v2) {
        double valPropre = 0;
        for (int i = 0; i < v.getTaille(); i++) {
            valPropre += v.getElement(i) * v2.getElement(i);
        }
        return valPropre;
    }

    public Matrice transposition(Matrice m) {
        int ligne = m.getLigne();
        int colonnes = m.getColonnes();
        Matrice transpose = new Matrice(ligne, colonnes);
        for (int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonnes; j++) {
                transpose.setElement(i, j, m.getElement(j, i));
            }
        }

        return transpose;
    }

    public void affTab() {
        for (Vecteur v : tabVectPropre) {
            v.aff_vecteur();
        }
    }

    public double moyenne(int col) {
        double moy = 0.0;
        for (int i = 0; i < this.m.getLigne(); i++) {
            moy += this.m.getElement(i, col);
        }
        moy /= this.m.getLigne();

        return moy;
    }

    public double variance(int col) {
        double var = 0.0;
        double moy = this.moyenne(col);
        for (int i = 0; i < this.m.getLigne(); i++) {
            var += ((this.m.getElement(i, col) - moy) * (this.m.getElement(i, col) - moy));
        }
        var /= this.m.getLigne();
        return var;
    }

    public double ecart_type(int col) {
        return Math.sqrt(this.variance(col));
    }

    public void centrer_reduire() {
        double new_value = 0;
        for (int j = 0; j < this.m.getColonnes(); j++) {
            double moy = moyenne(j);
            double ec = ecart_type(j);
            this.tabMoyenne.add(moy);
            this.tabEcartType.add(ec);
            System.out.println("moy"+j+" = "+moy);
            System.out.println("ec"+j+" = "+ec);
            if (ec != 0) {
                for (int i = 0; i < this.m.getLigne(); i++) {
                    new_value = (this.m.getElement(i, j) - moy) / ec;
                    this.m.setElement(i, j, new_value);
                }
            } else {
                for (int i = 0; i < this.m.getLigne(); i++) {
                    new_value = (this.m.getElement(i, j) - moy);
                    this.m.setElement(i, j, new_value);
                }
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

    public double moyenne(Vecteur v) {
        double m = 0;
        for (int i = 0; i < v.getTaille(); i++) {
            m += v.getElement(i);
        }
        return m / v.getTaille();
    }

    public void calcul_plus_grand_vecteur_propre(Vecteur v2) {
        int i = 0;
        double valeur_propre = 1, valeur_propre_2 = 0;
        /*
        System.out.println("Matrice : ");
        m.aff_matrice();
         System.out.println("vect base");*/
        vecteur_base= m.random();
       
        //vecteur_base.aff_vecteur();
        v = (Vecteur) vecteur_base.clone();
        v.setElement(0, 40);
        v2 = (Vecteur) vecteur_base.clone();
        while (Math.abs(valeur_propre_2 - valeur_propre) > 0.00000000001 /*|| !vecteur_propre(v2)*/) {
            valeur_propre = valeur_propre_2;
            v2 = normalise(v); //rpz le vecteur B dans lalgo
            //System.out.println("v2=");
            //v2.aff_vecteur();
            v = multiplicate(v2, this.m);// rpz le vecteur x dans l'algo
            //System.out.println("new v=");
            //v.aff_vecteur();
            //System.out.println("vp_i="+valeur_propre);
            valeur_propre_2 = produitScalaire(v, v2);
            //System.out.println("vp_i+1="+valeur_propre_2);
            //System.out.println("v2=");
            //v2.aff_vecteur();
            i++;
        }
        tabValPropre.add(valeur_propre_2);
        tabVectPropre.add(v2);
        System.out.println("nb ite" + i);
        System.out.println(valeur_propre_2);

    }

    public boolean vecteur_propre(Vecteur v) {
        double val = 0;
        for (int i = 0; i < v.getTaille(); i++) {
            val += v.getElement(i) * v.getElement(i);
        }
        if (sqrt(val) == 1) {
            return true;
        }
        return false;
    }
    public void matrice2image(double ecart_type,double moyenne){
        multiplicate(mvp, ecart_type);
        add(mvp, moyenne);
        //remplir_zéro(mvp);
        mvp.aff_matrice();
    }
    
    public void remplir_zéro(Matrice m){
        Matrice m_new = new Matrice(this.m.getColonnes(), this.m.getColonnes());
        for (int i = 0; i < this.m.getColonnes(); i++) {
            for (int j = 0; j < this.m.getLigne(); j++) {
                m_new.setElement(i, j, 0);
            }
            
        }
        
        for (int i = 0; i < m.getLigne(); i++) {
            for (int j = 0; j < m.getColonnes(); j++) {
                m_new.setElement(i, j, m.getElement(i, j));
            }
        }
    }
    
}
