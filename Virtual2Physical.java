public class Virtual2Physical {
    public static int pageNum(long addr, int virtPageNum, long block) {
        if (block <= 0) return 0;
        int p = (int)(addr / block);
        if (p < 0) p = 0;
        if (p > virtPageNum) p = virtPageNum;
        return p;
    }
}