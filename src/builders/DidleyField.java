package builders;

import model.Field;
import model.Node;
import model.Path;
import model.Tuple;
import pathfinders.AStar;
import pathfinders.PathFinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericd on 4/22/2016.
 */
public class DidleyField implements FieldBuilder {
    public Node[][] build(int width, int height, double... params)
    {
        Node[][] rfield = new Node[height][width];
        List<Tuple> marked = new ArrayList<>();
        marked.add(new Tuple(0,0));
        //marked.add(new Tuple(width-1,0));
        for(int y=0; y<height; y++)
        {
            if(Math.random() > 0.5)
            {
                for(int x=0; x<width; x++)
                {
                    double mark_chance = Math.random();
                    if(mark_chance > params[0] && (
                            (x==marked.get(marked.size()-1).x && y>marked.get(marked.size()-1).y) ||
                                    (x<marked.get(marked.size()-1).x && y>marked.get(marked.size()-1).y) ||
                                    (x>marked.get(marked.size()-1).x && y>marked.get(marked.size()-1).y)
                    )
                            )
                    {
                        marked.add(new Tuple(x,y));
                    }
                    rfield[y][x] = new Node(new Tuple(x,y),0,false);
                }
            }
            else
            {
                for(int x=width-1; x>-1; x--)
                {
                    double mark_chance = Math.random();
                    if(mark_chance > params[0] && (
                            (x==marked.get(marked.size()-1).x && y>marked.get(marked.size()-1).y) ||
                                    (x<marked.get(marked.size()-1).x && y>marked.get(marked.size()-1).y) ||
                                    (x>marked.get(marked.size()-1).x && y>marked.get(marked.size()-1).y)
                    )
                            )
                    {
                        marked.add(new Tuple(x,y));
                    }
                    rfield[y][x] = new Node(new Tuple(x,y),0,false);
                }
            }
        }
        marked.add(new Tuple(width-1, height-1));
        Tuple curr = new Tuple(0,0);
        Tuple next = null;
        Path end_path = new Path(rfield[0][0], rfield[height-1][width-1]);
        int cursor = 0;
        while(cursor < marked.size()-1)
        {
            curr = marked.get(cursor);
            next = marked.get(cursor+1);
            PathFinder pathFinder = new AStar(new Field(rfield));
            pathFinder.setDir8(false);
            Path path = pathFinder.findPath(rfield[curr.y][curr.x], rfield[next.y][next.x]);
            if(path == null){cursor++; continue;}
            for(Node $ : path)
            {
                if(!end_path.contains($))
                {
                    end_path.add($);
                }
            }
            cursor++;
        }
        for(int y=0; y<height; y++)
        {
            for(int x=0; x<width; x++)
            {
                rfield[y][x] = new Node(new Tuple(x,y),0,true);
            }
        }
        for(Node $: end_path)
        {
            rfield[$.getPos().y][$.getPos().x] = new Node(new Tuple($.getPos().x,$.getPos().y),0,false);
        }
        for(Tuple $: marked)
        {
            rfield[$.y][$.x].setSearched(true);
        }
        return rfield;
    }
}
