# 1.两数之和

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        int[] targets=new int[2];
        for(int i=0;i<nums.length;++i){
            if(map.containsKey(nums[i])==false){
                map.put(target-nums[i],i);
            }else{
                targets[0]=map.get(nums[i]);
                targets[1]=i;
                break;
            }
        }
       return targets;
    }
}
```





# 2.两数相加

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3=new ListNode();//存储链表l3,返回时头结点去掉
        ListNode firstNode=l3;//记录头结点
        int i=1,j=1;//计数l1,l2
        ListNode l1s=l1;//记录指针
        ListNode l2s=l2;
        while((l1s=l1s.next)!=null){
            ++i;
        }
         while((l2s=l2s.next)!=null){
            ++j;
        }
        ListNode longList=i>=j?l1:l2;//取最长
        ListNode shortList=i<j?l1:l2;//取最短
        int num1=0,num2=0;//l3新节点与进位
        while(shortList!=null){//处理短链表
            int nums=longList.val+shortList.val+num2;
            if(nums<=9){
                num1=nums;
                num2=0;
            }else{
                num2=nums/10;
                num1=nums%10;
            }
            shortList=shortList.next;//移动指针
            longList=longList.next;
            ListNode newNode=new ListNode(num1);
            l3.next=newNode;
            l3=newNode;
        }
        while(longList!=null){//处理长链接表
            int nums=longList.val+num2;
            if(nums<=9){
                num1=nums;
                num2=0;
            }else{
                num2=nums/10;
                num1=nums%10;
            }
            longList=longList.next;
            ListNode newNode=new ListNode(num1);
            l3.next=newNode;
            l3=newNode;
        }
        if(num2!=0){//处理余数
            ListNode newNode=new ListNode(num2);
            l3.next=newNode;
            l3=newNode;
        }
        return firstNode.next;
        

    }
}
```





# 3.无重复最长子串

```java
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
        Set<Character> ch=new HashSet<>();//记录字符
        int n=s.length();
        int li=0,ri=0;//两个指针初始位置
        int maxLength=0;
        //查找以第li个开始的最长子串，就可以包含所有情况
        for(li=0;li<n;++li){
            if(li!=0){
                ch.remove(s.charAt(li-1));
            }
            while(ri<n&&ch.contains(s.charAt(ri))==false){//不包含且未越界
                ch.add(s.charAt(ri));//入集合
                ++ri;//指针后移
            }
            maxLength=Math.max(maxLength,ri-li);
            if(maxLength>=(n-li)){
                break;//余下不用比了，超不过
            }
        }

        return maxLength;
    }
}
```





# 4.寻找两个正序数组的中位数

```java
做法1
classSolution{
    publicdoublefindMedianSortedArrays(int[] nums1, int[] nums2){
          int lengthSum = nums1.length + nums2.length;
            double result = 0;
            int i = 0, j = 0, k = 0;
            LinkedList<Integer> tempList = newLinkedList<>();
            while(i < nums1.length && j < nums2.length) {//从小到大合并数组
                if(nums1[i] <= nums2[j]) {
                    tempList.add(nums1[i]);
                    ++i;
                    ++k;
                } else{
                    tempList.add(nums2[j]);
                    ++j;
                    ++k;
                }
                if(k==lengthSum / 2+1) {//如果已经超过中位数所在长度，停止
                    break;
                }
            }
if(k != lengthSum / 2+1) {//如果未超过直接合并剩下的
                while(i<nums1.length){
                    tempList.add(nums1[i++]);
                }
                while(j<nums2.length){
                    tempList.add(nums2[j++]);
                }
}
            if(lengthSum%2!=0){//奇偶判断
                result=tempList.get((lengthSum-1)/2);
            }else{
                result=((int)tempList.get((lengthSum/2)-1)+(int)tempList.get(lengthSum/2))/2.0;
            }
            return result;
    }
}
思路：使用链表对两个数组进行合并，直到长度大于中位数位置

```



```java
做法2：
思路：根据中位数的定义，左边数字数量等于右边数字数量
左边的最大值小于右边的最小值，奇数中位数为左边最大值，偶数个为左边最大值与右边最
小值之和一半

```



