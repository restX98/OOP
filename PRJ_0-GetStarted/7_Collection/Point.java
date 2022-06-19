public class Point implements Comparable<Point>{

    protected int x;
    protected int y;

    public Point(){
        this(0, 0);
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p){
        return (int)(Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2))
                    - Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2)));
    }

    @Override
    public String toString(){
        return this.getClass().getName() + "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (!(o instanceof Point)) return false;
        Point p = (Point)o;
        return this.x == p.x && this.y == p.y;
    }
}
