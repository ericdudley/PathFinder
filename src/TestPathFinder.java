/**
 * Created by ericd on 2/20/16.
 */
public class TestPathFinder {
    public static void main(String[] args)
    {
        Path path = null;
        int size = 20;
        double density = 0.3;
        int counter = 0;
        PathFinder astar = new AStar(
                new Field(size+"x"+size+" Random Field with obstacle density of "+density,size,size,density));
        astar.setDir8(true);
        astar.useWorldPos(false);
        while(path == null)
        {
            counter++;
            astar.reset();
            astar.setFieldObj(
                    new Field(size+"x"+size+" Random Field with obstacle density of "+density,size,size,density));
            path = astar.findPath(astar.getField()[0][0], astar.getField()[size-1][size-1]);
        }
        RenderField renderer = new RenderField(astar.getFieldObj(),path);
        renderer.render();
        System.out.println("Required: "+counter+" iterations");
        System.out.println("Total nodes: "+(size*size));
        System.out.println("# Nodes Searched: "+astar.getNodesSearched());

    }
}
