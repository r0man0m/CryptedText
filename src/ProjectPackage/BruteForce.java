package ProjectPackage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;


public class BruteForce {
    public static int key = 0;
    public  void force (String text,int start, int sizeKeys, int sizeAlphabet, Path path, char firstLetter) throws IOException {
        Dictionary dictionary = new Dictionary(sizeAlphabet, path, firstLetter);
        dictionary.setDictionary();
        int count;
        boolean isFind = false;

        for (int i = start; i < sizeKeys; i++) { // Get decryption text for next key
            count = 0;
            Encryption obj = new Encryption(i);
            String[] decryptionText = obj.decryptionMethod(text).toLowerCase().split(" "); // Get split array from decryption text
            char first = firstLetter; // Set first letter
            for (int j = 0; j < sizeAlphabet; j++) {
                ArrayList<String> listForNextLetter = dictionary.getDictionary(first); // Get list for next letter
                for (int k = 0; k < decryptionText.length; k++) {
                    if(listForNextLetter.contains(decryptionText[k])) { //Search contains every word of text in list of next letter
                        count++; // if contains is exist, increment count
                    }
                }
                first++; // next letter
            }
            if(count > (decryptionText.length / 10)) { // Check for contains words in the decryption text
                isFind = true;
                key = i;
                break;
            }

        }
        if (isFind) {
            Encryption encryption = new Encryption(key);
            System.out.println(encryption.decryptionMethod(text));
            System.out.println("Your key is " + key);

        }
        else {
            System.out.println("Not find");
        }
    }
    public int returnKey() {
        return key;
    }

}

