package Items.Interfaces;

public interface Collectable {
    void setPositionX(int positionX);

    void setPositionY(int positionY);

    // when create item, randomly set positionX and positionY
    void spawnOnMap();
    // when player collect item
    void collected();
    // check if it collected
    boolean isCollected();
    public int getPositionX();
    public int getPositionY();
}
