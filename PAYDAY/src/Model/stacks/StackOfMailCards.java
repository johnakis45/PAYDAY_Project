package Model.stacks;

import Model.cards.DealCard;
import Model.cards.MailCard;
import java.util.ArrayList;
import java.util.Collections;

public class StackOfMailCards {
    private ArrayList<MailCard> mailCards;

    /**
     * <b>constructor</b>:constructs a StackOfMailCards
     * /initializes the mailCards ArrayList
     */
    public StackOfMailCards(){
        mailCards=new ArrayList<MailCard>();
    }
    /**
     * <b>mutator</b>: Adds a card to the stack
     * @param m the card that will be added
     */
    public void push(MailCard m){
        mailCards.add(m);
    }

    /**
     * <b>observer</b>: Check if the stack is empty or not
     * @return if the arraylist is empty or not
     */
    public boolean isEmpty(){
        return mailCards.isEmpty();
    }

    /**
     * <b>mutator</b>: Get a card from the stack
     * @return the removed card
     */
    public MailCard pop(){
        MailCard temp=mailCards.get(0);
        mailCards.remove(0);
        return temp;
    }

    /**
     * <b>mutator</b>:Shuffles the cards of the stack
     * <b>precondition</b> The stack should not be empty
     */
    //check if empty or not
    public void Shuffle(){
        Collections.shuffle(this.mailCards);
    }

}
