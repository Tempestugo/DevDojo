package org.example.ZZJCrud.conn.test;

import org.example.ZZJCrud.conn.service.AnimeService;
import org.example.ZZJCrud.conn.service.ProducerService;

import java.util.Scanner;

public class CrudTest01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int op;
        while (true) {
            System.out.println("Type the number of your operation");
            System.out.println("1. Producer");
            System.out.println("2. Anime");
            System.out.println("0. Exit");
            op = Integer.parseInt(scanner.nextLine());
            if (op == 0) break;

            switch (op) {
                case 1 -> {
                    producerMenu(scanner);
                }
                case 2 -> {
                    animeMenu(scanner);
                }
            }
        }
    }

    private static void producerMenu(Scanner scanner) {
        System.out.println("Type the number of your operation");
        System.out.println("1. Search for producer");
        System.out.println("2. Delete producer");
        System.out.println("3. Save producer");
        System.out.println("4. Update producer");
        System.out.println("9. Go back");

        int op = Integer.parseInt(scanner.nextLine());
        if (op == 9) return;
        ProducerService.menu(op);
    }

    private static void animeMenu(Scanner scanner) {
        System.out.println("Type the number of your operation");
        System.out.println("1. Search for anime");
        System.out.println("2. Delete anime");
        System.out.println("3. Save anime");
        System.out.println("4. Update anime");
        System.out.println("9. Go back");

        int op = Integer.parseInt(scanner.nextLine());
        if (op == 9) return;
        AnimeService.menu(op);
    }
}