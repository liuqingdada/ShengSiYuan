package com.leetcode.binarytree

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by liuqing.yang
 * 2020/4/19.
 * Email: 1239604859@qq.com
 */

class CessorNode {
    fun predecessor(node: TreeNode): TreeNode? {
        // step 1
        var cur: TreeNode? = node.left
        if (cur != null) {
            while (cur != null) {
                cur = cur.right
            }
            return cur
        }
        // step 2
        var pre: TreeNode? = node
        while (pre?.parent != null && pre == pre.parent?.left) {
            pre = node.parent
        }
        return pre?.parent
    }

    fun preorder(root: TreeNode) {
        val stack = Stack<TreeNode>()
        stack.push(root)
        while (stack.isNotEmpty()) {
            val curr = stack.pop()
            println(curr)
            curr?.right?.let { stack.push(it) }
            curr?.left?.let { stack.push(it) }
        }
    }

    fun inorder(root: TreeNode) {
        val stack = Stack<TreeNode>()
        var curr: TreeNode? = root
        while (curr != null || stack.isNotEmpty()) {
            curr = if (curr != null) {
                stack.push(curr)
                curr.left
            } else {
                val tmp = stack.pop()
                println(tmp)
                tmp.right
            }
        }
    }

    fun postorder(root: TreeNode) {
        val stack = Stack<TreeNode>()
        val ret = Stack<TreeNode>()
        var curr: TreeNode? = root

        stack.push(curr)
        while (stack.isNotEmpty()) {
            curr = stack.pop()
            ret.push(curr)

            curr?.left?.let { stack.push(it) }
            curr?.right?.let { stack.push(it) }
        }
        while (ret.isNotEmpty()) {
            println(ret.pop())
        }
    }

    fun levelOrder(root: TreeNode) {
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        while (queue.isNotEmpty()) {
            val head = queue.poll()
            head?.left?.let { queue.offer(it) }
            head?.right?.let { queue.offer(it) }
        }
    }
}
