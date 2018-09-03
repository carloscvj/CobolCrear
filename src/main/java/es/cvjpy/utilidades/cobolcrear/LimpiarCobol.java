/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.utilidades.cobolcrear;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.resources.FileResource;

/**
 *
 * @author carlos
 */
public class LimpiarCobol extends Task implements Serializable {

    private String baseDir=".";
    private String cualHago;
    private String carpetaSrc="src";
    private String carpetaLyb="lyb";
    private List<FileSet> filesets = new ArrayList();

    public void addFileset(FileSet fileset) {
        getFilesets().add(fileset);
    }

    @Override
    public void execute() {
        log("-------------------------limpiando fuentes COBOL-----------------------------------");
        for (FileSet unF : getFilesets()) {
            Iterator iter = unF.iterator();
            while (iter.hasNext()) {
                Object obj = iter.next();
                FileResource uno = (FileResource) obj;
                //log(uno.getFile().getAbsolutePath()); //YA pintaremos si lo hacemos
                try {
                    //Poner qui el hacedor
                    Haciendo hac = new Haciendo();
                    hac.clean(uno.getFile(), getBaseDir(), getCualHago(), getCarpetaSrc(), getCarpetaLyb()); //ste se encarga de mirar los lastModified de los ficheros.
                } catch (Exception ex) {
                    log(ex.toString(), Project.MSG_ERR);
                }
            }
        }
        log("-------------------------fin limpiando fuentes COBOL-------------------------------");
    }

    public List<FileSet> getFilesets() {
        return filesets;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getCualHago() {
        return cualHago;
    }

    public void setCualHago(String cualHago) {
        this.cualHago = cualHago;
    }

    public String getCarpetaSrc() {
        return carpetaSrc;
    }

    public void setCarpetaSrc(String carpetaSrc) {
        this.carpetaSrc = carpetaSrc;
    }

    public String getCarpetaLyb() {
        return carpetaLyb;
    }

    public void setCarpetaLyb(String carpetaLyb) {
        this.carpetaLyb = carpetaLyb;
    }
}
