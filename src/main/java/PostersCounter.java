import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.testng.Assert.assertEquals;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PostersCounter pc = new PostersCounter();
        pc.countFromeConsole(in);
    }
}


class PostersCounter {

    static String path = "src/main/resources/in/";
    int noPosters = 0;
    LinkedList<Integer> openPosters = new LinkedList<>();

    void countFromeConsole(BufferedReader in) {
        int noBuildings;
        try {
            noBuildings = Integer.parseInt(in.readLine());
            for (int i = 0; i < noBuildings; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                st.nextToken();
                countPostersAlgorithm(Integer.valueOf(st.nextToken()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        print(String.valueOf(noPosters));
    }

    int countFromFile(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path + fileName + ".in"));
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            st.nextToken();
            countPostersAlgorithm(Integer.valueOf(st.nextToken()));
        }
        print(String.valueOf(noPosters));
        return noPosters;
    }

    private void countPostersAlgorithm(int current) {

        while (openPosters.size() > 0 && openPosters.getFirst() > current) { //O(1)
            openPosters.pop(); //O(1)
        }
        if (openPosters.size() == 0 || openPosters.getFirst() < current) { //O(1)
            noPosters++;
            openPosters.push(current); //O(1)
        }
    }

    private static void print(String message) {
        System.out.println(message);
    }

}


class PostersCounterTest {
    String resultPath = "src/main/resources/out/";
    SortedSet<String> filesNames = new TreeSet<String>();
    PostersCounter pc = new PostersCounter();

    @Test
    public void Test() throws IOException {
        getFilesNames();
        long totalTime = 0;
        for (String name : filesNames) {
            totalTime += fileTest(name);
        }

        System.out.println("\n Total time for all files: " + totalTime + " ms.");
    }

    private void getFilesNames() {
        File[] files = new File(resultPath).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String[] list = file.getName().split(".out");
                filesNames.add(list[0]);
            }
        }
    }

    private long fileTest(String n) throws IOException {

        PostersCounter pc = new PostersCounter();
        long startTime = System.currentTimeMillis();
        assertEquals(pc.countFromFile(n), readFileOutput(n));
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Testing for: " + n + ".in" + " finished in: " + elapsedTime + " ms.");
        return elapsedTime;
    }

    private int readFileOutput(String name) throws IOException {
        BufferedReader out = new BufferedReader(new FileReader(resultPath + name + ".out"));
        int n = Integer.parseInt(out.readLine());
        return n;
    }
}

