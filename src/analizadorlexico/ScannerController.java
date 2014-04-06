/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

/**
 *
 * @author Netto
 */
public class ScannerController {

    private ScannerAF scanner;
    private Terminal terminal;

    public void inicializa() {
        scanner = new ScannerAF();
        terminal = new Terminal(this);
    }

    public ScannerAF getScanner() {
        return scanner;
    }

    public void setScanner(ScannerAF scanner) {
        this.scanner = scanner;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
    
    
    
    
}
