package es.cvjpy.utilidades.cobolcrear;

import java.io.FileReader;
import java.io.IOException;

public class UnaSEL {

    private String nombreArchivo;
    private String nombre;
    private String nombrenombre;
    private String laclave;
    private String camposClave[] = new String[16];
    private int ncamposClave = 0;
    private String elTexto;
    private String descripcionClave;
    private int nalternates;
    private String alternate[] = new String[256];
    private String camposAltenates[][] = new String[256][16];
    private int ncamposAltenates[] = new int[16];
    private String elTextos[] = new String[256];
    private String descripciones[] = new String[256];
    private String FileStatus;
    private MiST datos;
    private FileReader FR;
    private boolean esrelativo;
    private boolean esindexado;
    private String relativekey;

    public void muestra() {
        System.out.println(getNombreArchivo() + "," + getNombre() + "," + getNombrenombre() + "," + getLaclave() + "," + getElTexto() + "," + getDescripcionClave());
        System.out.println("campos de la clave");
        for (int i = 0; i < getNcamposClave(); i++) {
            System.out.print(getCamposClave()[i] + ",");
        }
        System.out.println("");
        System.out.println("alternates");
        for (int i = 0; i < getNalternates(); i++) {
            System.out.println(getAlternate()[i] + ", campos:");
            for (int j = 0; j < getNcamposAltenates()[i]; j++) {
                System.out.print(getCamposAltenates()[i][j] + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public UnaSEL(String s) {
        String re;
        nombreArchivo = s;
        nalternates = 0;
        esrelativo = false;
        esindexado = false;
        relativekey = "";

        try {
            FR = new FileReader(getNombreArchivo());
            datos = new MiST(getFR());

            while (!datos.isElfin()) {
                re = datos.sigpal();
                if (datos.getAntpal().equalsIgnoreCase("SELECT")) {
                    nombre = re;
                }
                if (datos.getAntpal().equalsIgnoreCase("ASSIGN")) {
                    nombrenombre = re;
                    re = datos.sigpal();
                    if (datos.getAntpal().equalsIgnoreCase("TO")) {
                        nombrenombre = re;
                        re = datos.sigpal();
                        if (datos.getAntpal().equalsIgnoreCase("RANDOM")) {
                            nombrenombre = re;
                        }
                    }
                }
                if (datos.getAntpal().equalsIgnoreCase("ALTERNATE")) {
                    esindexado = true;
                    alternate[nalternates] = re;
                    re = datos.sigpal();
                    if (datos.getAntpal().equalsIgnoreCase("RECORD")) {
                        alternate[nalternates] = re;
                        re = datos.sigpal();
                        if (datos.getAntpal().equalsIgnoreCase("KEY")) {
                            alternate[nalternates] = re;
                            re = datos.sigpal();
                            if (datos.getAntpal().equalsIgnoreCase("IS")) {
                                alternate[nalternates] = re;
                            }
                        }
                    }
                    if (datos.getAntpal().equalsIgnoreCase("KEY")) {
                        alternate[nalternates] = re;
                        re = datos.sigpal();
                        if (datos.getAntpal().equalsIgnoreCase("IS")) {
                            alternate[nalternates] = re;
                        }
                    }

                    if (!datos.getAntpal().equals("=")) {
                        camposAltenates[nalternates][ncamposAltenates[nalternates]++] = alternate[nalternates];
                    } else {
                        while (!re.equals("ALTERNATE")
                                && !re.equals("ASSIGN")
                                && !re.equals("LOCK")
                                && !re.equals("ORGANIZATION")
                                && !re.equals("ACCESS")
                                && !re.equals("WITH")
                                && !re.equals("PASSWORD")) {
                            camposAltenates[nalternates][ncamposAltenates[nalternates]++] =
                                    re;
                            re = datos.sigpal();
                        }
                    }
                    nalternates++;
                }
                if (datos.getAntpal().equalsIgnoreCase("RECORD")) {
                    re = datos.sigpal(); //No pongo aqui la clave porque se confunde con LOCK ON RECORD
                    //Asi que es necesario que exista RECORD KEY como minimo.
                    if (datos.getAntpal().equalsIgnoreCase("KEY")) {
                        esindexado = true;
                        laclave = re;
                        re = datos.sigpal();
                        if (datos.getAntpal().equalsIgnoreCase("IS")) {
                            laclave = re;


                            re = datos.sigpal();
                            if (!datos.getAntpal().equals("=")) {
                                camposClave[ncamposClave++] = laclave;
                            } else {
                                while (!re.equals("ALTERNATE")
                                        && !re.equals("ASSIGN")
                                        && !re.equals("LOCK")
                                        && !re.equals("ORGANIZATION")
                                        && !re.equals("ACCESS")
                                        && !re.equals("WITH")
                                        && !re.equals("PASSWORD")) {
                                    camposClave[ncamposClave++] = re;
                                    re = datos.sigpal();
                                }
                            }


                        }
                    }
                }
                if (datos.getAntpal().equalsIgnoreCase("RELATIVE")) {
                    re = datos.sigpal();
                    esrelativo = true;
                    if (datos.getAntpal().equalsIgnoreCase("KEY")) {
                        relativekey = re;
                        laclave = relativekey;
                        re = datos.sigpal();
                        if (datos.getAntpal().equalsIgnoreCase("IS")) {
                            relativekey = re;
                            laclave = relativekey;
                        }
                    }

                }

            }
        } catch (IOException e) {
            System.out.println(e + nombreArchivo);
        }
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombrenombre() {
        return nombrenombre;
    }

    public void setNombrenombre(String nombrenombre) {
        this.nombrenombre = nombrenombre;
    }

    public String getLaclave() {
        return laclave;
    }

    public void setLaclave(String laclave) {
        this.laclave = laclave;
    }

    public String[] getCamposClave() {
        return camposClave;
    }

    public void setCamposClave(String[] camposClave) {
        this.camposClave = camposClave;
    }

    public int getNcamposClave() {
        return ncamposClave;
    }

    public void setNcamposClave(int ncamposClave) {
        this.ncamposClave = ncamposClave;
    }

    public String getElTexto() {
        return elTexto;
    }

    public void setElTexto(String elTexto) {
        this.elTexto = elTexto;
    }

    public String getDescripcionClave() {
        return descripcionClave;
    }

    public void setDescripcionClave(String descripcionClave) {
        this.descripcionClave = descripcionClave;
    }

    public int getNalternates() {
        return nalternates;
    }

    public void setNalternates(int nalternates) {
        this.nalternates = nalternates;
    }

    public String[] getAlternate() {
        return alternate;
    }

    public void setAlternate(String[] alternate) {
        this.alternate = alternate;
    }

    public String[][] getCamposAltenates() {
        return camposAltenates;
    }

    public void setCamposAltenates(String[][] camposAltenates) {
        this.camposAltenates = camposAltenates;
    }

    public int[] getNcamposAltenates() {
        return ncamposAltenates;
    }

    public void setNcamposAltenates(int[] ncamposAltenates) {
        this.ncamposAltenates = ncamposAltenates;
    }

    public String[] getElTextos() {
        return elTextos;
    }

    public void setElTextos(String[] elTextos) {
        this.elTextos = elTextos;
    }

    public String[] getDescripciones() {
        return descripciones;
    }

    public void setDescripciones(String[] descripciones) {
        this.descripciones = descripciones;
    }

    public String getFileStatus() {
        return FileStatus;
    }

    public void setFileStatus(String FileStatus) {
        this.FileStatus = FileStatus;
    }

    public MiST getDatos() {
        return datos;
    }

    public void setDatos(MiST datos) {
        this.datos = datos;
    }

    public FileReader getFR() {
        return FR;
    }

    public void setFR(FileReader FR) {
        this.FR = FR;
    }

    public boolean isEsrelativo() {
        return esrelativo;
    }

    public void setEsrelativo(boolean esrelativo) {
        this.esrelativo = esrelativo;
    }

    public boolean isEsindexado() {
        return esindexado;
    }

    public void setEsindexado(boolean esindexado) {
        this.esindexado = esindexado;
    }

    public String getRelativekey() {
        return relativekey;
    }

    public void setRelativekey(String relativekey) {
        this.relativekey = relativekey;
    }
}
