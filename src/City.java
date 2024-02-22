import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class City {
    int cityId;     //Identifiant unique pour la ville.
    String cityName;     //Nom de la ville.
    double currentTemperature;    //Température actuelle.
    double currentHumidity;   //Taux d'humidité actuelle.
    double currentWindSpeed;  //Vitesse du vent actuelle.

    public City(int cityId, String cityName, double currentTemperature, double currentHumidity, double currentWindSpeed) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        this.currentWindSpeed = currentWindSpeed;
    }

    public static Connection getConnection()throws SQLException {
         return DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/Weather_APP",
                 "root",
                 "alaa2001..");
     }

    public static List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM City");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int cityId = resultSet.getInt("cityId");
            String cityName = resultSet.getString("cityName");
            double currentTemperature = resultSet.getDouble("currentTemperature");
            double currentHumidity = resultSet.getDouble("currentHumidity");
            double currentWindSpeed = resultSet.getDouble("currentWindSpeed");
            cities.add(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return cities;
    }





    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", currentTemperature=" + currentTemperature +
                ", currentHumidity=" + currentHumidity +
                ", currentWindSpeed=" + currentWindSpeed +
                '}';
    }
}
