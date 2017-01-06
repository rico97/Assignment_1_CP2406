import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;

/**
 * Created by Rico on 1/4/2017.
 */
public class MineralSupertrumps {

    public static void main(String[] args)
    {
        Object[] options = {3,4,5};
        int numberOfPlayer = JOptionPane.showOptionDialog(null,"Enter the number of players", "Players",JOptionPane.YES_NO_CANCEL_OPTION
                ,JOptionPane.QUESTION_MESSAGE,null,options,options[0]) + 3;
        ArrayList<Card> cardList = new ArrayList<Card>();
        String[] array;
        String string = "";
        Path file =
                Paths.get("C:\\Users\\Rico\\IdeaProjects\\Assignment 1 CP2406\\src\\card.txt");
        try
        {
            InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            reader.readLine();
            while ((string = reader.readLine()) != null){
                array = string.split(",");
                cardList.add(new NormalCard(array[0],Float.valueOf(array[1]),Float.valueOf(array[2]),array[3],array[4],array[5]));
            }
            cardList.add(new SupertrumpCard("The Mineralogist"));
            cardList.add(new SupertrumpCard("The Geologist"));
            cardList.add(new SupertrumpCard("The Geophysicist"));
            cardList.add(new SupertrumpCard("The Petrologist"));
            cardList.add(new SupertrumpCard("The Miner"));
            cardList.add(new SupertrumpCard("The Gemmologist"));
        }
        catch(Exception e)
        {
            System.out.println("Message: " + e.getMessage());
        }
        Deck gameDeck = new Deck(cardList);
        TableGame gameTable = new TableGame(numberOfPlayer,gameDeck);
        int counter = 0;
        while (gameTable.getGameplayers().size()>1)
        {
            int playerno = counter%gameTable.getGameplayers().size();
            if(gameTable.getDeckCard().getDeckContent().size()==0)
            {
                gameTable.addBackCard();
            }
            else{
                    gameTable.getGameplayers().get(playerno).getTurn(gameTable);
            }
            counter+=1;
        }
    }
}
