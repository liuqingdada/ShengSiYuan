package com.leetcode.redblack

import com.leetcode.binarytree.RBNode
import com.leetcode.binarytree.RBNode.Companion.BLACK
import com.leetcode.binarytree.RBNode.Companion.RED

/**
 * Created by cooper
 * 2021/4/26.
 * Email: 1239604859@qq.com
 *
 * 这里不写具体代码了，总结一下: (假设，添加的节点为node，parent，grand，uncle)
 * 1. 红黑树添加节点一定是在叶子节点上添加，并且node默认是红色
 * 2. 一共有12中添加情况，parent如果是黑色(4种情况)，不用处理。
 * 3. node有uncle节点的情况(4种情况): 只需要染色，node和uncle染成黑色，grand上溢并且染成红色当做是新添加的节点
 * 4. 剩下4中情况需要旋转，逻辑和AVL树一样；另外需要染色：就一个逻辑，旋转完成后，子树的根节染成黑色，叶子染成红色
 */
class RBTree {

    private fun color(node: RBNode, color: Boolean): RBNode {
        node.color = color
        return node
    }

    private fun red(node: RBNode) = color(node, RED)
    private fun black(node: RBNode) = color(node, BLACK)
    private fun colorOf(node: RBNode?) = if (node?.color == RED) RED else BLACK
    private fun isBlack(node: RBNode?) = colorOf(node) == BLACK
    private fun isRed(node: RBNode?) = colorOf(node) == RED
}
