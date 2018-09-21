import java.util.Random;
/**
    Player class designed to hold each player's face up and face down piles
*/
public class Player
{
   private MultiDS<Card> faceDown = new MultiDS<Card>(52);
   private MultiDS<Card> faceUp = new MultiDS<Card>(52);
   /**
   blank constructor
   */
   public Player()
   {
   
   }
   /**
       Adds a card to the player's face down pile
       @param added Card to be added
   */
   
   public void giveCard(Card added)
   {
      faceDown.addItem(added);
   }
   
   /**
       Removes the top card from the faceDown pile
       @return The top card from the faceDown pile
   */
   public Card getCard()
   {
      return faceDown.removeItem();
   }
   
   /**
       Adds a card to the faceUp pile
       @param up Card to be added to faceUp pile
   */
   public void toFaceUp(Card up)
   {
      faceUp.addItem(up);
   }
   /**
       Checks to see if the player is out of cards
       @return Returns true if player has no more cards, false otherwise
   */
   public boolean outOfCards()
   {
      if(faceDown.empty() && faceUp.empty())
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   /**
      Checks to see if the player's faceDown pile is empty
      @return Returns true if empty, false otherwise
   */
   public boolean emptyDown()
   {
      return faceDown.empty();
   }
   
   /**
      Moves all of the cards from the player's faceUp pile to the player's faceDown pile
   */
   public void transferCards()
   {
      faceDown.addItem(faceUp.removeItem());
      faceDown.shuffle();
   }
   /**
       Gives the number of cards in the faceUp pile
       @return The number of cards in the faceUp pile
   */
   public int cardsInUp()
   {
      return faceUp.size();
   }
   /**
       Determines if the player yells Snap
       @param matching the boolean that determines the probability of the player yelling snap
       @return returns true if the player yells snap, false otherwise
   */
   public boolean yellSnap(boolean matching)
   {
      Random yelling = new Random();
      int prob = yelling.nextInt(100);
      if(matching && prob < 100 && prob > 49)
      {
         return true;
      }
      else if(!matching && prob == 1)
      {
         return true;
      }
      else
      {
         return false;
      }
       
   }
   
   /**
       Removes a card from the faceUp pile
       @return returns the card being removed from the pile
   */
   public Card loseCard()
   {
      return faceUp.removeItem();
   }
   /**
       Shuffles the player's faceUp pile
   */
   public void shuffleUp()
   {
      faceUp.shuffle();
   }
   
   /**
       Gets the total number of cards the player has
       @return total cards
   */
   
   public int numOfCards()
   {
      return faceUp.size() + faceDown.size();
   }
   
}