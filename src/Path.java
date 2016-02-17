import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ericd on 2/16/2016.
 */
public class Path extends ArrayList<Node> {
    public Node getFinish() {
        return finish;
    }

    public void setFinish(Node finish) {
        this.finish = finish;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    private Node start;
    private Node finish;
    public Path(Node start, Node finish)
    {
        this.start = start;
        this.finish = finish;
    }

    public boolean add(Node elem, boolean sort)
    {
        if(sort)
        {
            this.add(elem);
            Collections.sort(this);
            return true;
        }
        return false;
    }
}
