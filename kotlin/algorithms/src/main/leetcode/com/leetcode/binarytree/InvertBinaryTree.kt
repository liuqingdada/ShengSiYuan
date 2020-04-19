package com.leetcode.binarytree

import java.util.*

/**
 * Created by liuqing.yang
 * 2020/4/19.
 * Email: 1239604859@qq.com
 */
// https://leetcode-cn.com/problems/invert-binary-tree/

class InvertBinaryTree {
    fun invertTree(root: TreeNode): TreeNode {
        val queue = LinkedList<TreeNode>()
        queue.add(root)

        while (queue.isNotEmpty()) {
            val node = queue.pop()
            // ...
            val tmp = node.left
            node.left = node.right
            node.right = tmp

            if (node.left != null) {
                queue.push(node)
            }

            if (node.right != null) {
                queue.push(node)
            }
        }

        return root
    }
}