import java.util.ArrayList;
import java.util.Scanner;

public class DeckTester_7Fung{
   public static void main(String[] args){
      System.out.println("Testing Deck: ");
      Deck deck = new Deck();
      deck.shuffle();
     
      Player player = new Player();
      
      Card dealtCard = deck.deal();
      player.add( dealtCard );
      dealtCard = deck.deal();
      player.add( dealtCard );
      
      System.out.println( player );
      
      System.out.println( "Hand value: " + player.getHandValue( ) );
      
      while( player.getUserChoice().equals("Hit") ) {
        dealtCard = deck.deal();
        player.add( dealtCard );
      
       
        System.out.println( player );
        System.out.println( "Hand value: " + player.getHandValue( ) );
      }
      
      System.out.println("Thanks for playing!");
      
   }
}

class Player{
   private Hand hand;
      
   public Player(){
       hand = new Hand();
   }
   
   public String getUserChoice(){ 
      Scanner myObj = new Scanner(System.in);
      System.out.println("Hit or Stay?");
      String hitStay = myObj.nextLine();
      return hitStay;
   }  
   public void add( Card card ){
      hand.addCard(card);
   }
   public int getHandValue(){
      return hand.getHandValue();
   }
   
   public String toString(){
      return hand.toString();
   }
}

class Hand{
   private ArrayList<Card> cards; 
   
   public Hand(){
      cards = new ArrayList<Card>();
   }
   
   public void addCard(Card dealtCard){
      cards.add(dealtCard);
   }
   
   public int getHandValue(){
      int val = 0;
      int aceCount = 0;
      for (int i = 0; i < cards.size(); i++){
         val += cards.get(i).getValue();
         if (cards.get(i).getRank() == 1){
            aceCount++;
         }
      }
      for (int i = 0; i < aceCount; i++){
         if (val > 21){
            val -= 10;
         }
      }
      return val;
   }
   public String toString(){
      String endStr = "";
      for (int i = 0; i < cards.size(); i++){
         endStr = endStr + cards.get(i) + ", ";
      }
      return endStr;
   }
}

class Deck{

   private ArrayList<Card> deck = new ArrayList<Card>();
   
   public Deck(){
      for (int i = 1; i < 14; i++){
         for (int j = 1; j < 5; j++){
            deck.add(new Card(i, j));
         }
      }
   }
   
   public Card deal(){
      Card temp = (deck.get(deck.size()-1));
      deck.remove(deck.size()-1);
      return temp;
   }
   
   public void shuffle(){

      int ran;
      Card temp = deck.get(0);
      for (int i = 51; i >= 0; i--){
         ran = (int)(Math.random()*i+1);
         temp = deck.get(ran);
         deck.set(ran, deck.get(i));
         deck.set(i, temp);
      }
   }
}

class Card{
   private int suit; // 1: spade, 2: hearts, 3: diamond, 4: clubs
   private int rank; // 1: ace... 13: king
   private int value;
   
   private String suitStr;
   private String rankStr;
   
   public Card(int rank, int suit){
      this.suit = suit;
      this.rank = rank;
      if (suit == 1){
         suitStr = "Spades";
      } else if (suit == 2){
         suitStr = "Hearts";
      } else if (suit == 3){
         suitStr = "Diamonds";
      } else if (suit == 4){
         suitStr = "Clubs";
      }
      
      //rank conversion
      if (rank == 1){
         rankStr = "Ace";
      } else if (rank == 11){
         rankStr = "Jack";
      } else if (rank == 12){
         rankStr = "Queen";
      } else if (rank == 13){
         rankStr = "King";
      } else {
         rankStr = rank + "";
      }         
   }
  
   public String toString(){
      return rankStr + " of " + suitStr + " (point value: " + getValue() + ")";
   }
   
   public int getValue(){
      if (rank == 1){
         value = 11;
      } else if (rank == 11 || rank == 12 || rank == 13){
         value = 10;
      } else {
         value = rank;
      }
      return value;
   }
   public int getRank(){
      return rank;
   }
}
