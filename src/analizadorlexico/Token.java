package analizadorlexico;

/**
 *
 * @author cleriston.os
 */
public class Token {

    private String lexema;
    private int coluna, linha;
    private int classificacao;
    /*
     * Tokens:
     * 0-Palavra Reservada
     * 1-Id
     * 2-Número
     * 3-String
     * 4-Char
     * 5-Operador
     * 6-Delimitador
     * 7-Comentario
     * 
     * Tokens Mal formados:
     * 
     * 8-Id mal formado;
     * 9-Numero mal formado
     * 10-String mal formada
     * 11-Char mal formado
     * 12-Operador mal formado
     * 13-Comentário mal formado
     * 14-Símbolo Inválido
     * 15-Não pertence ao alfabeto
     */

    public Token(String lexema, int coluna, int linha, int classificacao) {
        this.lexema = lexema;
        this.coluna = coluna;
        this.linha = linha;
        this.classificacao = classificacao;
    }

    public String decodificaCLassificacao(int classificacao) {
        String c = new String();

        switch (classificacao) {
            case 0:
                return "Palavra reservada";

            case 1:
                return "Identificador";

            case 2:
                return "Número";

            case 3:
                return "String";

            case 4:
                return "Caractere";

            case 5:
                return "Operador";

            case 6:
                return "Delimitador";

            case 7:
                return "Comentário";

            case 8:
                return "Identificador mal formado";

            case 9:
                return "Número mal formado";

            case 10:
                return "String mal formada";

            case 11:
                return "Caractere mal formado";

            case 12:
                return "Operador mal formado";

            case 13:
                return "Comentário mal formado";

            case 14:
                return "Símbolo inválido";

            case 15:
                return "Não pertence ao alfabeto";

            default:
                return "ERRO: Cassificação não identificada!";

        }
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

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
}
