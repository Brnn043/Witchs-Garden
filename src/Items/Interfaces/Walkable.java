package Items.Interfaces;

public interface Walkable {
    // this interface implement on character that could move
    // use with int walkingSpeed, positionX, positionY
    float getPositionX();
    float getPositionY();
    float getSpeedRate();

    void setPositionY(float positionY);

    void setSpeedRate(float speedRate);
    void walk();
}
