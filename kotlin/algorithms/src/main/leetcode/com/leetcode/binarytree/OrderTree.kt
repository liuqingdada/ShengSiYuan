package com.leetcode.binarytree

import java.util.*

/**
 * Created by liuqing.yang
 * 2021/5/1.
 * Email: 1239604859@qq.com
 */
class OrderTree {

    fun preorder(node: TreeNode) {
        val stack = Stack<TreeNode>()
        var curr: TreeNode? = node
        while (true) {
            curr = when {
                curr != null -> {
                    println(curr.element)
                    curr.right?.let { stack.push(it) }
                    curr.left
                }
                stack.isEmpty() -> return
                else -> stack.pop()
            }
        }
    }

    fun inorder(node: TreeNode) {
        val stack = Stack<TreeNode>()
        var curr: TreeNode? = node
        while (true) {
            curr = when {
                curr != null -> {
                    stack.push(curr)
                    curr.left
                }
                stack.isEmpty() -> return
                else -> {
                    curr = stack.pop()
                    println(curr?.element)
                    curr?.right
                }
            }
        }
    }

    fun postorder(node: TreeNode) {
        val stack = Stack<TreeNode>()
        var preNode: TreeNode? = null
        stack.push(node)
        while (stack.isNotEmpty()) {
            val top = stack.peek()
            if (top.isLeaf() || preNode?.parent == top) {
                preNode = stack.pop()
                println(preNode)
            } else {
                top?.right.let { stack.push(it) }
                top?.left.let { stack.push(it) }
            }
        }
    }
}