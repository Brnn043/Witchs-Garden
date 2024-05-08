package GUISharedObject;

public class CollidableEntity extends Entity {
    protected double width;
    protected double height;

    protected CollidableEntity(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
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

    public boolean collideWith(double otherLeft,double otherTop,double otherWidth,double otherHeight) {
        // Calculate the bounding box of this entity
        double thisLeft = getX();
        double thisRight = getX() + width;
        double thisTop = getY();
        double thisBottom = getY() + height;

        // Calculate the bounding box of the other entity
        double otherRight = otherLeft + otherWidth;
        double otherBottom = otherTop + otherHeight;

        // Check for collision
        return thisLeft < otherRight &&
                thisRight > otherLeft &&
                thisTop < otherBottom &&
                thisBottom > otherTop;
    }

}
