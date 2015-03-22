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
        
        System.out.print("What are the names of players? ");
        
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
             playersTurn(i,players ,deck, playersNames);
         }
              
 
        dealersTurn(players, deck, playersNames);
    }
    
    //printing the cards of each player
    public static void printCards(int[][] array, int player, String [] playersNames) {
        
        char s = 6; //suits will display in command line but not eclipse
        char d = 4;
        char c = 5;
        char h = 3;
        int cardNumber = 0;
        
        for (int j = 0; j < playersNames.length; j++){
            System.out.println(playersNames[j] + "'s cards are: ");
                for (int i = 0; i < array[0].length && array[j][i]!= -1; i++) {
                	/*if (array[j][i] == -1)
                		break; */
                	if (array[j][i] / 13 == 0) {
                        cardNumber = (array[j][i] % 13) + 1;
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
                        if (playersNames[j] == "Dealer" && (player!=playersNames.length-1))
                            break;                         	
                        }
                        
                        
                    
                  if (array[j][i] / 13 == 1) {
                	  cardNumber = (array[j][i] % 13) + 1;
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
                      if (playersNames[j] == "Dealer" && (player!=playersNames.length-1))
                          break;                         	
                      }
                  
                    if (array[j][i] / 13 == 2) {
                    	cardNumber = (array[j][i] % 13) + 1;
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
                        if (playersNames[j] == "Dealer" && (player!=playersNames.length-1))
                            break;                         	
                        }
                    if (array[j][i] / 13 == 3) {
                    	cardNumber = (array[j][i] % 13) + 1;
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
                        if (playersNames[j] == "Dealer" && (player!=playersNames.length-1))
                            break;                         	
                        }
                }
            }
            
        }
                    
   public static void dealCards(boolean[] deck, int[] array) {
        int count = 0;
      
      while(count < 2) {
       int deal = (int)(Math.random() * 52);
       
    
       while (deck[deal]) {
          deal = (int)(Math.random() * 52);
       }    
            
       deck[deal] = true;
       array[count] = deal;
       
       ++count;
      }
    }
    
    
    
    
    public static int countCards(int[] cards,  String [] playersNames) {
    int sum = 0;
    int c = 0;  //changed to 0
        for (int i = 0; i < cards.length && cards[i] != -1 ; i++) {//the loop doesn't need to go through the whole array 
               	c = cards[i] % 13; //divide to get card number, but it's 1 off (lower cuz of 0 index)
               	
               	if (c > 0 && c < 9)
               		sum += c + 1;
               	
               	if (c >= 9 && c <= 12)
               		c = 10;
            		sum += c ;
            
              	if (c == 0){
            		if(isAceHigh(cards, sum)){
            			System.out.println("Your Ace is high");
            			c = 11;
            			sum += c;
            		}
            		else {
            			System.out.println("Your Ace is low");
            			c = 1;
            			sum += c;
            			}
              		}    		
        		}
            	return sum;
            }
    
    public static boolean isAceHigh(int[] cards, int sum) { //the math is still in the process
        if (sum < 11) {
           //System.out.println("Your Ace is high");
           return true;
       }
       else {
    	  // System.out.println("Your Ace is low");
    	   return false;
           }
         
    }
   
    public static void promptUser(int i, int[][] players,boolean[] deck, String[] playersNames) { 
        Scanner input = new Scanner(System.in);
        boolean isHit;
       do{
            int x = countCards(players[i], playersNames);
            if(x >  21){
                System.out.println("Your turn is over; You lost ");
                break;
            }  
           // printCards(players,i, playersNames); COMMENTED OUT SO WON'T REPRINT, DIDN'T DELETE CUZ NOT SURE IF NEED LATER
            System.out.print(playersNames[i] +", your cards count up to " + x +" Would you like to Hit or Stick?(put in -true- for hit and -false- for stick)");
            isHit = input.nextBoolean();
            
             if(isHit)
                hit(deck,players[i]);
             else
                System.out.println("Your turn is over");
        } while(isHit);
        
           
    }
    
    public static void playersTurn(int i, int[][] players,boolean[] deck,String [] playersName) {  
        printCards(players, i, playersName);
                //need to put this if statment in the startGame method
               if(isBlackJack(players[i])){
                    System.out.println("Your turn is over; You Won ");  
                
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
        else if(isThereOneAce(players[players.length - 1]) && dSum == 17 && isAceHigh(players[players.length - 1], dSum)) // this is what the method is expecting... the isThereOneAce method is also expecting to be passed in something
            hit(deck, players[players.length - 1]);
        else{   //stick
          for(int i = 0; i < players.length; i++)
            if(dSum > 21)
                revealWinnerAndTerminate("Player wins!");
            else{ 
                if(countCards(players[i],playersNames) > dSum)
                     revealWinnerAndTerminate("Player wins!");
                else{ 
                    if(countCards(players[i],playersNames) == dSum)
                         revealWinnerAndTerminate("Draw..");
                    else
                         revealWinnerAndTerminate("Dealer wins!");
                }
            }
        }
    }
    
    public static boolean isThereOneAce(int [] dealer){
        boolean isAce = false;
        for(int i = 0; i < dealer.length; i++){
            if(dealer[i] == 0 || dealer[i] == 13 || dealer[i] == 26 || dealer[i] == 39){
                isAce = true;
                break;
            }
        }
        return isAce;
    }
    
    public static void revealWinnerAndTerminate(String output){
        System.out.println(output);
        System.exit(1); //dont know if this is right
    }
    public static boolean isBlackJack(int[] playerHand){
        for(int i = 0; i < playerHand.length; i++) {
            if (playerHand[i]==0||playerHand[i]==13||playerHand[i]==26||playerHand[i]==39){
                if((playerHand[i]>=9 && playerHand[i]<=12)||(playerHand[i]>=22&&playerHand[i]<=25)||(playerHand[i]>=35 && playerHand[i]<=38)||(playerHand[i]>=48&&playerHand[i]>=51))
                    return true;//blackjack can only happen where there are 2 cards and not more.
            }
        }
        return false;
    }
    
    public static void populateDeck(int[] playerDeck) {
         for( int i = 0;i < playerDeck.length; i++){
             playerDeck[i] = -1; 
         }
    }
} 


