package game;

import java.util.Random;
import java.util.Stack;

/**
 * Contains representation of a deck of cards numbered 0-99
 * @author Jacob Beckstrom
 */
public class Deck {
    /**
     * Stores the cards in a FIFO manner
     */
    private Stack<Integer> cards;

    public Deck(){
        cards = new Stack<>();
        for (int i = 0; i < 100; i++) {
            cards.push(i);
        }
        shuffle();
    }

    private void shuffle() {
        int[] shuf = new int[100];
        for (int i =0; i < 100; i++) {
            shuf[i] = this.cards.pop();
        }
        for (int j = 0; j < 10; j++) {
            Random rand = new Random();
            rand.nextInt();
            for (int k = 0; k < 100-1; k++) {
                int change = rand.nextInt(99-k);
                swap(shuf, k, change);
            }
        }
        for (int n = 0; n < 100; n++) {
            this.cards.push(shuf[n]);
        }
    }

    private void swap(int[] deck, int i, int j) {
        int temp = deck[i];
        deck[i] = deck[j];
        deck[j] = temp;
    }

    public int draw() {
        return this.cards.pop();
    }

    public void reset() {
        cards = null;
        cards = new Stack<>();
        for (int i = 0; i < 100; i++) {
            cards.push(i);
        }
        shuffle();
    }
}