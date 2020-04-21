package com.leetcode.binarytree

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
}