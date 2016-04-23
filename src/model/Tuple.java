package model;

/**
 * Created by ericd on 2/16/2016.
 */
public class Tuple {
    public int x;
    public int y;
    public Tuple(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj)
    {
        Tuple other;
        if (obj == null)
        {
            return false;
        }
        if( obj instanceof Tuple)
        {
            other = (Tuple)obj;
            if(other.x == this.x && other.y == this.y)
            {
                return true;
            }
        }
        else
        {
            return false;
        }
        return false;
    }
}
