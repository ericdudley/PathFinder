package builders;

import model.Node;
import model.Tuple;

import java.util.List;

/**
 * Created by ericd on 4/22/2016.
 */
public class RandomField implements FieldBuilder{
    @Override
    public Node[][] build(int width, int height, double... params)
    {
        Node[][] rfield = new Node[height][width];
        for(int y=0; y<height; y++)
        {
            for(int x=0; x<width; x++)
            {
                rfield[y][x] = new Node(new Tuple(x,y),0,Math.random()<params[0]);
            }
        }
        return rfield;
    }
}
