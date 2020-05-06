import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class HashTable<T>{
    private final LinkedList[] hashTable;
    private final int size;

    public HashTable(int size){
        hashTable = new LinkedList[size];
        this.size = size;
    }

    /**
     *
     * @param title Title of the text to be added to each arrayList. Input as title of the book without the ".txt"
     */
    public HashTable(String title, int size){
        hashTable = new LinkedList[size];
        this.size = size;
        readText(title);
    }

    public void delete(String s){
        int address = hash(s);
        boolean contains = hashTable[s].contains(s);
        if(contains){
            this.hashTable[s].remove(s);
        }
        else{
            System.out.println("Item you tried to remove is not stored in the Hash Table");
        }
    }

}

    public void put(String word){
        int index = hash(word);
        for(Object str : hashTable[index]){
            if(str.equals(word)){
                return;
            }
        }
        //TODO Test add first
        hashTable[index].add(word);
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
    private ArrayList<String> readText(String title)  {
        Scanner text = null;
        try {
            text = new Scanner(new BufferedReader(new FileReader(title +".txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> output = new ArrayList<String>();
        while (text.hasNext()){
            String line = text.nextLine();
            String[] pureLine = line.trim().replaceAll("[^a-zA-Z-']", " ").split(" ");
            output.addAll(Arrays.asList(pureLine));

        }
        System.out.println(output);
        return output;
    }



}
