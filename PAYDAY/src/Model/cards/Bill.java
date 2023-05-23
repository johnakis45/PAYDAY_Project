package Model.cards;

import Model.Player;

/**
 * @version Alpha
 * @author csd4622
 */
public class Bill extends MailCard{
    /**
     * <b>constructor</b>:Constructs a new Bill Card
     * @param imageName the URL of the card's image
     * @param message the message of the card
     * @param cost the money that the player will give to the card(eventually the bank)
     */
    public Bill(String imageName, String message,String bmsg, int cost) {
        super(imageName, message,bmsg, cost);
        this.title="Bill";
    }

    /**
     *<b>postcondition</b>:update the player's bills
     * @param p the player that holds the card
     * player bills = bills +cost
     */
    @Override
    public void PerformAction(Player p) {
        p.setBills(this.getCost());
    }
}
