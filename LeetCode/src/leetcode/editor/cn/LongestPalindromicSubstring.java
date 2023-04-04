/**
  * 题目Id：5
  * 题目：最长回文子串
  * 日期：2021-08-29 21:54:10
*/
//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 👍 4034 👎 0

package leetcode.editor.cn;

import sun.security.util.Length;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome("ac")
        );
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        //空串或一个直接返回
        if(s.length()==0||s.length()==1){
            return s;
        }
        //记录长度
        int length=s.length();
        //接受最长字符串
        String longestStr="";
        //确定中心传一个下标确定两个中心
        for(int i=0;i<length;++i){
            String s1 = getString(i, s);
            //返回值比较
            longestStr=longestStr.length()>=s1.length()?longestStr:s1;
        }
        return longestStr;
    }
    public String getString(int index,String s){
        //用来判断以哪方式结束循环
        boolean flag=true;
        int lp=index,rp=index;
        //取元素为中心结果为偶数情况
        while(lp>0&&rp<s.length()-1){
            if(!(s.charAt(--lp)==s.charAt(++rp))){
                flag=false;
                break;
            }
        }
        //截取
        String indexString1="";
        if((lp==0||rp==s.length()-1)&&flag){//边界终止
            indexString1 = s.substring(lp, rp+1);
        }else{//不等终止
            indexString1=s.substring(lp+1,rp);
        }
        //取元素之间的右边为中心偶数情况
        //指针复原
        lp=index;
        rp=index;
        flag=true;
        while(lp>=0&&rp<s.length()-1){
            if(!(s.charAt(lp--)==s.charAt(++rp))){
                flag=false;
                break;
            }
        }
        String indexString2="";
        if((lp==-1||rp==s.length()-1)&&flag){
            indexString2 = s.substring(lp+1, rp+1);
        }else{
            indexString2=s.substring(lp+2,rp);
        }
        //两个中心只返回较长的结果
        return indexString1.length()>=indexString2.length()?indexString1:indexString2;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

} 
