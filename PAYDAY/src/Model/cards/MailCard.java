package Model.cards;

import Model.Player;
/**
 * @version Alpha
 * @author csd4622
 */
public abstract class MailCard extends Cards {
    final private String buttonmsg;

    /**
     * <b>constructor</b>:Constructs a new MailCard Card(it is used for the subclasses)
     * @param imageName the URL of the card's image
     * @param message the message of the card
     * @param cost the money that the player will give to the card(eventually the neighbor)
     */
    public MailCard(String imageName, String message,String bmsg, int cost) {
        super(imageName, message, cost);
        this.buttonmsg=bmsg;
    }

    /**
     * <b>accessor</b>:Access the card's button message
     * @return tmessage of the button
     */
    public String getbuttonmsg(){
        return buttonmsg;
    }

    /**
     * Does nothing here
     * @param p the player that holds the card
     */
    @Override
    public void PerformAction(Player p) {}
}
