package com.leetcode.linkedlist

class ListNode(var element: Int) {
    var next: ListNode? = null
}

data class SimpleLinked(
    val node1: ListNode,
    val node2: ListNode,
    val node3: ListNode,
    val node4: ListNode,
    val node5: ListNode
)

fun getSimpleLinked(): SimpleLinked {
    val node1 = ListNode(1)
    val node2 = ListNode(2)
    val node3 = ListNode(3)
    val node4 = ListNode(4)
    val node5 = ListNode(5)

    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5

    return SimpleLinked(node1, node2, node3, node4, node5)
}