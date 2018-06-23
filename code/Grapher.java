import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author nabil fannoush
 * @description De Bruijn graph implementation per algorithm
 */
public class Grapher {

    static ArrayList<String> RightVerts = new ArrayList<String>();
    static ArrayList<String> LeftVerts = new ArrayList<String>();

    public static void main(String[] args) {
        ArrayList<String> Kmers = new ArrayList<String>();

        Kmers = getKmers(readSequenceFromFile("file to read sequence from/actual sequence"), 6, Kmers);
        System.out.println("..........................................");

        for (int t = 0; t < Kmers.size(); t++)
            System.out.println(t + "- " + Kmers.get(t));

        System.out.println("..........................................");

        getVerts(Kmers);
        System.out.println("................left verts...................");

        for (int t = 0; t < LeftVerts.size(); t++)
            System.out.println(t + LeftVerts.get(t));

        System.out.println("................right verts...................");

        for (int t = 0; t < RightVerts.size(); t++)
            System.out.println(t + RightVerts.get(t));

    }

    public static ArrayList<String> getKmers(String seq, int length, ArrayList<String> Kmers2) {
        int seqLength = seq.length();
        if (seqLength > length) {
            for (int i = 0; i < seqLength - length + 1; i++) {
                Kmer k = new Kmer(seq.substring(i, length + i));
                if (!Kmers2.contains(k.getKmer()))
                    Kmers2.add(k.getKmer());
            }
        } else {
            Kmer j = new Kmer(seq);
            if (!Kmers2.contains(j.getKmer()))
                Kmers2.add(j.getKmer());
        }
        return Kmers2;
    }

    public static void getVerts(ArrayList<String> kmers) {
        for (int m = 0; m < kmers.size(); m++) {
            String leftMiniMer = (kmers.get(m).substring(0, kmers.get(m).length() - 1));
            String rightMiniMer = (kmers.get(m).substring(1, kmers.get(m).length()));
            LeftVerts.add(leftMiniMer);
            RightVerts.add(rightMiniMer);
        }

    }

    public static String readSequenceFromFile(String fileName) {
        String subStr = "";
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                subStr += strLine;
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());

        }
        return subStr.trim();

    }
}
