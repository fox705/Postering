import org.junit.jupiter.api.Test;
import org.testng.AssertJUnit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

import static org.testng.Assert.assertEquals;


public class PlakatowanieTest {

    Plakatowanie p = new Plakatowanie();
    String path = "src/main/resources/in/";
    String resultPath = "src/main/resources/out/";

    @Test
    public void TestAlgo() {
        try {
            assertEquals(readFileInputAlgo("pla4ocen"), readFileOutput("pla4ocen"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestPlacatowanie() {
        try {
            AssertJUnit.assertEquals(readFileInput("pla0"), readFileOutput("pla0"));
            AssertJUnit.assertEquals(readFileInput("pla1b"), readFileOutput("pla1b"));
            AssertJUnit.assertEquals(readFileInput("pla1c"), readFileOutput("pla1c"));
            AssertJUnit.assertEquals(readFileInput("pla1ocen"), readFileOutput("pla1ocen"));
            AssertJUnit.assertEquals(readFileInput("pla2a"), readFileOutput("pla2a"));
            AssertJUnit.assertEquals(readFileInput("pla2b"), readFileOutput("pla2b"));
            AssertJUnit.assertEquals(readFileInput("pla2c"), readFileOutput("pla2c"));
            AssertJUnit.assertEquals(readFileInput("pla2ocen"), readFileOutput("pla2ocen"));
            AssertJUnit.assertEquals(readFileInput("pla3a"), readFileOutput("pla3a"));
            AssertJUnit.assertEquals(readFileInput("pla3b"), readFileOutput("pla3b"));
            AssertJUnit.assertEquals(readFileInput("pla3c"), readFileOutput("pla3c"));
            AssertJUnit.assertEquals(readFileInput("pla3ocen"), readFileOutput("pla3ocen"));
            AssertJUnit.assertEquals(readFileInput("pla4a"), readFileOutput("pla4a"));
            AssertJUnit.assertEquals(readFileInput("pla4b"), readFileOutput("pla4b"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test4() {
        try {
            AssertJUnit.assertEquals(readFileInput("pla4ocen"), readFileOutput("pla4ocen"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test5() {

        try {
            AssertJUnit.assertEquals(readFileInput("pla5a"), readFileOutput("pla5a"));
            AssertJUnit.assertEquals(readFileInput("pla5b"), readFileOutput("pla5b"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test6() {
        try {
            AssertJUnit.assertEquals(readFileInput("pla6a"), readFileOutput("pla6a"));
            AssertJUnit.assertEquals(readFileInput("pla6b"), readFileOutput("pla6b"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test7() {
        try {
            AssertJUnit.assertEquals(readFileInput("pla7b"), readFileOutput("pla7b"));
            AssertJUnit.assertEquals(readFileInput("pla7a"), readFileOutput("pla7a"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int readFileInput(String name) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path + name + ".in"));
        int result = Plakatowanie.Postering(in);
        return result;
    }

    public int readFileInputAlgo(String name) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path + name + ".in"));
        int n = Integer.parseInt(in.readLine());
        LinkedList<Integer> ints = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            st.nextToken();
            ints.add(Integer.valueOf(st.nextToken()));

        }
        int result = Algorithm.countPosters(ints);
        return result;
    }

    public int readFileOutput(String name) throws IOException {
        BufferedReader out = new BufferedReader(new FileReader(resultPath + name + ".out"));
        int n = Integer.parseInt(out.readLine());
        return n;
    }

}
