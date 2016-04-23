package builders;

import model.Node;
import model.Tuple;

/**
 * Created by ericd on 4/22/2016.
 */
public class EmptyField implements FieldBuilder{
    @Override
    public Node[][] build(int width, int height, double... params)
    {
        Node[][] rfield = new Node[height][width];
        for(int y=0; y<height; y++)
        {
            for(int x=0; x<width; x++)
            {
                rfield[y][x] = new Node(new Tuple(x,y),0,false);
            }
        }
        return rfield;
    }
}
