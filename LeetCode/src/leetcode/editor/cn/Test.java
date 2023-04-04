package leetcode.editor.cn;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.sql.Connection;
import java.sql.JDBCType;
import java.util.Arrays;

/**
 * @author oscarzqf
 * @description
 * @create 2021-10-11-9:12
 */
public class Test {
    @org.junit.Test
    public void test(){
      String s1="aab";
      String s2="c*a*b";
        System.out.println();
    }
    public int finMaxVal(int n,int m){
        final int maxn=10000;//背包容量
        int[] f=new int[maxn];//背包
        int[] w=new int[maxn];//物品重量
        int[] val=new int[maxn];//物品价格
        //n为物品数m为背包总体积
        for(int i = 1;i <= n;i++){
            for(int v = m;v > 0;v--){
                if(v >= w[i])
                    f[v] = Math.max(f[v],f[v-w[i]]+val[i]);
            }
        }
        return f[m];
    }
}
