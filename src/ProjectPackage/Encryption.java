package ProjectPackage;

import java.util.Scanner;

public class Encryption extends AbstractCryptClass {
    private  int key;
    private String firstSmallLetter;
    private  String lastSmallLetter;
    private  String firstBigLetter;
    private  String lastBigLetter;
    private int size;


    public void setAlphabet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first small letter");
        firstSmallLetter = scanner.nextLine();
        System.out.println("Enter last small letter");
        lastSmallLetter = scanner.nextLine();
        System.out.println("Enter first big letter");
        firstBigLetter = scanner.nextLine();
        System.out.println("Enter last big letter");
        lastBigLetter = scanner.nextLine();
        System.out.println("Enter size of alphabet");
        size = scanner.nextInt();
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    public String encryptionMethod(String text) {
        char[]word = text.toCharArray();
        for (int i = 0; i < word.length; i++) {
            if(word[i] >= (int)firstBigLetter.charAt(0) && word[i] <= (int)lastBigLetter.charAt(0)) {
                word[i] = (char) (module((word[i] - (int)firstBigLetter.charAt(0) + key), size ) + (int)firstBigLetter.charAt(0)); // Big letter
            }
            else if(word[i] >= (int)firstSmallLetter.charAt(0) && word[i] <= (int)lastSmallLetter.charAt(0)) {
                word[i] = (char) (module((word[i] - (int)firstSmallLetter.charAt(0) + key), size ) + (int)firstSmallLetter.charAt(0)); // Small letter
            }
            else if(word[i] >= 32 && word[i] <= 46) {
                word[i] = (char) (module((word[i] - 32 + key), 15) + 32); // Symbols
            }

        }
        return new String(word);
    }
    public String decryptionMethod(String text) {
        char[]word = text.toCharArray();
        for (int i = 0; i < word.length; i++) {
            if(word[i] >= (int)firstBigLetter.charAt(0) && word[i] <= (int)lastBigLetter.charAt(0)) {
                word[i] = (char) (module((word[i] - (int)firstBigLetter.charAt(0) - key), size ) + (int)firstBigLetter.charAt(0)); // Big letter
            }
            else if(word[i] >= (int)firstSmallLetter.charAt(0) && word[i] <= (int)lastSmallLetter.charAt(0)) {
                word[i] = (char) (module((word[i] - (int)firstSmallLetter.charAt(0) - key), size ) + (int)firstSmallLetter.charAt(0)); // Small letter
            }
            else if(word[i] >= 32 && word[i] <= 46) {
                word[i] = (char) (module((word[i] - 32 - key), 15) + 32); // Symbols
            }

        }
        return new String(word);
    }

}

