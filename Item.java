public class Item extends GameComponent
        implements Collectible, Comparable<Item> {

  
    private int value;
  
    private String itemType; // KEY, TOOL, CLUE

    public Item(String name, int value, String itemType) {
        super(name);
        this.value = value;
        this.itemType = itemType;
    }

    public String getItemType() {
        return itemType;
    }

    @Override
    public void collect(Player p) {
        p.getInventory().add(this);
        System.out.println("Picked up: " + name);
    }

    @Override
    public int compareTo(Item o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public void inspect() {
        System.out.println("Item: " + name +
                " | Type: " + itemType +
                " | Value: " + value);
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

