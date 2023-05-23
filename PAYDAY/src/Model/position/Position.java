package Model.position;
import Model.Player;

public abstract class Position {
    private int index;
    final private String image;
    private String dayname;

    /**
     * <b>Constuctor</b>Constructs a Position(just for the subclasses)
     * @param imageurl the image's URL of the position
     * @param pos positions number which must be from 1 to 30 and not be taken
     * from another position
     */
    public Position(String imageurl,int pos) {
        this.image=imageurl;
        setIndex(pos);
    }

    /**
     * <b>mutator</b>:Sets the number of the position
     * <b>precondition</b>:the pos parameter should be between 0 and 32
     * @param pos the index of the position
     */
    public void setIndex(int pos){
        if(pos>32 || pos<0) throw new IllegalArgumentException();
        this.index=pos;
    }

    /**
     * <b>accessor</b>:Gets the number of the position
     * @return index
     */
    public int getIndex(){return index;}


    /**
     * <b>accessor</b>:Gets the image URL of the position
     * @return image
     */
    public String getImage(){return image;}


    /**
     * <b>mutator</b>: Sets a day for the position
     * <b>precondtion</b>: name of the day should ba a name from the 7-days of the week
     * @param name the day name that the position gets
     */
    public void setDay(String name){
        if(name.equals("Monday") || name.equals("Tuesday") || name.equals("Wednesday") || name.equals("Thursday")
                || name.equals("Friday") || name.equals("Saturday") || name.equals("Sunday") || name.equals("Start")){
            this.dayname=name;
        }else{ throw new IllegalArgumentException();}
    }

    /**
     * <b>accessor</b>:Gets the day name of the position
     * @return dayname
     */
    public String getDay(){return dayname;}

    /**
     * <b>observer</b>: check if it is Sunday
     * @return if the dayname is Sunday
     */
    public boolean isSunday(){
        return this.dayname.equals("Sunday");
    }

    /**
     * <b>observer</b>: check if it is Thursday
     * @return if the dayname is Thursday
     */
    public boolean isThursday(){
        return this.dayname.equals("Thursday");
    }

    /**
     * An abstract method that is used for the action its position does
     * @param p the player that is in this position
     */
    public abstract void performAction(Player p);
}
