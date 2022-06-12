import java.util.List;

public class Rectangle extends Shape {

    private int height;
    private int width;
    
    public Rectangle(){
        this(0, 0);
    }
    public Rectangle(int height, int width){
        this.height = height;
        this.width = width;
    }

    @Override
    public void draw(){
        // DO STUFF
    }



}