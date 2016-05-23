package calculation;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalcCompatibility {

    private final double[] table = {
            100, 99, 99, 99, 98, 98, 96, 95, 95, 92, 88, 88, 85, 83, 80, 78, 78, 78, 70, 70,
            70, 60, 57, 57, 57, 50, 43, 43, 40, 36, 30, 30, 25, 25, 22, 17, 17, 15, 15, 15,
            8, 4, 4, 4, 3, 2, 1, 0.5, 0.5, 0, 0, 0.5, 1, 1, 2, 3, 4, 8, 8, 8,
            15, 15, 15, 17, 22, 22, 25, 30, 30, 36, 36, 40, 43, 48, 48, 50, 57, 60, 70, 70,
            70, 78, 78, 78, 80, 83, 85, 88, 92, 92, 95, 95, 96, 98, 98, 99, 99, 99, 99};

    private final double phaseEmotion = 23;
    private final double phasePhysical = 28;
    private final double phaseIntellect = 33;


    public Double[] calcCompatibility(GregorianCalendar human_one, GregorianCalendar human_two) {
        Double[] result = new Double[4];
        int period = countPeriod(human_one, human_two);
        result[0] = (int) (table[getEmotionalCompatibility(period)] * 100) / 100.0;
        result[1] = (int) (table[getPhysicalCompatibility(period)] * 100) / 100.0;
        result[2] = (int) (table[getIntellectualCompatibility(period)] * 100) / 100.0;
        result[3] = (int) (((result[0] + result[1] + result[2]) / 3) * 100) / 100.0;
        return result;
    }


    private int getEmotionalCompatibility(int period) {
        return (int) ((period / phaseEmotion - Math.floor(period / phaseEmotion)) * 100);
    }

    private int getPhysicalCompatibility(int period) {
        return (int) ((period / phasePhysical - Math.floor(period / phasePhysical)) * 100);
    }

    private int getIntellectualCompatibility(int period) {
        return (int) ((period / phaseIntellect - Math.floor(period / phaseIntellect)) * 100);
    }


    private int countPeriod(GregorianCalendar human_one, GregorianCalendar human_two) {

        int year;
        GregorianCalendar day;
        if (human_one.after(human_two))
            day = human_two;
        else day = human_one;

        int period = human_two.get(Calendar.DAY_OF_YEAR) - human_one.get(Calendar.DAY_OF_YEAR);
        //period = human_two.get(Calendar.SECOND) - human_one.get(Calendar.SECOND)
        for (int i = day.get(Calendar.YEAR); i < human_two.get(Calendar.YEAR); ++i) {
            period += 365;
            year = day.get(Calendar.YEAR);
            if ((year % 4) == 0) {
                period++;
            }
            day.add(Calendar.YEAR, 1);
        }
        return Math.abs(period);

    }

}
