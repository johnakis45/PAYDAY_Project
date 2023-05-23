package View;


import javax.swing.*;
import java.awt.*;

public class SellCardUI {
    /**
     * <b>constructor</b> constructs a UI for the Sell Card
     * @param name to get information we need
     * @param cost the cost of the card
     */
    public SellCardUI(String name,int cost){
        Object[] options ={"OK"};
        ImageIcon image=new ImageIcon(name);
        Image image2 = image.getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane p = new JOptionPane();
        p.setLocation(50, 50);
        int n = p.showOptionDialog(null,
                "Πουλησες μια κάρτα σου για "+cost,
                "Πώληση",
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options,
                options[0]);
    }
}
