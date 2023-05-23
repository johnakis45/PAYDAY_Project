package Model.cards;

import Model.Player;
/**
 * @version Alpha
 * @author csd4622
 */
public class TakefromNeighbor extends MailCard {
    /**
     * <b>constructor</b>:Constructs a new TakefromNeighbor Card
     * @param imageName the URL of the card's image
     * @param message the message of the card
     * @param cost the money that the player will get from the card(eventually the neighbor)
     */
    public TakefromNeighbor(String imageName, String message,String bmsg, int cost) {
        super(imageName, message,bmsg, cost);
        this.title="Take from Neighbor";
    }


    /**
     * Performs the actions that should be executed if the Player has this type of card
     * <b>postcondition</b>:get money from the opponent
     * @param p the player that has the card
     * checks if the opponent has money>cost to give to player p else the opponent must take a loan and pay him
     */
    @Override
    public void PerformAction(Player p) {
        if(p.getOpponent().hasMoney(this.getCost())) {
            p.getOpponent().setMoney(-this.getCost());
            p.setMoney(this.getCost());
        }else{
            while(!p.getOpponent().hasMoney(this.getCost())){
                p.getOpponent().setLoan(1000);
            }
            p.getOpponent().setMoney(-this.getCost());
            p.setMoney(this.getCost());
        }
    }
}
