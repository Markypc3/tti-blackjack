package BlackJack;
/*  marco Pariente-Cohen
    Pessy Weinstock
    Hadassah Bergstein
    Ettie Schreiber
    Chaya Berliner
    Rachel Leah Wachsman
    Goldy Friedman
    
*/

import java.util.*;
public class Blackjack{
    public static void main(String[] args){
        
        startGame();
        
        
    } 
    public static void startGame() {
        //creating a deck of cards
        boolean[] deck = new boolean[52];
   
        
        //finding out how many players are playing
        Scanner input = new Scanner(System.in);
        System.out.print("How many players will be playing? ");
        int amountOfPlayers = input.nextInt();
        
        //array of players names
        String[] playersNames = new String[amountOfPlayers + 1];
        
        System.out.print("What are the names of the players? ");
        
        //populating the array with the names of the players
        for(int i = 0; i < playersNames.length - 1; i++)
            playersNames[i] = input.next();
        playersNames[amountOfPlayers] = "Dealer";
            
        int[][] players = new int [amountOfPlayers + 1][12];
        
        //populating the players array with the value -1
        for (int i = 0; i < players.length; i++)
            populateDeck(players[i]);
        
        
        //dealing cards to all the players    
        for (int i = 0; i < players.length; i++) {
            dealCards(deck, players[i]);
            
        }
        
         for (int i = 0; i < players.length - 1; i++) {
             System.out.println("It is " + playersNames[i] + "'s turn.");
             playersTurn(i, players ,deck, playersNames);
         }
              
 
        dealersTurn(players, deck, playersNames);
        input.close();
    }
    
    //printing the cards of each player
    public static void printCards(int[][] playersHands, int currentPlayer, String [] playersNames) {
        
//        char s = 6; //suits will display in command line but not eclipse
//        char d = 4;
//        char c = 5;
//        char h = 3;
    	String s = "Spades", d = "Diamonds", c = "Clubs", h = "Hearts";
        int cardNumber = 0;
        
        for (int player = 0; player < playersNames.length; player++){
            System.out.println(playersNames[player] + "'s cards are: ");
                for (int card = 0; card < playersHands[0].length && playersHands[player][card]!= -1; card++) {
                	/*if (array[j][i] == -1)
                		break; */
                	if (playersHands[player][card] / 13 == 0) {
                        cardNumber = (playersHands[player][card] % 13) + 1;
                        if (cardNumber == 1)
                        	System.out.println("Ace of " + s);
                        if (cardNumber == 11)
                        	System.out.println("Jack of " + s);
                        if (cardNumber == 12)
                        	System.out.println("Queen of " + s);
                        if (cardNumber == 13)
                        	System.out.println("King of " + s);
                        if (cardNumber != 1 && cardNumber != 11 && cardNumber != 12 && cardNumber != 13)
                        	System.out.println(cardNumber + " of " + s);
                        if (playersNames[player] == "Dealer" && (currentPlayer!=playersNames.length-1))
                            break;                         	
                        }
                        
                        
                    
                  if (playersHands[player][card] / 13 == 1) {
                	  cardNumber = (playersHands[player][card] % 13) + 1;
                      if (cardNumber == 1)
                      	System.out.println("Ace of " + d);
                      if (cardNumber == 11)
                      	System.out.println("Jack of " + d);
                      if (cardNumber == 12)
                      	System.out.println("Queen of " + d);
                      if (cardNumber == 13)
                      	System.out.println("King of " + d);
                      if (cardNumber != 1 && cardNumber != 11 && cardNumber != 12 && cardNumber != 13)
                      	System.out.println(cardNumber + " of " + d);
                      if (playersNames[player] == "Dealer" && (currentPlayer!=playersNames.length-1))
                          break;                         	
                      }
                  
                    if (playersHands[player][card] / 13 == 2) {
                    	cardNumber = (playersHands[player][card] % 13) + 1;
                        if (cardNumber == 1)
                        	System.out.println("Ace of " + c);
                        if (cardNumber == 11)
                        	System.out.println("Jack of " + c);
                        if (cardNumber == 12)
                        	System.out.println("Queen of " + c);
                        if (cardNumber == 13)
                        	System.out.println("King of " + c);
                        if (cardNumber != 1 && cardNumber != 11 && cardNumber != 12 && cardNumber != 13)
                        	System.out.println(cardNumber + " of " + c);
                        if (playersNames[player] == "Dealer" && (currentPlayer!=playersNames.length-1))
                            break;                         	
                        }
                    if (playersHands[player][card] / 13 == 3) {
                    	cardNumber = (playersHands[player][card] % 13) + 1;
                        if (cardNumber == 1)
                        	System.out.println("Ace of " + h);
                        if (cardNumber == 11)
                        	System.out.println("Jack of " + h);
                        if (cardNumber == 12)
                        	System.out.println("Queen of " + h);
                        if (cardNumber == 13)
                        	System.out.println("King of " + h);
                        if (cardNumber != 1 && cardNumber != 11 && cardNumber != 12 && cardNumber != 13)
                        	System.out.println(cardNumber + " of " + h);
                        if (playersNames[player] == "Dealer" && (currentPlayer!=playersNames.length-1))
                            break;                         	
                        }
                }
            }
            
        }
                    