```java
做法3：
二分法找第K小的数

public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    int left = (n + m + 1) / 2;//两个数左边的第n数
    int right = (n + m + 2) / 2;//两个数右边的数
    //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
    return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;  //调用两次得到两个不同K对应值
}
    
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;//记录数组1剩余长度
        int len2 = end2 - start2 + 1;//记录数组2剩余长度
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);//保证len1<len2
        if (len1 == 0) return nums2[start2 + k - 1];//数组1长度为0时返回

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);//k=1时说明找到且为开头
//返回两个中小的(start+1-1)

	//更新比较的起始位置，选择k/2与len中较小的元素，避免下标越界
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
	//比较
        if (nums1[i] > nums2[j]) {
	//更新起始位置和K,即减掉已经比较过的元素，成为新的第k小
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }



```







# 5.最长回文子串

思路：扩展中心：以每一个元素及与右边元素的中间为中心，向外扩展，考虑结果的奇偶性，一共有

2n-1个中心

空间复杂度：0(1),常数个变量

时间复杂度：0(n²)

代码：可将第二个方法优化直接记录下标返回，最后再截取

```java
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
```

![](D:\java\imagesrecord\QQ截图20211005211559.png)



# 6.Z字形变换

方式1：沿z的方向进行字符的存储

空间复杂度：0(n)

时间复杂度：0(n²)

```java
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
```

![](D:\java\imagesrecord\QQ截图20211007225157.png)

 宣告失败，存储方式原因导致后面还要遍历一遍链表，导致时间复杂度高



方式二：

空间复杂度：0(n)

时间复杂度：0(n)

```java
public String convert(String s, int numRows) {
		//只有1行直接返回
        if (numRows == 1) return s;
		//创建可变数组类型的链表，免去后面在转换
        List<StringBuilder> rows = new ArrayList<>();
    	//实例化可变字符串
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());
		//当前行
        int curRow = 0;
    	//当前方向
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            //添加
            rows.get(curRow).append(c);
            //判断是否是第一行或最后一行
            if (curRow == 0 || curRow == numRows - 1) 
                //每次到最上或最下方向才变化
                goingDown = !goingDown;
            //以后对于涉及方向变化可采用这种该方式
            curRow += goingDown ? 1 : -1;
        }
		//结果处理
        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

```

总结：对于字符串长度发生变化的采用StringBuilder，以后对于涉及方向变化可采用这种该方式
            curRow += goingDown ? 1 : -1;来避免使用循环





# 7.整数反转

方式1：使用字符串+抛异常处理

空间复杂度：0(n)

时间复杂度：0(n)

字符串方法占用内存且处理速度慢

```java
public int reverse(int x) {
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
```

方式2：数学运算方法

```java
    public int reverse(int x) {
        if(-9<=x&&x<=9){
            return x;
        }
        int result=0;
        while (x!=0){
            //取末尾的数
            int lastplace=x%10;
             x=x/10;
            //判断是否超过范围
            if(result>214748364||((result==214748364)&&(lastplace>7))){
                return 0;
            }
            if(result<-214748364||result==-214748364&&lastplace<-8){
                return 0;
            }
           result=result*10+lastplace;

        }
        return result;
    }
```

<img src="D:\java\imagesrecord\QQ截图20211008130927.png" style="zoom:50%;" />

<img src="D:\java\imagesrecord\QQ截图20211008130944.png" style="zoom:50%;" />



# 8.字符串转换整数(atoi)

时间复杂度：O(n) 遍历字符串

空间复杂度：O(1)

