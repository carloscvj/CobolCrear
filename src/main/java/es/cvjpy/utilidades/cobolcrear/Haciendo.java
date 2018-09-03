package es.cvjpy.utilidades.cobolcrear;

import java.io.*;

public class Haciendo {

    public Haciendo() {
    }

    private void pinta(File file) {
        System.out.println(file.getAbsolutePath());
    }

    public void run(File fileFD, String baseDir, String cualHago, String carpetaSrc, String carpetaLyb) throws Exception {

        FicheroCobol mifc;
        mifc = new FicheroCobol(fileFD.getAbsolutePath(), baseDir, carpetaSrc, carpetaLyb);

        mifc.getLaFD().vuelta1();
        mifc.getLaFD().vuelta2();
        mifc.getLaFD().setLaSEL(mifc.getLaSEL());    //Para utilizarla en vueltaparacampos();
        mifc.getLaFD().vueltaparacampos();
        mifc.getLaFD().cuentaenteros();

        if (cualHago.equals("API")) {
            //mifc.deUsar.getParentFile().mkdirs(); //Ya no se usa
            //mifc.crearUsar();

            if (mifc.getDeInterfaceBean().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeInterfaceBean());
                mifc.getDeInterfaceBean().getParentFile().mkdirs();
                mifc.crearInterfaceBean();
            }

            if (mifc.getDeInterfaceFichero().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeInterfaceFichero());
                mifc.getDeInterfaceFichero().getParentFile().mkdirs();
                mifc.crearInterfaceFichero();
            }

        }

        if (cualHago.equals("IMP")) {
            if (mifc.getDeRegistro().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeRegistro());
                mifc.getDeRegistro().getParentFile().mkdirs();
                mifc.crearRegistro();
            }

            if (mifc.getDeBean().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeBean());
                mifc.getDeBean().getParentFile().mkdirs();
                mifc.crearBean();
            }

            if (mifc.getDeFichero().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeFichero());
                mifc.getDeFichero().getParentFile().mkdirs();
                mifc.crearFichero();
            }

            if (mifc.getDeFICHEROX().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeFICHEROX());
                mifc.getDeFICHEROX().getParentFile().mkdirs();
                mifc.crearFICHEROX();
            }
        }

        if (cualHago.equals("SRV")) {
            if (mifc.getDeWO().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeWO());
                mifc.getDeWO().getParentFile().mkdirs();
                mifc.crearWO();
            }

            if (mifc.getDeDEC().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeDEC());
                mifc.getDeDEC().getParentFile().mkdirs();
                mifc.crearDEC();
            }

            if (mifc.getDeA().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeA());
                mifc.getDeA().getParentFile().mkdirs();
                mifc.crearA();
            }

            if (mifc.getDeT().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeT());
                mifc.getDeT().getParentFile().mkdirs();
                mifc.crearT();
            }

            if (mifc.getDeCOBOL().lastModified() < fileFD.lastModified()) {
                pinta(mifc.getDeCOBOL());
                mifc.getDeCOBOL().getParentFile().mkdirs();
                mifc.crearCBL();
            }
        }

    }

    public void clean(File fileFD, String baseDir, String cualHago, String carpetaSrc, String carpetaLyb) throws Exception {

        FicheroCobol mifc;
        mifc = new FicheroCobol(fileFD.getAbsolutePath(), baseDir, carpetaSrc, carpetaLyb);

        mifc.getLaFD().vuelta1();
        mifc.getLaFD().vuelta2();
        mifc.getLaFD().setLaSEL(mifc.getLaSEL());    //Para utilizarla en vueltaparacampos();
        mifc.getLaFD().vueltaparacampos();
        mifc.getLaFD().cuentaenteros();

        if (cualHago.equals("API")) {
            mifc.getDeInterfaceBean().delete();
            mifc.getDeInterfaceFichero().delete();
        }

        if (cualHago.equals("IMP")) {
            mifc.getDeRegistro().delete();
            mifc.getDeBean().delete();
            mifc.getDeFichero().delete();
            mifc.getDeFICHEROX().delete();
        }

        if (cualHago.equals("SRV")) {
            mifc.getDeWO().delete();
            mifc.getDeDEC().delete();
            mifc.getDeA().delete();
            mifc.getDeT().delete();
            mifc.getDeCOBOL().delete();
        }

    }

}
