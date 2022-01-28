//To write a program for travelling salesman problem using Brute Force

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import java.util.Map;

public class TSP {

private final Map<String, Map<String, Integer>> distances;

public TSP(Map<String, Map<String, Integer>> distances) {

this.distances = distances;

}

public static <T> void swap(T[] array, int first, int second) {

T temp = array[first];

array[first] = array[second];

array[second] = temp;

}

private static <T> void allPermutationsHelper(T[] permutation, List<T[]> permutations, int n) {

if (n <= 0) {

permutations.add(permutation);

return;

}

T[] tempPermutation = Arrays.copyOf(permutation, permutation.length);

for (int i = 0; i < n; i++) {

swap(tempPermutation, i, n - 1);

allPermutationsHelper(tempPermutation, permutations, n - 1);

swap(tempPermutation, i, n - 1);

}

}

private static <T> List<T[]> permutations(T[] original) {

List<T[]> permutations = new ArrayList<>();

allPermutationsHelper(original, permutations, original.length);

return permutations;

}

public int pathDistance(String[] path) {

String last = path[0];

int distance = 0;

for (String next : Arrays.copyOfRange(path, 1, path.length)) {

distance += distances.get(last).get(next);

last = next;

}

return distance;

}

public String[] findShortestPath() {

String[] cities = distances.keySet().toArray(String[]::new);

List<String[]> paths = permutations(cities);

String[] shortestPath = null;

int minDistance = Integer.MAX_VALUE;

for (String[] path : paths) {

int distance = pathDistance(path);

distance += distances.get(path[path.length - 1]).get(path[0]);

if (distance < minDistance) {

minDistance = distance;

shortestPath = path;

}

}

shortestPath = Arrays.copyOf(shortestPath, shortestPath.length + 1);

shortestPath[shortestPath.length - 1] = shortestPath[0];

return shortestPath;

}

public static void main(String[] args) {

Map<String, Map<String, Integer>> vtDistances = Map.of(

"Rutland", Map.of(

"Burlington", 67,

"White River Junction", 46,

"Bennington", 55,

"Brattleboro", 75),

"Burlington", Map.of(

"Rutland", 67,

"White River Junction", 91,

"Bennington", 122,

"Brattleboro", 153),

"White River Junction", Map.of(

"Rutland", 46,

"Burlington", 91,

"Bennington", 98,

"Brattleboro", 65),

"Bennington", Map.of(

"Rutland", 55,

"Burlington", 122,

"White River Junction", 98,

"Brattleboro", 40),

"Brattleboro", Map.of(

"Rutland", 75,

"Burlington", 153,

"White River Junction", 65,

"Bennington", 40));

TSP tsp = new TSP(vtDistances);

String[] shortestPath = tsp.findShortestPath();

int distance = tsp.pathDistance(shortestPath);

System.out.println("The shortest path is " + Arrays.toString(shortestPath) + " in " +

distance + " miles.");

}

}