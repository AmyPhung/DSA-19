public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        // TODO
        return firstBadVersionHelper(1,n,isBadVersion);
    }

    private static long firstBadVersionHelper(long start, long end, IsFailingVersion isBadVersion) {
        if (end-start < 15){
            for (long n=start; n<=end; n++) {
                if (isBadVersion.isFailingVersion(n)) {
                    return n;
                }
            }
        } else {
            if (isBadVersion.isFailingVersion((end-start)/2 + start)) {
                return firstBadVersionHelper(start, (end-start)/2 + start, isBadVersion);
            } else {
                return firstBadVersionHelper((end-start)/2 + start, end, isBadVersion);
            }
        }
        return -1;
    }
}
