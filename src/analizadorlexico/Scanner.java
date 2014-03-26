package analizadorlexico;

import javax.print.DocFlavor;

/**
 *
 * @author cleriston.os
 */
public class Scanner {

    public Scanner() {
    }

    public void leitorEntrada(String entrada) {

        int tamanhoEntrada = entrada.length();
        int linha = 1;
        int coluna = 1;
        String lexema = "";

        for (int i = 0; i < tamanhoEntrada; i++) {

            String caracterAtual = entrada.substring(i, i + 1);

            if (caracterAtual.equals("\n")) {
                linha++;
                coluna = 1;
            } else {

                //Primeiro verifica se existe algum caractere que não pertence ao alfabeto
                if (!verificaSimbolo(caracterAtual.charAt(0))) {
                    System.out.println(caracterAtual + " simbolo mal formado - linha" + linha + " coluna:" + coluna);
                    continue;
                }
                
                lexema = lexema.concat(caracterAtual);             
                //verifica fim de um lexema
                if (caracterAtual.equals(" ") || i + 1 == tamanhoEntrada || this.verificaDelimitador(caracterAtual)|| this.verificaOperador(caracterAtual)|| this.verificaDelimitador(caracterAtual) ) {

                    //remove espaço em branco
                    lexema = lexema.trim();

                    // se lexema for vazia não faz verificações
                    if (lexema.length() > 0) {

                        if (this.verificaPalavraReservada(lexema)) {
                            System.out.println(lexema + " é palavra reservada");
                        } else {
                            if (this.verificaIdentificador(lexema)) {
                                System.out.println(lexema + " é identificador");
                            } else {
                                if (this.verificaDigito(lexema)) {
                                    System.out.println(lexema + " é digito");
                                } else {

                                    if (this.verificaNumero(lexema)) {
                                        System.out.println(lexema + " é numero");
                                    } else {
                                        System.out.println(lexema + " indentificador mal formado linha" + linha + " coluna:" + coluna);
                                    }
                                }
                            }

                        }

                    }
                    lexema = "";
                }

                //System.out.println("c:" + coluna + "  l:" + linha + " caractere:" + caracterAtual);
            }

            coluna++;
        }

    }

    public boolean verificaLetra(char caracter) {
        return ("" + caracter).matches("[a-zA-Z]");
    }

    public boolean verificaDigito(String palavraEntrada) {
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

    public boolean verificaOperador(String palavraEntrada) {
        switch (palavraEntrada.trim()) {

            case "+":
                return true;

            case "-":
                return true;

            case "*":
                return true;

            case "/":
                return true;

            case "==":
                return true;

            case "!=":
                return true;

            case ">":
                return true;

            case ">=":
                return true;

            case "<":
                return true;

            case "<=":
                return true;

            case "&&":
                return true;

            case "||":
                return true;

            case "=":
                return true;

            case "++":
                return true;

            case "--":
                return true;

           
                
                
            default:
                return false;
            // etc...
        }
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
    
    
    
    /*
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

     public boolean verificaSimbolo(String palavraEntrada) {
     int ascii = (int) palavraEntrada.charAt(0);

     return ascii > 31 & ascii < 127 & ascii != 34 & ascii != 39;

     }*/
}
