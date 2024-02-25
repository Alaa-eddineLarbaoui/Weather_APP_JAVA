import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;


// le attribus :
public class City {
    private int cityId;     //Identifiant unique pour la ville.
    private String cityName;     //Nom de la ville.
    private int currentTemperature;    //Température actuelle.
    private int currentHumidity;   //Taux d'humidité actuelle.
    private int currentWindSpeed;  //Vitesse du vent actuelle.

    public City() {

    }


    // getter et setter:

    public int getCityId() {return cityId;}

    public void setCityId(int cityId) { this.cityId = cityId;}

    public String getCityName() {return cityName;}

    public void setCityName(String cityName) {this.cityName = cityName;}

    public int getCurrentTemperature() {return currentTemperature;}

    public void setCurrentTemperature(int currentTemperature) {this.currentTemperature = currentTemperature;}

    public int getCurrentHumidity() {return currentHumidity;}

    public void setCurrentHumidity(int currentHumidity) {this.currentHumidity = currentHumidity;}

    public int getCurrentWindSpeed() {return currentWindSpeed;}

    public void setCurrentWindSpeed(int currentWindSpeed) {this.currentWindSpeed = currentWindSpeed;}

    //constructeur :
    public City(int cityId, String cityName, int currentTemperature, int currentHumidity, int currentWindSpeed) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        this.currentWindSpeed = currentWindSpeed;
    }


    @Override
    public String toString() {
        return "cityId=" + cityId + ", cityName='" + cityName  + ", currentTemperature=" + currentTemperature + ", currentHumidity=" + currentHumidity + ", currentWindSpeed=" + currentWindSpeed + '}';
    }




    //Connection :
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Weatheer_APP",
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
            int currentTemperature = resultSet.getInt("currentTemperature");
            int currentHumidity = resultSet.getInt("currentHumidity");
            int currentWindSpeed = resultSet.getInt("currentWindSpeed");
            cities.add(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return cities;
    }




    // Les Methodes :
    public static void AjouterCity(City NuCityCara) {
        try {
            Connection connection = getConnection();
            String query = "INSERT INTO City (cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, NuCityCara.cityId);
                preparedStatement.setString(2, NuCityCara.cityName);
                preparedStatement.setInt(3,NuCityCara.currentTemperature);
                preparedStatement.setInt(4, NuCityCara.currentHumidity);
                preparedStatement.setInt(5, NuCityCara.currentWindSpeed);
                preparedStatement.executeUpdate();
                System.out.println("la ville a ete ajouter avec succes ");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void SupprimerCity(int cityId) throws SQLException {
        String sql = "DELETE FROM City WHERE cityId = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityId);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("la ville a ete supprimer avec succes !!");
    }


    public static void ModifierCity(City city) throws SQLException {
        String sql = "UPDATE City SET cityName = ? ,currentTemperature = ?, currentHumidity = ? ,currentWindSpeed = ?  WHERE cityId = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,city.cityName);
        statement.setInt(2, city.currentTemperature);
        statement.setInt(3, city.currentHumidity);
        statement.setInt(4, city.currentWindSpeed);
        statement.setInt(5, city.cityId);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City updated successfully!");
    }
}








