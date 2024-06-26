package _08_LeagueSnake;

import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    Segment head;
    ArrayList<Segment> tailSegments = new ArrayList<Segment>();
    int foodX;
    int foodY;
    int direction=UP;
    int amountOfFoodEaten=0;
	
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
        size(500,500);

        
    }

    @Override
    public void setup() {
        head=new Segment(200,200);
        frameRate(10);
        dropFood();
        
    }
    
    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(25)*20);
    	foodY = ((int)random(25)*20);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
        background(0,0,0);
        drawFood();
        move();
        drawSnake();
        eat();
   	 	checkBoundaries();
    }

    void drawFood() {
        // Draw the food
    	fill(0,250,0);
        rect(foodX,foodY,20,20);
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	
    	fill(250,0,0);
    	rect(head.x,head.y,20,20);
    	manageTail(); 
    }

    void drawTail() {
    	for(int i = 0; i<tailSegments.size(); i++) {
    		fill(250,0,0);
    		rect(tailSegments.get(i).x,tailSegments.get(i).y,20,20);
    	}
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.
     	checkTailCollision();
     	drawTail();
     	Segment nSeg=new Segment(head.x, head.y);
     	tailSegments.add(nSeg);
     	tailSegments.remove(0);
     	
    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
    	Segment n=new Segment(head.x,head.y);
    	print(tailSegments.size());
    	for(Segment obj : tailSegments) {
    		if(head.x==obj.x&&head.y==obj.y) {
            	amountOfFoodEaten=0;
            	tailSegments.clear();
            	Segment nSeg=new Segment(head.x, head.y);
             	tailSegments.add(nSeg);
    		}
    	}

    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed
    	if(keyCode==UP&&direction!=DOWN) {
    		direction=UP;
    	}
    	if(keyCode==DOWN&&direction!=UP) {
    		direction=DOWN;
    	}
    	if(keyCode==RIGHT&&direction!=LEFT) {
    		direction=RIGHT;
    	}
    	if(keyCode==LEFT&&direction!=RIGHT) {
    		direction=LEFT;
    	}
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.

        
        if (direction == UP) {
            head.y=head.y-20;
        } else if (direction == DOWN) {
            head.y=head.y+20;
        } else if (direction == LEFT) {
            head.x=head.x-20;
        } else if (direction == RIGHT) {
        	head.x=head.x+20;
        }
        checkBoundaries();
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(head.x>500) {
        	head.x=0;
        }
        else if(head.x<0) {
        	head.x=480;
        }
        else if(head.y>500) {
        	head.y=0;
        }
        else if(head.y<0) {
        	head.y=480;
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
        if(head.x==foodX&&head.y==foodY) {
        	amountOfFoodEaten=amountOfFoodEaten+1;
        	System.out.println(amountOfFoodEaten);
        	Segment nSeg=new Segment(head.x, head.y);
          	tailSegments.add(nSeg);
        	dropFood();
        }
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
