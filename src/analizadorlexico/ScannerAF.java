/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.util.ArrayList;

/**
 *
 * @author Netto
 */
public class ScannerAF {

    private int estado, linha, lastColuna;
    private String cadeia, saida;
    private ArrayList<Token> tokens = new ArrayList<Token>();

    ;
    //private char caracterAtual;

    //private boolean aceito;
    public ScannerAF() {

        this.estado = 0;
        cadeia = "";
        //aceito = false;
    }

    public String lerEntrada(String entrada) {

        saida = new String();
        entrada = entrada + "¬";
        System.out.println(entrada);
        int tamanhoEntrada = entrada.length();
        linha = 1;
        lastColuna = 0;
        //int coluna = 0; coluna = i - lastColuna;

        for (int i = 0; i < tamanhoEntrada; i++) {
            char caracterAtual = entrada.charAt(i);

            if (caracterAtual == '\n') {
                linha++;
                lastColuna = i;
            }

            /*if (!verificaSimbolo(caracterAtual)) {
             System.out.println("ERRO Símbolo não identificado - " + caracterAtual + " Não pertence ao alfabeto");
             cadeia = "";
             }*/
            //Estado Inicial
            if (estado == 0) {
                if (caracterAtual == '\'') {
                    estado = 27;
                    i = verificaChar(entrada, i, cadeia);

                } else if (caracterAtual == '"') {
                    estado = 24;
                    i = verificaString(entrada, i, cadeia);

                } else if (caracterAtual == '-') {
                    i = barriuMenos(entrada, i, cadeia);

                } else if (verificaDigito(caracterAtual)) {
                    estado = 3;
                    i = verificaNumero(entrada, i, cadeia);
                } else if (verificaLetra(caracterAtual)) {
                    estado = 1;
                    i = verificaIdentificador(entrada, i, cadeia);
                } else if (verificaInicioDeOperador(caracterAtual)) {
                    estado = 6;
                    i = verificaOperador(entrada, i, cadeia);
                } else if (caracterAtual == '/') {
                    estado = 18;
                    i = verificaBarra(entrada, i, cadeia);
                } else if (verificaDelimitador(caracterAtual)) {
                    //System.out.println(caracterAtual + " é Delimitador: Linha " + linha + " - Coluna " + (i - lastColuna));
                    tokens.add(new Token(cadeia, (i - lastColuna), linha, "Delimitador"));
                    saida = saida + "\n" + caracterAtual + " é Delimitador: Linha " + linha + " - Coluna " + (i - lastColuna);
                }
            } else {
                //goToEstado();
            }

            if ((i + 1) == tamanhoEntrada && caracterAtual == '¬') {
                //System.out.println("EOF");
                saida = saida + "\n" + "EOF";

                continue;
            }
        }
        //System.out.println(saida);
        return saida;
    }

    private int barriuMenos(String entrada, int i, String cadeia) {

        if (verificaDigito(entrada.charAt(i + 1))) {
            if ((verificaDigito(entrada.charAt(i - 1)) || verificaLetra(entrada.charAt(i - 1)) || entrada.charAt(i - 1) == ')')) {
                //É um Operador
                estado = 6;
                i = verificaOperador(entrada, i, cadeia);
            } else {
                //é um Número
                this.cadeia = "-";
            }
        } else {
            //Operador
            estado = 6;
            i = verificaOperador(entrada, i, cadeia);
        }

        return i;
    }

