package ProjectPackage;


public interface CryptInterface {
    void setKey(int key);
    String encryptionMethod (String text);
    String decryptionMethod (String text);
}
