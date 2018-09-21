package es.cvjpy.utilidades.cobolcrear;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FicheroCobol {

    private String PROYECTOAPI = "nuevos";
    private String PROYECTO = "nuevos";
    private String SERVIDOR = "nuevos";
    private final String carpetaSrc;
    private final String carpetaLyb;
    private String PACKAGE = "es/cvjpy/cobol";
    private String nombreArchivo;
    private String NombreArchivoFD;
    private String NombreArchivoSEL;
    private UnaFD laFD;
    private UnaSEL laSEL;
    private File deUsar;
    private File deInterfaceBean;
    private File deRegistro;
    private File deBean;
    private File deInterfaceFichero;
    private File deFichero;
    private File deFICHEROX;
    private File deCOBOL;
    private File deWO;
    private File deDEC;
    private File deA;
    private File deT;
    private String nombreusar;
    private String nombreinterfacebean;
    private String nombreclaseregistro;
    private String nombreclasebean;
    private String nombreinterfacefichero;
    private String nombreclasefichero;
    private String nombreFICHEROX;
    private String raiz;
    private String sub2;
    private String sub3;
    private String sub4;
    private String sub5;
    private String sub6;
    private String SUB;
    private int totalchars = 0;

    public FicheroCobol(String nombreArchivo, String baseDir, String carpetaSrc, String carpetaLyb) {
        this.nombreArchivo = nombreArchivo;
        this.PROYECTO = baseDir;
        this.PROYECTOAPI = baseDir;
        this.SERVIDOR = baseDir;
        this.carpetaSrc = carpetaSrc;
        this.carpetaLyb = carpetaLyb;
        try {
            crearFiles();
        } catch (Exception ex) {
            Logger.getLogger(FicheroCobol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearFiles() throws Exception {
        setNombreArchivoFD(getNombreArchivo());
        setNombreArchivoSEL(getNombreArchivo().substring(0, getNombreArchivo().indexOf("-FD")) + "-SEL");

        setLaSEL(new UnaSEL(getNombreArchivoSEL()));
        setLaFD(new UnaFD(getNombreArchivoFD()));

        setSub2(getLaFD().getNombre().substring(1, 3));
        setSub3((getLaFD().getNombre() + "     ").substring(1, 4));
        setSub4((getLaFD().getNombre() + "      ").substring(1, 5));
        setSub5((getLaFD().getNombre() + "       ").substring(1, 6));
        setSub6((getLaFD().getNombre() + "        ").substring(1, 7));
        if (getSub5().equals("GICOB")
                || getSub5().equals("I130A")
                || getSub5().equals("I131A")
                || getSub5().equals("I300A")) {
            setSub2(getSub5());
        }
        if (getSub4().equals("I036")
                || getSub4().equals("I037")
                || getSub4().equals("I110")
                || getSub4().equals("I123")
                || getSub4().equals("I130")
                || getSub4().equals("I131")
                || getSub4().equals("I216")
                || getSub4().equals("I296")
                || getSub4().equals("I300")
                || getSub4().equals("I303")
                || getSub4().equals("I310")
                || getSub4().equals("I320")
                || getSub4().equals("I330")
                || getSub4().equals("I332")
                || getSub4().equals("I332")
                || getSub4().equals("I390")
                || getSub4().equals("RNT7")
                || getSub4().equals("PAT7")
                || getSub4().equals("RNT8")
                || getSub4().equals("PAT8")) {
            setSub2(getSub4());
        }
        if (getSub3().equals("CYL")) {
            setSub2(getSub3());
        }
        if (getSub3().equals("L11")) {
            setSub2(getSub3());
        }
        if (getSub3().equals("L14")) {
            setSub2(getSub3());
        }
        if (getSub3().equals("FCC")) {
            setSub2(getSub3());
        }
        if (getSub3().equals("IRM")) {
            setSub2(getSub3());
        }
        setSUB(getSub2());
        setSub2(getSub2().toLowerCase());

        setNombreusar("UsarF" + getLaFD().getNombre().substring(1).toLowerCase().replace('-', '_'));
        setDeUsar(new File(getPROYECTOAPI() + "/" + getCarpetaSrc() + "/" + getPACKAGE() + "/" + getSub2() + "/" + getNombreusar() + ".java"));

        setNombreinterfacebean("B" + getLaFD().getNombre().substring(1).toLowerCase().replace('-', '_'));
        setDeInterfaceBean(new File(getPROYECTOAPI() + "/" + getCarpetaSrc() + "/" + getPACKAGE() + "/" + getSub2() + "/" + getNombreinterfacebean() + ".java"));

        setNombreclaseregistro("Registro" + "F" + getLaFD().getNombre().substring(1).toLowerCase().replace('-', '_'));
        setDeRegistro(new File(getPROYECTO() + "/" + getCarpetaSrc() + "/" + getPACKAGE() + "/" + getSub2() + "/" + getNombreclaseregistro() + ".java"));

        setNombreclasebean("Bean" + "F" + getLaFD().getNombre().substring(1).toLowerCase().replace('-', '_'));
        setDeBean(new File(getPROYECTO() + "/" + getCarpetaSrc() + "/" + getPACKAGE() + "/" + getSub2() + "/" + getNombreclasebean() + ".java"));

        setNombreinterfacefichero("InterF" + getLaFD().getNombre().substring(1).toLowerCase().replace('-', '_'));
        setDeInterfaceFichero(new File(getPROYECTOAPI() + "/" + getCarpetaSrc() + "/" + getPACKAGE() + "/" + getSub2() + "/" + getNombreinterfacefichero() + ".java"));

        setNombreclasefichero("Fichero" + "F" + getLaFD().getNombre().substring(1).toLowerCase().replace('-', '_'));
        setDeFichero(new File(getPROYECTO() + "/" + getCarpetaSrc() + "/" + getPACKAGE() + "/" + getSub2() + "/" + getNombreclasefichero() + ".java"));

        setNombreFICHEROX(getLaFD().getNombre().toUpperCase().replace('-', '_'));
        setDeFICHEROX(new File(getPROYECTO() + "/" + getCarpetaSrc() + "/" + getPACKAGE() + "/" + getSub2() + "/" + getNombreFICHEROX() + ".java"));

        setDeCOBOL(new File(getSERVIDOR() + "/" + getCarpetaSrc() + "/" + getSub2() + "/" + "S" + getLaFD().getNombre().substring(1) + ".CBL"));
        setDeWO(new File(getSERVIDOR() + "/" + getCarpetaLyb() + "/" + getSub2() + "/" + "F" + getLaFD().getNombre().substring(1) + "-WO"));
        setDeDEC(new File(getSERVIDOR() + "/" + getCarpetaLyb() + "/" + getSub2() + "/" + "F" + getLaFD().getNombre().substring(1) + "-DEC"));
        setDeA(new File(getSERVIDOR() + "/" + getCarpetaSrc() + "/" + getSub2() + "/" + "A" + getLaFD().getNombre().substring(1) + ".CBL"));
        setDeT(new File(getSERVIDOR() + "/" + getCarpetaSrc() + "/" + getSub2() + "/" + "T" + getLaFD().getNombre().substring(1) + ".CBL"));

    }

    public void crearUsar() {
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeUsar()), "ISO-8859-1");
            PrintWriter w = new PrintWriter(ow);

            w.println("package es.cvjpy.cobol." + getSub2() + ";");
            w.println("");
            w.println("import es.cvjpy.cobol.*;");
            w.println("");
            w.println("public class " + getNombreusar() + " implements java.io.Serializable {");
            w.println("");
            w.println("private DatosConexion datosconexion;");
            w.println("private " + getNombreinterfacefichero() + " man;");
            w.println("private " + getNombreinterfacefichero() + " bus;");
            w.println("private " + getNombreinterfacefichero() + " lis;");
            w.println("private " + getNombreinterfacefichero() + " con;");
            w.println("");
            w.println("public " + getNombreusar() + "(DatosConexion datosconexion) {");
            w.println("  this.datosconexion = datosconexion;");
            w.println("}");

            w.println("public " + getNombreinterfacefichero() + " usa(TipoUso modo) throws ClassNotFoundException, InstantiationException, IllegalAccessException {");
            w.println("  ProcesoCobolPro enl;");
            w.println("  " + getNombreinterfacefichero() + " ret = null;");
            w.println("  switch (modo.ordinal()) {");
            w.println("    case 0:");
            w.println("      enl = datosconexion.getSesion().getEnlaceMantenimientos();");
            w.println("      if (man == null) {");
            w.println("        man = this.crea();");
            w.println("        man.setEnlace(enl);");
            w.println("      }");
            w.println("      ret = man;");
            w.println("      break;");
            w.println("    case 1:");
            w.println("      enl = datosconexion.getSesion().getEnlaceBusquedas();");
            w.println("      if (bus == null) {");
            w.println("        bus = this.crea();");
            w.println("        bus.setEnlace(enl);");
            w.println("      }");
            w.println("      ret = bus;");
            w.println("      break;");
            w.println("    case 2:");
            w.println("      enl = datosconexion.getSesion().getEnlaceListados();");
            w.println("      if (lis == null) {");
            w.println("        lis = this.crea();");
            w.println("        lis.setEnlace(enl);");
            w.println("      }");
            w.println("      ret = lis;");
            w.println("      break;");
            w.println("    case 3:");
            w.println("      enl = datosconexion.getSesion().getEnlaceConsultas();");
            w.println("      if (con == null) {");
            w.println("        con = this.crea();");
            w.println("        con.setEnlace(enl);");
            w.println("      }");
            w.println("      ret = con;");
            w.println("      break;");
            w.println("    case 4:");
            w.println("      enl = datosconexion.getSesion().creaEnlace();");
            w.println("      ret = this.crea();");
            w.println("      ret.setEnlace(enl);");
            w.println("      break;");
            w.println("  }");
            w.println("  return ret;");
            w.println("}");
            w.println("public " + getNombreinterfacefichero() + " crea() throws ClassNotFoundException, InstantiationException, IllegalAccessException {");
            w.println("  return (" + getNombreinterfacefichero() + ") Class.forName(\"es.cvjpy.cobol." + getSub2() + "." + getNombreclasefichero() + "\").newInstance();");
            w.println("}");
            w.println("}");

            w.println("");

            w.close();
        } catch (IOException x) {
            x.printStackTrace();
        }

    }

    public void crearInterfaceBean() {
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeInterfaceBean()), "ISO-8859-1");
            PrintWriter w = new PrintWriter(ow);

            DefinirCampos dc = new DefinirCampos(w);

            w.println("package es.cvjpy.cobol." + getSub2() + ";");
            w.println("");
            w.println("import java.math.*;");
            w.println("import java.util.*;");
            w.println("import es.cvjpy.cobol.*;");
            w.println("");
            w.println("public interface " + getNombreinterfacebean() + " extends java.io.Serializable {");
            w.println("");
            w.println("  public void initAll();");
            w.println("  public Registrable getRegistro();");
            w.println("");
            for (int i = 0; i < getLaFD().getNhijos(); i++) {
                dc.defineinterface(getLaFD().getHijos()[i]);
            }
            w.println("");
            w.println("}");

            w.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public void crearRegistro() {
        //laSEL.muestra();
        String nombredelregistro = "registro" + getLaFD().getNombre().toLowerCase().replace('-', '_');
        String nombredelaclave = getLaSEL().getLaclave().toLowerCase().replace('-', '_');

        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeRegistro()), "ISO-8859-1");
            PrintWriter w = new PrintWriter(ow);

            DefinirCampos dc = new DefinirCampos(w);

            w.println("package es.cvjpy.cobol." + getSub2() + ";");
            w.println("");
            w.println("import es.cvjpy.cobol.*;");
            w.println("");
            w.println("public final class " + getNombreclaseregistro() + " extends Registro {");
            w.println("");
            for (int i = 0; i < getLaFD().getNhijos(); i++) {
                dc.define(getLaFD().getHijos()[i]);
            }
            w.println("");
            w.println("  public Key " + "key_" + nombredelaclave + ";");
            for (int i = 0; i < getLaSEL().getNalternates(); i++) {
                w.println("  public Key key_" + getLaSEL().getAlternate()[i].toLowerCase().replace('-', '_') + ";");
            }

            w.println("");
            w.println("  public " + getNombreclaseregistro() + "() {");
            w.println("    super(\"" + nombredelregistro + "\", " + getLaFD().getMaxlongitud() + ");");
            w.println("    crearcampos();");
            w.println("    crearclaves();");
            w.println("    initAll();");
            w.println("  }");
            w.println("");
            w.println("  protected void crearcampos() {");
            for (int i = 0; i < getLaFD().getNhijos(); i++) {
                dc.crear(getLaFD().getHijos()[i]);
            }
            w.println("  }");
            w.println("");
            w.println("  protected void crearclaves() {");
            w.println("    key_" + nombredelaclave + " = new Key(\"" + nombredelaclave + "\");");
            for (int i = 0; i < getLaSEL().getNcamposClave(); i++) {
                w.println("    key_" + nombredelaclave + ".add(getCampo(\"" + getLaSEL().getCamposClave()[i].toLowerCase().replace('-', '_') + "\"));");
            }
            w.println("    setKey(key_" + nombredelaclave + ");");
            for (int i = 0; i < getLaSEL().getNalternates(); i++) {
                String nombredesta = getLaSEL().getAlternate()[i].toLowerCase().replace('-', '_');
                w.println("    key_" + nombredesta + " = new Key(\"" + nombredesta + "\");");
                for (int j = 0; j < getLaSEL().getNcamposAltenates()[i]; j++) {
                    String nombrecampo = getLaSEL().getCamposAltenates()[i][j].toLowerCase().replace('-', '_');
                    w.println("    key_" + nombredesta + ".add(getCampo(\"" + nombrecampo + "\"));");
                }
                w.println("    addAlterKey(key_" + nombredesta + ");");
            }

            w.println("  ");
            w.println("  }");
            w.println("");
            w.println("}");

            w.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public void crearBean() {
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeBean()), "ISO-8859-1");
            PrintWriter w = new PrintWriter(ow);

            DefinirCampos dc = new DefinirCampos(w);

            w.println("package es.cvjpy.cobol." + getSub2() + ";");
            w.println("");
            w.println("import java.math.BigDecimal;");
            w.println("import java.util.*;");
            w.println("import es.cvjpy.cobol.*;");
            w.println("");
            w.println("public class " + getNombreclasebean() + " implements " + getNombreinterfacebean() + " {");
            w.println("");
            w.println("  private final " + getNombreclaseregistro() + " campos;");
            w.println("");
            w.println("  public " + getNombreclasebean() + "(" + getNombreclaseregistro() + " campos) {");
            w.println("    this.campos = campos;");
            w.println("  }");
            w.println("");
            w.println("  @Override");
            w.println("  public Registro getRegistro() {");
            w.println("    return campos;");
            w.println("  }");
            w.println("");
            w.println("  @Override");
            w.println("  public void initAll() {");
            w.println("    campos.initAll();");
            w.println("  }");
            w.println("");
            for (int i = 0; i < getLaFD().getNhijos(); i++) {
                dc.definebean(getLaFD().getHijos()[i]);
            }
            w.println("");
            w.println("}");

            w.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public void crearInterfaceFichero() {
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeInterfaceFichero()), "ISO-8859-1");
            PrintWriter w = new PrintWriter(ow);

            DefinirCampos dc = new DefinirCampos(w);

            w.println("package es.cvjpy.cobol." + getSub2() + ";");
            w.println("");
            w.println("import es.cvjpy.cobol.*;");
            w.println("import java.util.*;");
            w.println("");
            w.println("public interface " + getNombreinterfacefichero() + " extends Ficherable {");
            w.println("  public " + getNombreinterfacebean() + " get" + getNombreinterfacebean() + "();");

            w.println("}");
            w.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public void crearFichero() {
        //laSEL.muestra();
        String nombredelregistro = "registro" + getLaFD().getNombre().toLowerCase().replace('-', '_');
        String nombredelbean = "bean" + getLaFD().getNombre().toLowerCase().replace('-', '_');

        String nombredelaclave = getLaSEL().getLaclave().toLowerCase().replace('-', '_');
        String nombreprograma = "S" + getLaFD().getNombre().substring(1);

        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeFichero()), "ISO-8859-1");
            PrintWriter w = new PrintWriter(ow);

            DefinirCampos dc = new DefinirCampos(w);

            w.println("package es.cvjpy.cobol." + getSub2() + ";");
            w.println("");
            w.println("import es.cvjpy.cobol.*;");
            w.println("import java.util.*;");
            //w.println("import java.util.logging.*;");

            w.println("");
            w.println("public class " + getNombreclasefichero() + " extends Fichero implements " + getNombreinterfacefichero() + " {");

            w.println("  protected " + getNombreclaseregistro() + " record;");
            w.println("  private final " + getNombreclasebean() + " " + nombredelbean + ";");
            //w.println("  private List<" + getNombreinterfacebean() + "> todos;");

            w.println("  public " + getNombreclasefichero() + "() {");
            w.println("    super(\"" + getLaFD().getNombre().replace('-', '_') + "\", \"" + getDd_dir() + "/" + getSUB() + "/" + getLaFD().getNombre().replace('-', '_') + "\", \"" + nombreprograma + "\");");
            w.println("    record = new " + getNombreclaseregistro() + "();");
            w.println("    setRegistro(record);");
            w.println("    " + nombredelbean + " = new " + getNombreclasebean() + "(record);");

            w.println("  }");
            w.println("");
            w.println("  @Override");
            w.println("  public " + getNombreinterfacebean() + " get" + getNombreinterfacebean() + "() {");
            w.println("    return " + nombredelbean + ";");
            w.println("  }");
            w.println("");

            w.println("}");
            w.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public void crearFICHEROX() {
        //laSEL.muestra();
        String nombredelregistro = "registro" + getLaFD().getNombre().toLowerCase().replace('-', '_');
        String nombredelbean = "bean" + getLaFD().getNombre().toLowerCase().replace('-', '_');

        String nombredelaclave = getLaSEL().getLaclave().toLowerCase().replace('-', '_');
        String nombreprograma = "S" + getLaFD().getNombre().substring(1);
        String primerrecord = getLaFD().getHijos()[0].getNombre().replace("-", "_");

        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeFICHEROX()), "ISO-8859-1");
            PrintWriter w = new PrintWriter(ow);

            DefinirCampos dc = new DefinirCampos(w);

            w.println("package es.cvjpy.cobol." + getSub2() + ";");
            w.println("");
            w.println("import es.cvjpy.cobol.*;");
            w.println("import java.math.BigDecimal;");
            w.println("import java.util.Date;");
            w.println("");
            w.println("public class " + getNombreFICHEROX() + " extends " + getNombreclasefichero() + " {");
            w.println("  public " + getNombreFICHEROX() + "() {");
            w.println("  }");
            w.println("  public " + getNombreFICHEROX() + "(ProcesoCobolPro enl) {");

            w.println("    this.setEnlace(enl);");
            w.println("  }");
            w.println("");
            w.println("  public " + getNombreFICHEROX() + "(Object enl) {");
            w.println("    this((ProcesoCobolPro) enl);");
            w.println("   }");
            w.println("");
            w.println("  public String get" + primerrecord + "() {");
            w.println("    return record.getAll();");
            w.println("  }");
            w.println("");
            w.println("  public void set" + primerrecord + "(String todo) {");
            w.println("    record.setAll(todo);");
            w.println("  }");
            w.println("");
            for (int i = 0; i < getLaFD().getNhijos(); i++) {
                dc.defineFICHEROX(getLaFD().getHijos()[i]);
            }
            w.println("");
            w.println("}");
            w.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public void crearWO() {

        String WOMBRE = "F" + getLaFD().getNombre().substring(1) + "-WO";
        String NOMBRE = getLaFD().getNombre();
        String RECORD = getLaFD().getHijos()[0].getNombre();
        String LAKEY = getLaSEL().getRelativekey();
        if (getLaSEL().isEsrelativo()) {
            laSEL.setLaclave(LAKEY);
        }

        PrintWriter w;
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeWO()), "ISO-8859-1");
            w = new PrintWriter(ow);

            w.println("       01 NOMBRE" + NOMBRE + " PIC X(256) VALUE \"" + getDd_dir() + "/" + getSUB() + "/" + NOMBRE + "\".");
            w.close();
        } catch (Exception ex) {
            Logger.getLogger(FicheroCobol.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void crearDEC() {

        String WOMBRE = "F" + getLaFD().getNombre().substring(1) + "-DEC";
        String AOMBRE = "A" + getLaFD().getNombre().substring(1);
        String TOMBRE = "T" + getLaFD().getNombre().substring(1);
        String NOMBRE = getLaFD().getNombre();
        String RECORD = getLaFD().getHijos()[0].getNombre();
        String LAKEY = getLaSEL().getRelativekey();
        if (getLaSEL().isEsrelativo()) {
            laSEL.setLaclave(LAKEY);
        }

        PrintWriter w;
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeDEC()), "ISO-8859-1");
            w = new PrintWriter(ow);


            w.println("       ERR-" + NOMBRE + " SECTION.");
            w.println("           USE AFTER ERROR PROCEDURE ON " + NOMBRE + ".");
            w.println("       ER-" + NOMBRE + ".");

            w.println("           EVALUATE ERRORES");
            w.println("              WHEN \"35\"");
            w.println("                 CALL \"" + AOMBRE + "\" USING NOMBRE" + NOMBRE);
            w.println("              WHEN \"30\"");
            w.println("                 CALL \"" + TOMBRE + "\" USING NOMBRE" + NOMBRE);
            w.println("              WHEN \"98\"");
            w.println("                 CALL \"" + TOMBRE + "\" USING NOMBRE" + NOMBRE);
            w.println("              WHEN \"9G\"");
            w.println("                 CALL \"" + TOMBRE + "\" USING NOMBRE" + NOMBRE);
            w.println("              WHEN OTHER");
            w.println("                 CALL \"PRUERR\" USING ERRORES NOMBRE" + NOMBRE + " PROG");
            w.println("                 CANCEL \"PRUERR\"");
            w.println("           END-EVALUATE.");
            w.println("       FIN-ERR-" + NOMBRE + ".");
            w.println("           EXIT.");

            w.close();
        } catch (Exception ex) {
            Logger.getLogger(FicheroCobol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearA() {

        String AOMBRE = "A" + getLaFD().getNombre().substring(1);
        String TOMBRE = "T" + getLaFD().getNombre().substring(1);
        String NOMBRE = getLaFD().getNombre();
        String RECORD = getLaFD().getHijos()[0].getNombre();
        String LAKEY = getLaSEL().getRelativekey();
        if (getLaSEL().isEsrelativo()) {
            laSEL.setLaclave(LAKEY);
        }

        PrintWriter w;
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeA()), "ISO-8859-1");
            w = new PrintWriter(ow);


            w.println("        IDENTIFICATION DIVISION.");
            w.println("        PROGRAM-ID.    " + AOMBRE + ".");
            w.println("        SECURITY.      ASIGNADOR.");
            w.println("        INPUT-OUTPUT SECTION.");
            w.println("        FILE-CONTROL.");
            w.println("            COPY \"" + NOMBRE + "-SEL\".");
            w.println("        DATA DIVISION.");
            w.println("        FILE SECTION.");
            w.println("            COPY \"" + NOMBRE + "-FD\".");
            w.println("        WORKING-STORAGE SECTION.");
            w.println("           COPY \"WO-ERRORES\".");
            w.println("        LINKAGE SECTION.");
            w.println("        01 NOMBRE" + NOMBRE + " PIC X(256).");
            w.println("        PROCEDURE DIVISION USING");
            w.println("                  NOMBRE" + NOMBRE + ".");
            w.println("        PROGRAMA SECTION.");
            w.println("        PROCESOS.");
            w.println("            IF NOMBRE" + NOMBRE + " = SPACES OR LOW-VALUES");
            w.println("               MOVE \"" + getDd_dir() + "/" + getSUB() + "/" + NOMBRE + "\" TO NOMBRE" + NOMBRE + "");
            w.println("            END-IF.");
            w.println("            CALL \"PRUMKDIR\" USING NOMBRE" + NOMBRE + ".");
            w.println("            OPEN OUTPUT " + NOMBRE + ".");
            w.println("            IF ERRORES NOT = \"00\"");
            w.println("               CALL \"PRUERR\" USING");
            w.println("                    ERRORES");
            w.println("                    NOMBRE" + NOMBRE);
            w.println("                   \"ASIGNADOR\"");
            w.println("               GO TO FIN-PROGRAMA.");
            w.println("            CLOSE " + NOMBRE + ".");
            w.println("        FIN-PROCESOS.");
            w.println("            EXIT PROGRAM.");
            w.println("        FIN-PROGRAMA.");
            w.println("            STOP RUN.");

            w.close();
        } catch (Exception ex) {
            Logger.getLogger(FicheroCobol.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void crearT() {

        String AOMBRE = "A" + getLaFD().getNombre().substring(1);
        String TOMBRE = "T" + getLaFD().getNombre().substring(1);
        String NOMBRE = getLaFD().getNombre();
        String RECORD = getLaFD().getHijos()[0].getNombre();
        String LAKEY = getLaSEL().getRelativekey();
        if (getLaSEL().isEsrelativo()) {
            laSEL.setLaclave(LAKEY);
        }

        PrintWriter w;
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeT()), "ISO-8859-1");
            w = new PrintWriter(ow);


            w.println("        IDENTIFICATION DIVISION.");
            w.println("        PROGRAM-ID.    " + TOMBRE + ".");
            w.println("        SECURITY.      REORGANIZA.");
            w.println("");
            w.println("        ENVIRONMENT DIVISION.");
            w.println("");
            w.println("        INPUT-OUTPUT SECTION.");
            w.println("        FILE-CONTROL.");
            w.println("");
            w.println("            COPY \"" + NOMBRE + "-SEL\".");
            w.println("");
            w.println("            SELECT FOLD");
            w.println("                   ASSIGN TO NOMBREOLD");
            w.println("                   ORGANIZATION IS LINE SEQUENTIAL");
            w.println("                   ACCESS MODE IS SEQUENTIAL");
            w.println("                   FILE STATUS IS ERRORES.");
            w.println("");
            w.println("        DATA DIVISION.");
            w.println("        FILE SECTION.");
            w.println("");
            w.println("            COPY \"" + NOMBRE + "-FD\".");
            w.println("");
            w.println("        FD  FOLD");
            w.println("            DATA RECORD IS ROLD.");
            w.println("        01  ROLD       PIC X(4096).");
            w.println("");
            w.println("        WORKING-STORAGE SECTION.");
            w.println("");
            w.println("            COPY \"WO-ERRORES\".");

            w.println("");
            w.println("        77  PROG PIC X(128)  VALUE \"" + TOMBRE + "\".");
            w.println("        77  REGL PIC 9(8).");
            w.println("        77  REGD PIC 9(8).");
            w.println("        77  REGG PIC 9(8).");
            w.println("        77  NLIN PIC 9(8).");
            w.println("");
            w.println("        LINKAGE SECTION.");
            w.println("        01 NOMBRE" + NOMBRE + " PIC X(256).");
            w.println("        PROCEDURE DIVISION USING");
            w.println("                  NOMBRE" + NOMBRE + ".");
            w.println("        DECLARATIVES.");
            w.println("            COPY \"" + NOMBRE + "-DEC\".");
            w.println("        END DECLARATIVES.");
            w.println("");
            w.println("        PROGRAMA SECTION.");
            w.println("        INICIALES.");
            w.println("            IF NOMBRE" + NOMBRE + " = SPACES OR LOW-VALUES");
            w.println("               MOVE \"" + getDd_dir() + "/" + getSUB() + "/" + NOMBRE + "\" TO NOMBRE" + NOMBRE + "");
            w.println("            END-IF.");
            w.println("            INITIALIZE REGL REGD REGG.");
            w.println("        PROCESOS.");
            w.println("            PERFORM MOVIDA.");
            w.println("            PERFORM ASIGNACION.");
            w.println("            PERFORM GRABACION.");
            w.println("            PERFORM BORRADO.");
            w.println("        FIN-PROCESO.");
            w.println("            EXIT PROGRAM.");
            w.println("        FIN-PROGRAMA.");
            w.println("            STOP RUN.");
            w.println("");
            w.println("        MOVIDA SECTION.");
            w.println("        LLA.");
            w.println("            MOVE SPACES TO NOMBREOLD.");
            w.println("            STRING NOMBRE" + NOMBRE + " \"OLD\"");
            w.println("                   DELIMITED BY \" \"");
            w.println("                   INTO NOMBREOLD.");
            w.println("*********** ATENCION:YA DEBERÍA ESTAR MOVIDO A OLD.");
            w.println("*********** DESDE JAVA YA SE HACE. SI LO ESTÁS LLAMANDO A MANO DEBES HABERLO MOVIDO A OLD ANTES");
            w.println("            OPEN INPUT FOLD.");
            w.println("            IF ERRORES NOT = \"00\" *>SI EL OLD NO EXISTE HAY QUE HACERLO.");
            w.println("               DISPLAY \"HAY QUE HACER EL \" NOMBREOLD UPON SYSOUT");
            w.println("               DISPLAY \"ANTES DE LLAMAR A ÉSTE\" UPON SYSOUT");
            w.println("               EXIT PROGRAM");
            w.println("               STOP RUN");
            w.println("            END-IF.");
            w.println("            CLOSE FOLD.");

            w.println("        FIN-MOVIDA.");
            w.println("            EXIT.");
            w.println("");
            w.println("        ASIGNACION SECTION.");
            w.println("        ASIG.");
            w.println("            CALL \"" + AOMBRE + "\" USING NOMBRE" + NOMBRE + ".");
            w.println("        FIN-ASIGNACION.");
            w.println("            EXIT.");
            w.println("");
            w.println("        GRABACION SECTION.");
            w.println("        ABRIR.");
            w.println("            MOVE ZEROES TO NLIN.");
            w.println("            MOVE SPACES TO ERRORES.");
            w.println("            PERFORM UNTIL ERROR-1 = \"0\"");
            w.println("               OPEN I-O " + NOMBRE + "");
            w.println("            END-PERFORM.");
            w.println("            OPEN INPUT FOLD.");
            w.println("        LEER.");
            w.println("            READ FOLD AT END GO TO CERRAR.");
            w.println("            ADD 1 TO NLIN.");
            w.println("            IF NLIN > 1000");
            w.println("               MOVE 1 TO NLIN");
            w.println("            END-IF.");
            w.println("            ADD 1 TO REGL.");
            w.println("            IF NLIN = 1");
            w.println("               DISPLAY \"DEBUG:LEIDOS:\" REGL UPON SYSOUT.");
            w.println("            IF ROLD(1:1) = LOW-VALUES");
            w.println("               GO TO LEER");
            w.println("            END-IF.");
            w.println("            MOVE ROLD TO " + RECORD + "");
            w.println("            READ " + NOMBRE + " INVALID KEY");
            w.println("                 GO TO GRABA-BIEN.");
            w.println("        GRABA-MAL.");
            w.println("            ADD 1 TO REGD.");
            w.println("            IF NLIN = 1");
            w.println("               DISPLAY \"DEBUG:DUPLICADOS:\" REGD UPON SYSOUT.");
            w.println("            GO TO LEER.");
            w.println("        GRABA-BIEN.");
            w.println("            WRITE " + RECORD + " INVALID KEY GO TO GRABA-MAL.");
            w.println("            ADD 1 TO REGG.");
            w.println("            IF NLIN = 1");
            w.println("               DISPLAY \"DEBUG:GRABADOS:\" REGG UPON SYSOUT.");
            w.println("            GO TO LEER.");
            w.println("        CERRAR.");
            w.println("            DISPLAY \"DEBUG:LEIDOS:\" REGL UPON SYSOUT.");
            w.println("            DISPLAY \"DEBUG:GRABADOS:\" REGG UPON SYSOUT.");
            w.println("            DISPLAY \"DEBUG:DUPLICADOS:\" REGD UPON SYSOUT.");
            w.println("            CLOSE " + NOMBRE + ".");
            w.println("            CLOSE FOLD.");
            w.println("        FIN-GRABACION.");
            w.println("            EXIT.");
            w.println("");
            w.println("        BORRADO SECTION.");
            w.println("        BORROLD.");
            w.println("            CALL \"PRURM\" USING NOMBREOLD.");
            w.println("        FIN-BORRADO.");
            w.println("            EXIT.");

            w.close();
        } catch (Exception ex) {
            Logger.getLogger(FicheroCobol.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void crearCBL() {

        String SOMBRE = "S" + getLaFD().getNombre().substring(1);
        String NOMBRE = getLaFD().getNombre();
        String RECORD = getLaFD().getHijos()[0].getNombre();
        String LAKEY = getLaSEL().getRelativekey();
        if (getLaSEL().isEsrelativo()) {
            laSEL.setLaclave(LAKEY);
        }
        PrintWriter w;
        try {
            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(getDeCOBOL()), "ISO-8859-1");
            w = new PrintWriter(ow);

            w.println("       IDENTIFICATION DIVISION.");
            w.println("");
            w.println("       PROGRAM-ID.    " + SOMBRE + ".");
            w.println("       AUTHOR.        El hacedor de SERVIDORES.");
            w.println("       SECURITY.      SERVIDOR DE " + getLaFD().getNombre() + ".");
            w.println("");
            w.println("       INPUT-OUTPUT SECTION.");
            w.println("       FILE-CONTROL.");
            w.println("           COPY \"" + NOMBRE + "-SEL\".");
            w.println("");
            w.println("       DATA DIVISION.");
            w.println("       FILE SECTION.");
            w.println("           COPY \"" + NOMBRE + "-FD\".");
            w.println("");
            w.println("       WORKING-STORAGE SECTION.");
            w.println("");
            w.println("       01 ESCRIBIBLE  PIC X IS EXTERNAL.");
            w.println("       01 USUARIO  PIC X(15) IS EXTERNAL.");
            w.println("       01 ABM      PIC X.");
            w.println("       01 REGISTRO PIC X(16384).");
            w.println("       01 TRAZAR   PIC XXXXX.");
            w.println("");
            for (int ind = 0; ind < getLaFD().getNhijos(); ind++) {
                defincobol(w, getLaFD().getHijos()[ind]);
            }
            w.println("       01  VALORDIR          PIC X(128).");
            w.println("       01  NOMBRDIR          PIC X(6).");
            w.println("       01  ERRORES.");
            w.println("           03 ERROR-1        PIC X.");
            w.println("           03 ERROR-2        PIC X.");
            w.println("           03 ERROR-2N REDEFINES ERROR-2 PIC 9 COMP-X.");
            w.println("");
            w.println("       01 NOMBRE" + NOMBRE + "                PIC X(256).");
            w.println("       01 ABIERTO                     PIC 9.");
            w.println("       01 IGNORELOCK                  PIC 9.");
            w.println("       01 INVALIDKEY                  PIC 9.");
            w.println("       01 INVALIDKEYNEXT              PIC 9.");
            w.println("       01 INVALIDKEYPREV              PIC 9.");
            w.println("       01 CONVERTIR                   PIC 9.");
            if (getLaSEL().isEsrelativo()) {
                w.println("       01 " + LAKEY + "               PIC 9.");
            }
            w.println("       01 PANII                       PIC 99.");
            w.println("       01 PANJJ                       PIC 99.");
            w.println("       01 II                          PIC 99999.");
            w.println("       01 JJ                          PIC 99999.");
            w.println("       01 KK                          PIC 99999.");
            w.println("       01 LONGI                       PIC 99999.");
            w.println("       01 ESTADO                      PIC XX.");
            w.println("       01 PALPRUPARJ                   PIC X(255).");
            w.println("       01 OPERACION                   PIC X(128).");
            w.println("       01 VALORES                     PIC X("
                    + (getLaFD().getHijos()[0].getLongitud() + 256) + ").");
            w.println("       01 BUFERENT                    PIC X("
                    + (getLaFD().getHijos()[0].getLongitud() + 256) + ").");
            w.println("       01 MIBUF                       PIC X("
                    + (getLaFD().getHijos()[0].getLongitud() + 256) + ").");
            w.println("       01 CMP-1                       PIC X("
                    + (getLaFD().getHijos()[0].getLongitud() + 256) + ").");
            w.println("       01 CMP-2                       PIC X("
                    + (getLaFD().getHijos()[0].getLongitud() + 256) + ").");
            w.println("       01 MILONGI                     PIC 99999.");

            w.println("       01 ENTEXTO PIC X(" + getLaFD().getHijos()[0].getLongitud() + ").");
            w.println("       01 ELNUMERO USAGE IS COMP-2.");
            w.println("       01 ENPIC PIC -9(14).9999.");
            w.println("       01 COMPIC PIC -9(14).9999.");
            w.println("       01 FINPIC PIC -9(14).9999.");
            w.println("       01 ENVALOR PIC S9(14)V9999 SIGN LEADING SEPARATE.");
            w.println("       01 ENLETRA REDEFINES ENVALOR.");
            w.println("          03 ELENTERO  PIC X(15).");
            w.println("          03 ELDECIMAL PIC X(4).");
            w.println("       01 PRIMERO PIC X(" + getLaFD().getHijos()[0].getLongitud() + ").");
            w.println("       01 SEGUNDO PIC X(" + getLaFD().getHijos()[0].getLongitud() + ").");
            w.println("       01 TERCERO PIC X(" + getLaFD().getHijos()[0].getLongitud() + ").");
            w.println("       01 ELRESTO PIC X(" + getLaFD().getHijos()[0].getLongitud() + ").");
            w.println("       01 ELRESTO1 PIC X(" + getLaFD().getHijos()[0].getLongitud() + ").");
            w.println("       01 ELRESTO2 PIC X(" + getLaFD().getHijos()[0].getLongitud() + ").");
            w.println("");
            w.println("       01 SEPARADOR PIC XXX VALUE \"|@|\".");
            w.println("       01 BUFER-DIR.");
            w.println("          03 BUF-1 PIC X OCCURS 256.");
            w.println("       01 DIRECTORIO.");
            w.println("          03 DIR-1 PIC X OCCURS 256.");
            w.println("       01 X-ERROR PIC X(80).");
            w.println("       01 CUANTOS PIC 99 VALUE 79.");
            w.println("       01 LINKA PIC 9 VALUE 0.");
            w.println("");

            w.println("         PROCEDURE DIVISION.");

            w.println("");
            w.println("       PROGRAMA SECTION.");
            w.println("       INICIALES.");
            w.println("           IF ABIERTO < 1 OR > 4");
            w.println("              MOVE \"" + getDd_dir() + "/" + getSUB() + "/" + NOMBRE
                    + "\" TO NOMBRE" + NOMBRE);
            w.println("              MOVE 4 TO ABIERTO");
            w.println("              INITIALIZE " + RECORD);
            w.println("              INITIALIZE CONVERTIR");
            w.println("              MOVE 1 TO IGNORELOCK");
            w.println("              INITIALIZE INVALIDKEY");
            w.println("              MOVE \"00\" TO ERRORES");
            w.println("           END-IF.");
            w.println("       PROCESOS.");
            w.println("           IF ESCRIBIBLE = \"P\"");
            w.println("              CALL \"Leer\" USING BUFERENT");
            w.println("           ELSE");
            w.println("              ACCEPT BUFERENT FROM SYSIN");
            w.println("           END-IF.");
            w.println("           PERFORM COGE-TRABAJO.");
            w.println("           EVALUATE OPERACION");
            w.println("              WHEN \"TODOS\" PERFORM CARGATODOS");
            w.println("              WHEN \"TRAZAR\"");
            w.println("                 MOVE VALORES TO TRAZAR");
            w.println("              WHEN \"SNO\"  PERFORM PONNOMBRE");
            w.println("              WHEN \"OIN\"  PERFORM ABREINPUT");
            if (getLaSEL().getLaclave() != null) {
                w.println("              WHEN \"OIO\"  PERFORM ABREIO");
            } else {
                w.println("              WHEN \"OEX\"  PERFORM ABREEXTEND");
            }
            w.println("              WHEN \"OOU\"  PERFORM ABREOUTPUT");
            w.println("              WHEN \"REA\"  PERFORM LEER");
            w.println("              WHEN \"WRI\"  PERFORM ESCRIBIR");
            w.println("              WHEN \"REW\"  PERFORM REESCRIBIR");
            if (getLaSEL().getLaclave() != null) {
                w.println("              WHEN \"DEL\"  PERFORM BORRAR");
                w.println("              WHEN \"ST<\"  PERFORM STMENOR");
                w.println("              WHEN \"ST>\"  PERFORM STMAYOR");
                w.println("              WHEN \"STN<\" PERFORM STNOMENOR");
                w.println("              WHEN \"STN>\" PERFORM STNOMAYOR");
            }
            for (int ind = 0; ind < getLaSEL().getNalternates(); ind++) {
                w.println("              WHEN \"REAK" + getLaSEL().getAlternate()[ind].replace('-', '_')
                        + "\"");
                w.println("              PERFORM LK" + getLaSEL().getAlternate()[ind]);
                w.println("              WHEN \"ST<K" + getLaSEL().getAlternate()[ind].replace('-', '_')
                        + "\"");
                w.println("              PERFORM STMK" + getLaSEL().getAlternate()[ind]);
                w.println("              WHEN \"ST>K" + getLaSEL().getAlternate()[ind].replace('-', '_')
                        + "\"");
                w.println("              PERFORM STYK" + getLaSEL().getAlternate()[ind]);
                w.println("              WHEN \"STN<K" + getLaSEL().getAlternate()[ind].replace('-', '_')
                        + "\"");
                w.println("              PERFORM STNMK" + getLaSEL().getAlternate()[ind]);
                w.println("              WHEN \"STN>K" + getLaSEL().getAlternate()[ind].replace('-', '_')
                        + "\"");
                w.println("              PERFORM STNYK" + getLaSEL().getAlternate()[ind]);
            }
            w.println("              WHEN \"NEX\"  PERFORM LEERNEXT");
            if (getLaSEL().getLaclave() != null) {
                w.println("              WHEN \"PRE\"  PERFORM LEERPREVIOUS");
            }
            w.println("              WHEN \"UNL\"  PERFORM NOBLOC");
            w.println("              WHEN \"CLO\"  PERFORM CERRAR");
            w.println("              WHEN \"SIL\"  PERFORM SETIGNORELOCK");
            w.println("              WHEN \"GER\"  PERFORM GETERRORES");
            if (getLaSEL().isEsrelativo()) {
                w.println("              WHEN \"G" + LAKEY.replace('-', '_')
                        + "\"");
                w.println("              PERFORM GET" + LAKEY);
                w.println("              WHEN \"S" + LAKEY.replace('-', '_')
                        + "\"");
                w.println("              PERFORM SET" + LAKEY);
                w.println("              WHEN \"I" + LAKEY.replace('-', '_')
                        + "\"");
                w.println("              PERFORM INI" + LAKEY);
            }
            w.println("              WHEN \"GETALL\" PERFORM GETALL");
            w.println("              WHEN \"SETALL\" PERFORM SETALL");
            w.println("              WHEN \"STP\" PERFORM TERMINA");
            w.println("           END-EVALUATE.");
            w.println("       FIN-PROCESOS.");
            w.println("           EXIT PROGRAM.");
            w.println("       FIN-PROGRAMA.");
            w.println("           STOP RUN.");
            w.println("");
            w.println("       TERMINA SECTION.");
            w.println("       TERMI.");
            w.println("           MOVE 5 TO LONGI.");
            w.println("           IF ESCRIBIBLE = \"P\"");
            w.println("              CALL \"Escribir\" USING \"|FIN|\" LONGI");
            w.println("           ELSE");
            w.println("              DISPLAY \"|FIN|\" UPON SYSOUT");
            w.println("           END-IF.");
            w.println("           STOP RUN.");
            w.println("       FIN-TERMINA.");
            w.println("");
            w.println("       COGE-TRABAJO SECTION.");
            w.println("       COG-TRA.");
            w.println("           IF ESCRIBIBLE = \"P\"");
            w.println("              CALL \"CogeTrabajo\" USING OPERACION VALORES PALPRUPARJ");
            w.println("           ELSE");
            w.println("              UNSTRING BUFERENT DELIMITED BY SEPARADOR INTO");
            w.println("                    OPERACION");
            w.println("                    VALORES");
            w.println("              END-UNSTRING");
            w.println("           END-IF.");
            w.println("       FIN-COGE-TRABAJO.");

            w.println("");
            w.println("       ABREINPUT SECTION.");
            w.println("       ABR-INP.");
            w.println("           IF ABIERTO = 1");
            w.println("              GO TO FIN-ABREINPUT");
            w.println("           END-IF.");
            w.println("           IF ABIERTO = 2 OR 3");
            w.println("              PERFORM CERRAR");
            w.println("           END-IF.");
            w.println("           OPEN INPUT " + getLaFD().getNombre() + ".");
            w.println("           IF ERRORES = \"35\" OR");
            w.println("             (ERROR-1 = \"9\" AND ERROR-2N = 9)");
            w.println("              PERFORM ABREOUTPUT");
            w.println("              PERFORM CERRAR");
            w.println("              GO TO ABR-INP");
            w.println("           END-IF.");
            w.println("           MOVE 1 TO ABIERTO.");
            w.println("       FIN-ABREINPUT.");
            w.println("");
            if (getLaSEL().getLaclave() != null) {
                w.println("       ABREIO SECTION.");
                w.println("       ABR-IO.");
                w.println("           IF ABIERTO = 2");
                w.println("              GO TO FIN-ABREIO");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 1 OR 3");
                w.println("              PERFORM CERRAR");
                w.println("           END-IF.");
                w.println("           OPEN I-O " + NOMBRE + ".");
                w.println("           IF ERRORES = \"35\" OR");
                w.println("              ERROR-1 = \"9\" AND ERROR-2N = 9");
                w.println("              PERFORM ABREOUTPUT");
                w.println("              PERFORM CERRAR");
                w.println("              GO TO ABR-IO.");
                w.println("           MOVE 2 TO ABIERTO.");
                w.println("       FIN-ABREIO.");
            } else {
                w.println("       ABREEXTEND SECTION.");
                w.println("       ABR-EXT.");
                w.println("           IF ABIERTO = 2");
                w.println("              GO TO FIN-ABREEXTEND");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 1 OR 3");
                w.println("              PERFORM CERRAR");
                w.println("           END-IF.");
                w.println("           OPEN EXTEND " + NOMBRE + ".");
                w.println("           IF ERRORES = \"35\" OR");
                w.println("             (ERROR-1 = \"9\" AND ERROR-2N = 9)");
                w.println("              PERFORM HACDIR");
                w.println("              OPEN EXTEND " + NOMBRE);
                w.println("              IF ERRORES = \"35\" OR");
                w.println("                (ERROR-1 = \"9\" AND ERROR-2N = 9)");
                w.println("                 GO TO FIN-ABREEXTEND.");
                w.println("           MOVE 3 TO ABIERTO.");
                w.println("       FIN-ABREEXTEND.");
            }
            w.println("");
            w.println("       ABREOUTPUT SECTION.");
            w.println("       ABR-OUT.");
            w.println("           IF ABIERTO = 3");
            w.println("              GO TO FIN-ABREOUTPUT");
            w.println("           END-IF.");
            w.println("           IF ABIERTO = 1 OR 2");
            w.println("              PERFORM CERRAR");
            w.println("           END-IF.");
            w.println("           OPEN OUTPUT " + NOMBRE + ".");
            w.println("           IF ERRORES = \"35\" OR");
            w.println("             (ERROR-1 = \"9\" AND ERROR-2N = 9)");
            w.println("              PERFORM HACDIR");
            w.println("              OPEN OUTPUT " + NOMBRE);
            w.println("              IF ERRORES NOT = \"00\"");
            w.println("                 CALL \"PRUERR\" USING");
            w.println("                      ERRORES");
            w.println("                      NOMBRE" + NOMBRE);
            w.println("                     \"ASIGNADOR\"");
            w.println("                 GO TO FIN-ABREOUTPUT.");
            w.println("           MOVE 3 TO ABIERTO.");
            w.println("       FIN-ABREOUTPUT.");
            w.println("");
            w.println("       CERRAR SECTION.");
            w.println("       CER.");
            w.println("           IF ABIERTO < 1 OR > 3");
            w.println("              GO TO FIN-CERRAR");
            w.println("           END-IF.");
            w.println("           CLOSE " + NOMBRE + ".");
            w.println("           MOVE 4 TO ABIERTO.");
            w.println("       FIN-CERRAR.");
            w.println("");
            w.println("       NOBLOC SECTION.");
            w.println("       CER.");
            w.println("           IF ABIERTO < 1 OR > 3");
            w.println("              GO TO FIN-NOBLOC");
            w.println("           END-IF.");
            w.println("           UNLOCK " + NOMBRE + ".");
            w.println("       FIN-NOBLOC.");
            w.println("");
            w.println("       LEER SECTION.");
            w.println("       LEER-PR.");
            w.println("           PERFORM MOVEJTOC.");
            w.println("           MOVE 0 TO INVALIDKEY.");
            w.println("           IF ABIERTO < 1 OR > 3");
            w.println("              PERFORM ABREINPUT");
            w.println("           END-IF.");
            w.println("           IF ABIERTO = 3");
            w.println("              PERFORM CERRAR");
            w.println("              PERFORM ABREINPUT");
            w.println("           END-IF.");
            w.println("           IF IGNORELOCK = 1");
            if (getLaSEL().getLaclave() != null) {
                w.println("              READ " + NOMBRE
                        + " WITH IGNORE LOCK INVALID KEY");
                w.println("                   MOVE 1 TO INVALIDKEY");
                w.println("                   GO TO FIN-LEER");
            } else {
                w.println("              READ " + NOMBRE + " WITH IGNORE LOCK AT END");
                w.println("                   MOVE 1 TO INVALIDKEY");
                w.println("                   GO TO FIN-LEER");
            }
            w.println("           ELSE");
            if (getLaSEL().getLaclave() != null) {
                w.println("              READ " + NOMBRE + " INVALID KEY");
                w.println("                   MOVE 1 TO INVALIDKEY");
                w.println("                   GO TO FIN-LEER");
            } else {
                w.println("              READ " + NOMBRE);
            }
            w.println("           END-IF.");
            w.println("           IF ERROR-1 NOT = \"0\"");
            w.println("              MOVE 2 TO INVALIDKEY");
            w.println("              GO TO FIN-LEER");
            w.println("           END-IF.");
            w.println("           PERFORM MOVECTOJ.");
            w.println("       FIN-LEER.");
            w.println("           MOVE 1 TO LONGI");
            w.println("           IF ESCRIBIBLE = \"P\"");
            w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
            w.println("           ELSE");
            w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
            w.println("           END-IF.");
            w.println("");
            w.println("       ESCRIBIR SECTION.");
            w.println("       ESCR.");
            w.println("           PERFORM MOVEJTOC.");
            w.println("           MOVE 0 TO INVALIDKEY.");
            w.println("           IF ABIERTO < 1 OR > 3");
            if (getLaSEL().getLaclave() != null) {
                w.println("              PERFORM ABREIO");
            } else {
                w.println("              PERFORM ABREEXTEND");
            }
            w.println("           END-IF.");
            w.println("           IF ABIERTO = 1");
            w.println("              PERFORM CERRAR");
            if (getLaSEL().getLaclave() != null) {
                w.println("              PERFORM ABREIO");
            } else {
                w.println("              PERFORM ABREEXTEND");
            }
            w.println("           END-IF.");
            if (getLaSEL().getLaclave() != null) {
                w.println("           WRITE " + RECORD + " INVALID KEY");
                w.println("                 MOVE 1 TO INVALIDKEY");
                w.println("                 GO TO FIN-ESCRIBIR.");
            } else {
                w.println("           WRITE " + RECORD + ".");
            }
            w.println("           IF ERROR-1 NOT = \"0\"");
            w.println("              MOVE 2 TO INVALIDKEY");
            w.println("              GO TO FIN-ESCRIBIR");
            w.println("           END-IF.");
            w.println("           IF TRAZAR = \"true\"");
            w.println("              MOVE \"A\" TO ABM");
            w.println("              MOVE " + RECORD + " TO REGISTRO");
            w.println("              CALL \"SRUTRAZA\" USING");
            w.println("                   NOMBRE" + NOMBRE);
            w.println("                   ABM");
            w.println("                   REGISTRO");
            w.println("           END-IF.");
            w.println("       FIN-ESCRIBIR.");
            w.println("           MOVE 1 TO LONGI");
            w.println("           IF ESCRIBIBLE = \"P\"");
            w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
            w.println("           ELSE");
            w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
            w.println("           END-IF.");
            w.println("");
            w.println("       REESCRIBIR SECTION.");
            w.println("       REESCR.");
            w.println("           PERFORM MOVEJTOC.");
            w.println("           MOVE 0 TO INVALIDKEY.");
            w.println("           IF ABIERTO < 1 OR > 3");
            if (getLaSEL().getLaclave() != null) {
                w.println("              PERFORM ABREIO");
            } else {
                w.println("              PERFORM ABREEXTEND");
            }
            w.println("           END-IF.");
            w.println("           IF ABIERTO = 1 OR 3");
            w.println("              PERFORM CERRAR");
            if (getLaSEL().getLaclave() != null) {
                w.println("              PERFORM ABREIO");
            } else {
                w.println("              PERFORM ABREEXTEND");
            }
            w.println("           END-IF.");
            if (getLaSEL().getLaclave() != null) {
                w.println("           REWRITE " + RECORD + " INVALID KEY");
                w.println("                   MOVE 1 TO INVALIDKEY");
                w.println("                   GO TO FIN-REESCRIBIR.");
            } else {
                w.println("           REWRITE " + RECORD + ".");
            }
            w.println("           IF ERROR-1 NOT = \"0\"");
            w.println("              MOVE 2 TO INVALIDKEY");
            w.println("              GO TO FIN-REESCRIBIR");
            w.println("           END-IF.");
            w.println("           IF TRAZAR = \"true\"");
            w.println("              MOVE \"M\" TO ABM");
            w.println("              MOVE " + RECORD + " TO REGISTRO");
            w.println("              CALL \"SRUTRAZA\" USING");
            w.println("                   NOMBRE" + NOMBRE);
            w.println("                   ABM");
            w.println("                   REGISTRO");
            w.println("           END-IF.");
            w.println("       FIN-REESCRIBIR.");
            w.println("           MOVE 1 TO LONGI");
            w.println("           IF ESCRIBIBLE = \"P\"");
            w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
            w.println("           ELSE");
            w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
            w.println("           END-IF.");
            w.println("");
            if (getLaSEL().getLaclave() != null) {
                w.println("       BORRAR SECTION.");
                w.println("       BO.");
                w.println("           PERFORM MOVEJTOC.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           IF ABIERTO < 1 OR > 3");
                w.println("              PERFORM ABREIO");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 1 OR 3");
                w.println("              PERFORM CERRAR");
                w.println("              PERFORM ABREIO");
                w.println("           END-IF.");
                w.println("           DELETE " + NOMBRE + " INVALID KEY");
                w.println("                  MOVE 1 TO INVALIDKEY");
                w.println("                  GO TO FIN-BORRAR.");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("              GO TO FIN-BORRAR");
                w.println("           END-IF.");
                w.println("           IF TRAZAR = \"true\"");
                w.println("              MOVE \"B\" TO ABM");
                w.println("              MOVE " + RECORD + " TO REGISTRO");
                w.println("              CALL \"SRUTRAZA\" USING");
                w.println("                   NOMBRE" + NOMBRE);
                w.println("                   ABM");
                w.println("                   REGISTRO");
                w.println("           END-IF.");
                w.println("       FIN-BORRAR.");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");
                w.println("");
            }

            if (getLaSEL().getLaclave() != null) {
                w.println("       STMENOR SECTION.");
                w.println("       STME.");
                w.println("           PERFORM MOVEJTOC.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           MOVE 0 TO INVALIDKEYNEXT.");
                w.println("           MOVE 0 TO INVALIDKEYPREV.");
                w.println("           IF ABIERTO < 1 OR > 3");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 3");
                w.println("              PERFORM CERRAR");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           START " + NOMBRE + " KEY < " + getLaSEL().getLaclave()
                        + " INVALID KEY");
                w.println("                 MOVE 1 TO INVALIDKEY");
                w.println("                 GO TO FIN-STMENOR.");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("              GO TO FIN-STMENOR");
                w.println("           END-IF.");
                w.println("       FIN-STMENOR.");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYNEXT.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYPREV.");
                w.println("");
                w.println("       STMAYOR SECTION.");
                w.println("       STMA.");
                w.println("           PERFORM MOVEJTOC.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           MOVE 0 TO INVALIDKEYNEXT.");
                w.println("           MOVE 0 TO INVALIDKEYPREV.");
                w.println("           IF ABIERTO < 1 OR > 3");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 3");
                w.println("              PERFORM CERRAR");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           START " + NOMBRE + " KEY > " + getLaSEL().getLaclave()
                        + " INVALID KEY");
                w.println("                 MOVE 1 TO INVALIDKEY");
                w.println("                 GO TO FIN-STMAYOR.");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("              GO TO FIN-STMAYOR");
                w.println("           END-IF.");
                w.println("       FIN-STMAYOR.");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");

                w.println("           MOVE INVALIDKEY TO INVALIDKEYNEXT.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYPREV.");
                w.println("");
                w.println("       STNOMENOR SECTION.");
                w.println("       STANOME.");
                w.println("           PERFORM MOVEJTOC.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           MOVE 0 TO INVALIDKEYNEXT.");
                w.println("           MOVE 0 TO INVALIDKEYPREV.");
                w.println("           IF ABIERTO < 1 OR > 3");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 3");
                w.println("              PERFORM CERRAR");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           START " + NOMBRE + " KEY NOT < " + getLaSEL().getLaclave()
                        + " INVALID KEY");
                w.println("                 MOVE 1 TO INVALIDKEY");
                w.println("                 GO TO FIN-STNOMENOR.");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("              GO TO FIN-STNOMENOR");
                w.println("           END-IF.");
                w.println("       FIN-STNOMENOR.");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYNEXT.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYPREV.");
                w.println("");
                w.println("       STNOMAYOR SECTION.");
                w.println("       STANOMA.");
                w.println("           PERFORM MOVEJTOC.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           MOVE 0 TO INVALIDKEYNEXT.");
                w.println("           MOVE 0 TO INVALIDKEYPREV.");
                w.println("           IF ABIERTO < 1 OR > 3");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 3");
                w.println("              PERFORM CERRAR");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           START " + NOMBRE + " KEY NOT > " + getLaSEL().getLaclave()
                        + " INVALID KEY");
                w.println("                 MOVE 1 TO INVALIDKEY");
                w.println("                 GO TO FIN-STNOMAYOR.");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("              GO TO FIN-STNOMAYOR");
                w.println("           END-IF.");
                w.println("       FIN-STNOMAYOR.");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYNEXT.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYPREV.");
                w.println("");
            }
            w.println("       LEERNEXT SECTION.");
            w.println("       LENE.");
            w.println("           MOVE 0 TO INVALIDKEY.");
            w.println("           MOVE 0 TO INVALIDKEYPREV.");
            w.println("           IF INVALIDKEYNEXT = 1");
            w.println("              MOVE 1 TO INVALIDKEY");
            w.println("              GO TO FIN-LEERNEXT");
            w.println("           END-IF.");
            w.println("           IF IGNORELOCK = 1");
            w.println("              READ " + NOMBRE
                    + " NEXT RECORD WITH IGNORE LOCK AT END");
            w.println("                   MOVE 1 TO INVALIDKEY");
            w.println("                   GO TO FIN-LEERNEXT");
            w.println("           ELSE");
            w.println("              READ " + NOMBRE + " NEXT RECORD AT END");
            w.println("                   MOVE 1 TO INVALIDKEY");
            w.println("                   GO TO FIN-LEERNEXT");
            w.println("           END-IF.");
            w.println("           IF ERROR-1 NOT = \"0\"");
            w.println("              MOVE 2 TO INVALIDKEY");
            w.println("              GO TO FIN-LEERNEXT");
            w.println("           END-IF.");
            w.println("           PERFORM MOVECTOJ.");
            w.println("       FIN-LEERNEXT.");
            w.println("           MOVE 1 TO LONGI");
            w.println("           IF ESCRIBIBLE = \"P\"");
            w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
            w.println("           ELSE");
            w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
            w.println("           END-IF.");
            w.println("           MOVE INVALIDKEY TO INVALIDKEYNEXT.");
            w.println("");

            if (getLaSEL().getLaclave() != null) {
                w.println("       LEERPREVIOUS SECTION.");
                w.println("       LEPR.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           MOVE 0 TO INVALIDKEYNEXT.");
                w.println("           IF INVALIDKEYPREV = 1");
                w.println("              MOVE 1 TO INVALIDKEY");
                w.println("              GO TO FIN-LEERPREVIOUS");
                w.println("           END-IF.");
                w.println("           IF IGNORELOCK = 1");
                w.println("              READ " + NOMBRE
                        + " PREVIOUS RECORD WITH IGNORE LOCK AT END");
                w.println("                   MOVE 1 TO INVALIDKEY");
                w.println("                   GO TO FIN-LEERPREVIOUS");
                w.println("           ELSE");
                w.println("              READ " + NOMBRE + " PREVIOUS RECORD AT END");
                w.println("                   MOVE 1 TO INVALIDKEY");
                w.println("                   GO TO FIN-LEERPREVIOUS");
                w.println("           END-IF.");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("              GO TO FIN-LEERPREVIOUS");
                w.println("           END-IF.");
                w.println("           PERFORM MOVECTOJ.");
                w.println("       FIN-LEERPREVIOUS.");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");

                w.println("           MOVE INVALIDKEY TO INVALIDKEYPREV.");
                w.println("");
            }

            for (int ind = 0; ind < getLaSEL().getNalternates(); ind++) {
                w.println("       LK" + getLaSEL().getAlternate()[ind] + " SECTION.");
                w.println("       LEER-PR.");
                w.println("           PERFORM MOVEJTOC.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           IF ABIERTO < 1 OR > 3");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 3");
                w.println("              PERFORM CERRAR");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           IF IGNORELOCK = 1");
                w.println("              READ " + NOMBRE + " WITH IGNORE LOCK KEY IS "
                        + getLaSEL().getAlternate()[ind]);
                w.println("                   INVALID KEY");
                w.println("                   MOVE 1 TO INVALIDKEY");
                w.println("                   GO TO FIN-LEER");
                w.println("           ELSE");
                w.println("              READ " + NOMBRE + " KEY IS "
                        + getLaSEL().getAlternate()[ind] + " INVALID KEY");
                w.println("                   MOVE 1 TO INVALIDKEY");
                w.println("                   GO TO FIN-LEER");
                w.println("           END-IF.");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("              GO TO FIN-LEER");
                w.println("           END-IF.");
                w.println("           PERFORM MOVECTOJ.");
                w.println("       FIN-LK" + getLaSEL().getAlternate()[ind] + ".");
                w.println("       FIN-LEER.");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");
                w.println("");
                w.println("       STMK" + getLaSEL().getAlternate()[ind] + " SECTION.");
                w.println("       STME.");
                w.println("           PERFORM MOVEJTOC.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           MOVE 0 TO INVALIDKEYNEXT.");
                w.println("           MOVE 0 TO INVALIDKEYPREV.");
                w.println("           IF ABIERTO < 1 OR > 3");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 3");
                w.println("              PERFORM CERRAR");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           START " + NOMBRE + " KEY < "
                        + getLaSEL().getAlternate()[ind] + " INVALID KEY");
                w.println("                 MOVE 1 TO INVALIDKEY");
                w.println("                 GO TO FIN-STMK" + getLaSEL().getAlternate()[ind]
                        + ".");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("           END-IF.");
                w.println("       FIN-STMK" + getLaSEL().getAlternate()[ind] + ".");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYNEXT.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYPREV.");
                w.println("");
                w.println("       STYK" + getLaSEL().getAlternate()[ind] + " SECTION.");
                w.println("       STMA.");
                w.println("           PERFORM MOVEJTOC.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           MOVE 0 TO INVALIDKEYNEXT.");
                w.println("           MOVE 0 TO INVALIDKEYPREV.");
                w.println("           IF ABIERTO < 1 OR > 3");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 3");
                w.println("              PERFORM CERRAR");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           START " + NOMBRE + " KEY > "
                        + getLaSEL().getAlternate()[ind] + " INVALID KEY");
                w.println("                 MOVE 1 TO INVALIDKEY");
                w.println("                 GO TO FIN-STYK" + getLaSEL().getAlternate()[ind]
                        + ".");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("           END-IF.");
                w.println("       FIN-STYK" + getLaSEL().getAlternate()[ind] + ".");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYNEXT.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYPREV.");
                w.println("");
                w.println("       STNMK" + getLaSEL().getAlternate()[ind] + " SECTION.");
                w.println("       STME.");
                w.println("           PERFORM MOVEJTOC.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           MOVE 0 TO INVALIDKEYNEXT.");
                w.println("           MOVE 0 TO INVALIDKEYPREV.");
                w.println("           IF ABIERTO < 1 OR > 3");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 3");
                w.println("              PERFORM CERRAR");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           START " + NOMBRE + " KEY NOT < "
                        + getLaSEL().getAlternate()[ind] + " INVALID KEY");
                w.println("                 MOVE 1 TO INVALIDKEY");
                w.println("                 GO TO FIN-STNMK" + getLaSEL().getAlternate()[ind]
                        + ".");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("           END-IF.");
                w.println("       FIN-STNMK" + getLaSEL().getAlternate()[ind] + ".");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYNEXT.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYPREV.");
                w.println("");
                w.println("       STNYK" + getLaSEL().getAlternate()[ind] + " SECTION.");
                w.println("       STMA.");
                w.println("           PERFORM MOVEJTOC.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           MOVE 0 TO INVALIDKEYNEXT.");
                w.println("           MOVE 0 TO INVALIDKEYPREV.");
                w.println("           IF ABIERTO < 1 OR > 3");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           IF ABIERTO = 3");
                w.println("              PERFORM CERRAR");
                w.println("              PERFORM ABREINPUT");
                w.println("           END-IF.");
                w.println("           MOVE 0 TO INVALIDKEY.");
                w.println("           START " + NOMBRE + " KEY NOT > "
                        + getLaSEL().getAlternate()[ind] + " INVALID KEY");
                w.println("                 MOVE 1 TO INVALIDKEY");
                w.println("                 GO TO FIN-STNYK" + getLaSEL().getAlternate()[ind]
                        + ".");
                w.println("           IF ERROR-1 NOT = \"0\"");
                w.println("              MOVE 2 TO INVALIDKEY");
                w.println("           END-IF.");
                w.println("       FIN-STNYK" + getLaSEL().getAlternate()[ind] + ".");
                w.println("           MOVE 1 TO LONGI");
                w.println("           IF ESCRIBIBLE = \"P\"");
                w.println("              CALL \"Escribir\" USING INVALIDKEY LONGI");
                w.println("           ELSE");
                w.println("              DISPLAY INVALIDKEY UPON SYSOUT");
                w.println("           END-IF.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYNEXT.");
                w.println("           MOVE INVALIDKEY TO INVALIDKEYPREV.");
                w.println("");
            }
            w.println("       HACDIR SECTION.");
            w.println("       HACIENDODIR.");
            w.println("           MOVE SPACES TO VALORDIR.");
            w.println("           MOVE SPACES TO NOMBRDIR.");
            w.println("           STRING \"dd_\" NOMBRE" + NOMBRE + "(1:3)");
            w.println("                  DELIMITED BY \" \"");
            w.println("                  INTO NOMBRDIR");
            w.println("           END-STRING.");
            w.println("           DISPLAY NOMBRDIR UPON ENVIRONMENT-NAME.");
            w.println("           ACCEPT VALORDIR FROM ENVIRONMENT-VALUE.");
            w.println("           IF VALORDIR NOT = SPACES AND");
            w.println("              VALORDIR NOT = LOW-VALUES");
            w.println("              MOVE SPACES TO BUFER-DIR");
            w.println("              STRING VALORDIR NOMBRE" + NOMBRE + "(4:)");
            w.println("                     DELIMITED BY \" \"");
            w.println("                     INTO BUFER-DIR");
            w.println("              END-STRING");
            w.println("           ELSE");
            w.println("              MOVE NOMBRE" + NOMBRE + " TO BUFER-DIR");
            w.println("           END-IF.");
            w.println("           MOVE SPACES TO DIRECTORIO.");
            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > 256");
            w.println("                   IF BUF-1(II) = \"/\" OR \"\\\"");
            w.println(
                    "                      CALL \"CBL_CREATE_DIR\" USING DIRECTORIO ESTADO");
            w.println("                   END-IF");
            w.println("                   MOVE BUF-1(II) TO DIR-1(II)");
            w.println("                   IF DIR-1(II) = \" \"");
            w.println("                      MOVE 256 TO II");
            w.println("                   END-IF");
            w.println("           END-PERFORM.");
            w.println("       FIN-HACDIR.");
            w.println("");
            w.println("");
            w.println("       PONNOMBRE SECTION.");
            w.println("       PON.");
            w.println("           MOVE VALORES TO NOMBRE" + NOMBRE + ".");
            w.println("       FIN-PONNOMBRE.");
            w.println("");
            w.println("       SETIGNORELOCK SECTION.");
            w.println("       PON.");
            w.println("           MOVE \"1\" TO IGNORELOCK.");
            w.println("           IF VALORES(1:1) = \"0\"");
            w.println("              MOVE \"0\" TO IGNORELOCK.");
            w.println("       FIN-IGNORELOCK.");
            w.println("");
            w.println("       GETERRORES SECTION.");
            w.println("       GON.");
            w.println("           MOVE 2 TO LONGI");
            w.println("           IF ESCRIBIBLE = \"P\"");
            w.println("              CALL \"Escribir\" USING ERRORES LONGI");
            w.println("           ELSE");
            w.println("              DISPLAY ERRORES UPON SYSOUT");
            w.println("           END-IF.");

            w.println("");
            if (getLaSEL().isEsrelativo()) {
                w.println("       GET" + LAKEY + " SECTION.");
                w.println("       GLK.");
                w.println("           MOVE 1 TO LONGI");
                w.println("           DISPLAY " + LAKEY + " UPON SYSOUT");
                w.println("       FIN-GET" + LAKEY + ".");
                w.println("");
                w.println("       SET" + LAKEY + " SECTION.");
                w.println("       SLK.");
                w.println("           MOVE VALORES TO ENTEXTO.");
                w.println("           PERFORM PASA-A-NUMERO.");
                w.println("           MOVE ENVALOR TO " + LAKEY + ".");
                w.println("       FIN-SET" + LAKEY + ".");
                w.println("       INI" + LAKEY + " SECTION.");
                w.println("       SLK.");
                w.println("           INITIALIZE " + LAKEY + ".");
                w.println("       FIN-INI" + LAKEY + ".");
                w.println("");
            }
            w.println("");
            w.println("       GETALL SECTION.");
            w.println("       GT.");
            w.println("           MOVE " + getLaFD().getHijos()[0].getLongitud() + " TO LONGI");
            w.println("           IF ESCRIBIBLE = \"P\"");
            w.println("              CALL \"Escribir\" USING J" + RECORD + " LONGI");
            w.println("           ELSE");
            w.println("              DISPLAY J" + RECORD + " UPON SYSOUT");
            w.println("           END-IF.");
            w.println("       FIN-GETALL.");
            w.println("");
            w.println("       SETALL SECTION.");
            w.println("       ST.");
            w.println("           MOVE VALORES TO J" + RECORD + ".");
            w.println("       FIN-SETALL.");
            w.println("");
            w.println("       MOVEJTOC SECTION.");
            w.println("       MJTOC.");
            for (int ind = 0; ind < getLaFD().getNhijos(); ind++) {
                movejtoc(w, getLaFD().getHijos()[ind]);
            }
            w.println("       FIN-MOVEJTOC.");
            w.println("");
            w.println("       MOVECTOJ SECTION.");
            w.println("       MCTOJ.");
            for (int ind = 0; ind < getLaFD().getNhijos(); ind++) {
                movectoj(w, getLaFD().getHijos()[ind]);
            }
            w.println("       FIN-MOVECTOJ.");
            w.println("");

            w.println("       CARGATODOS SECTION.");
            w.println("       INI-CARGA.");
            w.println("           IF ABIERTO < 1 OR > 3");
            w.println("              PERFORM ABREINPUT");
            w.println("           END-IF.");
            w.println("           IF ABIERTO = 3");
            w.println("              PERFORM CERRAR");
            w.println("              PERFORM ABREINPUT");
            w.println("           END-IF.");
            w.println("           DISPLAY \"|INI-MUCHOS|\" UPON SYSOUT.");
            w.println("           INITIALIZE " + RECORD + ".");
            w.println("           IF VALORES = SPACES");
            w.println("              START " + NOMBRE);
            w.println("                 KEY NOT < " + getLaSEL().getLaclave());
            w.println("                 INVALID KEY");
            w.println("                 GO TO FIN-CARGATODOS");
            w.println("           END-IF.");
            for (int ind = 0; ind < getLaSEL().getNalternates(); ind++) {
                w.println("           IF VALORES = " + (ind + 1));
                w.println("              START " + NOMBRE);
                w.println("                 KEY NOT < " + getLaSEL().getAlternate()[ind]);
                w.println("                 INVALID KEY");
                w.println("                 GO TO FIN-CARGATODOS");
                w.println("           END-IF.");
            }
            w.println("       LEE-CARGA.");
            w.println("           READ " + NOMBRE + " NEXT RECORD WITH IGNORE LOCK");
            w.println("                AT END GO TO FIN-CARGATODOS.");
            w.println("           PERFORM MOVECTOJ.");
            w.println("           PERFORM GETALL.");
            w.println("           GO TO LEE-CARGA.");
            w.println("       FIN-CARGATODOS.");
            w.println("           DISPLAY \"|FIN-MUCHOS|\" UPON SYSOUT.");

            w.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void defincobol(PrintWriter w, CobolItem ci) {
        String sn = "J" + ci.getNombre();
        int c = ci.getVecesA();

        w.print("      ");
        for (int i = 0; i < ci.getNivel(); i++) {
            w.print(" ");
        }
        w.print("0" + ci.getNivel() + " " + sn);
        if (ci.isRedefines()) {
            w.println("");
            w.print("      ");
            for (int i = 0; i < ci.getNivel(); i++) {
                w.print(" ");
            }
            w.print("   REDEFINES J" + ci.getRedefinida());
        }
        if (ci.getTipo().equals("X")) {
            w.print(" PIC X(" + ci.getLongitud() + ")");
        }
        if (ci.getTipo().equals("B")) {
            w.print(" PIC X(" + ci.getLongitud() + ")");
        }

        if (ci.getTipo().equals("S")) {
            w.print(" PIC S9(" + (ci.getLongitud() - ci.getDecimales() - 1) + ")");
        }
        if (ci.getTipo().equals("9")) {
            w.print(" PIC 9(" + (ci.getLongitud() - ci.getDecimales()) + ")");
        }
        if (ci.getDecimales() > 0) {
            w.print("V9(" + ci.getDecimales() + ")");
        }
        if (ci.isConsigno()) {
            w.println("");
            w.print("      ");
            for (int i = 0; i < ci.getNivel(); i++) {
                w.print(" ");
            }
            w.print("   SIGN LEADING SEPARATE");
        }

        if (c > 1) {
            w.print(" OCCURS " + c);
            for (int i = 0; i < ci.getNhijos(); i++) {
                ci.getHijos()[i].setVecesA(ci.getHijos()[i].getVecesB());
                ci.getHijos()[i].setVecesB(ci.getHijos()[i].getVeces());
                ci.getHijos()[i].setVeces(1);
                izquierda(ci.getHijos()[i]);
            }
        }
        w.println(".");

        for (int i = 0; i < ci.getNhijos(); i++) {
            defincobol(w, ci.getHijos()[i]);
        }
        if (c > 1) {
            for (int i = 0; i < ci.getNhijos(); i++) {
                ci.getHijos()[i].setVeces(ci.getHijos()[i].getVecesB());
                ci.getHijos()[i].setVecesB(ci.getHijos()[i].getVecesA());
                ci.getHijos()[i].setVecesA(c);
                derecha(ci.getHijos()[i], c);
            }
        }
    }

    private void izquierda(CobolItem ci) {
        for (int i = 0; i < ci.getNhijos(); i++) {
            ci.getHijos()[i].setVecesA(ci.getHijos()[i].getVecesB());
            ci.getHijos()[i].setVecesB(ci.getHijos()[i].getVeces());
            ci.getHijos()[i].setVeces(1);
            izquierda(ci.getHijos()[i]);
        }
    }

    private void derecha(CobolItem ci, int c) {
        for (int i = 0; i < ci.getNhijos(); i++) {
            ci.getHijos()[i].setVeces(ci.getHijos()[i].getVecesB());
            ci.getHijos()[i].setVecesB(ci.getHijos()[i].getVecesA());
            ci.getHijos()[i].setVecesA(c);
            derecha(ci.getHijos()[i], c);
        }
    }

    private void movejtoc(PrintWriter w, CobolItem ci) {
        String sn = ci.getNombre();
        int a = ci.getVecesA();
        int b = ci.getVecesB();
        int c = ci.getVeces();
        if (!(sn.equals("FILLER"))) {
            if (ci.getTipo().length() > 0) {
                if (a > 1) {
                    if (b > 1) {
                        if (c > 1) {
                            for (int x = 0; x < c; x++) {
                                w.println(
                                        "           PERFORM VARYING II FROM 1 BY 1 UNTIL II > " + a);
                                w.println(
                                        "              PERFORM VARYING JJ FROM 1 BY 1 UNTIL JJ > "
                                        + b);
                                w.println(
                                        "                 PERFORM VARYING KK FROM 1 BY 1 UNTIL KK > "
                                        + c);
                                w.println("            MOVE J" + sn
                                        + "(II, JJ, KK) TO " + sn + "(II, JJ, KK)");
                                w.println("                 END-PERFORM");
                                w.println("              END-PERFORM");
                                w.println("           END-PERFORM.");
                            }
                        } else {
                            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > "
                                    + a);
                            w.println(
                                    "              PERFORM VARYING JJ FROM 1 BY 1 UNTIL JJ > "
                                    + b);
                            w.println("            MOVE J" + sn + "(II, JJ) TO " + sn
                                    + "(II, JJ)");
                            w.println("              END-PERFORM");
                            w.println("           END-PERFORM.");
                        }
                    } else {
                        if (c > 1) {
                            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > "
                                    + a);
                            w.println(
                                    "              PERFORM VARYING JJ FROM 1 BY 1 UNTIL JJ > "
                                    + c);
                            w.println("            MOVE J" + sn + "(II, JJ) TO " + sn
                                    + "(II, JJ)");
                            w.println("              END-PERFORM");
                            w.println("           END-PERFORM.");
                        } else {
                            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > "
                                    + a);
                            w.println("            MOVE J" + sn + "(II) TO " + sn + "(II)");
                            w.println("           END-PERFORM.");
                        }
                    }
                } else {
                    if (b > 1) {
                        if (c > 1) {
                            for (int x = 0; x < c; x++) {
                                w.println(
                                        "           PERFORM VARYING II FROM 1 BY 1 UNTIL II > " + b);
                                w.println(
                                        "              PERFORM VARYING JJ FROM 1 BY 1 UNTIL JJ > "
                                        + c);
                                w.println("            MOVE J" + sn + "(II, JJ) TO " + sn
                                        + "(II, JJ)");
                                w.println("              END-PERFORM");
                                w.println("           END-PERFORM.");
                            }
                        } else {
                            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > "
                                    + b);
                            w.println("            MOVE J" + sn + "(II) TO " + sn + "(II)");
                            w.println("           END-PERFORM.");
                        }
                    } else {
                        if (c > 1) {
                            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > "
                                    + c);
                            w.println("            MOVE J" + sn + "(II) TO " + sn + "(II)");
                            w.println("           END-PERFORM.");
                        } else {
                            w.println("           MOVE J" + sn + " TO " + sn + ".");
                        }
                    }
                }
            } else {
                for (int i = 0; i < ci.getNhijos(); i++) {
                    movejtoc(w, ci.getHijos()[i]);
                }
            }
        }
    }

    private void movectoj(PrintWriter w, CobolItem ci) {
        String sn = ci.getNombre();
        int a = ci.getVecesA();
        int b = ci.getVecesB();
        int c = ci.getVeces();
        if (!(sn.equals("FILLER"))) {
            if (ci.getTipo().length() > 0) {
                if (a > 1) {
                    if (b > 1) {
                        if (c > 1) {
                            for (int x = 0; x < c; x++) {
                                w.println(
                                        "           PERFORM VARYING II FROM 1 BY 1 UNTIL II > " + a);
                                w.println(
                                        "              PERFORM VARYING JJ FROM 1 BY 1 UNTIL JJ > "
                                        + b);
                                w.println(
                                        "                 PERFORM VARYING KK FROM 1 BY 1 UNTIL KK > "
                                        + c);
                                w.println("            MOVE " + sn
                                        + "(II, JJ, KK) TO J" + sn + "(II, JJ, KK)");
                                w.println("                 END-PERFORM");
                                w.println("              END-PERFORM");
                                w.println("           END-PERFORM.");
                            }
                        } else {
                            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > "
                                    + a);
                            w.println(
                                    "              PERFORM VARYING JJ FROM 1 BY 1 UNTIL JJ > "
                                    + b);
                            w.println("            MOVE " + sn + "(II, JJ) TO J" + sn
                                    + "(II, JJ)");
                            w.println("              END-PERFORM");
                            w.println("           END-PERFORM.");
                        }
                    } else {
                        if (c > 1) {
                            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > "
                                    + a);
                            w.println(
                                    "              PERFORM VARYING JJ FROM 1 BY 1 UNTIL JJ > "
                                    + c);
                            w.println("            MOVE " + sn + "(II, JJ) TO J" + sn
                                    + "(II, JJ)");
                            w.println("              END-PERFORM");
                            w.println("           END-PERFORM.");
                        } else {
                            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > "
                                    + a);
                            w.println("            MOVE " + sn + "(II) TO J" + sn + "(II)");
                            w.println("           END-PERFORM.");
                        }
                    }
                } else {
                    if (b > 1) {
                        if (c > 1) {
                            for (int x = 0; x < c; x++) {
                                w.println(
                                        "           PERFORM VARYING II FROM 1 BY 1 UNTIL II > " + b);
                                w.println(
                                        "              PERFORM VARYING JJ FROM 1 BY 1 UNTIL JJ > "
                                        + c);
                                w.println("            MOVE " + sn + "(II, JJ) TO J" + sn
                                        + "(II, JJ)");
                                w.println("              END-PERFORM");
                                w.println("           END-PERFORM.");
                            }
                        } else {
                            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > "
                                    + b);
                            w.println("            MOVE " + sn + "(II) TO J" + sn + "(II)");
                            w.println("           END-PERFORM.");
                        }
                    } else {
                        if (c > 1) {
                            w.println("           PERFORM VARYING II FROM 1 BY 1 UNTIL II > "
                                    + c);
                            w.println("            MOVE " + sn + "(II) TO J" + sn + "(II)");
                            w.println("           END-PERFORM.");
                        } else {
                            w.println("           MOVE " + sn + " TO J" + sn + ".");
                        }
                    }
                }
            } else {
                for (int i = 0; i < ci.getNhijos(); i++) {
                    movectoj(w, ci.getHijos()[i]);
                }
            }
        }
    }

    private void sqlando(PrintWriter w, CobolItem ci, int tipo) {
        String sn = ci.getNombre();
        int a = ci.getVecesA();
        int b = ci.getVecesB();
        int c = ci.getVeces();
        int mi = 0;
        int mj = 0;
        int mk = 0;
        if (!(sn.equals("FILLER"))) {
            if (tipo == 0) {
                if (a > 1) {
                    if (b > 1) {
                        if (c > 1) {
                            for (mi = 1; mi <= a; mi++) {
                                for (mj = 1; mj <= b; mj++) {
                                    for (mk = 1; mk <= c; mk++) {
                                        w.println("        WHEN \"" + ci.getNombre() + "-" + mi + "-"
                                                + mj + "-" + mk
                                                + "\"");
                                        if (!ci.isNumero()) {
                                            w.println("            MOVE J" + ci.getNombre() + "(" + mi
                                                    + "," + mj + "," + mk + ")"
                                                    + " TO VALOR-INI");
                                            w.println("            MOVE " + ci.getLongitud()
                                                    + " TO LONGITUD");
                                            w.println("        IF COMPARA NOT = SPACES");
                                            w.println("          IF VALOR-INI < COMPARA OR VALOR-INI > VALOR-FIN");
                                            w.println("             MOVE 3 TO INVALIDKEY");
                                            w.println("          END-IF");
                                            w.println("        END-IF");
                                        } else {
                                            w.println("            MOVE J" + ci.getNombre() + "(" + mi
                                                    + "," + mj + "," + mk + ")"
                                                    + " TO ENPIC");
                                            w.println("            MOVE ENPIC TO VALOR-INI");
                                            w.println("            MOVE 20 TO LONGITUD");
                                            w.println("        IF COMPARA NOT = SPACES");
                                            w.println("          MOVE COMPARA TO ENTEXTO");
                                            w.println("          PERFORM PASA-A-NUMERO");
                                            w.println("          MOVE ENVALOR TO COMPIC");
                                            w.println("          MOVE VALOR-FIN TO ENTEXTO");
                                            w.println("          PERFORM PASA-A-NUMERO");
                                            w.println("          MOVE ENVALOR TO FINPIC");
                                            w.println("          IF ENPIC < COMPIC OR ENPIC > FINPIC");
                                            w.println("             MOVE 3 TO INVALIDKEY");
                                            w.println("          END-IF");
                                            w.println("        END-IF");
                                        }
                                    }
                                }
                            }
                        } else {
                            for (mi = 1; mi <= a; mi++) {
                                for (mj = 1; mj <= b; mj++) {
                                    w.println("        WHEN \"" + ci.getNombre() + "-" + mi + "-"
                                            + mj
                                            + "\"");
                                    if (!ci.isNumero()) {
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi
                                                + "," + mj + ")"
                                                + " TO VALOR-INI");
                                        w.println("            MOVE " + ci.getLongitud()
                                                + " TO LONGITUD");
                                        w.println("        IF COMPARA NOT = SPACES");
                                        w.println("          IF VALOR-INI < COMPARA OR VALOR-INI > VALOR-FIN");
                                        w.println("             MOVE 3 TO INVALIDKEY");
                                        w.println("          END-IF");
                                        w.println("        END-IF");
                                    } else {
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi
                                                + "," + mj + ")"
                                                + " TO ENPIC");
                                        w.println("            MOVE ENPIC TO VALOR-INI");
                                        w.println("            MOVE 20 TO LONGITUD");
                                        w.println("        IF COMPARA NOT = SPACES");
                                        w.println("          MOVE COMPARA TO ENTEXTO");
                                        w.println("          PERFORM PASA-A-NUMERO");
                                        w.println("          MOVE ENVALOR TO COMPIC");
                                        w.println("          MOVE VALOR-FIN TO ENTEXTO");
                                        w.println("          PERFORM PASA-A-NUMERO");
                                        w.println("          MOVE ENVALOR TO FINPIC");
                                        w.println("          IF ENPIC < COMPIC OR ENPIC > FINPIC");
                                        w.println("             MOVE 3 TO INVALIDKEY");
                                        w.println("          END-IF");
                                        w.println("        END-IF");
                                    }
                                }
                            }
                        }
                    } else {
                        if (c > 1) {
                            for (mi = 1; mi <= a; mi++) {
                                for (mj = 1; mj <= c; mj++) {
                                    w.println("        WHEN \"" + ci.getNombre() + "-" + mi + "-"
                                            + mj
                                            + "\"");
                                    if (!ci.isNumero()) {
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi
                                                + "," + mj + ")"
                                                + " TO VALOR-INI");
                                        w.println("            MOVE " + ci.getLongitud()
                                                + " TO LONGITUD");
                                        w.println("        IF COMPARA NOT = SPACES");
                                        w.println("          IF VALOR-INI < COMPARA OR VALOR-INI > VALOR-FIN");
                                        w.println("             MOVE 3 TO INVALIDKEY");
                                        w.println("          END-IF");
                                        w.println("        END-IF");
                                    } else {
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi
                                                + "," + mj + ")"
                                                + " TO ENPIC");
                                        w.println("            MOVE ENPIC TO VALOR-INI");
                                        w.println("            MOVE 20 TO LONGITUD");
                                        w.println("        IF COMPARA NOT = SPACES");
                                        w.println("          MOVE COMPARA TO ENTEXTO");
                                        w.println("          PERFORM PASA-A-NUMERO");
                                        w.println("          MOVE ENVALOR TO COMPIC");
                                        w.println("          MOVE VALOR-FIN TO ENTEXTO");
                                        w.println("          PERFORM PASA-A-NUMERO");
                                        w.println("          MOVE ENVALOR TO FINPIC");
                                        w.println("          IF ENPIC < COMPIC OR ENPIC > FINPIC");
                                        w.println("             MOVE 3 TO INVALIDKEY");
                                        w.println("          END-IF");
                                        w.println("        END-IF");
                                    }
                                }
                            }

                        } else {
                            for (mi = 1; mi <= a; mi++) {
                                w.println("        WHEN \"" + ci.getNombre() + "-" + mi
                                        + "\"");
                                if (!ci.isNumero()) {
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")"
                                            + " TO VALOR-INI");
                                    w.println("            MOVE " + ci.getLongitud() + " TO LONGITUD");
                                    w.println("        IF COMPARA NOT = SPACES");
                                    w.println(
                                            "          IF VALOR-INI < COMPARA OR VALOR-INI > VALOR-FIN");
                                    w.println("             MOVE 3 TO INVALIDKEY");
                                    w.println("          END-IF");
                                    w.println("        END-IF");
                                } else {
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")"
                                            + " TO ENPIC");
                                    w.println("            MOVE ENPIC TO VALOR-INI");
                                    w.println("            MOVE 20 TO LONGITUD");
                                    w.println("        IF COMPARA NOT = SPACES");
                                    w.println("          MOVE COMPARA TO ENTEXTO");
                                    w.println("          PERFORM PASA-A-NUMERO");
                                    w.println("          MOVE ENVALOR TO COMPIC");
                                    w.println("          MOVE VALOR-FIN TO ENTEXTO");
                                    w.println("          PERFORM PASA-A-NUMERO");
                                    w.println("          MOVE ENVALOR TO FINPIC");
                                    w.println("          IF ENPIC < COMPIC OR ENPIC > FINPIC");
                                    w.println("             MOVE 3 TO INVALIDKEY");
                                    w.println("          END-IF");
                                    w.println("        END-IF");
                                }
                            }
                        }
                    }
                } else {
                    if (b > 1) {
                        if (c > 1) {
                            for (mi = 1; mi <= b; mi++) {
                                for (mj = 1; mj <= c; mj++) {
                                    w.println("        WHEN \"" + ci.getNombre() + "-" + mi + "-"
                                            + mj
                                            + "\"");
                                    if (!ci.isNumero()) {
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi
                                                + "," + mj + ")"
                                                + " TO VALOR-INI");
                                        w.println("            MOVE " + ci.getLongitud()
                                                + " TO LONGITUD");
                                        w.println("        IF COMPARA NOT = SPACES");
                                        w.println("          IF VALOR-INI < COMPARA OR VALOR-INI > VALOR-FIN");
                                        w.println("             MOVE 3 TO INVALIDKEY");
                                        w.println("          END-IF");
                                        w.println("        END-IF");
                                    } else {
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi
                                                + "," + mj + ")"
                                                + " TO ENPIC");
                                        w.println("            MOVE ENPIC TO VALOR-INI");
                                        w.println("            MOVE 20 TO LONGITUD");
                                        w.println("        IF COMPARA NOT = SPACES");
                                        w.println("          MOVE COMPARA TO ENTEXTO");
                                        w.println("          PERFORM PASA-A-NUMERO");
                                        w.println("          MOVE ENVALOR TO COMPIC");
                                        w.println("          MOVE VALOR-FIN TO ENTEXTO");
                                        w.println("          PERFORM PASA-A-NUMERO");
                                        w.println("          MOVE ENVALOR TO FINPIC");
                                        w.println("          IF ENPIC < COMPIC OR ENPIC > FINPIC");
                                        w.println("             MOVE 3 TO INVALIDKEY");
                                        w.println("          END-IF");
                                        w.println("        END-IF");
                                    }
                                }
                            }
                        } else {
                            for (mi = 1; mi <= b; mi++) {
                                w.println("        WHEN \"" + ci.getNombre() + "-" + mi
                                        + "\"");
                                if (!ci.isNumero()) {
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")"
                                            + " TO VALOR-INI");
                                    w.println("            MOVE " + ci.getLongitud() + " TO LONGITUD");
                                    w.println("        IF COMPARA NOT = SPACES");
                                    w.println("          IF VALOR-INI < COMPARA OR VALOR-INI > VALOR-FIN");
                                    w.println("             MOVE 3 TO INVALIDKEY");
                                    w.println("          END-IF");
                                    w.println("        END-IF");
                                } else {
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")"
                                            + " TO ENPIC");
                                    w.println("            MOVE ENPIC TO VALOR-INI");
                                    w.println("            MOVE 20 TO LONGITUD");
                                    w.println("        IF COMPARA NOT = SPACES");
                                    w.println("          MOVE COMPARA TO ENTEXTO");
                                    w.println("          PERFORM PASA-A-NUMERO");
                                    w.println("          MOVE ENVALOR TO COMPIC");
                                    w.println("          MOVE VALOR-FIN TO ENTEXTO");
                                    w.println("          PERFORM PASA-A-NUMERO");
                                    w.println("          MOVE ENVALOR TO FINPIC");
                                    w.println("          IF ENPIC < COMPIC OR ENPIC > FINPIC");
                                    w.println("             MOVE 3 TO INVALIDKEY");
                                    w.println("          END-IF");
                                    w.println("        END-IF");
                                }
                            }
                        }
                    } else {
                        if (c > 1) {
                            for (mi = 1; mi <= c; mi++) {
                                w.println("        WHEN \"" + ci.getNombre() + "-" + mi
                                        + "\"");
                                if (!ci.isNumero()) {
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")"
                                            + " TO VALOR-INI");
                                    w.println("            MOVE " + ci.getLongitud() + " TO LONGITUD");
                                    w.println("        IF COMPARA NOT = SPACES");
                                    w.println("          IF VALOR-INI < COMPARA OR VALOR-INI > VALOR-FIN");
                                    w.println("             MOVE 3 TO INVALIDKEY");
                                    w.println("          END-IF");
                                    w.println("        END-IF");
                                } else {
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")"
                                            + " TO ENPIC");
                                    w.println("            MOVE ENPIC TO VALOR-INI");
                                    w.println("            MOVE 20 TO LONGITUD");
                                    w.println("        IF COMPARA NOT = SPACES");
                                    w.println("          MOVE COMPARA TO ENTEXTO");
                                    w.println("          PERFORM PASA-A-NUMERO");
                                    w.println("          MOVE ENVALOR TO COMPIC");
                                    w.println("          MOVE VALOR-FIN TO ENTEXTO");
                                    w.println("          PERFORM PASA-A-NUMERO");
                                    w.println("          MOVE ENVALOR TO FINPIC");
                                    w.println("          IF ENPIC < COMPIC OR ENPIC > FINPIC");
                                    w.println("             MOVE 3 TO INVALIDKEY");
                                    w.println("          END-IF");
                                    w.println("        END-IF");
                                }
                            }
                        } else {
                            w.println("        WHEN \"" + ci.getNombre()
                                    + "\"");
                            if (!ci.isNumero()) {
                                w.println("            MOVE J" + ci.getNombre()
                                        + " TO VALOR-INI");
                                w.println("            MOVE " + ci.getLongitud() + " TO LONGITUD");
                                w.println("        IF COMPARA NOT = SPACES");
                                w.println("          IF VALOR-INI < COMPARA OR VALOR-INI > VALOR-FIN");
                                w.println("             MOVE 3 TO INVALIDKEY");
                                w.println("          END-IF");
                                w.println("        END-IF");
                            } else {
                                w.println("            MOVE J" + ci.getNombre()
                                        + " TO ENPIC");
                                w.println("            MOVE ENPIC TO VALOR-INI");
                                w.println("            MOVE 20 TO LONGITUD");
                                w.println("        IF COMPARA NOT = SPACES");
                                w.println("          MOVE COMPARA TO ENTEXTO");
                                w.println("          PERFORM PASA-A-NUMERO");
                                w.println("          MOVE ENVALOR TO COMPIC");
                                w.println("          MOVE VALOR-FIN TO ENTEXTO");
                                w.println("          PERFORM PASA-A-NUMERO");
                                w.println("          MOVE ENVALOR TO FINPIC");
                                w.println("          IF ENPIC < COMPIC OR ENPIC > FINPIC");
                                w.println("             MOVE 3 TO INVALIDKEY");
                                w.println("          END-IF");
                                w.println("        END-IF");
                            }
                        }
                    }
                }
            }
            if (tipo == 1) {
                if (a > 1) {
                    if (b > 1) {
                        if (c > 1) {
                            for (mi = 1; mi <= a; mi++) {
                                for (mj = 1; mj <= b; mj++) {
                                    for (mk = 1; mk <= c; mk++) {
                                        w.println("        WHEN \"" + ci.getNombre() + "-" + mi + "-" + mj + "-" + mk
                                                + "\"");
                                        if (!ci.isNumero()) {
                                            w.println("            MOVE VALOR-INI TO J" + ci.getNombre() + "("
                                                    + mi + "," + mj + "," + mk + ")");
                                            w.println("            MOVE J" + ci.getNombre() + "(" + mi + "," + mj + "," + mk + ")"
                                                    + " TO " + ci.getNombre() + "(" + mi + "," + mj + "," + mk + ")");
                                        } else {
                                            w.println("            MOVE VALOR-INI TO ENTEXTO");
                                            w.println("            PERFORM PASA-A-NUMERO");
                                            w.println("            MOVE ENVALOR TO ENPIC");
                                            w.println("            MOVE ENPIC TO J" + ci.getNombre() + "(" + mi + "," + mj + "," + mk
                                                    + ")");
                                            w.println("            MOVE J" + ci.getNombre() + "(" + mi + "," + mj + "," + mk + ")"
                                                    + " TO " + ci.getNombre() + "(" + mi + "," + mj + "," + mk + ")");
                                        }
                                    }
                                }
                            }
                        } else {
                            for (mi = 1; mi <= a; mi++) {
                                for (mj = 1; mj <= b; mj++) {
                                    w.println("        WHEN \"" + ci.getNombre() + "-" + mi + "-" + mj
                                            + "\"");
                                    if (!ci.isNumero()) {
                                        w.println("            MOVE VALOR-INI TO J" + ci.getNombre() + "("
                                                + mi + "," + mj + ")");
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi + "," + mj + ")"
                                                + " TO " + ci.getNombre() + "(" + mi + "," + mj + ")");
                                    } else {
                                        w.println("            MOVE VALOR-INI TO ENTEXTO");
                                        w.println("            PERFORM PASA-A-NUMERO");
                                        w.println("            MOVE ENVALOR TO ENPIC");
                                        w.println("            MOVE ENPIC TO J" + ci.getNombre() + "(" + mi + "," + mj
                                                + ")");
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi + "," + mj + ")"
                                                + " TO " + ci.getNombre() + "(" + mi + "," + mj + ")");
                                    }
                                }
                            }
                        }
                    } else {
                        if (c > 1) {
                            for (mi = 1; mi <= a; mi++) {
                                for (mj = 1; mj <= c; mj++) {
                                    w.println("        WHEN \"" + ci.getNombre() + "-" + mi + "-" + mj
                                            + "\"");
                                    if (!ci.isNumero()) {
                                        w.println("            MOVE VALOR-INI TO J" + ci.getNombre() + "("
                                                + mi + "," + mj + ")");
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi + "," + mj + ")"
                                                + " TO " + ci.getNombre() + "(" + mi + "," + mj + ")");
                                    } else {
                                        w.println("            MOVE VALOR-INI TO ENTEXTO");
                                        w.println("            PERFORM PASA-A-NUMERO");
                                        w.println("            MOVE ENVALOR TO ENPIC");
                                        w.println("            MOVE ENPIC TO J" + ci.getNombre() + "(" + mi + "," + mj
                                                + ")");
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi + "," + mj + ")"
                                                + " TO " + ci.getNombre() + "(" + mi + "," + mj + ")");
                                    }
                                }
                            }
                        } else {
                            for (mi = 1; mi <= a; mi++) {
                                w.println("        WHEN \"" + ci.getNombre() + "-" + mi
                                        + "\"");
                                if (!ci.isNumero()) {
                                    w.println("            MOVE VALOR-INI TO J" + ci.getNombre() + "("
                                            + mi + ")");
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")"
                                            + " TO " + ci.getNombre() + "(" + mi + ")");
                                } else {
                                    w.println("            MOVE VALOR-INI TO ENTEXTO");
                                    w.println("            PERFORM PASA-A-NUMERO");
                                    w.println("            MOVE ENVALOR TO ENPIC");
                                    w.println("            MOVE ENPIC TO J" + ci.getNombre() + "("
                                            + mi + ")");
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")"
                                            + " TO " + ci.getNombre() + "(" + mi + ")");
                                }
                            }
                        }
                    }
                } else {
                    if (b > 1) {
                        if (c > 1) {
                            for (mi = 1; mi <= b; mi++) {
                                for (mj = 1; mj <= c; mj++) {
                                    w.println("        WHEN \"" + ci.getNombre() + "-" + mi + "-" + mj
                                            + "\"");
                                    if (!ci.isNumero()) {
                                        w.println("            MOVE VALOR-INI TO J" + ci.getNombre() + "("
                                                + mi + "," + mj + ")");
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi + "," + mj + ")"
                                                + " TO " + ci.getNombre() + "(" + mi + "," + mj + ")");
                                    } else {
                                        w.println("            MOVE VALOR-INI TO ENTEXTO");
                                        w.println("            PERFORM PASA-A-NUMERO");
                                        w.println("            MOVE ENVALOR TO ENPIC");
                                        w.println("            MOVE ENPIC TO J" + ci.getNombre() + "(" + mi + "," + mj
                                                + ")");
                                        w.println("            MOVE J" + ci.getNombre() + "(" + mi + "," + mj + ")"
                                                + " TO " + ci.getNombre() + "(" + mi + "," + mj + ")");
                                    }
                                }
                            }
                        } else {
                            for (mi = 1; mi <= b; mi++) {
                                w.println("        WHEN \"" + ci.getNombre() + "-" + mi
                                        + "\"");
                                if (!ci.isNumero()) {
                                    w.println("            MOVE VALOR-INI TO J" + ci.getNombre() + "(" + mi + ")");
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")" + " TO " + ci.getNombre() + "(" + mi + ")");
                                } else {
                                    w.println("            MOVE VALOR-INI TO ENTEXTO");
                                    w.println("            PERFORM PASA-A-NUMERO");
                                    w.println("            MOVE ENVALOR TO ENPIC");
                                    w.println("            MOVE ENPIC TO J" + ci.getNombre() + "(" + mi + ")");
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")" + " TO " + ci.getNombre() + "(" + mi + ")");
                                }
                            }
                        }
                    } else {
                        if (c > 1) {
                            for (mi = 1; mi <= c; mi++) {
                                w.println("        WHEN \"" + ci.getNombre() + "-" + mi
                                        + "\"");
                                if (!ci.isNumero()) {
                                    w.println("            MOVE VALOR-INI TO J" + ci.getNombre() + "(" + mi + ")");
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")" + " TO " + ci.getNombre() + "(" + mi + ")");
                                } else {
                                    w.println("            MOVE VALOR-INI TO ENTEXTO");
                                    w.println("            PERFORM PASA-A-NUMERO");
                                    w.println("            MOVE ENVALOR TO ENPIC");
                                    w.println("            MOVE ENPIC TO J" + ci.getNombre() + "(" + mi + ")");
                                    w.println("            MOVE J" + ci.getNombre() + "(" + mi + ")" + " TO " + ci.getNombre() + "(" + mi + ")");
                                }
                            }
                        } else {
                            w.println("        WHEN \"" + ci.getNombre()
                                    + "\"");
                            if (!ci.isNumero()) {
                                w.println("            MOVE VALOR-INI TO J" + ci.getNombre());
                                w.println("            MOVE J" + ci.getNombre() + " TO " + ci.getNombre());
                            } else {
                                w.println("            MOVE VALOR-INI TO ENTEXTO");
                                w.println("            PERFORM PASA-A-NUMERO");
                                w.println("            MOVE ENVALOR TO ENPIC");
                                w.println("            MOVE ENPIC TO J" + ci.getNombre());
                                w.println("            MOVE J" + ci.getNombre() + " TO " + ci.getNombre());
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < ci.getNhijos(); i++) {
                sqlando(w, ci.getHijos()[i], tipo);
            }
        }
    }

    public void setPROYECTOAPI(String PROYECTOAPI) {
        this.PROYECTOAPI = PROYECTOAPI;
    }

    public void setPROYECTO(String PROYECTO) {
        this.PROYECTO = PROYECTO;
    }

    public void setSERVIDOR(String SERVIDOR) {
        this.SERVIDOR = SERVIDOR;
    }

    public String getPROYECTOAPI() {
        return PROYECTOAPI;
    }

    public String getPROYECTO() {
        return PROYECTO;
    }

    public String getSERVIDOR() {
        return SERVIDOR;
    }

    public String getPACKAGE() {
        return PACKAGE;
    }

    public void setPACKAGE(String PACKAGE) {
        this.PACKAGE = PACKAGE;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivoFD() {
        return NombreArchivoFD;
    }

    public void setNombreArchivoFD(String NombreArchivoFD) {
        this.NombreArchivoFD = NombreArchivoFD;
    }

    public String getNombreArchivoSEL() {
        return NombreArchivoSEL;
    }

    public void setNombreArchivoSEL(String NombreArchivoSEL) {
        this.NombreArchivoSEL = NombreArchivoSEL;
    }

    public UnaFD getLaFD() {
        return laFD;
    }

    public void setLaFD(UnaFD laFD) {
        this.laFD = laFD;
    }

    public UnaSEL getLaSEL() {
        return laSEL;
    }

    public void setLaSEL(UnaSEL laSEL) {
        this.laSEL = laSEL;
    }

    public File getDeUsar() {
        return deUsar;
    }

    public void setDeUsar(File deUsar) {
        this.deUsar = deUsar;
    }

    public File getDeInterfaceBean() {
        return deInterfaceBean;
    }

    public void setDeInterfaceBean(File deInterfaceBean) {
        this.deInterfaceBean = deInterfaceBean;
    }

    public File getDeRegistro() {
        return deRegistro;
    }

    public void setDeRegistro(File deRegistro) {
        this.deRegistro = deRegistro;
    }

    public File getDeBean() {
        return deBean;
    }

    public void setDeBean(File deBean) {
        this.deBean = deBean;
    }

    public File getDeInterfaceFichero() {
        return deInterfaceFichero;
    }

    public void setDeInterfaceFichero(File deInterfaceFichero) {
        this.deInterfaceFichero = deInterfaceFichero;
    }

    public File getDeFichero() {
        return deFichero;
    }

    public void setDeFichero(File deFichero) {
        this.deFichero = deFichero;
    }

    public File getDeFICHEROX() {
        return deFICHEROX;
    }

    public void setDeFICHEROX(File deFICHEROX) {
        this.deFICHEROX = deFICHEROX;
    }

    public File getDeCOBOL() {
        return deCOBOL;
    }

    public void setDeCOBOL(File deCOBOL) {
        this.deCOBOL = deCOBOL;
    }

    public File getDeWO() {
        return deWO;
    }

    public void setDeWO(File deWO) {
        this.deWO = deWO;
    }

    public File getDeDEC() {
        return deDEC;
    }

    public void setDeDEC(File deDEC) {
        this.deDEC = deDEC;
    }

    public File getDeA() {
        return deA;
    }

    public void setDeA(File deA) {
        this.deA = deA;
    }

    public File getDeT() {
        return deT;
    }

    public void setDeT(File deT) {
        this.deT = deT;
    }

    public String getNombreusar() {
        return nombreusar;
    }

    public void setNombreusar(String nombreusar) {
        this.nombreusar = nombreusar;
    }

    public String getNombreinterfacebean() {
        return nombreinterfacebean;
    }

    public void setNombreinterfacebean(String nombreinterfacebean) {
        this.nombreinterfacebean = nombreinterfacebean;
    }

    public String getNombreclaseregistro() {
        return nombreclaseregistro;
    }

    public void setNombreclaseregistro(String nombreclaseregistro) {
        this.nombreclaseregistro = nombreclaseregistro;
    }

    public String getNombreclasebean() {
        return nombreclasebean;
    }

    public void setNombreclasebean(String nombreclasebean) {
        this.nombreclasebean = nombreclasebean;
    }

    public String getNombreinterfacefichero() {
        return nombreinterfacefichero;
    }

    public void setNombreinterfacefichero(String nombreinterfacefichero) {
        this.nombreinterfacefichero = nombreinterfacefichero;
    }

    public String getNombreclasefichero() {
        return nombreclasefichero;
    }

    public void setNombreclasefichero(String nombreclasefichero) {
        this.nombreclasefichero = nombreclasefichero;
    }

    public String getNombreFICHEROX() {
        return nombreFICHEROX;
    }

    public void setNombreFICHEROX(String nombreFICHEROX) {
        this.nombreFICHEROX = nombreFICHEROX;
    }

    public String getRaiz() {
        return raiz;
    }

    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }

    public String getSub2() {
        return sub2;
    }

    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }

    public String getSub3() {
        return sub3;
    }

    public void setSub3(String sub3) {
        this.sub3 = sub3;
    }

    public String getSub4() {
        return sub4;
    }

    public void setSub4(String sub4) {
        this.sub4 = sub4;
    }

    public String getSub5() {
        return sub5;
    }

    public void setSub5(String sub5) {
        this.sub5 = sub5;
    }

    public String getSub6() {
        return sub6;
    }

    public void setSub6(String sub6) {
        this.sub6 = sub6;
    }

    public String getSUB() {
        return SUB;
    }

    public void setSUB(String SUB) {
        this.SUB = SUB;
    }

    public int getTotalchars() {
        return totalchars;
    }

    public void setTotalchars(int totalchars) {
        this.totalchars = totalchars;
    }

    public String getDd_dir() {
        if (getSUB().equals("MU") || getSUB().equals("SG")) {
            return "mnu";
        } else {
            return "dir";
        }
    }

    public String getCarpetaSrc() {
        return carpetaSrc;
    }

    public String getCarpetaLyb() {
        return carpetaLyb;
    }
}
