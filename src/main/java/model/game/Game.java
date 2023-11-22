package model.game;

import org.json.JSONObject;

import java.util.Dictionary;

public abstract class Game {
    public static final String AGILITY_GAME = "AgilityGame";
    public static final int GAME_NOT_START = 0;
    public static final int GAME_RUNNING = 1;
    public static final int GAME_WIN = 2;
    public static final int GAME_LOSE = 4;
    public static final int GAME_PAUSE = 8;
    protected int gameState = 0;
    protected int gameScore;

    /**
     * Function to initialize the game.
     */
    public abstract void startGame();

    /**
     * Function to get game state as a dictionary.
     *
     * @return a JSON Object which is the game state, the game state guarantee to have a state
     * is game which is the game's name
     */
    public abstract JSONObject getGameState();

    /**
     * Function to submit user's respond to the game.
     *
     * @param jsonObject the user's input to the game
     * @return return a JSONObject which is the game's respond
     */
    public abstract JSONObject submitData(JSONObject jsonObject);

    /**
     * Check if the game is won.
     *
     * @return true if user won, false if otherwise
     */
    public boolean win() {
        return gameState == GAME_WIN;
    }

    /**
     * Check if the game is lost.
     *
     * @return true if user won, false if otherwise
     */
    public boolean lose() {
        return gameState == GAME_LOSE;
    }

    public int getGameScore() {
        return gameScore;
    }
}