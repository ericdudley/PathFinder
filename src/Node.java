/**
 * Created by ericd on 2/16/2016.
 * Nodes with position and weight value.
 */
public class Node implements Comparable{
    public Tuple getPos() {
        return pos;
    }

    public void setPos(Tuple pos) {
        this.pos = pos;
    }

    private Tuple pos;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int weight;

    public int getWeight2() {
        return weight2;
    }

    public void setWeight2(int weight2) {
        this.weight2 = weight2;
    }

    private int weight2;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    private int cost;
    public boolean isSolid(){
        return this.solid;
    }
    public void setSolid(boolean solid)
    {
        this.solid = solid;
    }
    private boolean solid;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    private Node parent;
    public Node(Tuple pos, int weight, boolean solid)
    {
        this.pos = pos;
        this.weight = weight;
        this.solid = solid;
    }

    public boolean equals(Object obj)
    {
        Node other;
        if (obj == null)
        {
            return false;
        }
        if( obj instanceof Node)
        {
            other = (Node)obj;
            if(this.getPos().equals(other.getPos()))
            {
                return true;
            }
        }
        return false;
    }

    public int compareTo(Object obj)
    {
        Node other;
        if( obj instanceof Node)
        {
            other = (Node)obj;
            if(this.cost < other.cost)
            {
                return -1;
            }
            else if(this.cost > other.cost)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        return 0;
    }


}
