package es.cvjpy.utilidades.cobolcrear;

import java.io.IOException;

public final class CobolItem {

    private int nivel;
    private String nombre;
    private String tipo = null;
    private int inicio;
    private int longitud;
    private int enteros;
    private int decimales;
    private int vecesA;
    private int cadaA;
    private int vecesB;
    private int cadaB;
    private int veces;
    private int cadaC;
    private boolean numero;
    private boolean consigno;
    private boolean separado;
    private boolean redefines;
    private String redefinida;
    private int nhijos;
    private CobolItem hijos[] = new CobolItem[1000];

    @Override
    public String toString() {
        return "CobolItem{" + "nombre=" + nombre + ", nhijos=" + nhijos + '}';
    }

    public CobolItem(MiST datos, int n) {
        try {
            this.nivel = n;
            this.nombre = datos.sigpal();
            this.inicio = 0;
            this.longitud = 0;
            this.enteros = 0;
            this.decimales = 0;
            this.vecesA = 1;
            this.cadaA = 0;
            this.vecesB = 1;
            this.cadaB = 0;
            this.veces = 1;
            this.cadaC = 0;
            this.nhijos = 0;
            this.tipo = "";
            this.numero = false;
            this.consigno = false;
            this.separado = false;
            this.redefines = false;
            this.redefinida = "";
            String re;
            while (!datos.isElfin()) {
                re = datos.sigpal();
                if (datos.isPrimerodelinea() || datos.isPatras()) {
                    datos.setPatras(false);
/// ----EMN
/// Corregir falta de campos en los ficheros tratados
///          if( datos.nval>this.nivel && datos.nval!=88) {
                    if (datos.nval > this.nivel) {
                        // Cuando consumo el principio de linea, lo desmarco
                        datos.setPrimerodelinea(false);
                        if (datos.nval != 88) {
/// Fin EMN
                            hijos[nhijos] = new CobolItem(datos, (int) datos.nval);
                            this.longitud = this.longitud
                                    + (this.hijos[nhijos].longitud * this.hijos[nhijos].veces);
                            if (this.redefines) {
                                this.longitud = 0;
                            }
                            nhijos++;
                        } else {
/// ----EMN
/// Corregir falta de campos en los ficheros tratados
                            while (!datos.isPrimerodelinea()) {
                                re = datos.sigpal();
                            }
                            datos.pushBack();
                            datos.setPatras(true);
                            continue;
/// Fin EMN
                        }
                    } else {
                        datos.pushBack();
                        datos.setPatras(true);
                        break;
                    }
                }

                if (datos.getAntpal().equalsIgnoreCase("PIC")) {
                    this.tipo = datos.sval.substring(0, 1);
                    if (this.tipo.equals("9")) {
                        this.numero = true;
                    }
                    if (this.tipo.equals("S")) {
                        this.numero = true;
                        this.consigno = true;
                    }
                    this.longitud = this.longitud + datos.sval.length();
                    //if (this.consigno) this.longitud--;
                    int bi = datos.sval.indexOf('V');
                    if (bi != -1) {
                        this.longitud--;
                        String s;
                        s = datos.sval.substring(bi + 1);
                        this.decimales = s.length();
                    }
                    if (this.redefines) {
                        this.longitud = 0;
                    }
                }
                if (datos.getAntpal().equalsIgnoreCase("SIGN")) {
                    this.separado = true;
                    //this.longitud++;
                    if (this.redefines) {
                        this.longitud = 0;
                    }
                }
                if (datos.getAntpal().equalsIgnoreCase("REDEFINES")) {
                    this.redefinida = datos.sval;
                    this.redefines = true;
                    this.longitud = 0;
                }

                if (datos.getAntpal().equalsIgnoreCase("(")) {
                    this.longitud = this.longitud + (int) datos.nval - 1;
                    //if( this.consigno ) this.longitud--;
                    if (this.redefines) {
                        this.longitud = 0;
                    }
                }
                if (datos.getAntpal().equalsIgnoreCase(")")) {
                    int bi = datos.sval.indexOf('V');
                    if (bi != -1) {
                        String s;
                        s = datos.sval.substring(bi + 1);
                        this.decimales = s.length();
                        this.longitud = this.longitud + s.length();
                    }
                    if (this.redefines) {
                        this.longitud = 0;
                    }
                }
                if (datos.getAntpal().equalsIgnoreCase("OCCURS")) {
                    this.veces = (int) datos.nval;
                    if (this.getVecesA() == 1) {
                        this.setVecesA(getVeces());
                    }
                }

                if (re.equalsIgnoreCase("FECHAR")) {
                    System.out.println(this.nombre + " FECHAR VISTO");
                    this.tipo = "R";
                }
                if (re.equalsIgnoreCase("FECHAD")) {
                    System.out.println(this.nombre + " FECHAD VISTO");
                    this.tipo = "D";
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getEnteros() {
        return enteros;
    }

    public void setEnteros(int enteros) {
        this.enteros = enteros;
    }

    public int getDecimales() {
        return decimales;
    }

    public void setDecimales(int decimales) {
        this.decimales = decimales;
    }

    public int getVecesA() {
        return vecesA;
    }

    public void setVecesA(int vecesA) {
        this.vecesA = vecesA;
    }

    public int getCadaA() {
        return cadaA;
    }

    public void setCadaA(int cadaA) {
        this.cadaA = cadaA;
    }

    public int getVecesB() {
        return vecesB;
    }

    public void setVecesB(int vecesB) {
        this.vecesB = vecesB;
    }

    public int getCadaB() {
        return cadaB;
    }

    public void setCadaB(int cadaB) {
        this.cadaB = cadaB;
    }

    public int getVeces() {
        return veces;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }

    public int getCadaC() {
        return cadaC;
    }

    public void setCadaC(int cadaC) {
        this.cadaC = cadaC;
    }

    public boolean isNumero() {
        return numero;
    }

    public void setNumero(boolean numero) {
        this.numero = numero;
    }

    public boolean isConsigno() {
        return consigno;
    }

    public void setConsigno(boolean consigno) {
        this.consigno = consigno;
    }

    public boolean isSeparado() {
        return separado;
    }

    public void setSeparado(boolean separado) {
        this.separado = separado;
    }

    public boolean isRedefines() {
        return redefines;
    }

    public void setRedefines(boolean redefines) {
        this.redefines = redefines;
    }

    public String getRedefinida() {
        return redefinida;
    }

    public void setRedefinida(String redefinida) {
        this.redefinida = redefinida;
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
}
