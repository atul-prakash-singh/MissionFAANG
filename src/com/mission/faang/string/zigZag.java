package com.mission.faang.string;
//https://leetcode.com/problems/zigzag-conversion/
public class zigZag {

    public static void main(String[] args) {
        String s = "sangyatri";
        System.out.println(convert(s,3));
    }
    public static String convert(String s, int numRows) {

        if(numRows == 1)
            return s;
        StringBuilder[] strings = new StringBuilder[Math.min(numRows,s.length())];

        int cur = 0;
        boolean direction = false;

        for(char c : s.toCharArray()){
            if(strings[cur] == null)
                strings[cur] = new StringBuilder();
            strings[cur].append(c);
            if(cur==0 || cur==numRows-1)
                direction = !direction;
            cur += direction?1:-1;
        }

        StringBuilder res = new StringBuilder();
        for(StringBuilder temp : strings)
            res.append(temp);
        return res.toString();
    }
}
