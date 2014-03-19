package analizadorlexico;

import java.util.ArrayList;

/**
 *
 * @author cleriston.os
 */
public class Scanner {

    public void leitorEntrada(String entrada) {

        int tamanhoEntrada = entrada.length();
        int linha = 1;
        int coluna = 1;
        String cadeia = "";

        for (int i = 0; i < tamanhoEntrada; i++) {
            String caracterAtual = entrada.substring(i, i + 1);

            if (caracterAtual.equals("\n")) {
                linha++;
                coluna = 0;
            } else {
                cadeia = cadeia.concat(caracterAtual);
//                if (caracterAtual.matches("[a-zA-Z]")) {
//                   
//                }
                if (caracterAtual.equals(" ") || i + 1 == tamanhoEntrada) {

                    //remove espaço em branco
                    cadeia = cadeia.trim();
                    // se cadeia for vazia não faz verificações
                    if (cadeia.length() > 0) {

                        if (this.verificaPalavraReservada(cadeia)) {
                            System.out.println(cadeia + " é palavra reservada");
                        } else {
                            if (this.verificaIdentificador(cadeia)) {
                                System.out.println(cadeia + " é identificador");
                            } else {
                                if (this.verificaDigito(cadeia)) {
                                    System.out.println(cadeia + " é digito");
                                } else {

                                    if (this.verificaNumero(cadeia)) {
                                        System.out.println(cadeia + " é numero");
                                    } else {
                                        System.out.println(cadeia + " indentificador mal formado linha" + linha + " coluna:" + coluna);
                                    }
                                }
                            }

                        }

                    }
                    cadeia = "";
                }

                //System.out.println("c:" + coluna + "  l:" + linha + " caractere:" + caracterAtual);
            }

            coluna++;
        }

    }

    public boolean verificaLetra(String caracter) throws Exception {
        if (caracter.length() == 1) {
            if (caracter.matches("[a-zA-Z]")) {
                return true;
            } else {

                throw new Exception(caracter + " não é uma letra");
            }

        } else {
            throw new Exception();
        }

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

    public boolean verificaDigito(String palavraEntrada) {
        return palavraEntrada.trim().matches("[0-9]");
    }

    public boolean verificaNumero(String palavraEntrada) {
        return palavraEntrada.trim().matches("[-]?[0-9]*\\.?[0-9]*");
    }

}
