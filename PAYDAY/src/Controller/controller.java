package Controller;

import Model.Event.Crypto;
import Model.Event.Football;
import Model.board;
import Model.cards.*;
import Model.Player;
import Model.dice;
import Model.position.*;
import View.interfaceUI;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @version Alpha
 * @author csd4622
 */
public class controller{
    private Player A,B;
    private double scoreA;
    private double scoreB;
    private int maxmonths;
    private int lotteryA;
    private int lotteryB;
    private Crypto c;
    private Football f;
    private boolean GameHasStarted=false;
    private board board=new board();
    private interfaceUI gamewindow;
    private String msg;
    private boolean isLottery;
    private boolean isRadio;

    /**
     * <b>accessor</b> Get Player A
     */
    public Player GetA(){
        return A;
    }

    /**
     * <b>accessor</b> Get Player B
     */
    public Player GetB(){
        return B;
    }

    /**
     * <b>accessor</b> Get board
     */
    public board GetBoard(){
        return board;
    }

    /**
     * <b>constructor</b> Constructs a controller
     */
    public controller(){
        //initialization(such a create board);
        //pop up the name chooser for name players
        //create the two players and then the gamewindow
        //then initialize functions
        nameUI names;
        String n1="",n2="";
        for(int i=0;i<2;i++){
            if (i==0){
                names=new nameUI(true);
                n1=names.getname();
                this.A=new Player(names.getname(),true);
            }else {
                names=new nameUI(false);
                n2=names.getname();
                this.B=new Player(names.getname(),false);
            }
        }
        if(n1.equals(n2)){
            this.A=new Player("Player 1",true);
            this.B=new Player("Player 2",false);
        }
        A.setOpponent(B);
        B.setOpponent(A);
        gamewindow= new interfaceUI(this);
        gamewindow.changeposition(0,A.getCurrentpos(),true);
        gamewindow.changeposition(0,B.getCurrentpos(),false);
        gamewindow.setEnableMail(false);
        gamewindow.setEnableDeal(false);
        gamewindow.player1.addrollaction(new listenforroll());
        gamewindow.player2.addrollaction(new listenforroll());
        gamewindow.player1.addendturnaction(new listenendturn());
        gamewindow.player2.addendturnaction(new listenendturn());
        gamewindow.player1.addgetloanaction(new listengetloan());
        gamewindow.player2.addgetloanaction(new listengetloan());
        gamewindow.adddealbuttonaction(new listenfordeal());
        gamewindow.addmailbuttonaction(new listenformail());
        gamewindow.player1.addplayerdealaction(new listenforplayerdeal());
        gamewindow.player2.addplayerdealaction(new listenforplayerdeal());
        gamewindow.setVisible(true);
        whostarts();
        start_game();
    }

    /**
     * <b>mutator</b>:initializes the game
     */
    public void start_game(){/*initialize the game
    set gamehasstarted=true,here put the monthchooserUI so we can update maxmonths
    ,create the interfaceUI,whostarts()*/
        GameHasStarted=true;
        chooserUI month=new chooserUI(false, false);
        maxmonths=month.getChoice();
        gamewindow.refresh_infobox("---");
    };

    /**
     * <b>mutator</b> Adds the jackpot money to the player p and makes jackpot money = 0
     * @param p the player that wins the money
     */
    public void jackpot_winner(Player p){
        //update player p money to the money he wins
        p.setMoney(board.j.getMoney());
        board.j.reset();
    }

    /**
     * <b>accessor</b>Get the remaining months
     * @return the months
     */
    public int getMonths(){return this.maxmonths;/*return the months*/}

    /**
     *<b>mutator</b>Throws the dice of every player and whoever has the bigger number starts
     * <b>postcondition</b> One from two players should have hasTurn==true
     * <p>
     * Determines who will play first by throwing the dice for both of them
     */
    public void whostarts() {
        //determine who will start
        //update String msg
        //and the hasTurn of the player
        A.getMydice().roll();
        B.getMydice().roll();
        while(A.getMydice().getNumber()==B.getMydice().getNumber()){
            A.getMydice().roll();
            B.getMydice().roll();
        }
        if(A.getMydice().getNumber()>B.getMydice().getNumber()){
            A.setTurn(true);
            B.setTurn(false);
            gamewindow.player2.disablebuttons();
            gamewindow.player1.enablebuttons();
            gamewindow.refresh_infobox("---");
        }else{
            A.setTurn(false);
            B.setTurn(true);
            gamewindow.player1.disablebuttons();
            gamewindow.player2.enablebuttons();
            gamewindow.refresh_infobox("---");
        }
    }

