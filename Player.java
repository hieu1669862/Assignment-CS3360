import java.util.ArrayList;
import java.util.Stack;

public class Player {

 
    private Stack<Room> moveHistory;

    private ArrayList<Item> inventory;
    private Room currentRoom;

    public Player(Room start) {
        currentRoom = start;
        moveHistory = new Stack<>();
        inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void moveTo(Room r) throws LockedRoomException {
        if (r.requiresKey() && !hasKey(r.getRequiredKey())) {
            throw new LockedRoomException("Room locked! Need key: " + r.getRequiredKey());
        }
        moveHistory.push(currentRoom);
        currentRoom = r;
    }

    public void goBack() {
        if (!moveHistory.isEmpty())
            currentRoom = moveHistory.pop();
    }

    public boolean hasKey(String keyName) {
        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(keyName))
                return true;
        }
        return false;
    }

    public Stack<Room> getMoveHistory() {
        return moveHistory;
    }

    public void setMoveHistory(Stack<Room> moveHistory) {
        this.moveHistory = moveHistory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
}
