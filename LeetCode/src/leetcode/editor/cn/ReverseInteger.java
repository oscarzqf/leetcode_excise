/**
  * 题目Id：7
  * 题目：整数反转
  * 日期：2021-10-07 23:24:13
*/
//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−2³¹, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//
// 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//
// 
//输入：x = 120
//输出：21
// 
//
// 示例 4： 
//
// 
//输入：x = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= x <= 2³¹ - 1 
// 
// Related Topics 数学 👍 3127 👎 0

package leetcode.editor.cn;

import java.math.BigInteger;

public class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
        System.out.println(solution.reverse(
                121));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        if(-9<=x&&x<=9){
            return x;
        }
        int result=0;
        while (x!=0){
            //取末尾的数
            int lastplace=x%10;
            //判断是否超过范围
            if(result>214748364||((result==214748364)&&(lastplace>7))){
                return 0;
            }
            if(result<-214748364||result==-214748364&&lastplace<-8){
                return 0;
            }
           result=result*10+lastplace;
            x=x/10;

        }
        return result;
    }
        public int reverse1(int x) {
            int  y=x>=0?x:-x;
            char[] str=(y+"").toCharArray();
            for(int i=0,j=str.length-1;i<j;++i,--j){
                char temp=str[i];
                str[i]=str[j];
                str[j]=temp;
            }
            String result=new String(str);
            try {
                y=Integer.parseInt(result);
            } catch (NumberFormatException e) {
                y=0;
            }
            y=x>=0?y:-y;
            return y;
        }

}
//leetcode submit region end(Prohibit modification and deletion)

} 
