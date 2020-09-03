package controller;


import dao.TicketDAO;
import model.Ticket;

public class TicketController {

    TicketDAO ticketDAO = new TicketDAO();

    public void  save(Ticket ticket){
        ticketDAO.saveEntity(ticket);
    }

    public void  update(Ticket ticket){
        ticketDAO.updateEntity(ticket);
    }

    public void  remove(Ticket ticket){
        ticketDAO.removeEntity(ticket);
    }


    public Ticket getById(Long id){
        return ticketDAO.getEntityById(id);
    }
}
