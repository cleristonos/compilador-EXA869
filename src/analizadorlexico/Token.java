package analizadorlexico;

/**
 *
 * @author cleriston.os
 */
public class Token {

    private String lexema, tipo;
    private int coluna, linha;

    public Token(String lexema, int coluna, int linha, String tipo) {
        this.lexema = lexema;
        this.coluna = coluna;
        this.linha = linha;
        this.tipo = tipo;
    }
    
    

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
