package Model.position;

import Model.jackpot;
import Model.Player;

public class casino extends OnePlayerPos{
    private boolean win;
    /**
     * <b>constructor</b>:Constructs a casino position
     * @param imageurl the image's URL of the position
     * @param pos positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public casino(String imageurl, int pos) {
        super(imageurl, pos);
    }

    /**
     * Performs the action of the casino
     * @param p the player that is on this position
     * @param j the jackpot that will be updated
     */
    public void PerformAction(Player p, jackpot j) {
        if(isWin(p.getMydice().getNumber())){
            p.setMoney(500);
        }else{
            p.setMoney(-500);
            j.addMoney(500);
        }
    }

    /**
     * <b>mutator</b>Check if the player won money from the casino
     * <b>precondition</b>dicenumb should be 1 to 6
     * <b>postcondition</b>update win variable
     * @param dicenumb the dice number of the player when they arrived in the position
     * @return if they won or not
     */
    public boolean isWin(int dicenumb){
    if(dicenumb%2==0){
        win= true;
    }else{
        win = false;
    }
    return win;
    }
}
