import java.util.*;

public class PointsList {

    public static void main(String[] args){

        List<Point> pointsList = new LinkedList<Point>();

        pointsList.add(new Point(7, 15));
        pointsList.add(new Point(2, 11));
        pointsList.add(new Point(5, 10));
        pointsList.add(new Point(6, 9));

        System.out.println("Not Sorted PointsList: " + pointsList);

        Collections.sort(pointsList);
 
        System.out.println("Sorted PointsList: " + pointsList);

    }
}