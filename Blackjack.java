package BlackJack;
/*  fix up list:
    -need to take out a busted player from game 
    -fix the isThereOneAceHigh method 
    -compare winners more accurately
    (-show both cards for dealer by dealers turn)
    (-need to fix by which card dealt - if king queen or jack...)
    -when dealer has blackjack the program errorfully deals him another card
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
            
        int[][] players = new int[amountOfPlayers + 1][12];
        
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
        //input.close();
    }
    
    //printing the cards of each player
    /**
     * Prints the face value of each of the players cards and the face value of the dealers first card except for when it is the dealers turn
     * @param playersHands All playersHands on the playing field
     * @param currentPlayer The index of playersHand array that is the current player
     * @param playersNames The names of all players
     */
    public static void printCards(int[][] playersHands, int currentPlayer, String [] playersNames) {
        
        for (int player = 0; player < playersNames.length; player++){
            System.out.println(playersNames[player] + "'s cards are: ");
                for (int card = 0; card < playersHands[0].length && playersHands[player][card]!= -1; card++) {
             
                	String cardFaceValue = getCardFaceValueText(playersHands, player, card); 
                	System.out.println(cardFaceValue);
                	if(playersNames[player] == "Dealer" && currentPlayer != playersHands.length -1)
                		break;
                }
             
            
               System.out.println();
        }
    }
	/**
	 * Gets the face value of a card on the playing field.
	 * @param playersHands All Players hands on the playing field.
	 * @param player The index in the playershands array that is the current player.
	 * @param card The index of the current players card that we want to print.
	 * @return A String with the full Face value of the card.
	 */
	private static String getCardFaceValueText(int[][] playersHands,
			int player, int card) {
		int cardNumber;
		int suit;
		String cardFaceValue="";    
		cardNumber = (playersHands[player][card] % 13) + 1;
		    switch (cardNumber) {
		    	case 1: cardFaceValue="Ace of "; break;
		    	case 11: cardFaceValue="Jack of "; break;
		    	case 12: cardFaceValue="Queen of "; break;
		    	case 13: cardFaceValue="King of "; break;
		    	default: cardFaceValue= cardNumber + " of "; break;
		    }
		    suit = playersHands[player][card] / 13;
		    switch(suit) {
		    	case 0: cardFaceValue+="Spades"; break;
		    	case 1: cardFaceValue+="Diamonds"; break;
		    	case 2: cardFaceValue+="Clubs"; break;
		    	case 3: cardFaceValue+="Hearts"; break;
		    	default: cardFaceValue+="Unknown suit"; break;
		    }
		return cardFaceValue;
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
    	c = cards[i] % 13; //divide to get card number, but it's 1 off (lower because of 0 index)
       	if (c >= 0 && c < 9)
       		sum += c + 1;
       	if (c >= 9 && c <= 12)
       		sum += 10;      	
       	if (c == 0)
       		aceFound=true;
       	}
    if (aceFound)
    	sum+= (isSumLessThanEleven(sum)?10:0);//After summing all cards, add ten if there are any aces with room to be high
    return sum;
    }
    
    public static boolean isSumLessThanEleven(int sum) { 
    	if (sum < 11)
    		return true;
    	else 
    		return false;
    }
   
    public static void promptUser(int currentPlayer, int[][] players, boolean[] deck, String[] playersNames) {
    	Scanner input = new Scanner(System.in);
    	boolean isHit;
    	do{
            int sumOfCards = countCards(players[currentPlayer], playersNames);
            if(sumOfCards >  21){
                System.out.println(playersNames[currentPlayer]  + ", you Busted. Your turn is over; You lost ");
                break;
            }  
            System.out.print(playersNames[currentPlayer] +", your cards count up to " 
           + sumOfCards +" Would you like to Hit or Stick?"
           		+ "(put in -true- for hit and -false- for stick)");
            String response = input.next();
            response = response.toLowerCase();
            isHit = response.contentEquals("hit")||response.contentEquals("true");
            
             if(isHit)
             {	
            	hit(deck,players[currentPlayer]);
                System.out.println("You were dealt a " + getCardFaceValueText(players, currentPlayer, getPlayersFirstEmptyCardIndex(players[currentPlayer])-1));//need to add here if its a 10,11,12 that its king queen or jack
             }
             else
                System.out.println(playersNames[currentPlayer] + ", Your turn is over");
        } while(isHit);
       // input.close();
           
    }
    
    public static void playersTurn(int i, int[][] players,boolean[] deck,String [] playersName) {
    	printCards(players, i, playersName);
    	//need to put this if statement in the startGame method
    	if(isBlackJack(players[i])){
    		System.out.println(playersName[i] + " has BlackJack!!! Your turn is over; You Won! ");
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
        whosTurn[getPlayersFirstEmptyCardIndex(whosTurn)] = x;
        return x;
    }
	/**
	 * @param whosTurn The player index for whom we want the last card.
	 * @return The index of the last card in the players hand
	 */
	private static int getPlayersFirstEmptyCardIndex(int[] whosTurn) {
		int i;
        for(i= 0; i < whosTurn.length; i++) {
            if (whosTurn[i] == -1) {
                break;
            }
        }
		return i;
	}
    
    public static void dealersTurn(int [][] players, boolean [] deck, String [] playersNames){
        int dSum = countCards(players[players.length - 1], playersNames);
        boolean isHit;
        
        System.out.println("It is the dealer's turn. \nThe dealers cards are:"); //added this to print dealers first 2 cards
        for (int i = 0; i < 2; i++)
        	System.out.println(getCardFaceValueText(players, players.length -1, i));
        do{
        	isHit = false;
        	if (dSum < 17){
        		hit(deck, players[players.length - 1]);
        		dSum = countCards(players[players.length - 1], playersNames);
        		System.out.println("To see what the dealer has dealt, press enter!"); 
        		Scanner input = new Scanner(System.in);
        		input.nextLine();
        		System.out.println("The dealer was dealt a " 
        		    + getCardFaceValueText(players, players.length - 1, getPlayersFirstEmptyCardIndex(players[players.length - 1])-1) + " your cards now"
        				+ " count up to " + dSum);
        		isHit = true;
        	}else if(isThereOneAceHigh(players[players.length - 1], dSum) && dSum == 17){ // this is what the method is expecting... the isThereOneAce method is also expecting to be passed in something
        		hit(deck, players[players.length - 1]);
        		dSum = countCards(players[players.length - 1], playersNames);
        		System.out.println("Dealer, you were dealt a " + ((hit(deck,players[players.length - 1])%13)+1) + " your cards now"
        				+ " count up to " + dSum);
        		isHit = true;
        	}else{   //stick
                System.out.println("\nAll players cards will be displayed:");
        		printCards(players, players.length - 1, playersNames);
        		if(dSum > 21){
        		    System.out.println("Dealer busted with a total of " + dSum );
        			for(int i = 0; i < players.length - 1; i++){
        				if(isPlayerNotBusted(i, players, playersNames))
        					System.out.println(playersNames[i] + ": won!");
        				else
        					System.out.println(playersNames[i] + ": lost..");
        			}
        		}else
        			for(int i = 0; i < players.length - 1; i++){
        				if(isPlayerNotBusted(i, players, playersNames)){
        					if(countCards(players[i], playersNames) > dSum)
        						System.out.println(playersNames[i] + ": won!");
        					else if(countCards(players[i], playersNames) == dSum)
        						System.out.println(playersNames[i] + " and the Dealer -- PUSH, DRAW");
        					else//player has less than dealerSum
        						System.out.println(playersNames[i] + " lost..");
        				}else
        					System.out.println(playersNames[i] + " lost..");
        			}
        	}	
        }while(isHit);
    }
    
    private static boolean isPlayerNotBusted(int currentPlayer,int [][] players, String [] playersNames){
    	int sumOfCurrentPlayer = countCards(players[currentPlayer], playersNames);
    	//for(int j = 0; j < players[0].length; j++)
    		//sumOfCurrentPlayer += players[currentPlayer][j];
    	if(sumOfCurrentPlayer > 21)
    		return false;
    	else
    		return true;
    }
    
/**
     * This method checks to see if there is an Ace in dealer's hand and if the Ace is high
     * @param dealer The index of the dealer's cards on the playing field
     * @param dealersSum The sum of the dealer's hand
     * @return Returns true if there is an Ace in the dealer's hand and if the Ace is high
     */
    public static boolean isThereOneAceHigh(int [] dealer, int dealersSum){
    	int sum = 0;
    	boolean aceFound = false;
    	for (int i = 0; i < dealer.length && dealer[i] != -1 ; i++ ) {//the loop doesn't need to go through the whole array
        	int c = dealer[i] % 13; //divide to get card number, but it's 1 off (lower because of 0 index)
           	if (c >= 0 && c < 9)
           		sum += c + 1;
           	else if (c >= 9 && c <= 12)
           		sum += 10;      	
           	if (c == 0) {
           		aceFound=true;
           		if(isSumLessThanEleven(sum)) //this method used to be called aceIsHigh(int sum)
           			return true;
           		else 
           			return false;
           	}
    	}
      	return aceFound;
    }
    
    public static void revealWinnerAndTerminate(String output){
        System.out.println(output);
        System.exit(1);
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


