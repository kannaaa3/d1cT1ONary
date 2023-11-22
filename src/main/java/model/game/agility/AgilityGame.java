package model.game.agility;

import javafx.util.Pair;
import model.database.Database;
import model.database.TableCreator;
import model.game.Game;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgilityGame extends Game {
    public static final String DEFAULT_FILE = "src/main/resources/specialWord.txt";
    private final List<String[]> synonyms, antonyms;
    private final Random random = new Random();
    private List<String> questionData;
    private int questionAnswer;

    /**
     * Function to create the agility game.
     */
    public AgilityGame() {
        synonyms = new ArrayList<>();
        antonyms = new ArrayList<>();
        String[] data = TableCreator.getQueryData(DEFAULT_FILE, "\n");
        for (int i = 0; i < data.length; i += 2) {
            synonyms.add(data[i].trim().split(" "));
            antonyms.add(data[i + 1].trim().split(" "));
        }
    }

    /**
     * Function to get k random value.
     *
     * @param origin        the lower bound
     * @param bound         the upper bound
     * @param numberOfValue the number of value
     * @return numberOfValue number in range [origin, bound)
     */
    private List<Integer> getRandomKValue(int origin, int bound, int numberOfValue) {
        if (bound - origin < numberOfValue) {
            System.out.println("The number of value > the length of the bound");
            return null;
        }
        List<Integer> numberSet = new ArrayList<>();
        for (int i = 0; i < numberOfValue; i++) {
            while (true) {
                Integer number = random.nextInt(origin, bound);
                if (!numberSet.contains(number)) {
                    numberSet.add(number);
                    break;
                }
            }
        }
        return numberSet;
    }

    /**
     * Function to create a multiple choice question with number of choices answer.
     *
     * @param numberOfChoices the number of available choice
     */
    private void createQuestion(int numberOfChoices) {
        int questionSet = random.nextInt(0, synonyms.size());
        List<String> result = new ArrayList<>();
        List<Integer> synonym = getRandomKValue(0,
                synonyms.get(questionSet).length, numberOfChoices - 1 + 1);
        List<Integer> antonym = getRandomKValue(0,
                antonyms.get(questionSet).length, 1);
        int resultPosition = random.nextInt(0, numberOfChoices) + 1;
        for (int i = 0; i <= numberOfChoices; i++) {
            if (i == resultPosition) {
                result.add(antonyms.get(questionSet)[antonym.get(0)]);
            }
            if (i < numberOfChoices) {
                result.add(synonyms.get(questionSet)[synonym.get(i)]);
            }
        }
        questionData = result;
        questionAnswer = resultPosition;
    }

    public void startGame() {
        gameScore = 0;
        gameState = GAME_RUNNING;
        createQuestion(4);
    }

    /**
     * Function to get game state.
     *
     * @return the game state representation:
     * game: AGILITY_GAME
     * questionData: List<String>
     */
    @Override
    public JSONObject getGameState() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("game", AGILITY_GAME);
        jsonObject.put("questionData", questionData);
        return jsonObject;
    }

    /**
     * Function to submit user's input.
     *
     * @param jsonObject the user's input to the game
     * @return a json object which is the response of the game
     */
    @Override
    public JSONObject submitData(JSONObject jsonObject) {
        int choice = jsonObject.getInt("answer");
        if (choice == questionAnswer) {
            gameScore++;
        }
        JSONObject respond = new JSONObject();
        jsonObject.put("questionAnswer", questionAnswer);
        createQuestion(4);
        return jsonObject;
    }
}