import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class HashTable{
    private final LinkedList[] hashTable;
    private final int size;

    public HashTable(int size){
        hashTable = new LinkedList[size];
        this.size = size;
    }

    /**
     *
     * @param title Title of the text to be added to each arrayList. Input as title of the book without the ".txt"
     * @param size Size of the hash table
     */
    public HashTable(String title, int size){
        hashTable = new LinkedList[size];
        this.size = size;
        readText(title);
    }


    /**
     * searches for a given string in the hashmap and deletes if it exists
     * @param s the string you are looking to delete
     */

    public void delete(String s){
        int address = hash(s);
        boolean contains = hashTable[hash(s)].contains(s);
        if(contains){
            this.hashTable[hash(s)].remove(s);
        }
        else{
            System.out.println("Item you tried to remove is not stored in the Hash Table");
        }
    }

    public void put(String word){
        int index = hash(word);
        if(hashTable[index] == null){
            hashTable[index] = new LinkedList();
        }
        for(Object str : hashTable[index]){
            if(str.equals(word)){
                return;
            }
        }
        //TODO Test add first
        hashTable[index].add(word);
    }

    public LinkedList get(final int position){
        return hashTable[position];
    }

    public int hash(String word){
        final int C = 123; //TODO Figure out what to use for C
        int h = 0;

        for(int i = 0; i < word.length(); i++){
            h = (h * C + ord(word.charAt(i))) % size;
        }

        return h;
    }

    public int ord(char c){
        return c;
    }

    //TODO make tests should be working fine but tests may be nice as a "hey we are thorough with our work"

    /**
     * Method to read texts from the .txt files
     * @param title Title of the book; also name of the corresponding .txt
     * @return An array list of string containing each word of the texts with punctuation (!,?,.,,) removed
     */
    private void readText(String title)  {
        Scanner text = null;
        try {
            text = new Scanner(new BufferedReader(new FileReader(title +".txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (text.hasNext()){
            String line = text.nextLine();
            String[] pureLine = splitLine(line);
            for(int i = 0; i < pureLine.length; i++){
                put(pureLine[i]);
            }

        }
    }

    String[] splitLine(String line){
        return line.trim().replaceAll("[^a-zA-Z-' ]", "").split(" ");
    }

    /**
     * Prints the hashTable
     */
    public void printTable(){
        System.out.println("Hash    |||     Word     ");
        for(int i = 0; i < hashTable.length; i++){
            System.out.println( i + "     |||    "+hashTable[i]);
        }
    }

    /**
     * finds the most used address in the hash table
     */

    public void findMostUsedAddress(){
        int largestList = 0;
        int largestListLocation = 0;
        for(int i=0; i<this.size; i++){
            if(this.hashTable[i].size()>largestList){
                largestList = this.hashTable[i].size();
                largestListLocation = i;
            }
        }
        System.out.println("The has address containing the greatest number of distinct words is " + largestListLocation + " and contains " + largestList + " words");
    }

    /**
     * finds the longest cluster of items stored in the has table
     */

    public void findLongestCluster(){
        int tempCount = 0;
        int tempLocationStart = 0;
        int longest = 0;
        int longestLocationStart = 0;
        int longestLocationEnd = 0;

        for (int i = 0; i < 1000; i++) {
            if (this.hashTable[i] == null) {
                if (longest < tempCount) {
                    longest = tempCount;
                    longestLocationStart = tempLocationStart;
                    longestLocationEnd = i - 1;
                }
                tempCount = 0;
            } else {
                if (i == 0) {
                    tempCount++;
                } else {
                    if (this.hashTable[i - 1] == null) {
                        tempLocationStart = i;
                    }
                    tempCount++;
                }
            }
        }

        System.out.println("\nc) The longest cluster in the table is " + longest + " and it is between indexes " + longestLocationStart + " and " + longestLocationEnd + " inclusive");
    }

    /**
     * counts the number of non-empty addresses and finds the load factor of the hash table
     */

    public void countAddresses(){
        int numberOfItems = 0;
        for(int i=0; i<this.size; i++){
            if(this.hashTable[i]!=null){
                numberOfItems++;
            }
        }
        float loadFactor = (float)numberOfItems/this.size;
        System.out.printf("There are " + numberOfItems + " non-empty addresses in the table. This makes the load factor " + loadFactor);
    }

    /**
     * counts the longest empty area in the hash table
     */

    public void longestEmptyArea(){
        int tempCount = 0;
        int tempLocationStart = 0;
        int longest = 0;
        int longestLocationStart = 0;
        int longestLocationEnd = 0;

        for (int i = 0; i < 1000; i++) {               //finds the longest empty area
            if (this.hashTable[i] != null) {
                if (longest < tempCount) {
                    longest = tempCount;
                    longestLocationStart = tempLocationStart;
                    longestLocationEnd = i-1;
                }
                tempCount = 0;
            } else {
                if (i == 0) {
                    tempCount++;
                } else {
                    if (this.hashTable[i - 1] != null) {
                        tempLocationStart = i;
                    }
                    tempCount++;
                }
            }
        }

        System.out.println("The longest empty area in the table is " + longest + " and it is between indexes " + longestLocationStart + " and " + longestLocationEnd + ", inclusive");
    }

}
