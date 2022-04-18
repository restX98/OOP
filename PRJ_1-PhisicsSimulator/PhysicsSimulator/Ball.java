import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Ball extends JComponent {
    
    private int diameter;
    private float x;
    private float y;
    private float speedY;
    private float accelerationY;
    private float mass;
    private long time;

    public Ball() {
        this(100, 100, 20);
    }

    public Ball(float x, float y) {
        this(x, y, 20);
    }

    public Ball(float x, float y, int diameter){
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.speedY = 0;
        this.accelerationY = 0;
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(Space.metersToPixels(x, 0), Space.metersToPixels(y, 0), diameter, diameter);
    }

    public void moveBall(float dx, float dy) {
        System.out.println("dx: " + dx + ", dy: " + dy);
        this.x += dx;
        this.y += dy;
        this.repaint();
    }

    public void update(long time){
        System.out.println("Time: " + getTimeInSecond(time));
        updateSpeed(time);
        updateY(time);
        
        this.moveBall(0, (y - this.y));

        this.speedY = speedY;
        this.y = y;
        this.time = time;
        
    }

    public void applyGravityForce(){
        this.accelerationY = 9.81F;
    }

    // Getter & Setter
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getYY(){
        return this.y;
    }
    public void setDiameter(int diameter){
        //this.diameter = diameter;
    }
    public int getDiameter(){
        return this.diameter;
    }

    public void setSpeedY(float speed) {
        this.speedY = speed;
    }

    private void updateSpeed(long time){
        float speed = this.speedY + (this.accelerationY * (getTimeInSecond(time)
                                                        - getTimeInSecond(this.time)));
        System.out.println("Speed: " + speed);
        this.setSpeedY(speed);
    }

    private void updateY(long time){
        float y = this.y + (speedY * (getTimeInSecond(time) - getTimeInSecond(this.time)))
                + (this.accelerationY * (float)(Math.pow(getTimeInSecond(time), 2) - Math.pow(getTimeInSecond(this.time), 2)));
        System.out.println("Y: " + y);
        this.setY(y);
    }

    private float getTimeInSecond(long time){
        float newTime = time / 1000F;
        System.out.println("new Time dio cane: " + time / 1000F);
        return newTime;
    }
    
}