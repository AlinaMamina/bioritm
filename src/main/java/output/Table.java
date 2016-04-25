package output;


import java.util.GregorianCalendar;

public class Table {
    private String data;
    private String result;

    public Table(String data, String result) {
        this.data = data;
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public String getResult() {
        return result;
    }
}
