public class State {
    int[][] stateVal;
    State parent;
    int depth;

    public State(int[][] stateVal, State parent, int depth){
        this.stateVal = stateVal;
        this.parent = parent;
        this.depth = depth;
    }
}