    /**
     * <b>Observer</b>
     * <p>
     * <b>Postcondition:</b> return true if the game is over
     * <p>
     * @return true if both players have not reached the months they set in the beginning
     */
    public boolean isThisTheEnd(){
        if(maxmonths==0){
            return true;
        }
        return false;
        /*checks if this is the end*/
    }

    /**
     * Checks if it is Sunday or not
     * @param t the play who have just moved
     */
    void isSunday(Player t){
        if(board.tablo[t.getCurrentpos()].isSunday()){
            FootballUI match=new FootballUI();
            f=new Football();
            if (f.EventAction(match.getChoice())==0) {
                    match = new FootballUI(true);
                    t.setMoney(1000);
            } else if (f.EventAction(match.getChoice())==1) {
                    match = new FootballUI(true);
                    t.setMoney(1000);
            } else if (f.EventAction(match.getChoice())==2) {
                    match = new FootballUI(true);
                    t.setMoney(1000);
            } else if (f.EventAction(match.getChoice())==3){
                    match = new FootballUI(false);
                    while(!t.hasMoney(500)){
                        t.setLoan(1000);
                    }
                    t.setMoney(-500);
            }else{
                t.setMoney(0);
            }
            gamewindow.player1.refresh(true);
            gamewindow.player2.refresh(false);
        }
    }
    /**
     * Checks if it is Thursday or not
     * @param t the player who have just moved
     */
    void isThursday(Player t){
        if(board.tablo[t.getCurrentpos()].isThursday()){
            CryptoUI bitcoin=new CryptoUI();
            c=new Crypto();
            int n=c.EventAction(bitcoin.getChoice());
            if (n==1) {
                bitcoin = new CryptoUI(1);
                while(!t.hasMoney(300)){
                    t.setLoan(1000);
                }
                t.setMoney(-300);
            } else if (n==2) {
                bitcoin = new CryptoUI(2);
            } else if (n==0) {
                bitcoin = new CryptoUI(0);
                t.setMoney(600);
            }else{
                t.setMoney(0);
            }
            gamewindow.player1.refresh(true);
            gamewindow.player2.refresh(false);
        }
    }

    /**
     *Moves the player depending the number of the dice and changes  the players position
     * @param e the action event taken from the dicelistener
     */
    public void Move_player(ActionEvent e){
        if(e.getSource()==gamewindow.player1.getDicebt()){
            A.getMydice().roll();
            gamewindow.player1.refreshdice(A.getMydice().getNumber());
            if(A.getMydice().getRolled()) {
                gamewindow.player1.setdice(false);
                if(GameHasStarted){
                    if(A.getMydice().getNumber()==6 && board.j.getMoney()!=0){
                        jackpot_winner(A);
                        gamewindow.paintJackpot(0);
                        gamewindow.player1.refresh(true);
                        gamewindow.refresh_infobox(A.getName() +" won the Jackpot");
                    }
                    if(A.getCurrentpos()+A.getMydice().getNumber()>31){
                        gamewindow.changeposition(31,A.getCurrentpos(),true);
                        A.setCurrentpos(31);
                        positionActions(A,A.getCurrentpos());
                    }else{
                        gamewindow.changeposition(A.getCurrentpos()+A.getMydice().getNumber(),A.getCurrentpos(),true);
                        A.setCurrentpos(A.getMydice().getNumber());
                        isSunday(A);
                        isThursday(A);
                        positionActions(A,A.getCurrentpos());
                    }
                }
            }
        }else{
            B.getMydice().roll();
            gamewindow.player2.refreshdice(B.getMydice().getNumber());
            if(B.getMydice().getRolled()) {
                gamewindow.player2.setdice(false);
                if(GameHasStarted){
                    if(B.getMydice().getNumber()==6 && board.j.getMoney()!=0){
                        jackpot_winner(B);
                        gamewindow.paintJackpot(0);
                        gamewindow.player2.refresh(false);
                        gamewindow.refresh_infobox(B.getName() +" won the Jackpot");
                    }
                    if(B.getCurrentpos()+B.getMydice().getNumber()>31){
                        gamewindow.changeposition(31,B.getCurrentpos(),false);
                        B.setCurrentpos(31);
                        positionActions(B,B.getCurrentpos());
                    }else{
                        gamewindow.changeposition(B.getCurrentpos()+B.getMydice().getNumber(),B.getCurrentpos(),false);
                        B.setCurrentpos(B.getMydice().getNumber());
                        isSunday(B);
                        isThursday(B);
                        positionActions(B,B.getCurrentpos());
                    }
                }
            }
        }
    }

