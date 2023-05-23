package Model.position;

import Model.Player;

public class Sale extends OnePlayerPos{
    /**
     * <b>constructor</b>:Constructs a Sale position
     * @param imageurl the image's URL of the position
     * @param pos positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public Sale(String imageurl, int pos) {
        super(imageurl, pos);
    }

    /**
     * Does nothing here
     * @param p the player that is in this position
     */
    @Override
    public void performAction(Player p) {

        //check if dice was rolled(should roll it before calling the method
        //if yes then reduce the money of the player by dice numb * 100
        //if no enough take a 1000 loan money
        //the card tha should take afterwards implement it in controller

    }
}
