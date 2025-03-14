public class ReorderList {
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

        ListNode reverse(ListNode node) {
            if (node == null) {
                return null;
            }

            ListNode cur = node;
            ListNode prev = null;
            ListNode next;

            while (cur != null) {
                next = cur.next;
                cur.next = prev;

                prev = cur;
                cur = next;
            }

            return prev;
        }

        void reversingSolution(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode rev = reverse(slow.next);
            slow.next = null;

            ListNode cur = head;
            while (rev != null) {
                ListNode left = cur.next;
                ListNode right = rev.next;
                cur.next = rev;
                rev.next = left;
                cur = left;
                rev = right;
            }
        }

        ListNode current;

        void reorder(ListNode node) {
            if (node == null) {
                return;
            }

            reorder(node.next);

            ListNode temp = current.next;

            if (temp == null) {
                return;
            }

            if (current == temp.next) {
                current.next = null;
                return;
            }

            current.next = node;
            node.next = temp;
            current = temp;
        }

        public void reorderList(ListNode head) {
            current = head;
            reorder(head);
        }
    }
}
