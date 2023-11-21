package model.util;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSHandler {
    private static Voice voice;
    private static VoiceManager freettsVoice;

    public static void playTTSOfWord(String word) {
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice("kevin16");
        if (voice == null) {
            System.err.println("Cannot find voice: kevin16");
            System.exit(1);
        }
        voice.allocate();
        voice.speak(word);
        voice.deallocate();
    }
}