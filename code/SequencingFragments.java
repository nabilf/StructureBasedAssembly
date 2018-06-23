package on;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author           Nabil Fannoush
 * @description      This class simulates the random reading in of the genomic
 *                   sequence as fragments of in the form of substrings of characters
 *                   representing nucleotides, as well as computing the reverse complement.
 */
public class SequencingFragments {

    /**
     * @description     This function splits the sequence randomly into substrings
     *                  of random lengths between 100 and 200, which is the range
     *                  of fragment sizes that many sequencers produce.
     * @param  toshred  Genomic sequence to fragment and simulate sequencing
     *                  process.
     */
    public static void makeshreds(String toshred) {
        Random randomGenerator = new Random();
        ArrayList<String> toshuffle = new ArrayList<String>();
        for (int x = 0; x < 5; x++) {
            StringBuilder str = new StringBuilder(toshred);
            System.out.println(">Sequence " + "fragments-Cover " + x);
            int randomInt;
            for (int hh = 0; str.length() > 200; hh++) {
                randomInt = randomGenerator.nextInt(101) + 100;
                str.insert(randomInt, "@");
                String[] splitsies = str.toString().split("@");
                toshuffle.add(splitsies[0]);
                StringBuilder strtemp = new StringBuilder(splitsies[1]);
                str = strtemp;
            }
            toshuffle.add(str.toString());
            Collections.shuffle(toshuffle);
            for (int i = 0; i < toshuffle.size(); i++) {
                System.out.println(">RF-" + x + "-" + i);
                System.out.println(toshuffle.get(i));
            }
        }
    }

    /**
     * @description      This function reverses sub-sequences for the purposes of
     *                   creating reverse complements.
     * @param  original  Genomic sequence to fragment and simulate sequencing
     *                   process.
     * @return           The reversed string.
     */
    public static String flip(String original) {
        String reverse = "";
        int length = original.length();
        for (int i = length - 1; i >= 0; i--)
            reverse = reverse + original.charAt(i);
        return reverse;
    }

    /**
     * @description  This function to calculate the reverse complement for
     *               a sequence string.
     * @param  str   Sequence string to reverse complemented.
     * @return       The reverse complement.
     */
    public static String reversecomp(String str) {
        StringBuilder str2 = new StringBuilder("");
        for (int nn = 0; nn < str.length(); nn++) {
            if (str.charAt(nn) == 'A') {
                str2.append('U');
            }
            if (str.charAt(nn) == 'U') {
                str2.append('A');
            }
            if (str.charAt(nn) == 'C') {
                str2.append('G');
            }
            if (str.charAt(nn) == 'G') {
                str2.append('C');
            }
        }
        return (flip(str2.toString()));
    }

    /**
     * the main contains an example run with an RNA sequence.
     */
    public static void main(String[] args) {
        String shredthis = "UUCCAGUGCGGAGUCUGGAGACACCCAGGGGGAAGCGAAGACCCUAUGGAGCUUUACUGCAGGCUGUCGCUGAGGACUCUCACUCCGGGAGGAGGACACCGAUAGCCGGGCAGUUUGACUGGGGCGGUACGCGCUCGAAAAGAUAUCGAGCGCGCCCUAUGGCUAUCUCAGCCGGGGACCCGGCGAAGAGUGCAAGAGCAAAAGAUAGCUUGACAGUGUUCUUCCCAACGAGGAACGCUGACGCGAAAGCGUGGUCUAGCGAACCAAUUAGCCUGCUUGAUGCGGGCAAUUGAUGACAGAAAAGCUACCCUAGGGAUAACAGAGUCGUCACUCGCAAGAGCACAUAUCGACCGAGUGGCUUGCUACCUCGAUGUCGGUUCCCUCCAUCCUGCCCGUGCAGAAGCGGGCAAGGGUGAGGUUGUUCGCCUAUUAAAGGAGGUCGUGAGCUGGGUUUAGACCGUCGUGAGACAGGUCGGCUGCUAUCUACUGGGUGUGUCCAA";
        String shredreversecomp = reversecomp(shredthis);
        makeshreds(shredthis);
        makeshreds(shredreversecomp);
    }
}
