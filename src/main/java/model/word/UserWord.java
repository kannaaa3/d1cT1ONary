package model.word;

public class UserWord extends Word {
    public static final int DAY_TO_SECOND = 24 * 3600;
    public static final int[] REVIEW_TIME = new int[]{
            DAY_TO_SECOND, 3 * DAY_TO_SECOND, 10 * DAY_TO_SECOND, 30 * DAY_TO_SECOND
    };
    private int fluencyLevel;
    private int lastReviewTimestamp;
    private int reviewTimes;

    /**
     * Constructor for the user's word data.
     *
     * @param word the word we want to save
     * @param fluencyLevel the fluency level
     * @param lastReviewTimestamp the last timestamp
     * @param reviewTimes the number of review time
     */
    public UserWord(Word word, int fluencyLevel, int lastReviewTimestamp, int reviewTimes) {
        super(word.getWord(), word.getPhonetic(), word.getMeaning());
        this.fluencyLevel = fluencyLevel;
        this.lastReviewTimestamp = lastReviewTimestamp;
        this.reviewTimes = reviewTimes;
    }

    public int getFluencyLevel() {
        return fluencyLevel;
    }

    public int getLastReviewTimestamp() {
        return lastReviewTimestamp;
    }

    public int getReviewTimes() {
        return reviewTimes;
    }

    /**
     * Function to update when user review the word.
     */
    public void review() {
        this.lastReviewTimestamp = (int)(System.currentTimeMillis() / 1000);
        reviewTimes++;
    }

    /**
     * Function to get the next recommended revision time.
     *
     * @return a number is timestamp in second for the next recommended time for reviewing the
     * current word
     */
    public int getNextRevisionTime() {
        return this.lastReviewTimestamp
                + REVIEW_TIME[Math.min(reviewTimes, REVIEW_TIME.length - 1)];
    }

    /**
     * Function to update user's fluency level.
     *
     * @param updateValue the addition of fluency level (fluency += updateValue)
     */
    public void updateFluencyLevel(int updateValue) {
        this.fluencyLevel += updateValue;
    }
}