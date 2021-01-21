import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by Toshiko Kuno
 * Date: 2021/01/21
 * Time: 18:52
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class DAO {

    public static List<Worker> getAllWorkers(Path path) {
        List<Worker> workerList = new ArrayList<>();

        try (Scanner in = new Scanner(path)) {
            while (in.hasNext()) {
                List<String> staff = new ArrayList<>(Arrays.asList(in.next().split(",")));
                String chef = staff.get(0);
                staff.remove(0);

                Worker w = new Worker(chef, staff);
                workerList.add(w);
            }
        } catch (IOException e) {
            System.out.println("Det gick inte att läsa in");
            e.printStackTrace();
            System.exit(0);
        }
        return workerList;
    }
}
