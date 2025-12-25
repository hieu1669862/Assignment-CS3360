public abstract class GameComponent {
    protected String name;

    public GameComponent(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void inspect();
}
