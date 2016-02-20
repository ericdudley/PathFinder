import java.util.ArrayList;

/**
 * Created by ericd on 2/20/16.
 */
public abstract class PathFinder {
    public abstract Path findPath(Node start, Node finish);
    public abstract ArrayList<Node> findAdjacent(Node curr);
    protected Field field_obj;
    protected Path path;
    protected boolean dir8;
    protected boolean hurdle;
    public PathFinder(Field field_obj)
    {
        this.field_obj = field_obj;
    }
    public void setPath(Path path)
    {
        this.path = path;
    }

    public void setFieldObj(Field field_obj)
    {
        this.field_obj = field_obj;
    }

    public Field getFieldObj()
    {
        return this.field_obj;
    }

    public Node[][] getField()
    {
        return this.field_obj.getField();
    }
    public void setDir8(boolean val)
    {
        this.dir8 = val;
    }

    public void setHurdle(boolean val)
    {
        this.hurdle = val;
    }

    public int getDistance(Node one, Node two)
    {
        int dx = Math.abs(one.getPos().x - two.getPos().x);
        int dy = Math.abs(one.getPos().y - two.getPos().y);
        //return dx + dy;
        return (int)Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
    }
}
