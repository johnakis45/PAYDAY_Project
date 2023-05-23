package Model;

import Model.cards.*;
import Model.position.*;
import Model.stacks.StackOfCards;
import Model.stacks.StackOfDealCards;
import Model.stacks.StackOfMailCards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version Alpha
 * @author csd4622
 */
public class board {
    ClassLoader cldr;
    String[][] mailCards = new String[48][4];
    String[][] dealCards = new String[20][8];
    public Position[] tablo;
    public jackpot j;
    public StackOfCards rejected;
    public StackOfMailCards mailstack;
    public StackOfDealCards dealstack;

    /**
     * <b>constructor</b> Initializes the tablo,stacksofCards and the jackpot
     */
    public board() {
        cldr= this.getClass().getClassLoader();
        rejected=new StackOfCards();
        stack_initializer();
        Position_initializer();
        j=new jackpot();
    }

    /**
     * <b>mutator</b> Creates two new stacks and adds the cards they have
     */
    public void stack_initializer() {/*initialize the stacks*/
        mailstack=new StackOfMailCards();
        dealstack=new StackOfDealCards();
        readFile("resources/dealCards.csv", "Deal");
        readFile("resources/mailCards.csv", "Mail");
        int i=0;
        for (int j = 0; j < 6; j++) {
            for (int c = 0; c < 8; c++) {
                switch (j){
                    case 0 :
                        mailstack.push(new Advertisement("src\\resources\\images\\" + mailCards[i][5],mailCards[i][2],mailCards[i][3],Integer.parseInt(mailCards[i][4])));
                        break;
                    case 1 :
                        mailstack.push(new Bill("src\\resources\\images\\" + mailCards[i][5],mailCards[i][2],mailCards[i][3],Integer.parseInt(mailCards[i][4])));
                        break;
                    case 2 :
                        mailstack.push(new Charity("src\\resources\\images\\" + mailCards[i][5],mailCards[i][2],mailCards[i][3],Integer.parseInt(mailCards[i][4])));
                        break;
                    case 3 :
                        mailstack.push(new PaytheNeighbor("src\\resources\\images\\" + mailCards[i][5],mailCards[i][2],mailCards[i][3],Integer.parseInt(mailCards[i][4])));
                        break;
                    case 4 :
                        mailstack.push(new TakefromNeighbor("src\\resources\\images\\" + mailCards[i][5],mailCards[i][2],mailCards[i][3],Integer.parseInt(mailCards[i][4])));
                        break;
                    case 5 :
                        mailstack.push(new MoveTo("src\\resources\\images\\" + mailCards[i][5],mailCards[i][2],mailCards[i][3]));
                        break;
                    default:
                        break;
                }
                i++;
            }
        }
       for(int k=0;k<20;k++){
           dealstack.push(new DealCard(Integer.parseInt(dealCards[k][4]),Integer.parseInt(dealCards[k][3]),dealCards[k][2],"src\\resources\\images\\" + dealCards[k][5]));
       }
      dealstack.Shuffle();
      mailstack.Shuffle();
    }
    /**
     * <b>transformer</b>Initialises the positions inside the table array
     * randomly
     * <p>
     * <b>Postcondition:</b> sets an array that holds all the positions of the
     * game tha are needed
     */
    public void Position_initializer(){
        /*initializes the positions*/
        tablo=new Position[32];
        tablo[0]=new StartPosition("src\\resources\\images\\start.png");
        tablo[31]=new PayDayPosition("src\\resources\\images\\pay.png");
        int i=1;
        while(i<9){
            if(i%2==0){
                tablo[i]=new CardPosition("src\\resources\\images\\mc1.png",i,true,false);
            }else{
                tablo[i]=new CardPosition("src\\resources\\images\\mc2.png",i,false,false);
            }
            i++;
        }
        while(i<14){
            tablo[i]=new CardPosition("src\\resources\\images\\deal.png",i,true,true);
            i++;
        }
        while(i<16){
            tablo[i]=new sweepstakes("src\\resources\\images\\sweep.png",i);
            i++;
        }
        while(i<18){
            tablo[i]=new radiocontest("src\\resources\\images\\radio.png",i);
            i++;
        }
        while(i<20){
            tablo[i]=new casino("src\\resources\\images\\casino.png",i);
            i++;
        }
        while(i<22){
            tablo[i]=new Sale("src\\resources\\images\\yard.png",i);
            i++;
        }
        while(i<28){
            tablo[i]=new Buyer("src\\resources\\images\\buyer.png",i);
            i++;
        }
        while(i<31){
            tablo[i]=new lottery("src\\resources\\images\\lottery.png",i);
            i++;
        }
        Position_randomizer(tablo);
    }

    /**
     * <b>mutator</b>
     *puts the positions of the table in random positions(except the start and payday)
     * precondition:An initialised array
     * postcondition:returns an array that except first and last
     * all the other positions are random
     * @param table the array that holds the positions
     */
    private void Position_randomizer(Position[] table){
        /*randomizes the positions except the first and the last one*/
        Random rand = new Random();
        int k= table.length;
        for(int i = 1; i < k; ++i) {
            int index = rand.nextInt(k - i);
            if(index !=0) {
                Position tmp = table[i];
                table[i] = table[index];
                table[index] = tmp;
            }
        }
        for(int i=1;i<k;i++){
            table[i].setIndex(i);
            switch (i%7){
                case 6:
                    table[i].setDay("Saturday");
                    break;
                case 5:
                    table[i].setDay("Friday");
                    break;
                case 4:
                    table[i].setDay("Thursday");
                    break;
                case 3:
                    table[i].setDay("Wednesday");
                    break;
                case 2:
                    table[i].setDay("Tuesday");
                    break;
                case 1:
                    table[i].setDay("Monday");
                    break;
                case 0:
                    table[i].setDay("Sunday");
                    break;
                default:
                    break;
            }
        }
    }

    //it is used to get the information for every card(messages,prices etc.)
    //reads from the csv file and returns the String array of the type of card we want
    public void readFile(String path, String type) {//was String[][]
        BufferedReader br = null;
        String sCurrentLine;
        try {
            String fullPath = cldr.getResource(path).getPath();
            br = new BufferedReader(new FileReader(fullPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(board.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        try {
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                if (type.equals("Mail")) {
                    mailCards[count++] = sCurrentLine.split(",");
                } else {
                    dealCards[count++] = sCurrentLine.split(",");
                }
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(board.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

