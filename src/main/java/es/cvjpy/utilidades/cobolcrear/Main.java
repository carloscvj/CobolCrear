/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.utilidades.cobolcrear;

import java.io.File;

/**
 *
 * @author carlos
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Haciendo hac=new Haciendo();
        hac.run(new File("lyb/it/FIT47-FD"), ".", "IMP", "src", "lyb");
    }
}
