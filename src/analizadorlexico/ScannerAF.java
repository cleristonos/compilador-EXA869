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
    //private boolean aceito;

    public ScannerAF() {
        this.estado = 0;
        cadeia = "";
        //aceito = false;
    }

    public void leitorEntrada(String entrada) {

        int tamanhoEntrada = entrada.length();
        int linha = 1;
        int coluna = 1;

        for (int i = 0; i < tamanhoEntrada; i++) {

            char caracterAtual = entrada.charAt(i);
            //System.out.println("Estado: " + estado + " - Debug: " + cadeia + " Consumindo:" + caracterAtual);

            if (caracterAtual == '\n') {
                linha++;
                coluna = 1;
            } else {
                //cadeia = cadeia.concat("" + caracterAtual);

                switch (estado) {
                    case 0:
                        if (!verificaSimbolo(caracterAtual)) {
                            System.out.println("ERRO Símbolo não identificado - " + caracterAtual + " Não pertence ao alfabeto");
                            cadeia = "";
                        } else if (verificaDigito(caracterAtual)) {
                            //Faz nada...
                        } else if (caracterAtual == '_') {
                            //faz nada...
                        } else if (verificaLetra(caracterAtual)) {
                            estado = 1;
                            cadeia = cadeia.concat("" + caracterAtual);
                        }
                        break;

                    case 1:
                        if (!verificaSimbolo(caracterAtual)) {
                            System.out.println(cadeia + " é Identificador");
                            //System.out.println("ERRO Símbolo não identificado - " + caracterAtual + " Não pertence ao alfabeto");
                            cadeia = "";
                            estado = 0;
                            i--;

                        } else if (verificaLetra(caracterAtual) || verificaDigito(caracterAtual) || caracterAtual == '_') {
                            estado = 1;
                            cadeia = cadeia.concat("" + caracterAtual);
                        } else {
                            System.out.println(cadeia + " é Identificador");
                            cadeia = "";
                            estado = 0;
                            i--;
                        }
                        break;
                }
                coluna++;
            }

        }
        if (cadeia.length() > 0 && estado == 1) {
            System.out.println(cadeia + " é Identificador");
            cadeia = "";
            estado = 0;
        }

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

    public boolean verificaIdentificador(String palavraEntrada) {
        return palavraEntrada.trim().matches("[a-zA-Z]([a-zA-Z]|[0-9]|_)*");
    }

    public boolean verificaNumero(String palavraEntrada) {
        return palavraEntrada.trim().matches("[-]?[0-9]*\\.?[0-9]*");
    }
}
