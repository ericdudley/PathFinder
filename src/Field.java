/**
 * Created by ericd on 2/16/2016.
 */
public class Field {
    public static int next_id = 0;
    public static final double DEFAULT_OBSTACLE_DENSITY = 0.2;
    private Node[][] field;
    private int width;
    private int height;
    private String name;
    private int id;
    public Field()
    {

        this("Field "+Field.next_id,30,30,DEFAULT_OBSTACLE_DENSITY);
    }

    public Field(String name, int width, int height, double obstacle_density)
    {
        this.width = width;
        this.height = height;
        this.name = name;
        this.id = Field.next_id;
        Field.next_id++;
        this.field = this.randomField(width,height, obstacle_density);
    }

    public Field(String name, int width, int height)
    {
        this(name,width,height,DEFAULT_OBSTACLE_DENSITY);
    }
    
    public Field(String name, Node[][] field)
    {
        this(name,field[0].length,field.length, 0.0);
        this.field = field;
    }

    public String getName()
    {
        return this.name;
    }

    public int getId()
    {
        return this.id;
    }

    public Node[][] getField()
    {
        return this.field;
    }

    public int getWidth(){ return this.width; }
    public int getHeight(){ return this.height; }
    private Node[][] randomField(int width, int height, double obstacle_density)
    {
        Node[][] rfield = new Node[height][width];
        for(int y=0; y<height; y++)
        {
            for(int x=0; x<width; x++)
            {
                rfield[y][x] = new Node(new Tuple(x,y),0,Math.random()<obstacle_density);
            }
        }
        return rfield;
    }


}
