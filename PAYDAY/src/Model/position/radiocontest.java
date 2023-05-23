package Model.position;

import Model.Player;

public class radiocontest extends TwoPlayerPos{
    /**
     * <b>constructor</b>:Constructs a radio contest position
     * @param imageurl the image's URL of the position
     * @param pos positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public radiocontest(String imageurl, int pos) {
        super(imageurl, pos);
    }


    /**
     * Return true if player p has won,false if not
     * @param p the player who is at the position
     * @param n1 the dice number of the first player
     * @param n2 the dice number of the second player
     * @return if the player p has won
     */
    @Override
    public boolean PerformAction(Player p, int n1, int n2) {
        if(n1>n2){
            return true;
        }else if(n1<n2){
            return false;
        }
        return true;
    }
}
