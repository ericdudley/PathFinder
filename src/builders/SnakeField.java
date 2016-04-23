package builders;

import model.Node;
import model.Tuple;

/**
 * Created by ericd on 4/22/2016.
 */
public class SnakeField implements FieldBuilder {
    @Override
    public Node[][] build(int width, int height, double... params)
    {
        Node[][] rfield = new Node[height][width];
        for(int y=0; y<height; y++)
        {
            for(int x=0; x<width; x++)
            {
                if(x%2 == 0)
                {
                    if(x%4 == 0 && y > 0)
                    {
                        rfield[y][x] = new Node(new Tuple(x,y), 0, true);
                    }
                    else if(x%4 != 0 && y < height-1)
                    {
                        rfield[y][x] = new Node(new Tuple(x,y), 0, true);
                    }
                    else
                    {
                        rfield[y][x] = new Node(new Tuple(x,y), 0, false);
                    }
                }
                else
                {
                    rfield[y][x] = new Node(new Tuple(x,y), 0, false);
                }
            }
        }
        rfield[0][0] = new Node(new Tuple(0,0), 0, false);
        return rfield;
    }
}
