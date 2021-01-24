import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeckTest {

    private Deck testDeck1;
    private Deck testDeck2;
    private Deck testDeck3;

    @Before
    public void setUp() {
        testDeck1 = new Deck();
        testDeck2 = new Deck();
        testDeck3 = new Deck();
    }

    @Test
    public void testCards() {
        int collisions = 0;
        for (int i = 0; i < 100; i++) {
            int val1 = testDeck1.draw();
            int val2 = testDeck2.draw();
            int val3 = testDeck3.draw();
            assertTrue("Outside of deck range" + val1, 0 <= val1 && val1 < 100);
            assertTrue("Outside of deck range" + val2, 0 <= val2 && val2 < 100);
            assertTrue("Outside of deck range" + val3, 0 <= val3 && val3 < 100);
            if (val1 == val2 && val1 == val3) {
                collisions++;
            }
        }
        assertTrue("Too Many collisions" + collisions, collisions <= 1);
    }
}
