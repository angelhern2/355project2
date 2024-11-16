public class CWE1025 {
    public void compareValues(int id1, String name1, int id2, String name2) {
        // Incorrect comparison of unrelated factors
        if (id1 == name2.length()) { // Should compare id1 with id2 or name1 with name2
            System.out.println("Match found (incorrect comparison)");
        }
  if(name1 == name2)//Should be using the .equals method to compare string literals
     System.out.println( "Match found (incorrect comparison)\n");
}

