package View;


import javax.swing.*;
import java.awt.*;

public class winUI {
    /**
     * <b>constructor</b> constructs a nameUI
     * <b>postcondition</b>must update again variable
     * @param name the winner
     * @param draw if no one won
     */
    public winUI(String name,boolean draw){
        JOptionPane q = new JOptionPane();
        ImageIcon image=new ImageIcon("src\\resources\\images\\winner.jpg");
        Image image2 = image.getImage();
        image2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        Object[] options1 = {"OK"};
        q.setLocation(50, 50);
        String msg;
        msg="Ο "+name+" κέρδισε!";
        if(draw) msg="Ισοπαλία!Rematch?";
        int n= q.showOptionDialog(null,
                msg,
                "ΤΕΛΟΣ", JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image2),
                options1,
                options1[0]);
    }


}
