package GUISharedObject;

public class CollidableEntity extends Entity {
    protected double width;
    protected double height;

    protected CollidableEntity(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    protected CollidableEntity(double width,double height){
        super();
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

    public boolean collideWith(CollidableEntity other) {
        // Calculate the bounding box of this entity
        double thisLeft = getX();
        double thisRight = getX() + width;
        double thisTop = getY();
        double thisBottom = getY() + height;

        // Calculate the bounding box of the other entity
        double otherLeft = other.getX();
        double otherRight = other.getX() + other.getWidth();
        double otherTop = other.getY();
        double otherBottom = other.getY() + other.getHeight();

        // Check for collision
        return thisLeft < otherRight &&
                thisRight > otherLeft &&
                thisTop < otherBottom &&
                thisBottom > otherTop;
    }
}
