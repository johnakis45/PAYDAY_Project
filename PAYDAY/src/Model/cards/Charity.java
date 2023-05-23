package Model.cards;

import Model.Player;

/**
 * @version Alpha
 * @author csd4622
 */
public class Charity extends MailCard{
    /**
     * <b>constructor</b>:Constructs a new Charity Card
     * @param imageName the URL of the card's image
     * @param message the message of the card
     * @param cost the money that the player will give to the card(eventually the Jackpot)
     */
    public Charity(String imageName, String message,String bmsg, int cost) {
        super(imageName, message,bmsg, cost);
        this.title="Charity";
    }


}
