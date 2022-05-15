package com.example.yarinproject;

import android.widget.ImageView;

public class ImageUtil {
    private ImageView Gfigure,floor1,floor2,stair1,door;
    public ImageUtil(){}
    public ImageUtil(ImageView Gfigure,ImageView floor1, ImageView floor2, ImageView stair1,ImageView door){
            this.Gfigure=Gfigure;
            this.floor1=floor1;
            this.floor2=floor2;
            this.stair1=stair1;
            this.door=door;
    }
    public  boolean Part1(){
        boolean b=false;
        if (Gfigure.getX()+Gfigure.getWidth()<floor1.getX()+floor1.getWidth())
            b=true;

        return b;
    }
    public  boolean Part2(){
        boolean b=false;
        if (Math.abs(Gfigure.getX()+Gfigure.getWidth()-floor1.getX()+floor1.getWidth())<2)
            b=true;

        return b;
    }
    public  boolean Part3(){
        boolean b=false;
        if(Gfigure.getX()+Gfigure.getWidth()-20>floor1.getX()+floor1.getWidth()
                &&Gfigure.getX()<floor2.getX()
                    &&Gfigure.getY()+Gfigure.getHeight()==floor1.getY())
            b=true;

        return b;
    }public  boolean Part4(){
        boolean b=false;
        if(Gfigure.getX()+Gfigure.getWidth()>floor1.getX()+floor1.getWidth()
                &&Gfigure.getX()<floor2.getX()
                &&Gfigure.getY()+Gfigure.getHeight()<floor1.getY())
            b=true;

        return b;
    }

    public  boolean Part5(){
        boolean b=false;
        if(Gfigure.getX()>floor2.getX()&&
                Gfigure.getX()+Gfigure.getWidth()<stair1.getX()-51)
            b=true;

        return b;
    }
    public  boolean Part6(){
        boolean b=false;
        if(Gfigure.getX()+Gfigure.getWidth()<stair1.getX()&&
                Gfigure.getX()+Gfigure.getWidth()>stair1.getX()-51)
            b=true;

        return b;
    }
    public  boolean Part7(){
        boolean b=false;
        if(Gfigure.getX()>stair1.getX()&&(Gfigure.getY()+Gfigure.getHeight())>(stair1.getY()+stair1.getHeight()))
            b=true;

        return b;
    }
    public  boolean Part8(){
        boolean b=false;
        if(door.getX()-Gfigure.getX()-Gfigure.getWidth()>=50)
            b=true;

        return b;
    }
    public  boolean Part9(){
        boolean b=false;
        if(door.getX()-Gfigure.getX()-Gfigure.getWidth()<50)
            b=true;

        return b;
    }
}
