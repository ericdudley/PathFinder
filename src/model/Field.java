package model;

import builders.EmptyField;
import builders.FieldBuilder;
import builders.RandomField;

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
        this("Field #"+Field.next_id,30,30,new RandomField(),DEFAULT_OBSTACLE_DENSITY);
    }

    public Field(String name, int width, int height)
    {
        this(name,width,height,new RandomField(),DEFAULT_OBSTACLE_DENSITY);
    }

    public Field(Node[][] field)
    {
        this.name = "Default Name";
        this.width = field[0].length;
        this.height = field.length;
        this.field = field;
        this.id = next_id;
        next_id++;
    }
    public Field(String name, int width, int height, FieldBuilder fieldBuilder, double... params)
    {
        this.name = name;
        this.width = width;
        this.height = height;
        this.field = fieldBuilder.build(width, height, params);
        this.id = next_id;
        next_id++;
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

    public String toString()
    {
        String rstr = "";
        for(int y=0; y<this.height; y++)
        {
            for(int x=0; x<this.width; x++)
            {
                String sym = "1";
                if(this.field[y][x].isSolid())
                {
                    sym = "0";
                }
                rstr += sym+" ";
            }
            rstr += "\n";
        }
        return rstr;
    }


}
