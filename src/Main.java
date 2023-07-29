import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int choice = 1;
        int start = 0;
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
            Path encryptPath = Path.of("C:\\Users\\Roman\\IdeaProjects\\ProjectCriptoText\\EncryptionText.txt");
            Path pathDict = Path.of("C:\\Users\\Roman\\IdeaProjects\\ProjectCriptoText\\dictionary.txt");
            switch (choice) {
                case 1:
                    Files.delete(encryptPath);
                    Files.createFile(encryptPath);
                    System.out.println("Enter the path to the text file");
                    Scanner scanner1 = new Scanner(System.in);
                    String path = scanner1.nextLine();
                    Path textPath = Path.of(path);
                    System.out.println("Enter key");
                    object = new Encryption(scanner.nextInt());
                    System.out.println("Encryption text");
                    System.out.println();
                    encryptionText = object.encryptionMethod(Files.readString(textPath));
                    Files.write(encryptPath, encryptionText.getBytes(StandardCharsets.UTF_8));
                    System.out.println(encryptionText);
                    break;
                case 2:
                    System.out.println("If you have already encrypted the text, enter: 1");
                    System.out.println("For a new encryption enter: 2");
                    int c = scanner.nextInt();
                    switch (c) {
                        case 1:
                            System.out.println("Enter the path to the encrypted text");
                            Scanner scanner2 = new Scanner(System.in);
                            String StringPathEncrypt = scanner2.nextLine();
                            Path path1 = Path.of(StringPathEncrypt);
                            System.out.println("Enter key");
                            int key3 = scanner2.nextInt();
                            System.out.println("Decryption text");
                            System.out.println();
                            object = new Encryption(key3);
                            System.out.println(object.decryptionMethod(Files.readString(path1)));
                            break;
                        case 2:
                            System.out.println("Enter the path to the text");
                            Scanner scanner3 = new Scanner(System.in);
                            String pathToText = scanner3.nextLine();
                            Path path2 = Path.of(pathToText);
                            System.out.println("Enter the key for encryption");
                            int key2 = scanner.nextInt();
                            System.out.println("Decryption text");
                            System.out.println();
                            object = new Encryption(key2);
                            System.out.println(object.decryptionMethod(object.encryptionMethod(Files.readString(path2))));
                            Files.delete(encryptPath);
                            Files.createFile(encryptPath);
                            Files.write(encryptPath,object.decryptionMethod(object.encryptionMethod(Files.readString(path2))).getBytes(StandardCharsets.UTF_8));
                            break;
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
                    int ok = 0;
                    while (ok == 0) {
                        BruteForce bruteForce = new BruteForce();
                        System.out.println("Wait please...");
                        bruteForce.force(Files.readString(encryptPath), start, key, size, pathDict, firstLetterString.charAt(0));

                        System.out.println("Everything is OK ?");
                        System.out.println("If the text is not accurate enter 0, else enter 1");
                        start = bruteForce.returnKey() + 1;
                        ok = scanner.nextInt();
                    }
                    break;

            }
        }
    }


}
