package com.example.education.tasks;

/*Практическое задание - Stream API - генерация чисел
        Предположим, у нас есть список заказов, и каждый заказ представляет собой продукт и его стоимость.
        Задача состоит в использовании Stream API и коллекторов для решения следующих задач:

        Создайте список заказов с разными продуктами и их стоимостями.
        Группируйте заказы по продуктам.
        Для каждого продукта найдите общую стоимость всех заказов.
        Отсортируйте продукты по убыванию общей стоимости.
        Выберите три самых дорогих продукта.
        Выведите результат: список трех самых дорогих продуктов и их общая стоимость.*/

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaStreamApi {
    static class Order {
        private String product;
        private double cost;

        public Order(String product, double cost) {
            this.product = product;
            this.cost = cost;
        }

        public String getProduct() {
            return product;
        }

        public double getCost() {
            return cost;
        }
    }

    public static class StreamCollectorsExample {
        public static void main(String[] args) {
            List<Order> orders = List.of(
                    new Order("Laptop", 1200.0),
                    new Order("Smartphone", 800.0),
                    new Order("Laptop", 1500.0),
                    new Order("Tablet", 500.0),
                    new Order("Smartphone", 900.0)
            );

            Map<String, List<Order>> groupOrdersByProduct = orders.stream().collect(Collectors.groupingBy(Order::getProduct));

            Map<String, Double> groupOrdersByCost = orders.stream().collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)));

            List<Map.Entry<String, Double>> sortedByCost = groupOrdersByCost.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).toList();

            List<Order> threeMostExpensiveProduct = orders.stream().sorted(Comparator.comparingDouble(Order::getCost).reversed()).limit(3).toList();

            double sum = threeMostExpensiveProduct.stream().mapToDouble(Order::getCost).sum();

            System.out.println("Три самых дорогих продукта: ");
            threeMostExpensiveProduct.forEach(order -> System.out.println("Продукт: " + order.getProduct() + ", Стоимость: " + order.getCost()));
            System.out.println("Общая стоимость: " + sum);
        }
    }
}