import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;


public class Plakatowanie {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int result = Postering(in);
        print(String.valueOf(result));
    }

    public static int Postering(BufferedReader in) throws IOException {
        return countPosters(new City(in));
    }

    public static int countPosters(City city) {
        long startTime = System.currentTimeMillis();
        Building current = null;
        int noPosters = 0;
        LinkedList<Building> openPosters = new LinkedList<>();

        for (int i = 0; i < city.buildings.size(); ++i) {
            current = city.buildings.get(i);
            while (openPosters.size() > 0 && openPosters.getFirst().high > current.high) {
                noPosters += openPosters.stream().filter(isHigherThan(current.high)).distinct().count();
                openPosters.removeIf(isHigherThan(current.high));
            }
            if (openPosters.size() == 0 || openPosters.getLast().high < current.high) {
                openPosters.push(current);
            }
        }
        noPosters += openPosters.stream().distinct().count();

        long elapsedTime = System.currentTimeMillis() - startTime;

        print("Total execution time: " + elapsedTime + " ms");
        return noPosters;
    }


    private static Predicate<Building> isHigherThan(int high) {
        return b -> b.high > high;
    }

    private static void print(String message) {
        System.out.println(message);
    }
}

class City {

    LinkedList<Building> buildings;

    public City(BufferedReader in) throws IOException {
        AssignBuildings(in);
    }

    private void AssignBuildings(BufferedReader in) throws IOException {

        buildings = new LinkedList<Building>();

        int no = Integer.parseInt(in.readLine());

        for (int i = 0; i < no; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            while (st.hasMoreElements()) {
                buildings.add(new Building(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
        }

        in.close();
    }

}

class Building {

    int lenght;
    int high;

    public Building(int lenght, int high) {
        this.lenght = lenght;
        this.high = high;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return high == building.high;
    }

    @Override
    public int hashCode() {
        return Objects.hash(high);
    }
}
