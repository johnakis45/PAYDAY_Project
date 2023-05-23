package Model.cards;

import Model.Player;
/**
 * @version Alpha
 * @author csd4622
 */
public abstract class Cards {
    final private String ImageName;
    final private int cost;
    final private String message;
    protected String title;

    /**
     * <b>constructor</b>:Constructs a new PaytheNeighbor Card
     * @param imageName the URL of the card's image
     * @param message the message of the card
     * @param cost the money that the player will get or give(depends on the card)
     */
    public Cards(String imageName,String message,int cost) {
        this.ImageName=imageName;
        if(cost<0) {
            this.cost = -cost;
        }else{
            this.cost=cost;
        }
        this.message=message;
    }


    /**
     * <b>accessor</b>:Access the card's cost
     * @return the cost(money that player will get or give) of the card
     */
    public String getTitle() {
        return title;
    }


    /**
     * <b>accessor</b>:Access the card's cost
     * @return the cost(money that playr will get or give) of the card
     */
    public int getCost() {
        return cost;
    }

    /**
     * <b>accessor</b>:Access the card's ImageName
     * @return the ImageName which is a URL string for the icon of the card
     */
    public String getImageName(){
        return ImageName;
    }

    /**
     * <b>accessor</b>:Access the card's message
     * @return the String message that the card should display
     */
    public String getMessage(){
        return message;
    }

    /**
     * Abstract function that does nothing here
     * It is used to execute the actions for each card(subclass)
     * @param p the player that holds the card
     */
    public abstract void PerformAction(Player p);
}
