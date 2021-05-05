package com.leetcode.linkedlist

/**
 * Created by liuqing.yang
 * 2020/4/18.
 * Email: 1239604859@qq.com
 */
// https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/

class DuplicateLinked {
    fun deleteDuplicates(head: ListNode): ListNode {
        var cur: ListNode? = head

        while (cur != null) {
            if (cur.element == cur.next?.element) {
                cur.next = cur.next?.next
            } else {
                cur = cur.next
            }
        }

        return head
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
    simpleLinked.node2.element = 1
    simpleLinked.node4.element = 3
    simpleLinked.node5.element = 3

    val duplicateLinked = DuplicateLinked()
    val node = duplicateLinked.deleteDuplicates(simpleLinked.node1)
    duplicateLinked.printLinked(node)
}