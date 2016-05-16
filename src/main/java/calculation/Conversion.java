package calculation;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Conversion {

    public  GregorianCalendar toCalendar(Object day, Object month, Object year) throws NullPointerException,IllegalArgumentException
    {
        GregorianCalendar data = new GregorianCalendar();
        data.setLenient(false);
        data.set(Integer.valueOf(year.toString()),Integer.valueOf(month.toString())-1,Integer.valueOf(day.toString()));
        return  data;
    }

    public  String[] dataToString (GregorianCalendar[] a, int count)
    {
        String[] str = new String[count];
        for( int i = 0; i< count;i++)
            str[i]=String.valueOf(a[i].get(Calendar.DAY_OF_MONTH))+"."+String.valueOf(a[i].get(Calendar.MONTH)+1)+"."+String.valueOf(a[i].get(Calendar.YEAR));
      return str;
    }

    public  String bioritmToString (Double[] a)
    {
        String str = new String();
        str = "Физический - "+a[0].toString()+"%."+'\n'+"Эмоциональный - "+a[1].toString()+"%."+'\n'+" Интеллектуальный - "+a[2].toString()+
                "%."+'\n'+" Общий -"+a[3].toString()+"%.";
        return str;
    }

}
