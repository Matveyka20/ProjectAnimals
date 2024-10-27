package MySQL;

import AnimalNursery.Animals;
import Controller.CtrlFunc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySQL {
    static String url = "jdbc:mysql://localhost:3306/human_friends";
    static String user = "root";
    static String password = "qqasew";

    private static String makeBirthday(int[] arr) {
        String res;
        res = "'" + arr[2] + "-" + arr[1] + "-" + arr[0] + "'";
        return res;
    }

    public static void getAnimalsInfo() {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM animals");
            //System.out.println("id      name_animal     type_animal");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "         " + rs.getString("name_animal") + "           " + rs.getString("type_animal"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getAnimalInfoID(int idPet) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM animals WHERE id = " + idPet);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "         " + rs.getString("name_animal") + "           " + rs.getString("type_animal"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int getNextID() {
        int lastID = 0;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM animals");
            //System.out.println("id      name_animal     type_animal");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "         " + rs.getString("name_animal") + "           " + rs.getString("type_animal"));
                lastID = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lastID + 1;
    }

    public static void changePet() {
        Scanner scanner = new Scanner(System.in);
        int userSelect;
        int chosePet;
        System.out.println("Введите ID питомца по которому необходимо изменить информацию:\n" +
                ">>> ");
        chosePet = CtrlFunc.valueOf(scanner.next());
        System.out.println("Какую информацию о питомце вы хотите изменить?\n" +
                "Введите нужное число:\n" +
                "1. - Имя питомца\n" +
                "2. - Дата рождения\n" +
                "0. - Для отмены");
        userSelect = CtrlFunc.valueOf(scanner.next());
        switch (userSelect) {
            case 1:
                System.out.println("Введите новое имя питомца: \n" +
                        ">>> ");
                String newPetName = scanner.next();
                if (newPetName.isEmpty()) System.out.println("Вы ничего не ввели");
                else {
                    String insertName = "UPDATE animals\n" +
                            "SET animal_name " + newPetName + " WHERE id = " + chosePet;
                    useSQL(insertName);
                }
                break;
            case 2:
                int[] newBirthday = new int[3];
                String newBirthdayStr;
                System.out.println("Введите дату рождения питомца в формате DD-MM-YYYY");
                System.out.println("Введите Day");
                System.out.println(">>> ");
                newBirthday[0] = scanner.nextInt();
                System.out.println("Введите MM");
                System.out.println(">>> ");
                newBirthday[1] = scanner.nextInt();
                System.out.println("Введите YYYY");
                System.out.println(">>> ");
                newBirthday[2] = scanner.nextInt();
                newBirthdayStr = makeBirthday(newBirthday);
                String insertBirthday;
                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT type_animal FROM animals WHERE id = " + chosePet);
                    while (rs.next()) {
                        String typeAnimal = rs.getString("type_animal");
                        switch (typeAnimal) {
                            case "cat":
                                insertBirthday = "UPDATE cats\n" +
                                        "SET birthday " + newBirthdayStr + " WHERE id_cat = " + chosePet;
                                useSQL(insertBirthday);
                                break;
                            case "dog":
                                insertBirthday = "UPDATE dogs\n" +
                                        "SET birthday " + newBirthdayStr + " WHERE id_dog = " + chosePet;
                                useSQL(insertBirthday);
                                break;
                            case "hamster":
                                insertBirthday = "UPDATE hamsters\n" +
                                        "SET birthday " + newBirthdayStr + " WHERE id_ham = " + chosePet;
                                useSQL(insertBirthday);
                                break;
                            case "hourse":
                                insertBirthday = "UPDATE hourses\n" +
                                        "SET birthday " + newBirthdayStr + " WHERE id_hourse = " + chosePet;
                                useSQL(insertBirthday);
                                break;
                            case "donkey":
                                insertBirthday = "UPDATE donkeys\n" +
                                        "SET birthday " + newBirthdayStr + " WHERE id_don = " + chosePet;
                                useSQL(insertBirthday);
                                break;
                            case "camel":
                                insertBirthday = "UPDATE camels\n" +
                                        "SET birthday " + newBirthdayStr + " WHERE id_camel = " + chosePet;
                                useSQL(insertBirthday);
                                break;

                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Работа блока завершена");

        }
    }

    public static void showAnimalCom() {
        Scanner scanner = new Scanner(System.in);
        int chosePet, userChose;
        String dbSelector;
        System.out.println("Введите ID питомца, по которому необходимо вывести список команд:\n" +
                ">>> ");
        chosePet = CtrlFunc.valueOf(scanner.next());
        getAnimalInfoID(chosePet);
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT type_animal FROM animals WHERE id = " + chosePet);
            while (rs.next()) {
                String typeAnimal = rs.getString("type_animal");
                switch (typeAnimal) {
                    case "cat":
                        dbSelector = "SELECT commands from cats WHERE id_cat = " + chosePet;
                        rs = stmt.executeQuery(dbSelector);
                        System.out.println("Список команд: ");
                        while (rs.next()) {
                            System.out.println(rs.getString("commands"));
                        }
                        System.out.println("Для обучения командам нажмите (1), для очистки списка команд - (2), для выхода - (0)\n" +
                                ">>> ");
                        userChose = scanner.nextInt();
                        switch (userChose) {
                            case 1:
                                System.out.println("Введите команды которым хотели бы обучить питомца: \n" +
                                        ">>> ");
                                dbSelector = "UPDATE cats SET commands = \"" + CtrlFunc.stadyPetToSQL() + "\" WHERE id_cat = " + chosePet;
                                useSQL(dbSelector);
                                break;
                            case 2:
                                dbSelector = "UPDATE cats SET commands = null WHERE id_cat = " + chosePet;
                                useSQL(dbSelector);
                                System.out.println("Список команд очищен!");
                                break;
                            default:
                                System.out.println("Работа с блоком команд завершена!");
                        }
                        break;
                    case "dog":
                        dbSelector = "SELECT commands from dogs WHERE id_dog = " + chosePet;
                        rs = stmt.executeQuery(dbSelector);
                        System.out.println("Список команд: ");
                        while (rs.next()) {
                            System.out.println(rs.getString("commands"));
                        }
                        System.out.println("Для обучения командам нажмите (1), для очистки списка команд - (2), для выхода - (0)\n" +
                                ">>> ");
                        userChose = scanner.nextInt();
                        switch (userChose) {
                            case 1:
                                System.out.println("Введите команды которым хотели бы обучить питомца: \n" +
                                        ">>> ");
                                dbSelector = "UPDATE dogs SET commands = \"" + CtrlFunc.stadyPetToSQL() + "\" WHERE id_dog = " + chosePet;
                                useSQL(dbSelector);
                                break;
                            case 2:
                                dbSelector = "UPDATE dogs SET commands = null WHERE id_dog = " + chosePet;
                                useSQL(dbSelector);
                                System.out.println("Список команд очищен!");
                                break;
                            default:
                                System.out.println("Работа с блоком команд завершена!");
                        }
                        break;
                    case "hamster":
                        dbSelector = "SELECT commands from hamsters WHERE id_ham = " + chosePet;
                        rs = stmt.executeQuery(dbSelector);
                        System.out.println("Список команд: ");
                        while (rs.next()) {
                            System.out.println(rs.getString("commands"));

                        }
                        System.out.println("Для обучения командам нажмите (1), для очистки списка команд - (2), для выхода - (0)\n" +
                                ">>> ");
                        userChose = scanner.nextInt();
                        switch (userChose) {
                            case 1:
                                System.out.println("Введите команды которым хотели бы обучить питомца: \n" +
                                        ">>> ");
                                dbSelector = "UPDATE hamsters SET commands = \"" + CtrlFunc.stadyPetToSQL() + "\" WHERE id_ham = " + chosePet;
                                useSQL(dbSelector);
                                break;
                            case 2:
                                dbSelector = "UPDATE hamsters SET commands = null WHERE id_ham = " + chosePet;
                                useSQL(dbSelector);
                                System.out.println("Список команд очищен!");
                                break;
                            default:
                                System.out.println("Работа с блоком команд завершена!");
                        }
                        break;
                    case "hourse":
                        dbSelector = "SELECT commands from hourses WHERE id_hs = " + chosePet;
                        rs = stmt.executeQuery(dbSelector);
                        System.out.println("Список команд: ");
                        while (rs.next()) {
                            System.out.println(rs.getString("commands"));

                        }
                        System.out.println("Для обучения командам нажмите (1), для очистки списка команд - (2), для выхода - (0)\n" +
                                ">>> ");
                        userChose = scanner.nextInt();
                        switch (userChose) {
                            case 1:
                                System.out.println("Введите команды которым хотели бы обучить питомца: \n" +
                                        ">>> ");
                                dbSelector = "UPDATE hourses SET commands = \"" + CtrlFunc.stadyPetToSQL() + "\" WHERE id_hs = " + chosePet;
                                useSQL(dbSelector);
                                break;
                            case 2:
                                dbSelector = "UPDATE hourses SET commands = null WHERE id_hs = " + chosePet;
                                useSQL(dbSelector);
                                System.out.println("Список команд очищен!");
                                break;
                            default:
                                System.out.println("Работа с блоком команд завершена!");
                        }
                        break;
                    case "donkey":
                        dbSelector = "SELECT commands from donkeys WHERE id_don = " + chosePet;
                        rs = stmt.executeQuery(dbSelector);
                        System.out.println("Список команд: ");
                        while (rs.next()) {
                            System.out.println(rs.getString("commands"));

                        }
                        System.out.println("Для обучения командам нажмите (1), для очистки списка команд - (2), для выхода - (0)\n" +
                                ">>> ");
                        userChose = scanner.nextInt();
                        switch (userChose) {
                            case 1:
                                System.out.println("Введите команды которым хотели бы обучить питомца: \n" +
                                        ">>> ");
                                dbSelector = "UPDATE donkeys SET commands = \"" + CtrlFunc.stadyPetToSQL() + "\" WHERE id_don = " + chosePet;
                                useSQL(dbSelector);
                                break;
                            case 2:
                                dbSelector = "UPDATE donkeys SET commands = null WHERE id_don = " + chosePet;
                                useSQL(dbSelector);
                                System.out.println("Список команд очищен!");
                                break;
                            default:
                                System.out.println("Работа с блоком команд завершена!");
                        }
                        break;
                    case "camel":
                        dbSelector = "SELECT commands from camels WHERE id_camel = " + chosePet;
                        rs = stmt.executeQuery(dbSelector);
                        System.out.println("Список команд: ");
                        while (rs.next()) {
                            System.out.println(rs.getString("commands"));

                        }
                        System.out.println("Для обучения командам нажмите (1), для очистки списка команд - (2), для выхода - (0)\n" +
                                ">>> ");
                        userChose = scanner.nextInt();
                        switch (userChose) {
                            case 1:
                                System.out.println("Введите команды которым хотели бы обучить питомца: \n" +
                                        ">>> ");
                                dbSelector = "UPDATE camels SET commands = \"" + CtrlFunc.stadyPetToSQL() + "\" WHERE id_camel = " + chosePet;
                                useSQL(dbSelector);
                                break;
                            case 2:
                                dbSelector = "UPDATE camels SET commands = null WHERE id_camel = " + chosePet;
                                useSQL(dbSelector);
                                System.out.println("Список команд очищен!");
                                break;
                            default:
                                System.out.println("Работа с блоком команд завершена!");
                        }
                        break;
                    default:
                        System.out.println("///");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addAnimalToDB(Animals animals) {
        int id = getNextID();
        String name_animal = animals.getName();
        String type_animal = animals.getType_animal();
        String commands = animals.getCommands();
        boolean is_pet = animals.isPet();
        String birthday = makeBirthday(animals.getBirthday());
        String tableOfAnimals = null;
        String columnTypeID = null;
        switch (type_animal) {
            case "Cats":
                tableOfAnimals = "cats";
                columnTypeID = "id_cat";
                break;
            case "Dogs":
                tableOfAnimals = "dogs";
                columnTypeID = "id_dog";
                break;
            case "Hamsters":
                tableOfAnimals = "hamsters";
                columnTypeID = "id_ham";
                break;
            case "Camels":
                tableOfAnimals = "camels";
                columnTypeID = "id_camel";
                break;
            case "Hourses":
                tableOfAnimals = "hourses";
                columnTypeID = "id_hs";
                break;
            case "Donkeys":
                tableOfAnimals = "donkeys";
                columnTypeID = "id_don";
                break;
        }
        String insertSQLAnimal1 = "INSERT animals (id, name_animal, is_pet, type_animal) VALUES (" + id + " ," + "\"" +
                name_animal + "\", " + is_pet + ", \"" + type_animal + "\")";

        String insertSQLAnimal2 = "INSERT " + tableOfAnimals + " (" + columnTypeID + ", birthday, commands) " +
                "VALUES (" + id + ", " + birthday + ", " + "\"" + commands + "\")";
        useSQL(insertSQLAnimal1);
        useSQL(insertSQLAnimal2);
    }

    public static void useSQL(String str) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            stmt.execute(str);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
