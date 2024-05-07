import tester.*;

class TestHoorayList {
    boolean testAddAndGet(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();
        list.add(5);
        list.add(10);
        list.add(15);

        return t.checkExpect(list.get(0), 5)
                && t.checkExpect(list.get(1), 10)
                && t.checkExpect(list.get(2), 15);
    }

    boolean testAddAndGetWithNulls(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();
        list.add(5);
        list.add(null);
        list.add(15);
        list.add(null);
        list.add(20);

        return t.checkExpect(list.get(0), 5)
                && t.checkExpect(list.get(1), null)
                && t.checkExpect(list.get(2), 15)
                && t.checkExpect(list.get(3), null)
                && t.checkExpect(list.get(4), 20);
    }

    boolean testAddWithExpand(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        boolean result = true;
        for (int i = 0; i < 100; i++) {
            result &= t.checkExpect(list.get(i), i);
        }

        return result;
    }

    boolean testAddWithIndexBasic(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(i * 100);
        }

        list.add(0, 42);

        boolean result = true;
        result &= t.checkExpect(list.get(0), 42);
        for (int i = 1; i < 6; i++) {
            result &= t.checkExpect(list.get(i), (i - 1) * 100);
        }
        return result;
    }

    boolean testSize(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();

        for (int i = 0; i < 8; i++) {
            list.add(i, i * 100);
        }

        return t.checkExpect(list.size(), 8);
    }

    boolean testSizeWithExpansion(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        return t.checkExpect(list.size(), 100);
    }

    boolean testRemove(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(i, i * 10);
        }
        list.remove(5);

        return t.checkExpect(list.get(5), 60)
                && t.checkExpect(list.size(), 7)
                && t.checkExpect(list.get(4), 40);
    }

    boolean testSet(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(i, i * 10);
        }

        list.set(3, -300);
        list.set(6, -600);

        return t.checkExpect(list.get(3), -300)
                && t.checkExpect(list.get(6), -600);
    }

    boolean testClear(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(i, i * 10);
        }
        list.clear();

        return t.checkExpect(list.size(), 0);
    }

    boolean testAddException(Tester t) {
        CSE11List<String> list = new HoorayList<>();

        list.add("hi");
        list.add("hi2");
        list.add("hi3");

        return t.checkException(new IndexOutOfBoundsException(4), list, "add", 4, "nope")
                && t.checkException(new IndexOutOfBoundsException(-1), list, "add", -1, "nope");
    }

    boolean testSetException(Tester t) {
        CSE11List<String> list = new HoorayList<>();

        list.add("hi");
        list.add("hi2");
        list.add("hi3");

        return t.checkException(new IndexOutOfBoundsException(3), list, "set", 3, "nope")
                && t.checkException(new IndexOutOfBoundsException(-1), list, "set", -1, "nope");
    }

    boolean testIndexOf(Tester t) {
        CSE11List<String> list = new HoorayList<>();

        list.add("hi");
        list.add("hi2");
        list.add("hi3");

        return t.checkExpect(list.indexOf("hi"), 0)
                && t.checkExpect(list.indexOf("hi3"), 2)
                && t.checkExpect(list.indexOf("Jerry"), -1);
    }

    boolean testIsEmpty(Tester t) {
        CSE11List<String> list = new HoorayList<>();
        return t.checkExpect(list.isEmpty(), true);
    }

    boolean testIsEmptyNotEmpty(Tester t) {
        CSE11List<String> list = new HoorayList<>();
        list.add("abc");
        return t.checkExpect(list.isEmpty(), false);
    }

    boolean testContains(Tester t) {
        CSE11List<String> list = new HoorayList<>();

        list.add("hi");
        list.add("hi2");
        list.add("hi3");

        return t.checkExpect(list.contains("hi"), true)
                && t.checkExpect(list.contains("hello"), false);
    }

    boolean testSubList(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        CSE11List<Integer> subList = list.subList(10, 20);

        boolean result = true;
        result &= t.checkExpect(subList.size(), 10);

        for (int i = 0; i < subList.size(); i++) {
            result &= t.checkExpect(subList.get(i), list.get(10 + i));
        }

        return result;
    }

    boolean testSubListEmptySubList(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        CSE11List<Integer> subList = list.subList(4, 4);

        return t.checkExpect(subList.size(), 0);
    }

    boolean testSubListIllegalArguments(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        return t.checkException(
                new IllegalArgumentException(),
                list, "subList", 5, 3);
    }

    boolean testSubListIndexOutOfBounds(Tester t) {
        CSE11List<Integer> list = new HoorayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        return t.checkException(new IndexOutOfBoundsException(-5), list, "subList", -5, 3)
                && t.checkException(new IndexOutOfBoundsException(100), list, "subList", 2, 100);

    }
}
