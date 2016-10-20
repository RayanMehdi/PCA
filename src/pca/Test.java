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
public class Test {

    Image imag = new Image("test10.png");

    public Test(){
        
        Matrice m1 = new Matrice(3, 3);
        for( int i = 0; i < m1.getColonnes(); i++ ){
               for( int j = 0; j < m1.getLigne(); j++ ){
                   m1.setElement(j, i, 2);
               }
        }
        Matrice m2 = new Matrice(3, 3);
        
        m2.setElement(0, 0, 1);
        m2.setElement(1, 0, 4);
        m2.setElement(0, 1, 4);
        m2.setElement(2, 0, 5);
        m2.setElement(0, 2, 5);
        m2.setElement(1, 2, 6);
        m2.setElement(2, 1, 6);
        m2.setElement(1, 1, 2);
        m2.setElement(2, 2, 3);
        
        m1 = multiplicate(m1,m2);
        
        Matrice m=imag.MatriceNoirBlanc();
        
        Vecteur v = new Vecteur(m.getLigne());
        v.random();
                

        CalculMatriciel c = new CalculMatriciel(MatriceRandom(), v, 0.9);
        c.calcul_valeurpropre();
        //c.affTab();
        //c.getMvp().creerImage();
        
        //v.aff_vecteur();

       // v.aff_vecteur();

        
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
    public Matrice MatriceRandom(){
        Matrice m = new Matrice(10, 10);
        for(int i=0;i<m.getLigne();i++){
            for(int j=0;j<m.getColonnes();j++){
                m.setElement(i, j, Math.random()*255);
            }
        }
        m.aff_matrice();
        return m;
    }
    
}
