
public class Main {
    public static void main(String[] args) {
        int[] initialState = {1,2,5,3,4,0,6,7,8};
//        int[] initialState = {0,1,2,3,7,5,6,4,8};
        int[]goalState ={0,1,2,3,4,5,6,7,8};
        System.out.println("------------------Breadth-First Search-----------------------");
        BreadthFS bfs = new BreadthFS();
        long startb = System.currentTimeMillis();
        System.out.println(bfs.BFS(initialState, goalState));
        System.out.println("Execution time: " + ((System.currentTimeMillis() - startb) / 1000d) + " seconds");
        System.out.println("------------------Depth-First Search-----------------------");
        DepthFS dfs = new DepthFS();
        long startd = System.currentTimeMillis();
        System.out.println(dfs.DFS(initialState,goalState));
        System.out.println("Execution time: " + ((System.currentTimeMillis() - startd) / 1000d) + " seconds");

    }
}
