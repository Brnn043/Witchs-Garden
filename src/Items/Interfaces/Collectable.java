package Items.Interfaces;

public interface Collectable {
    // when create item, randomly set positionX and positionY
    void spawnOnMap();
    // when player collect item
    void collected();
    // check if it collected
    boolean isCollected();
}
