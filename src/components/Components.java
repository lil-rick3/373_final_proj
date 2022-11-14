package components;

public abstract class Components {

    protected double xloc;
	protected double yloc;
	protected int width;
	protected int height;

    public Components() {
        this.xloc = 0.0;
        this.yloc = 0.0;
        this.width = 0;
        this.height = 0;
    }
    
    public double getXloc() {
        return xloc;
    }

    public double getYloc() {
        return yloc;
    }
}
