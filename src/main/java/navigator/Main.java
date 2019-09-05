package navigator;

public class Main {

    public static void main(String[] args) {
        Route route = new Navigator().getFastestRoute(new NavigationMap(), Location.GARDEN, Location.CASTLE, true);
        System.out.println(route);
    }

}
