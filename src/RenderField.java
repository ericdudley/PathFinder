/**
 * Created by ericd on 2/16/2016.
 */

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RenderField extends JFrame {
    public static final int WINDOW_SIZE = 800;
    private int NODE_SIZE;
    private int SPACE_SIZE;
    private int VERT_SPACE_PAD;
    private Field field;
    private Path path;

    public RenderField(Field field)
    {
        this.field = field;
        this.setTitle(this.field.getName()+" Render | Created by: Eric Dudley");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(RenderField.WINDOW_SIZE, RenderField.WINDOW_SIZE));
        this.setResizable(false);
        this.NODE_SIZE = (int)(2*RenderField.WINDOW_SIZE)/(3*this.field.getWidth()+1);
        this.SPACE_SIZE = this.NODE_SIZE/2;
        this.VERT_SPACE_PAD = 15;
        this.path = null;
    }

    public RenderField(Field field, Path path)
    {
        this(field);
        this.path = path;
    }

    public void render()
    {
        this.pack();
        this.setVisible(true);
        this.NODE_SIZE = (int)(2*RenderField.WINDOW_SIZE)/(3*this.field.getWidth()+1);
        this.SPACE_SIZE = this.NODE_SIZE/2;
    }

    public void setField(Field field){ this.field = field; }
    public void setPath(Path path){ this.path = path; }
    @Override
    public void paint(Graphics g)
    {
        boolean special = false;
        for(int y=0; y<this.field.getHeight(); y++)
        {
            for(int x=0; x<this.field.getWidth(); x++)
            {
                Node $ = this.field.getField()[y][x];
                special = false;
                g.setColor(Color.GREEN);
                if(this.path != null)
                {
                    if(this.path.contains($))
                    {
                        g.setColor(Color.BLUE);
                    }
                    if($.equals(this.path.getStart()) ||
                            $.equals(this.path.getFinish()))
                    {
                        g.setColor(Color.BLACK);
                    }
                }
                if($.isSolid())
                {
                    g.setColor(Color.RED);
                }
                int xcoor = x*this.NODE_SIZE+((x+1)*this.SPACE_SIZE);
                int ycoor = y*this.NODE_SIZE+((y+1)*this.SPACE_SIZE+this.VERT_SPACE_PAD);
                g.fillRect(xcoor, ycoor,this.NODE_SIZE, this.NODE_SIZE);
                if(this.path != null)
                {
                    if(this.path.indexOf($) > 0)
                    {
                        int xcoor1 = x*this.NODE_SIZE+((x+1)*this.SPACE_SIZE)+this.NODE_SIZE/2;
                        int ycoor1 = y*this.NODE_SIZE+((y+1)*this.SPACE_SIZE+this.VERT_SPACE_PAD+this.NODE_SIZE/2);
                        int w = this.path.get(this.path.indexOf($)-1).getPos().x;
                        int z = this.path.get(this.path.indexOf($)-1).getPos().y;
                        int xcoor2 = w*this.NODE_SIZE+((w+1)*this.SPACE_SIZE)+this.NODE_SIZE/2;
                        int ycoor2 = z*this.NODE_SIZE+((z+1)*this.SPACE_SIZE+this.VERT_SPACE_PAD+this.NODE_SIZE/2);
                        g.setColor(Color.BLUE);
                        Graphics2D g2 = (Graphics2D)g;
                        g2.setStroke(new BasicStroke(10));
                        g.drawLine(xcoor1,ycoor1,xcoor2,ycoor2);
                    }

                    if(this.path.contains($))
                    {
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("TimesRoman", Font.BOLD, 16));
                        g.drawString(Integer.toString(this.path.size()-this.path.indexOf($)),xcoor+(int)Math.round(this.NODE_SIZE*(0.33)),ycoor+(int)Math.round(this.NODE_SIZE*(0.66)));
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
                        g.setColor(Color.BLACK);
                    }
                    if ($.equals(this.path.getFinish()))
                    {
                        g.drawString("FINISH", xcoor, ycoor);
                        special = true;
                    }
                    else if ($.equals(this.path.getStart()))
                    {
                        g.drawString("START", xcoor, ycoor);
                        special = true;
                    }
                }
                if(!special)
                {
                    g.drawString(Integer.toString($.getWeight()), xcoor, ycoor);
                }

            }
        }
    }
}
