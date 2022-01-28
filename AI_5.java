// To write a program for 8 puzzle problem with breadth first search



//Node.java

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

public class Node {

int i, j;

Integer nodeVal;

Integer[][] state = new Integer[3][3];

List<Node> neighbors = new ArrayList<>();

String pathToGoal = "";

Integer costOfPath = 0;

int searchDepth = 0;

int maxSearchDepth = 0;

public Node(Integer nodeVal, int i, int j, Integer[][] state, String pathToGoal,Integer costOfPath)

{

this.nodeVal = nodeVal;

this.i = i;

this.j = j;

this.state = state;

this.pathToGoal = pathToGoal;

this.costOfPath = costOfPath;

this.searchDepth = costOfPath;

}

private void getRightNeighbor() {

Integer[][] stateTemp = new Integer[3][3];

for(int k = 0; k < 3; k++)

{

for(int l = 0; l < 3; l++)

{

stateTemp[k][l] = state[k][l];

}

}

int i1 = i, j1 = j + 1;

Integer nodeValTemp;

if(j + 1 < 3)

{

nodeValTemp = stateTemp[i1][j1];

Integer temp = stateTemp[i1][j1];

stateTemp[i1][j1] = stateTemp[i][j];

stateTemp[i][j] = temp;

Node tempNode = new Node(nodeValTemp, i1, j1, stateTemp, pathToGoal+" Right", costOfPath+1);

neighbors.add(tempNode);

}

}

private void getLeftNeighbor() {

Integer[][] stateTemp = new Integer[3][3];

for(int k = 0; k < 3; k++)

{

for(int l = 0; l < 3; l++)

{

stateTemp[k][l] = state[k][l];

}

}

int i1 = i, j1 = j - 1;

Integer nodeValTemp;

if(j - 1 >= 0)

{

nodeValTemp = stateTemp[i1][j1];

Integer temp = stateTemp[i1][j1];

stateTemp[i1][j1] = stateTemp[i][j];

stateTemp[i][j] = temp;

Node tempNode = new Node(nodeValTemp, i1, j1, stateTemp, pathToGoal+" Left", costOfPath+1);

neighbors.add(tempNode);

}

}

private void getBottomNeighbor() {

Integer[][] stateTemp = new Integer[3][3];

for(int k = 0; k < 3; k++)

{

for(int l = 0; l < 3; l++)

{

stateTemp[k][l] = state[k][l];

}

}

int i1 = i + 1, j1 = j;

Integer nodeValTemp;

if(i + 1 < 3)

{

nodeValTemp = stateTemp[i1][j1];

Integer temp = stateTemp[i1][j1];

stateTemp[i1][j1] = stateTemp[i][j];

stateTemp[i][j] = temp;

Node tempNode = new Node(nodeValTemp, i1, j1, stateTemp, pathToGoal+" Bottom", costOfPath+1);

neighbors.add(tempNode);

}

}

private void getTopNeighbor() {

Integer[][] stateTemp = new Integer[3][3];

for(int k = 0; k < 3; k++)

{

for(int l = 0; l < 3; l++)

{

stateTemp[k][l] = state[k][l];

}

}

int i1 = i - 1, j1 = j;

Integer nodeValTemp;

if(i - 1 >= 0)

{

nodeValTemp = stateTemp[i1][j1];

Integer temp = stateTemp[i1][j1];

stateTemp[i1][j1] = stateTemp[i][j];

stateTemp[i][j] = temp;

Node tempNode = new Node(nodeValTemp, i1, j1, stateTemp, pathToGoal+" Top", costOfPath+1);

neighbors.add(tempNode);

}

}

public Node() {

super();

}

public int getI() {

return i;

}

public void setI(int i) {

this.i = i;

}

public int getJ() {

return j;

}

public void setJ(int j) {

this.j = j;

}

public Integer getNodeVal() {

return nodeVal;

}

public void setNodeVal(Integer nodeVal) {

this.nodeVal = nodeVal;

}

public Integer[][] getState() {

return state;

}

public void setState(Integer[][] state) {

this.state = state;

}

public List<Node> getNeighbors() {

getTopNeighbor();

getBottomNeighbor();

getLeftNeighbor();

getRightNeighbor();

return neighbors;

}

public String getPathToGoal() {

return pathToGoal;

}

public void setPathToGoal(String pathToGoal) {

this.pathToGoal = pathToGoal;

}

public void setNeighbors(List<Node> neighbors) {

this.neighbors = neighbors;

}

@Override

public int hashCode() {

final int prime = 31;

int result = 1;

result = prime * result + ((nodeVal == null) ? 0 : nodeVal.hashCode());

return result;

}

@Override

public boolean equals(Object obj) {

if (this == obj)

return true;

if (obj == null)

return false;

if (getClass() != obj.getClass())

return false;

Node other = (Node) obj;

if (nodeVal == null) {

if (other.nodeVal != null)

return false;

} else if (!nodeVal.equals(other.nodeVal))

return false;

return true;

}

public Integer getCostOfPath() {

return costOfPath;

}

public void setCostOfPath(Integer costOfPath) {

this.costOfPath = costOfPath;

}

public int getSearchDepth() {

return searchDepth;

}

public void setSearchDepth(int searchDepth) {

this.searchDepth = searchDepth;

}

public int getMaxSearchDepth() {

return searchDepth+1;

}

public void setMaxSearchDepth(int maxSearchDepth) {

this.maxSearchDepth = maxSearchDepth;

}

}


