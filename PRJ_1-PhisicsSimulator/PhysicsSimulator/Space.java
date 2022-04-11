import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Space extends JFrame {

    public Ball ball;
    public long spaceTime;
    private final int timeFrame = 100;

    public Space(){
        this.spaceTime = 0;

        ball = new Ball(100, 100);

        this.setSize(500, 500);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        this.add(ball);
        this.setVisible(true);

        initEnvironment();
    }

    private void initEnvironment() {
        ball.applyGravityForce();
        try{
            while(true){
                TimeUnit.MILLISECONDS.sleep(this.timeFrame);
                this.increaseTime(this.timeFrame);
                ball.update(this.spaceTime);
            }            
        }catch(InterruptedException e){
            System.out.println("Error");
        }
    }

    private void increaseTime(int dTime){
        if(dTime>0){
            this.spaceTime += dTime;
        }
    }

}