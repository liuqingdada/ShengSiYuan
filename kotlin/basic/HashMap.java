
public class HashMap {
    /**
     * 异或：相同返回 0 ，不同返回 1
     * 这里的目的是为了让 key 的 hash 值的高 16 位也参与运算。也就是让高 16 位和低 16 位都参与运算，降低hash冲突概率。换句话说：
     * HashCode 是 int值，32个 bit，如果直接用原始的 HashCode 计算的话：(n - 1) & hash，正常 HashCode 的 size 不会太大，高 16 位参与不到计算位置的运算里，所以计算hash 的时候进行了高 16 位和低 16 位的异或运算，根本目的是为了散列更均匀。
     * 这里顺便提一个疑问：为什么 (n - 1) & hash，能够获取下标？
     * 因为之前说了，数组的长度一定是 2 的次幂，假设长度是 16 即 10000，也就是说不管怎么说，最高位都是1其余的都是0，然后n-1 就是最高位是 0 其余都是 1即01111，那么这个时候是不是 n-1 的范围就是
     * 0-15，因为数组的下标是从0开始的，所以不管hash是什么值，最后的结果一定是在数组的长度范围之内。
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

     /**
     * Implements Map.put and related methods.
     *
     * @param hash         key 的 hash 值
     * @param key          key 值
     * @param value        value 值
     * @param onlyIfAbsent true：如果某个 key 已经存在那么就不插了；false 存在则替换，没有则新增。这里为 false
     * @param evict        不用管了，我也不认识
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        // tab 表示当前 hash 散列表的引用
        Node<K, V>[] tab;
        // 表示具体的散列表中的元素
        Node<K, V> p;
        // n：表示散列表数组的长度
        // i：表示路由寻址的结果
        int n, i;
        // 将 table 赋值发给 tab ；如果 tab == null，说明 table 还没有被初始化。则此时是需要去创建 table 的
        // 为什么这个时候才去创建散列表？因为可能创建了 HashMap 时候可能并没有存放数据，如果在初始化 HashMap 的时候就创建散列表，势必会造成空间的浪费
        // 这里也就是延迟初始化的逻辑
        if ((tab = table) == null || (n = tab.length) == 0) {
            //resize()下面会单独详细讲解
            n = (tab = resize()).length;
        }
        // 如果 p == null，说明寻址到的桶的位置没有元素。那么就将 key-value 封装到 Node 中，并放到寻址到的下标为 i 的位置
        if ((p = tab[i = (n - 1) & hash]) == null) {
            tab[i] = newNode(hash, key, value, null);
        }
        // 到这里说明 该位置已经有数据了，且此时可能是链表结构，也可能是树结构
        else {
            // e 表示找到了一个与当前要插入的key value 一致的元素
            Node<K, V> e;
            // 临时的 key
            K k;
            // p 的值就是上一步 if 中的结果即：此时的 (p = tab[i = (n - 1) & hash]) 不等于 null
            // p 是原来的已经在 i 位置的元素，且新插入的 key 是等于 p中的key
            //说明找到了和当前需要插入的元素相同的元素（其实就是需要替换而已）
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                //将 p 的值赋值给 e
                e = p;
                //说明已经树化，红黑树会有单独的文章介绍，本文不再赘述，不然文章要非常非常的长
            else if (p instanceof TreeNode) {
                e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
            } else {
                //到这里说明不是树结构，也不相等，那说明不是同一个元素，那就是链表了
                for (int binCount = 0; ; ++binCount) {
                    //如果 p.next == null 说明 p 是最后一个元素，说明，该元素在链表中也没有重复的，那么就需要添加到链表的尾部
                    if ((e = p.next) == null) {
                        //直接将 key-value 封装到 Node 中并且添加到 p的后面
                        p.next = newNode(hash, key, value, null);
                        // 当元素已经是 7了，再来一个就是 8 个了，那么就需要进行树化
                        if (binCount >= TREEIFY_THRESHOLD - 1) {
                            treeifyBin(tab, hash);
                        }
                        break;
                    }
                    //在链表中找到了某个和当前元素一样的元素，即需要做替换操作了。
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        break;
                    }
                    //将e(即p.next)赋值为e，这就是为了继续遍历链表的下一个元素（没啥好说的）下面有张图帮助大家理解。
                    p = e;
                }
            }
            //如果条件成立，说明找到了需要替换的数据，
            if (e != null) {
                //这里不就是使用新的值赋值为旧的值嘛
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null) {
                    e.value = value;
                }
                //这个方法没用，里面啥也没有
                afterNodeAccess(e);
                //HashMap put 方法的返回值是原来位置的元素值
                return oldValue;
            }
        }
        // 上面说过，对于散列表的 结构修改次数，那么就修改 modCount 的次数
        ++modCount;
        //size 即散列表中的元素的个数，添加后需要自增，如果自增后的值大于扩容的阈值，那么就触发扩容操作
        if (++size > threshold) {
            resize();
        }
        //啥也没干
        afterNodeInsertion(evict);
        //原来位置没有值，那么就返回 null 呗
        return null;
    }


    final Node<K, V>[] resize() {
        // oldTab 表示引用扩容前的 散列表
        Node<K, V>[] oldTab = table;
        // oldCap 扩容前的 table 数组的长度，后面就是一个简单的三目运算符：oldTab 为 null，长度则为 0 ，否则就取 table 实际的长度
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        //表示扩容之前的扩容阈值，也即触发本次 扩容的阈值
        int oldThr = threshold;
        // newCap：扩容之后的 table 的数组的长度
        // newThr：扩容之后下次触发扩容的阈值
        int newCap, newThr = 0;
        //条件成立：说明散列表已经初始化过了，就是一次正常的容量不够了的扩容（因为在 table 没有初始化也会进行 resize 的）
        if (oldCap > 0) {
            //基本的容量大小判断，基本是不可能达到这个数值的，但是为了保持程序的健壮性，还是需要做该检查的。
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                //直接返回原来的容量，已经已经达到最大值，无法再继续扩容了。
                return oldTab;
            }
            //走到这里，首先将 newCap 扩大为原来的 2 倍，且需要判断是否超过了最大值
            //并且要保证扩容之后的容量是大于扩容之前的阈值（16） oldCap >= DEFAULT_INITIAL_CAPACITY 这个条件会不成立吗？假设你创建HashMap 的时候传的初始容量为3 那么就不走这部进行扩容了
            //两个条件都满足以后，那么就将扩容的阈值翻倍
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)
                //将原来的扩容阈值扩大一倍后赋值给新的扩容阈值
                newThr = oldThr << 1;
        }
        // 到这一步说明 oldCap == 0，说明此时散列表中没有任何的元素。但是为什么扩容阈值会可能有大于 0 的情况。
        //需要回头看下构造方法，除了无参构造，别的方法里面最终执行 tableSizeFor()方法。这就导致了 threshold 可能是 > 0 的
        else if (oldThr > 0) {
            newCap = oldThr;
        } else {
            // 到这一步说明 oldTab = 0,oldThr = 0；此时直接非 容量赋值初始值
            newCap = DEFAULT_INITIAL_CAPACITY;
            //通过 容量 * 负载因子 得到 扩容阈值
            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        //这个是什么情况？ 第一种是上面的  else if (oldThr > 0) {  newCap = oldThr;  }的情况下，还有一种是上面的第一个 if 中的else if 条件没有满足。这个时候 newThr == 0 是成立的
        if (newThr == 0) {
            // 这里面就是在计算新的扩容阈值。
            float ft = (float) newCap * loadFactor;
            //这里真没什么好说的，就是简单的三目运算
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ? (int) ft : Integer.MAX_VALUE);
        }
        //将新的扩容阈值赋值给 threshold
        threshold = newThr;
        @SuppressWarnings({"rawtypes", "unchecked"})
        //创建一个容量更大的数组
        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
        //将新数组赋值给 table
        table = newTab;
        //条件成立，说明 原来的散列表中有元素呗
        if (oldTab != null) {
            //扩容没有捷径，就是每个桶位置去处理
            for (int j = 0; j < oldCap; ++j) {
                //e：表示当前 node 节点
                Node<K, V> e;
                //将 j位置的元素赋值给 e，且如果 j 位置元素不为null。否则继续下一轮循环
                if ((e = oldTab[j]) != null) {
                    //将 j 位置置为 null，方便 GC
                    oldTab[j] = null;
                    //如果 e.next 为空，说明该位置没有发生过 hash 碰撞。
                    if (e.next == null) {
                        //计算新的桶的小标，并将e设置进去
                        newTab[e.hash & (newCap - 1)] = e;
                    } else if (e instanceof TreeNode) {
                        //判断是否已经树化，本文不讨论，过~
                        ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
                    } else {
                      //★★★★★最重要的的地方★★★★★ 处理链表 再拿出来单独介绍
                        ......
                    }
                }
            }
        }
        return newTab;
    }

    final Node<K, V>[] resize() {
        //★★★★★最重要的的地方★★★★★ 处理链表
        // 低位链表：存放扩容之后的数组的下标位置，与当前数组的下标位置是一致的（下面会结合图来解释）
        Node<K, V> loHead = null, loTail = null;
        // 高位链表：存放扩容之后的数组的下标位置，当前数组的下标位置 + 扩容之前的数组的长度（下面会结合图来解释）
        //卡不明白不要急，下面会针对这里详细讲解
        Node<K, V> hiHead = null, hiTail = null;
        //下一个节点
        Node<K, V> next;
        do {
            //开始遍历元素
            next = e.next;
            //oldCap 一定是1000...这样形式的（2的次幂，最高位一定是 1 ）
            //e.hash有两种情况，低位不用管，怎么都是0，高位可能是1也可能是0，如果是1那么结果就是1，那该条件就不成立了，如果是0那么结果必然是0
            if ((e.hash & oldCap) == 0) {
                //如果改位置为空，直接将e放进去
                if (loTail == null) {
                    loHead = e;
                } else {
                    //否则就添加到链表的后面
                    loTail.next = e;
                }
                loTail = e;
            } else {
                //到这一步说明高位1为1，添加也是如果原来位置没有元素那么就直接添加，
                if (hiTail == null) {
                    hiHead = e;
                } else {
                    //原来位置有值就将新元素添加到链表的尾部
                    hiTail.next = e;
                }
                hiTail = e;
            }
        } while ((e = next) != null);
        //下面的两个if不明白的请看下图中的注释
        //低位链表有数据
        if (loTail != null) {
            //将原来的低位链表的next置空，方便GC，
            loTail.next = null;
            //将低位链表直接添加到新的散列表的和原来的一样的下标位置
            newTab[j] = loHead;
        }
        //高位链表有数据
        if (hiTail != null) {
            //将原来的高位链表的next置空，方便GC
            hiTail.next = null;
            //将高位链表的放在 新的散列表的 老表的长度+老表的位置 的下标位置
            newTab[j + oldCap] = hiHead;
        }

        //可能很多朋友还是不明白，这里我再拿出来继续讲解下，假设原来散列表的长度是16，length - 1转成二进制是 0 1111，
        //现在假设有一个 A 和 B 两个 Node 的 key 的 hash 值分别为：0 1111,1 1111 A 和 0 1110 取余 结果是：0 1111 & 0 1111 = 0 1111，下标是15，
        //同样 B 1 1111 & 0 1111 = 0 1111，这个时候是在原来的桶中的，现在散列表扩容后长度变成了 32 ，32 - 1 = 31 = 1 1111，
        //此时再来计算 A 和 B 的在新的散列表中的位置，A ：0 1111 & 1 1111 = 0 1111 = 15，也就是说 A 在迁移到新的桶中的下标位置还是 15 ，
        //再来看下 B ：1 1111 & 1 1111 = 1 1111 = 31，即 B 在新的散列表中的位置为 原来的散列表的长度（16）+ 原来的下标的位置（15） = 新的下标的位置（31），这就是迁移后元素存放的特点。
    }

    final Node<K, V> getNode(int hash, Object key) {
        //当前HashMap的散列表的引用
        Node<K, V>[] tab;
        //first：桶头元素
        //e：用于存放临时元素
        Node<K, V> first, e;
        //n：table 数组的长度
        int n;
        //元素中的 k
        K k;
        // 将 table 赋值为 tab，不等于null 说明有数据，(n = tab.length) > 0 同理说明 table 中有数据
        //同时将 改位置的元素 赋值为 first
        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n - 1) & hash]) != null) {
            //定位到了桶的到的位置的元素就是想要获取的 key 对应的，直接返回该元素
            if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k)))) {
                return first;
            }
            //到这一步说明定位到的元素不是想要的，且改位置不仅仅有一个元素，需要判断是链表还是树
            if ((e = first.next) != null) {
                //是否已经树化，本文不考虑
                if (first instanceof TreeNode) {
                    return ((TreeNode<K, V>) first).getTreeNode(hash, key);
                }
                //处理链表的情况
                do {
                    //如果遍历到了就直接返回该元素
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                        return e;
                    }
                } while ((e = e.next) != null);
            }
        }
        //遍历不到返回null
        return null;
    }

     /**
     * Implements Map.remove and related methods.
     *
     * @param hash       hash 值
     * @param key        key 值
     * @param value      value 值
     * @param matchValue 是否需要值匹配 false 表示不需要
     * @param movable    不用管
     * @return the node, or null if none
     */
    final Node<K, V> removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable) {
        //当前HashMap 中的散列表的引用
        Node<K, V>[] tab;
        //p：表示当前的Node元素
        Node<K, V> p;
        // n：table 的长度
        // index：桶的下标位置
        int n, index;
        //(tab = table) != null && (n = tab.length) > 0 条件成立，说明table不为空（table 为空就没必要执行了）
        // p = tab[index = (n - 1) & hash]) != null 将定位到的捅位的元素赋值给 p ，并判断定位到的元素不为空
        if ((tab = table) != null && (n = tab.length) > 0 && (p = tab[index = (n - 1) & hash]) != null) {
            //进到 if 里面来了，说明已经定位到元素了
            //node：保存查找到的结果
            //e：表示当前元素的下一个元素
            Node<K, V> node = null, e;
            K k;
            V v;
            // 该条件如果成立，说明当前的元素就是要找的结果（这是最简单的情况，这个是很好理解的）
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k)))) {
                node = p;
            }
            //到这一步，如果 (e = p.next) != null 说明该捅位找到的元素可能是链表或者是树，需要继续判断
            else if ((e = p.next) != null) {
                //树，不考虑
                if (p instanceof TreeNode) {
                    node = ((TreeNode<K, V>) p).getTreeNode(hash, key);
                }
                //处理链表的情况
                else {
                    do {
                        //如果条件成立，说明已经匹配到了元素，直接将查找到的元素赋值给 node，并跳出循环（总体还是很好理解的）
                        if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                        //将正在遍历的当前的临时元素 e 赋值给 p
                        p = e;
                    } while ((e = e.next) != null);
                }
            }
            // node != null 说明匹配到了元素
            //matchValue为false ，所以!matchValue  = true，后面的条件直接不用看了
            if (node != null && (!matchValue || (v = node.value) == value || (value != null && value.equals(v)))) {
                //树，不考虑
                if (node instanceof TreeNode) {
                    ((TreeNode<K, V>) node).removeTreeNode(this, tab, movable);
                }
                // 这种情况是上面的最简单的情况
                else if (node == p) {
                    //直接将当前节点的下一个节点放在当前的桶位置（注意不是下一个桶位置，是该桶位置的下一个节点）
                    tab[index] = node.next;
                } else {
                    //说明定位到的元素不是该桶位置的头元素了，那直接进行一个简单的链表的操作即可
                    p.next = node.next;
                }
                //移除和添加都属于结构的修改，需要同步自增 modCount 的值
                ++modCount;
                //table 中的元素个数减 1
                --size;
                //啥也没做，不用管
                afterNodeRemoval(node);
                //返回被移除的节点元素
                return node;
            }
        }
        //没有匹配到返回null 即可
        return null;
    }
}
