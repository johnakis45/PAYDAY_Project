package Model.cards;

import Model.Player;
/**
 * @version Alpha
 * @author csd4622
 */
public class DealCard extends Cards{
    private final int sellprice;
    /**
     * <b>constructor</b>:Constructs a new DealCard Card
     * @param sellprice the price of the card if it will be sold
     * @param buyprice the money that the player gives to get the card
     * @param message the message the UI should display
     * @param Imagename the image URL of the card
     */
    public DealCard(int sellprice,int buyprice,String message,String Imagename) {
        super(Imagename,message,buyprice);
        this.sellprice=sellprice;
        this.title="Deal Card";
    }

    /**
     * <b>accessor</b>:Access the card's sell price
     * @return the sell price of the deal card
     */
    public int getSellprice(){
        return sellprice;
    }

    /**
     *The actions that should do if a player has this card
     * <b>postcondition</b>:add card to the player's card list and reduce p.money
     * @param p the player that has the card
     * if p.money>cost (else get a loan) the card goes to the player card stack
     */
    @Override
    public void PerformAction(Player p) {
        while(p.getMoney()<this.getCost()){
            p.setLoan(1000);
        }
        p.AddDealCard(this,false);
    }
}
