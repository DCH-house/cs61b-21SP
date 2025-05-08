package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * @author Practice
 * @project proj1
 */
public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static GuitarString[] guitarStrings;
    static {
        int len = keyboard.length();
        guitarStrings = new GuitarString[len];
        for(int i = 0; i < len; i ++){
            guitarStrings[i] = new GuitarString(440*Math.pow(2,(i - 24) / 12));
        }
    }
    public static void main(String[] args){
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    guitarStrings[index].pluck();
                }
            }
            /* compute the superposition of samples */
            double sample = 0.0;
            for(int i = 0,len = keyboard.length(); i < len; i ++) {
                sample += guitarStrings[i].sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);
            for(int i = 0,len = keyboard.length(); i < len; i ++) {
                guitarStrings[i].tic();
            }
        }
    }
}
