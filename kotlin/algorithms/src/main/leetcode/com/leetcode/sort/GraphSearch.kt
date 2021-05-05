package com.leetcode.sort

import java.util.*

/**
 * Created by liuqing.yang
 * 2021/5/2.
 * Email: 1239604859@qq.com
 */
class GraphSearch {
    fun bfs(vertex: Vertex) {
        val visitedSet = hashSetOf<Vertex>()
        val queue = LinkedList<Vertex>()
        queue.offer(vertex)
        visitedSet.add(vertex)
        while (queue.isNotEmpty()) {
            val head = queue.poll()
            println(head.value)

            head.outEdge.forEach {
                if (!visitedSet.contains(it.toVertex)) {
                    queue.offer(it.toVertex)
                    visitedSet.add(it.toVertex)
                }
            }
        }
    }

    fun dfs(vertex: Vertex) {
        val visitedSet = hashSetOf<Vertex>()
        val stack = Stack<Vertex>()
        stack.push(vertex)
        visitedSet.add(vertex)
        println(vertex.value)
        while (stack.isNotEmpty()) {
            val top = stack.pop()

            for (it in top.outEdge) {
                if (visitedSet.contains(it.toVertex)) continue

                stack.push(it.fromVertex)
                stack.push(it.toVertex)
                visitedSet.add(it.toVertex)
                println(it.toVertex.value)
                break
            }
        }
    }

    fun shortestValue(vertex: Vertex): Map<Vertex, Int> {
        val result = hashMapOf<Vertex, Int>()
        val paths = hashMapOf<Vertex, Int>()
        vertex.outEdge.forEach {
            paths[it.toVertex] = it.weight
        }
        while (paths.isNotEmpty()) {
            val min = getMinPath(paths)
            result[min.key] = min.value
            paths.remove(min.key)
            for (edge in min.key.outEdge) {
                if (result.containsKey(edge.toVertex) || edge.toVertex == vertex) {
                    continue
                }
                val newWeight = min.value + edge.weight
                val oldWeight = paths[edge.toVertex]
                if (oldWeight == null || newWeight < oldWeight) {
                    paths[edge.toVertex] = newWeight
                }
            }
        }
        return result
    }

    /**
     * 单源最短路径算法
     * 不支持 负权边
     * dijkstra
     */
    fun shortestPath(startVertex: Vertex): Map<Vertex, PathInfo> {
        val result = hashMapOf<Vertex, PathInfo>()
        val paths = hashMapOf<Vertex, PathInfo>()

        val startInfo = PathInfo(0)
        paths[startVertex] = startInfo
        while (paths.isNotEmpty()) {
            val min = getMinPathInfo(paths)
            result[min.key] = min.value
            paths.remove(min.key)
            for (edge in min.key.outEdge) {
                if (result.containsKey(edge.toVertex)) {
                    continue
                }
                relax(min.value, edge, paths)
            }
        }
        result.remove(startVertex)
        return result
    }

    /**
     * Bellman-Ford
     * 也属于单源最短路径算法，支持负权边，还能检测出是否有负权环
     * 原理：对所有的边分别进行 V - 1 次松弛操作(V 是节点数量)，得到所有可能的最短路径
     *
     * @param graph Set<Vertex> 所有顶点信息
     * @return Map<Vertex, PathInfo>
     */
    fun bellmanFord(graph: Set<Vertex>, startVertex: Vertex): Map<Vertex, PathInfo> {
        val edges = hashSetOf<Edge>()
        for (vertex in graph) {
            edges.addAll(vertex.inEdge)
            edges.addAll(vertex.outEdge)
        }
        val result = hashMapOf<Vertex, PathInfo>()
        val startInfo = PathInfo(0)
        result[startVertex] = startInfo

        for (i in 0 until edges.size - 1) { // v - 1
            for (edge in edges) {
                result[edge.fromVertex]?.let {
                    relax(it, edge, result)
                }
            }
        }

        // 如果再进行一次松弛，还能找到更短的路径，证明图中有负权环
        for (edge in edges) {
            result[edge.fromVertex]?.let {
                if (relax(it, edge, result)) {
                    throw IllegalAccessException("Find negative weight ring")
                }
            }
        }

        result.remove(startVertex)
        return result
    }

    /**
     * 多源路径算法，能求出任意两点之间的最短路径，支持负权边
     */
    fun floyd() {
    }

    private fun getMinPath(paths: HashMap<Vertex, Int>): Map.Entry<Vertex, Int> {
        val iterator = paths.entries.iterator()
        var min = iterator.next()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (min.value < entry.value) {
                min = entry
            }
        }
        return min
    }

    private fun getMinPathInfo(paths: HashMap<Vertex, PathInfo>): Map.Entry<Vertex, PathInfo> {
        val iterator = paths.entries.iterator()
        var min = iterator.next()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (min.value.weight < entry.value.weight) {
                min = entry
            }
        }
        return min
    }

    /**
     * @param fromPath PathInfo 当前提起点的路径
     * @param edge Edge 需要松弛的边
     * @param paths HashMap<Vertex, PathInfo> 暂时存放目前最短的点
     */
    private fun relax(
        fromPath: PathInfo,
        edge: Edge,
        paths: HashMap<Vertex, PathInfo>,
    ): Boolean {
        val newWeight = fromPath.weight + edge.weight
        val old = paths[edge.toVertex]
        val oldWeight = old?.weight
        if (oldWeight == null || newWeight < oldWeight) {
            if (old == null) {
                val info = PathInfo(newWeight)
                info.edgeInfos.add(edge)
                paths[edge.toVertex] = info
            } else {
                old.weight = newWeight
                old.edgeInfos.clear()
                old.edgeInfos.addAll(old.edgeInfos)
                old.edgeInfos.add(edge)
            }
            return true
        }
        return false
    }
}

class Vertex(
    var value: Int,
    val inEdge: Set<Edge> = hashSetOf(),
    val outEdge: Set<Edge> = hashSetOf(),
)

class Edge(
    val weight: Int,
    var toVertex: Vertex,
    var fromVertex: Vertex,
)

class PathInfo(
    var weight: Int, // 总权值
    val edgeInfos: LinkedList<Edge> = LinkedList(), // 路径
)