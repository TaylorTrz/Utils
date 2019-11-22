package leetcode.addTwoNumbers;

/**
 * @description 两数相加，数表示为非空链表，头结点从个位开始逆序排列
 * @date 20191017
 * @details 链表操作，递归法解答，两个节点其中有一个节点为null，但是后一位进位后仍然大于10，导致需要进位
 */

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int val1, val2;
        ListNode l1Null = l1;
        ListNode l2Null = l2;
        l1 = l1 == null ? new ListNode(0) : l1;
        l2 = l2 == null ? new ListNode(0) : l2;
        ListNode listNode = new ListNode(l1.val + l2.val);

        // 代码优化调整.............
        //
//        int keyValue = l1.val + l2.val;

        if (l1.val + l2.val < 10) {
            // 判断最高位并返回Null
            if (l1.val + l2.val == 0 && l1.next == null && l2.next == null && l1Null == null && l2Null == null) {
                return null;
            }
            listNode.next = addTwoNumbers(l1.next, l2.next);
            return listNode;
        } else {
            listNode.val = listNode.val - 10;
            // 判断是否进位
            if (l1.next != null) {
                l1.next.val++;
            } else if (l2.next != null) {
                l2.next.val++;
            } else {
                l1.next = new ListNode(1);
            }
            listNode.next = addTwoNumbers(l1.next, l2.next);
            return listNode;
        }
    }


    /**
     * @description 官方解答:迭代法
     * @details 思路基本相同，但是有dummyNode来简化空节点的选择表示
     */

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            // 节点null值优化
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            // 获取并设置进位和当前位
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            // 迭代下一节点
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        // 如果仍需进位，则创建新节点
        if (carry == 1) {
            curr.next = new ListNode(carry);
        }
        // dummyHead节点代表头结点的前一节点
        return dummyHead.next;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

}
