package com.leetcode.avl

import com.leetcode.binarytree.AvlNode
import kotlin.math.abs

/**
 * Created by cooper
 * 2021/4/23.
 * Email: 1239604859@qq.com
 */
class AVL {
    fun afterAdd(node: AvlNode) {
        var parent: AvlNode? = node.parent
        while (parent != null) {
            if (isBalanced(parent)) {
                parent.updateHeight()
            } else {
                rebalance(parent)
                break
            }
            //
            parent = parent.parent
        }
    }

    private fun isBalanced(node: AvlNode): Boolean {
        return abs(node.blanceFactor()) <= 1
    }

    private fun rebalance(grand: AvlNode) {
        val parent = grand.tallerChild()
        val node = parent.tallerChild()
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { // LL
                rotateRight(grand)
            } else { // LR
                rotateLeft(parent)
                rotateRight(grand)
            }
        } else {
            if (node.isLeftChild()) { // RL
                rotateRight(parent)
                rotateLeft(grand)
            } else { // RR
                rotateLeft(grand)
            }
        }
    }

    private fun rotateLeft(g: AvlNode) {
        val root = g.parent
        val p = g.right
        val pc = p?.left

        g.right = pc
        p?.left = g
        p?.parent = root
        pc?.parent = g
        g.parent = p
        when {
            g.isLeftChild() -> {
                root?.left = p
            }
            g.isRightChild() -> {
                root?.right = p
            }
            else -> {
                p?.parent = null
            }
        }

        g.updateHeight()
        p?.updateHeight()
    }

    private fun rotateRight(g: AvlNode) {
        val root = g.parent
        val p = g.left
        val pc = p?.right

        g.left = pc
        p?.right = g
        p?.parent = root
        pc?.parent = g
        g.parent = p
        when {
            g.isLeftChild() -> {
                root?.left = g
            }
            g.isRightChild() -> {
                root?.right = g
            }
            else -> {
                p?.parent = null
            }
        }

        g.updateHeight()
        p?.updateHeight()
    }
}
