package leetcode.mergeTwoSortedLists;

/**
 * 合并两个有序链表
 * @leetcode
 * @date 20191016
 */

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 一开始就出现空串
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null && l2 != null) {
            return l2;
        }
        if (l1 != null && l2 == null) {
            return l1;
        }

        // 对比两个节点，创建合适的listNode，并将子节点进行递归调用
        if (l1.val > l2.val ) {
            ListNode queue = new ListNode(l2.val);
            if (l2.next == null) {
                queue.next = l1;
            }
            if (l2.next != null) {
                queue.next = mergeTwoLists(l1, l2.next);
            }
            return queue;
        }
        if (l1.val <= l2.val) {
            ListNode queue = new ListNode(l1.val);
            if (l1.next == null) {
                queue.next = l2;
            }
            if (l1.next != null) {
                queue.next = mergeTwoLists(l1.next, l2);
            }
            return queue;
        }

        return null;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
