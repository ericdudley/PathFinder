package builders;

import model.Node;

import java.util.List;

/**
 * Created by ericd on 4/22/2016.
 */
public interface FieldBuilder {
    public Node[][] build(int width, int height, double... params);
}
