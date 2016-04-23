package pathfinders;

import model.Field;
import model.Node;
import model.Path;
import model.Tuple;

import java.util.ArrayList;

/**
 * Created by ericd on 2/20/16.
 */
public abstract class PathFinder {
    private int nodes_searched;
    public abstract Path findPath(Node start, Node finish);
    public abstract ArrayList<Node> findAdjacent(Node curr);
    protected Field field_obj;
    protected Path path;
    protected boolean dir8;
    protected boolean hurdle;
    public PathFinder(Field field_obj)
    {
        this.nodes_searched = 0;
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
        Tuple pos1, pos2;
        if(this.usingWorldPos())
        {
            pos1 = one.getWorldPos();
            pos2 = two.getWorldPos();
        }
        else
        {
            pos1 = one.getPos();
            pos2 = two.getPos();
        }
        int dx = Math.abs(pos1.x - pos2.x);
        int dy = Math.abs(pos1.y - pos2.y);
        //return dx + dy;
        return (int)Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
    }

    public int getPseudoDistance(Node one, Node two)
    {
        Tuple pos1, pos2;
        if(this.usingWorldPos())
        {
            pos1 = one.getWorldPos();
            pos2 = two.getWorldPos();
        }
        else
        {
            pos1 = one.getPos();
            pos2 = two.getPos();
        }
        int dx = Math.abs(pos1.x - pos2.x);
        int dy = Math.abs(pos1.y - pos2.y);
        return dx+dy;
    }

    public int getNodesSearched()
    {
        return this.nodes_searched;
    }

    protected void nodeSearched()
    {
        this.nodes_searched++;
    }


    public boolean usingWorldPos() {
        return use_world_pos;
    }

    public void useWorldPos(boolean use_world_pos) {
        this.use_world_pos = use_world_pos;
    }

    private boolean use_world_pos;

    public void reset()
    {
        this.nodes_searched = 0;
    }
}
