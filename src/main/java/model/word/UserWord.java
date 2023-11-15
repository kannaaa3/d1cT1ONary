package model.word;

public class UserWord extends Word {
    private int fluencyLevel;
    private int lastReviewTimestamp;
    private int reviewTimes;
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


}
