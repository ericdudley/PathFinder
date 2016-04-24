package pathfinders;

import model.Field;
import model.Node;
import model.Path;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ericd on 2/16/2016.
 */
public class AStar extends PathFinder {

    public AStar(Field field_obj)
    {
        super(field_obj);
    }
    public ArrayList<Node> findAdjacent(Node curr)
    {
        Node[][] field = this.field_obj.getField();
        ArrayList<Node> rlist = new ArrayList<Node>();
        int x = curr.getPos().x;
        int y = curr.getPos().y;
        if(y+1 < this.field_obj.getHeight() && !field[y+1][x].isSolid())
        {
            rlist.add(field[y+1][x]);
        }
        if(x+1 < this.field_obj.getWidth() && !field[y][x+1].isSolid())
        {
            rlist.add(field[y][x+1]);
        }
        if(y-1 > -1 && !field[y-1][x].isSolid())
        {
            rlist.add(field[y-1][x]);
        }
        if(x-1 > -1 && !field[y][x-1].isSolid())
        {
            rlist.add(field[y][x-1]);
        }
        if(this.dir8)
        {
            if(y+1 < this.field_obj.getHeight() && x+1 < this.field_obj.getWidth() && !field[y+1][x+1].isSolid())
            {
                rlist.add(field[y+1][x+1]);
            }
            if(x-1 > -1 && y-1 > -1 && !field[y-1][x-1].isSolid())
            {
                rlist.add(field[y-1][x-1]);
            }
            if(y-1 > -1 && x+1 < this.field_obj.getWidth() && !field[y-1][x+1].isSolid())
            {
                rlist.add(field[y-1][x+1]);
            }
            if(y+1 < this.field_obj.getHeight() && x-1 > -1 && !field[y+1][x-1].isSolid())
            {
                rlist.add(field[y+1][x-1]);
            }
        }
        if(this.hurdle)
        {
            if(x+1 < this.field_obj.getWidth() && field[y][x+1].isSolid() &&
                    x+2 < this.field_obj.getWidth() && !field[y][x+2].isSolid())
            {
                rlist.add(field[y][x+2]);
            }
            if(x-1 > 0 && field[y][x-1].isSolid() &&
                    x-2 > 0 && !field[y][x-2].isSolid())
            {
                rlist.add(field[y][x-2]);
            }
            if(y+1 < this.field_obj.getWidth() && field[y+1][x].isSolid() &&
                    y+2 < this.field_obj.getWidth() && !field[y+2][x].isSolid())
            {
                rlist.add(field[y+2][x]);
            }
            if(y-1 > 0 && field[y-1][x].isSolid() &&
                    y-2 > 0 && !field[y-2][x].isSolid())
            {
                rlist.add(field[y-2][x]);
            }
        }
        return rlist;
    }

    public boolean greaterOrEqual(Node one, ArrayList<Node> two)
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
    
    public Path findPath(Node start, Node finish)
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
        Node curr;
        open.add(rpath.getStart());
        rpath.getFinish().setParent(null);
        int counter = 0;
        while(open.size()>0 && !open.contains(rpath.getFinish()))
        {
            counter++;
            curr = open.get(0);
            this.nodeSearched();
            curr.setSearched(true);
            open.remove(curr);
            closed.add(curr);
            curr.getAdj().clear();
            curr.setAdj(findAdjacent(curr));
            int costTo;
            for( Node $: curr.getAdj())
            {
                costTo = curr.getCost()+this.getDistance(curr,$);
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
                    $.setWeight2(this.getDistance($,rpath.getFinish()));
                    $.setWeight(costTo);
                    $.setCost($.getWeight2()+$.getWeight());
                    $.setParent(curr);
                    open.add($);
                    Collections.sort(open);
                    if(Math.random() > 1.0)
                    {
                        Collections.shuffle(open);
                    }
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
