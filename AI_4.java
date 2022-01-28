//To write a program for water jug problem 

import java.util.*; 

 class Water { 

int a_max = 5; 

int b_max = 3; 

int a = 0; 

int b = 0; 

int goal = 4; 

 

void checkGoal() { 

int fin = 0; 

while(fin != 1) { 

if((this.a == this.goal) || (this.b == this.goal)) { fin = 1; } 

 

if(this.a==0) { 

fillA(); 

 

} else if ((this.a > 0) && (this.b != this.b_max)) { 

transferAtoB(); 

} else if ((this.a > 0) && (this.b == this.b_max)) { 

emptyB(); 

} 

} 

} 

void fillA() { 

this.a = this.a_max; 

System.out.println("(" + this.a + "," + this.b + ")"); 

} 

void fillB() { 

this.b = this.b_max; 

System.out.println("{" + this.a + "," + this.b + "}"); 

} 

void transferAtoB() { 

 

int fin = 0; 

 

while(fin != 1) { 

this.b += 1; 

this.a -= 1; 

 

if((this.b == this.b_max) || (this.a == 0)) { fin = 1;} 

} 

System.out.println("(" + this.a + "," + this.b + ")"); 

 

} 

void emptyA() { 

this.a=0; 

System.out.println("(" + this.a + "," + this.b + ")"); 

} 

void emptyB() { 

this.b=0; 

System.out.println("(" + this.a + "," + this.b + ")");}} 

public class WaterJug{ 

  public static void main(String args[]) { 

      System.out.println("Measuring 4L water by using two buckets 5L and 3L"); 

Water w = new Water(); 

w.checkGoal();}} 