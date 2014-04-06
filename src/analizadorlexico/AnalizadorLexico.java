package analizadorlexico;

//import java.util.logging.Level;
import java.util.ArrayList;

//import java.util.logging.Logger;
/**
 *
 * @author cleriston.os
 */
public class AnalizadorLexico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            ScannerController sc = new ScannerController();
            sc.inicializa();
            //ScannerAF scanner = new ScannerAF();
           // scanner.lerEntrada(" if '¬'  '' main class-3  as1a1s)-2 a@sa3s_a f_dt123r_f1d _abc -1 2555.355a55 +12.12323123 \"string1\" '1' testek \"string22¬2222222222222222222222222222222\"   a 1 23 ¬ £casa £teste £ 'a' \"string mal formada\" 'a' ");
           //  scanner.lerEntrada("  \"\" \"teste\" \"\"  \"mal¬formada\" \"pior ' ainda\" \"piorcaso ");
            
//            scanner.lerEntrada("/* £¢¬£¢¬ */  /* teste*");
//            ArrayList<Token> tabelaTokens = scanner.getTokens();
//            
//            System.out.println("Resultado:");
//            for (Token token : tabelaTokens) {
//                System.out.println(token.getLexema()+" é: "+token.getTipo());
//            }
            //scanner.leitorEntrada("+++---!!===<><=>=/*.&&&||");
            //scanner.leitorEntrada("if(x==3) \ny=5-(-4)\ncount++");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }
}
