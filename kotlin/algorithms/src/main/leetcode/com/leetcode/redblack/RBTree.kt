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
 * 3. node有uncle节点的情况(4种情况): 只需要染色，node和uncle染成黑色，grand上溢并且染成红色当做是新添加的节点(递归)
 * 4. 剩下4中情况需要旋转，逻辑和AVL树一样；另外需要染色：就一个逻辑，旋转完成后，子树的根节染成黑色，叶子染成红色
 *
 * 删除的情况稍微复杂一些，站在4介B树的角度可以简化问题：
 * 1. 删除的元素一定是叶子节点，如果删除的是非叶子，可以转化为删除叶子(通过前驱or后继)
 * 2. 删除叶子节点时，可以考虑和兄弟节点借元素保持平衡，也就是B树中的下溢
 * 3. 删除的叶子节点是红色，不用操作
 * 4. 删除的节点有子节点：
 *    b，删除一个拥有 1 or 2 个字节点的黑色节点，替代的叶子节(前驱or后继)点是红色，替代的叶子节点染成黑色即可
 * 5. 删除的节点没有子节点，可以向兄弟节点借
 *    如果兄弟节点(兄弟节点是黑色, 兄弟节点是红色意味着是uncle，站在B树的角度)有红色子节点，那么有三种情况，
 *    a，兄弟的right是红色；b，兄弟的left是红色；c，兄弟的left、right都是红色
 *    d，删除叶子节点后，做旋转操作；e，旋转之后，中心节点继承parent的颜色
 *    f，旋转之后的左右节点染为黑色
 *    如果兄弟节点没有子节点，那么也是下溢，parent和兄弟节点合并即可；
 *    a，如果parent是红色，兄弟节点染成红色，parent染成黑色
 *    b，如果parent是黑色，把parent当做被删除的节点(递归)
 * 6. 删除叶子节点的兄弟是红色：
 *    a，兄弟染成黑色，parent染成红色，然后旋转 ==> 转换成情况 3、4
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
