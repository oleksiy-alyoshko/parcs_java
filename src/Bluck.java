import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import parcs.*;

public class Bluck {


    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("MyClass.jar");
        List<String> data = readData(curtask.findFile("input"));
        int n_workers = Integer.parseInt(data.get(0));

        AMInfo info = new AMInfo(curtask, null);
        List<channel> channels = new ArrayList<>();
        for (int i = 0; i < n_workers; i++) {
            point p = info.createPoint();
            channel c = p.createChannel();
            channels.add(c);
            p.execute("MyClass");
        }
        long startTime = System.nanoTime();

        int step = data.size() / n_workers;

        for (int i = 0; i < n_workers; i++) {
            int first = step * i + 1;
            int last = i == n_workers - 1 ? data.size() : step * (i + 1) + 1;
            String[] row = new String[last - first];
            for (int j = first; j < last; j++) {
                row[j - first] = data.get(j);
            }
            System.out.println("data sent is " + Arrays.toString(row));
            channels.get(i).write(row);
        }

        System.out.println(((System.nanoTime() - startTime) / 1000000) + " ms took");

        List<String> answer = new ArrayList<>();

        for (int i = 0; i < n_workers; i++) {
            String[] ans = (String[]) channels.get(i).readObject();
            System.out.println("Ans from " + i + " channel is " + Arrays.toString(ans));
            answer.addAll(Arrays.asList(ans));
        }
        answer.forEach(System.out::println);
        System.out.println("End of task");
        curtask.end();
    }

    public static List<String> readData(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));
        List<String> data = new ArrayList<>();
        while (sc.hasNext()) {
            data.add(sc.next());
        }
        return data;
    }
}
