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

class Particles {
    static ArrayList<Particle> particles, greenParticles, yellowParticles, redParticles; 
    static int xLimit,yLimit;
    static int greenCount, yellowCount, redCount;
    static double[][] AttractionMatrix={{-0.32,0.34,-0.17},
                                 {-0.2,0.15,0},
                                 {-0.34,0,-0.1}
                                };
    static void setDefaultParticlesInfo(){
        xLimit=1280;
        yLimit=720;
        greenCount=1000;
        yellowCount=1000;
        redCount=1000;
        particles = new ArrayList<Particle>();
        greenParticles = createParticles(greenCount, Color.green);
        yellowParticles = createParticles(yellowCount, Color.yellow);
        redParticles = createParticles(redCount, Color.red);
    }

    static void setParticlesInfo(int xlim,int ylim, int gc,int yc, int rc){
        xLimit = xlim;
        yLimit = ylim;
        greenCount = gc;
        yellowCount = yc;
        redCount = rc;
        greenParticles = createParticles(greenCount, Color.green);
        yellowParticles = createParticles(yellowCount, Color.yellow);
        redParticles = createParticles(redCount, Color.red);
    }

    static void setAttractionMatrix(double mat[][]){
        for(int i=0;i<mat.length;i++){
            for(int j=0;i<mat[i].length;j++){
                AttractionMatrix[i][j]=mat[i][j];
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
            a.vx = (a.vx + fx) * 0.5;
            a.vy = (a.vy + fy) * 0.5;
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
        updateParticles(greenParticles,greenParticles,AttractionMatrix[0][0]);
        updateParticles(greenParticles,yellowParticles,AttractionMatrix[0][1]);
        updateParticles(greenParticles,redParticles,AttractionMatrix[0][2]);
        updateParticles(yellowParticles,yellowParticles,AttractionMatrix[1][1]);
        updateParticles(yellowParticles,greenParticles,AttractionMatrix[1][0]);
        updateParticles(redParticles,redParticles,AttractionMatrix[2][2]);
        updateParticles(redParticles,greenParticles,AttractionMatrix[2][0]);
        // updateParticles(greenParticles,greenParticles,-0.32);
        // updateParticles(greenParticles,yellowParticles,0.34);
        // updateParticles(greenParticles,redParticles,-0.17);
        // updateParticles(yellowParticles,yellowParticles,0.15);
        // updateParticles(yellowParticles,greenParticles,-0.2);
        // updateParticles(redParticles,redParticles,-0.1);
        // updateParticles(redParticles,greenParticles,-0.34);
   
    }

    static ArrayList<Particle> getParticles(){
        return particles;
    }

}
