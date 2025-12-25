import java.util.ArrayList;
public class Room extends GameComponent {

    private ArrayList<GameComponent> contents;
    private ArrayList<Room> connectedRooms;
    private boolean isExit;
    private String requiredKey;

    public Room(String name, boolean isExit){
        super(name);
        this.isExit=isExit;
        contents = new ArrayList<>();
        connectedRooms= new ArrayList<>();
    }
    public void setRequiredKey(String key) {
        this.requiredKey = key;
    }

    public boolean requiresKey() {
        return requiredKey != null;
    }

    public String getRequiredKey() {
        return requiredKey;
    }

    public boolean isExit() {
        return isExit;
    }

    public void addContent(GameComponent gc) {
        contents.add(gc);
    }

    public void connectRoom(Room r) {
        connectedRooms.add(r);
    }

    public ArrayList<GameComponent> getContents() {
        return contents;
    }

    public ArrayList<Room> getConnectedRooms() {
        return connectedRooms;
    }

    // RECURSION 

    public void exploreRecursive(int depth) {
        for (int i = 0; i < depth; i++) System.out.print("  ");
        System.out.println("- Room: " + name);

        for (GameComponent gc : contents) {
            for (int i = 0; i < depth + 1; i++) System.out.print("  ");
            System.out.println("* " + gc.getName());
        }

        for (Room r : connectedRooms) {
            r.exploreRecursive(depth + 1);
        }
    }

    public boolean containsItemRecursive(String itemName) {
        for (GameComponent gc : contents) {
            if (gc instanceof Item &&
                    gc.getName().equalsIgnoreCase(itemName))
                return true;
        }
        for (Room r : connectedRooms) {
            if (r.containsItemRecursive(itemName))
                return true;
        }
        return false;
    }

    public int maxDepthRecursive() {
        int max = 0;
        for (Room r : connectedRooms) {
            max = Math.max(max, r.maxDepthRecursive());
        }
        return max + 1;
    }

    @Override
    public void inspect() {
        System.out.println("You are in room: " + name);
    }

    public void setContents(ArrayList<GameComponent> contents) {
        this.contents = contents;
    }

    public void setConnectedRooms(ArrayList<Room> connectedRooms) {
        this.connectedRooms = connectedRooms;
    }

    public boolean isIsExit() {
        return isExit;
    }

    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }
}

