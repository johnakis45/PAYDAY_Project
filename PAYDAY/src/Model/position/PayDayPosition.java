package Model.position;

import Model.Player;

public class PayDayPosition extends Position{

    /**
     * <b>constructor</b>:Constructs a Payday position
     * @param image the image's URL of the position,
     * There is not pos parameter as the Starting position is always at 31
     */
    public PayDayPosition(String image){
        super(image,31);
        setDay("Wednesday");
    };

    /**
     * Does nothing here
     * @param p the player that is in this position
     */
    @Override
    public void performAction(Player p) {
    }
}
