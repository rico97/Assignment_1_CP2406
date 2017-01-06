import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Rico on 1/5/2017.
 */
public class TableGame {
    private ArrayList<Card> usedCard;
    private Deck deckCard;
    private ArrayList<Player> gameplayers;
    private String categoryMode;
    private String lastPlayerTurn;

    public String getCategoryMode() {
        return categoryMode;
    }

    public String getGameMode()
    {
        String game = "";
        if(categoryMode.equals("H"))
        {
            game = "It is a game of hardness";
        }
        else if(categoryMode.equals("S"))
        {
            game = "It is a game of specific gravity";
        }
        else if(categoryMode.equals("C"))
        {
            game = "It is a game of cleavage";
        }
        else if(categoryMode.equals("A"))
        {
            game = "It is a game of crustal abundance";
        }
        else if(categoryMode.equals("E")) {
            game = "It is a game of economic value";
        }
        return game;
    }

    public void setCategoryMode(String categoryMode) {
        this.categoryMode = categoryMode;
    }

    TableGame(int numberOfPlayers, Deck deckcard)
    {
        categoryMode = "";
        usedCard = new ArrayList<Card>();
        gameplayers = new ArrayList<Player>();
        deckCard = deckcard;
        lastPlayerTurn = "";
        for(int x = 0; x < numberOfPlayers; x++)
        {
            String nameOfPlayer = JOptionPane.showInputDialog(null,"Enter player name", "Name",
                    JOptionPane.INFORMATION_MESSAGE);
            gameplayers.add(new Player(nameOfPlayer));
        }
        for(int x = 0; x<8; x++) {
            for (Player player : gameplayers) {
                player.drawCard(deckcard.cardDrawn());
            }
        }
    }

    public void addBackCard()
    {
        Card cardLastPlayed = getLastCard();
        usedCard.remove(cardLastPlayed);
        deckCard = new Deck(usedCard);
        usedCard.clear();
        usedCard.add(cardLastPlayed);

    }
    public ArrayList<Player> getGameplayers() {
        return gameplayers;
    }
    public Card getLastCard() {
        return usedCard.get(usedCard.size()-1);
    }

    public boolean anyCardPlayed()
    {
        boolean played = false;
        if(usedCard.size()>0)
        {played = true;}
        return played;
    }

    public Deck getDeckCard() {
        return deckCard;
    }

    public Player getPlayer(int x)
    {
        return gameplayers.get(x);
    }

    public boolean playCard(Card card, Player play)
    {
        boolean isHigher = false;
        int comparison = 0;
        if(usedCard.size()==0 || this.playerGetTurnAgain(play))
        {
            if(card  instanceof SupertrumpCard)
            {
                categoryMode = ((SupertrumpCard) card).useEffect();
            }
            isHigher = true;
        }
        else {
            if(card instanceof NormalCard) {
                if (getLastCard() instanceof NormalCard) {
                    if (categoryMode.equals("H")) {
                        Float current = new Float(((NormalCard) card).getHardness());
                        Float previous = new Float(((NormalCard) getLastCard()).getHardness());
                        comparison = current.compareTo(previous);
                    } else if (categoryMode.equals("S")) {
                        Float current = new Float(((NormalCard) card).getSpecificGravity());
                        Float previous = new Float(((NormalCard) getLastCard()).getSpecificGravity());
                        comparison = current.compareTo(previous);
                    } else if (categoryMode.equals("C")) {
                        Float current = new Float(((NormalCard) card).getCleavageValue());
                        Float previous = new Float(((NormalCard) getLastCard()).getCleavageValue());
                        comparison = current.compareTo(previous);
                    } else if (categoryMode.equals("A")) {
                        Float current = new Float(((NormalCard) card).getCrustalAbundanceValue());
                        Float previous = new Float(((NormalCard) getLastCard()).getCrustalAbundanceValue());
                        comparison = current.compareTo(previous);
                    } else if (categoryMode.equals("E")) {
                        Float current = new Float(((NormalCard) card).getEcoValueValue());
                        Float previous = new Float(((NormalCard) getLastCard()).getEcoValueValue());
                        comparison = current.compareTo(previous);
                    }
                    if (comparison > 0) {
                        isHigher = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Invalid selection, your choice don't have enough value to fight the last card");
                    }
                } else {
                    isHigher = true;
                }

            }
            else
            {
                setCategoryMode(((SupertrumpCard) card).useEffect());
                isHigher = true;
            }
        }
        return isHigher;
    }

    public void putCard(Card card)
    {
        usedCard.add(card);
    }

    public ArrayList<Card> getUsedCard() {
        return usedCard;
    }

    public String getLastPlayerTurn() {
        return lastPlayerTurn;
    }

    public void setLastPlayerTurn(String lastPlayerTurn) {
        this.lastPlayerTurn = lastPlayerTurn;
    }

    public boolean playerGetTurnAgain(Player gamePlayer)
    {
        boolean statement = false;
        if(getLastPlayerTurn().equals(gamePlayer.getName()))
        {
            statement = true;
        }
        return statement;
    }

}
