package blackjack;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class BlackJackTest {
    private ArrayList<Card> handPlayer1;
    private ArrayList<Card> handPlayer2;
    private ArrayList<Card> handPlayer3;
    private ArrayList<Card> handCroupier;
    private ArrayList<Card> deck;
    private Player jugador1;
    private Player jugador2;
    private Player jugador3;
    private Player croupier;
    private ArrayList<Player> ganadores;
    
    
    /**
     * Test of getSumHand method, of class BlackJack.
     */
    
    /*### Prueba acerca del metodo que calcula el valor de la mano del jugador/croupier 
    Player 1: "J", "A" (21, Black Jack)
    Player 2: "10", "5", "6" (21)
    Player 3: "3", "6", "A", "3", "A", "K" (24)
    Croupier: "9", "7" (16)*/
    
    @Test
    public void testGetSumHand() {
        int sumHandPlayer1 = 21;
        int sumHandPlayer2 = 21;
        int sumHandPlayer3 = 24;
        int sumHandCroupier = 16;
        
        handPlayer1 = new ArrayList<>();
        handPlayer1.add(new FigureCard("J"));
        handPlayer1.add(new Ace());
        
        handPlayer2 = new ArrayList<>();
        handPlayer2.add(new NormalCard(10));
        handPlayer2.add(new NormalCard(5));
        handPlayer2.add(new NormalCard(6));
        
        handPlayer3 = new ArrayList<>();
        handPlayer3.add(new NormalCard(3));
        handPlayer3.add(new NormalCard(6));
        handPlayer3.add(new Ace());
        handPlayer3.add(new NormalCard(3));
        handPlayer3.add(new Ace());
        handPlayer3.add(new FigureCard("K"));
        
        handCroupier = new ArrayList<>();
        handCroupier.add(new NormalCard(9));
        handCroupier.add(new NormalCard(7));
        
        jugador1 = new Player("Jugador 1",handPlayer1);
        jugador2 = new Player("Jugador 2",handPlayer2);
        jugador3 = new Player("Jugador 3",handPlayer3);
        croupier = new Player("Croupier",handCroupier);
        
        assertEquals("Verificando suma del valor de las cartas del jugador 1", sumHandPlayer1, BlackJack.getSumHand(jugador1.getHand()));
        assertEquals("Verificando suma del valor de las cartas del jugador 2", sumHandPlayer2, BlackJack.getSumHand(jugador2.getHand()));
        assertEquals("Verificando suma del valor de las cartas del jugador 3", sumHandPlayer3, BlackJack.getSumHand(jugador3.getHand()));
        assertEquals("Verificando suma del valor de las cartas del jugador 4", sumHandCroupier, BlackJack.getSumHand(croupier.getHand()));
    }

    /*### Caso 1
    Player 1: "J", "A" (21, Black Jack)
    Player 2: "10", "5", "6" (21)
    Player 3: "3", "6", "A", "3", "A", "K" (24)
    Croupier: "9", "7" (16)
    Deck: "5", "4", "K", "2" */
    
    @Test
    public void test_1_BlackJackGame() {
        handPlayer1 = new ArrayList<>();
        handPlayer1.add(new FigureCard("J"));
        handPlayer1.add(new Ace());
        
        handPlayer2 = new ArrayList<>();
        handPlayer2.add(new NormalCard(10));
        handPlayer2.add(new NormalCard(5));
        handPlayer2.add(new NormalCard(6));
        
        handPlayer3 = new ArrayList<>();
        handPlayer3.add(new NormalCard(3));
        handPlayer3.add(new NormalCard(6));
        handPlayer3.add(new Ace());
        handPlayer3.add(new NormalCard(3));
        handPlayer3.add(new Ace());
        handPlayer3.add(new FigureCard("K"));
        
        handCroupier = new ArrayList<>();
        handCroupier.add(new NormalCard(9));
        handCroupier.add(new NormalCard(7));
        
        deck = new ArrayList<>();
        deck.add(new NormalCard(5));
        deck.add(new NormalCard(4));
        deck.add(new FigureCard("K"));
        deck.add(new NormalCard(2));
        
        jugador1 = new Player("Jugador 1",handPlayer1);
        jugador2 = new Player("Jugador 2",handPlayer2);
        jugador3 = new Player("Jugador 3",handPlayer3);
        croupier = new Player("Croupier",handCroupier);
        
        ganadores = new ArrayList<>();
        ganadores.add(jugador1);
        
        assertEquals("Ganadores del BlackJack test1 (Jugador1) ", ganadores, BlackJack.getWinners(jugador1, jugador2, jugador3, croupier, deck));
    }
    
    /*### Caso 2
    Player 1: "10", "K" (20)
    Player 2: "10", "2", "6" (18)
    Player 3: "8", "8", "5" (21)
    Croupier: "5", "10" (15)
    Deck: "A" , "3" , "K" , "2" */

    @Test
    public void test_2_BlackJackGame() {
        handPlayer1 = new ArrayList<>();
        handPlayer1.add(new NormalCard(10));
        handPlayer1.add(new FigureCard("K"));
        
        handPlayer2 = new ArrayList<>();
        handPlayer2.add(new NormalCard(10));
        handPlayer2.add(new NormalCard(2));
        handPlayer2.add(new NormalCard(6));
        
        handPlayer3 = new ArrayList<>();
        handPlayer3.add(new NormalCard(8));
        handPlayer3.add(new NormalCard(8));
        handPlayer3.add(new NormalCard(5));
        
        handCroupier = new ArrayList<>();
        handCroupier.add(new NormalCard(5));
        handCroupier.add(new NormalCard(10));
        
        deck = new ArrayList<>();
        deck.add(new Ace());
        deck.add(new NormalCard(3));
        deck.add(new FigureCard("K"));
        deck.add(new NormalCard(2));
        
        jugador1 = new Player("Jugador 1",handPlayer1);
        jugador2 = new Player("Jugador 2",handPlayer2);
        jugador3 = new Player("Jugador 3",handPlayer3);
        croupier = new Player("Croupier",handCroupier);
        
        ganadores = new ArrayList<>();
        ganadores.add(jugador1);
        ganadores.add(jugador3);
        
        assertEquals("Ganadores del BlackJack test2 (Jugador1, Jugador3) ", ganadores, BlackJack.getWinners(jugador1, jugador2, jugador3, croupier, deck));
    }
    
    /*### Caso 3
    Player 1: "10", "10", "A" (21)
    Player 2: "9", "A", "A" (21)
    Player 3: "9", "2", "A" (12)
    Croupier: "K", "6" (16)
    Deck: "K" , "3" , "Q" , "2" */

    @Test
    public void test_3_BlackJackGame() {
        handPlayer1 = new ArrayList<>();
        handPlayer1.add(new NormalCard(10));
        handPlayer1.add(new NormalCard(10));
        handPlayer1.add(new Ace());
        
        handPlayer2 = new ArrayList<>();
        handPlayer2.add(new NormalCard(9));
        handPlayer2.add(new Ace());
        handPlayer2.add(new Ace());
        
        
        handPlayer3 = new ArrayList<>();
        handPlayer3.add(new NormalCard(9));
        handPlayer3.add(new NormalCard(2));
        handPlayer3.add(new Ace());
        
        handCroupier = new ArrayList<>();
        handCroupier.add(new FigureCard("K"));
        handCroupier.add(new NormalCard(6));
        
        deck = new ArrayList<>();
        deck.add(new FigureCard("K"));
        deck.add(new NormalCard(3));
        deck.add(new FigureCard("Q"));
        deck.add(new NormalCard(2));
        
        jugador1 = new Player("Jugador 1",handPlayer1);
        jugador2 = new Player("Jugador 2",handPlayer2);
        jugador3 = new Player("Jugador 3",handPlayer3);
        croupier = new Player("Croupier",handCroupier);
        
        ganadores = new ArrayList<>();
        ganadores.add(jugador1);
        ganadores.add(jugador2);
        ganadores.add(jugador3);
        
        assertEquals("Ganadores del BlackJack test2 (Jugador1, Jugador3) ", ganadores, BlackJack.getWinners(jugador1, jugador2, jugador3, croupier, deck));
    }
    
}
