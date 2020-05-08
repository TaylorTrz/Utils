package leetcode.merge.k.sorted.lists;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author taoruizhe
 * @details 合并K个排序链表:合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * @date 2020/04/26
 */
public class Solution {

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode sorted = head;
        // 循环比较所有链表节点的val，直至所有链表节点为null
        while (true) {
            // 当前ListNode都为null，则跳出主循环
            boolean alive = false;
            for (ListNode list : lists) {
                if (list != null) {
                    alive = true;
                    break;
                }
            }
            if (!alive) {
                break;
            }

            // minimum value
            int min = Integer.MAX_VALUE;
            // location of minimum value ListNode
            int loc = 0;

            // 取最小val，放入sorted
            int i = 0;
            for (; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    loc = i;
                }
            }

            // 构建排序后的链表sorted
            sorted.next = new ListNode(lists[loc].val);
            sorted = sorted.next;

            // 修改节点取next
            lists[loc] = lists[loc].next;
        }

        return head.next;
    }

}
