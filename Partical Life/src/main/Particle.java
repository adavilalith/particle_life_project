package main;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

class Particle{
    int x,y;
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
    ArrayList<Particle> particles, greenParticles, yellowParticles, redParticles; 
    int xLimit,yLimit;
    int greenCount, yellowCount, redCount;
    double[][] AttractionMatrix={{-0.32,0.34,-0.17},
                                 {-0.2,0.15,0},
                                 {-.34,0,-0.1}
                                };
    Particles(){
        xLimit=16*3*32;
        yLimit=16*3*18;
        greenCount=10;
        yellowCount=10;
        redCount=10;
        particles = new ArrayList<Particle>();
        greenParticles = createParticles(greenCount, Color.green);
        yellowParticles = createParticles(yellowCount, Color.yellow);
        redParticles = createParticles(redCount, Color.red);
    }

    Particles(int xlim,int ylim, int gc,int yc, int rc){
        xLimit = xlim;
        yLimit = ylim;
        greenCount = gc;
        yellowCount = yc;
        redCount = rc;
        greenParticles = createParticles(greenCount, Color.green);
        yellowParticles = createParticles(yellowCount, Color.yellow);
        redParticles = createParticles(redCount, Color.red);
    }

    void setAttractionMatrix(double mat[][]){
        for(int i=0;i<mat.length;i++){
            for(int j=0;i<mat[i].length;j++){
                AttractionMatrix[i][j]=mat[i][j];
            }
        }
    }

    ArrayList<Particle> createParticles(int number, Color c){
        Random rand = new Random();
        ArrayList<Particle> group = new ArrayList<Particle>();
        for(int i=0;i<number;i++){
            Particle p = new Particle(rand.nextInt(xLimit), rand.nextInt(yLimit),0,0,c);
            group.add(p);
            particles.add(p);
        }
        return group;

    }

    void updateParticles(ArrayList<Particle> group1, ArrayList<Particle> group2,double g){
        for(int i=0;i<group1.size();i++){
            double fx=0,fy=0;
            for(int j=0;j<group2.size();j++){
                Particle a = group1.get(i);
                Particle b = group2.get(j);
                double dx = a.x - b.x;
                double dy = a.y - b.y;
                double d = Math.sqrt(dx*dx+dy*dy);
                if(d>0 && d<500){
                    double F =(g*1)/2;
                    fx += F + dx;
                    fy += F * dy;
                }
                a.vx = (a.vx + fx) * 0.5;
                a.vy = (a.vy + fy) * 0.5;
                a.x += a.vx;
                a.y += a.vy;
                if(a.x<0 || a.x>=xLimit){a.vx*=-1;}
                if(a.y<0 || a.y>=yLimit){a.vy*=-1;}
            }
        }
    }

    void update(){
        updateParticles(greenParticles,greenParticles,AttractionMatrix[0][0]);
        updateParticles(greenParticles,yellowParticles,AttractionMatrix[0][1]);
        updateParticles(greenParticles,redParticles,AttractionMatrix[0][2]);
        updateParticles(yellowParticles,yellowParticles,AttractionMatrix[1][1]);
        updateParticles(yellowParticles,greenParticles,AttractionMatrix[1][0]);
        updateParticles(redParticles,redParticles,AttractionMatrix[2][2]);
        updateParticles(redParticles,greenParticles,AttractionMatrix[2][0]);
   
    }

    ArrayList<Particle> getParticles(){
        return particles;
    }

}
