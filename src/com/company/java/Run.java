import dao.LocomotiveDAO;
import dao.PassengerDAO;
import dao.TicketDAO;
import model.Locomotive;
import model.Passenger;
import model.PassengerWagoon;
import model.Ticket;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Run {
    public static void main(String[] args) throws ParseException {
//        Locomotive locomotive1 = new Locomotive(11L, "Loco", 100, 1000, 1990, Locomotive.FuelType.DIESEL);
//        Locomotive locomotive2 = new Locomotive(15L, "Loco", 100, 1000, 1990, Locomotive.FuelType.DIESEL);
//        LocomotiveDAO locomotiveDAO = new LocomotiveDAO();
        //locomotiveDAO.saveEntity(locomotive1);
        //locomotiveDAO.saveEntity(locomotive2);
        //locomotiveDAO.removeEntity(locomotive1);
//        locomotiveDAO.saveEntity(locomotive2);

        // index = list.indexOf(entity); не работает в ПассажиреДао  :(((
//        Passenger passenger1 = new Passenger("FName_1", "SName_1", 1L);
//        Passenger passenger2 = new Passenger("FName_2", "SName_2", 2L);
//        Passenger passenger3 = new Passenger("FName_3", "SName_3", 3L);
//        Passenger passenger3_4 = new Passenger("FName_3/4", "SName_3/4", 3L);
//        PassengerDAO passengerDAO = new PassengerDAO();
//        passengerDAO.saveEntity(passenger1);
//        passengerDAO.saveEntity(passenger2);
//        passengerDAO.saveEntity(passenger3);
//        passengerDAO.removeEntity(passenger3);
//        System.out.println(passengerDAO.getEntityById(1L).toString());
//        passengerDAO.updateEntity(passenger3_4);


//        Date dateNew = null;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
//        String date1 = "20/10/2020 in 19:20";
//        try {
//            dateNew = dateFormat.parse(date1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Ticket ticket1 = new Ticket(1L, PassengerWagoon.TypeWagoon.PLATSKART,dateFormat.parse("21.10.2020 20:10") ,dateFormat.parse("18.10.2020 10:20"),true, Ticket.TeaCoffee.COFFEE, Ticket.Luggage.HEAVY_LUGGAGE);
        Ticket ticket2 = new Ticket(2L, PassengerWagoon.TypeWagoon.CW, dateFormat.parse("22.10.2020 21:50"),dateFormat.parse("18.10.2020 18:50"),false, Ticket.TeaCoffee.TEA, Ticket.Luggage.MEDIUM_LUGGAGE);
        Ticket ticket3 = new Ticket(3L, PassengerWagoon.TypeWagoon.KYPE,dateFormat.parse("25.01.2020 10:20"),dateFormat.parse("21.01.2020 17:30"),true, Ticket.TeaCoffee.NO_DRINKS, Ticket.Luggage.NO_LUGGAGE);

        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.saveEntity(ticket1);
        ticketDAO.saveEntity(ticket2);
        ticketDAO.saveEntity(ticket3);
    }
}
