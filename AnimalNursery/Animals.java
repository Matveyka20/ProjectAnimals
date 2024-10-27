package AnimalNursery;

import Controller.CtrlFunc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class Animals {
    int id, bYear, bMonth, bDay;
    String name, type_animal;
    public boolean isPet;
    ArrayList<String> commands = new ArrayList<>();
    LocalDate birthday;

    public static List<Animals> animalsList = new ArrayList<>();

    public Animals(int id, String name, int bDay, int bMonth, int bYear, boolean isPet,
                String type_animal, ArrayList<String> commands) {
        this.id = id;
        this.isPet = isPet;
        this.name = name;
        this.type_animal = type_animal;
        this.birthday = LocalDate.of(bYear, bMonth, bDay);
        this.commands = commands;
    }

    @Override
    public String toString() {
        return ("id: " + id + " " + type_animal + " " + name + " Дата рождения: " + birthday);
    }

    public String printCommands(){
        return commands.toString();
    }

    public int getId() {
        return id;
    }

    public int[] getBirthday(){
        int[] res = new int[3];
        res[0] = bDay;
        res[1] = bMonth;
        res[2] = bYear;
        return res;
    }

    public String getName(){
        return name;
    }

    public boolean isPet() {
        return isPet;
    }

    public String getCommands(){return commands.toString(); }
    public String getType_animal() {
        return type_animal;
    }

    public static Animals createAnimal() {
        Animals animals = null;
        Scanner scanner = new Scanner(System.in);
        int typeAnimal;
        System.out.println("Для добавления нового питомца, необходимо заполнить анкету.\n");
        System.out.println("Укажите тип питомца:\n" +
                "Dogs - 1\n" +
                "Cats - 2\n" +
                "Hamsters - 3\n" +
                "Hourses - 4\n" +
                "Donkeys - 5\n" +
                "Camels - 6\n" +
                "Для отмены - 0");
        System.out.println(">>> ");
        typeAnimal = scanner.nextInt();
        switch (typeAnimal) {
            case (1):
                animals = new Dogs();
                //animalsList.add(dogs);
                break;
            case (2):
                animals = new Cats();
                //animalsList.add(cats);
                break;
            case (3):
                animals = new Hamsters();
                //animalsList.add(hamsters);
                break;
            case (4):
                animals = new Hourses();
                //animalsList.add(hourses);
                break;
            case (5):
                animals = new Donkeys();
                //animalsList.add(donkeys);
                break;
            case (6):
                animals = new Camels();
                //animalsList.add(camels);
                break;
            default:
                System.out.println("Вы не ввели число от 1 до 6");
        }
        return animals;
    }

    public static Animals setAnimalData(Animals animals) {
        animals.id = 1;
        animals.setName();
        animals.setBirthday();
        animals.studyPet();
    return animals;
    }

    protected void setName() {
        String res;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя питомца: ");
        System.out.println(">>> ");
        res = scanner.next();
        this.name = res;
    }

    protected void setBirthday() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату рождения питомца в формате DD-MM-YYYY");
        System.out.println("Введите Day");
        System.out.println(">>> ");
        this.bDay = scanner.nextInt();
        System.out.println("Введите MM");
        System.out.println(">>> ");
        this.bMonth = scanner.nextInt();
        System.out.println("Введите YYYY");
        System.out.println(">>> ");
        this.bYear = scanner.nextInt();
        this.birthday = LocalDate.of(bYear,bMonth,bDay);
    }

    protected void setID(List<Animals> list) {
        int result = 1;
        if (list.size()==1) {
            this.id = 1;
        } else {
            for (Animals a: list) {
                if (a.id > result) result = a.id;
            }
            this.id = result + 1;
        }
    }

    protected void studyPet(){
        Scanner scanner = new Scanner(System.in);
        String userAnswer;
        int numberOfCommand;
        System.out.println("Ваш питомец обучен командам или вы хотели бы его обучить?\n" +
                "y/n\n" +
                ">>> ");
        userAnswer = scanner.next();
        if (userAnswer.equalsIgnoreCase("y")){
            CtrlFunc.showCommands();
            System.out.println("Введите команды, которые знает/хочет узнать ваш питомец \n" +
                    "для выхода/отмены нажмите 0\n" +
                    ">>> ");
            userAnswer = scanner.next();
            numberOfCommand = CtrlFunc.valueOf(userAnswer);
            while (numberOfCommand != 0){
                if (numberOfCommand > 8 || numberOfCommand < 1) {
                    System.out.println("Введите номер команды от 1 до 8");
                    userAnswer = scanner.next();
                    numberOfCommand = CtrlFunc.valueOf(userAnswer);
                }
                else{
                    userAnswer = PetsCommands.Взять.getcommand(numberOfCommand);
                    this.commands.add(userAnswer);
                    userAnswer = scanner.next();
                    numberOfCommand = CtrlFunc.valueOf(userAnswer);
                }

            }
        }
    }

}