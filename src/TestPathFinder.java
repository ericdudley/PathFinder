import builders.*;
import model.Field;
import model.Node;
import model.Path;
import pathfinders.AStar;
import pathfinders.PathFinder;
import renderer.RenderField;

import java.util.ArrayList;

/**
 * Created by ericd on 2/20/16.
 */
public class TestPathFinder {
    public static void main(String[] args)
    {
        Path path = null;
        int size = 20;
        double density = 0.4;
        int counter = 0;
        PathFinder astar = new AStar(
                new Field(size+"x"+size+" Random model.Field with obstacle density of "+density,size,size,new DidleyField(), density));
        //RenderField renderer = new RenderField(astar.getFieldObj());
        //renderer.render();
        for(int i=0; i<11; i++)
        {
            double squash = (double)(i)/10;
            Field field = new Field("Test for "+squash, size, size, new DidleyField(), squash);
            PathFinder astar2 = new AStar(field);
            Path path2 = astar2.findPath(astar2.getField()[0][0], astar2.getField()[size-1][size-1]);
            RenderField renderer = new RenderField(astar2.getFieldObj(), path2);
            renderer.render();
        }
        astar.setDir8(false);
        astar.useWorldPos(false);
       /* while(path == null)
        {
            counter++;
            astar.reset();
            astar.setFieldObj(
                    new Field(size+"x"+size+" Random model.Field with obstacle density of "+density,size,size, new SnakeField(), density));
            path = astar.findPath(astar.getField()[0][0], astar.getField()[size-1][size-1]);
        }
        RenderField renderer2 = new RenderField(astar.getFieldObj(),path);
        renderer2.render();
        System.out.println("Required: "+counter+" iterations");
        System.out.println("Total nodes: "+(size*size));
        System.out.println("# Nodes Searched: "+astar.getNodesSearched());
        double efficiency = 1-((double)astar.getNodesSearched()/(size*size));
        System.out.println("Efficiency: "+Math.round(100*efficiency)+"% of nodes avoided");*/

    }
}
