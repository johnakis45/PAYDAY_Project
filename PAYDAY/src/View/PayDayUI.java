package View;

import javax.swing.*;

/**
 *
 * @author csd3142
 */
public class PayDayUI {
    private final ImageIcon image=new ImageIcon("src/resources/images/pay.jpg");
    int choice;

    /**
     * <b>constructor</b>: Creates a new PayDay Window  <br />
     * <b>postconditions</b>: Creates a new PayDay Window
     */
    public PayDayUI() {
        Object options[] = {"Ναι", "Πληρωμή μέρους", "Όχι"};
        choice = JOptionPane.showOptionDialog(null, "Θα πληρώσεις τα δάνεια σου?", "Bank", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
    }

    /**
     * <b>Accessor<b> Returns the choice of the player
     *
     * @return the choice of the player
     */
    public int getChoice() {
        return choice;
    }
}
