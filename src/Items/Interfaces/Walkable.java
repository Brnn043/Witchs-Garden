package Items.Interfaces;

// this interface implement on character that could move
public interface Walkable {
    // use with int walkingSpeed, positionX, positionY
    float getSpeedRate();
    void setSpeedRate(float speedRate);
    void walk();
}
