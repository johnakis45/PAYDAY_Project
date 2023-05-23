package Model.cards;

import Model.Player;
/**
 * @version Alpha
 * @author csd4622
 */
public class Advertisement extends MailCard{
    /**
     * <b>constructor</b>:Constructs a new Advertisement Card
     * @param imageName the URL of the card's image
     * @param message the message of the card
     * @param cost the money that the player will get
     */
    public Advertisement(String imageName, String message,String bmsg, int cost) {
        super(imageName, message,bmsg, cost);
        this.title="Advertisement";
    }

    /**
     *<b>postcondition</b>:update the player's money
     * @param p the player that holds the card
     * player money = money+cost
     */
    @Override
    public void PerformAction(Player p) {
        p.setMoney(this.getCost());
    }
}
