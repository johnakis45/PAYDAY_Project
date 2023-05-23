package Model.position;

public abstract class OnePlayerPos extends DicePosition {

    /**
     * <b>Constructor</b> Just for the subclasses
     * @param imageurl the image of the position
     * @param pos positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public OnePlayerPos(String imageurl, int pos) {
        super(imageurl, pos);
    }
}
