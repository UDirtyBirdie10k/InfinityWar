package avengers;

import java.util.ArrayList;

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes. 
 * Then, write into the output file a boolean (true or false) indicating if 
 * the graph is still connected.
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 * Read from PredictThanosSnapInputFile with the format:
 *    1. seed (long): a seed for the random number generator
 *    2. p (int): number of people (vertices in the graph)
 *    2. p lines, each with p edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note: the last p lines of the PredictThanosSnapInputFile is an ajacency matrix for
 * an undirected graph. 
 * 
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the matrix, 0-1, 1-0, 0-2, 2-0).
 * 
 * 0 1 1 0
 * 1 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * Delete random vertices from the graph. You can use the following pseudocode.
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) { 
 *     if (StdRandom.uniform() <= 0.5) { 
 *          delete vertex;
 *     }
 * }
 * Answer the following question: is the graph (after deleting random vertices) connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 * 
 * Note 1: a connected graph is a graph where there is a path between EVERY vertex on the graph.
 * 
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, isConnected is true if the graph is connected,
 *   false otherwise):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(isConnected);
 * 
 * @author Yashas Ravi
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/PredictThanosSnap predictthanossnap.in predictthanossnap.out
*/

public class PredictThanosSnap {
	 
    public static void main (String[] args) {
 
        if ( args.length < 2 ) {
            StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>");
            return;
        }
        
    	// WRITE YOUR CODE HERE

        String PredictThanosSnapInputFile = args[0];
        String PredictThanosSnapOutputFile = args[1]; 

        StdIn.setFile(PredictThanosSnapInputFile);

        long assignedseed = StdIn.readLong();
        int thanosSnap = StdIn.readInt();
        int [][] blackWitchery  = new int [thanosSnap][thanosSnap];
        ArrayList<Integer> Prediction = new ArrayList<>();

        for (int i = 0; i < thanosSnap; i++)
        {
            for (int j=0; j<thanosSnap; j++){
                blackWitchery[i][j] = StdIn.readInt();
                }
        }

        StdRandom.setSeed(assignedseed);

           for (int i=0; i<thanosSnap; i++){ 
            for (int j=0; j<thanosSnap; j++)
            {
                if (StdRandom.uniform()<= 0.5){
                    blackWitchery[i][j] = 0;
                    blackWitchery[j][i] = 0;
                 }else{
                    Prediction.add(i);
                    }
            }

        }
        boolean lol =  true;
        for(int i  = 0; i < thanosSnap; i++){
                 lol = false;
            for(int j = 0; j < thanosSnap; j++){
                if(blackWitchery[i][j] == 1){
                    lol = true;
                }
            }
            if(lol ==  false){
                break;
            }
        }

        StdOut.setFile(PredictThanosSnapOutputFile);
        StdOut.println(lol);
    }
}

//         String PredictThanosSnapInputFile  = args[0];
//         String PredictThanosSnapOutputFile = args[1];

//         StdIn.setFile(PredictThanosSnapInputFile);

//         long seed = StdIn.readLong();
//         int numberOfPeople = StdIn.readInt();

//         int[][] adjMatrix = new int[numberOfPeople][numberOfPeople]; 
//         ArrayList<Integer> arl = new ArrayList<Integer>();
 

//         for(int i = 0; i < numberOfPeople; i++){
//             for(int j = 0; j < numberOfPeople; j++){

//                 adjMatrix[i][j] = StdIn.readInt();

//             }
//         }

//         StdRandom.setSeed(seed);


//         for (int i = 0; i < numberOfPeople; i++) { 
//             for(int j = 0; j < numberOfPeople; j++){
                
//                 if (StdRandom.uniform() <= 0.5) { 
                    
//                     adjMatrix[i][j] = 0;
//                     adjMatrix[j][i] = 0;

//                 }
//                 else{

//                     arl.add(i);

//                 }
            
//             }
//         }

//         boolean connect = true;

//         for(int i = 0; i < numberOfPeople; i++){

//             connect = false;

//             for(int j = 0; j < numberOfPeople; j++){
                    
//                     if(adjMatrix[i][j] == 1){
                        
//                         connect = true;
                    
//                     }
//             }
//             if(connect == false){
//                 break;
//             }
            
//         }
        
//         StdOut.setFile(PredictThanosSnapOutputFile);

//         StdOut.print(connect);
//     }
// }
