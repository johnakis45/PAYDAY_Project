package View;

import javax.swing.*;

/**
 * @version Alpha
 * @author csd4622
 */
public class nameUI {
    private String name;

    /**
     * <b>constructor</b> constructs a nameUI
     * <b>postcondition</b>must update name
     */
    public nameUI(boolean first){
        String userenters= JOptionPane.showInputDialog ("What is your name?");
        if(userenters!=null){
            name=userenters;
        }else{
            if(first){
                name="Player 1";
            }else{
                name="Player 2";
            }
        }
    }
    /**
     * <b>accessor</b>Gets the name the player typed
     * @return the name
     */
    public String getname(){
        return this.name;
    }

}
