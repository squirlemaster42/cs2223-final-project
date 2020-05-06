public class HashTable {

    public int hash(String word){
        final int C = 123, m = 1000;
        int h = 0;

        for(int i = 0; i < word.length(); i++){
            h = (h * C + ord(word.charAt(i))) % m;
        }

        return h;
    }

    public int ord(char c){
        return c;
    }
}
