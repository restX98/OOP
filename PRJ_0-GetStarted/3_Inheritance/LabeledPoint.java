public class LabeledPoint extends Point{

    private String label;
    
    public LabeledPoint(){
        this("NoName");
    }

    public LabeledPoint(String label){
        super(); // can be omitted
        this.label = label;
    }

    public LabeledPoint(int x, int y){
        this("NoName");
    }

    public LabeledPoint(String label, int x, int y){
        super(x, y);
        this.label = label;
    }

    @Override
    public String toString(){
        return this.getClass().getName() + "(" + x + ", " + y + ", " + label + ")";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (!(o instanceof LabeledPoint)) return false;

        LabeledPoint p = (LabeledPoint)o;
        return this.x == p.x && this.y == p.y && this.label == p.label;
    }


    public void setLabel(String label){
        this.label = label;
    }
    public String getLabel(){
        return this.label;
    }

}