   public static void dealCards(boolean[] deck, int[] playerhand) {
	   int count = 0;
	   while(count < 2) {
		   int deal = (int)(Math.random() * 52);
		   while (deck[deal])
			   deal = (int)(Math.random() * 52);
		   deck[deal] = true;
		   playerhand[count] = deal;
		   ++count;
		   }
	   }
    
    
    
    
    public static int countCards(int[] cards,  String [] playersNames) {
    int sum = 0;
    boolean aceFound=false;
    int c = 0;  //changed to 0
    for (int i = 0; i < cards.length && cards[i] != -1 ; i++ ) {//the loop doesn't need to go through the whole array
    	c = cards[i] % 13; //divide to get card number, but it's 1 off (lower cuz of 0 index)
       	if (c >= 0 && c < 9)
       		sum += c + 1;
       	if (c >= 9 && c <= 12)
       		sum += 10;      	
       	if (c == 0)
       		aceFound=true;
       	}
    if (aceFound)
    	sum+= (aceIsHigh(sum)?10:0);//After summing all cards, add ten if there are any aces with room to be high
    return sum;
    }
    
    public static boolean aceIsHigh(int sum) { //the math is still in the process
    	if (sum < 11) //System.out.println("Your Ace is high");
    		return true;
    	else // System.out.println("Your Ace is low");
    		return false;
    }
   
    public static void promptUser(int currentPlayer, int[][] players,boolean[] deck, String[] playersNames) {
    	Scanner input = new Scanner(System.in);
    	boolean isHit;
    	do{
            int sumOfCards = countCards(players[currentPlayer], playersNames);
            if(sumOfCards >  21){
                System.out.println("You Busted. Your turn is over; You lost ");
                break;
            }  
           // printCards(players,i, playersNames); COMMENTED OUT SO WON'T REPRINT, DIDN'T DELETE CUZ NOT SURE IF NEED LATER
            System.out.print(playersNames[currentPlayer] +", your cards count up to " 
           + sumOfCards +" Would you like to Hit or Stick?"
           		+ "(put in -true- for hit and -false- for stick)");
            String response = input.next();
            response = response.toLowerCase();
            isHit = response.contentEquals("hit")||response.contentEquals("true");
            
             if(isHit)
                System.out.println("You were dealt a " + (hit(deck,players[currentPlayer])%13)+1);
             else
                System.out.println(playersNames[currentPlayer] + ", Your turn is over");
        } while(isHit);
        input.close();
           
    }
    
    public static void playersTurn(int i, int[][] players,boolean[] deck,String [] playersName) {
    	printCards(players, i, playersName);
    	//need to put this if statement in the startGame method
    	if(isBlackJack(players[i])){
    		System.out.println(playersName[i] + "has BlackJack!!! Your turn is over; You Won! ");
    		return;
    	}
    	promptUser(i, players, deck, playersName);
        
    }
    
    public static int hit(boolean[] deck, int[] whosTurn) {
        int x = (int)(Math.random() * 52); 
        while(deck[x] == true) {
            x = (int)(Math.random() * 52);
        }
        deck[x] = true;
        for(int i = 0; i < whosTurn.length; i++) {
            if (whosTurn[i] == -1) {
                whosTurn[i] = x;
                break;
            }
        }
        return x;
    }
    
    public static void dealersTurn(int [][] players, boolean [] deck, String [] playersNames){
        int dSum = countCards(players[players.length - 1], playersNames);
        
        if (dSum < 17)
            hit(deck, players[players.length - 1]);
        else if(isThereOneAceHigh(players[players.length - 1], dSum) && dSum == 17) // this is what the method is expecting... the isThereOneAce method is also expecting to be passed in something
            hit(deck, players[players.length - 1]);
        else{   //stick
          for(int i = 0; i < players.length; i++)
            if(dSum > 21)
                revealWinnerAndTerminate(playersNames[i] + " Player wins!");
            else{ 
                if(countCards(players[i],playersNames) > dSum)
                     revealWinnerAndTerminate(playersNames[i] + " Player wins!");
                else{ 
                    if(countCards(players[i],playersNames) == dSum)
                         revealWinnerAndTerminate("Draw..");
                    else
                         revealWinnerAndTerminate("Dealer wins!");
                }
            }
        }
    }
    
    public static boolean isThereOneAceHigh(int [] dealer, int dealersSum){
        int inverseSum = dealersSum;
        for(int i = 0; i < dealer.length; i++){
            inverseSum -= (dealer[i] % 13<9? dealer[i] + 1:10);
            }
        if(inverseSum==10)
        	return true;
        return false;
    }
    
    public static void revealWinnerAndTerminate(String output){
        System.out.println(output);
        System.exit(1); //dont know if this is right
    }
    public static boolean isBlackJack(int[] playerHand){
    	   boolean oneTenValueCard=false;
           boolean oneAceCard=false;
           for(int i = 0; i < 2; i++) {
        	   if (playerHand[i]%13==0)
        		   oneAceCard=true;
        	   else if(playerHand[i]%13>=9)
        		   oneTenValueCard=true;
        	   }
           if(oneAceCard&&oneTenValueCard)
        	   return true;//blackjack can only happen where there are 2 cards and not more.     
        return false;
    }
    
    public static void populateDeck(int[] playerDeck) {
         for( int i = 0;i < playerDeck.length; i++){
             playerDeck[i] = -1;
         }
    }
} 


