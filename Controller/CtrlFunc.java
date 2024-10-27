package Controller;

import AnimalNursery.Animals;
import AnimalNursery.PetsCommands;
import MySQL.MySQL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static AnimalNursery.Animals.animalsList;
import static java.lang.Integer.parseInt;

public class CtrlFunc {

    public static void showPets() {
        System.out.println("Список питомцев: ");
        animalsList.forEach(System.out::println);
    }


    public static void showCommands() {
        System.out.println("Доступные команды: ");
        int i = 1;
        for (PetsCommands e : PetsCommands.values()) {
            System.out.printf("%d %s%n", i, e.toString());
            i++;
        }
    }

    public static ArrayList<Animals> findAnimal() {
        Scanner scanner = new Scanner(System.in);
        int userNum;
        ArrayList<Animals> res = new ArrayList<>();
        System.out.println("Для того чтобы найти питомца выберите критерий поиска:\n" +
                "1 - Поиск по ID\n" +
                "2 - Поиск по имени\n" +
                "0 - Для возврата в предыдущее меню");
        System.out.println(">>> ");
        userNum = scanner.nextInt();
        if (userNum == 1) {
            int idAnimal;
            System.out.println("Введите ID питомца\n" +
                    ">>> ");
            idAnimal = scanner.nextInt();
            for (Animals an : animalsList) {
                if (an.getId() == idAnimal) {
                    res.add(an);
                }
            }
        } else if (userNum == 2) {
            String nameAnimal;
            System.out.println("Введите имя питомца\n" +
                    ">>> ");
            nameAnimal = scanner.next();
            for (Animals an : animalsList) {
                if (an.getName().contains(nameAnimal)) {
                    res.add(an);
                }
            }
        } else {
            return res;
        }
        return res;
    }

    public static Integer valueOf(String s) throws NumberFormatException {
        return parseInt(s);
    }

    private static void introduction() {
        System.out.println("Добро пожаловать в приют животных");
    }

    private static int selectOperation() {
        int res;
        System.out.println("в программе реализован следующий функционал:");
        System.out.println("1. Вывести список всех животных\n" +
                "2. Добавить новое животное \n" +
                "3. Внести изменения в основную информацию о животном \n" +
                "4. Работа с обучением питомца");
        System.out.println("Введите номер нужной операции или 0 для отмены");
        System.out.println(">>>");
        Scanner scanner = new Scanner(System.in);
        res = scanner.nextInt();
        return res;
    }

    public static void Control() {
        introduction();
        int selector = 1;
        while (selector != 0) {
            selector = selectOperation();
            switch (selector) {
                case 1:
                    MySQL.getAnimalsInfo();
                    pressAnyKey();
                    break;
                case 2:
                    MySQL.addAnimalToDB(Animals.setAnimalData(Animals.createAnimal()));
                    System.out.println("Питомец добавлен");
                    break;
                case 3:
                    MySQL.changePet();
                    System.out.println("Информация о питомце обновлена");
                    break;
                case 4:
                    MySQL.showAnimalCom();
                    System.out.println("Данные обновлены");
                    break;
                default:
                    System.out.println("____");
            }

        }
        System.out.println("Работа программы завершена!");
    }

    private static void pressAnyKey() {
        String pressAnyKey;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Для продолжения нажмите любую клавишу \n" +
                    ">>> ");
            pressAnyKey = scanner.next();
        } while (pressAnyKey == null);
    }

    public static String stadyPetToSQL() {
        String res;
        List<String> commands = new ArrayList<>();
        int numberOfCommand;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Доступные команды: ");
        CtrlFunc.showCommands();
        System.out.println("Введите команды, которым хотите обучить \n" +
                "для выхода/отмены нажмите 0\n" +
                ">>> ");
        numberOfCommand = scanner.nextInt();
        while (numberOfCommand != 0) {
            if (numberOfCommand > 8 || numberOfCommand < 1) {
                System.out.println("Введите номер команды от 1 до 8");
                numberOfCommand = scanner.nextInt();
            } else {
                res = PetsCommands.Взять.getcommand(numberOfCommand);
                commands.add(res);
                numberOfCommand = scanner.nextInt();
            }
        }
        return commands.toString();
    }
}
