package View;

import Controller.controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @version Alpha
 * @author csd4622
 */
public class InfoBoxUI extends JDesktopPane{
    JLabel info=new JLabel("Info Box");
    JTextField  months,turn,message;
    controller g;

    /**
     * <b>constructor</b> constructs the panel of the InfoBox
     * <b>postcondition</b>creates a panel
     * <p>
     * initializes the textfields and adds them to the panel
     */
    public InfoBoxUI(controller t){
        this.g=t;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) Math.round(screenSize.getWidth()) - 220;
        int height = (int) Math.round(screenSize.getHeight()) - 220;
        months =new JTextField() ;
        turn =new JTextField() ;
        months.setText(g.getMonths()+" Month Left");
        if(g.GetA().getTurn()) {
            turn.setText("Turn : " + g.GetA().getName());
        }else{
            turn.setText("Turn : " + g.GetB().getName());
        }
        message =new JTextField() ;
        message.setText("---");
        setBounds(750, 300, width*50 , height * 13/86);
        setSize(320, 130);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        Border border = new LineBorder(Color.black, 3, false);
        setBorder(border);
        turn.setEditable(false);
        turn.setOpaque(false);
        turn.setBorder(null);
        message.setEditable(false);
        message.setOpaque(false);
        message.setBorder(null);
        months.setEditable(false);
        months.setOpaque(false);
        months.setBorder(null);


        add(info);
        add(months);
        add(turn);
        add(message);

    }

    /**
     * <b>mutator</b>:takes a String array and from it ,it updates the status of the infobox
     * @param status the informaton that the infobox should display
     */
    public void refresh(String status){

        months.setText(g.getMonths()+" Month Left");
        if(g.GetA().getTurn()) {
            turn.setText("Turn : " + g.GetA().getName());
        }else{
            turn.setText("Turn : " + g.GetB().getName());
            }
        message.setText(status);
    }
}
