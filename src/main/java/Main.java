import model.Grant;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Grant> grants = new ArrayList<>();
        grants = Tools.getGrantsFromCsv("E:\\Engine\\JavaP\\GrantProj\\src\\main\\resources\\data.csv");
        DataBase.connection();
        DataBase.createDB();
        DataBase.writeDB(grants);

        System.out.println("1 Задача: ");
        System.out.println("Постройте график по среднему количеству рабочих мест для каждого фискального года");
        System.out.println("Ответ в отдельном окне");
        DataBase.GraphAveragePlaces();

        String task2 = DataBase.AverageGrant();
        System.out.println("2 Задача: ");
        System.out.println("Выведите в консоль средний размер гранта для бизнеса типа \"Salon/Barbershop\"");
        System.out.println("Ответ - " + task2);

        String task3 = DataBase.MostWorkPlaces();
        System.out.println("3 Задача: ");
        System.out.println("Выведите в консоль тип бизнеса, предоставивший " +
                        "наибольшее количество рабочих мест, где размер гранта не превышает $55,000.00 ");
        System.out.println("Ответ - " + task3);
    }
}
