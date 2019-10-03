import java.security.SecureRandom;

public class Test {
    public static void main(String[] args)
    {
//        SecureRandom random = new SecureRandom();
//        byte bytes[] = new byte[20];
//        random.nextBytes(bytes);
//        String token = bytes.toString();
//        System.out.println(token);
         final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
         SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder( 15 );
        for( int i = 0; i < 15; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        System.out.println(sb.toString());

    }
}
