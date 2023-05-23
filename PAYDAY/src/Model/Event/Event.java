package Model.Event;

/**
 * @version Alpha
 * @author csd4622
 */
public abstract class Event {
    protected int money;

    /**
     * <b>constructor</b>:Constructs an event
     * / no money parameter
     * /initialize money = 0
     */
    public Event(){
        this.money=0;
    }

    /**
     * <b>accessor</b>Get the money
     * @return getMoney
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Does nothing here
     * @param choice should be between 0 and 3
     * @return if the player has won , lost or did not play
     */
    public abstract int EventAction(int choice);

}
