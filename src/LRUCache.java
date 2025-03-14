import java.util.HashMap;

class LRUCache {
    private static HashMap<Integer, Node> map;
    private static int capacity;
    private static List list;

    public LRUCache(int capacity) {
        LRUCache.capacity = capacity;
        list = new List();
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            list.removeNode(node);
            list.addLast(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            list.removeNode(node);
            node.val = value;
            list.addLast(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            list.addLast(node);
            if (list.size > capacity) {
                map.remove(list.head.key);
                list.removeFirst();
            }
        }
    }

    private static class Node {
        int key;
        int val;
        Node next;
        Node prev;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            prev = null;
            next = null;
        }
    }

    private static class List {
        Node head;
        Node tail;
        int size = 0;

        List() {
            head = tail = null;
        }

        void removeFirst() {
            if (size == 1) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            size--;
        }

        private void removeLast() {
            if (size == 1) {
                head = tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
            size--;
        }

        void addLast(Node node) {
            if (size == 0) {
                head = tail = node;
                size++;
            } else {
                addNext(tail, node);
                tail = node;
            }
        }

        void addNext(Node cur, Node next) {
            next.next = cur.next;
            next.prev = cur;
            cur.next = next;
            if (next.next != null) {
                next.next.prev = next;
            }
            size++;
        }

        private void removeNext(Node cur) {
            cur.next = cur.next.next;
            if (cur.next != null) {
                cur.next.prev = cur;
            }
            size--;
        }

        void removeNode(Node node) {
            if (node == head) {
                removeFirst();
            } else if (node == tail) {
                removeLast();
            } else {
                removeNext(node.prev);
            }
        }
    }
}