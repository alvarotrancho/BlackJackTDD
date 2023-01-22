package blackjack;

import java.util.ArrayList;

public class Player {
    String nombre;
    ArrayList<Card> hand;

    public Player(String nombre, ArrayList<Card> hand) {
        this.nombre = nombre;
        this.hand = hand;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    
    
}
