package Model.stacks;

import Model.cards.Cards;
import Model.cards.DealCard;
import Model.cards.MailCard;

import java.util.ArrayList;

public class StackOfCards {
    private ArrayList<Cards> rejectedcards;

    /**
     * <b>constructor</b>:constructs a StackOfCards
     * /initializes the rejectedcards ArrayList
     */
    public StackOfCards(){
        this.rejectedcards=new ArrayList<Cards>();
    }

    /**
     * <b>observer</b>: Check if the stack is empty or not
     * @return if the arraylist is empty or not
     */
    public boolean isEmpty(){
        return rejectedcards.isEmpty();
    }

    /**
     * <b>mutator</b>:Takes the cards from the stack and creates two other stacks
     * <b>postcondition</b>:The rejectedcards array list is empty in the end
     * @param deals the stack of deal cards
     * @param mails the stack of mail cards
     */
    public void createstacks(StackOfDealCards deals,StackOfMailCards mails){
        while (!this.isEmpty()){
            Cards temp=this.rejectedcards.get(0);
            if(temp instanceof MailCard){
                mails.push((MailCard) temp);
            }else{
                deals.push((DealCard)temp);
            }
            rejectedcards.remove(temp);
        }
        deals.Shuffle();
        mails.Shuffle();
    }

    /**
     * <b>mutator</b>: Adds a card to the stack
     * @param c the card that will be added
     */
    public void push(Cards c){
        rejectedcards.add(c);
    }
}
