/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

/**
 *
 * @author Netto
 */
public class ScannerAF {

    private int estado;
    private String cadeia;
    //private char caracterAtual;

    //private boolean aceito;
    public ScannerAF() {

        this.estado = 0;
        cadeia = "";
        //aceito = false;
    }

    public void leitorEntrada(String entrada) {

        entrada = entrada + "¬";
        System.out.println(entrada);
        int tamanhoEntrada = entrada.length();
        int linha = 1;
        //int coluna = 0; coluna = i - lastColuna;
        int lastColuna = 0;

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
            if (estado == 0) {
                if (caracterAtual == '-') {
                    //Operador
                   i =  barriuMenos(entrada, i, cadeia);
                    //estado = 3;
                    //i = verificaNumero(entrada, i, cadeia);
                } else if (verificaDigito(caracterAtual)) {
                    estado = 3;
                    i = verificaNumero(entrada, i, cadeia);
                } else if (verificaLetra(caracterAtual)) {
                    estado = 1;
                    i = verificaIdentificador(entrada, i, cadeia);
                }
            }

            if ((i + 1) == tamanhoEntrada && caracterAtual == '¬') {
                System.out.println("EOF");
                continue;
            }
        }

    }

    private int barriuMenos(String entrada, int i, String cadeia) {

        if (verificaDigito(entrada.charAt(i + 1))) {
            if ((verificaDigito(entrada.charAt(i - 1)) || verificaLetra(entrada.charAt(i - 1)) || entrada.charAt(i - 1) == ')')) {
                //É um Operador
            } else {
                //é um Número
                this.cadeia = "-";
                 //i = verificaNumero(entrada, i+2, "-");
            }
        } else {
            //Operador
        }
        return i;
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
                        System.out.println(cadeia + " é palavra reservada");
                        cadeia = "";
                        estado = 0;
                        j--;
                    } else {
                        System.out.println(cadeia + " é Identificador");
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

    public boolean verificaIdentificadorTemp(String palavraEntrada) {
        return palavraEntrada.trim().matches(" ");
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
                    System.out.println(cadeia + " é numero");
                    this.cadeia = "";
                    estado = 0;
                    j--;
                    break;
                default:
                    return i + (j - i) - 1;

            }

        }

        return i + (j - i) - 1;

        // return palavraEntrada.trim().matches("[-]?[0-9]*\\.?[0-9]*");
    }

    public boolean verificaOperador(String palavraEntrada) {

        return false;
    }

    public boolean verificaDelimitador(String palavraEntrada) {
        switch (palavraEntrada.trim()) {

            case ";":
                return true;

            case ",":
                return true;

            case "(":
                return true;

            case ")":
                return true;

            case "{":
                return true;

            case "}":
                return true;

            case "[":
                return true;

            case "]":
                return true;

            default:
                return false;
            // etc...
        }
    }

}
