package es.cvjpy.utilidades.cobolcrear;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class MiST extends StreamTokenizer {

    private boolean elfin;
    private boolean primerodelinea;
    private boolean patras;
    private String antpal;
/// ----EMN
/// Corregir falta de campos en los ficheros tratados
    private boolean findelinea;
/// fin EMN

    public MiST(Reader r) {
        super(r);
        commentChar('*');
        ordinaryChars('0', '9');
        ordinaryChar('.');
        wordChars('0', '9');
        antpal = "";
        elfin = false;
        primerodelinea = true;
        patras = false;
    }

    @Override
    public int nextToken() throws IOException {
        int i;
        int l;
        nval = 0;
        setAntpal(sval);
        l = lineno();
        setPrimerodelinea(false);
        i = super.nextToken();
        //i=this.traga();
/// ----EMN
/// Corregir falta de campos en los ficheros tratados
///  if ( l!=lineno() ) primerodelinea=true;
        // Marco el principio de linea solo si me encuentro un final de linea
        if (isFindelinea() == true) {
            setPrimerodelinea(true);
        }
        setFindelinea(false);
/// Fin EMN

        if (i == TT_EOF) {
            setElfin(true);
        }
        if (i == TT_WORD) {
            try {
                Double mival = new Double(sval);
                nval = mival.doubleValue();
            } catch (NumberFormatException e) {
            }
        }
        if (this.ttype == '(') {
            sval = "(";
        }
        if (this.ttype == ')') {
            sval = ")";
        }
        if (this.ttype == '=') {
            sval = "=";
        }
/// ----EMN
/// Corregir falta de campos en los ficheros tratados
        // Marco el punto como final de linea
        if ((this.ttype == '.') || (this.ttype == '\r')) { ///
            setFindelinea(true);
        }
/// Fin EMN
        if (getAntpal() == null) {
            setAntpal(" ");
        }
        return i;
    }

    public String sigpal() throws IOException {
        int i;
        String r = "";

        while ((i = this.nextToken()) != TT_EOF) {
            if (i == TT_WORD) {
                r = sval;
                break;
            }
        }
        return r;
    }

    private int traga() throws IOException {
        int anti;
        int i = super.nextToken();
        if (this.ttype == '*') {
            return vaciar();
        } else {
            if (this.ttype == '.') {
                anti = i;
                i = super.nextToken();
                if (this.ttype == '*') {
                    i = super.nextToken();
                    if (this.ttype == '>') {
                        return i;
                    } else {
                        this.pushBack();
                        return vaciar();
                    }
                } else {
                    this.pushBack();
                    return anti;
                }
            }
        }
        return i;
    }

    private int vaciar() throws IOException {
        int i;
        while ((i = super.nextToken()) != TT_EOF) {
            if (i == this.TT_EOL) {
                break;
            }
        }
        return i;
    }

    /*  public String sigpalde(String s) throws IOException {
    int i;
    String r="";
    
    while( (i=this.nextToken())!=TT_EOF ){
    if( i==TT_WORD ){
    if(sval.equalsIgnoreCase(s) ){
    r=sigpal();
    break;
    }
    }
    }
    return r;
    }
     */
    public boolean isElfin() {
        return elfin;
    }

    public void setElfin(boolean elfin) {
        this.elfin = elfin;
    }

    public boolean isPrimerodelinea() {
        return primerodelinea;
    }

    public void setPrimerodelinea(boolean primerodelinea) {
        this.primerodelinea = primerodelinea;
    }

    public boolean isPatras() {
        return patras;
    }

    public void setPatras(boolean patras) {
        this.patras = patras;
    }

    public String getAntpal() {
        return antpal;
    }

    public void setAntpal(String antpal) {
        this.antpal = antpal;
    }

    public boolean isFindelinea() {
        return findelinea;
    }

    public void setFindelinea(boolean findelinea) {
        this.findelinea = findelinea;
    }
}