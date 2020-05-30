package leetcode.largest.rectangle.in.histogram;

/**
 * @leetcode     柱状图中的最大矩形
 * @author       taoruizhe
 * @date         2020/05/30
 */
public class Solution {
    /**
     * 基础思路1：滑动窗口，对每一个元素向左向右搜索至边界或较小值，然后记录围成的矩形面积，最后取最大面积
     * 基础思路2：从底至顶绘制矩形图，每次增加1高度。每次从左至右进行搜索，然后滑动窗口记录该高度下最长拓展距离
     * 思路拓展： 两种方式都可以，思路1时间复杂度高度依赖柱形图长度，思路2复杂度高度依赖柱形图高度，这里可以考虑判断。
     *
     * @param heights  给定一组高度数据，宽度都为1
     * @return         围成的最大矩形面积
     */
    public int largestRectangleArea(int[] heights) {
        // 检查柱状图的高度与宽度，为后面判断提供数据
        int heightMax = 0;
        int widthMax = heights.length;
        for (int height : heights) {
            if (height > heightMax) {
                heightMax = height;
            }
        }

        // 滑动窗口法获取矩形面积
        int areaMax = 0;
        for (int i = 0; i < heights.length; i++) {
            int flag = heights[i];
            int left, right;
            for (left = i - 1; left >= 0; left--) {
                if (heights[left] < heights[i]) {
                    break;
                }
            }
            for (right = i + 1; right < heights.length; right++) {
                if (heights[right] < heights[i]) {
                    break;
                }
            }
            int width = right - left - 1;
            if (areaMax < width * heights[i]) {
                areaMax = width * heights[i];
            }
        }

        return areaMax;
    }


    public static void main(String[] args) {
        int [] heights = {2,1,5,6,2,3};
        System.out.println(new Solution().largestRectangleArea(heights));
    }
}
