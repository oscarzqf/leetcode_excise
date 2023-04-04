/**
  * 题目Id：6
  * 题目：Z 字形变换
  * 日期：2021-10-05 21:43:24
*/
//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
// Related Topics 字符串 👍 1297 👎 0

package leetcode.editor.cn;

import java.awt.font.NumericShaper;
import java.util.Iterator;
import java.util.LinkedList;

public class ZigzagConversion {
    public static void main(String[] args) {
        Solution solution = new ZigzagConversion().new Solution();
        System.out.println(solution.convert("AB",1));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convert(String s, int numRows) {
        if(numRows==1){
            return s;
        }
        //想到使用数组+链表来装结果
        LinkedList[] lists=new LinkedList[numRows];
        //初始化链表
        for (int i = 0; i < numRows; i++) {
            lists[i]=new LinkedList();
        }
        //装数据
        for (int i = 0, j = 0; i <s.length(); i++,j++) {
            //装竖着的数据
            lists[j].add(s.charAt(i));
            //装中间的数据
            if (j==numRows-1){
                for(j=j-1;j>0&&i<s.length()-1;--j){
                    lists[j].add(s.charAt(++i));
                }
                //循环结束后j会+1,保证j为0，开始新的一轮竖着的数据
                j=-1;
            }
        }
        //处理结果
        String result="";
        for(int k=0;k<numRows;++k){
            for (int j = 0; j <lists[k].size() ; j++) {
                result+=(char)lists[k].get(j);
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

} 
