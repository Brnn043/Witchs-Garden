package Items.Interfaces;

public interface Walkable {
    // this interface implement on character that could move
    // use with int walkingSpeed, positionX, positionY
    int getPositionX();
    int getPositionY();
    float getSpeedRate();
    void setPositionX(int positionX);
    void setPositionY(int positionY);
    void setSpeedRate(float speedRate);
    void walk();
}
