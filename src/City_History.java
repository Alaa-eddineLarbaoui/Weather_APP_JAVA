
import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;


//Classe City History:
public class City_History {
    private  int HistoricalDataID;
    private int ID;
    private  LocalDate EventDate;
    private  int Temperature;

    public City_History(int historicalDataID, int ID, LocalDate eventDate, int temperature) {
        HistoricalDataID = historicalDataID;
        this.ID = ID;
        EventDate = eventDate;
        Temperature = temperature;
    }

    public City_History() {

    }
  // Getters et Setters :
    public int getHistoricalDataID() {
        return HistoricalDataID;
    }

    public void setHistoricalDataID(int historicalDataID) {
        HistoricalDataID = historicalDataID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
    public static void addCityHistory(City_History cityHistory) throws SQLException {
        String sql = "INSERT INTO cityhistory (HistoriacalDataID ,ID ,EventDate ,Temperature) values (?,?,?,?)";
        Connection connection = City.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityHistory.HistoricalDataID);
        statement.setInt(2, cityHistory.ID);
        statement.setDate(3, Date.valueOf(cityHistory.EventDate));
        statement.setInt(4, cityHistory.Temperature);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("The city history is added successfully ");
    }


    public static ArrayList<City_History> showCityHistory() throws SQLException {
        ArrayList<City_History> cityHistoryList = new ArrayList<>();
        String sql = "SELECT * FROM cityhistory";
        Connection connection = City.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int HistoricalDataID = resultSet.getInt("HistoriacalDataID");
            int ID = resultSet.getInt("ID");
            LocalDate EventDate = resultSet.getDate("EventDate").toLocalDate();
            int Temperature = resultSet.getInt("Temperature");
            cityHistoryList.add(new City_History(HistoricalDataID, ID, EventDate, Temperature));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return cityHistoryList;
    }


    public static void UpdateCityHistory(City_History NucityHist) throws SQLException {
        String sql = "UPDATE cityhistory SET Temperature=?, EventDate=? WHERE HistoriacalDataID = ?";
        Connection connection = City.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, NucityHist.Temperature);
        statement.setDate(2, Date.valueOf(NucityHist.EventDate));
        statement.setInt(3, NucityHist.HistoricalDataID);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City History updated successfully ");


    }



    public static void DeleteCityHistory(City_History cityHistory) throws SQLException {
        String sql = "DELETE FROM cityhistory WHERE HistoriacalDataID =?";
        Connection connection = City.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityHistory.HistoricalDataID);
        statement.executeUpdate();
        connection.close();
        statement.close();
        System.out.println("City History deleted successfully ");

    }

    @Override
    public String toString() {
        return  "HistoricalDataID = " + HistoricalDataID + ", ID = " + ID + ", EventDate = " + EventDate + ", Temperature = " + Temperature
                ;
    }

    public void readCityHistory(int ID) throws  SQLException{
        String sql = "SELECT ch.HistoriacalDataID, ch.ID, ch.EventDate, ch.Temperature, c.name " +
                "FROM cityhistory ch " +
                "JOIN city c ON ch.ID = c.ID " +
                "WHERE ch.ID = ?";
        PreparedStatement statement = City.getConnection().prepareStatement(sql);
        statement.setInt(1, ID);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            HistoricalDataID = resultSet.getInt("HistoriacalDataID");
            ID = resultSet.getInt("ID");
            String name = resultSet.getString("name");
            EventDate = Date.valueOf(resultSet.getDate("EventDate").toLocalDate()).toLocalDate();
            Temperature = resultSet.getInt("Temperature");

            System.out.println("Historical Data ID: " + HistoricalDataID + ", City ID: " + ID +
                    ", name: " + name + ", Event Date: " + EventDate + ", Temperature: " + Temperature);

        }
    }



}



//    private int historicalDataId;  //Identifiant unique pour les données historiques.
//    private int cityId;          //Identifiant de la ville associée.
//    private String eventDate;    //Date de l'événement météorologique historique.
//    private int temperature;    //Température historique
//
//
//    public void AjouterHisto(){
//
//    }
//    public void AfficherHisto() {
//
//    }
//
//    public void SupprimerHisto() {
//
//    }
//
//    public void ModifierHisto() {
//    }


