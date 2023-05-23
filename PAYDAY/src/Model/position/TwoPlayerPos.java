package Model.position;
import Model.Player;

public abstract class TwoPlayerPos extends DicePosition{

    /**
     * <b>constructor</b>:Constructs a lottery position
     * @param imageurl the image's URL of the position
     * @param pos positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public TwoPlayerPos(String imageurl, int pos) {
        super(imageurl, pos);
    }

    /**
     * Abstract method that does nothing here
     * and it's only here just for the subclasses
     * @param p the player who is at the position
     * @param n1 the number of the first player
     * @param n2 the number of the second player
     * @return if the player p has won
     */
    public abstract boolean PerformAction(Player p,int n1,int n2);
}
