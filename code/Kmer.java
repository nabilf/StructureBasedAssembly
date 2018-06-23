/**
 * @author          nabil fannoush
 * @description     De Bruijn K-mer object class
 */
public class Kmer {
    private static String subsequence;
    
      public Kmer1 (String input)
      {
          this.setKmer(input);
      }
    
      private void setKmer(String input)
    {
        subsequence = input;
    }
      String getKmer()
    {
        return subsequence ;
    }
    
}
