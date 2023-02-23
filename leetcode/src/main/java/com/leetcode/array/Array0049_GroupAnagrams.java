package com.leetcode.array;

import java.util.*;

/**
 * level: medium
 * <p>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * <p>
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Example 2:
 * <p>
 * Input: strs = [""]
 * <p>
 * Output: [[""]]
 * <p>
 * Example 3:
 * <p>
 * Input: strs = ["a"]
 * <p>
 * Output: [["a"]]
 */
public class Array0049_GroupAnagrams {

    public static void main(String[] args) {
        Array0049_GroupAnagrams algorithm = new Array0049_GroupAnagrams();
        String[] strs = {""};
        System.out.println(algorithm.groupAnagrams(strs));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        return myAnswer(strs);
    }

    private List<List<String>> myAnswer(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            if (!map.containsKey(s)){
                map.put(s, new ArrayList<>());
            }
            List<String> list = map.get(s);
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }
}