    /**
     * depending on the position the player is some actions in graphic are happening
     * @param p the player that is on the position pos
     * @param pos number of the position
     */
    public  void positionActions(Player p,int pos) {
        gamewindow.player1.setMyDeals(false);
        gamewindow.player2.setMyDeals(false);
        if (board.tablo[pos] instanceof CardPosition) {
            if (((CardPosition) board.tablo[pos]).isDeal()) {
                gamewindow.setEnableDeal(true);
                gamewindow.refresh_infobox("Draw a Deal Card");
            } else if (!((CardPosition) board.tablo[pos]).isDeal()) {
                gamewindow.setEnableMail(true);
                gamewindow.refresh_infobox("Draw a Mail Card");
            }
        }
        if (board.tablo[pos] instanceof sweepstakes) {
            if (p.equals(A)) {
                A.setWait(true);
                gamewindow.refresh_infobox("Throw the Dice again");
                if (A.getTurn()) {
                    gamewindow.player1.setdice(true);
                } else {
                    gamewindow.player2.setdice(true);
                }
            } else {
                B.setWait(true);
                gamewindow.refresh_infobox("Throw the Dice again");
                if (A.getTurn()) {
                    gamewindow.player1.setdice(true);
                } else {
                    gamewindow.player2.setdice(true);
                }
            }
        }
        if (board.tablo[pos] instanceof Sale) {
            if (p.equals(A)) {
                A.setWait(true);
                gamewindow.refresh_infobox("Throw the Dice again");
                if (A.getTurn()) {
                    gamewindow.player1.setdice(true);
                } else {
                    gamewindow.player2.setdice(true);
                }
            } else {
                B.setWait(true);
                gamewindow.refresh_infobox("Throw the Dice again");
                if (A.getTurn()) {
                    gamewindow.player1.setdice(true);
                } else {
                    gamewindow.player2.setdice(true);
                }
            }
        }
        if (board.tablo[pos] instanceof casino) {
            int money;
            if (p.getMydice().getNumber() % 2 == 1) {
                money = -500;
                while(!p.hasMoney(500)){
                    p.setLoan(1000);
                }
                p.setMoney(money);
                if (p.equals(A)) {
                    gamewindow.refresh_infobox(A.getName() + " gave 500 to the jackpot");
                } else {
                    gamewindow.refresh_infobox(B.getName() + " gave 500 to the jackpot");
                }
                board.j.addMoney(500);
                gamewindow.paintJackpot(board.j.getMoney());
            } else {
                money = 500;
                if (p.equals(A)) {
                    A.setMoney(money);
                    gamewindow.refresh_infobox(A.getName() + " won 500 from the casino");
                } else {
                    B.setMoney(money);
                    gamewindow.refresh_infobox(B.getName() + " won 500 from the casino");
                }
            }
            if (p.equals(A)) {
                gamewindow.player1.enableEnd(true);
            } else {
                gamewindow.player2.enableEnd(true);
            }

        }
        if (board.tablo[pos] instanceof radiocontest) {
            isRadio = true;
            if (p.equals(A) && !A.isFinish() && !B.isFinish()) {
                gamewindow.refresh_infobox("Throw the dice");
                A.getMydice().setRolled(false);
                B.getMydice().setRolled(false);
                gamewindow.player1.setdice(true);
            } else if (p.equals(B) && !A.isFinish() && !B.isFinish()) {
                gamewindow.refresh_infobox("Throw the dice");
                A.getMydice().setRolled(false);
                B.getMydice().setRolled(false);
                gamewindow.player2.setdice(true);
            }
        }
        if (board.tablo[pos] instanceof lottery) {
            isLottery = true;
            if (p.equals(A) && !A.isFinish() && !B.isFinish()) {
                gamewindow.player1.setdice(true);
                lotteryUI p1 = new lotteryUI(A.getName());
                lotteryA = p1.getChoice();
                lotteryUI p2 = new lotteryUI(B.getName());
                lotteryB = p2.getChoice();
            } else if(p.equals(B) && !A.isFinish() && !B.isFinish()){
                gamewindow.player2.setdice(true);
                lotteryUI p1 = new lotteryUI(A.getName());
                lotteryA = p1.getChoice();
                lotteryUI p2 = new lotteryUI(B.getName());
                lotteryB = p2.getChoice();
            }
        }
        if (board.tablo[pos] instanceof Buyer) {
            if (p.equals(A) && A.hasCards()) {
                gamewindow.refresh_infobox("You can sell a Deal Card");
                gamewindow.player1.setMyDeals(true);
                gamewindow.player1.enableEnd(true);
            } else if (p.equals(B) && B.hasCards()) {
                gamewindow.refresh_infobox("You can sell a Deal Card");
                gamewindow.player2.setMyDeals(true);
                gamewindow.player2.enableEnd(true);
            }
            if (p.equals(A) && !A.hasCards()) {
                gamewindow.refresh_infobox("Has no cards");
                gamewindow.player1.enableEnd(true);
            } else if (p.equals(B) && !B.hasCards()) {
                gamewindow.refresh_infobox("Has no cards");
                gamewindow.player2.enableEnd(true);
            }
        }
        if (board.tablo[pos] instanceof PayDayPosition) {
            if (p.equals(A)) {
                A.setMoney(3500);
                if (A.hasBills()) {
                    while (!A.hasMoney(A.getBills())) {
                        gamewindow.refresh_infobox("You should get a loan to pay your bills");
                        A.setLoan(1000);
                    }
                    A.setMoney(-A.getBills());
                    A.clearBills();
                }
            } else {
                B.setMoney(3500);
                if (B.hasBills()) {
                    while (!B.hasMoney(B.getBills())) {
                        gamewindow.refresh_infobox("You should get a loan to pay your bills");
                        B.setLoan(1000);
                    }
                    B.setMoney(-B.getBills());
                    B.clearBills();
                }
            }
            if (p.hasLoan()) {
                while (!p.hasMoney(0.1 * p.getloan())) {
                    gamewindow.refresh_infobox("You should get a loan to pay 10% of your loan");
                    p.setLoan(1000);
                }
                p.setLoan(-0.1 * p.getloan());
                gamewindow.player1.refresh(true);
                gamewindow.player2.refresh(false);
                PayDayUI pd = new PayDayUI();
                if (pd.getChoice() == 0) {
                    if(!p.hasMoney(p.getloan())) {
                        gamewindow.refresh_infobox("You dont have enough money to pay your loan");
                    }else{
                        p.setLoan(-p.getloan());
                    }
                } else if (pd.getChoice() == 1) {
                    double payloan;
                    loanUI l = new loanUI();
                    payloan = l.getLoanmoney();
                    if (l.getLoanmoney() >= p.getloan()) {
                        payloan = p.getloan();
                    }
                    if(!p.hasMoney(payloan)) {
                        gamewindow.refresh_infobox("You dont have enough money to pay your loan");
                    }else{
                        p.setLoan(-payloan);
                    }
                }
            }
            if (p.getMonths() != 0) {
                p.setFinish(true);
                p.setMonths(p.getMonths() - 1);
                p.clearDeals();
            }
            if (p.equals(A)) {
                gamewindow.player1.enableEnd(true);
                A.setFinish(true);
                gamewindow.refresh_infobox("---");
            } else {
                gamewindow.player2.enableEnd(true);
                B.setFinish(true);
                gamewindow.refresh_infobox("---");
            }

            if(A.getCurrentpos()==31 && B.getCurrentpos()==31 && A.isFinish() && B.isFinish()){
                maxmonths--;
                A.setFinish(false);
                B.setFinish(false);
                A.setCurrentpos(0);
                A.setMonths(A.getMonths()-1);
                gamewindow.changeposition(0, 31, true);
                B.setCurrentpos(0);
                B.setMonths(B.getMonths()-1);
                gamewindow.changeposition(0, 31, false);
                gamewindow.player1.setEnabled(true);
                gamewindow.refresh_infobox("---");
            }
            if(isThisTheEnd()){
                winUI w;
                A.clearDeals();
                B.clearDeals();
                scoreA=A.getMoney()-(A.getBills()+A.getloan());
                scoreB=B.getMoney()-(B.getBills()+B.getloan());
                if(scoreA>scoreB){
                    w=new winUI(A.getName(),false);
                }else if(scoreB>scoreA){
                    w=new winUI(B.getName(),false);
                }else {
                    w=new winUI("",true);
                }
                gamewindow.player1.disablebuttons();
                gamewindow.player2.disablebuttons();
            }
            gamewindow.player1.refresh(true);
            gamewindow.player2.refresh(false);
        }
    }

