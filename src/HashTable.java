import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HashTable {
    private ArrayList<String> mobyDick;
    private ArrayList<String> elephantsChild;
    public HashTable(){

    }

    /**
     *
     * @param title Title of the text to be added to each arrayList. Input as title of the book without the ".txt"
     */
    public HashTable(String title){
        if(title.equals("MobyDick")){
            mobyDick = readText(title);
        }
        else if(title.equals("ElephantsChild")){
            elephantsChild = readText(title);
        }
    }


    //TODO make tests should be working fine but ttests may be nice as a "hey we are thorough with our work"

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
            String[] pureLine = line.split(" ");

            for(int i = 0; i < pureLine.length; i++){
                if(pureLine[i].contains(".")){
                    String temp = pureLine[i].substring(0,pureLine[i].indexOf('.'));
                    pureLine[i] = temp;
                }
                if(pureLine[i].contains(",")){
                    String temp = pureLine[i].substring(0,pureLine[i].indexOf(','));
                    pureLine[i] = temp;
                }
                if(pureLine[i].contains("?")){
                    String temp = pureLine[i].substring(0,pureLine[i].indexOf('?'));
                    pureLine[i] = temp;
                }
                if(pureLine[i].contains("!")){
                    String temp = pureLine[i].substring(0,pureLine[i].indexOf('!'));
                    pureLine[i] = temp;
                }
            }
            output.addAll(Arrays.asList(pureLine));

        }
        System.out.println(output);
        return output;
    }



}
