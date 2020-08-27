import dao.LocomotiveDAO;
import dao.PassengerDAO;
import model.Locomotive;
import model.Passenger;

public class Run {
    public static void main(String[] args) {
//        Locomotive locomotive1 = new Locomotive(11L, "Loco", 100, 1000, 1990, Locomotive.FuelType.DIESEL);
//        Locomotive locomotive2 = new Locomotive(15L, "Loco", 100, 1000, 1990, Locomotive.FuelType.DIESEL);
//        LocomotiveDAO locomotiveDAO = new LocomotiveDAO();
        //locomotiveDAO.saveEntity(locomotive1);
        //locomotiveDAO.saveEntity(locomotive2);
        //locomotiveDAO.removeEntity(locomotive1);
//        locomotiveDAO.saveEntity(locomotive2);

        // index = list.indexOf(entity); не работает в ПассажиреДао  :(((
        Passenger passenger1 = new Passenger("FName_1", "SName_1", 1L);
        Passenger passenger2 = new Passenger("FName_2", "SName_2", 2L);
        Passenger passenger3 = new Passenger("FName_3", "SName_3", 3L);
        Passenger passenger3_4 = new Passenger("FName_3/4", "SName_3/4", 3L);
        PassengerDAO passengerDAO = new PassengerDAO();
        passengerDAO.saveEntity(passenger1);
        passengerDAO.saveEntity(passenger2);
        passengerDAO.saveEntity(passenger3);
        passengerDAO.removeEntity(passenger3);
        System.out.println(passengerDAO.getEntityById(1L).toString());
        passengerDAO.updateEntity(passenger3_4);


    }
}
