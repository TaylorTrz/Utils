package sort.algorithms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @details 哈希排序
 * @description 百度面试题：
 * 搜索引擎会通过日志文件把用户每次检索使用的所有检索串都记录下来，每个查询串的长度为1-255字节。
 * 假设目前有一千万个记录（这些查询串的重复度比较高，虽然总数是1千万，但如果除去重复后，不超过3百万个。
 * 一个查询串的重复度越高，说明查询它的用户越多，也就是越热门。），请你统计最热门的10个查询串，要求使用的内存不能超过1G。
 * @see [1] https://www.cnblogs.com/tham/p/6827322.html
 */

public class HashSort {

    public static void main(String[] args) {
        Hash hashA = new Hash(11);
        Hash hashB = new Hash(11);
        Map<Hash, String> map = new HashMap<>();
        map.put(hashA, "11");
        map.put(hashB, "101");
        Iterator<Map.Entry<Hash, String>> entryIterator = map.entrySet().iterator();
        while(entryIterator.hasNext()) {
            Map.Entry<Hash, String> entry = entryIterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }

    /**
     * @detail 通过这个内部静态类，我们可以了解key是如何获取散列值并且判断相等的
     * 如果hash值相等，那么不一定代表key相同
     * 如果hash值不等，那么代表key一定不同，这个理解成人的名字就可以
     * Java8中，HashMap是以数组+链表的形式存储，在一个位置如果hash值相同，那么在这个位置会存在多个key，并且以链表形式存在，参考put/get函数的next方法
     */
    static class Hash {
        public int hashKey;

        public Hash(int hashKey) {
            this.hashKey = hashKey;
        }

        @Override
        public boolean equals(Object object) {
            if (this.getClass() != object.getClass()) {
                return false;
            }
            Hash h = (Hash)object;
            return (this.hashKey == h.hashKey);
        }

        public int hashCode() {
            return (this.hashKey == 0) ? 0 : (31 * this.hashKey);
        }

    }

}

