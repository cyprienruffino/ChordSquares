package ovh.cyprienruffino.chordsquares;

public class Utils {
    public static int mod(int x, int y)
    {
        int result = x % y;
        if (result < 0)
        {
            result += y;
        }
        return result;
    }
}
