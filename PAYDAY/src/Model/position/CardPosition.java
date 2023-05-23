package Model.position;
import Model.Player;

public class CardPosition extends Position {
    private final boolean drawonecard;
    private final boolean deal;

    /**
     * <b>constructor</b>:Constructs a Card position
     * @param imageurl the image's URL of the position
     * @param pos positions number which must be from 1 to 30 and not be taken
     * from another position
     * @param drawonecard if it is a position in which the player draws one card
     */
    public CardPosition(String imageurl, int pos,boolean drawonecard,boolean deal) {
        super(imageurl, pos);
        this.drawonecard=drawonecard;
        this.deal=deal;
    }
    /**
     * <b>Accessor</b>:
     * returns if the player must draw one card or two
     * @return drawonecard
     */
    public boolean getOneCard(){
        return drawonecard;
    }

    /**
     * <b>Accessor</b>:
     * returns if the card is a deal card or not
     * @return deal
     */
    public boolean isDeal(){
        return this.deal;
    }

    /**
     * Does nothing here
     * @param p the player that is in this position
     */
    @Override
    public void performAction(Player p) {}
}
