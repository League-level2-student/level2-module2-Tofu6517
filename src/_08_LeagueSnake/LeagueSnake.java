package _08_LeagueSnake;

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
    int foodX;
    int foodY;
    int direction=UP;
    int amountOfFoodEaten=0;
	int movinX=5;
	int movinY=5;
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
        head=new Segment();
        frameRate(20);
        dropFood();
    }

    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(50)*10);
    	foodY = ((int)random(50)*10);
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
        drawSnake(0,0);
        eat();
    }

    void drawFood() {
        // Draw the food
    	fill(0,250,0);
        rect(foodX,foodY,10,10);
        fill(0,250,0);
    }

    void drawSnake(int changeInDX,int changeInDY) {
        // Draw the head of the snake followed by its tail
    	movinX=movinX+changeInDX;
    	movinY=movinY+changeInDY;
    	head.initialize();
    	fill(250,0,0);
    	rect(head.x+movinX,head.y+movinY,10,10);
    	fill(250,0,0);
    	 checkBoundaries();
    }

    void drawTail() {
        // Draw each segment of the tail
        
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

    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
        
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
            drawSnake(0,-5);
            checkBoundaries();
        } else if (direction == DOWN) {
            drawSnake(0,5);
            checkBoundaries();  
        } else if (direction == LEFT) {
            drawSnake(-5,0);
            checkBoundaries();
        } else if (direction == RIGHT) {
            drawSnake(5,0);
            checkBoundaries();
        }
        
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(head.x>500) {
        	head.x=0;
        	movinX=0;
        }
        else if(head.x<0) {
        	head.x=500;
        	movinX=500;
        }
        else if(head.y>500) {
        	head.y=0;
        	movinY=0;
        }
        else if(head.y<0) {
        	head.y=500;
        	movinY=500;
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
        if(head.x==foodX&&head.y==foodY) {
        	amountOfFoodEaten=amountOfFoodEaten+1;
        	dropFood();
        }
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
