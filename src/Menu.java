
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {


    //objet :
    City NuCityCara = new City();
    City_History NuCityHist = new City_History();


// les methodes pour swticher:
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
        int choix, cityId;
        String cityName;
        int currentTemperature, currentHumidity, currentWindSpeed;
        do {
            System.out.println("\t\t\t||======================================================================||");
            System.out.println("\t\t\t||------------|              villes Management              |-----------||");
            System.out.println("\t\t\t||======================================================================||");
            System.out.println("\t\t\t||------------|   1: Ajouter une ville                      |-----------||");
            System.out.println("\t\t\t||------------|   2: supprimer une ville                    |-----------||");
            System.out.println("\t\t\t||------------|   3: Modifier une ville                     |-----------||");
            System.out.println("\t\t\t||------------|   4: Afficher le tableau des villes         |-----------||");
            System.out.println("\t\t\t||------------|   5: Retour                                 |-----------||");
            System.out.println("\t\t\t||======================================================================||");
            System.out.println("Enter votre choix: ");
            choix = new Scanner(System.in).nextInt();
            switch (choix) {
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
                    System.out.print("Enter city ID to delete: ");
                    cityId = new Scanner(System.in).nextInt();
                    City.SupprimerCity(cityId);
                    break;
                case 3:
                    System.out.print("Enter city ID to update: ");
                    cityId = new Scanner(System.in).nextInt();

                    System.out.print("new name of city: ");
                    cityName = new Scanner(System.in).nextLine();

                    System.out.print(" new temperature : ");
                    currentTemperature = new Scanner(System.in).nextInt();

                    System.out.print(" new current Humidity : ");
                    currentHumidity = new Scanner(System.in).nextInt();

                    System.out.print(" new current Wind Speed : ");
                    currentWindSpeed = new Scanner(System.in).nextInt();
                    City.ModifierCity(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed));
                    break;

                case 4:
                    System.out.println("All  cities :");
                    for (City std : City.getAllCities()) {
                        System.out.println(std);
                    }
                    break;
                case 5:
                    OptionsManagemenet();
                    break;
                default:
                    System.out.println("choix invalide veillez ressayer !!");
            }
        } while (choix != 5);
    }


    public void VilleHistoryOptions() throws SQLException {
        int choix;
        do {
        System.out.println("\t\t\t||======================================================================||");
        System.out.println("\t\t\t||------------|           ville History Management          |-----------||");
        System.out.println("\t\t\t||======================================================================||");
        System.out.println("\t\t\t||------------|   1: Ajouter l'historique d'une ville       |-----------||");
        System.out.println("\t\t\t||------------|   2: Afficher les villes historique         |-----------||");
        System.out.println("\t\t\t||------------|   3: Modifier  l'historique d'une ville     |-----------||");
        System.out.println("\t\t\t||------------|   4: supprimer une ville                    |-----------||");
        System.out.println("\t\t\t||------------|   5: Retour                                 |-----------||");
        System.out.println("\t\t\t||======================================================================||");
        System.out.println();
        System.out.println("Entrer votre choix:");
        choix = new Scanner(System.in).nextInt();

            switch (choix) {

                case 1:
                    System.out.println("Enter HistoricalDataID :");
                    NuCityHist.setHistoricalDataId(new Scanner(System.in).nextInt());

                    System.out.println("Enter City ID :");
                    NuCityHist.setCityId(new Scanner(System.in).nextInt());

                    System.out.println("Enter Event date (YYYY-MM-DD):");
                    String EV = new Scanner(System.in).next();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    NuCityHist.setEventDate(LocalDate.parse(EV, dateTimeFormatter));

                    System.out.println("Enter Temperature :");
                    NuCityHist.setTemperature(new Scanner(System.in).nextInt());

                    City_History.addCityHistory(NuCityHist);
                    break;
                case 2:
                    System.out.println(" Tableaux des villes :");
                    for (City_History std : City_History.showCityHistory()) {
                        System.out.println(std);
                    }
                    break;

                case 3:

                    System.out.println("Enter the Historical Data ID of City history that you wanna update :");
                    NuCityHist.setHistoricalDataId(new Scanner(System.in).nextInt());

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
                    NuCityHist.setHistoricalDataId(new Scanner(System.in).nextInt());
                    City_History.DeleteCityHistory(NuCityHist);
                    break;

                case 5:
                    OptionsManagemenet();
                    break;

                default:
                    System.out.println("choix invalide veillez ressayer !!");


            }

        } while (choix != 5);
    }
}
