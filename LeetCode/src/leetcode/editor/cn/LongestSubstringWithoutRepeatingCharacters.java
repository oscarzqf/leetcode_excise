package leetcode.editor.cn;

//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 5974 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters{
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abccdefghijk"));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null){
            return 0;
        }
        if(s==""){
            return 0;
        }
        if(s.length()==1){
            return 1;
        }
        Map<Character,Integer>map=new HashMap<>();//元素与下标
        //Set<Character> ch=new HashSet<>();//记录字符
        int n=s.length();
        int li=0,ri=0;//两个指针初始位置
        int maxLength=0;
        //查找以第li个开始的最长子串，就可以包含所有情况
        while(li<n){
            /*if(li!=0){
                map.remove(s.charAt(li-1));//出列
            }*/
            while(ri<n&&map.containsKey(s.charAt(ri))==false){//不包含且未越界
                map.put(s.charAt(ri),ri);//入列
                ++ri;//指针后移
            }

            if(ri<n&&map.get(s.charAt(ri))!=null){
                int flag=map.get(s.charAt(ri));
                int flag1=flag-li;
                while(flag1>=0){
                    map.remove(s.charAt(li++));
                    flag1--;
                }
            }
            maxLength=Math.max(maxLength,ri-li);
            if(maxLength>=(n-li)){
                break;//余下不用比了，超不过已经有的长度
            }
        }

        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
