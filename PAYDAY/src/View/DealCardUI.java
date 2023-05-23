package View;
import javax.swing.*;
import java.awt.*;
/**
 * @version Alpha
 * @author csd4622
 */
public class DealCardUI{
    private boolean choice;

    /**
     * <b>constructor</b> constructs a UI for the Deal Card
     * <b>postcondition</b>update boolean choice
     * @param img the image url
     * @param msg the message
     * @param cost the cost of the card
     * @param sell the selling price of the card
     */
    public DealCardUI(String img,String msg,int cost,int sell){
        JOptionPane q = new JOptionPane();
        ImageIcon image=new ImageIcon(img);
        Image image2 = image.getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        Object[] options1 = {"Αγόρασε","Αγνόησε"};
        q.setLocation(50, 50);
        int n = q.showOptionDialog(null,
                msg +"\nΤιμή αγοράς: " + cost
                        + "\nΤιμή πώλησης :"+ sell ,
                "Deal", JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options1,
                options1[0]);
        if(n==0){
            choice=true;
        }else{
            choice=false;
        }
    }

    /**
     * <b>accessor</b>Gets the choice (if it is true player got the card)
     * @return choice of the player
     */
    public boolean getchoice(){
        return choice;
    }

}
