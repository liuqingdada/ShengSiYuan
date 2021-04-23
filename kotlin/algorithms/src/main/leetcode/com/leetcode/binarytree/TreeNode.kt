package com.leetcode.binarytree

/**
 * Created by liuqing.yang
 * 2020/4/19.
 * Email: 1239604859@qq.com
 */

class TreeNode(e: Int) : TN<TreeNode>(e)

class AvlNode(e: Int) : TN<AvlNode>(e) {
    var height = 1

    fun blanceFactor(): Int {
        val leftHeight = left?.height ?: 0
        val rightHeight = right?.height ?: 0
        return leftHeight - rightHeight
    }

    fun updateHeight() {
        val leftHeight = left?.height ?: 0
        val rightHeight = right?.height ?: 0
        height = 1 + maxOf(leftHeight, rightHeight)
    }

    fun tallerChild(): AvlNode {
        val leftHeight = left?.height ?: 0
        val rightHeight = right?.height ?: 0
        return if (leftHeight > rightHeight) {
            left!!
        } else if (rightHeight > leftHeight) {
            right!!
        } else {
            if (isLeftChild()) left!! else right!!
        }
    }
}

open class TN<T : TN<T>>(var element: Int) {
    var parent: T? = null
    var left: T? = null
    var right: T? = null

    fun isLeaf() = left == null && right == null
    fun isLeftChild() = parent != null && this == parent!!.left
    fun isRightChild() = parent != null && this == parent!!.right
}
