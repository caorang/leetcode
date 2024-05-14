package cn.hutool.jackie.algorithm.ds;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation DSU
 *
 * @author rcao1
 */
public class Dsu {

    public Dsu() {

    }

    private Map<Integer, TreeSet<Integer>> mp = new HashMap<>();

    public int getGroup(int number) {
        if (mp.containsKey(number)) {
            TreeSet<Integer> set = mp.get(number);
            return set.first();
        }
        return -1;
    }

    public int addGroup(int number) {
        TreeSet<Integer> set;
        if (mp.containsKey(number)) {
            set = mp.get(number);
        } else {
            set = new TreeSet<>();
            mp.put(number, set);
        }
        set.add(number);
        return set.first();
    }

    public int addTo2FromGroup(int from, int to) {
        int fromGroup = getGroup(from);
        if (fromGroup == -1) {
            fromGroup = addGroup(from);
        }
        int toGroup = getGroup(to);
        if (toGroup == -1) {
            toGroup = addGroup(to);
        }
        if (fromGroup == toGroup) {
            return fromGroup;
        }
        TreeSet<Integer> fromSet = mp.get(fromGroup);
        fromSet.addAll(mp.get(toGroup));
        for (int fromNum : fromSet) {
            mp.put(fromNum, fromSet);
        }
        return fromGroup;
    }

    private void union(int[] ints) {
        for (int i : ints) {
            addGroup(i);
        }
        for (int i = 0; i < ints.length - 1; i++) {
            addTo2FromGroup(ints[i], ints[i + 1]);
        }
    }

    private Set<Set<Integer>> sets() {
        Set<Set<Integer>> res = new HashSet<>();
        for (Map.Entry<Integer, TreeSet<Integer>> entry : mp.entrySet()) {
            Set<Integer> value = entry.getValue();
            if (!res.contains(value)) {
                res.add(value);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Dsu dsu = new Dsu();
        dsu.union(new int[] {5, 6});
        dsu.union(new int[] {12, 20, 23});
        dsu.union(new int[] {6, 8});
        dsu.union(new int[] {17, 18, 14});
        dsu.union(new int[] {1, 2, 4});
        dsu.union(new int[] {14, 12});
        dsu.union(new int[] {7, 8, 9});
        System.out.println(dsu.sets());
    }
}
