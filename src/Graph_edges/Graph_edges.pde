
// An example explaining how 
// to draw lines not to the center 
// but to the side of the circle

void setup()
{
  size(800,600);
  background(100);
}

void draw()
{
  int r = 100;
  // Point A coordinates:
  int x1 = 210;
  int y1 = 310;
  // Point B cordinates:
  int x2 = 550;
  int y2 = 400;
  
  // Drawing points:
  fill(0,200,10);
  ellipse(x1,y1,r,r); //<>//
  ellipse(x2,y2,r,r);
  //line(x1,y1,x2,y2);
  
  // Calculating length between points:
  double l =  sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)); //<>//
  
  int x3 =(int) (x2 - (r/2)*((x2-x1)/l));
  int y3 =(int) (y2 - (r/2)*((y2-y1)/l));
  int x4 = (int) (x1 - (r/2)*((x1-x2)/l));
  int y4 =(int) (y1 - (r/2)*((y1-y2)/l));
  // Drawing line:
  //line(x1,y1,x3,y3);
  line(x4,y4,x3,y3);
  
  
  
}