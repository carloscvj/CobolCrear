/*
 * DefinirCampos.java
 *
 * Created on 8 de marzo de 2007, 18:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package es.cvjpy.utilidades.cobolcrear;

import java.io.PrintWriter;

public class DefinirCampos {

    private PrintWriter escritor;

    public DefinirCampos(PrintWriter w) {
        this.escritor = w;
    }

    private void colocaveces(CobolItem aeste) {
        if (aeste.getVecesA() == 1) {
            if (aeste.getVeces() > 1) {
                aeste.setVecesA(aeste.getVeces());
                aeste.setVeces(1);
            }
        }
        if (aeste.getVecesB() == 1) {
            if (aeste.getVeces() > 1) {
                aeste.setVecesB(aeste.getVeces());
                aeste.setVeces(1);
            }
        }
    }

    private void escribedefine(CobolItem este) {
        String cuadraditos = "";
        String sn = este.getNombre().toLowerCase().replace('-', '_');
        int tipo = 0;
        if (!sn.equals("filler")) {
            if (este.getVecesA() > 1) {
                cuadraditos = cuadraditos + "[]";
            }
            if (este.getVecesB() > 1) {
                cuadraditos = cuadraditos + "[]";
            }
            if (este.getVeces() > 1) {
                cuadraditos = cuadraditos + "[]";
            }
            if (este.getTipo().equals("9")) {
                tipo = 1;
            }
            if (este.getTipo().equals("S")) {
                tipo = 2;
            }
            if (este.getTipo().equals("R")) {
                tipo = 3;
            }
            if (este.getTipo().equals("D")) {
                tipo = 4;
            }
            if (este.getTipo().equals("C")) {
                tipo = 5;
            }
            if (este.getTipo().equals("B")) {
                tipo = 6;
            }
            switch (tipo) {
                case 0:
                    getEscritor().println("  public Texto " + sn + cuadraditos + ";");
                    break;
                case 1:
                    getEscritor().println("  public Numero " + sn + cuadraditos + ";");
                    break;
                case 2:
                    getEscritor().println("  public Numero " + sn + cuadraditos + ";");
                    break;
                case 3:
                    getEscritor().println("  public Fecha " + sn + cuadraditos + ";");
                    break;
                case 4:
                    getEscritor().println("  public DiaMesAno " + sn + cuadraditos + ";");
                    break;
                case 5:
                    getEscritor().println("  public Campo " + sn + cuadraditos + ";");
                    break;
                case 6:
                    getEscritor().println("  public SiNo " + sn + cuadraditos + ";");
                    break;
                default:
                    break;
            }
        }

    }

    public void define(CobolItem este) {
        colocaveces(este);
        if (este.getNhijos() > 0
                && !este.getTipo().equals("D")
                && !este.getTipo().equals("R")) {

            if (este.getTipo().equals("C")) {
                escribedefine(este);
            }

            for (int i = 0; i < este.getNhijos(); i++) {
                define(este.getHijos()[i]);
            }
        } else {
            escribedefine(este);
        }

    }

    void crear(CobolItem este) {
        colocaveces(este);
        if (este.getNhijos() > 0
                && !este.getTipo().equals("D")
                && !este.getTipo().equals("R")) {

            if (este.getTipo().equals("C")) {
                escribecrear(este);
            }

            for (int i = 0; i < este.getNhijos(); i++) {
                crear(este.getHijos()[i]);
            }
        } else {
            escribecrear(este);
        }
    }

    private void escribecrearlosfor(CobolItem este, int tipo) {
        String sn = este.getNombre().toLowerCase().replace('-', '_');
        String sncc = "\"" + sn + "\"";

        int a = este.getVecesA();
        int b = este.getVecesB();
        int c = este.getVeces();

        if (a > 1 && b < 2 && c < 2) {
            getEscritor().println("    for(int x=0; x<" + a + "; x++) {");
            switch (tipo) {
                case 0:
                    getEscritor().println("      Texto aux = new Texto(this, \"" + sn + "_\" + x, " + este.getInicio() + " + (x*" + este.getCadaA() + "), " + este.getLongitud() + ");");
                    getEscritor().println("      " + sn + "[x]=aux;");
                    getEscritor().println("      add(aux);");
                    break;
                case 1:
                    getEscritor().println("      Numero aux = new Numero(this, \"" + sn + "_\" + x, " + este.getInicio() + " + (x*" + este.getCadaA() + "), false, " + este.getEnteros() + ", " + este.getDecimales() + ");");
                    getEscritor().println("      " + sn + "[x]=aux;");
                    getEscritor().println("      add(aux);");

                    break;
                case 2:
                    getEscritor().println("      Numero aux = new Numero(this, \"" + sn + "_\" + x, " + este.getInicio() + " + (x*" + este.getCadaA() + "), true, " + este.getEnteros() + ", " + este.getDecimales() + ");");
                    getEscritor().println("      " + sn + "[x]=aux;");
                    getEscritor().println("      add(aux);");
                    break;
                case 3:
                    getEscritor().println("      Fecha aux = new Fecha(this, \"" + sn + "_\" + x, " + este.getInicio() + " + (x*" + este.getCadaA() + "));");
                    getEscritor().println("      " + sn + "[x]=aux;");
                    getEscritor().println("      add(aux);");
                    break;
                case 4:
                    getEscritor().println("      DiaMesAno aux = new DiaMesAno(this, \"" + sn + "_\" + x, " + este.getInicio() + " + (x*" + este.getCadaA() + "));");
                    getEscritor().println("      " + sn + "[x]=aux;");
                    getEscritor().println("      add(aux);");
                    break;
                case 5:
                    getEscritor().println("      Campo aux = new Campo(this, \"" + sn + "_\" + x, " + este.getInicio() + " + (x*" + este.getCadaA() + "), " + este.getLongitud() + ");");
                    getEscritor().println("      " + sn + "[x]=aux;");
                    getEscritor().println("      add(aux);");
                    break;
            }
            getEscritor().println("    }");
        }

        if (a > 1 && b > 1 && c < 2) {
            getEscritor().println("    for(int x=0; x<" + a + "; x++) {");
            getEscritor().println("      for(int y=0; y<" + b + "; y++) {");
            switch (tipo) {
                case 0:
                    getEscritor().println("        Texto aux = new Texto(this, \"" + sn + "_\" + x + \"_\" + y, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + "), " + este.getLongitud() + ");");
                    getEscritor().println("        " + sn + "[x][y]=aux;");
                    getEscritor().println("        add(aux);");
                    break;
                case 1:
                    getEscritor().println("        Numero aux = new Numero(this, \"" + sn + "_\" + x + \"_\" + y, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + "), false, " + este.getEnteros() + ", " + este.getDecimales() + ");");
                    getEscritor().println("        " + sn + "[x][y]=aux;");
                    getEscritor().println("        add(aux);");

                    break;
                case 2:
                    getEscritor().println("        Numero aux = new Numero(this, \"" + sn + "_\" + x + \"_\" + y, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + "), true, " + este.getEnteros() + ", " + este.getDecimales() + ");");
                    getEscritor().println("        " + sn + "[x][y]=aux;");
                    getEscritor().println("        add(aux);");
                    break;
                case 3:
                    getEscritor().println("        Fecha aux = new Fecha(this, \"" + sn + "_\" + x + \"_\" + y, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + "));");
                    getEscritor().println("        " + sn + "[x][y]=aux;");
                    getEscritor().println("        add(aux);");
                    break;
                case 4:
                    getEscritor().println("        DiaMesAno aux = new DiaMesAno(this, \"" + sn + "_\" + x + \"_\" + y, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + "));");
                    getEscritor().println("        " + sn + "[x][y]=aux;");
                    getEscritor().println("        add(aux);");
                    break;
                case 5:
                    getEscritor().println("        Campo aux = new Campo(this, \"" + sn + "_\" + x + \"_\" + y, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + "), " + este.getLongitud() + ");");
                    getEscritor().println("        " + sn + "[x][y]=aux;");
                    getEscritor().println("        add(aux);");
                    break;
            }
            getEscritor().println("      }");
            getEscritor().println("    }");
        }

        if (a > 1 && b > 1 && c > 1) {
            getEscritor().println("    for(int x=0; x<" + a + "; x++) {");
            getEscritor().println("      for(int y=0; y<" + b + "; y++) {");
            getEscritor().println("        for(int z=0; z<" + c + "; z++) {");
            switch (tipo) {
                case 0:
                    getEscritor().println("          Texto aux = new Texto(this, \"" + sn + "_\" + x + \"_\" + y + \"_\" + z, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + ") + (z*" + este.getCadaC() + "), " + este.getLongitud() + ");");
                    getEscritor().println("          " + sn + "[x][y][z]=aux;");
                    getEscritor().println("          add(aux);");
                    break;
                case 1:
                    getEscritor().println("          Numero aux = new Numero(this, \"" + sn + "_\" + x + \"_\" + y + \"_\" + z, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + ") + (z*" + este.getCadaC() + "), false, " + este.getEnteros() + ", " + este.getDecimales() + ");");
                    getEscritor().println("          " + sn + "[x][y][z]=aux;");
                    getEscritor().println("          add(aux);");

                    break;
                case 2:
                    getEscritor().println("          Numero aux = new Numero(this, \"" + sn + "_\" + x + \"_\" + y + \"_\" + z, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + ") + (z*" + este.getCadaC() + "), true, " + este.getEnteros() + ", " + este.getDecimales() + ");");
                    getEscritor().println("          " + sn + "[x][y][z]=aux;");
                    getEscritor().println("          add(aux);");
                    break;
                case 3:
                    getEscritor().println("          Fecha aux = new Fecha(this, \"" + sn + "_\" + x + \"_\" + y + \"_\" + z, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + ") + (z*" + este.getCadaC() + "));");
                    getEscritor().println("          " + sn + "[x][y][z]=aux;");
                    getEscritor().println("          add(aux);");
                    break;
                case 4:
                    getEscritor().println("          DiaMesAno aux = new DiaMesAno(this, \"" + sn + "_\" + x + \"_\" + y + \"_\" + z, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + ") + (z*" + este.getCadaC() + "));");
                    getEscritor().println("          " + sn + "[x][y][z]=aux;");
                    getEscritor().println("          add(aux);");
                    break;
                case 5:
                    getEscritor().println("          Campo aux = new Campo(this, \"" + sn + "_\" + x + \"_\" + y + \"_\" + z, " + este.getInicio() + " + (x*" + este.getCadaA() + ") + (y*" + este.getCadaB() + ") + (z*" + este.getCadaC() + "), " + este.getLongitud() + ");");
                    getEscritor().println("          " + sn + "[x][y][z]=aux;");
                    getEscritor().println("          add(aux);");
                    break;
            }
            getEscritor().println("        }");
            getEscritor().println("      }");
            getEscritor().println("    }");
        }

    }

    private void escribecrear(CobolItem este) {
        String cuadraditos = "";
        String sn = este.getNombre().toLowerCase().replace('-', '_');
        String sncc = "\"" + sn + "\"";
        int tipo = 0;
        boolean tiene = false;
        if (!sn.equals("filler")) {
            if (este.getVecesA() > 1) {
                cuadraditos = cuadraditos + "[" + este.getVecesA() + "]";
                tiene = true;
            }
            if (este.getVecesB() > 1) {
                cuadraditos = cuadraditos + "[" + este.getVecesB() + "]";
                tiene = true;
            }
            if (este.getVeces() > 1) {
                cuadraditos = cuadraditos + "[" + este.getVeces() + "]";
                tiene = true;
            }
            if (este.getTipo().equals("9")) {
                tipo = 1;
            }
            if (este.getTipo().equals("S")) {
                tipo = 2;
            }
            if (este.getTipo().equals("R")) {
                tipo = 3;
            }
            if (este.getTipo().equals("D")) {
                tipo = 4;
            }
            if (este.getTipo().equals("C")) {
                tipo = 5;
            }
            if (este.getTipo().equals("B")) {
                tipo = 6;
            }

            switch (tipo) {
                case 0:
                    if (tiene) {
                        getEscritor().println("    " + sn + " = new Texto" + cuadraditos + ";");
                    } else {
                        getEscritor().println("    " + sn + " = new Texto(this, " + sncc + ", " + este.getInicio() + ", " + este.getLongitud() + ");");
                        getEscritor().println("    add(" + sn + ");");
                    }
                    break;
                case 1:
                    if (tiene) {
                        getEscritor().println("    " + sn + " = new Numero" + cuadraditos + ";");
                    } else {
                        getEscritor().println("    " + sn + " = new Numero(this, " + sncc + ", " + este.getInicio() + ", false, " + este.getEnteros() + ", " + este.getDecimales() + ");");
                        getEscritor().println("    add(" + sn + ");");

                    }
                    break;
                case 2:
                    if (tiene) {
                        getEscritor().println("    " + sn + " = new Numero" + cuadraditos + ";");
                    } else {
                        getEscritor().println("    " + sn + " = new Numero(this, " + sncc + ", " + este.getInicio() + ", true, " + este.getEnteros() + ", " + este.getDecimales() + ");");
                        getEscritor().println("    add(" + sn + ");");
                    }
                    break;
                case 3:
                    if (tiene) {
                        getEscritor().println("    " + sn + " = new Fecha" + cuadraditos + ";");
                    } else {
                        getEscritor().println("    " + sn + " = new Fecha(this, " + sncc + ", " + este.getInicio() + ");");
                        getEscritor().println("    add(" + sn + ");");
                    }
                    break;
                case 4:
                    if (tiene) {
                        getEscritor().println("    " + sn + " = new DiaMesAno " + cuadraditos + ";");
                    } else {
                        getEscritor().println("    " + sn + " = new DiaMesAno(this, " + sncc + ", " + este.getInicio() + ");");
                        getEscritor().println("    add(" + sn + ");");
                    }
                    break;
                case 5:
                    if (tiene) {
                        getEscritor().println("    " + sn + " = new Campo " + cuadraditos + ";");
                    } else {
                        getEscritor().println("    " + sn + " = new Campo(this, " + sncc + ", " + este.getInicio() + ", " + este.getLongitud() + ");");
                        getEscritor().println("    add(" + sn + ");");
                    }
                    break;
                case 6:
                    if (tiene) {
                        getEscritor().println("    " + sn + " = new SiNo " + cuadraditos + ";");
                    } else {
                        getEscritor().println("    " + sn + " = new SiNo(this, " + sncc + ", " + este.getInicio() + ", " + este.getLongitud() + ");");
                        getEscritor().println("    add(" + sn + ");");
                    }
                    break;
                default:
                    break;
            }
            if (tiene) {
                escribecrearlosfor(este, tipo);
            }
        }
    }

    public void defineinterface(CobolItem este) {
        colocaveces(este);
        if (este.getNhijos() > 0
                && !este.getTipo().equals("D")
                && !este.getTipo().equals("R")) {

            if (este.getTipo().equals("C")) {
                escribeinterface(este);
            }

            for (int i = 0; i < este.getNhijos(); i++) {
                defineinterface(este.getHijos()[i]);
            }
        } else {
            escribeinterface(este);
        }

    }

    private void escribeinterface(CobolItem este) {
        String cuadraditos = "";
        String sn = este.getNombre().toLowerCase().replace('-', '_');
        String Sn = "" + sn.charAt(0);
        Sn = Sn.toUpperCase() + sn.substring(1);
        String sncc = "\"" + sn + "\"";
        int tipo = 0;
        boolean tiene = false;
        if (!sn.equals("filler")) {
            if (este.getVecesA() > 1) {
                cuadraditos = cuadraditos + ", int x";
                tiene = true;
            }
            if (este.getVecesB() > 1) {
                cuadraditos = cuadraditos + ", int y";
                tiene = true;
            }
            if (este.getVeces() > 1) {
                cuadraditos = cuadraditos + ", int z";
                tiene = true;
            }
            if (este.getTipo().equals("9")) {
                tipo = 1;
            }
            if (este.getTipo().equals("S")) {
                tipo = 2;
            }
            if (este.getTipo().equals("R")) {
                tipo = 3;
            }
            if (este.getTipo().equals("D")) {
                tipo = 4;
            }
            if (este.getTipo().equals("C")) {
                tipo = 5;
            }
            if (este.getTipo().equals("B")) {
                tipo = 6;
            }

            switch (tipo) {
                case 0:
                    if (tiene) {
                        getEscritor().println("  public String get" + Sn + "(" + cuadraditos.substring(1) + ");");
                        getEscritor().println("  public void set" + Sn + "(String s " + cuadraditos + ");");

                    } else {
                        getEscritor().println("  public String get" + Sn + "();");
                        getEscritor().println("  public void set" + Sn + "(String s);");
                    }
                    break;
                case 1:
                    if (tiene) {
                        getEscritor().println("  public BigDecimal get" + Sn + "(" + cuadraditos.substring(1) + ");");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n " + cuadraditos + ");");
                    } else {
                        getEscritor().println("  public BigDecimal get" + Sn + "();");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n);");
                    }
                    break;
                case 2:
                    if (tiene) {
                        getEscritor().println("  public BigDecimal get" + Sn + "(" + cuadraditos.substring(1) + ");");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n " + cuadraditos + ");");
                    } else {
                        getEscritor().println("  public BigDecimal get" + Sn + "();");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n);");
                    }
                    break;
                case 3:
                    if (tiene) {
                        getEscritor().println("  public Date get" + Sn + "(" + cuadraditos.substring(1) + ");");
                        getEscritor().println("  public void set" + Sn + "(Date d " + cuadraditos + ");");
                    } else {
                        getEscritor().println("  public Date get" + Sn + "();");
                        getEscritor().println("  public void set" + Sn + "(Date d);");
                    }
                    break;
                case 4:
                    if (tiene) {
                        getEscritor().println("  public Date get" + Sn + "(" + cuadraditos.substring(1) + ");");
                        getEscritor().println("  public void set" + Sn + "(Date d " + cuadraditos + ");");
                    } else {
                        getEscritor().println("  public Date get" + Sn + "();");
                        getEscritor().println("  public void set" + Sn + "(Date d);");
                    }
                    break;
                case 5:
                    if (tiene) {
                        getEscritor().println("  public String get" + Sn + "(" + cuadraditos.substring(1) + ");");
                        getEscritor().println("  public void set" + Sn + "(String s " + cuadraditos + ");");
                    } else {
                        getEscritor().println("  public String get" + Sn + "();");
                        getEscritor().println("  public void set" + Sn + "(String s);");
                    }
                    break;
                case 6:
                    if (tiene) {
                        getEscritor().println("  public Boolean get" + Sn + "(" + cuadraditos.substring(1) + ");");
                        getEscritor().println("  public void set" + Sn + "(Boolean s " + cuadraditos + ");");
                    } else {
                        getEscritor().println("  public Boolean get" + Sn + "();");
                        getEscritor().println("  public void set" + Sn + "(Boolean s);");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void defineinterfaceconectores(CobolItem este) {
        colocaveces(este);
        if (este.getNhijos() > 0
                && !este.getTipo().equals("D")
                && !este.getTipo().equals("R")) {

            if (este.getTipo().equals("C")) {
                escribeinterfaceconectores(este);
            }

            for (int i = 0; i < este.getNhijos(); i++) {
                defineinterfaceconectores(este.getHijos()[i]);
            }
        } else {
            escribeinterfaceconectores(este);
        }

    }

    private void escribeinterfaceconectores(CobolItem este) {
        String cuadraditos = "";
        String sn = este.getNombre().toLowerCase().replace('-', '_');
        String Sn = "" + sn.charAt(0);
        Sn = Sn.toUpperCase() + sn.substring(1);
        String sncc = "\"" + sn + "\"";
        int tipo = 0;
        boolean tiene = false;
        if (!sn.equals("filler")) {
            if (este.getVecesA() > 1) {
                cuadraditos = cuadraditos + ", int x";
                tiene = true;
            }
            if (este.getVecesB() > 1) {
                cuadraditos = cuadraditos + ", int y";
                tiene = true;
            }
            if (este.getVeces() > 1) {
                cuadraditos = cuadraditos + ", int z";
                tiene = true;
            }
            if (este.getTipo().equals("9")) {
                tipo = 1;
            }
            if (este.getTipo().equals("S")) {
                tipo = 2;
            }
            if (este.getTipo().equals("R")) {
                tipo = 3;
            }
            if (este.getTipo().equals("D")) {
                tipo = 4;
            }
            if (este.getTipo().equals("C")) {
                tipo = 5;
            }
            if (este.getTipo().equals("B")) {
                tipo = 6;
            }

            if (tiene) {
                getEscritor().println("  public CampoCobol getConector" + Sn + "(" + cuadraditos.substring(1) + ");");
            } else {
                getEscritor().println("  public CampoCobol getConector" + Sn + "();");
            }
        }
    }

    public void definebean(CobolItem este) {
        colocaveces(este);
        if (este.getNhijos() > 0
                && !este.getTipo().equals("D")
                && !este.getTipo().equals("R")) {

            if (este.getTipo().equals("C")) {
                escribebean(este);
            }

            for (int i = 0; i < este.getNhijos(); i++) {
                definebean(este.getHijos()[i]);
            }
        } else {
            escribebean(este);
        }

    }

    private void escribebean(CobolItem este) {
        String cuadraditos = "";
        String indices = "";

        String sn = este.getNombre().toLowerCase().replace('-', '_');
        String Sn = "" + sn.charAt(0);
        Sn = Sn.toUpperCase() + sn.substring(1);
        String sncc = "\"" + sn + "\"";
        int tipo = 0;
        boolean tiene = false;
        if (!sn.equals("filler")) {
            if (este.getVecesA() > 1) {
                cuadraditos = cuadraditos + ", int x";
                indices = indices + "[x]";
                tiene = true;
            }
            if (este.getVecesB() > 1) {
                cuadraditos = cuadraditos + ", int y";
                indices = indices + "[y]";
                tiene = true;
            }
            if (este.getVeces() > 1) {
                cuadraditos = cuadraditos + ", int z";
                indices = indices + "[y]";
                tiene = true;
            }
            if (este.getTipo().equals("9")) {
                tipo = 1;
            }
            if (este.getTipo().equals("S")) {
                tipo = 2;
            }
            if (este.getTipo().equals("R")) {
                tipo = 3;
            }
            if (este.getTipo().equals("D")) {
                tipo = 4;
            }
            if (este.getTipo().equals("C")) {
                tipo = 5;
            }
            if (este.getTipo().equals("B")) {
                tipo = 6;
            }
            switch (tipo) {
                case 0:
                    if (tiene) {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public String get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return campos." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(String s " + cuadraditos + ") {");
                        getEscritor().println("    campos." + sn + indices + ".set(s);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public String get" + Sn + "() {");
                        getEscritor().println("    return campos." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(String s) {");
                        getEscritor().println("    campos." + sn + ".set(s);");
                        getEscritor().println("  }");
                    }
                    break;
                case 1:
                    if (tiene) {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public BigDecimal get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return campos." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n " + cuadraditos + ") {");
                        getEscritor().println("    campos." + sn + indices + ".set(n);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public BigDecimal get" + Sn + "() {");
                        getEscritor().println("    return campos." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n) {");
                        getEscritor().println("    campos." + sn + ".set(n);");
                        getEscritor().println("  }");
                    }
                    break;
                case 2:
                    if (tiene) {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public BigDecimal get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return campos." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n " + cuadraditos + ") {");
                        getEscritor().println("    campos." + sn + indices + ".set(n);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public BigDecimal get" + Sn + "() {");
                        getEscritor().println("    return campos." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n) {");
                        getEscritor().println("    campos." + sn + ".set(n);");
                        getEscritor().println("  }");
                    }
                    break;
                case 3:
                    if (tiene) {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public Date get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return campos." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(Date d " + cuadraditos + ") {");
                        getEscritor().println("    campos." + sn + indices + ".set(d);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public Date get" + Sn + "() {");
                        getEscritor().println("    return campos." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(Date d) {");
                        getEscritor().println("    campos." + sn + ".set(d);");
                        getEscritor().println("  }");
                    }
                    break;
                case 4:
                    if (tiene) {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public Date get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return campos." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(Date d " + cuadraditos + ") {");
                        getEscritor().println("    campos." + sn + indices + ".set(d);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public Date get" + Sn + "() {");
                        getEscritor().println("    return campos." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(Date d) {");
                        getEscritor().println("    campos." + sn + ".set(d);");
                        getEscritor().println("  }");
                    }
                    break;
                case 5:
                    if (tiene) {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public String get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return campos." + sn + indices + ".getCobolText();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(String s " + cuadraditos + ") {");
                        getEscritor().println("    campos." + sn + indices + ".setCobolText(s);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public String get" + Sn + "() {");
                        getEscritor().println("    return campos." + sn + ".getCobolText();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(String s) {");
                        getEscritor().println("    campos." + sn + ".setCobolText(s);");
                        getEscritor().println("  }");
                    }
                    break;
                case 6:
                    if (tiene) {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public Boolean get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return campos." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(Boolean d " + cuadraditos + ") {");
                        getEscritor().println("    campos." + sn + indices + ".set(d);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  @Override");
                        getEscritor().println("  public Boolean get" + Sn + "() {");
                        getEscritor().println("    return campos." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  @Override");
                        getEscritor().println("  public void set" + Sn + "(Boolean d) {");
                        getEscritor().println("    campos." + sn + ".set(d);");
                        getEscritor().println("  }");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void defineFICHEROX(CobolItem este) {
        colocaveces(este);
        if (este.getNhijos() > 0
                && !este.getTipo().equals("D")
                && !este.getTipo().equals("R")) {

            if (este.getTipo().equals("C")) {
                escribeFICHEROX(este);
            }

            for (int i = 0; i < este.getNhijos(); i++) {
                defineFICHEROX(este.getHijos()[i]);
            }
        } else {
            escribeFICHEROX(este);
        }

    }

    private void escribeFICHEROX(CobolItem este) {
        String cuadraditos = "";
        String indices = "";
        String SN = este.getNombre().toUpperCase().replace('-', '_');
        String sn = este.getNombre().toLowerCase().replace('-', '_');
        String Sn = "" + SN;
        int tipo = 0;
        boolean tiene = false;
        if (!sn.equals("filler")) {
            if (este.getVecesA() > 1) {
                cuadraditos = cuadraditos + ", int x";
                indices = indices + "[x]";
                tiene = true;
            }
            if (este.getVecesB() > 1) {
                cuadraditos = cuadraditos + ", int y";
                indices = indices + "[y]";
                tiene = true;
            }
            if (este.getVeces() > 1) {
                cuadraditos = cuadraditos + ", int z";
                indices = indices + "[y]";
                tiene = true;
            }
            if (este.getTipo().equals("9")) {
                tipo = 1;
            }
            if (este.getTipo().equals("S")) {
                tipo = 2;
            }
            if (este.getTipo().equals("R")) {
                tipo = 3;
            }
            if (este.getTipo().equals("D")) {
                tipo = 4;
            }
            if (este.getTipo().equals("C")) {
                tipo = 5;
            }
            if (este.getTipo().equals("B")) {
                tipo = 6;
            }
            switch (tipo) {
                case 0:
                    if (tiene) {
                        getEscritor().println("  public String get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return record." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(String s " + cuadraditos + ") {");
                        getEscritor().println("    record." + sn + indices + ".set(s);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  public String get" + Sn + "() {");
                        getEscritor().println("    return record." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(String s) {");
                        getEscritor().println("    record." + sn + ".set(s);");
                        getEscritor().println("  }");
                    }
                    break;
                case 1:
                    if (tiene) {
                        getEscritor().println("  public BigDecimal get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return record." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n " + cuadraditos + ") {");
                        getEscritor().println("    record." + sn + indices + ".set(n);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  public BigDecimal get" + Sn + "() {");
                        getEscritor().println("    return record." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n) {");
                        getEscritor().println("    record." + sn + ".set(n);");
                        getEscritor().println("  }");
                    }
                    break;
                case 2:
                    if (tiene) {
                        getEscritor().println("  public BigDecimal get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return record." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n " + cuadraditos + ") {");
                        getEscritor().println("    record." + sn + indices + ".set(n);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  public BigDecimal get" + Sn + "() {");
                        getEscritor().println("    return record." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(BigDecimal n) {");
                        getEscritor().println("    record." + sn + ".set(n);");
                        getEscritor().println("  }");
                    }
                    break;
                case 3:
                    if (tiene) {
                        getEscritor().println("  public Date get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return record." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(Date d " + cuadraditos + ") {");
                        getEscritor().println("    record." + sn + indices + ".set(d);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  public Date get" + Sn + "() {");
                        getEscritor().println("    return record." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(Date d) {");
                        getEscritor().println("    record." + sn + ".set(d);");
                        getEscritor().println("  }");
                    }
                    break;
                case 4:
                    if (tiene) {
                        getEscritor().println("  public Date get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return record." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(Date d " + cuadraditos + ") {");
                        getEscritor().println("    record." + sn + indices + ".set(d);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  public Date get" + Sn + "() {");
                        getEscritor().println("    return record." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(Date d) {");
                        getEscritor().println("    record." + sn + ".set(d);");
                        getEscritor().println("  }");
                    }
                    break;
                case 5:
                    if (tiene) {
                        getEscritor().println("  public String get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return record." + sn + indices + ".getCobolText();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(String s " + cuadraditos + ") {");
                        getEscritor().println("    record." + sn + indices + ".setCobolText(s);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  public String get" + Sn + "() {");
                        getEscritor().println("    return record." + sn + ".getCobolText();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(String s) {");
                        getEscritor().println("    record." + sn + ".setCobolText(s);");
                        getEscritor().println("  }");
                    }
                    break;
                case 6:
                    if (tiene) {
                        getEscritor().println("  public Boolean get" + Sn + "(" + cuadraditos.substring(1) + ") {");
                        getEscritor().println("    return record." + sn + indices + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(Boolean d " + cuadraditos + ") {");
                        getEscritor().println("    record." + sn + indices + ".set(d);");
                        getEscritor().println("  }");
                    } else {
                        getEscritor().println("  public Boolean get" + Sn + "() {");
                        getEscritor().println("    return record." + sn + ".get();");
                        getEscritor().println("  }");
                        getEscritor().println("  public void set" + Sn + "(Boolean d) {");
                        getEscritor().println("    record." + sn + ".set(d);");
                        getEscritor().println("  }");
                    }
                    break;

                default:
                    break;
            }
        }
    }

    public void defineconectores(CobolItem este) {
        colocaveces(este);
        if (este.getNhijos() > 0
                && !este.getTipo().equals("D")
                && !este.getTipo().equals("R")) {

            if (este.getTipo().equals("C")) {
                escribeconectores(este);
            }

            for (int i = 0; i < este.getNhijos(); i++) {
                defineconectores(este.getHijos()[i]);
            }
        } else {
            escribeconectores(este);
        }

    }

    private void escribeconectores(CobolItem este) {
        String cuadraditos = "";
        String indices = "";
        String sn = este.getNombre().toLowerCase().replace('-', '_');
        String Sn = "" + sn.charAt(0);
        Sn = Sn.toUpperCase() + sn.substring(1);
        String sncc = "\"" + sn + "\"";
        int tipo = 0;
        boolean tiene = false;
        if (!sn.equals("filler")) {
            if (este.getVecesA() > 1) {
                cuadraditos = cuadraditos + ", int x";
                indices = indices + "[x]";
                tiene = true;
            }
            if (este.getVecesB() > 1) {
                cuadraditos = cuadraditos + ", int y";
                indices = indices + "[y]";
                tiene = true;
            }
            if (este.getVeces() > 1) {
                cuadraditos = cuadraditos + ", int z";
                indices = indices + "[y]";
                tiene = true;
            }
            if (este.getTipo().equals("9")) {
                tipo = 1;
            }
            if (este.getTipo().equals("S")) {
                tipo = 2;
            }
            if (este.getTipo().equals("R")) {
                tipo = 3;
            }
            if (este.getTipo().equals("D")) {
                tipo = 4;
            }
            if (este.getTipo().equals("C")) {
                tipo = 5;
            }
            if (este.getTipo().equals("B")) {
                tipo = 6;
            }
            if (tiene) {
                getEscritor().println("  public CampoCobol getConector" + Sn + "(" + cuadraditos.substring(1) + ") {");
                getEscritor().println("    return campos." + sn + indices + ";");
                getEscritor().println("  }");
            } else {
                getEscritor().println("  public CampoCobol getConector" + Sn + "() {");
                getEscritor().println("    return campos." + sn + ";");
                getEscritor().println("  }");
            }
        }
    }

    public PrintWriter getEscritor() {
        return escritor;
    }

    public void setEscritor(PrintWriter escritor) {
        this.escritor = escritor;
    }
}
