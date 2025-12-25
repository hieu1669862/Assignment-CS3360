import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Room start = new Room("Cell", false);
        Room hall = new Room("Hall", false);
        Room exit = new Room("Exit", true);
        exit.setRequiredKey("GoldenKey");

        Item key = new Item("GoldenKey", 10, "KEY");
        start.addContent(key);

        start.connectRoom(hall);
        hall.connectRoom(exit);

        Player p = new Player(start);

        ArrayList<Room> map = new ArrayList<>();
        map.add(start);

        GameEngine engine = new GameEngine(p, map);
        engine.start();
    }
}
