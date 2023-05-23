package Model.cards;

/**
 * @version Alpha
 * @author csd4622
 */
public class MoveTo extends MailCard{
    /**
     * <b>constructor</b>:Constructs a new MoveTo Card
     * @param imageName the URL of the card's image
     * @param message the message of the card
     * there is no cost parameter because in this card the player does not get either give money
     * initialize cost=0
     */
    public MoveTo(String imageName, String message,String bmsg) {
        super(imageName, message,bmsg, 0);
        this.title="Move To";
    }
}
