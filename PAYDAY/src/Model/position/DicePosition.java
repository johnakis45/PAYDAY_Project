package Model.position;

import Model.Player;

public abstract class DicePosition extends Position {
    /**
     * <b>Constuctor</b>Constructs a DicePosition(just for the subclasses)
     * @param imageurl the image's URL of the position
     * @param pos positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public DicePosition(String imageurl, int pos) {
        super(imageurl, pos);
    }

    /**
     * Does nothing here
     * @param p the player that is in this position
     */
    @Override
    public void performAction(Player p) {}
}
