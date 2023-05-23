package Model;

import Model.cards.*;

import java.util.ArrayList;
/**
 * @version Alpha
 * @author csd4622
 */
public class Player {
    private double money;
    private double loan;
    private int bills;
    private ArrayList<DealCard> mydealcards;
    private ArrayList<Bill> mybillcards;
    private int currentpos;
    private int months;
    private Player opponent;
    private final dice mydice;
    private boolean turn;
    private boolean first;
    private boolean finish;
    private boolean wait;
    private final String name;

    /**
     * <b>constructor</b>:Constructs a new Player
     * @param name is the name of the player
     * initialize most of the private variables
     * money=3500 because every new player starts with 3500 money
     */
    public Player(String name,boolean first) {
        this.name=name;
        this.first=first; //added in phase B
        this.money=3500;
        this.loan=0;
        this.bills=0;
        this.months=0;
        this.currentpos=0;
        this.turn=false;
        this.finish=false;
        this.wait=false;
        this.mydice=new dice();
        this.mydealcards=new ArrayList<DealCard>();
        this.mybillcards=new ArrayList<Bill>();
    }



    /**
     * <b>accessor</b>:
     * @return player's bills
     */
    public int getBills(){
        return this.bills;
    }
    /**
    /**
     * <b>accessor</b>
     * @return player's money
     */
    public double getloan(){
        return this.loan;
    }
    /**
    /**
     * <b>accessor</b>:
     * @return player's money
     */
    public double getMoney(){
        return this.money;
    }
    /**
     * <b>mutator</b>:Add a deal card to the player deal cards
     * @param c the deal card we add
     */

    /**
     * <b>mutator</b>:Add money to the bills
     * <b>precondition</b> Cannot -money>player's bills
     * @param money that will be added to the bills
     */
    public void setBills(int money){
        this.bills=this.bills+money;
    }
    /**
     * <b>mutator</b>:Add money to the loan
     * <b>precondition</b> Cannot -money>player's loan
     * @param money that will be added to the loan
     */
    public void setLoan(double money){
        this.loan=this.loan+money;
        this.money=this.money+money;
    }
    /**
     * <b>mutator</b>:Add money to the player's money
     * <b>precondition</b> Cannot -money>player's money
     * @param money that will be added to the player's money
     */
    public void setMoney(double money){
        this.money=this.money+money;
    }
    /**
     * <b>mutator</b>:Add a deal card to the player deal cards
     * @param c the deal card we add
     * @param sale if it is gettin the card from a yard sale position
     */
    public void AddDealCard(DealCard c,boolean sale){
        if(!sale) {
            while (!this.hasMoney(c.getCost())) {
                this.setLoan(1000);
            }
            this.setMoney(-c.getCost());
        }
        this.mydealcards.add(c);
    }
    /**
     * <b>mutator</b>:Add a bill card to the player bill cards
     * @param c the bill card we add
     */
    public void AddBillCard(Bill c){
        c.PerformAction(this);
        this.mybillcards.add(c);
    }

    /**
     * <b>mutator</b>:Changes the things based on the mail card
     * @param m the mail card we have
     */
    public void AddMailCard(MailCard m){
        if(m instanceof Bill){
            this.AddBillCard((Bill)m);
        }else{
            m.PerformAction(this);
        }
    }

    /**
     * <b>mutator</b>:Removes the all the deal cards
     */
    public void clearDeals(){
        mybillcards.clear();
    }
    /**
     * <b>mutator</b>:Removes the all the bill cards from the player and initializes bill=0
     */
    public void clearBills(){
        this.bills=0;
    }
    /**
     * <b>accessor</b>:Gets PLayer's name
     * @return player's name
     */
    public String getName(){
        return this.name;
    }
    /**
     * <b>accessor</b>:Gets PLayer's last card int the list of cards
     * @return player's the card
     */
    public DealCard SellCard(){
        return this.mydealcards.get(0);
    }
    /**
     * <b>mutator</b>:Remove a card from the player's cards
     * <b>postcondition</b>Update the money of the player and remove the card
     */
    public void SellCardAction(){
        setMoney(this.SellCard().getSellprice());
        this.mydealcards.remove(0);
    }
    /**
     * <b>mutator</b>:Updates the player's months
     * <b>precondition</b>parameter months should not be smaller than 0 and bigger than 3
     * @param months the updated months
     */
    //check if months<0 and months>3
    public void setMonths(int months){this.months=months;}
    /**
     * <b>accessor</b>:Gets Player's current month
     * @return player's current month
     */
    public int getMonths(){
        return this.months;
    }

    /**
     * <b>mutator</b>:Updates the player's opponent
     * <b>precondition</b> Cannot be the same player
     * @param enemy the player that is the opponent
     */
    public void setOpponent(Player enemy){
        this.opponent=enemy;
    }

    /**
     * <b>accessor</b>:Gets PLayer's opponent
     * @return player's opponent
     */
    public Player getOpponent(){
        return opponent;
    }

    /**
     * <b>observer</b>:Gets player's name and compares them to a parameter
     * @param cost the money that it compares player;s money
     * @return if the player has more money than the cost
     */
    public boolean hasMoney(double cost){
        if(money>=cost){
            return true;
        }else return false;
    }

    /**
     * <b>observer</b>:Check if the player has loan
     * @return if the player has loan or not
     */
    public boolean hasLoan(){
        if(loan==0){
            return false;
        }else return true;
    }

    /**
     * <b>observer</b>:Check if has bills
     * @return if the player has bills or not
     */
    public boolean hasBills(){
        if(bills==0){
            return false;
        }else return true;
    }

    /**
     * <b>observer</b>:Check if the player has deal cards
     * @return if the player has cards or not
     */
    public boolean hasCards(){return !mydealcards.isEmpty();}

    /**
     * <b>accessor</b>:Gets Player's dice
     * @return player's dice
     */
    public dice getMydice() {
        return mydice;
    }

    /**
     * <b>accessor</b>:Gets Player's position
     * @return player's position
     */
    public int getCurrentpos() {
        return this.currentpos;
    }

    /**
     * <b>mutator</b>:Updates the position of the player
     * <b>precondition</b> pos should be positive
     * <b>postcondition</b> change the player's position (if new position out of range then put it in the last one)
     * @param pos player's new position
     */
    //check if the new current position is in within range and the pos>0
    public void setCurrentpos(int pos){this.currentpos=pos+this.currentpos;
    if(pos==31) currentpos=31;
    if(pos==0) currentpos=0;}

    /**
     * <b>observer</b>:Check if player has a turn
     * @return if the player has a turn or not
     */
    public boolean getTurn(){
        return this.turn;
    }

    /**
     * <b>mutator</b>:Updates the turn of the player
     * @param t player's new turn
     */
    public void setTurn(boolean t){
        this.turn=t;
    }

    /**
     * <b>observer</b>:Check if player has finished the month
     * @return if the player has a turn or not
     */
    public boolean isFinish(){
        return finish;
    }

    /**
     * <b>mutator</b>:Updates the finish variable
     * @param t is the new value if the player has finished or not
     */
    public void setFinish(boolean t){
        this.finish=t;
    }

    /**
     * <b>observer</b>:Check if player has a turn
     * @return if the player has a turn or not
     */
    public boolean isWait(){
        return wait;
    }

    /**
     * <b>mutator</b>:Updates the finish variable
     * @param t is the new value if the player has finished or not
     */
    public void setWait(boolean t){
        this.wait=t;
    }


}