    private int verificaBarra(String entrada, int i, String cadeia) {

        int tam = entrada.length();
        char caracterAtual;
        int j;
        for (j = i; j < tam; j++) {
            caracterAtual = entrada.charAt(j);

            switch (estado) {

                case 18:
                    if (caracterAtual == '/') {

                        estado = 19;
                        cadeia = cadeia.concat("" + caracterAtual);
                    }
                    break;
                case 19:
                    if (caracterAtual == '/') {
                        estado = 20;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '*') {
                        estado = 21;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {//Operador
                        estado = 6;
                        cadeia = "";
                        i = verificaOperador(entrada, i, cadeia);
                        j--;
                    }
                    break;

                case 20:
                    if (caracterAtual == '\n') {
                        estado = 23;
                    } else {
                        cadeia = cadeia.concat("" + caracterAtual);
                    }
                    break;

                case 21:
                    if (caracterAtual == '*') {
                        estado = 22;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        cadeia = cadeia.concat("" + caracterAtual);
                    }

                    break;

                case 22:
                    if (caracterAtual == '*') {
                        estado = 22;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '/') {
                        estado = 23;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 21;
                        cadeia = cadeia.concat("" + caracterAtual);
                    }
                    break;

                case 23:
                    tokens.add(new Token(cadeia, (i - lastColuna), linha, "Comentario"));
                    saida = saida + "\n" + cadeia + " é Comentário: Linha " + linha + " - Coluna " + (i - lastColuna);
                    cadeia = "";
                    estado = 0;
                    j--;
                    break;

                default:
                    return i + (j - i) - 1;
            }
        }

        return i + (j - i) - 1;
    }

    public boolean verificaLetra(char caracter) {
        return ("" + caracter).matches("[a-zA-Z]");
    }

    public boolean verificaDigito(char palavraEntrada) {
        return ("" + palavraEntrada).matches("[0-9]");
    }

    public boolean verificaSimbolo(char palavraEntrada) {
        int ascii = (int) palavraEntrada;
        boolean r = ascii >= 32 && ascii <= 126 && ascii != 34 && ascii != 39;

        return r;

    }

    public boolean verificaPalavraReservada(String palavraEntrada) {
        //System.out.println(palavraEntrada);
        switch (palavraEntrada.trim()) {

            case "variables":
                return true;

            case "methods":
                return true;

            case "constants":
                return true;

            case "class":
                return true;

            case "return":
                return true;

            case "empty":
                return true;

            case "main":
                return true;

            case "if":
                return true;

            case "then":
                return true;

            case "else":
                return true;

            case "while":
                return true;

            case "for":
                return true;

            case "read":
                return true;

            case "write":
                return true;

            case "integer":
                return true;

            case "float":
                return true;

            case "boolean":
                return true;

            case "string":
                return true;

            case "true":
                return true;

            case "false":
                return true;

            case "extends":
                return true;
            default:
                return false;
            // etc...
        }

    }

    private int verificaIdentificador(String entrada, int i, String cadeia) {
        int tam = entrada.length();
        char caracterAtual;
        int j;
        for (j = i; j < tam; j++) {
            caracterAtual = entrada.charAt(j);

            switch (estado) {

                case 1:
                    if (verificaLetra(caracterAtual) || verificaDigito(caracterAtual) || caracterAtual == '_') {

                        estado = 1;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 2;
                        j--;
                    }
                    break;
                case 2:
                    if (verificaPalavraReservada(cadeia)) {
                        //System.out.println(cadeia + " é palavra reservada");
                        tokens.add(new Token(cadeia, (i - lastColuna), linha, "Palavra Reservada"));
                        saida = saida + "\n" + cadeia + " é palavra reservada: Linha " + linha + " - Coluna " + (i - lastColuna);
                        cadeia = "";
                        estado = 0;
                        j--;
                    } else {
                        //System.out.println(cadeia + " é Identificador");
                        tokens.add(new Token(cadeia, (i - lastColuna), linha, "Identificador"));
                        saida = saida + "\n" + cadeia + " é Identificador: Linha " + linha + " - Coluna " + (i - lastColuna);
                        cadeia = "";
                        estado = 0;
                        j--;
                    }
                    break;

                default:
                    return i + (j - i) - 1;

            }

        }

        return i + (j - i) - 1;
    }

    public int verificaNumero(String entrada, int i, String cadeia) {

        int tam = entrada.length();
        char caracterAtual;
        int j;

        for (j = i; j < tam; j++) {

            caracterAtual = entrada.charAt(j);

            switch (estado) {

                case 3:
                    if (verificaDigito(caracterAtual)) {
                        estado = 3;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '.') {
                        estado = 4;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 5;
                        j--;
                    }

                    break;
                case 4:
                    if (verificaDigito(caracterAtual)) {
                        estado = 4;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 5;
                        j--;
                    }
                    break;
                case 5:
                    //System.out.println(cadeia + " é numero");
                    tokens.add(new Token(cadeia, (i - lastColuna), linha, "Numero"));
                    saida = saida + "\n" + cadeia + " é numero: Linha " + linha + " - Coluna " + (i - lastColuna);
                    this.cadeia = "";
                    estado = 0;
                    j--;
                    break;
                default:
                    return i + (j - i) - 1;

            }

        }

        return i + (j - i) - 1;
    }

    private int verificaOperador(String entrada, int i, String cadeia) {

        int tam = entrada.length();
        char caracterAtual;
        int j;

        for (j = i; j < tam; j++) {

            caracterAtual = entrada.charAt(j);

            switch (estado) {

                case 6:
                    if (caracterAtual == '-') {
                        estado = 7;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '+') {
                        estado = 8;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '*') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '/') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '.') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '=') {
                        estado = 9;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '!') {
                        estado = 10;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '>') {
                        estado = 11;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '<') {
                        estado = 12;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '&') {
                        estado = 13;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else if (caracterAtual == '|') {
                        estado = 14;
                        cadeia = cadeia.concat("" + caracterAtual);
                    }

                    break;

                case 7:
                    if (caracterAtual == '-') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 15;
                    }
                    break;

                case 8:
                    if (caracterAtual == '+') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 15;
                    }
                    break;

