import java.util.*;

public class DepthFS {
   private int nodeExpanded = 0;
   private int searchDepth = 0;

    public boolean DFS(int[] initialState, int[] goalState){
        int[][] initialState2D;
        int[][] goalState2D;
        ArrayList<State> history = new ArrayList<>();
        Stack<State> frontier = new Stack<>();
        Set<State> explored = new HashSet<>();

        initialState2D = convertTo2D(initialState);
        goalState2D = convertTo2D(goalState);

        if (!solvable(initialState)){
            return false;
        }

        State s ;
        frontier.push(new State(initialState2D, null, 0));

        while (!(frontier.isEmpty())){
            s = frontier.pop();
            explored.add(s);
            history.add(s);

            if (Arrays.deepEquals(s.stateVal, goalState2D)){
                System.out.println("Path to goal is: ");
                for (int k = 0; k < history.size(); k++) {
                    for ( int i = 0; i < 3; i++ ){
                        for ( int j = 0; j < 3; j++ ){
                            System.out.print(history.get(k).stateVal[i][j]);
                        }
                        System.out.println();
                    }
                    System.out.println();
                }

                searchDepth = s.depth;

                System.out.println("Shortest Path to goal is: ");
                while (s.parent != null){
                    for ( int i = 0; i < 3; i++ ){
                        for ( int j = 0; j < 3; j++ ){
                            System.out.print(s.stateVal[i][j]);
                        }
                        System.out.println();
                    }
                    System.out.println();
                    s = s.parent;
                }
                for ( int i = 0; i < 3; i++ ){
                    for ( int j = 0; j < 3; j++ ){
                        System.out.print(initialState2D[i][j]);
                    }
                    System.out.println();
                }

                System.out.println("Nodes Expanded: " + nodeExpanded);
                System.out.println("Cost: " + (explored.size() - 1));
                System.out.println("Search Depth: " +searchDepth);
                return true;
            }

            ArrayList<int[][]> neighbours = getNeighbours(s.stateVal);
            for (int[][]neighbour : neighbours) {
                if (!findINFrontier(neighbour, frontier)
                        && !findINExplored(neighbour,explored)){
                    frontier.add(new State(neighbour, s, s.depth + 1));
                }
            }
        }
        return false;
    }

    private  Boolean solvable(int[] numbers) {
        int counter =0;
        boolean result = false;
        for (int i=0;i<numbers.length-1;i++){
            if (numbers[i]==0) continue;
            for(int j=i+1;j<numbers.length;j++){
                if (numbers[j] !=0 && numbers[i]>numbers[j]){
                    counter++;
                }

            }
        }
        if (counter%2==0)
            result = true;
        return result;
    }

    private boolean findINFrontier(int[][] neighbour, Stack<State> frontier){
        for (int i = 0; i < frontier.size(); i++) {
            if (Arrays.deepEquals(frontier.get(i).stateVal, neighbour)) {
                return true;
            }
        }
        return false;
    }

    private boolean findINExplored (int[][] neighbour, Set<State> explored) {
        for (Iterator<State> it = explored.iterator(); it.hasNext(); ) {
            State f = it.next();
            if (Arrays.deepEquals(neighbour, f.stateVal)){
                return true;
            }
        }
        return false;
    }

    private int[][] convertTo2D(int[] arr1d){
        int[][] arr2d = new int[3][3];
        for ( int i = 0; i < 3; i++ )
            System.arraycopy(arr1d, (i*3), arr2d[i], 0, 3);
        return arr2d;
    }

    private ArrayList<int[][]> getNeighbours (int[][] arr){
        ArrayList<int[][]> states = new ArrayList<>();
        int [] index = indexOf(arr);
        int x =index[0];
        int y = index[1];

        //right
        if (y + 1 < 3 && y + 1 >= 0) {
            nodeExpanded++;
            states.add(swap(arr,x,y,x,y+1));
        }
        //down
        if (x + 1 < 3 && x + 1 >= 0){
            nodeExpanded++;
            states.add(swap(arr,x,y,x+1,y));
        }
        //left
        if (y - 1 < 3 && y - 1 >= 0) {
            nodeExpanded++;
            states.add(swap(arr,x,y,x,y-1));
        }
        //up
        if (x - 1 < 3 && x - 1 >= 0){
            nodeExpanded++;
            states.add(swap(arr,x,y,x-1,y));
        }
        return states;
    }

    private int[] indexOf(int [][] ar) {
        int [] index = new int [2];
        index [0] = index[1] = -1;
        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                if(ar[i][j] == 0) {
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
        }
        return index;
    }

    private int[][] swap(int[][] array, int i1, int j1, int i2, int j2) {
        int[][]current = new int[3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                current[i][j]=array[i][j];
        int temp = current[i1][j1];
        current[i1][j1] = current[i2][j2];
        current[i2][j2] = temp;
        return current;
    }
}