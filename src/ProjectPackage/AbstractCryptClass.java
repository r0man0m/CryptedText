package ProjectPackage;

public abstract class AbstractCryptClass implements CryptInterface{

    public abstract void setAlphabet();

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
