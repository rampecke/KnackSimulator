public class SwapTuple {
    int indexHandCard = 0;
    int indexTableCard = 0;

    int pointsOnHandAfterSwap = 0;

    public SwapTuple(int indexHandCard, int indexTableCard, int pointsOnHandAfterSwap) {
        this.indexHandCard = indexHandCard;
        this.indexTableCard = indexTableCard;
        this.pointsOnHandAfterSwap = pointsOnHandAfterSwap;
    }

    public int getIndexHandCard() {
        return indexHandCard;
    }

    public int getIndexTableCard() {
        return indexTableCard;
    }

    public int getPointsOnHandAfterSwap() {
        return pointsOnHandAfterSwap;
    }
}
