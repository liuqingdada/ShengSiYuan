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

    fun preForEach(node: TreeNode): List<Int> {
        val ret = ArrayList<Int>()
        val stack = Stack<TreeNode>()

        var curr: TreeNode? = node
        while (curr != null || stack.isNotEmpty()) {
            while (curr != null) {
                ret.add(curr.element)

                stack.push(curr)
                curr = curr.left
            }

            if (stack.isNotEmpty()) {
                val pop = stack.pop()
                curr = pop?.right
            }
        }

        return ret
    }

    fun midForEach(node: TreeNode): List<Int> {
        val ret = ArrayList<Int>()
        val stack = Stack<TreeNode>()

        var curr: TreeNode? = node
        while (curr != null || stack.isNotEmpty()) {
            while (curr != null) {
                stack.push(curr)
                curr = curr.left
            }
            if (stack.isNotEmpty()) {
                val pop = stack.pop()
                ret.add(pop.element)
                curr = pop?.right
            }
        }
        return ret
    }
}