                case 9:
                    if (caracterAtual == '=') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 15;
                    }
                    break;

                case 10:
                    if (caracterAtual == '=') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 17;
                    }
                    break;

                case 11:
                    if (caracterAtual == '=') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 15;
                    }
                    break;

                case 12:
                    if (caracterAtual == '=') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 15;
                    }
                    break;

                case 13:
                    if (caracterAtual == '&') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 17;
                    }

                    break;

                case 14:
                    if (caracterAtual == '|') {
                        estado = 16;
                        cadeia = cadeia.concat("" + caracterAtual);
                    } else {
                        estado = 17;
                    }

                    break;

                case 15:    //Aceitação por "Outro"
                    //System.out.println(cadeia + " é Operador: Linha " + linha + " - Coluna " + (i - lastColuna));
                    tokens.add(new Token(cadeia, (i - lastColuna), linha, "Operador"));
                    saida = saida + "\n" + cadeia + " é Operador: Linha " + linha + " - Coluna " + (i - lastColuna);
                    this.cadeia = "";
                    estado = 0;
                    j = j - 2;
                    break;

                case 16:    //Aceitação
                    //System.out.println(cadeia + " é Operador: Linha " + linha + " - Coluna " + (i - lastColuna));
                    tokens.add(new Token(cadeia, (i - lastColuna), linha, "Operador"));
                    saida = saida + "\n" + cadeia + " é Operador: Linha " + linha + " - Coluna " + (i - lastColuna);
                    this.cadeia = "";
                    estado = 0;
                    j--;
                    break;

                case 17:    //Erro identificador mal formado
                    //System.out.println("Erro identificador mal formado: Linha " + linha + " - Coluna " + (i - lastColuna));
                    tokens.add(new Token(cadeia, (i - lastColuna), linha, "Erro identificador mal formado"));
                    saida = saida + "\n" + "Erro identificador mal formado: Linha " + linha + " - Coluna " + (i - lastColuna);
                    this.cadeia = "";
                    estado = 0;
                    j = j - 2;
                    break;

                default:
                    return i + (j - i) - 1;
            }

        }

        return i + (j - i) - 1;
    }

    public boolean verificaInicioDeOperador(char caractere) {
        switch (caractere) {

            case '+':
                return true;

            case '*':
                return true;

            case '=':
                return true;

            case '!':
                return true;

            case '>':
                return true;

            case '<':
                return true;

            case '&':
                return true;

            case '|':
                return true;

            case '.':
                return true;

            default:
                return false;
            // etc...
        }

    }

    public boolean verificaDelimitador(char caractere) {
        switch (caractere) {

            case ';':
                return true;

            case ',':
                return true;

            case '(':
                return true;

            case ')':
                return true;

            case '{':
                return true;

            case '}':
                return true;

            case '[':
                return true;

            case ']':
                return true;

            default:
                return false;
            // etc...
        }
    }

     // Char está ok, salvando erros e tokens validos
    private int verificaString(String entrada, int i, String cadeia) {

        int tam = entrada.length();
        char caracterAtual;
        int j;

        for (j = i; j < tam; j++) {

            caracterAtual = entrada.charAt(j);

            switch (estado) {

                case 24:
                    if (caracterAtual == '"') {
                        estado = 25;
                        cadeia = cadeia.concat("" + caracterAtual);
                    }
                    break;
                case 25:

                    if (verificaSimbolo(caracterAtual)) {
                        cadeia = cadeia.concat("" + caracterAtual);
                        estado = 25;
                    } else if (caracterAtual == '"') {
                        cadeia = cadeia.concat("" + caracterAtual);
                        estado = 26;
                        j--;
                    } else {
                        j--;
                        estado = 99;
                    }
                    break;
                case 26:
                    if (caracterAtual == '"') {
                        tokens.add(new Token(cadeia, (i - lastColuna), linha, "String"));
                        saida = saida + "\n" + cadeia + " é string: Linha " + linha + " - Coluna " + (i - lastColuna);
                        this.cadeia = "";
                        estado = 0;
                    }
                    break;

                case 99:

                    if (caracterAtual == '"') {
                        cadeia = cadeia.concat("" + caracterAtual);
                        tokens.add(new Token(cadeia, (i - lastColuna), linha, "String mal formada"));
                        this.cadeia = "";
                        estado = 0;
                    } else {
                        if ((j + 1) == tam && caracterAtual == '¬') {
                            tokens.add(new Token(cadeia, (i - lastColuna), linha, "String mal formada EOF"));
                            this.cadeia = "";
                            estado = 0;
                        } else {
                            cadeia = cadeia.concat("" + caracterAtual);
                            estado = 99;
                        }
                    }
                    break;

                default:
                    return i + (j - i) - 1;

            }

        }
        return i + (j - i) - 1;
    }

    // Char está ok, salvando erros e tokens validos
    private int verificaChar(String entrada, int i, String cadeia) {

        int tam = entrada.length();
        char caracterAtual;
        int j;

        for (j = i; j < tam; j++) {

            caracterAtual = entrada.charAt(j);

            switch (estado) {

                case 27:
                    if (caracterAtual == '\'') {
                        estado = 28;
                        cadeia = cadeia.concat("" + caracterAtual);
                    }
                    break;
                case 28:
                    if (verificaSimbolo(caracterAtual) && caracterAtual != ' ') {

                        cadeia = cadeia.concat("" + caracterAtual);
                        estado = 29;
                    } else {
                        j--;
                        estado = 30;
                    }
                    break;
                case 29:
                    if (caracterAtual == '\'') {
                        cadeia = cadeia.concat("" + caracterAtual);
                        tokens.add(new Token(cadeia, (i - lastColuna), linha, "Char"));
                        saida = saida + "\n" + cadeia + " é char: Linha " + linha + " - Coluna " + (i - lastColuna);
                        this.cadeia = "";
                        estado = 0;

                    } else {
                        cadeia = cadeia.concat("" + caracterAtual);
                        estado = 30;
                    }
                    break;

                case 30:
                    if (caracterAtual == '\'') {
                        cadeia = cadeia.concat("" + caracterAtual);
                        tokens.add(new Token(cadeia, (i - lastColuna), linha, "Char mal formado"));
                        this.cadeia = "";
                        estado = 0;
                    } else {
                        if ((j + 1) == tam && caracterAtual == '¬') {
                            tokens.add(new Token(cadeia, (i - lastColuna), linha, "Char mal formado EOF"));
                            this.cadeia = "";
                            estado = 0;
                        } else {
                            cadeia = cadeia.concat("" + caracterAtual);
                            estado = 30;
                        }
                    }
                    break;
                default:
                    return i + (j - i) - 1;

            }

        }
        return i + (j - i) - 1;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

}
