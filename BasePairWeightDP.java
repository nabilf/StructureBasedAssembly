package thes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * @author           Nabil Fannoush
 * @description      This class is a bare-bones initial Dynamic Programming
 *                   Similarity Matrix that scores reverse complement pairings
 *                   between fragments based on the strength of possible bonds.
 *                   This suggests an ordering of fragments that is supported by
 *                   physical and chemical conditions.
 */
public class BasePairWeightDP {
    static double maxval = -1.0;
    static double maxvalmain = -1.0;
    static int maxvalmainmarker;


    //Example fragments and their reverse complements. These can be
    // created by SequencingFragments
    static String[] orig = {"UCCGUAACAUAAUUGGCUU", "CAUAUAGAAGGAUAGGAGUAACGAACCUA",
            "UAGUCUGAACAUAUAUGGAAA", "GAGCCUUUAUACAGUAAUGU",
            "CUAUUCCGAUAGGAAGUAGGGUCAAGUGACUCGAAAU", "AACACCUAAGGCAAUCCUGAG",
            "GGGGAUUACCCUUCUAGGGUAGUGAUA", "AUAUCGAAAAAUCCUCUAAUUCAGGG",
            "CUAAGCUCUUAGUAAUAAGAGAAAGUGCAACGA"};

    static String[] comp = {"UUCGGUUAAUACAAUGCCU", "AUCCAAGCAAUGAGGAUAGGAAGAUAUAC",
            "AAAGGUAUAUACAAGUCUGAU", "UGUAAUGACAUAUUUCCGAG",
            "UAAAGCUCAGUGAACUGGGAUGAAGGAUAGCCUUAUC", "GAGUCCUAACGGAAUCCACAA",
            "AUAGUGAUGGGAUCUUCCCAUUAGGGG", "GGGACUUAAUCUCCUAAAAAGCUAUA",
            "AGCAACGUGAAAGAGAAUAAUGAUUCUCGAAUC"};

    /**
     * @description     This function finds the max value for matrix parsing.
     * @param   left    The value left of current position.
     * @param   up      The value above current position.
     * @param   diag    The value diagonal to current position.
     * @return          Which of the three values is the biggest.
     */
    public static double max(double left, double up, double diag)
    {
        double biggest;
        biggest = diag;
        if (biggest < up) biggest = up;
        if (biggest < left) biggest = left;
        if (biggest < 0) biggest = 0;
        BigDecimal bd = new BigDecimal(biggest);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * @description     This function creates the Similarity Matrix.
     * @param  orig     The set of actual fragments.
     * @param  comp     The set of reverse complements.
     */
    public static void newmat(String orig, String comp)
    {

//===========strings input start=================	

        String string1 = "";
        String string2 = "";
        ArrayList<String[]> matches = new ArrayList<>();

        string1 = orig;

        string2 = comp;
        System.out.println(orig);
        System.out.println(comp);

//===========strings input end===================

//===========Initialise matrix start===================

        double[][] simil = new double[string1.length() + 1][string2.length() + 1];
        int n = string1.length();
        int m = string2.length();

        for (int ii = 0; ii <= n; ++ii) {
            simil[ii][0] = 0;

        }
        for (int jj = 0; jj <= m; ++jj) {
            simil[0][jj] = 0;

        }
//===========Initialise matrix end=====================


////===========matrix border start===================
//for (int i=0; i<=string1.length(); i++)	      
//{
//	simil[i][0] =0;
//
//	
//}
//
//for (int j=0; j<=string2.length(); j++)	      
//{
//	simil[0][j] =0;
//
//	
//}
//	      
////===========matrix border end=====================


//===========fill matrix start===================
        int p = -1;
        double g = 0.0;
        for (int i = 1; i <= string1.length(); i++) {
            maxval = -1.0;
            for (int j = 1; j <= string2.length(); j++) {


                if ((string1.charAt(i - 1) == 'C' && string2.charAt(j - 1) == 'G')
                        || (string1.charAt(i - 1) == 'G' && string2.charAt(j - 1) == 'C')
                        || (string1.charAt(i - 1) == 'A' && string2.charAt(j - 1) == 'U')
                        || (string1.charAt(i - 1) == 'U' && string2.charAt(j - 1) == 'A')
                        || (string1.charAt(i - 1) == 'G' && string2.charAt(j - 1) == 'U')
                        || (string1.charAt(i - 1) == 'U' && string2.charAt(j - 1) == 'G')
                        ) {
                    if (string1.charAt(i - 1) == 'G' && string2.charAt(j - 1) == 'C') {
                        g = -8.4;
                        p = 1;
                    }
                    if (string1.charAt(i - 1) == 'C' && string2.charAt(j - 1) == 'G') {
                        g = -8.4;
                        p = 1;
                    }
                    if (string1.charAt(i - 1) == 'A' && string2.charAt(j - 1) == 'U') {
                        g = -8.0;
                        p = 1;
                    }

                    if (string1.charAt(i - 1) == 'U' && string2.charAt(j - 1) == 'A') {
                        g = -8.0;
                        p = 1;
                    }
                    if (string1.charAt(i - 1) == 'G' && string2.charAt(j - 1) == 'U') {
                        g = -1.0;
                        p = 1;
                    }
                    if (string1.charAt(i - 1) == 'U' && string2.charAt(j - 1) == 'G') {
                        g = -1.0;
                        p = 1;
                    }
                } else

                    g = 2.0;

                simil[i][j] = max(simil[i - 1][j] - 1,
                        simil[i][j - 1] - 1,
                        simil[i - 1][j - 1] + (-1 * g));


                if (simil[i][j] > maxval) maxval = simil[i][j];
            }
        }
//System.out.println("=================================");
//System.out.println("Output similarity matrix:");
        System.out.println();

        for (int i = 0; i <= string1.length(); i++) {
            for (int j = 0; j <= string2.length(); j++) {
                System.out.print(simil[i][j] + "  ");
            }
            System.out.println();
        }

//===========fill matrix end=====================
    }
    /**
     * @description     The main then prints out the scoring pairings of fragments .
     */
    public static void main(String[] args) {

        for (int i = 0; i < orig.length; i++) {
            maxvalmain = -1;
            System.out.println();

            System.out.println("####################################################################################################################################################################");
            System.out.println("Fragment " + i + " " + orig[i] + "  ######################################################################################################################");
            System.out.println("####################################################################################################################################################################");
            for (int j = 0; j < comp.length; j++) {

                if (i != j) {
                    System.out.println("against reverse complement of fragment " + j + " __________________________________________________________________________________________\n");
                    newmat(orig[i], comp[j]);
                    if (maxval > maxvalmain) {
                        maxvalmain = maxval;
                        maxvalmainmarker = j;
                    }
                }
            }
            System.out.println("***************************************");
            System.out.println("*   BEST SCORING IS WITH " + comp[maxvalmainmarker] + " WITH A SCORE OF " + maxvalmain + "   *");
            System.out.println("***************************************");
        }
    }
}
