package Model.position;

import Model.Player;

public class lottery extends TwoPlayerPos{
    /**
     * <b>constructor</b>:Constructs a lottery position
     * @param imageurl the image's URL of the position
     * @param pos positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public lottery(String imageurl, int pos) {
        super(imageurl, pos);
    }


    /**
     *Returns true if player p has won ,false if not
     * @param p the player who is at the position
     * @param n1 the number of the first player
     * @param n2 the number of the second player
     * @return if the player p has won
     */
    @Override
    public boolean PerformAction(Player p, int n1, int n2) {
        if(p.getMydice().getNumber()==n1){
            return true;
        }else if(p.getOpponent().getMydice().getNumber()==n2){
            return false;
        }
        return false;
    }
}
