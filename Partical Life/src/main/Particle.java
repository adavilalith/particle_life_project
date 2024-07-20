package main;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

class Particle{
    double x,y;
    double vx,vy;
    Color c;
    Particle(int x, int y, int vx, int vy, Color c){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.c = c;
    }
}

class AttractionMatrixElement{
    ArrayList<Particle> source,target;
    double force=0.0;

}

class Particles {
    static ArrayList<Particle> particles, greenParticles, yellowParticles, redParticles, blueParticles, magentaParticles; 
    static int xLimit,yLimit;
    static int greenCount, yellowCount, redCount, blueCount, magentaCount; 
    static AttractionMatrixElement[] attractionMatrix;

    static void setDefaultParticlesInfo(){
        xLimit=GamePanel.screenWidth;
        yLimit=GamePanel.screenHeight;
        greenCount=500;
        yellowCount=1000;
        redCount=500;
        blueCount=500;
        magentaCount=500;
        particles = new ArrayList<Particle>();
        greenParticles = createParticles(greenCount, Color.green);
        yellowParticles = createParticles(yellowCount, Color.yellow);
        redParticles = createParticles(redCount, Color.red);
        blueParticles = createParticles(blueCount, Color.blue);
        magentaParticles = createParticles(magentaCount, Color.magenta);
        double[][] temp={{-0.32,0.34,-0.17,0,0},
                                    {-0.2,0.15,0,0,0},
                                    {-0.34,0,-0.1,0,0},
                                    {0,0,0,0,0},
                                    {0,0,0,0,0},
                                    }; 
        setAttractionMatrix(temp);
        
    }

    static void setParticlesInfo(int gc,int yc, int rc, int bc, int mc){
        greenCount = gc;
        yellowCount = yc;
        redCount = rc;
        blueCount = bc;
        magentaCount = mc;
        particles = new ArrayList<Particle>();

        greenParticles = createParticles(greenCount, Color.green);
        yellowParticles = createParticles(yellowCount, Color.yellow);
        redParticles = createParticles(redCount, Color.red);
        blueParticles = createParticles(blueCount, Color.blue);
        magentaParticles = createParticles(magentaCount, Color.magenta);
    }

    static void setRandomAttractionMatrix(){
        Random rand = new Random(); 
        double temp[][] = new double[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                temp[i][j] = rand.nextDouble();
                temp[i][j] *= Math.pow(-1,rand.nextInt()); 
            }
        }
        setAttractionMatrix(temp);
    }

    static void setAttractionMatrix(double mat[][]){
        attractionMatrix = new AttractionMatrixElement[25];
        for(int i=0;i<25;i++)
            attractionMatrix[i]=new AttractionMatrixElement();
        /*
         *   #  g  y  r  b  m
         *   g gg gy gr gb gm
         *   y yg yy yr yb ym
         *   r rg ry rr rb rm
         *   b bg by br bb bm
         *   m gm gy gr gb gm  
         */

        attractionMatrix[0].source = greenParticles;
        attractionMatrix[0].target = greenParticles;


        attractionMatrix[1].source = greenParticles;
        attractionMatrix[1].target = yellowParticles;

        
        attractionMatrix[2].source = greenParticles;
        attractionMatrix[2].target = redParticles;


        attractionMatrix[3].source = greenParticles;
        attractionMatrix[3].target = blueParticles;


        attractionMatrix[4].source = greenParticles;
        attractionMatrix[4].target = magentaParticles;


        attractionMatrix[5].source = yellowParticles;
        attractionMatrix[5].target = greenParticles;


        attractionMatrix[6].source = yellowParticles;
        attractionMatrix[6].target = yellowParticles;


        attractionMatrix[7].source = yellowParticles;
        attractionMatrix[7].target = redParticles;


        attractionMatrix[8].source = yellowParticles;
        attractionMatrix[8].target = blueParticles;


        attractionMatrix[9].source = yellowParticles;
        attractionMatrix[9].target = magentaParticles;


        attractionMatrix[10].source = redParticles;
        attractionMatrix[10].target = greenParticles;


        attractionMatrix[11].source = redParticles;
        attractionMatrix[11].target = yellowParticles;


        attractionMatrix[12].source = redParticles;
        attractionMatrix[12].target = redParticles;


        attractionMatrix[13].source = redParticles;
        attractionMatrix[13].target = blueParticles;


        attractionMatrix[14].source = redParticles;
        attractionMatrix[14].target = magentaParticles;


        attractionMatrix[15].source = blueParticles;
        attractionMatrix[15].target = greenParticles;


        attractionMatrix[16].source = blueParticles;
        attractionMatrix[16].target = yellowParticles;


        attractionMatrix[17].source = blueParticles;
        attractionMatrix[17].target = redParticles;


        attractionMatrix[18].source = blueParticles;
        attractionMatrix[18].target = blueParticles;


        attractionMatrix[19].source = blueParticles;
        attractionMatrix[19].target = magentaParticles;

        attractionMatrix[20].source = magentaParticles;
        attractionMatrix[20].target = greenParticles;


        attractionMatrix[21].source = magentaParticles;
        attractionMatrix[21].target = yellowParticles;


        attractionMatrix[22].source = magentaParticles;
        attractionMatrix[22].target = redParticles;


        attractionMatrix[23].source = magentaParticles;
        attractionMatrix[23].target = blueParticles;


        attractionMatrix[24].source = magentaParticles;
        attractionMatrix[24].target = magentaParticles;

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                attractionMatrix[i*5+j].force=mat[i][j];
            }
        }
    }

    static ArrayList<Particle> createParticles(int number, Color c){
        Random rand = new Random();
        ArrayList<Particle> group = new ArrayList<Particle>();
        for(int i=0;i<number;i++){
            Particle p = new Particle(rand.nextInt(xLimit), rand.nextInt(yLimit),0,0,c);
            group.add(p);
            particles.add(p);
        }
        return group;

    }

    static void updateParticles(ArrayList<Particle> group1, ArrayList<Particle> group2,double g){
        for(int i=0;i<group1.size();i++){
            double fx=0,fy=0;
            Particle a = group1.get(i);
            for(int j=0;j<group2.size();j++){
                a = group1.get(i);
                Particle b = group2.get(j);
                double dx = a.x - b.x;
                double dy = a.y - b.y;
                double d = Math.sqrt(dx*dx+dy*dy);
                if(d>0 && d<80){
                    double F =(g*1)/(d);
                    fx += F * dx;
                    fy += F * dy;
                }
            }                
            a.vx = (a.vx + fx) * 0.2;
            a.vy = (a.vy + fy) * 0.2;
            a.x += a.vx;
            a.y += a.vy;
            if(a.x<0 || a.x>=xLimit){
                a.vx*=-1;
                a.x%=xLimit;
            }
            if(a.y<0 || a.y>=yLimit){
                a.vy*=-1;
                a.y%=yLimit;
            }
        }
    }
    

    static void update(){
        for(int i=0;i<25;i++){
                updateParticles(attractionMatrix[i].source,attractionMatrix[i].target,attractionMatrix[i].force);
        } 
    }

    static ArrayList<Particle> getParticles(){
        return particles;
    }

}
