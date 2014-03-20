package analizadorlexico;

//import java.util.logging.Level;
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
            // TODO code application logic here

            //Scanner scanner = new Scanner();
            //scanner.leitorEntrada(" if main class as1a1s a@sa3s_a  f_dt123r_f1d _abc -1 2555.355a55 +12.12323123 1 23 ¬ £casa £teste £ 'asda'");
            ScannerAF scanner = new ScannerAF();
            scanner.leitorEntrada("Count max_1 p345 _pic 55_operacao DoÃ var_char©do_it");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }
}
