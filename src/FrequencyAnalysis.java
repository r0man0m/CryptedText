import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FrequencyAnalysis {
    private final Path PATH;
    public FrequencyAnalysis(Path path) {

        PATH = path;
    }
    public static char[] readFile(Path path) throws IOException {
         char[] chars = Files.readString(path).toCharArray();
         return chars;
   }
   public static HashMap<Character, Integer> setMap(char[] c) {
        int counter = 1;
       HashMap<Character, Integer> hashMap = new HashMap<>();
       for (int i = 0; i < c.length; i++) {
           if(!hashMap.containsKey(c[i])) {
               hashMap.put(c[i],counter);
           }else {
               hashMap.put(c[i], hashMap.get(c[i] + 1));
           }
       }
       return hashMap;
   }
   public static Integer getCounter(Path path) throws IOException{
        Integer i = setMap(readFile(path)).get('Т');
        return i;
   }

    }

