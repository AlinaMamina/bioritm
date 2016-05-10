import database.DataBase;
import database.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import java.io.File;
import java.sql.SQLException;

public class DataBaseTest {
    private DataBase d;
    private User u;

    public DataBaseTest() {
        File file;
        if (!(file = new File("src\\main\\resources\\testbase.mv.db")).exists())
            throw new RuntimeException("File not found!");
        String str = file.getAbsolutePath();
        this.d = new DataBase(str.replace(".mv.db", ""));
        this.u = new User();
    }

    @Test
    public void test() throws SQLException, ClassNotFoundException {

        u.login = "Veron505";
        u.password = "12345";
        u.birthday = "04-02-1996";
        u.date = "23-04-2016";

        d.Connect();
        d.Add_user(u);
        d.Get_user_data(u);

        Assert.assertTrue("Пользователь не добавился", d.HasLogin(u));
        Assert.assertEquals("Пароль добавился неправильно", u.password, "12345");
        Assert.assertEquals("Дата рождения добавилась неправильно", u.birthday, "04-02-1996");
        Assert.assertEquals("Дата последнего захода добавилась неправильно", u.date, "23-04-2016");

        d.Change_data_last_call(u, "10-02-2012");
        d.Get_user_data(u);
        Assert.assertEquals("Дата последнего захода не изменилась", u.date, "10-02-2012");
        d.Disconnect();
    }
}