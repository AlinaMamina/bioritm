import calculation.CalcBiorithms;
import calculation.CalcCompatibility;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class TestForBiorhytms {


    @Test
    public void test() {
        Double[][] arrayOfBiorhythms;
        Double[] result_comp_1;
        Double[][] standard = {{-81.69, 0.0, -18.92, -33.54}};
        Double[] result_comp_2 = {100.0, 99.0,25.0, 74.66 };

        GregorianCalendar data = new GregorianCalendar(2016, Calendar.MARCH, 23);
        GregorianCalendar birthday = new GregorianCalendar(1995, Calendar.SEPTEMBER, 20);

        GregorianCalendar human_one = new GregorianCalendar(1995, Calendar.NOVEMBER, 1);
        GregorianCalendar human_two = new GregorianCalendar(1995, Calendar.FEBRUARY, 21);

        CalcBiorithms calc = new CalcBiorithms();
        CalcCompatibility comp = new CalcCompatibility();
        int count_of_days = 1;




        calc.calcResult(count_of_days, data, birthday);
        arrayOfBiorhythms = calc.getResult();


        result_comp_1 =  comp.calcCompatibility(human_one, human_two);

        Assert.assertTrue("All right with biorhythms", testAscendingBiorhythms(standard, arrayOfBiorhythms, count_of_days));
        Assert.assertTrue("All right with compatibility)", testAscendingComp(result_comp_1, result_comp_2));

    }

    private boolean testAscendingBiorhythms(Double[][] standard,Double[][] result, int count) {
        for (int i = 0; i < count; i++) {
            for(int j = 0; j < 4; j++){
                if (!result[i][j].equals(standard[i][j]) )
                    return false;
            }
        }
        return true;
    }

    private boolean testAscendingComp(Double[] result,Double[] standard) {
        for (int i = 0; i < 4; i++) {
            if (!result[i].equals(standard[i]))
                return false;
        }
        return true;
    }

}