package View;

import javax.swing.*;
/**
 * @version Alpha
 * @author csd4622
 */
public class chooserUI {
    private int choice=1;

    /**
     * <b>constructor</b> constructs a chooserUI
     * @param t if it is false is for months if not is for lottery
     * @param b  if the player with blue pawn chooses or not
     */
    public chooserUI(boolean t, boolean b){
        if(!t) {
            Object[] options = {1, 2, 3};
            Integer m = (Integer) JOptionPane.showInputDialog(null, "Choose how many months you want to play",
                    "Months Playing", JOptionPane.PLAIN_MESSAGE, null, options, 1);
            if (m != null) {
                choice = m;
            }
        }else{
            String temp="Blue player chooses";
            if(!b){
                temp="Yellow player chooses";
            }
            Object[] options = {1, 2, 3 , 4 , 5 , 6};
            Integer m = (Integer) JOptionPane.showInputDialog(null, "Choose your betting number",
                    temp, JOptionPane.PLAIN_MESSAGE, null, options, 1);
            if (m != null) {
                choice = m;
            }
        }
    }
    /**
     * <b>accessor</b>Gets the month that the user chose
     * @return month
     */
    public int getChoice(){
        return choice;
    }
}
