package Source;

public class Env {

    private int allowedRunTimeMinutes;

    private Assign currentBestSolution;

    private int evalCurrentBestSolution;

    public Assign getCurrentBestSolution() {
        return currentBestSolution;
    }

    /**
     * Function to set the current best solution in the env.
     *
     * @param currentBestSolution The assign of the current best solution.
     * @param evalCurrentBestSolution The eval of the current best solution
     */
    public void setCurrentBestSolution(Assign currentBestSolution, int evalCurrentBestSolution) {
        this.currentBestSolution = currentBestSolution;
        this.evalCurrentBestSolution = evalCurrentBestSolution;
    }

    public int getEvalCurrentBestSolution() {
        return evalCurrentBestSolution;
    }

}
