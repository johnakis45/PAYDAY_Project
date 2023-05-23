package View;
import javax.swing.*;
import java.awt.*;

public class CryptoUI {
    private final ImageIcon image=new ImageIcon("src/resources/images/crypto.jpg");
    private int choice;

    /**
     * <b>constructor</b>Creates a JOptionPane and gets the choice of the user
     */
    public CryptoUI(){
        JOptionPane q = new JOptionPane();
        Image image2 = image.getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        Object[] options1 = {"Πόνταρε στο κρυπτονόμισμα","Παράβλεψε το ποντάρισμα"};
        q.setLocation(50, 50);
        choice = q.showOptionDialog(null,
                "Ποντάρισμα σε κρυπτονομίσματα.Πόνταρε 300 euro",
                "Crypto Thursday", JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options1,
                options1[0]);
    }

    /**
     * <b>constructor</b>Creates a JOptionPane that displays a message that depends on the results variable
     * @param results if the player won or lost
     */
    public CryptoUI(int results){
        JOptionPane q = new JOptionPane();
        Integer dummy;
        if(results==0){
            Object options[]={"Κέρδισες 600 Ευρώ"};
            dummy=q.showOptionDialog(null,"Το κρυπτονόμισμα εκτοξεύτηκε",
                    "Crypto results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }else if(results==1){
            Object[] options = {"Έχασες 300 ευρώ"};
            dummy=q.showOptionDialog(null, "Σημερα το κρυπτονόμισμα έπεσε",
                    "Crypto results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }else if(results==2){
            Object[] options = {"Πήρες τα λεφτά σου πίσω"};
            dummy=q.showOptionDialog(null, "Σημερα το κρυπτονόμισμα παρέμεινε σταθερό",
                    "Crypto results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }else{
            Object[] options = {"Δεν πόνταρες"};
            dummy=q.showOptionDialog(null, "Δέν κοίταξες σήμερα το κρυπτο",
                    "Crypto results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }
    }

    /**
     * <b>accessor</b>
     * @return the player's choice
     */
    public int getChoice() {
        return choice;
    }
}
