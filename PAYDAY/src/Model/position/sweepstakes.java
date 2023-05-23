package Model.position;

import Model.Player;

public class sweepstakes extends OnePlayerPos{
    /**
     * <b>constructor</b>:Constructs a sweepstakes position
     * @param imageurl the image's URL of the position
     * @param pos positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public sweepstakes(String imageurl, int pos) {
        super(imageurl, pos);
    }

    /**
     * Performs the actions of the sweepstakes position
     * @param p the play that is on this position
     */
    @Override
    public void performAction(Player p) {
        //should roll the dice before calling the action
        p.setMoney(p.getMydice().getNumber()*1000);
    }
}
