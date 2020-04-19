package com.leetcode.linkedlist

/**
 * Created by liuqing.yang
 * 2020/4/18.
 * Email: 1239604859@qq.com
 */
// https://leetcode-cn.com/problems/remove-linked-list-elements/

class RemoveNodeValue {

    fun removeElements(head: ListNode, value: Int): ListNode? {
        val temp = ListNode(0)
        temp.next = head

        var pre: ListNode? = temp
        var cur: ListNode? = head

        while (cur != null) {
            if (cur.element == value) {
                pre?.next = cur.next
            } else {
                pre = cur
            }

            cur = cur.next
        }

        return temp.next
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
    simpleLinked.node1.element = 2
    simpleLinked.node5.element = 2

    val removeNodeValue = RemoveNodeValue()
    val newNode = removeNodeValue.removeElements(simpleLinked.node1, 2)
    removeNodeValue.printLinked(newNode)
}