import java.util.*;

public class GameEngine {

   
    private Player player;

    private ArrayList<Room> map;

    private Queue<String> hintQueue;
    private int turnCount;
    
    private Scanner sc;

    public GameEngine(Player p, ArrayList<Room> map) {
        this.player = p;
        this.map = map;
        hintQueue = new LinkedList<>();
        sc = new Scanner(System.in);
    }

    @SuppressWarnings("UseSpecificCatch")
    public void start() {
        System.out.println("=== ESCAPE ROOM GAME ===");

        while (true) {
            try {
                printStatus();
                String cmd = sc.nextLine();
                processCommand(cmd);
                turnCount++;

                if (turnCount % 3 == 0 && !hintQueue.isEmpty()) {
                    System.out.println("HINT: " + hintQueue.poll());
                }

                if (winConditionCheck())
                    break;

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void processCommand(String cmd)
            throws InvalidCommandException, LockedRoomException {

        String[] parts = cmd.split(" ");

        switch (parts[0]) {
            case "look" -> player.getCurrentRoom().inspect();

            case "move" -> {
                for (Room r : player.getCurrentRoom().getConnectedRooms()) {
                    if (r.getName().equalsIgnoreCase(parts[1])) {
                        player.moveTo(r);
                        return;
                    }
                }
                throw new InvalidCommandException("Room not found");
            }

            case "back" -> player.goBack();

            case "inventory" -> {
                for (Item i : player.getInventory())
                    i.inspect();
            }

            case "map" -> map.get(0).exploreRecursive(0);

            case "pickup" -> {
                if (parts.length < 2) {
                    throw new InvalidCommandException("Pickup what?");
                }
                
                boolean found = false;
                Iterator<GameComponent> it =
                        player.getCurrentRoom().getContents().iterator();
                
                while (it.hasNext()) {
                    GameComponent gc = it.next();
                    
                    if (gc instanceof Item &&
                            gc.getName().equalsIgnoreCase(parts[1])) {
                        
                        Item item = (Item) gc;
                        item.collect(player);
                        it.remove(); // delete item in room
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    throw new InvalidCommandException("Item not found");
                }       }


            default -> throw new InvalidCommandException("Unknown command");
        }
    }

    private void printStatus() {
        System.out.println("\nCurrent room: " +
                player.getCurrentRoom().getName());
        System.out.print("> ");
    }

    private boolean winConditionCheck() {
        if (player.getCurrentRoom().isExit()) {
            System.out.println("YOU ESCAPED!");
            return true;
        }
        return false;
    }

    public Queue<String> getHintQueue() {
        return hintQueue;
    }

    public void setHintQueue(Queue<String> hintQueue) {
        this.hintQueue = hintQueue;
    }

    public ArrayList<Room> getMap() {
        return map;
    }

    public void setMap(ArrayList<Room> map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public Scanner getSc() {
        return sc;
    }
}