```java
public int myAtoi(String s){
        int len=s.length();
        char[] chars = s.toCharArray();
        //索引和指示正负默认为正
        int j=0,sign=1;
        while (j<len&&chars[j]==' '){
            ++j;//去掉空格
        }
        if(j==len){
            return 0;//s全为空格
        }
        //验证第一个字符类型
        if(chars[j]=='+'){
            ++j;
        }else if(chars[j]=='-'){
            ++j;
            sign=-1;//负数
        }
        //结果
        int result=0;
        while(j<len){
            if(chars[j]>'9'||chars[j]<'0'){
                break;
            }
            //判断是否越界,换成提供的几个常量
            //转化为数字
            int curnum=(chars[j]-'0')*sign;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && curnum > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (result < Integer.MIN_VALUE / 10 || (result  == Integer.MIN_VALUE / 10 && curnum <Integer.MIN_VALUE % 10)) {
                return Integer.MIN_VALUE;
            }
            result=result*10+curnum;
            ++j;

        }
        return result;
    }
```

![](D:\java\imagesrecord\QQ截图20211009202510.png)

遇到的问题：在curname时没有加符号导致后面进行越界判断时出现错误



# 9.回文数

```java
public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        if(0<=x&&x<=9){
            return true;
        }
        int y=x;
        int res=0;
        while(y!=0){
            int temp=y%10;
            res=res*10+temp;
            y=y/10;
        }
        return x==res;
    }
```

![](D:\java\imagesrecord\QQ截图20211009204925.png)

易错点：注意保留原来的x用于反转后的比较



# 动态规划总结：

###### 1.递推问题

类似于数列，通过前几项的值推到出当前项的值。

例：斐波那契数列，通过递推公式将问题线性化

```java
public void climbStairs(int n){
        int[] f=new int[100];
        f[0]=f[1]=1;//初始条件
        for (int i = 2; i <=n; i++) {
            f[i]=f[i-1]+f[i-2];//递推
        }
    }
```

###### 2.线性DP

a.最小花费

  数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值cost[i]（下标从 0 开始）。每当爬上一个阶梯，都要花费对应的体力值，一旦支付了相应的体力值，就可以选择 向上爬一个阶梯 或者 爬两个阶梯。求找出达到楼层顶部的最低花费。在开始时，可以选择从下标为 0  或 1  的元素作为初始阶梯。

 令走到第 i 层的最小消耗为 f [ i ] 。假设当前的位置在 i 层楼梯，那么只可能从 i−1 层过来，或者i−2 层过来；
如果从 i − 1  层过来，则需要消耗体力值：f [ i − 1 ] + c o s t [ i − 1 ] 
如果从i−2 层过来，则需要消耗体力值：f [ i − 2 ] + c o s t [ i − 2 ] 
  起点可以在第 0 或者 第 1 层，于是有状态转移方程：

![](D:\java\imagesrecord\QQ截图20211011093122.png)

这个是求最小值，涉及到了动态取舍问题。时间复杂度变为O(n)

```java
public int minCostClimbStairs(int costSize,int[] cost){
        int[] f=new int[1024];
        f[0]=f[1]=0;
        for (int i = 2; i <=costSize; i++) {
            f[i]=Math.min(f[i-1]+cost[i-1],f[i-2]+cost[i-2]);
        }
        return f[costSize];
    }
 
```

b.最大子段和

  给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
![](D:\java\imagesrecord\QQ截图20211011094731.png)

```java
public int maxSubArray(int[] nums){
        int maxValue=nums[0];
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i]=nums[i];
            if(dp[i-1]>0){
                dp[i]+=dp[i-1];
            }
            maxValue=Math.max(maxValue,dp[i]);
        }
        return maxValue;
    }
```

​	c)最长单调子序列

```java
给定一个长度为 n ( 1 ≤ n ≤ 1000 )  的数组ai，求给出它的最长递增子序列的长度。
```
**最长单调子序列** 是指对于原数组序列，能够找到的元素个数最多的单调子序列。
   以 [1，2，4，6，3，5，9] 为例，它的最长单调子序列为：[1，2，4，6，9]，长度为 5；也可以是 [1，2，3，5，9]，长度同样为 5。

![](D:\java\imagesrecord\QQ截图20211011123613.png)

