package ProjectPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static boolean wrong = true;
    static Scanner scanner = new Scanner(System.in);
    static  String encryptionText;
    static Encryption object = new Encryption();

    public static void main(String[] args)throws IOException{
        System.out.println("Please, set the alphabet\n");
        object.setAlphabet();
        int choice = 1;
        int start = 0;
        Path encryptPath = Path.of("src/EncryptionText.txt");
        Path pathDict = Path.of("src/ProjectPackage/dictionary.txt");
        Path keyPath = Path.of("src/key.txt");


        while ( choice != 0) {
            System.out.println();
            System.out.println("Select the program operation mode:");
            System.out.println("-------------------------------------------------------");
            System.out.println("If you want to encrypt the file enter: 1");
            System.out.println("If you want to decrypt the file enter: 2");
            System.out.println("If you want to brute force decryption the file enter: 3");
            System.out.println("For exit enter: 0");
            System.out.println("-------------------------------------------------------");

            while (wrong) {
                try {

                    choice = scanner.nextInt();

                    wrong = false;
                } catch (InputMismatchException ex1) {
                    System.out.println("You must enter the number");
                    scanner.next();

                }
            }
            switch (choice) {
                case 0: System.out.println("Exit...");
                    break;
                case 1: decrypt(encryptPath, keyPath);
                    break;
                case 2: encrypt(encryptPath, keyPath);
                    break;
                case 3: brutForce(encryptPath, pathDict, start);
                default:
                    System.out.println("You must enter 1 or 2 or 3 for choice or 0 for exit\n");
                    wrong = true;
            }
        }
        scanner.close();
    }
    public static void decrypt(Path encryptPath, Path keyPath) throws IOException {
        if(Files.notExists(encryptPath)) {
            Files.createFile(encryptPath);
        }
        if(Files.notExists(keyPath)) {
            Files.createFile(keyPath);
        }
         wrong = true;
        while (wrong) {
            try {
                System.out.println("Enter the path to the text file");
                scanner.nextLine();
                String path = scanner.nextLine();
                Path textPath = Path.of(path);
                System.out.println("Enter key");
                int decryptionkey = scanner.nextInt();
                object.setKey(decryptionkey);
                System.out.println("Encryption text");
                System.out.println();
                encryptionText = object.encryptionMethod(Files.readString(textPath));
                Files.writeString(encryptPath, encryptionText);
                Files.write(keyPath, String.valueOf(decryptionkey).getBytes());
                System.out.println(encryptionText);
                wrong = false;
            } catch (InputMismatchException ex2) {
                System.out.println("You must enter the key us a number");
            }catch (NoSuchFileException n) {
                System.out.println("Wrong path to the file");
            }
        }
        wrong = true;
    }
    public static void encrypt(Path encryptPath, Path keyPath) throws IOException {
        wrong = true;
        while (wrong) {
            try {
                if(Files.notExists(encryptPath)){
                    Files.createFile(encryptPath);
                }
                if(Files.notExists(keyPath)) {
                    System.out.println("File key.txt is not exist, you must encrypt the file before");
                    System.out.println();
                    break;
                }
                if (Files.readString(encryptPath).length() != 0) {
                    System.out.println("Enter the path to the encrypted text");
                    scanner.nextLine();
                    String StringPathEncrypt = scanner.nextLine();
                    Path path1 = Path.of(StringPathEncrypt);
                    int key3 = Integer.parseInt(Files.readString(keyPath));
                    System.out.println();
                     object.setKey(key3);
                    System.out.println(object.decryptionMethod(Files.readString(path1)));
                } else {
                    System.out.println("Encrypted file is empty");
                    System.out.println("You must decrypt the file");
                    System.out.println();
                }
                wrong = false;

            }catch (NoSuchFileException n) {
                System.out.println("Wrong path to the file or not exist key.txt");
            }
            catch (InputMismatchException ex3) {
                System.out.println("You must enter right path to the encrypted text");
            }
        }
        wrong = true;
    }
    public static void brutForce(Path encryptPath, Path pathDict, int start) throws IOException{
        if(Files.notExists(encryptPath) || Files.readString(encryptPath).length() == 0) {
            System.out.println("Encryption file is not exist or empty,  you must encrypt the file before");
            return;
        }
        wrong = true;
        while (wrong) {
            try {
                System.out.println("Brute Force Method");
                System.out.println("___________________\n");
                System.out.println("Enter maximum volume of key");
                int key = scanner.nextInt();
                System.out.println("Enter size of alphabet");
                int size = scanner.nextInt();
                System.out.println("Enter first letter of alphabet");
                scanner.nextLine();
                String firstLetterString = scanner.nextLine();
                System.out.println("Enter the path to encrypted file");
                String pathToEncrypt = scanner.nextLine();
                Path path1 = Path.of(pathToEncrypt);
                int ok = 1;
                wrong = false;
                while (ok == 1) {
                    BruteForce bruteForce = new BruteForce();
                    System.out.println("Wait please...");
                    bruteForce.force(Files.readString(path1), start, key, size, pathDict, firstLetterString.charAt(0), object);
                    System.out.println("Everything is OK ?");
                    System.out.println("If the text is not accurate enter 1, else enter 0");
                    ok = scanner.nextInt();
                    start = bruteForce.returnKey() + 1;

                }
            } catch (InputMismatchException ex4) {
                System.out.println("You must enter the number");
                System.out.println();
                wrong = true;
            }catch (NoSuchFileException n) {
                System.out.println("Wrong path to the file");
                System.out.println();
                wrong = true;
            }
        }
        wrong = true;
    }


}