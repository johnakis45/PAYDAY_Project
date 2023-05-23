package View;

import javax.swing.*;
import java.awt.*;
/**
 * @version Alpha
 * @author csd4622
 */
public class FootballUI {
    private final ImageIcon image=new ImageIcon("src/resources/images/Barcelona_Real.jpg");
    int choice;

    /**
     * <b>constructor</b>Creates a JOptionPane and gets the choice of the user
     */
    public FootballUI(){
        JOptionPane q = new JOptionPane();
        Image image2 = image.getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        q.setLocation(50, 50);
        Object options[]={"Νίκη Μπαρτσελόνα","Ισοπαλία","Νίκη Ρεάλ","Δεν θέλω να κάνω πρόβλεψη"};
        choice=q.showOptionDialog(null,"Στοιχημάτισε 500 Ευρώ για τον αγώνα Μπαρτσελόνα-Ρεάλ",
                "Ποδοσφαιρικός Αγώνας Κυριακής", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon(image2), options, options[0]);
    }

    /**
     * <b>constructor</b>Creates a JOptionPane that displays a message that depends on the results variable
     * @param results if the player won or lost
     */
    public FootballUI(boolean results){
        JOptionPane q = new JOptionPane();
        int dummy;
        if(results){
            Object options[]={"Κέρδισες 1000 Ευρώ"};
            dummy=q.showOptionDialog(null,"Η πρόβλεψη σου ήταν σωστή",
                    "Match results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }else{
            Object[] options = {"Έχασες 500 ευρώ"};
            dummy=q.showOptionDialog(null, "Δεν πρόβλεψες σωστά",
                    "Match results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }
    }

    /**
     * <b>accessor</b>
     * @return the player's choice
     */
    public int getChoice(){return choice;};
}
