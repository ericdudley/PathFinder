import builders.*;
import model.Field;
import model.Path;
import pathfinders.AStar;
import pathfinders.PathFinder;
import renderer.RenderField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ericd on 2/20/16.
 */
public class TestPathFinder {
    public static void main(String[] args)
    {
        Path path = null;
        int size = 8;
        double density = 0.3;
        int counter = 0;
        PathFinder astar = new AStar(
                new Field(size+"x"+size+" Random model.Field with obstacle density of "+density,size,size,new EmptyField(), density));
        RenderField renderer0 = new RenderField(astar.getFieldObj(), astar.findPath(astar.getField()[0][0],astar.getField()[size-1][size-1]));
        //renderer0.render();
        for(double i=0; i<10000; i+=1)
        {
            if(i%1000 == 0)
            {
                System.out.println(i);
            }
            double squash = i/10;
            Field field = new Field("Test for "+squash, size, size, new MazeField(), squash);
            //PathFinder astar2 = new AStar(field);
            //astar2.setDir8(false);
            //Path path2 = astar2.findPath(astar2.getField()[0][0], astar2.getField()[size-1][size-1]);
            //RenderField renderer = new RenderField(field);
            //RenderField renderer = new RenderField(astar2.getFieldObj(), path2);
            //renderer.render();
            try(FileWriter writer = new FileWriter("ou.txt", true);
                BufferedWriter bwriter = new BufferedWriter(writer);
                PrintWriter pwriter = new PrintWriter(bwriter))
            {
                pwriter.println(field.toString());
            }
            catch(IOException exc)
            {
                //Nothing
                System.out.println("error");
            }
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
