package Model.stacks;

import Model.cards.DealCard;
import java.util.ArrayList;
import java.util.Collections;

public class StackOfDealCards {
    private ArrayList<DealCard> dealCards;

    /**
     * <b>constructor</b>:constructs a StackOfDealCards
     * /initializes the dealCards ArrayList
     */
    public StackOfDealCards(){
        dealCards=new ArrayList<DealCard>();
    }

    /**
     * <b>mutator</b>: Adds a card to the stack
     * @param d the card that will be added
     */
    public void push(DealCard d){
        dealCards.add(d);
    }

    /**
     * <b>observer</b>: Check if the stack is empty or not
     * @return if the arraylist is empty or not
     */
    public boolean isEmpty(){
        return dealCards.isEmpty();
    }

    /**
     * <b>mutator</b>: Get a card from the stack
     * @return the removed card
     */
    public DealCard pop(){
        DealCard temp=dealCards.get(0);
        dealCards.remove(0);
        return temp;
    }

    /**
     * <b>mutator</b>:Shuffles the cards of the stack
     * <b>precondition</b> The stack should not be empty
     */
    //check if empty or not then shuffle
    public void Shuffle(){
        Collections.shuffle(this.dealCards);
    }

}
