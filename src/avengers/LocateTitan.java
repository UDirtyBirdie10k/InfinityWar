 package avengers;
/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {

    private static int getMinCostNode(int[] minCost, boolean[]dijkstraSet){

        int lowest = 0;
        int temp = Integer.MAX_VALUE;        

        for(int i = 0; i < minCost.length; i++){

            if(minCost[i] < temp && dijkstraSet[i] == false){
    
                temp = minCost[i];
                lowest = i;

            }
        }
        return lowest;
    }
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }
 

    	// WRITE YOUR CODE HERE

        String LocateTitanInputFile  = args[0];
        String LocateTitanOutputFile = args[1];

        StdIn.setFile(LocateTitanInputFile);

        int numberOfGenerators = StdIn.readInt();

        double[][] generatorNumberFunctionality = new double[numberOfGenerators][2];

        for(int i = 0; i < numberOfGenerators; i ++){
            for(int j = 0; j < 2; j++){

                generatorNumberFunctionality[i][j] = StdIn.readDouble();

            }

        }

        int[][] adjMatrix = new int[numberOfGenerators][numberOfGenerators];

        for(int i = 0; i < numberOfGenerators; i ++){
            for(int j = 0; j < numberOfGenerators; j++){

                adjMatrix[i][j] = StdIn.readInt();

            }
        }

        for(int i = 0; i < numberOfGenerators; i ++){
            for(int j = 0; j < numberOfGenerators; j++){

                adjMatrix[i][j] = (int)(adjMatrix[i][j] / (generatorNumberFunctionality[j][1] * generatorNumberFunctionality[i][1]));

            }
        }

        int minCost[] = new int[numberOfGenerators];
        boolean[] dijkstraSet = new boolean[numberOfGenerators];
        
        for(int i = 0; i < minCost.length; i++){
            if(i == 0){
                minCost[i]= 0;
            }
            else{
                minCost[i] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < minCost.length -1; i++){

            int currentSource = getMinCostNode(minCost, dijkstraSet);

            dijkstraSet[currentSource] = true;

            for(int w = 0; w < minCost.length; w++){
                if(adjMatrix[currentSource][w] > 0){

                    if(dijkstraSet[w] == false && minCost[currentSource] != Integer.MAX_VALUE && minCost[w] > minCost[currentSource]
                    + adjMatrix[currentSource][w]){
                        minCost[w] = minCost[currentSource] + adjMatrix[currentSource][w];
                    }
                }
            }
        }

        
        StdOut.setFile(LocateTitanOutputFile);

        StdOut.println(minCost[numberOfGenerators-1]);

    }


}




