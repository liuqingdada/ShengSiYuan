package com.leetcode.sort

/**
 * Created by liuqing.yang
 * 2021/5/2.
 * Email: 1239604859@qq.com
 */
class SortAlgo {
    /**
     * 选择排序，稳定
     * @param input Array<Int>
     */
    fun selectSort(input: Array<Int>) {
        for (end in input.size - 1 downTo 0) {
            var maxIndex = 0
            for (i in 1..end) {
                if (input[maxIndex] <= input[i]) {
                    maxIndex = i
                }
            }
            val max = input[maxIndex]
            input[maxIndex] = input[end]
            input[end] = max
        }
    }

    /**
     * 堆排序，可以说是选择排序的优化，但是不稳定
     * 使用大顶堆进行升序排序
     * 1. 对 input 进行原地建堆 heapify
     * 2. 第一个元素和最后一个元素互换，input size--
     * 3. 对第一个元素进行下虑
     * 4. 重复 2，3，一直到 size == 1
     * @param input Array<Int>
     */
    fun heapSort(input: Array<Int>) {
        // step 1
        var heapSize = input.size
        for (i in heapSize.shr(1) - 1 downTo 0) {
            siftDown(i, input)
        }

        // step 2, 3, 4
        while (heapSize > 1) {
            // 2
            val max = input[0]
            input[0] = input[heapSize - 1]
            input[heapSize - 1] = max
            // 3
            siftDown(0, input)
            // 4
            heapSize--
        }
    }

    private fun siftDown(index: Int, input: Array<Int>) {
        var curr = input[index]
        var i = index
        while (i < input.size shr 1) {
            var childIndex = i.shl(1) + 1
            var chid = input[childIndex]
            val rightChildIndex = childIndex + 1
            if (rightChildIndex < input.size && input[rightChildIndex] > input[childIndex]) {
                chid = input[rightChildIndex]
                childIndex = rightChildIndex
            }

            if (curr >= chid) break
            curr = chid
            i = childIndex
        }
    }
}