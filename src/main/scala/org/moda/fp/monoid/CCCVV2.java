package org.moda.fp.monoid;


import java.util.*;

public class CCCVV2 {

    static class User {
        public int userId;
        public String name;

        public User(int userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userId=" + userId +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class Order {
        public int userId;
        public String orderId;

        public Order(int userId, String orderId) {
            this.userId = userId;
            this.orderId = orderId;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "userId=" + userId +
                    ", orderId='" + orderId + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
//        List<User> us = new ArrayList<User>();
//        us.add(new User(1, "1"));
//        us.add(new User(2, "2"));
//        us.add(new User(3, "2"));
//
//        List<Order> os = new ArrayList<Order>();
//        os.add(new Order(1, "55"));
//        os.add(new Order(2, "66"));
//        os.add(new Order(3, "77"));
//        os.add(new Order(3, "777"));
//
//        // List<User> collect = us.stream().filter(u -> u.name.equals("2") && u.id == 3).collect(Collectors.toList());
//        Object collect = us.stream().filter(u -> u.name.equals("2") && u.userId == 3)
//                               .map(u1 -> os.stream().filter(o -> o.userId == u1.userId).collect(Collectors.toList()))
//                               .flatMap(List::stream)
//                               .collect(Collectors.toList());
//        System.out.println(collect);
//
//        ccc(new String[]{"1", "2"});
//        ddd("1", "2", "3");
        ccc(new String[]{"1", "2"});
    }

    public static void ccc(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Integer i: list) {
            if (i % 2 != 0) {
                continue;
            }
            int v = 0;
            if (map.containsKey(i)) {
                v = map.get(i) + 1;
            } else {
                v = 1;
            }
            map.put(i,  v);
        }

        System.out.println(map);
    }

    public static void ddd(String... args) {
        for (int i = 0; i < args.length; i++) {

        }
    }
}
