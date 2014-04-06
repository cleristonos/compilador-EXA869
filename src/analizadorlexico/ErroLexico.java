package analizadorlexico;

/**
 *
 * @author cleriston.os
 */
public class ErroLexico {

    private String lexema;
    private int linha, coluna;

    public ErroLexico(String lexema, int linha, int coluna) {
        this.lexema = lexema;
        this.linha = linha;
        this.coluna = coluna;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

}
