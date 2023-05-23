package View;

import javax.swing.*;
/**
 * @version Alpha
 * @author csd4622
 */
public class lotteryUI {
    private Integer choice=6;

    /**
     * <b>Constructor</b> :constructs a lotteryUI
     * Creates a JOptionPane so the player can choose the number that wants to bet
     */
    public lotteryUI(String name){
        Integer[] options = {1, 2, 3, 4, 5, 6};
        Integer c = (Integer) JOptionPane.showInputDialog(null,name+ " Choose a number",
                "Add a new course", JOptionPane.PLAIN_MESSAGE, null, options, options[5]);
        if(c!=null){
            choice=c;
        }
    }

    /**
     * <b>accessor</b>Gets choice variable and returns it
     * @return choice
     */
    public int getChoice() {
        return choice;
    }
}
