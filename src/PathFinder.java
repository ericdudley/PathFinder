import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ericd on 2/16/2016.
 */
public class PathFinder {
    public static boolean dir8 = false;
    public static boolean vaulting = false;
    public static void addAdjacent(ArrayList<Node> list, Node curr, Field field_obj)
    {
        Node[][] field = field_obj.getField();
        int x = curr.getPos().x;
        int y = curr.getPos().y;
        if(y+1 < field_obj.getHeight() && !field[y+1][x].isSolid())
        {
            list.add(field[y+1][x]);
        }
        if(x+1 < field_obj.getWidth() && !field[y][x+1].isSolid())
        {
            list.add(field[y][x+1]);
        }
        if(y-1 > -1 && !field[y-1][x].isSolid())
        {
            list.add(field[y-1][x]);
        }
        if(x-1 > -1 && !field[y][x-1].isSolid())
        {
            list.add(field[y][x-1]);
        }
        if(dir8)
        {
            if(y+1 < field_obj.getHeight() && x+1 < field_obj.getWidth() && !field[y+1][x+1].isSolid())
            {
                list.add(field[y+1][x+1]);
            }
            if(x-1 > -1 && y-1 > -1 && !field[y-1][x-1].isSolid())
            {
                list.add(field[y-1][x-1]);
            }
            if(y-1 > -1 && x+1 < field_obj.getWidth() && !field[y-1][x+1].isSolid())
            {
                list.add(field[y-1][x+1]);
            }
            if(y+1 < field_obj.getHeight() && x-1 > -1 && !field[y+1][x-1].isSolid())
            {
                list.add(field[y+1][x-1]);
            }
        }
        if(vaulting)
        {
            if(x+1 < field_obj.getWidth() && field[y][x+1].isSolid() &&
                    x+2 < field_obj.getWidth() && !field[y][x+2].isSolid())
            {
                list.add(field[y][x+2]);
            }
            if(x-1 > 0 && field[y][x-1].isSolid() &&
                    x-2 > 0 && !field[y][x-2].isSolid())
            {
                list.add(field[y][x-2]);
            }
            if(y+1 < field_obj.getWidth() && field[y+1][x].isSolid() &&
                    y+2 < field_obj.getWidth() && !field[y+2][x].isSolid())
            {
                list.add(field[y+2][x]);
            }
            if(y-1 > 0 && field[y-1][x].isSolid() &&
                    y-2 > 0 && !field[y-2][x].isSolid())
            {
                list.add(field[y-2][x]);
            }
        }
    }

    public static boolean greaterOrEqual(Node one, ArrayList<Node> two)
    {
        for( Node $: two)
        {
            if(one.getPos().equals($.getPos()) && $.getWeight() >= one.getWeight())
            {
                return true;
            }
        }
        return false;
    }

    public static int getDistance(Node one, Node two)
    {
        int dx = Math.abs(one.getPos().x-two.getPos().x);
        int dy = Math.abs(one.getPos().y-two.getPos().y);
        return dx+dy;
        //return (int)Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
    }
    public static int getMoveWeight(Node curr, Node other)
    {
        return getDistance(curr, other);
    }
    public static Path findPath(Field field_obj, Node start, Node finish)
    {
        //Check for blocked start or finish
        if(start.isSolid() || finish.isSolid())
        {
            return null;
        }
        Node[][] field = field_obj.getField();
        Path rpath = new Path(start, finish);
        ArrayList<Node> open = new ArrayList<Node>();
        ArrayList<Node> closed = new ArrayList<Node>();
        ArrayList<Node> adj = new ArrayList<Node>();
        Node curr;
        open.add(rpath.getStart());
        rpath.getFinish().setParent(null);
        int counter = 0;
        while(open.size()>0 && !open.contains(rpath.getFinish()))
        {
            counter++;
            curr = open.get(0);
            open.remove(curr);
            closed.add(curr);
            adj.clear();
            addAdjacent(adj,curr,field_obj);
            int costTo;
            for( Node $: adj)
            {
                costTo = curr.getCost()+getMoveWeight(curr,$);
                if(costTo < $.getCost())
                {
                    //System.out.println("removed!");
                    if(open.contains($))
                    {
                        open.remove($);
                    }
                    if(closed.contains($))
                    {
                        closed.remove($);
                    }
                }
                if(!open.contains($) && !closed.contains($))
                {
                    $.setWeight2(getMoveWeight($,rpath.getFinish()));
                    $.setWeight(costTo);
                    $.setCost($.getWeight2()+$.getWeight());
                    $.setParent(curr);
                    open.add($);
                    Collections.sort(open);
                }
            }
            //System.out.println("Stuck! "+open.size());
        }
        //We have or do not have a path
        if(rpath.getFinish().getParent() == null)
        {
            return null;
        }
        //Know we have a path.
        else
        {
            Node cursor = rpath.getFinish();
            while(cursor.getParent() != rpath.getStart())
            {
                rpath.add(cursor);
                cursor = cursor.getParent();
            }
            rpath.add(cursor);
            rpath.add(cursor.getParent());
        }
        return rpath;
    }
}
