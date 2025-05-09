package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * @author Practice
 * @project proj1
 */
public class GuitarHero {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static GuitarString[] guitarStrings;
    static {
        int len = KEYBOARD.length();
        guitarStrings = new GuitarString[len];
        for (int i = 0; i < len; i++) {
            guitarStrings[i] = new GuitarString(440*Math.pow(2,(i - 24) / 12));
        }
    }
    public static void main(String[] args) {
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index != -1) {
                    guitarStrings[index].pluck();
                }
            }
            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0,len = KEYBOARD.length(); i < len; i++) {
                sample += guitarStrings[i].sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);
            for (int i = 0,len = KEYBOARD.length(); i < len; i++) {
                guitarStrings[i].tic();
            }
        }
    }
}
