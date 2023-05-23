package View;

import Controller.controller;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * @version Alpha
 * @author csd4622
 */
public class PlayerUI extends JDesktopPane {
    controller g;
    JButton rolldice,dealcards,getloan,endturn;
    JLabel playername;
    JTextField moneyinfo,loaninfo,billinfo;
    ImageIcon diceicon;
    JLabel dicelabel=new JLabel();

    /**
     * <b>constructor</b>:Constructs a new PlayerUI.<br />
     * <b>postcondition</b>:Constructs a new PlayerUI and initializes
     * its buttons and text
     */
    public PlayerUI(controller t,int x, int y,boolean playerA){
        //constructs a panel so it can be added later into the main interfaceUI
        this.g=t;
        setLayout(new BorderLayout());
        if(playerA) {
            playername = new JLabel(g.GetA().getName());
            moneyinfo=new JTextField("Money : "+g.GetA().getMoney());
            loaninfo=new JTextField("Loan : " +g.GetA().getloan());
            billinfo=new JTextField("Bills : "+g.GetA().getBills());
        }else {
            playername = new JLabel(g.GetB().getName());
            moneyinfo=new JTextField("Money : "+g.GetA().getMoney());
            loaninfo=new JTextField("Loan : " +g.GetA().getloan());
            billinfo=new JTextField("Bills : "+g.GetA().getBills());
        }
        rolldice=new JButton("Roll the dice");
        dealcards=new JButton("My Deal Cards");
        getloan=new JButton("Get Loan");
        endturn=new JButton("End Turn");
        endturn.setEnabled(false);


        diceicon=new ImageIcon("src\\resources\\images\\dice-1.jpg");
        Image diceimage=diceicon.getImage();
        diceimage = diceimage.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        dicelabel=new JLabel(new ImageIcon(diceimage));
        dicelabel.setSize(80,80);

        setBounds(x, y, 200, 130);
        setSize(320, 260);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        Color color=Color.yellow;
        if(playerA) {
            color=Color.blue;
        }
        Border border = new LineBorder(color, 3, false);
        setBorder(border);
        moneyinfo.setEditable(false);
        moneyinfo.setOpaque(false);
        moneyinfo.setBorder(null);
        loaninfo.setEditable(false);
        loaninfo.setOpaque(false);
        loaninfo.setBorder(null);
        billinfo.setEditable(false);
        billinfo.setOpaque(false);
        billinfo.setBorder(null);

        add(playername);
        add(moneyinfo);
        add(loaninfo);
        add(billinfo);
        add(rolldice);
        add(dicelabel);
        JDesktopPane temp1=new JDesktopPane();
        temp1.setLayout(new BoxLayout(temp1,BoxLayout.X_AXIS));
        temp1.add(dealcards);
        temp1.add(getloan);
        temp1.add(endturn);
        add(temp1,BorderLayout.SOUTH);
        setMyDeals(false);

    }


    /**
     * <b>mutator</b>
     * Set the  texts of the player's box information
     */
    public void refresh(boolean playerA){
        //set new text to the player box
        if(playerA) {
            moneyinfo.setText("Money : " + g.GetA().getMoney());
            loaninfo.setText("Loan : " + g.GetA().getloan());
            billinfo.setText("Bills : " + g.GetA().getBills());
        }else{
            moneyinfo.setText("Money : " + g.GetB().getMoney());
            loaninfo.setText("Loan : " + g.GetB().getloan());
            billinfo.setText("Bills : " + g.GetB().getBills());
        }
        this.repaint();
    }

    /**
     * <b>accessor</b>
     * Get the dice button
     */
    public JButton getDicebt(){
        return rolldice;
    }

    /**
     * <b>accessor</b>
     * Get the "My deals button" button
     */
    public JButton getmydealsbt(){
        return dealcards;
    }

    /**
     * Repaints the ImageIcon of the dice
     * @param n the number of the dice
     */
    public void refreshdice(int n){
        diceicon=new ImageIcon("src\\resources\\images\\dice-"+n+".jpg");
        Image diceimage=diceicon.getImage();
        diceimage = diceimage.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        dicelabel.setIcon(new ImageIcon(diceimage));
        dicelabel.setSize(80,80);
        dicelabel.repaint();
    }


    /**
     * <b>mutator</b>
     * Disables all the buttons
     */
    public void disablebuttons(){
        rolldice.setEnabled(false);
        dealcards.setEnabled(false);
        getloan.setEnabled(false);
        endturn.setEnabled(false);
    }

    /**
     * <b>mutator</b>
     * Enables all the buttons except the end turn button
     */
    public void enablebuttons(){
        rolldice.setEnabled(true);
        getloan.setEnabled(true);
    }

    /**
     * <b>mutator</b>
     * @param t enables or disables the dice button based on the t value
     */
    public void setdice(boolean t){
      rolldice.setEnabled(t);
    }

    /**
     * <b>mutator</b>
     * @param t enables or disables theMy Deal Cards button based on the t value
     */
    public void setMyDeals(boolean t){
        dealcards.setEnabled(t);
    }

    /**
     * <b>mutator</b>
     * @param t enables or disables the End Turn button based on the t value
     */
    public void enableEnd(boolean t){
        endturn.setEnabled(t);
    }

    /**
     * ActionListener of the dice
     */
    public void addrollaction(ActionListener listenforroll){
        rolldice.addActionListener(listenforroll);
    }
    /**
     * Adds an action for the "My Deal Cards" button
     */
    public void addplayerdealaction(ActionListener listenforplayerdeal){
        dealcards.addActionListener(listenforplayerdeal);
    }
    /**
     * Adds an action for the "Get a loan" button
     */
    public void addgetloanaction(ActionListener listengetloan){
        getloan.addActionListener(listengetloan);
    }
    /**
     * Adds an action for the "End Turn" button
     */
    public void addendturnaction(ActionListener listenendturn){
        endturn.addActionListener(listenendturn);
    }
}