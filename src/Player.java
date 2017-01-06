import javafx.scene.control.Tab;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Rico on 1/4/2017.
 */
public class Player {
    private ArrayList<Card> hand;
    private String name;
    Player(String nm)
    {
        hand = new ArrayList<Card>();
        name = nm;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Card getCard(int x)
    {
        return hand.get(x);
    }

    public void drawCard(Card card)
    {
        hand.add(card);
    }

    public void getTurn(TableGame table)
    {
        int cardNum;
        boolean next = false;
        String lastCardDescription;
        if(table.anyCardPlayed() && !(table.playerGetTurnAgain(this))) {
            if(table.getLastCard() instanceof NormalCard){
            lastCardDescription =
                    "\nLast card = Name: " + table.getLastCard().getName() + " " +
                            "Hardness: " + ((NormalCard) table.getLastCard()).getHardness() + " " +
                            "Specific Gravity: " + ((NormalCard) table.getLastCard()).getSpecificGravity() + " " +
                            "Cleavage: " + ((NormalCard) table.getLastCard()).getCleavage() + " " +
                            "Crystal Abundance: " + ((NormalCard) table.getLastCard()).getCrustalAbundance() + " " +
                            "Economic Value: " + ((NormalCard) table.getLastCard()).getEcoValue() + "\n";}
            else{
                lastCardDescription = "\nPrevious player play the " +((SupertrumpCard) table.getLastCard()).getName()+ " card\n";
            }
        }
        else if(table.anyCardPlayed() && table.playerGetTurnAgain(this)) {
            JOptionPane.showMessageDialog(null,"You are granted to choose the trump mode again");
            lastCardDescription = "You may pick again as you made them all pass";
            startGame(table);
        }
        else
        {
            lastCardDescription = "No cards have been played";
            startGame(table);
        }
        while (!next) {
            String choice = JOptionPane.showInputDialog(null, table.getGameMode() + "\n" + lastCardDescription +"\n"
                            +showAllCard(), getName() + ", enter the card number" +
                            " you want to play or simply enter PASS to pass",
                    JOptionPane.QUESTION_MESSAGE);
            if (choice.toUpperCase().equals("PASS")) {
                drawCard(table.getDeckCard().cardDrawn());
                next = true;
            }
            else {
                try {
                    cardNum = Integer.parseInt(choice);
                    Card cardplayed = getCard(cardNum);
                    boolean gameContinue = table.playCard(cardplayed,this);
                    if(table.getCategoryMode().equals("NEW"))
                    {   table.putCard(cardplayed);
                        hand.remove((cardNum));
                        startGame(table);
                        table.setLastPlayerTurn(this.getName());
                        next = true;
                    }
                    else if(table.getCategoryMode().equals("S"))
                    {
                        if(checkWinningCard())
                        {
                            for(Card cardInHand : hand)
                            {
                                table.putCard(cardInHand);
                                hand.remove(cardInHand);
                                table.setLastPlayerTurn(this.getName());
                            }
                            next = true;
                        }
                        if(gameContinue) {
                            table.putCard(cardplayed);
                            hand.remove(cardNum);
                            table.setLastPlayerTurn(this.getName());
                            next = true;
                        }
                    }
                    else {
                        if (gameContinue) {
                            table.putCard(cardplayed);
                            hand.remove(cardNum);
                            table.setLastPlayerTurn(this.getName());
                            next = true;
                        }
                    }
                } catch (Throwable e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        if(hand.size() == 0)
        {
            leaveGame(table);
        }

    }

    public String showAllCard()
    {
        String allHand = "";
        int cardNo = 0;
        for(Card cards : hand)
        {

            String cardDescription = "";
            if(cards instanceof NormalCard)
            {
                cardDescription = "No: "+ cardNo + " "+
                        "Name: " + cards.getName() + " " +
                        "Hardness: " + ((NormalCard) cards).getHardness() + " " +
                        "Specific Gravity: " + ((NormalCard) cards).getSpecificGravity() + " " +
                        "Cleavage: " + ((NormalCard) cards).getCleavage() + " " +
                        "Crystal Abundance: " + ((NormalCard) cards).getCrustalAbundance() + " " +
                        "Economic Value: " + ((NormalCard) cards).getEcoValue()+"\n";
            }
            else
            {
                cardDescription = "No: "+ cardNo+ " "+ "Name: " + cards.getName()+ " Description: "+
                        ((SupertrumpCard) cards).cardDescription()+"\n";
            }
            cardNo+=1;
            allHand += cardDescription;
        }
        return allHand;
    }

    public void startGame(TableGame gameStarter)
    {
        String mode = JOptionPane.showInputDialog(null,showAllCard()+"\n H = Hardness, S = Specific Gravity," +
                        " C = Cleavage, A = Crystal Abundance, E= Economic Value",getName()+ ", " +
                        "enter the mode you want to play",
                JOptionPane.QUESTION_MESSAGE);
        while(!(mode.equals("H") ||mode.equals("S")||mode.equals("C")||mode.equals("A")||mode.equals("E")||
        mode.equals("h") ||mode.equals("s")||mode.equals("c")||mode.equals("a")||mode.equals("e")) ) {
            JOptionPane.showMessageDialog(null,"Invalid mode");
            mode = JOptionPane.showInputDialog(null, showAllCard() + "\n H = Hardness, S = Specific Gravity," +
                            " C = Cleavage, A = Crystal Abundance, E= Economic Value", getName() + ", " +
                            "enter the mode you want to play",
                    JOptionPane.QUESTION_MESSAGE);
        }
        gameStarter.setCategoryMode(mode.toUpperCase());
    }

    public boolean checkWinningCard()
    {
        boolean winningCard = false;
        for(Card cards: hand)
        {
            if(cards.getName().equals("Magnetite"))
            {
                winningCard = true;
            }
        }
        return winningCard;
    }

    public void leaveGame(TableGame table)
    {
        table.getGameplayers().remove(this);
        JOptionPane.showMessageDialog(null,"Player " + this.getName() + " have left the game");
    }

}
