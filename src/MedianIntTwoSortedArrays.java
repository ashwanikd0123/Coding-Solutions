public class MedianIntTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            n1 = nums1.length;
            n2 = nums2.length;
        }

        int totalLength = n1 + n2;
        int leftSize = totalLength / 2;
        if (totalLength % 2 != 0) {
            leftSize++;
        }

        int i = 0;
        int j = n1;

        int x1 = 0;
        int x2 = 0;
        while (i <= j) {
            int s1 = (i + j) / 2;
            int s2 = leftSize - s1;

            int l1 = (s1 > 0) ? nums1[s1 - 1] : Integer.MIN_VALUE;
            int r1 = (s2 > 0) ? nums2[s2 - 1] : Integer.MIN_VALUE;

            int l2 = (s1 < n1) ? nums1[s1] : Integer.MAX_VALUE;
            int r2 = (s2 < n2) ? nums2[s2] : Integer.MAX_VALUE;

            if (l1 <= r2 && r1 <= l2) {
                x1 = x2 = Math.max(l1, r1);
                if ((n1 + n2) % 2 == 0) {
                    x2 = Math.min(l2, r2);
                }
                break;
            }

            if (l1 > r2) {
                j = s1 - 1;
            } else {
                i = s1 + 1;
            }
        }

        return (x1 + x2) / 2.0;
    }
}
