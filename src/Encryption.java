public class Encryption {
    private final int key;
    public Encryption(int key) {
        this.key = key;
    }
    public String encryptionMethod(String text) {
        char[]word = text.toCharArray();
        for (int i = 0; i < word.length; i++) {
            if(word[i] >= 1040 && word[i] <= 1071) {
                word[i] = (char) (module((word[i] - 1040 + key), 32) + 1040); // Big letter
            }
            else if(word[i] >= 1072 && word[i] <= 1103) {
                word[i] = (char) (module((word[i] - 1072 + key), 32) + 1072); // Small letter
            }
            else if(word[i] > 32 && word[i] <= 46) {
                word[i] = (char) (module((word[i] - 32 + key), 15) + 32); // Symbols
            }

        }
        return new String(word);
    }
    public String decryptionMethod(String text) {
        char[]word = text.toCharArray();
        for (int i = 0; i < word.length; i++) {
            if(word[i] >= 1040 && word[i] <= 1071) {
                word[i] = (char) (module((word[i] - 1040 - key), 32) + 1040); // Big letter
            }
            else if(word[i] >= 1072 && word[i] <= 1103) {
                word[i] = (char) (module((word[i] - 1072 - key), 32) + 1072); // Small letter
            }
            else if(word[i] > 32 && word[i] <= 46) {
                word[i] = (char) (module((word[i] - 32 - key), 15) + 32); // Symbols
            }

        }
        return new String(word);
    }
    public static int module(int m, int d) {
        int rez;
        if (m < 0) {
            m *= -1;
            if(m % d == 0) {
                return m % d;
            } else {
                rez = d - (m % d);
                if (m == d) {
                    return 0;
                }
            }
        }
        else {
            rez = m % d;
        }
        return rez;
    }
}

