import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int choice = 1;
        int start = 0;
        Path encryptPath = Path.of("C:\\Users\\Roman\\IdeaProjects\\CryptedText\\EncryptionText.txt");
        Path pathDict = Path.of("C:\\Users\\Roman\\IdeaProjects\\CryptedText\\dictionary.txt");
        Path keyPath = Path.of("C:\\Users\\Roman\\IdeaProjects\\CryptedText\\key.txt");
        while ( choice != 0) {
            System.out.println("Select the program operation mode:");
            System.out.println("If you want to encrypt the file enter: 1");
            System.out.println("If you want to decrypt the file enter: 2");
            System.out.println("If you want to brute force decryption the file enter: 3");
            System.out.println("For exit enter: 0");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            Encryption object;
            String encryptionText;
            switch (choice) {
                case 1:
                    Files.delete(keyPath);
                    Files.createFile(keyPath);
                    Files.delete(encryptPath);
                    Files.createFile(encryptPath);
                    System.out.println("Enter the path to the text file");
                    Scanner scanner1 = new Scanner(System.in);
                    String path = scanner1.nextLine();
                    Path textPath = Path.of(path);
                    System.out.println("Enter key");
                    int decryptionkey = scanner.nextInt();
                    object = new Encryption(decryptionkey);
                    System.out.println("Encryption text");
                    System.out.println();
                    encryptionText = object.encryptionMethod(Files.readString(textPath));
                    Files.write(encryptPath, encryptionText.getBytes(StandardCharsets.UTF_8));
                    Files.write(keyPath, String.valueOf(decryptionkey).getBytes());
                    System.out.println(encryptionText);
                    break;
                case 2:
                    if(Files.readString(encryptPath).length() != 0) {
                        System.out.println("Enter the path to the encrypted text");
                        Scanner scanner2 = new Scanner(System.in);
                        String StringPathEncrypt = scanner2.nextLine();
                        Path path1 = Path.of(StringPathEncrypt);
                        int key3 = Integer.parseInt(Files.readString(keyPath));
                        System.out.println("Decryption text");
                        System.out.println();
                        object = new Encryption(key3);
                        System.out.println(object.decryptionMethod(Files.readString(path1)));
                    }else {
                        System.out.println("Encrypted file is empty");
                        System.out.println("You must decrypt the file");
                        System.out.println();
                    }
                    break;

                case 3:
                    System.out.println("Brute Force Method");
                    System.out.println("Enter maximum volume of key");
                    int key = scanner.nextInt();
                    System.out.println("Enter size of alphabet");
                    int size = scanner.nextInt();
                    System.out.println("Enter first letter of alphabet");
                    Scanner scanner2 = new Scanner(System.in);
                    String firstLetterString = scanner2.nextLine();
                    System.out.println("Enter the path to encrypted file");
                    Scanner scanner3 = new Scanner(System.in);
                    String pathToEncrypt = scanner3.nextLine();
                    Path path1 = Path.of(pathToEncrypt);
                    int ok = 0;
                    while (ok == 0) {
                        BruteForce bruteForce = new BruteForce();
                        System.out.println("Wait please...");
                        bruteForce.force(Files.readString(path1), start, key, size, pathDict, firstLetterString.charAt(0));
                        System.out.println("Everything is OK ?");
                        System.out.println("If the text is not accurate enter 1, else enter 0");
                        start = bruteForce.returnKey() + 1;
                        ok = scanner.nextInt();
                    }
                    break;

            }
        }
    }

}
