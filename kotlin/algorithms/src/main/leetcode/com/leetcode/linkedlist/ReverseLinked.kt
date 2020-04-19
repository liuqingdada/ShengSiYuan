package com.leetcode.linkedlist

/**
 * Created by liuqing.yang
 * 2020/4/18.
 * Email: 1239604859@qq.com
 *
 * ---------------------------------
 *   1  |  2  |  3  |  4  |  NULL
 * ---------------------------------
 */
// https://leetcode-cn.com/problems/reverse-linked-list/

class ReverseLinked {

    fun reverseList1(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        val newHead = reverseList1(head.next)
        head.next?.next = head
        head.next = null
        return newHead
    }

    fun reverseList2(head: ListNode?): ListNode? {
        var pre: ListNode? = null
        var cur: ListNode? = head

        while (cur != null) {
            val next = cur.next // 下一个 node

            cur.next = pre
            pre = cur
            cur = next
        }

        return pre
    }

    fun printLinked(head: ListNode?) {
        var cur: ListNode? = head
        while (cur != null) {
            println(cur.element)
            cur = cur.next
        }
    }
}

fun main() {
    val simpleLinked = getSimpleLinked()

    val reverseLinked = ReverseLinked()
//    val newNode = reverseLinked.reverseList1(simpleLinked.node1)
    val newNode = reverseLinked.reverseList2(simpleLinked.node1)
    reverseLinked.printLinked(newNode)
}