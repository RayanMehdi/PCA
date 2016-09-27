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
public class PCA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Test t = new Test();
        CalculMatriciel c = new CalculMatriciel(t.getMatr(), t.getVect(), 0.0001);
        c.calcul_valeurpropre();
    }
    
}
