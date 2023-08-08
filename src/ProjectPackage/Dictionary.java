package ProjectPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Dictionary {
    private  final int SIZE;
    private  final Path PATH;
    private char firstLetter;
    private final HashMap<Character, ArrayList<String>> DICTIONARYMAP;
    public Dictionary(int size, Path path, char firstLetter) {
        SIZE = size;
        PATH = path;
        this.firstLetter = firstLetter;
        DICTIONARYMAP = new HashMap<>();
    }
    public void setDictionary() throws IOException {
        String first;
        List<String> list = Files.readAllLines(PATH);
        for (int i = 0; i < SIZE; i++) {
            first = String.valueOf(firstLetter);
            ArrayList<String> fullListForNextLetter = new ArrayList<>();
            for (String S:list
            ) {
                if(S.startsWith(first)) {
                    fullListForNextLetter.add(S);
                }
            }
            ArrayList<String> splitList = new ArrayList<>();
            for (String S: fullListForNextLetter
            ) {
                String[]arr = S.split(" ");
                for (String A: arr
                ) {
                    if(A.startsWith(first)) {
                        splitList.add(A.toLowerCase(Locale.ROOT));
                    }
                }
            }
            DICTIONARYMAP.put(firstLetter, splitList);
            firstLetter++;

        }
    }
    public ArrayList<String> getDictionary(char letter) {
        return DICTIONARYMAP.get(letter);
    }
}

