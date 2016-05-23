package calculation;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.lang.Math;

public class CalcBiorithms {

    private Double[][] arrayOfBiorhythms = new Double[14][4];
    private GregorianCalendar[] best = new GregorianCalendar[4];
    private GregorianCalendar[] table_data = new GregorianCalendar[14];
    private GregorianCalendar birthday = new GregorianCalendar();
    private int count;
    private int time;
    private final double phaseEmotion = 23;
    private final double phasePhysical = 28;
    private final double phaseIntellect = 33;

    public double getEmotionalBiorhythms(int period) {
        return (Math.sin(2 * Math.PI * period / phaseEmotion)) * 100;
    }

    public double getPhysicalBiorhythms(int period) {
        return (Math.sin(2 * Math.PI * period / phasePhysical)) * 100;
    }

    public double getIntellectualBiorhythms(int period) {
        return (Math.sin(2 * Math.PI * period / phaseIntellect)) * 100;
    }

    public double getAllBiorhythms(int period) {
        return (getIntellectualBiorhythms(period) + getPhysicalBiorhythms(period) + getEmotionalBiorhythms(period)) / 3;
    }

    private void setArrayOfBiorhythms(int i, int period) {
        arrayOfBiorhythms[i][0] = (int) (getEmotionalBiorhythms(period) * 100) / 100.0;
        arrayOfBiorhythms[i][1] = (int) (getPhysicalBiorhythms(period) * 100) / 100.0;
        arrayOfBiorhythms[i][2] = (int) (getIntellectualBiorhythms(period) * 100) / 100.0;
        arrayOfBiorhythms[i][3] = (int) (getAllBiorhythms(period) * 100) / 100.0;
    }


    public void calcResult(int count_of_day, GregorianCalendar data, GregorianCalendar birthday) {

        this.birthday.set(birthday.get(Calendar.YEAR), birthday.get(Calendar.MONTH), birthday.get(Calendar.DAY_OF_MONTH));
        this.count = count_of_day;
        int period = countPeriod(data, birthday);
        setArrayOfBiorhythms(0, period);

        table_data[0] = new GregorianCalendar();
        table_data[0].set(data.get(Calendar.YEAR), data.get(Calendar.MONTH), data.get(Calendar.DAY_OF_MONTH));

        for (int i = 1; i < count_of_day; ++i) {
            period++;
            setArrayOfBiorhythms(i, period);
            data.add(Calendar.DATE, 1);
            table_data[i] = new GregorianCalendar();
            table_data[i].set(data.get(Calendar.YEAR), data.get(Calendar.MONTH), data.get(Calendar.DAY_OF_MONTH));

        }
    }


    public Double[][] getResult() {
        return arrayOfBiorhythms;
    }

    public GregorianCalendar[] getData() {
        return table_data;
    }

    public GregorianCalendar getBirthday() {
        return birthday;
    }

    public GregorianCalendar[] getBest() {
        return best;
    }

    public int getCount() {
        return count;
    }

    public int getPeriod() {
        return time;
    }

    public void calcBest(int count_of_day) {
        Double[] bestBiorirm = new Double[best.length];


        for (int i = 0; i < best.length; ++i) {
            bestBiorirm[i] = arrayOfBiorhythms[0][i];
            best[i] = new GregorianCalendar();
            best[i] = table_data[0];
        }
        for (int j = 0; j < best.length; ++j) {
            for (int i = 1; i < count_of_day; ++i) {

                if (arrayOfBiorhythms[i][j] > bestBiorirm[j]) {
                    bestBiorirm[j] = arrayOfBiorhythms[i][j];
                    best[j] = table_data[i];
                }
            }
        }
    }


    private int countPeriod(GregorianCalendar data, GregorianCalendar birthday) {
        int period = 0;
        int year;
        GregorianCalendar day = birthday;

        period = data.get(Calendar.DAY_OF_YEAR) - birthday.get(Calendar.DAY_OF_YEAR);//разница между днями

        // в цикле идём от текущего года, до того момента,на который вычисляем биоритм
        for (int i = day.get(Calendar.YEAR); i < data.get(Calendar.YEAR); ++i) {
            period += 365;
            year = day.get(Calendar.YEAR);
            if ((year % 4) == 0) {
                period++;
            }
            day.add(Calendar.YEAR, 1);//сдвигаемся на год
        }
        time = period;
        return period;
    }


}
