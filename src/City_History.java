
import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;

import static java.sql.DriverManager.getConnection;


//Classe City History:
public class City_History {
    private  int historicalDataId;
    private int cityId;
    private  LocalDate EventDate;
    private  int Temperature;

    public City_History(int historicalDataID, int cityId, LocalDate eventDate, int temperature) {
        this.historicalDataId = historicalDataID;
        this.cityId = cityId;
        this.EventDate = eventDate;
        this.Temperature = temperature;
    }

    public City_History() {

    }


  // Getters et Setters :

    public int getHistoricalDataId() {
        return historicalDataId;
    }

    public void setHistoricalDataId(int historicalDataId) {
        this.historicalDataId = historicalDataId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public LocalDate getEventDate() {
        return EventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        EventDate = eventDate;
    }

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int temperature) {
        Temperature = temperature;
    }





    // Les methodes :
    public static void addCityHistory(City_History NuCityHist) throws SQLException {
        String sql = "INSERT INTO CityHistory (historicalDataId, cityId, EventDate, Temperature) VALUES (?, ?, ?, ?)";
        Connection connection = City.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, NuCityHist.historicalDataId);
        statement.setInt(2, NuCityHist.cityId);
        statement.setDate(3, Date.valueOf(NuCityHist.EventDate));
        statement.setInt(4, NuCityHist.Temperature);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("The city history is added successfully ");
    }


    public static ArrayList<City_History> showCityHistory() throws SQLException {
        ArrayList<City_History> cityHistoryList = new ArrayList<>();
        String sql = "SELECT * FROM CityHistory";
        Connection connection = City.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int historicalDataId = resultSet.getInt("historicalDataId");
            int cityId = resultSet.getInt("cityId");
            LocalDate EventDate = resultSet.getDate("EventDate").toLocalDate();
            int Temperature = resultSet.getInt("Temperature");
            cityHistoryList.add(new City_History(historicalDataId, cityId, EventDate, Temperature));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return cityHistoryList;
    }




    public static void UpdateCityHistory(City_History NucityHist) throws SQLException {
        String sql = "UPDATE CityHistory SET Temperature=?, EventDate=? WHERE historicalDataId = ?";
        Connection connection = City.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, NucityHist.Temperature);
        statement.setDate(2, Date.valueOf(NucityHist.EventDate));
        statement.setInt(3, NucityHist.historicalDataId);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City History updated successfully ");


    }



    public static void DeleteCityHistory(City_History cityHistory) throws SQLException {
        String sql = "DELETE FROM CityHistory WHERE historicalDataId =?";
        Connection connection = City.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityHistory.historicalDataId);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City History deleted successfully ");

    }

    @Override
    public String toString() {
        return  "HistoricalDataID = " + historicalDataId + ", cityId = " + cityId + ", EventDate = " + EventDate + ", Temperature = " + Temperature
                ;
    }



}



