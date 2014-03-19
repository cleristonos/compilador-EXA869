/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizadorlexico;

/**
 *
 * @author cleriston.os
 */
public class ExececaoLexica extends Exception{
    
    private String erroUI ;

    public String getErroUI() {
        return erroUI;
    }

    public void setErroUI(String erroUI) {
        this.erroUI = erroUI;
    }
    
    
}
