package Model.cards;

import Model.Player;
/**
 * @version Alpha
 * @author csd4622
 */
public class PaytheNeighbor extends MailCard {
    /**
     * <b>constructor</b>:Constructs a new PaytheNeighbor Card
     * @param imageName the URL of the card's image
     * @param message the message of the card
     * @param cost the money that the player will give to the card(eventually the neighbor)
     */
    public PaytheNeighbor(String imageName, String message,String bmsg, int cost) {
        super(imageName, message,bmsg, cost);
        this.title="Pay the Neighbor";
    }

    /**
     * Performs the actions that should be executed if the Player has this type of card
     *  <b>postcondition</b>:pay the opponent
     * @param p the player that has the card
     * checks if the player has money>cost to give to opponent else player p must take a loan and pay
     */
    @Override
    public void PerformAction(Player p) {
        if(p.hasMoney(this.getCost())) {
            p.getOpponent().setMoney(this.getCost());
            p.setMoney(-this.getCost());
        }else{
            while(!p.hasMoney(this.getCost())){
                p.setLoan(1000);
            }
            p.getOpponent().setMoney(this.getCost());
            p.setMoney(-this.getCost());
        }
    }
}
