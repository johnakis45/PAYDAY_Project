package Model.position;

import Model.Player;

public class StartPosition extends Position{
    /**
     * <b>constructor</b>:Constructs a Starting position
     * @param image the image's URL of the position,
     * There is not pos parameter as the Starting position is always at 0
     */
    public StartPosition(String image) {
        super(image,0);
        setDay("Start");
    }

    /**
     * Does nothing here
     * @param p the player that is in this position
     */
    @Override
    public void performAction(Player p) {}
}
