package blackjack;


import java.util.ArrayList;

public class BlackJack {
    public static void main(String[] args) {
        
        
        ArrayList<Card> handPlayer1 = new ArrayList<>();
        handPlayer1.add(new NormalCard(10));
        handPlayer1.add(new FigureCard("J"));
        
        ArrayList<Card> handPlayer2 = new ArrayList<>();
        handPlayer2.add(new NormalCard(10));
        handPlayer2.add(new NormalCard(2));
        handPlayer2.add(new NormalCard(6));
        
        ArrayList<Card> handPlayer3 = new ArrayList<>();
        handPlayer3.add(new NormalCard(8));
        handPlayer3.add(new NormalCard(8));
        handPlayer3.add(new NormalCard(5));
        
        
        ArrayList<Card> handCroupier = new ArrayList<>();
        handCroupier.add(new NormalCard(5));
        handCroupier.add(new NormalCard(10));
        
        ArrayList<Card> deck = new ArrayList<>();
        deck.add(new Ace());
        deck.add(new NormalCard(3));
        deck.add(new FigureCard("K"));
        deck.add(new NormalCard(2));
        
        
        Player jugador1 = new Player("Jugador 1",handPlayer1);
        Player jugador2 = new Player("Jugador 2",handPlayer2);
        Player jugador3 = new Player("Jugador 3",handPlayer3);
        Player croupier = new Player("Croupier",handCroupier);
        
        Player[] jugadores = new Player[4];
        jugadores[0] = jugador1;
        jugadores[1] = jugador2;
        jugadores[2] = jugador3;
        jugadores[3] = croupier;
        

        //Player[] ganadores = new Player[3];
        ArrayList<Player> winners = new ArrayList<>();
        winners = getWinners(jugadores[0], jugadores[1], jugadores[2], jugadores[3], deck);
        System.out.println("Jugador1 tiene: " + getSumHand(jugadores[0].getHand()));
        System.out.println("Jugador2 tiene: " + getSumHand(jugadores[1].getHand()));
        System.out.println("Jugador3 tiene: " + getSumHand(jugadores[2].getHand()));
        System.out.println("Croupier tiene: " + getSumHand(croupier.getHand()));
        for (Player winner : winners) {
            System.out.println("Ha ganado " + winner.getNombre() + " con un " + getSumHand(winner.getHand()));
        }
    } 
        
    /*### Caso 1
    Player 1: "J", "A" (21, Black Jack)
    Player 2: "10", "5", "6" (21)
    Player 3: "3", "6", "A", "3", "A", "K" (24)
    Croupier: "9", "7" (16)
    Deck: "5", "4", "K", "2" */
    
    public static ArrayList<Player> getWinners(Player jugador1, Player jugador2, Player jugador3, Player croupier, ArrayList<Card> deck) {
        ArrayList<Player> winners = new ArrayList<>();
        Player[] jugadores = new Player[3];
        jugadores[0] = jugador1;
        jugadores[1] = jugador2;
        jugadores[2] = jugador3;
        /* CASOS BASE */
        
        /* Si la suma de las cartas del croupier es menor a 17, tiene que sacar más cartas */
        
        for (int i = 0; i < deck.size() ; i++) {
            if ( getSumHand(croupier.getHand()) < 17 ) {
                croupier.getHand().add(deck.remove(0));
            } else {
                break;
            }
            
        }
        
        /* Si el croupier tiene un BlackJack, no va a ganar ningun jugador (devolvemos array vacio) */
        if ( getSumHand(croupier.getHand()) == 21 && croupier.getHand().size() == 2) {
                return winners;
        }
        
        for (int i = 0; i < jugadores.length; i++) {
            /* Si el jugador tiene un BlackJack, lo añadimos a la lista de ganadores y seguimos. */
            if ( getSumHand(jugadores[i].getHand()) == 21 && jugadores[i].getHand().size() == 2) {
                winners.add(jugadores[i]);
                continue;
            }
            
            if ( getSumHand(croupier.getHand()) > 21 && getSumHand(jugadores[i].getHand()) <= 21 ) {
                winners.add(jugadores[i]);
                continue;
            }
            
            /* Si la mano del jugador es menor o igual a 21 y la mano del croupier es menor a la mano del jugador, gana el jugador */
            if ( getSumHand(jugadores[i].getHand()) <= 21 && getSumHand(jugadores[i].getHand()) > getSumHand(croupier.getHand()) ) {
                winners.add(jugadores[i]);
                continue;
            }
            
            /* Si la mano del jugador es igual a la del croupier, gana el croupier */
            if ( getSumHand(croupier.getHand()) == getSumHand(jugadores[i].getHand()) ) {
                continue;
            }
        }
        return winners;
    }
    
    public static int getSumHand(ArrayList<Card> handPlayer){
        int sumHand = 0;
        for (Card card : handPlayer) {
            if (card instanceof Ace && sumHand == 10) {
                card.setValue(11);
                sumHand = sumHand + card.getValue();
            } else {
                sumHand = sumHand + card.getValue();
            }
        }
        return sumHand;
    }
}