//BFS.java

import java.util.ArrayList;

import java.util.Arrays;

import java.util.LinkedList;

import java.util.List;

import java.util.Queue;

public class BFS {

Integer[][] initialState = new Integer[3][3];

Node root;

Node currentNode;

Integer[][] goalState = {{0,1,2},{3,4,5},{6,7,8}};

Queue<Node> fringe = new LinkedList<Node>();

List<Node> exploredNodes = new ArrayList<>();

public BFS(String... state)

{

int index = 0;

for(int i = 0; i < 3; i++)

{

for(int j = 0; j < 3; j++)

{

if(state[index].equals("0")) {

root = new Node(0, i, j,initialState,"",0);

}

initialState[i][j] = Integer.parseInt(state[index++]);

}

}

root.setState(initialState);

}

public boolean solve()

{

boolean solved = false;

fringe.add(root);

while(!fringe.isEmpty())

{

currentNode = fringe.poll();

exploredNodes.add(currentNode);

if(gloalReached())

{

solved = true;

printCurrentState();

return solved;

}

else

{

printCurrentState();

}

for(Node neighbor : currentNode.getNeighbors())

{

if(exploredNodes.indexOf(neighbor) == -1 && fringe.contains(neighbor) == false)

{

fringe.add(neighbor);

}

}

currentNode.setNeighbors(null);

}

return solved;

}

private boolean gloalReached() {

boolean success = true;

for(int i = 0; i < 3; i++)

{

for(int j = 0; j < 3; j++)

{

if(!currentNode.getState()[i][j].equals(goalState[i][j]))

{

success = false;

break;

}

}

if(success == true)

{

break;

}

}

return success;

}

private void printCurrentState() {

System.out.println("Current state after: " + currentNode.getPathToGoal());

for(int i = 0;i < 3; i++)

{

for(int j = 0; j < 3; j++)

{

System.out.print(currentNode.getState()[i][j]+"");

}

System.out.println("");

}

System.out.println("");

}

public Integer[][] getInitialState() {

return initialState;

}

public void setInitialState(Integer[][] initialState) {

this.initialState = initialState;

}

public Node getRoot() {

return root;

}

public void setRoot(Node root) {

this.root = root;

}

public Node getCurrentNode() {

return currentNode;

}

public void setCurrentNode(Node currentNode) {

this.currentNode = currentNode;

}

public Integer[][] getGoalState() {

return goalState;

}

public void setGoalState(Integer[][] goalState) {

this.goalState = goalState;

}

public Queue<Node> getFringe() {

return fringe;

}

public void setFringe(Queue<Node> fringe) {

this.fringe = fringe;

}

public List<Node> getExploredNodes() {

return exploredNodes;

}

public void setExploredNodes(List<Node> exploredNodes) {

this.exploredNodes = exploredNodes;

}

}

//Main.java

import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;

import java.text.SimpleDateFormat;

import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;

import java.util.Calendar;

import java.util.Date;

import java.util.Locale;

public class Main {

static final long MEGABYTE_FACTOR = 1024L * 1024L;

public static void main(String[] args) {

System.out.println("Starting bfs to solve 8-puzzle");

//long start = System.currentTimeMillis();

LocalDateTime startTime = LocalDateTime.now();

BFS bfs = new BFS("1","2","5","3","4","0","6","7","8");

boolean success = bfs.solve();

LocalDateTime endTime = LocalDateTime.now();

if(success)

{

System.out.println("\t\t\tStatistics:");

System.out.println("Path to goal: " + bfs.getCurrentNode().getPathToGoal());

System.out.println("Cost to goal: " + bfs.getCurrentNode().getCostOfPath());

System.out.println("Nodes expanded: "+bfs.getExploredNodes().size());

System.out.println("Search depth: " + bfs.getCurrentNode().getSearchDepth());

System.out.println("Max Search depth: " + bfs.getCurrentNode().getMaxSearchDepth());

//long hours = startTime.until( endTime, ChronoUnit.HOURS);

long seconds = startTime.until( endTime, ChronoUnit.SECONDS);

if(seconds == 0)

{

long milliSeconds = startTime.until( endTime, ChronoUnit.MILLIS);

System.out.println("running time: "+ milliSeconds+" MILLISECONDS");

}

else

{

System.out.println("running time: "+ seconds+" SECONDS");

}

final long MEGABYTE_FACTOR = 1024L * 1024L;

final DecimalFormat ROUNDED_DOUBLE_DECIMALFORMAT;

DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);

otherSymbols.setDecimalSeparator('.');

otherSymbols.setGroupingSeparator(',');

ROUNDED_DOUBLE_DECIMALFORMAT = new DecimalFormat("####0.00", otherSymbols);

ROUNDED_DOUBLE_DECIMALFORMAT.setGroupingUsed(false);

double totalMiB = bytesToMiB(getUsedMemory());

System.out.println(String.format("Max memory usage: %s Megabytes", totalMiB)); ;

}

else

{

System.err.println("no solution found!");

}

}

public static long getTotalMemory() {

return Runtime.getRuntime().totalMemory();

}

private static double bytesToMiB(long bytes) {

return ((double) bytes / MEGABYTE_FACTOR);

}

public static long getMaxMemory() {

return Runtime.getRuntime().maxMemory();

}

public static long getUsedMemory() {

return getTotalMemory() - getFreeMemory();

}

public static long getFreeMemory() {

return Runtime.getRuntime().freeMemory();

}

}