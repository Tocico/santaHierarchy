import java.util.List;

/**
 * Created by Toshiko Kuno
 * Date: 2021/01/21
 * Time: 18:54
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class Worker {
   protected String name;
   protected List<String> staff;

   public Worker(String name, List<String> staff) {
       this.name = name;
       this.staff = staff;
   }

    public List<String> getStaff() {
        return staff;
    }

    public String getName() {
        return name;
    }
}
