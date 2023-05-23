package View;

import javax.swing.*;
/**
 * @version Alpha
 * @author csd4622
 */
public class loanUI {
    private final ImageIcon  image=new ImageIcon("src/resources/images/daneio.jpg");
    private Integer loanmoney;

    /**
     * <b>constructor</b> constructs a loanUI
     * <b>postcondition</b>must update loanmoney
     */
    public loanUI(){
        Integer[] options = {0, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        loanmoney = (Integer) JOptionPane.showInputDialog(null,"Choose a loan",
                "Loan Window", JOptionPane.PLAIN_MESSAGE, image, options, options[5]);
    }


    /**
     * <b>accessor</b>Gets the money the player chose
     * @return the loanmoney
     */
    public Integer getLoanmoney(){
        return this.loanmoney;
    }
}
