package Model.Event;

import Model.dice;

/**
 * @version Alpha
 * @author csd4622
 */
public class Crypto extends Event{
    /**
     * <b>constructor</b>:Constructs a new Crypto event
     * initialize money=300 because that's the bet for this event
     */
    public Crypto(){
        this.money=300;
    }


    /**
     * Takes the choice of the player
     * throws a dice for the player
     * and retruns the result
     * <b>precondition</b>: parameter choice should from 0 to 3
     * @param choice the choice that player has made through the CryptoUI
     * @return the result(win,lost,didn't play)
     */
    @Override
    public int EventAction(int choice) {
        dice temp=new dice();
        temp.roll();
        if(choice==0) {
            if ((temp.getNumber() == 1 || temp.getNumber() == 2)) {
                return 1;
            } else if ((temp.getNumber() == 3 || temp.getNumber() == 4)) {
                return 2;
            } else if ((temp.getNumber() == 5 || temp.getNumber() == 6)) {
                return 0;
            }
        }
        return 3;
        //randomize a number from 1 to 6(throws the dice for the player) and
        //compare it to the choice and return the result
        //return 0 if the choice was 3or4notsure (the player did not want to play)
        //return 1 if the player has won
        //return 2 if the player has lost
        //return 3 if he got the money back(nothing happened)
    }
}
