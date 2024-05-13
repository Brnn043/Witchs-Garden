package Item.Interface;

// this interface implement on item that can be collected
public interface Collectable {
    // when create item, randomly set positionX and positionY
    void spawnOnMap();
    // when player collect item
    void collected();
    // check if it collected
    boolean isCollected();
}
