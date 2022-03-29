package com.learn.alg.leetcode.array;

/**
 * <pre>
 *     给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 * </pre>
 */
public class MergeSortedArray {

    /**
     * 第一种方法:
     * <pre>
     *  创建一个和nums1相等长度的数组, 将合并之后的结果存储在中间结果中，然后将中间结果copy给nums1数组
     *
     *  这种解题方法, 因为要将nums1和nums2分别遍历一遍, 因此时间复杂度为O(m + n)
     *  空间复杂度, 因为使用和Nums1相同大小的temp数组，因此空间复杂度为O(m + n)
     * </pre>
     *
     * @param nums1 有序数组
     * @param m     数组长度
     * @param nums2 有序数组2
     * @param n     数组长度
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        int[] temp = new int[k];

        // 比较方式为, 从头向后比较
        for (int nums1Index = 0, nums2Index = 0, index = 0; index < k; ) {
            // nums1已经遍历完成, 只需要将nums2的数据拷贝就可以了
            if (nums1Index >= m) {
                temp[index++] = nums2[nums2Index++];
            }

            // num2已经遍历完成, 只需要将num1的数据拷贝就可以了
            else if (nums2Index >= n) {
                temp[index++] = nums1[nums1Index++];
            }

            // nums1的索引位置比nums2的索引位置要大, 因此将Nums1所在位置放入临时数组
            else if (nums1[nums1Index] >= nums2[nums2Index]) {
                temp[index++] = nums2[nums2Index++];
            }
            // nums1比nums2小, 将nums2的数字如数组
            else {
                temp[index++] = nums1[nums1Index++];
            }
        }

        for (int i = 0, len = temp.length; i < len; i++) {
            nums1[i] = temp[i];
        }
    }

    /**
     * 合并两个有序数组
     * <pre>
     *     解题思路:
     *
     *     针对本题中，因为nums1长度包含了nums2的长度，因此对于nums1中包含了空白的项, 因此我们可以从后向前比较
     *
     *     例如:
     *     [1,2,3,0,0,0]
     *     [2,5,6]
     *
     *     1) 比较3和6，将6移动到最后一个位置, -> [1,2,3,0,0,6]
     *     2) 比较3和5, 将5移动到导数第二个位置, -> [1,2,3,0,5,6]
     *     3) 比较3和2, 将3移动到倒数第三个位置, -> [1,2,3,3,5,6]
     *     4) 比较2和2, 将2移动到对应位置->[1,2,2,3,5,6]
     *
     *     这种算法实现，因为还是需要遍历num1和num2,所以时间复杂度还是O(m + n)
     *     但是因为在重新排序的时候，使用的nums1的空间，没有占用多余的空间，因此空间复杂度为O(1)
     * </pre>
     *
     * @param nums1 有序数组1
     * @param m     数组长度
     * @param nums2 有序数组2
     * @param n     数组长度
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        for (int num1Index = m - 1, num2Index = n - 1, index = k - 1; index >= 0; ) {
            // 判断是否遍历完成num2
            if (num2Index < 0) {
                return;
            }
            // num1遍历完成,
            else if (num1Index < 0) {
                nums1[index--] = nums2[num2Index--];
            }
            // 判断两则之间的关系
            else if (nums1[num1Index] >= nums2[num2Index]) {
                nums1[index--] = nums1[num1Index--];
            } else {
                nums1[index--] = nums2[num2Index--];
            }
        }
    }
}
