package View;

import Controller.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * @version Alpha
 * @author csd4622
 */
public class interfaceUI extends JFrame {
    controller g;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) Math.round(screenSize.getWidth()) - 220;
    private final int height = (int) Math.round(screenSize.getHeight()) - 220;
    JLabel pawnA=new JLabel();
    JLabel pawnB=new JLabel();
    PaneOver panel;
    JDesktopPane[] positions,pawns;
    JButton mailbutton =new JButton("");
    JButton dealbutton =new JButton("");
    JDesktopPane table,pawntable;
    ImageIcon jackpot;
    JTextField jackpotprice;
    public PlayerUI player1;
    public PlayerUI player2;
    InfoBoxUI info;

    /**
     * <b>constuctor</b>
     * @param t the controller
     */
    public interfaceUI(controller t){
        this.g=t;
        Image iconimage=new ImageIcon("src\\resources\\images\\logo.png").getImage();
        this.setIconImage(iconimage);
        this.setSize(width-600, height+20);
        this.getContentPane().setBackground(new Color(0, 109, 0));
        this.setResizable(false);
        this.setTitle("PayDay");
        this.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //add background
        ImageIcon background=new ImageIcon("src\\resources\\images\\bg_green.png");
        Image temp=background.getImage();
        Image img=temp.getScaledInstance(width-600,height+20,Image.SCALE_AREA_AVERAGING);
        panel=new PaneOver(img);
        add(panel);


        //add deal card button
        dealbutton.setEnabled(false);
        dealbutton.setSize(width * (2 / 16) - 40, height * 1 / 7);
        ImageIcon dealicon = scaleimage("src\\resources\\images\\dealCard.png",width * (2 / 16) - 20, height * 1 / 7 - 15);
        dealbutton.setIcon(dealicon);
        dealbutton.setBounds(920,450,150,80);
        //dealbutton.addActionListener(new Action());
        panel.add(dealbutton);

        //add mail card button
        mailbutton.setEnabled(false);
        mailbutton.setSize(width * (2 / 16) - 40, height * 1 / 7);
        ImageIcon mailicon=scaleimage("src\\resources\\images\\mailCard.png",width * (2 / 16) - 20, height * 1 / 7 - 15);
        mailbutton.setIcon(mailicon);
        mailbutton.setBounds(750,450,150,80);
        //mailbutton.addActionListener(new Action());
        panel.add(mailbutton);

        //add logo-title
        JLabel title=new JLabel();
        title.setIcon(scaleimage("src\\resources\\images\\logo.png",width * 36/85, height/ 5));
        title.setBounds(20,0,2000,200);
        panel.add(title);

        menu_creator();
        init_infobox();
        init_table();
        init_players();

    }

    /**
     * <b>accessor</b>
     * See if the mail or deal buttons are enabled
     */
    public boolean isEnabled(){
        return mailbutton.isEnabled() || dealbutton.isEnabled();
    }



    /**
     * Disable or enable mail button
     * @param t the value that determines if the button is enabled
     */
    public void setEnableMail(boolean t){
        mailbutton.setEnabled(t);
    }

    /**
     * Disable or enable deal button
     * @param t the value that determines if the button is enabled
     */
    public void setEnableDeal(boolean t){
        dealbutton.setEnabled(t);
    }


    /**
     * Initialises the menu buttons
     */
    public void menu_creator(){

    }

    /**
     *initialises the board with the positions
     */
    public void init_table(){
        this.table=new JDesktopPane();
        this.table.setOpaque(false);
        this.pawntable=new JDesktopPane();
        this.pawntable.setOpaque(false);
        this.pawns= new JDesktopPane[32];
        this.positions=new JDesktopPane[32];
        this.table.setLayout(new GridLayout(5,7));
        this.pawntable.setLayout(new GridLayout(5,7));

        this.table.setBounds(20, 220, width * 36/85, height * 30/43);
        this.pawntable.setBounds(20, 220, width * 36/85, height * 30/43);
        for(int i=0;i<32;i++){
            pawns[i]=new JDesktopPane();
            pawns[i].setOpaque(false);
            pawns[i].setLayout(new BoxLayout(pawns[i], BoxLayout.X_AXIS));
            pawns[i].setSize(100,100);

            positions[i]=new JDesktopPane();
            positions[i].setOpaque(true);
            positions[i].setLayout(new BoxLayout(positions[i], BoxLayout.Y_AXIS));
            positions[i].setSize(100,100);


            //add name
            JTextField PosName=new JTextField(g.GetBoard().tablo[i].getDay() + " " + g.GetBoard().tablo[i].getIndex());
            if(i==0){
                PosName=new JTextField(g.GetBoard().tablo[i].getDay());
            }
            PosName.setMaximumSize(new Dimension(200,40));
            PosName.setBackground(Color.YELLOW);
            PosName.setEditable(false);
            PosName.setOpaque(true);
            PosName.setBorder(null);
            positions[i].add(PosName);

            JLabel PosPic=new JLabel(scaleimage(g.GetBoard().tablo[i].getImage(),100,100));
            PosPic.setSize(100,100);
            positions[i].add(PosPic);

            pawntable.add(pawns[i]);
            table.add(positions[i]);
        }
        this.jackpot=scaleimage("src\\resources\\images\\jackpot.png",180,100);
        JLabel Jackpot=new JLabel(jackpot);
        Jackpot.setBounds(500,704,180,100);
        panel.add(Jackpot);

        Font f = new Font("Serif",Font.BOLD,20);
        jackpotprice=new JTextField();
        jackpotprice.setFont(f);
        jackpotprice.setForeground(Color.yellow);
        jackpotprice.setEditable(false);
        jackpotprice.setOpaque(false);
        jackpotprice.setBorder(null);
        jackpotprice.setBounds(500,770,180,100);
        paintJackpot(0);
        panel.add(jackpotprice);

        panel.add(pawntable);
        panel.add(table);

    }
    /**
     * Create 2 PLayerUIs,initialize them and add them to the interfaceUI
     */
    public void init_players(){
        //add player panels (should add Player element so it can take the info for each player)did do that
        //because I have not finished Player class yet
        player1=new PlayerUI(g,750,20,true);
        player2=new PlayerUI(g,750,550,false);
        panel.add(player1);
        panel.add(player2);


        //demo

    }
    /**
     * initialises the infobox panel
     */
    public void init_infobox(){
        info=new InfoBoxUI(g);
        panel.add(info);
    }

    /**
     * Prints the msg to the infobox
     * @param msg
     */
    public void refresh_infobox(String msg){
       info.refresh(msg);
    }
    /**
     *Moves the player depending the number of the dice and changes  the players pawn position
     * @param i the new position the player has to go
     * @param p the current position of the player
     * @param blue ig it is the blue pawn
     */
    public void changeposition(int i,int p,boolean blue){
        ImageIcon imgpawn;
        if (blue) {
            pawns[p].remove(pawnA);
            imgpawn= scaleimage("src\\resources\\images\\pawn_blue.png", 50, 50);
            pawnA.setIcon(imgpawn);
            pawns[i].add(pawnA);
        } else {
            pawns[p].remove(pawnB);
            imgpawn = scaleimage("src\\resources\\images\\pawn_yellow.png", 50, 50);
            pawnB.setIcon(imgpawn);
            pawns[i].add(pawnB);
        }
        pawns[p].repaint();
        pawns[i].repaint();
    }


    /**
     * <b>mutator</b>Updates jackpotpirce
     * @param price the new value that Jlabel Jackpotprice will display
     */
    public void paintJackpot(int price){
        if(price==0 || price==1){
            jackpotprice.setText("Jackpot : " + price + " Euro");
        }
        jackpotprice.setText("Jackpot : " + price + " Euros");
    }

    /**
     * Adds an action for the "Get a mail card" button
     */
    public void addmailbuttonaction(ActionListener listenformail){
        mailbutton.addActionListener(listenformail);
    }
    /**
     * Adds an action for the "Get a deal card" button
     */
    public void adddealbuttonaction(ActionListener listenfordeal){
        dealbutton.addActionListener(listenfordeal);
    }
    /**
     * Adds an action for the "New Game" button
     */
    public void addnewgameaction(ActionListener listenfornewgame){
        dealbutton.addActionListener(listenfornewgame);
    }

    /**
     * Takes an image path and scales the image ,returning the imageicon
     * @param image the path of the image
     * @param width the width we want it to be
     * @param height the height we want it to be
     * @return the ImageIcon of the image url we gave
     */
    static ImageIcon scaleimage(String image, int width, int height) {
        Image temp = new ImageIcon(image).getImage().getScaledInstance(
                width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(temp);
    }

    //added in phase B

    /**
     *  a class which is used for putting a background image to a jdesktoppane
     */
    public class PaneOver extends JLayeredPane {

        private Image image;

        public PaneOver (Image img) {
            image = img;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
}
