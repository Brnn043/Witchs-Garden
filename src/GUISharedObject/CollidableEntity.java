package GUISharedObject;

// this is entity of the game that can be collided with
public class CollidableEntity extends Entity {

    protected double width;
    protected double height;

    protected CollidableEntity(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    protected CollidableEntity() { super(); }

    //check if this CollidableEntity is collided with the element which is given the position and size
    public boolean collideWith(double otherX,double otherY,double otherWidth,double otherHeight) {
        // Calculate the bounding box of this entity
        double thisLeft = getX() - getWidth() / 2;
        double thisRight = getX() + getWidth() / 2;
        double thisTop = getY() - getHeight() / 2;
        double thisBottom = getY() + getHeight() / 2;

        // Calculate the bounding box of the other entity
        double otherLeft = otherX - otherWidth / 2;
        double otherRight = otherX + otherWidth / 2;
        double otherTop = otherY - otherHeight / 2;
        double otherBottom = otherY + otherHeight / 2;

        // Check for collision
        return thisLeft < otherRight &&
                thisRight > otherLeft &&
                thisTop < otherBottom &&
                thisBottom > otherTop;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