```java
//方式1：时间复杂度为0(n²)
public int maxSubArr(int[] nums){
        int maxSub=0;
        //存储以第i个结尾的子序列长度
        int[] L=new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            //每次先将以nums[i]结尾的最大递增序列长度L[i]初始化为1
            L[i]=1;
            for (int j = 0; j < i; j++){
                //从0-（i-1）寻找小于nums[i]的数
                if(nums[j]<nums[i]){
                    //寻找最优子结构
                   L[i]=Math.max(L[j]+1,L[i]);
                }
                if (L[i]>maxSub){
                    maxSub=L[i];//记录最大长度
                }
            }
        }
        //求出该子序列(补充)
        int index=0,length=maxSub;
        for (int i = 0; i < L.length; i++) {
            if(length==L[i]){
                index=i;//查找最长子序列的最后一个元素下标
            }
        }
        //逆向查找组成子序列的元素
        int[] seq=new int[length];//用于存储子序列
        seq[--length]=nums[index];
        for(int i=index;i>=0;i--){
            if(nums[i]<nums[index]&&L[i]==L[index]-1){
                seq[--length]=nums[i];
                index=i;//满足条件赋值
            }
        }
        for(int i:seq){
            System.out.print(i);
        }
        return  maxSub;//返回最大长度
    }
```



```java
//方式二：时间复杂的nlgn
public int maxSubArr(int[] nums){
        //动态规划中使用的数组，用于记录中间结果,下标从1开始,下标标示子序列长度
        //值表示存储的对应长度单调子序列的最小末尾，可以发现B中插入数据是有序的
        //可以使用二分查找，优化插入时间为logn
       int[]dp=new int[nums.length+1];
        int len=1;//用于标示dp数组的元素个数
        int start=0,end=len,mid;//用于二分查找
        dp[1]=nums[0];//初始化
        for (int i = 1; i < nums.length; i++) {
            //如果nums[i]大于当前的长度len的最后一个元素，长度增加
            if(nums[i]>dp[len]){
                len++;
                dp[len]=nums[i];
            }else{
                //不满足就二分查找nums[i]的插入位置
               start=1;end=len;
               while(start<=end){
                   mid=(start+end)/2;
                   if(dp[mid]<nums[i]){
                       start=mid+1;
                   }else{
                       end=mid-1;
                   }
                   dp[start]=nums[i];
               }

            }
        }
       return len;
    }
```

###### 3.二维DP

**a)最长公共子序列**

  给定两个数组序列a1,a2,...,an 和  b1,b2,...,bm，其中 n , m ≤ 1000n,m≤1000，求两个数组的最长公共子序列。

![](D:\java\imagesrecord\QQ截图20211015124058.png)

```java
 public int getLCSLength(int[] nums1,int[] nums2){
        //数组长度为0时返回0
        if(nums1.length*nums2.length==0){
            return 0;
        }
        //创建滚动数组，默认初始化为0，
        // 滚动数组为简化dp问题空间复杂度的一种方法
        //结果只与上一行有关，第一行，第二行，第三行由第二行得出，此时第一行没有用了
        //可以用新数据进行覆盖，只需要变量指示当前行即可
        int[][] f=new int[2][Math.max(nums1.length,nums2.length)+1];
        //表示当前行与上一行
        int cur=1,last=0;
        //数组1的每一个元素与数组2每一个元素比较，理解时一行一行看
        // 如果数组1中元素等于数组2，则对应长度+1，不等于的话就相当于
        // 长度还是原来的最大长度，最后存储的是结果
        for (int i = 0; i <nums1.length ; i++) {
            for (int j =0; j <nums2.length; j++) {
               if(nums1[i]==nums2[j]){
                   //j不从0开始相当于空出一列从f[1][1]开始存状态
                   f[cur][j+1]=f[last][j]+1;
               } else{
                   f[cur][j+1]=Math.max(f[cur][j],f[last][j+1]);
               }
            }
            //交换当前行
            cur=cur==1?0:1;
            last=last==0?1:0;
        }
        return f[last][nums2.length];
    }
```



**b)最小编辑距离**

长度为 n  的源字符串 a1,a2,...,an，经过一些给定操作变成长度为m 的目标字符串b1,b2,...bm，操作包括如下三种：
 -  Insert：在源字符串中插入一个字符，插入消耗为I；
 -  Delete：在源字符串中删除一个字符，删除消耗为 D；
 -  Replace：将源字符串中的一个字符替换成另一个字符，替换消耗为 R；
     求最少的总消耗，其中n,m≤1000。

