package Items.Interfaces;

public interface Collectable {
    void setPositionX(float positionX);

    void setPositionY(float positionY);


    // when create item, randomly set positionX and positionY
    void spawnOnMap();
    // when player collect item
    void collected();
    // check if it collected
    boolean isCollected();
    public float getPositionX();
    public float getPositionY();
}
