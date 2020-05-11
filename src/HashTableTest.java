import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Hashtable;
import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class HashTableTest {

    public static final int TABLE_SIZE = 1000;
    private static HashTable smallText, elephantChild, dict, mobyDick, hash;

    @BeforeClass
    public static void readFiles(){
        //TODO Add texts
        smallText = new HashTable("small", TABLE_SIZE);
        elephantChild = new HashTable("ElephantsChild", TABLE_SIZE);
        dict = new HashTable("dict", TABLE_SIZE);
        mobyDick = new HashTable("MobyDick", TABLE_SIZE);
        hash = new HashTable(TABLE_SIZE);
    }

    @Test
    public void testPutWord(){
        assertEquals(1, countWord(dict.get(hash.hash("a")), "a"));
        assertEquals(1, countWord(dict.get(hash.hash("saltus")), "saltus"));
        assertEquals(1, countWord(dict.get(hash.hash("unembayed")), "unembayed"));
        assertEquals(1, countWord(dict.get(hash.hash("vet")), "vet"));
        assertEquals(1, countWord(dict.get(hash.hash("zwitter")), "zwitter"));
    }

    @Test
    public void testWordAddedOnce(){
        assertEquals(1, countWord(dict.get(hash.hash("a")), "a"));
        assertEquals(1, countWord(dict.get(hash.hash("a")), "a"));
        assertEquals(1, countWord(dict.get(hash.hash("a")), "a"));
        assertEquals(1, countWord(dict.get(hash.hash("a")), "a"));
        assertEquals(1, countWord(dict.get(hash.hash("saltus")), "saltus"));
        assertEquals(1, countWord(dict.get(hash.hash("saltus")), "saltus"));
        assertEquals(1, countWord(dict.get(hash.hash("saltus")), "saltus"));
        assertEquals(1, countWord(dict.get(hash.hash("saltus")), "saltus"));
    }

    @Test
    public void testHash(){
        assertEquals(520, hash.hash("sugar-cane"));
        assertEquals(520, hash.hash("them"));
        assertEquals(522, hash.hash("tone"));
        assertEquals(523, hash.hash("uncle"));
        assertEquals(524, hash.hash("spanked"));
        assertEquals(524, hash.hash("young"));
        assertEquals(523, hash.hash("'Vantage"));
        assertEquals(522, hash.hash("mere-smear"));
    }

    @Test
    public void testDelete(){
        assertEquals(1, countWord(dict.get(hash.hash("a")), "a"));
        dict.delete("a");
        assertEquals(0, countWord(dict.get(hash.hash("a")), "a"));
        assertEquals(1, countWord(dict.get(hash.hash("saltus")), "saltus"));
        dict.delete("saltus");
        assertEquals(0, countWord(dict.get(hash.hash("saltus")), "saltus"));
    }

    @Test
    public void testOrd(){
        assertEquals(45, hash.ord('-'));
        assertEquals(65, hash.ord('A'));
    }

    @Test
    public void testSplitLine(){
        assertArrayEquals(new String[]{"a", "b", "c"}, hash.splitLine("a b c"));
        assertArrayEquals(new String[]{"a-b", "c"}, hash.splitLine("a-b c"));
        assertArrayEquals(new String[]{"a", "b'c"}, hash.splitLine("a b'c"));
        assertArrayEquals(new String[]{"a", "b", "c"}, hash.splitLine("a .b. c?"));
    }

    private int countWord(LinkedList list, String word){
        int count = 0;
        for(Object obj : list){
            if (obj.equals(word)){
                count++;
            }
        }
        return count;
    }

    @Test
    public void testDiffTableSize(){
        int tableSizeBase = TABLE_SIZE;
        int tableSize1 = 999;
        int tableSize2 = 10000000;


        //runTime
        System.out.println(" |      RunTime       |");
        System.out.println("< >==================< >");
        HashTable hashT1 = new HashTable("ElephantsChild", tableSizeBase, true);
        HashTable hashT2 = new HashTable("ElephantsChild", tableSize1, true);
        HashTable hashT3 = new HashTable("ElephantsChild", tableSize2, true);

        //emptySpace
        System.out.println(" |     EmptySpace     |");
        System.out.println("< >==================< >");
        hashT1.longestEmptyArea(tableSizeBase);
        hashT2.longestEmptyArea(tableSize1);
        hashT3.longestEmptyArea(tableSize2);

        //clusters
        System.out.println(" |      Clusters      |");
        System.out.println("< >==================< >");
        hashT1.findLongestCluster(tableSizeBase);
        hashT2.findLongestCluster(tableSize1);
        hashT3.findLongestCluster(tableSize2);
    }

}
