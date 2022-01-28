//To write a program for Best First search

//Best First Search

import java.util.*;

public class BFS{

static class eleCount {

int e;

int c;

};

static void moreThanNdK(int arr[], int n, int k)

{

if (k < 2)

return;

eleCount []temp = new eleCount[k - 1];

for (int i = 0; i < k - 1; i++)

temp[i] = new eleCount();

for (int i = 0; i < k - 1; i++) {

temp[i].c = 0;

}

for (int i = 0; i < n; i++)

{

int j;

for (j = 0; j < k - 1; j++)

{

if (temp[j].e == arr[i])

{

temp[j].c += 1;

break;

}

}

if (j == k - 1) {

int l;

for (l = 0; l < k - 1; l++)

{

if (temp[l].c == 0)

{

temp[l].e = arr[i];

temp[l].c = 1;

break;

}

}

if (l == k - 1)

for (l = 0; l < k-1; l++)

temp[l].c -= 1;

}

}

for (int i = 0; i < k - 1; i++)

{

int ac = 0;

for (int j = 0; j < n; j++)

if (arr[j] == temp[i].e)

ac++;

if (ac > n / k)

System.out.print("Number:" + temp[i].e

+ " Count:" + ac +"\n");

}

}

public static void main(String[] args)

{

System.out.print("First Test\n");

int arr1[] = { 4, 5, 6, 7, 8, 4, 4 };

int size = arr1.length;

int k = 3;

moreThanNdK(arr1, size, k);

System.out.print("\nSecond Test\n");

int arr2[] = { 4, 2, 2, 7 };

size = arr2.length;

k = 3;

moreThanNdK(arr2, size, k);

System.out.print("\nThird Test\n");

int arr3[] = { 2, 7, 2 };

size = arr3.length;

k = 2;

moreThanNdK(arr3, size, k);

System.out.print("\nFourth Test\n");

int arr4[] = { 2, 3, 3, 2 };

size = arr4.length;

k = 3;

moreThanNdK(arr4, size, k);

}

}

//Greedy Best First Search:

import java.util.HashMap;

import java.util.Map;

import java.util.PriorityQueue;

public class Graph {

private Map<String, AdjList> graph;

public static void main(String[] args) {

GBFS("Arad", "Bucharest");

}

public static void GBFS(String start, String end) {

Graph roads = new Graph();

roads.init();

AdjList adjList = roads.graph.get(start);

System.out.println("Greedy Best First Search Starts!");

AdjNode startNode = findNode(adjList, start);

System.out.print("start from node: \n" + start + "(" + startNode.cost + ")" + "-->");

int totalCost = 0;

while (adjList.size() > 0) {

AdjNode nextNode = adjList.poll();

int nodeCost = nextNode.cost;

totalCost += nodeCost;

System.out.print(nextNode.name + "(" + nodeCost + ")-->");

adjList = roads.graph.get(nextNode.name);

if (isExist(adjList, end)) {

int lastNodeCost = adjList.poll().cost;

totalCost += lastNodeCost;

System.out.println(end + "(" + lastNodeCost + ").");

System.out.println("find path! \ntotal cost is : " + totalCost);

return;

}

}

}

public void init() {

graph = new HashMap<>();

String name = "Arad";

int cost = 366;

AdjList adjList = new AdjList();

adjList.add(new AdjNode("Arad", 366));

adjList.add(new AdjNode("Zerind", 374));

adjList.add(new AdjNode("Sibiu", 253));

adjList.add(new AdjNode("Timisoara", 329));

graph.put(name, adjList);

name = "Sibiu";

adjList = new AdjList();

adjList.add(new AdjNode("Fagaras", 176));

adjList.add(new AdjNode("Rimnicu", 193));

adjList.add(new AdjNode("Rimnicu", 380));

graph.put(name, adjList);

name = "Fagaras";

adjList = new AdjList();

adjList.add(new AdjNode("Sibiu", 253));

adjList.add(new AdjNode("Bucharest", 0));

graph.put(name, adjList);

}

public static boolean isExist(AdjList adjList, String name) {

for (AdjNode n :

adjList) {

if (n.name.equals(name)) {

return true;

}

}

return false;

}

public static AdjNode findNode(AdjList adjList, String name) {

for (AdjNode n :

adjList) {

if (n.name.equals(name)) {

return n;

}
}
return null;

}

}

class AdjList extends PriorityQueue<AdjNode> {

}

