import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {


    //objet :
    City NuCityCara = new City();
    City_History NuCityHist = new City_History();


    public void OptionsManagemenet() throws SQLException {

        int choix;
        do {
            System.out.println();
            System.out.println("voici les options disponibles:");
            System.out.println("1. Pour accéder aux options du City");
            System.out.println("2. Pour accéder aux options du City History ");
            System.out.println("3. Sortie");
            System.out.println("_____________________________");
            System.out.print("Entrer votre choix : ");
            System.out.println("entrer un choix :");
            choix = new Scanner(System.in).nextInt();
            switch (choix) {
                case 1:
                    villeOptions();
                    break;
                case 2:
                    VilleHistoryOptions();
                    break;
                case 3:
                    System.out.println("Sortie...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 3);

    }





    public void villeOptions() throws SQLException {
        int choice, cityId;
        String cityName;
        int currentTemperature, currentHumidity, currentWindSpeed;
        do {
            System.out.println("\t\t\t||======================================================================||");
            System.out.println("\t\t\t||------------|              villes Management            |-----------||");
            System.out.println("\t\t\t||======================================================================||");
            System.out.println("\t\t\t||------------|   1: Ajouter ville                             |-----------||");
            System.out.println("\t\t\t||------------|   2: supprimer ville                            |-----------||");
            System.out.println("\t\t\t||------------|   3: Modifier ville                          |-----------||");
            System.out.println("\t\t\t||------------|   4: Afficher le tableau des villes                  |-----------||");
            System.out.println("\t\t\t||------------|   5: Retour                                     |-----------||");
            System.out.println("\t\t\t||------------|   6: Quitter application                    |-----------||");
            System.out.println("\t\t\t||======================================================================||");
            System.out.println("Enter votre choix: ");
            choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    System.out.print("ID : ");
                    NuCityCara.setCityId(new Scanner(System.in).nextInt());

                    System.out.print("NAME : ");
                    NuCityCara.setCityName(new Scanner(System.in).nextLine());

                    System.out.print("temperature : ");
                    NuCityCara.setCurrentTemperature(new Scanner(System.in).nextInt());

                    System.out.print("current Humidity : ");
                    NuCityCara.setCurrentHumidity(new Scanner(System.in).nextInt());

                    System.out.print("current Wind Speed : ");
                    NuCityCara.setCurrentWindSpeed(new Scanner(System.in).nextInt());

                    City.AjouterCity(NuCityCara);
                    break;
                case 2:
                    System.out.print("Enter student ID to delete: ");
                    cityId = new Scanner(System.in).nextInt();
                    City.SupprimerCity(cityId);
                    break;
                case 3:
                    System.out.print("Enter ville ID to update: ");
                    cityId = new Scanner(System.in).nextInt();

                    System.out.print("new nom de ville : ");
                    cityName = new Scanner(System.in).nextLine();

                    System.out.print(" new temperature : ");
                    currentTemperature = new Scanner(System.in).nextInt();

                    System.out.print(" new current Humidity : ");
                    currentHumidity = new Scanner(System.in).nextInt();

                    System.out.print(" new current Wind Speed : ");
                    currentWindSpeed = new Scanner(System.in).nextInt();
                    City.ModifierCity(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed));
                    break;

//                case 4:
////                    System.out.println("All  villes :");
////                    for (Student std : City.getAllStudents()) {
////                        System.out.println(std);
////                    }
//                    break;
                case 5:
                    OptionsManagemenet();
                case 6:
                    System.out.println(" merci pour l'utilisation de notre app !!!");
                    break;
                default:
                    System.out.println("choix invalide veillez ressayer !!");
            }
        } while (choice != 6);
    }


    public void VilleHistoryOptions() throws SQLException {
        System.out.println("\t\t\t||======================================================================||");
        System.out.println("\t\t\t||------------|              ville History Management            |-----------||");
        System.out.println("\t\t\t||======================================================================||");
        System.out.println("\t\t\t||------------|   1: Ajouter ville  hidtory                            |-----------||");
        System.out.println("\t\t\t||------------|   2: Afficher ville   hidtory                         |-----------||");
        System.out.println("\t\t\t||------------|   3: Modifier ville   hidtory                       |-----------||");
        System.out.println("\t\t\t||------------|   4: supprimer le tableau des villes      hidtory            |-----------||");
        System.out.println("\t\t\t||------------|   5: recherche          ville history                            |-----------||");
        System.out.println("\t\t\t||------------|   6: Retour                                     |-----------||");
        System.out.println("\t\t\t||------------|   7: Quitter application                    |-----------||");


        System.out.println("Entrer votre choix:");
        int choix = new Scanner(System.in).nextInt();
        do {


            switch (choix) {

                case 1:
                    System.out.println("Enter HistoricalDataID :");
                    NuCityHist.setHistoricalDataID(new Scanner(System.in).nextInt());

                    System.out.println("Enter City ID :");
                    NuCityHist.setID(new Scanner(System.in).nextInt());

                    System.out.println("Enter Event date (YYYY-MM-DD):");
                    String EV = new Scanner(System.in).next();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    NuCityHist.setEventDate(LocalDate.parse(EV, dateTimeFormatter));

                    System.out.println("Enter Temperature :");
                    NuCityHist.setTemperature(new Scanner(System.in).nextInt());

                    City_History.addCityHistory(NuCityHist);

                    break;

                case 2:
                    System.out.println("***************************List of city history**************************");
                    System.out.println("                                                                         ");
                    City_History.showCityHistory().forEach(cityHistory1 -> System.out.println(cityHistory1));
                    System.out.println("                                                                         ");
                    break;

                case 3:
                    System.out.println("Enter the Historical Data ID of City history that you wanna update :");
                    NuCityHist.setHistoricalDataID(new Scanner(System.in).nextInt());

                    System.out.println("Enter the new Historical Data ID :");
                    NuCityHist.setHistoricalDataID(new Scanner(System.in).nextInt());

                    System.out.println("Enter the new Event Date (YYYY-MM-DD) :");
                    String newEventDate = new Scanner(System.in).next();
                    dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    NuCityHist.setEventDate(LocalDate.parse(newEventDate, dateTimeFormatter));

                    System.out.println("Enter the new Temperature :");
                    NuCityHist.setTemperature(new Scanner(System.in).nextInt());

                    City_History.UpdateCityHistory(NuCityHist);
                    break;

                case 4:
                    System.out.println("Enter the Historical Data ID of City History that you wanna delete :");
                    NuCityHist.setHistoricalDataID(new Scanner(System.in).nextInt());
                    City_History.DeleteCityHistory(NuCityHist);
                    break;

                case 5:
                    City_History cityHistory1 = new City_History();
                    System.out.print("Enter the ID of city that you want to see its history: ");
                    int cityIdForHistoryRead = new Scanner(System.in).nextInt();
                    cityHistory1.readCityHistory(cityIdForHistoryRead);
                    break;
                case 6:
                    OptionsManagemenet();
                case 7:
                    System.out.println(" merci pour l'utilisation de notre app !!!");
                    break;

                default:
                    System.out.println("choix invalide veillez ressayer !!");


            }
        } while (choix

                != 7);
    }
}