![](D:\java\imagesrecord\QQ截图20211016105647.png)



![](D:\java\imagesrecord\QQ截图20211016105751.png)



```JAVA
public int minimumEditDistance(String sourceStr,String targetStr){
        //新建一个二维数组，用于存取两字符串的各个子字符串之间的最短编辑距离
        //其中新建的二维数组的行列都+1是因为最短编辑距离的矩阵的子字符串从空串""开始，
        // 把空串""考虑进去，因此行列都+1
        int[][] f=new int[sourceStr.length()+1][targetStr.length()+1];
        int I=1,D=1,R=1;//编辑消耗都为1时为莱文斯坦距离图
        //f[i][j]表示：字符串1的第0到第i-1个字符构成的子字符串 与
        // 字符串2的第0到第j-1个字符构成的子字符串 互相转换的最小编辑距离
        f[0][0]=0;//都为空串消耗0
        //targetStr为空
        for (int i = 1; i < sourceStr.length(); i++){
            f[i][0]=f[i-1][0]+D;
        }
        //sourceStr为空
        for (int j = 1; j < targetStr.length(); j++) {
            f[0][j]=f[0][j-1]+I;
        }
        //字符串都不是空串""时互相转换的最小编辑距离
        for (int i = 1; i < sourceStr.length()+1; i++) {
            for (int j = 1; j < targetStr.length()+1; j++) {
              f[i][j]=Math.min(f[i][j-1]+I,f[i-1][j]+D);
              if(sourceStr.charAt(i-1)!=targetStr.charAt(j-1)){
                  f[i][j]=Math.min(f[i][j],f[i-1][j-1]+R);
              }else{
                  f[i][j]=Math.min(f[i][j],f[i-1][j-1]);
              }
            }
        }
        //最后返回的是int型的两个字符串互相转换的最小编辑距离，
        // 该值是行列分别是1和2的长度的二维数组f的元素值
        return f[sourceStr.length()][targetStr.length()];
    }
```

**c)双串匹配问题**

给定一个 **匹配字符串 s** (只包含小写字母) 和一个 **模式字符串 p** (包含小写字母和两种额外字符：`'.'`和 `'*'`)，要求实现一个支持 `'.'`和 `'*'`的正则表达式匹配（`'*'`前面保证有字符）。
  `'.'`匹配任意单个字符
  `'*'`匹配零个或多个前面的那一个元素

![](D:\java\imagesrecord\QQ截图20211016132003.png)



# 10.正则表达式匹配

```java
public boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();

        // dp[i][j]:表示s的前i个字符，p的前j个字符是否能够匹配
        boolean[][] dp = new boolean[cs.length + 1][cp.length + 1];
        // s为空，p为空，能匹配上
        dp[0][0] = true;
        // p为空，s不为空，必为false(boolean数组默认值为false，无需处理)
        // s为空，p不为空，由于*可以匹配0个字符，所以有可能为true
        for (int j = 1; j <= cp.length; j++) {
            if (cp[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 填格子
        for (int i = 1; i <= cs.length; i++) {
            for (int j = 1; j <= cp.length; j++) {
                // 文本串和模式串末位字符能匹配上
                if (cs[i - 1] == cp[j - 1] || cp[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (cp[j - 1] == '*') { 
                    // 模式串末位是*
                    // 模式串*的前一个字符能够跟文本串的末位匹配上
                    if (cs[i - 1] == cp[j - 2] || cp[j - 2] == '.') {
                        dp[i][j] = dp[i][j - 2]      // *匹配0次的情况
                			|| dp[i - 1][j];     // *匹配1次或多次的情况
                    } else { // 模式串*的前一个字符不能够跟文本串的末位匹配
                        dp[i][j] = dp[i][j - 2];     // *只能匹配0次
                    }
                }
            }
        }
        return dp[cs.length][cp.length];
    }
```

![](D:\java\imagesrecord\QQ截图20211016152551.png)





