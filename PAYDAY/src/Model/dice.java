package Model;
/**
 * @version Alpha
 * @author csd4622
 */
public class dice {
    private int number; //from 1 to 6
    private boolean isRolled;

    /**
     * <b>constructor</b>:Constructs a new dice
     * initialize number=0(invalid value)
     * initialize isRolled=false because it has not been rolled yet
     */
    public dice(){
        this.number=0;
        this.isRolled=false;
    }

    /**
     * <b>accessor</b>Gets the isRolled variable
     * @return if it;s rolled or not
     */
    public boolean getRolled(){
        return this.isRolled;
    }

    /**
     * <b>Mutator</b>
     * <b>postcondition</b> Change the rolled variable
     * @param rolled use its value to update the isRolled variable
     */
    public void setRolled(boolean rolled){
        this.isRolled=rolled;
    }

    /**
     * <b>accessor</b>Gets the number of the dice
     * @return the current number of the dice
     */
    public int getNumber(){
        return this.number;
    }

    /**
     * Rolls the dice
     * <b>postcondition</b>:change the number of the dice randomly(could be the same as before)
     */
    public void roll(){
        isRolled=true;
        number=1+(int)(Math.random()*6);
    }
}
