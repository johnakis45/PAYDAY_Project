package Model;
/**
 * @version Alpha
 * @author csd4622
 */
public class jackpot {
    private int money;

    /**
     * <b>constructor</b>:Constructs a new jackpot
     * initialize money=0
     */
    public jackpot(){
        this.money=0;
    }

    /**
     * <b>accessor</b>:Gets jackpot's money
     * @return jackpot money
     */
    public int getMoney(){
        return money;
    }

    /**
     * <b>Mutator</b>:Changes the jackpot's money
     * <b>precondition</b>The parameter m should be a positive integer
     * @param m the money that will be added to the jackpot's money
     */
    public  void addMoney(int m){
        //check if m>=0
        this.money=this.money+m;
    }
    /**
     * <b>Mutator</b>:Changes the jackpot's money
     * <b>postcondition</b>Money should now be zero
     * Resets the money to zero
     */
    public void reset(){
        this.money=0;
    }

}
