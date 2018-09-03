package es.cvjpy.utilidades.cobolcrear;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class UnaFD {

    private String nombreArchivo;
    private String nombre;
    private int nhijos;
    private CobolItem hijos[] = new CobolItem[1000];
    private MiST datos;
    private FileReader FR;
    private BufferedReader lector;
    private int maxlongitud = 0;
    private List laslineas = new ArrayList();
    private boolean pajava = false;
    private UnaSEL laSEL;

    public UnaFD(String s) {
        String re;
        nombreArchivo = s;
        try {
            FR = new FileReader(getNombreArchivo());
            datos = new MiST(getFR());

            this.nhijos = 0;
            while (!datos.isElfin()) {
                re = datos.sigpal();
                if (datos.getAntpal() != null) {
                    if (datos.getAntpal().equalsIgnoreCase("FD")) {
                        nombre = re;
                    }
                }
                if (datos.isPrimerodelinea() || datos.isPatras()) {
                    if (datos.nval == 1) {
                        hijos[nhijos] = new CobolItem(getDatos(), 1);
                        nhijos++;
                    }
                }
            }
            FR.close();
            lector = new java.io.BufferedReader(new FileReader(getNombreArchivo()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                laslineas.add(linea);
                if (linea.indexOf("JAVA SI") > -1) {
                    pajava = true;
                }
            }
            lector.close();

        } catch (IOException e) {
            System.out.println(e + nombreArchivo);
        }
    }

    public void vuelta1(CobolItem ci, int este) {
        int voypor = este;
        for (int i = 0; i < ci.getNhijos(); i++) {
            if (ci.getHijos()[i].isRedefines()) {
                ci.getHijos()[i].setLongitud(ci.getHijos()[i - 1].getLongitud());
                ci.getHijos()[i].setInicio(ci.getHijos()[i - 1].getInicio());
            } else {
                ci.getHijos()[i].setInicio(voypor);
                voypor = voypor + (ci.getHijos()[i].getLongitud() * ci.getHijos()[i].getVeces());
            }
        }
        for (int i = 0; i < ci.getNhijos(); i++) {
            vuelta1(ci.getHijos()[i], ci.getHijos()[i].getInicio());
        }
    }

    public void vuelta1() {
        for (int i = 0; i < getNhijos(); i++) {
            vuelta1(getHijos()[i], 0);
        }
    }

    public void vuelta2(CobolItem ci, int a, int ca, int b, int cb) {
        ci.setVecesA(a);
        ci.setCadaA(ca);
        ci.setVecesB(b);
        ci.setCadaB(cb);
        ci.setCadaC(ci.getLongitud());
        for (int i = 0; i < ci.getNhijos(); i++) {
            vuelta2(ci.getHijos()[i], a, ca, b, cb);
        }
    }

    public void vuelta2(CobolItem ci, int a, int ca) {
        ci.setVecesA(a);
        ci.setCadaA(ca);
        ci.setCadaB(ci.getLongitud());
        ci.setCadaC(ci.getLongitud());
        if (ci.getVeces() > 1) {
            for (int i = 0; i < ci.getNhijos(); i++) {
                vuelta2(ci.getHijos()[i], a, ca, ci.getVeces(), ci.getLongitud());
            }
        } else {
            for (int i = 0; i < ci.getNhijos(); i++) {
                vuelta2(ci.getHijos()[i], a, ca);
            }
        }
    }

    public void vuelta2(CobolItem ci) {
        ci.setCadaC(ci.getLongitud());
        if (ci.getVeces() > 1) {
            for (int i = 0; i < ci.getNhijos(); i++) {
                vuelta2(ci.getHijos()[i], ci.getVeces(), ci.getLongitud());
            }
        } else {
            for (int i = 0; i < ci.getNhijos(); i++) {
                vuelta2(ci.getHijos()[i]);
            }
        }
    }

    public void vuelta2() {
        for (int i = 0; i < getNhijos(); i++) {
            vuelta2(getHijos()[i]);
            if (getHijos()[i].getLongitud() > getMaxlongitud()) {
                setMaxlongitud(getHijos()[i].getLongitud());
            }
        }
    }

    private void vueltaparacampos(CobolItem ci) {
        for (int i = 0; i < ci.getNhijos(); i++) {
            /*
            if (!esalgo(ci.hijos[i])) {
            vueltaparacampos(ci.hijos[i]);
            }
             */
            esalgo(ci.getHijos()[i]);
            vueltaparacampos(ci.getHijos()[i]);
        }
    }

    public void vueltaparacampos() {
        for (int i = 0; i < getNhijos(); i++) {

            if (!esalgo(hijos[i])) {
                this.vueltaparacampos(getHijos()[i]);
            }
            /*
            esalgo(hijos[i]);
            vueltaparacampos(hijos[i]);
             */
        }
    }

    private boolean esalgo(CobolItem ci) {
        boolean ret = false;
        String sn = ci.getNombre();
        String linea;
        Iterator iter = getLaslineas().iterator();
        while (iter.hasNext()) {
            linea = (String) iter.next();
            if (linea.indexOf(sn) > -1) {
                if (linea.indexOf("*>FECHAR") > -1) {
                    ci.setTipo("R");
                    ret = true;
                }
                if (linea.indexOf("*>FECHAD") > -1) {
                    ci.setTipo("D");
                    ret = true;
                }
                if (linea.indexOf("*>CAMPO") > -1) {
                    ci.setTipo("C");
                    ret = true;
                }
                if (linea.indexOf("*>SINO") > -1) {
                    ci.setTipo("B");
                    ret = true;
                }
                break;
            }
        }
        if (isClave(ci)) {
            if (ci.getTipo() == null) {
                ci.setTipo("C");
            } else {
                if (ci.getTipo().trim().equals("")) {
                    ci.setTipo("C");
                }
            }
            ret = true;
        }
        return ret;
    }

    private boolean isClave(CobolItem ci) {
        boolean sino = false;
        if (getLaSEL().getLaclave().equals(ci.getNombre())) {
            sino = true;
        } else {
            for (int i = 0; i < getLaSEL().getNalternates(); i++) {
                if (getLaSEL().getAlternate()[i].equals(ci.getNombre())) {
                    sino = true;
                    break;
                }
            }
        }
        return sino;
    }

    private void cuentaenteros(CobolItem ci) {
        ci.setEnteros(ci.getLongitud() - ci.getDecimales());
        if (ci.isConsigno()) {
            ci.setEnteros(ci.getEnteros() - 1);
        }
        for (int i = 0; i < ci.getNhijos(); i++) {
            this.cuentaenteros(ci.getHijos()[i]);
        }
    }

    public void cuentaenteros() {
        for (int i = 0; i < getNhijos(); i++) {
            this.cuentaenteros(getHijos()[i]);
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

    public int getNhijos() {
        return nhijos;
    }

    public void setNhijos(int nhijos) {
        this.nhijos = nhijos;
    }

    public CobolItem[] getHijos() {
        return hijos;
    }

    public void setHijos(CobolItem[] hijos) {
        this.hijos = hijos;
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

    public BufferedReader getLector() {
        return lector;
    }

    public void setLector(BufferedReader lector) {
        this.lector = lector;
    }

    public int getMaxlongitud() {
        return maxlongitud;
    }

    public void setMaxlongitud(int maxlongitud) {
        this.maxlongitud = maxlongitud;
    }

    public List getLaslineas() {
        return laslineas;
    }

    public void setLaslineas(List laslineas) {
        this.laslineas = laslineas;
    }

    public boolean isPajava() {
        return pajava;
    }

    public void setPajava(boolean pajava) {
        this.pajava = pajava;
    }

    public UnaSEL getLaSEL() {
        return laSEL;
    }

    public void setLaSEL(UnaSEL laSEL) {
        this.laSEL = laSEL;
    }
}
