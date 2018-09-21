/**
    The functional class of the first assignment. This runs the actual snap game
*/


public class Snap
{
   public static void main(String[] args)
   {
      if(args.length != 2)
      {
         System.out.println("You must put in the number of rounds and the number of players!");
         return;
      }
      int numOfRounds = Integer.parseInt(args[0]);
      int numOfPlayers = Integer.parseInt(args[1]);
      MultiDS<Card> deck = new MultiDS<Card>(52);
      MultiDS<Card> snapPile = new MultiDS<Card>(52);
      int round = 1;
      Player[] players = new Player[numOfPlayers];
      
      boolean gameDone = false;
      for(int pls = 0; pls < numOfPlayers; pls++)
      {
         players[pls] = new Player();
      }
      
      for(Card.Suits setSuits : Card.Suits.values())
      {
         for(Card.Ranks setRanks : Card.Ranks.values())
         {
            deck.addItem(new Card(setSuits, setRanks));
         }
      }
      deck.shuffle();
      deck.toString();
      if(52%numOfPlayers == 0)
      {
         while(!deck.empty())
         {
            for(int player = 0; player < numOfPlayers; player++)
            {
               players[player].giveCard(deck.removeItem());
            }
         }
      }
      else
      {
         while(deck.size() > (52 % numOfPlayers))
         {
            for(int player = 0; player < numOfPlayers; player++)
            {
               players[player].giveCard(deck.removeItem());
            }
         }
         while(!deck.empty())
         {
            snapPile.addItem(deck.removeItem());
         }
      }
      
      while(!gameDone)
      {
         boolean match = false;
         Card[] topCard = new Card[numOfPlayers];
         int[] playerShouted = new int[numOfPlayers];
         int numShouted = 0;
         for(int pDecks = 0; pDecks < numOfPlayers; pDecks++)
         {
            topCard[pDecks] = players[pDecks].getCard();
            players[pDecks].toFaceUp(topCard[pDecks]); 
         }
         for(int outer = 0; outer < (numOfPlayers - 1); outer++)
         {
            for(int inner = outer + 1; inner < numOfPlayers; inner++)
            {
               if(topCard[outer].compareTo(topCard[inner]) == 0)
               {
                  match = true;
               }
            }
         }
         for(int numP = 0; numP < numOfPlayers; numP++)
         {
            if(players[numP].yellSnap(match))
            {
               playerShouted[numShouted] = numP;
               numShouted++;
            }
         }
         System.out.printf("Round %d: ", round);
         if(match && numShouted == 1)
         {
            System.out.printf("Match! Player %d shouts alone.\n", playerShouted[0]);
            for(int outer = 0; outer < (numOfPlayers - 1); outer++)
            {
               for(int inner = outer + 1; inner < numOfPlayers; inner++)
               {
                  if(topCard[outer].compareTo(topCard[inner]) == 0)
                  {
                     System.out.printf("\t%s of Player %d (%d cards) matches %s of Player %d (%d cards)\n", topCard[outer].toString(), 
                          outer, players[outer].cardsInUp(), topCard[inner].toString(), inner, players[inner].cardsInUp());
                          
                     players[outer].shuffleUp();
                     players[inner].shuffleUp();
                     while(players[outer].cardsInUp() > 0)
                     {
                        players[playerShouted[0]].giveCard(players[outer].loseCard());
                     }
                     while(players[inner].cardsInUp() > 0)
                     {
                        players[playerShouted[0]].giveCard(players[inner].loseCard());
                     }
                     while(!snapPile.empty())
                     {
                        snapPile.shuffle();
                        players[playerShouted[0]].giveCard(snapPile.removeItem());
                     }
                          
                  }
               }
            }
         }
         else if(match)
         {
            System.out.printf("Match but no winner! %d players shouted.\n", numShouted);
         }
         else
         {
            System.out.println("No match!");
            if(numShouted > 0)
            {
               for(int numb = 0; numb < numShouted; numb++)
               {
                  System.out.printf("\tPlayer %d shouted incorrectly\n", playerShouted[numb]);
                  System.out.printf("\t%d cards moved to snap pile\n", players[playerShouted[numb]].cardsInUp());
                  while(players[playerShouted[numb]].cardsInUp() > 0)
                  {
                     snapPile.addItem(players[playerShouted[numb]].loseCard());
                  }
               }
            }
         }
         for(int count = 0; count < numOfPlayers; count++)
         {
            if(players[count].outOfCards())
            {
               gameDone = true;
               System.out.printf("Player %d ran out of cards!\n", count);
            }
            if(players[count].emptyDown())
            {
               players[count].transferCards();
            }
             
         }
         
         if(round == numOfRounds)
         {
            gameDone = true;
            
         }
         else
         {
            round++;
         }
      }
      boolean tie = false;
      int winningPlayer = 0;
      int mostCards = 0;
      System.out.printf("After %d rounds:\n", numOfRounds);
      for(int play = 0; play < numOfPlayers; play++)
      {
         System.out.printf("\tPlayer %d ends with %d cards\n", play, players[play].numOfCards());
         if(players[play].numOfCards() > mostCards)
         {
            winningPlayer = play;
         }
         else if(players[play].numOfCards() == mostCards)
         {
            tie = true;
         }
      }
      System.out.printf("\tSnap Pile ends with %d cards\n", snapPile.size());
      if(tie)
      {
         System.out.println("There was a tie!");
      }
      else
      {
         System.out.printf("Player %d wins!", winningPlayer);
      }
   }
   
}