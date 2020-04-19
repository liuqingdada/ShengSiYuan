package com.leetcode.linkedlist

/**
 * Created by liuqing.yang
 * 2020/4/18.
 * Email: 1239604859@qq.com
 */
// https://leetcode-cn.com/problems/linked-list-cycle/

class LinckedCycle {
    fun hasCycle(head: ListNode?): Boolean {
        if (head?.next == null) return false

        var slow = head
        var fast = head.next
        while (fast != null) {
            slow = slow?.next
            fast = fast.next?.next

            if (slow == fast) return true
        }
        return false
    }

    fun printLinked(head: ListNode?) {
        var cur: ListNode? = head
        while (cur != null) {
            println(cur.element)
            cur = cur.next
        }
    }
}