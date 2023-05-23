package View;
import javax.swing.*;
import java.awt.*;

/**
 * @version Alpha
 * @author csd4622
 */
public class MailCardUI {

    /**
     * <b>constructor</b> constructs a UI for the Mail Card
     * @param msg the message of the card'
     * @param title the title of the card
     * @param bt the button's name
     * @param img the image url of the card
     */
    public MailCardUI(String msg,String title,String bt,String img){
        Object[] options ={bt};
        ImageIcon image=new ImageIcon(img);
        Image image2 = image.getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane p = new JOptionPane();
        p.setLocation(50, 50);
        int n = p.showOptionDialog(null,
                msg,
                title,
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options,
                options[0]);
    }

}
