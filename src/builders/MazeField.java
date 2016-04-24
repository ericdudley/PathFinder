package builders;

import model.Field;
import model.Node;
import model.Tuple;

/**
 * Created by ericd on 4/24/2016.
 */
public class MazeField implements FieldBuilder {

    Node[][] field;
    Field field_obj;
    int width,height;
    private boolean isValid()
    {
        int failed_count = 0;
        for(int y=0; y<this.field.length; y++)
        {
            for(int x=0; x<this.field[0].length; x++)
            {
                //if(this.field[y][x].isSolid()){continue;}
                int count = 0;
                int count2 = 0;
                for(int i=y-1; i<Math.min(this.height,y+2); i++)
                {
                    if(i==-1){continue;}
                    for(int j=x-1; j<Math.min(this.width,x+2); j++)
                    {
                        if(j==-1 || (i==y && j==x)){continue;}
                        this.field[y][x].addAdj(this.field[i][j]);
                        if(this.field[i][j].isSolid())
                        {
                            count2++;
                        }
                        else
                        {
                            count++;
                        }
                    }
                }
                /*if(this.field[y][x].isSolid() && count2>5)
                {
                    return false;
                }
                else if(!this.field[y][x].isSolid() && count>5)
                {
                    return false;
                }*/
                for(Node $: this.field[y][x].getAdj())
                {
                    for(Node $2: this.field[y][x].getAdj())
                    {
                        if($ == $2 || ($.isSolid() && !$2.isSolid()) || (!$.isSolid() && $2.isSolid())){continue;}
                        Tuple disp = new Tuple(Math.abs($.getPos().x-$2.getPos().x),Math.abs($.getPos().y-$2.getPos().y));
                        if(disp.x != 1 || disp.y != 1){continue;}
                        Tuple mod1 = new Tuple($.getPos().x-x,$.getPos().y-y);
                        Tuple mod2 = new Tuple($2.getPos().x-x,$2.getPos().y-y);
                        Tuple mod3 = new Tuple(mod1.x+mod2.x,mod1.y+mod2.y);
                        if(x+mod3.x > this.width-1 || x+mod3.x<0 || y+mod3.y > this.height-1 || y+mod3.y<0){continue;}
                        Node $3 = this.field[y+mod3.y][x+mod3.x];
                        Node curr = this.field[y][x];
                        if($.isSolid() && $3.isSolid() && curr.isSolid())
                        {
                            failed_count++;
                            //return false;
                        }
                        else if(!$.isSolid() && !$3.isSolid() && !curr.isSolid())
                        {
                            failed_count++;
                            curr.setSearched(true);
                            //return false;
                        }
                    }
                }
            }
        }
        if(failed_count > this.width*this.height*0.0)
        {
            return false;
        }
        return true;
    }

    public Node[][] build(int width, int height, double... params)
    {
        this.field_obj = new Field("debug", width, height, new RandomField(), 0.4);
        this.field = this.field_obj.getField();
        this.width = width;
        this.height = height;
        int count = 0;
        while(!this.isValid())
        {
            //System.out.println(count);
            count++;
            this.field_obj = new Field("debug", width, height, new RandomField(), 0.5);
            this.field = this.field_obj.getField();
        }
        //System.out.println(count);
        return this.field;
    }
}
