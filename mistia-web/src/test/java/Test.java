
import java.math.BigDecimal;

public class Test {

    public static void main(String[] args) {

        BigDecimal test = new BigDecimal(0);
        System.out.println(test);
        test = test.add(new BigDecimal(30));
        System.out.println(test);
        test = test.add(new BigDecimal(-0.00125));
        System.out.println(test);

    }

}
