package Algorithms;

/**
 * Created by luoxianzhuo on 2018/8/13 16:33
 *
 * @author luoxianzhuo
 * @copyright Copyright 2014-2018 JD.COM All Right Reserved
 */

/**
 * 198 House Robber
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

 示例 1:
 输入: [1,2,3,1]
 输出: 4
 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 偷窃到的最高金额 = 1 + 3 = 4 。

 示例 2:
 输入: [2,7,9,3,1]
 输出: 12
 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 偷窃到的最高金额 = 2 + 9 + 1 = 12 。

 思路：

 （1）将题目内容转化为通俗易懂的形式为：给定一个整数数组Arr，求解数组中连续的不相邻元素的和的最大值。
 例如：对于数组中的元素A1,A2,A3,A4，则需要判断A1+A3,A1+A4,A2+A4中的最大值即为所求。

 （2）该题是一道简单动态规划相关的题目，如果能够正确地找到其中的递推关系，那么该题就很容易了。
 对于n个数的数组，如果要求得其连续不相邻元素的最大值，那么我们只需求得n-1个数的最大值，以及求得n-2个数的最大值即可，
 这样就形成了求解该问题的子问题的最大值问题，所以很容易考虑出递推关系，假设数组为Arr[]，
 n个数的数组对应的不相邻连续元素的最大值用函数f(n)表示，则有f(n) = max{f(n-1), f(n-2)+A[n-1]}，其中n>=2，f(n)也称为递推关系。其
 中f(n-1)为n-1个元素的最大值，f(n-2)+Arr[n-1]为n-2个元素的最大值加上数组第n个元素的值，因为要求元素不能相邻，所以会跳过第n-1个元素，这个应该很好理解。
 *
 */
public class HouseRobber {

    public static int rob(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int len = nums.length;
        int[] rt = new int[len];

        if (len == 1)
            return nums[0];

        if (len == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }

        for (int i = 0; i < len; i++) {
            if (i == 0) {
                rt[i] = nums[i];
            } else if (i == 1) {
                rt[i] = Math.max(rt[i - 1], nums[i]);
            } else {
                rt[i] = Math.max(rt[i - 1], rt[i - 2] + nums[i]);
            }
        }
        return rt[len - 1] > rt[len - 2] ? rt[len - 1] : rt[len - 2];
    }

}