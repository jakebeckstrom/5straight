package gui;

public final class Constants {

    private Constants() {
        // restrict instantiation
    }

    // Game variables
    public static final char PLAYER_ONE_PEG = '1';
    public static final char PLAYER_TWO_PEG = '2';
    public static final char OPEN_SPACE = '0';

    // Winner variables
    public static final char PLAYER_ONE_WIN = '1';
    public static final char PLAYER_TWO_WIN = '2';
    public static final char NO_WIN = '0';

    // Player type variables
    public static final char HUMAN = '0';
    public static final char EASY_BOT = '1';

    // Board representation variables
    public static final int PLAYER_ONE_INT = 200;
    public static final int PLAYER_TWO_INT = 300;

    //Game action variables
    public static final int ACTION_DRAW = 1;
    public static final int ACTION_PLAY = 2;

    // Turn toggle
    public static final boolean PLAYER_ONE_TURN = true;
    public static final boolean PLAYER_TWO_TURN = false;
    
}