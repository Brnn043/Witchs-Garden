package Items.Interfaces;

public interface Walkable {
    // this interface implement on character that could move
    // use with int walkingSpeed, positionX, positionY
    float getSpeedRate();
    void setSpeedRate(float speedRate);
    void walk();
}
