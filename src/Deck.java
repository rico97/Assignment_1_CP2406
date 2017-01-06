import java.util.Random;
import java.util.ArrayList;


/**
 * Created by Rico on 1/4/2017.
 */
public class Deck {
    private ArrayList<Card> deckContent;

    Deck(ArrayList<Card> cardlist)
    {
        deckContent = cardlist;
    }

    public Card cardDrawn()
    {
        int x = new Random().nextInt(deckContent.size());
        Card retrieve = deckContent.get(x);
        deckContent.remove(x);
        return retrieve;
    }

    public Card getCard(int x)
    {
        Card get = deckContent.get(x);
        return get;
    }

    public ArrayList<Card> getDeckContent() {
        return deckContent;
    }
}
