
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author javadeveloper
 */
public class Test3 {

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if(day ==  Calendar.SATURDAY){
            System.out.println("El dia es "+day);
        }
        System.out.println(day);

    }

}