    /**
     * ActionListener of the dice button
     */
    class listenforroll implements ActionListener {
        dice dice1 = A.getMydice();
        dice dice2 = B.getMydice();

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() != null) {
                if (e.getSource() == gamewindow.player1.getDicebt() && A.isWait()) {
                    dice1.roll();
                    gamewindow.player1.refreshdice(dice1.getNumber());
                    if(!B.isFinish()) {
                        gamewindow.player1.setdice(false);
                    }
                    if (board.tablo[A.getCurrentpos()] instanceof sweepstakes) {
                        board.tablo[A.getCurrentpos()].performAction(A);
                        gamewindow.player1.refresh(true);
                        gamewindow.refresh_infobox(A.getName() + " Won " + A.getMydice().getNumber() * 1000 + " E");
                        A.setWait(false);
                        gamewindow.player1.enableEnd(true);
                    } else if (board.tablo[A.getCurrentpos()] instanceof Sale) {
                        DealCard C =  board.dealstack.pop();
                        board.tablo[A.getCurrentpos()].performAction(A);
                        A.AddDealCard(C,true);
                        while(!A.hasMoney(A.getMydice().getNumber() * 100)){
                            A.setLoan(1000);
                        }
                        A.setMoney(-A.getMydice().getNumber() * 100);
                        gamewindow.player1.refresh(true);
                        gamewindow.refresh_infobox(A.getName() + " bought a deal card for " +  A.getMydice().getNumber() * 100);
                        A.setWait(false);
                        gamewindow.player1.enableEnd(true);
                    }
                } else if (e.getSource() == gamewindow.player2.getDicebt() && B.isWait()) {
                    dice2.roll();
                    gamewindow.player2.refreshdice(dice2.getNumber());
                    if(!A.isFinish()) {
                        gamewindow.player2.setdice(false);
                    }
                    if (board.tablo[B.getCurrentpos()] instanceof sweepstakes) {
                        board.tablo[B.getCurrentpos()].performAction(B);
                        gamewindow.player2.refresh(false);
                        gamewindow.refresh_infobox(B.getName() + " Won " + B.getMydice().getNumber() * 1000 + " E");
                        B.setWait(false);
                        gamewindow.player2.enableEnd(true);
                    } else if (board.tablo[B.getCurrentpos()] instanceof Sale) {
                        DealCard C =  board.dealstack.pop();
                        board.tablo[B.getCurrentpos()].performAction(B);
                        B.AddDealCard(C,true);
                        while(!B.hasMoney(B.getMydice().getNumber() * 100)){
                            B.setLoan(1000);
                        }
                        B.setMoney(-B.getMydice().getNumber() * 100);
                        gamewindow.player2.refresh(false);
                        gamewindow.refresh_infobox(B.getName() + " bought a deal card for " +  B.getMydice().getNumber() * 100);
                        B.setWait(false);
                        gamewindow.player2.enableEnd(true);
                    }
                } else if (isLottery && !A.isFinish() && !B.isFinish()) {
                    if (e.getSource() == gamewindow.player1.getDicebt()) {
                        dice1.roll();
                        gamewindow.player1.refreshdice(dice1.getNumber());
                        gamewindow.player1.setdice(false);
                        if (dice1.getNumber() == lotteryA) {
                            A.setMoney(1000);
                            gamewindow.player1.refresh(true);
                            gamewindow.refresh_infobox(A.getName() + " won 1000 from Lottery");
                            isLottery = false;
                        } else {
                            gamewindow.player2.setdice(true);
                        }
                        if(!isLottery){
                            if(A.getTurn()){
                                gamewindow.player1.enableEnd(true);
                            }else{
                                gamewindow.player2.enableEnd(true);
                            }
                        }
                    } else {
                        dice2.roll();
                        gamewindow.player2.refreshdice(dice2.getNumber());
                        gamewindow.player2.setdice(false);
                        if (dice2.getNumber() == lotteryB) {
                            B.setMoney(1000);
                            gamewindow.player2.refresh(false);
                            gamewindow.refresh_infobox(B.getName() + " won 1000 from Lottery");
                            isLottery = false;
                        } else {
                            gamewindow.player1.setdice(true);
                        }
                        if(!isLottery){
                            if(A.getTurn()){
                                gamewindow.player1.enableEnd(true);
                            }else{
                                gamewindow.player2.enableEnd(true);
                            }
                        }
                    }
                } else if (isRadio && !A.isFinish() && !B.isFinish()) {
                    if (e.getSource() == gamewindow.player1.getDicebt()) {
                        dice1.roll();
                        gamewindow.player1.refreshdice(dice1.getNumber());
                        gamewindow.player1.setdice(false);
                        gamewindow.player2.setdice(true);

                    } else {
                        dice2.roll();
                        gamewindow.player2.refreshdice(dice2.getNumber());
                        gamewindow.player2.setdice(false);
                        gamewindow.player1.setdice(true);

                    }
                    if (A.getMydice().getRolled() && B.getMydice().getRolled()) {
                        if (A.getMydice().getNumber() == B.getMydice().getNumber()) {
                            gamewindow.player1.setdice(true);
                            gamewindow.player2.setdice(true);
                            A.getMydice().setRolled(false);
                            B.getMydice().setRolled(false);
                            gamewindow.refresh_infobox("Throw again the dices");
                        } else {
                            if (A.getMydice().getNumber() > B.getMydice().getNumber()) {
                                A.setMoney(1000);
                                gamewindow.player1.setdice(false);
                                gamewindow.player2.setdice(false);
                                gamewindow.player1.refresh(true);
                                gamewindow.refresh_infobox(A.getName() + " won 1000 from Radio");
                            } else {
                                B.setMoney(1000);
                                gamewindow.player1.setdice(false);
                                gamewindow.player2.setdice(false);
                                gamewindow.player2.refresh(false);
                                gamewindow.refresh_infobox(B.getName() + " won 1000 from Radio");
                            }
                            isRadio = false;
                        }
                        if(A.getTurn()){
                            gamewindow.player1.enableEnd(true);
                        }else{
                            gamewindow.player2.enableEnd(true);
                        }
                    }
                } else {
                    if(A.isFinish() && !B.isFinish()){
                        gamewindow.player2.enableEnd(true);
                    }else if(!A.isFinish() && B.isFinish()){
                        gamewindow.player1.enableEnd(true);
                    }
                    Move_player(e);
                }
            }
        }
    }
        /**
         * ActionListener of the "My Deal Cards" button of the player
         */
        class listenforplayerdeal implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==gamewindow.player1.getmydealsbt()) {
                    DealCard k = A.SellCard();
                    SellCardUI dummy = new SellCardUI(k.getImageName(), k.getSellprice());
                    A.SellCardAction();
                    gamewindow.player1.setMyDeals(false);
                    gamewindow.player1.enableEnd(true);
                } else {
                    DealCard k = B.SellCard();
                    SellCardUI dummy = new SellCardUI(k.getImageName(), k.getSellprice());
                    B.SellCardAction();
                    gamewindow.player2.setMyDeals(false);
                    gamewindow.player2.enableEnd(true);
                }
                gamewindow.player1.refresh(true);
                gamewindow.player2.refresh(false);
            }
        }

        /**
         * ActionListener of the Get loan
         */
        class listengetloan implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                loanUI daneio = new loanUI();
                if(daneio.getLoanmoney()!=null) {
                    if (A.getTurn()) {
                        A.setLoan(daneio.getLoanmoney());
                        gamewindow.player1.refresh(true);
                    } else {
                        B.setLoan(daneio.getLoanmoney());
                        gamewindow.player2.refresh(false);
                    }
                }else{
                    if (A.getTurn()) {
                        A.setLoan(0);
                        gamewindow.player1.refresh(true);
                    } else {
                        B.setLoan(0);
                        gamewindow.player2.refresh(false);
                    }
                }
            }
        }

        /**
         * ActionListener of the End turn
         */
        class listenendturn implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gamewindow.isEnabled()) {
                    if (A.getTurn()) {
                        A.setTurn(false);
                        B.setTurn(true);
                        gamewindow.player1.disablebuttons();
                        gamewindow.player2.enablebuttons();
                    } else {
                        A.setTurn(true);
                        B.setTurn(false);
                        gamewindow.player2.disablebuttons();
                        gamewindow.player1.enablebuttons();
                    }
                    if(A.isFinish()){
                        A.setTurn(false);
                        B.setTurn(true);
                        gamewindow.player2.enablebuttons();
                        gamewindow.player1.disablebuttons();
                        gamewindow.player1.enableEnd(true);
                    }else if(B.isFinish()){
                        B.setTurn(false);
                        A.setTurn(true);
                        gamewindow.player1.enablebuttons();
                        gamewindow.player2.disablebuttons();
                        gamewindow.player2.enableEnd(true);
                    }
                    gamewindow.setEnableMail(false);
                    gamewindow.setEnableDeal(false);
                    gamewindow.refresh_infobox("---");
                }
            }
        }

        /**
         * ActionListener of the Get a mail card
         */
        class listenformail implements ActionListener {
            MailCard temp;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (board.mailstack.isEmpty()) {
                    board.rejected.createstacks(board.dealstack, board.mailstack);
                }
                if (A.getTurn() && board.tablo[A.getCurrentpos()] instanceof CardPosition && !((CardPosition) board.tablo[A.getCurrentpos()]).isDeal()) {
                    if (!(((CardPosition) board.tablo[A.getCurrentpos()]).getOneCard())) {
                        temp = board.mailstack.pop();
                        MailCardUI showtemp = new MailCardUI(temp.getMessage(), temp.getTitle(), temp.getbuttonmsg(), temp.getImageName());
                        A.AddMailCard(temp);
                        if (temp instanceof Charity) {
                            while (!A.hasMoney(temp.getCost())) {
                                A.setLoan(1000);
                            }
                            board.j.addMoney(temp.getCost());
                            A.setMoney(-temp.getCost());
                            gamewindow.paintJackpot(board.j.getMoney());
                        }
                        if (temp instanceof MoveTo) {
                            for (int i = A.getCurrentpos() + 1; i < 32; i++) {
                                if (board.tablo[i] instanceof Buyer) {
                                    gamewindow.changeposition(i, A.getCurrentpos(), true);
                                    A.setCurrentpos(i - A.getCurrentpos());
                                    positionActions(A, A.getCurrentpos());
                                    gamewindow.player1.enableEnd(true);
                                    i = 33;
                                }
                            }
                        }
                    }
                    temp = board.mailstack.pop();
                    MailCardUI showtemp = new MailCardUI(temp.getMessage(), temp.getTitle(), temp.getbuttonmsg(), temp.getImageName());
                    A.AddMailCard(temp);
                    if (temp instanceof Charity) {
                        while (!A.hasMoney(temp.getCost())) {
                            A.setLoan(1000);
                        }
                        board.j.addMoney(temp.getCost());
                        A.setMoney(-temp.getCost());
                        gamewindow.paintJackpot(board.j.getMoney());
                    }
                    if (temp instanceof MoveTo) {
                        for (int i = A.getCurrentpos()+1; i < 32; i++) {
                            if (board.tablo[i] instanceof Buyer) {
                                gamewindow.changeposition(i, A.getCurrentpos(), true);
                                A.setCurrentpos(i - A.getCurrentpos());
                                positionActions(A, A.getCurrentpos());
                                gamewindow.player1.enableEnd(true);
                                i = 33;
                            }
                        }
                    }
                    if(!A.isFinish()) {
                        gamewindow.player1.enableEnd(true);
                    }
                    board.rejected.push(temp);
                } else if (B.getTurn() && board.tablo[B.getCurrentpos()] instanceof CardPosition && !((CardPosition) board.tablo[B.getCurrentpos()]).isDeal()) {
                    if (!(((CardPosition) board.tablo[B.getCurrentpos()]).getOneCard())) {
                        temp = board.mailstack.pop();
                        MailCardUI showtemp = new MailCardUI(temp.getMessage(), temp.getTitle(), temp.getbuttonmsg(), temp.getImageName());
                        A.AddMailCard(temp);
                        if (temp instanceof Charity) {
                            while (!B.hasMoney(temp.getCost())) {
                                B.setLoan(1000);
                            }
                            board.j.addMoney(temp.getCost());
                            B.setMoney(-temp.getCost());
                            gamewindow.paintJackpot(board.j.getMoney());
                        }
                        if (temp instanceof MoveTo) {
                            for (int i = B.getCurrentpos() + 1; i < 32; i++) {
                                if (board.tablo[i] instanceof Buyer) {
                                    gamewindow.changeposition(i, B.getCurrentpos(), false);
                                    A.setCurrentpos(i - B.getCurrentpos());
                                    positionActions(B, B.getCurrentpos());
                                    gamewindow.player2.enableEnd(false);
                                    i = 33;
                                }
                            }
                        }
                    }
                temp = board.mailstack.pop();
                MailCardUI showtemp = new MailCardUI(temp.getMessage(), temp.getTitle(), temp.getbuttonmsg(), temp.getImageName());
                B.AddMailCard(temp);
                if (temp instanceof Charity) {
                    while (!B.hasMoney(temp.getCost())) {
                        B.setLoan(1000);
                    }
                    board.j.addMoney(temp.getCost());
                    B.setMoney(-temp.getCost());
                    gamewindow.paintJackpot(board.j.getMoney());
                }
                if (temp instanceof MoveTo) {
                    for (int i = B.getCurrentpos()+1; i < 32; i++) {
                        if (board.tablo[i] instanceof Buyer) {
                            gamewindow.changeposition(i, B.getCurrentpos(), false);
                            B.setCurrentpos(i - B.getCurrentpos());
                            positionActions(B, B.getCurrentpos());
                            gamewindow.player2.enableEnd(true);
                            i = 33;
                        }
                    }
                }
                if(!B.isFinish()) {
                    gamewindow.player2.enableEnd(true);
                }
                board.rejected.push(temp);
            }
                if(temp instanceof TakefromNeighbor || temp instanceof PaytheNeighbor){
                    A=B.getOpponent();
                    B=A.getOpponent();
                }
                gamewindow.player1.refresh(true);
                gamewindow.player2.refresh(false);
                gamewindow.setEnableMail(false);
                gamewindow.refresh_infobox("Card Taken");
            }
        }

        /**
         * ActionListener of the Get a deal card
         */
        class listenfordeal implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                DealCard temp;
                if(board.dealstack.isEmpty()) {
                    board.rejected.createstacks(board.dealstack,board.mailstack);
                }
                temp = board.dealstack.pop();
                DealCardUI showtemp = new DealCardUI(temp.getImageName(), temp.getMessage(), temp.getCost(), temp.getSellprice());
                if (A.getTurn() && board.tablo[A.getCurrentpos()] instanceof CardPosition && ((CardPosition) board.tablo[A.getCurrentpos()]).isDeal()) {
                    showtemp.getchoice();
                    if (showtemp.getchoice()) {
                        if (A.getTurn()) {
                            temp.PerformAction(A);
                            gamewindow.player1.refresh(true);
                        }
                    } else {
                        board.rejected.push(temp);
                    }
                    if(!A.isFinish()) {
                        gamewindow.player1.enableEnd(true);
                    }
                } else if (B.getTurn() && board.tablo[B.getCurrentpos()] instanceof CardPosition && ((CardPosition) board.tablo[B.getCurrentpos()]).isDeal()) {
                    showtemp.getchoice();
                    if (showtemp.getchoice()) {
                        temp.PerformAction(B);
                        gamewindow.player2.refresh(false);
                    } else {
                        board.rejected.push(temp);
                    }
                    if(!B.isFinish()) {
                        gamewindow.player2.enableEnd(true);
                    }
                }
                gamewindow.setEnableDeal(false);
                gamewindow.refresh_infobox("Card Taken");
            }
        }

}