class AdjNode implements Comparable<AdjNode> {

String name;

int cost;

public AdjNode(String name, int cost) {

this.name = name;

this.cost = cost;

}

@Override

public int compareTo(AdjNode o) {

return Integer.compare(cost, o.cost);

}

@Override

public String toString() {

return "AdjNode{" +

"name='" + name + '\'' +

", cost=" + cost +

'}';

}

}


//A* search:


import java.util.List;

import java.util.ArrayList;

import java.util.Collections;

public class ATreeSearch {

private final List<Node> open;

private final List<Node> closed;

private final List<Node> path;

private final int[][] maze;

private Node now;

private final int xstart;

private final int ystart;

private int xend, yend;

private final boolean diag;

static class Node implements Comparable {

public Node parent;

public int x, y;

public double g;

public double h;

Node(Node parent, int xpos, int ypos, double g, double h) {

this.parent = parent;

this.x = xpos;

this.y = ypos;

this.g = g;

this.h = h;

}

@Override

public int compareTo(Object o) {

Node that = (Node) o;

return (int)((this.g + this.h) - (that.g + that.h));

}

}

ATreeSearch(int[][] maze, int xstart, int ystart, boolean diag) {

this.open = new ArrayList<>();

this.closed = new ArrayList<>();

this.path = new ArrayList<>();

this.maze = maze;

this.now = new Node(null, xstart, ystart, 0, 0);

this.xstart = xstart;

this.ystart = ystart;

this.diag = diag;

}

public List<Node> findPathTo(int xend, int yend) {

this.xend = xend;

this.yend = yend;

this.closed.add(this.now);

addNeigborsToOpenList();

while (this.now.x != this.xend || this.now.y != this.yend) {

if (this.open.isEmpty()) {

return null;

}

this.now = this.open.get(0);

this.open.remove(0);

this.closed.add(this.now);

addNeigborsToOpenList();

}

this.path.add(0, this.now);

while (this.now.x != this.xstart || this.now.y != this.ystart) {

this.now = this.now.parent;

this.path.add(0, this.now);

}

return this.path;

}

private static boolean findNeighborInList(List<Node> array, Node node) {

return array.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));

}

private double distance(int dx, int dy) {

if (this.diag) {

return Math.hypot(this.now.x + dx - this.xend, this.now.y + dy - this.yend);

} else {

return Math.abs(this.now.x + dx - this.xend) + Math.abs(this.now.y + dy - this.yend);

}

}

private void addNeigborsToOpenList() {

Node node;

for (int x = -1; x <= 1; x++) {

for (int y = -1; y <= 1; y++) {

if (!this.diag && x != 0 && y != 0) {

continue;

}

node = new Node(this.now, this.now.x + x, this.now.y + y, this.now.g, this.distance(x, y));

if ((x != 0 || y != 0)

&& this.now.x + x >= 0 && this.now.x + x < this.maze[0].length

&& this.now.y + y >= 0 && this.now.y + y < this.maze.length

&& this.maze[this.now.y + y][this.now.x + x] != -1

&& !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) {

node.g = node.parent.g + 1.;

node.g += maze[this.now.y + y][this.now.x + x];

this.open.add(node);

}

}

}

Collections.sort(this.open);

}

public static void main(String[] args)

{

int[][] maze = {

{ 0, 0, 0, 0, 0, 0, 0, 0},

{ 0, 0, 0, 0, 0, 0, 0, 0},

{ 0, 0, 0,100,100,100, 0, 0},

{ 0, 0, 0, 0, 0,100, 0, 0},

{ 0, 0,100, 0, 0,100, 0, 0},

{ 0, 0,100, 0, 0,100, 0, 0},

{ 0, 0,100,100,100,100, 0, 0},

{ 0, 0, 0, 0, 0, 0, 0, 0},

};

ATreeSearch as = new ATreeSearch(maze, 0, 0, true);

List<Node> path = as.findPathTo(7, 7);

if (path != null) {

path.forEach((n) -> {

System.out.print("[" + n.x + ", " + n.y + "] ");

maze[n.y][n.x] = -1;

});

System.out.printf("\nTotal cost: %.02f\n", path.get(path.size() - 1).g);

for (int[] maze_row : maze) {

for (int maze_entry : maze_row) {

switch (maze_entry) {

case 0:

System.out.print("|_|");

break;

case -1:

System.out.print("|_|");

break;

default:

System.out.print("|@|");

}

}

System.out.println();

}

}

}

}