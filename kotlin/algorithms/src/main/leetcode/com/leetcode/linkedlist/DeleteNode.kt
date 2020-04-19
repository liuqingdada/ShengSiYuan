package com.leetcode.linkedlist

/**
 * Created by liuqing.yang
 * 2020/4/18.
 * Email: 1239604859@qq.com
 *
 * ------------------------------
 *     |     |     |     |  NULL
 * ------------------------------
 */
// https://leetcode-cn.com/problems/delete-node-in-a-linked-list/

class DeleteNode {
    fun deleteNode(node: ListNode) {
        node.next?.let {
            node.element = it.element
            node.next = it.next
        }
        if (node.next == null) { // 末尾节点无法处理
        }
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

    val deleteNode = DeleteNode()
    deleteNode.deleteNode(simpleLinked.node3)

    deleteNode.printLinked(simpleLinked.node1)
}
