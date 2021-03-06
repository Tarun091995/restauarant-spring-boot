package com.hotel.reservation.springjwt.controllers;

import com.hotel.reservation.springjwt.model.Reservation;
import com.hotel.reservation.springjwt.services.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// this class is responsible for managing reservations
@RestController
@Api(value = "Reservation Controller")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rest/reservation")

public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = "Hello world",method = RequestMethod.GET)
    public ResponseEntity<String> helloWorld()
    {
        return  new ResponseEntity<String>("hello world", HttpStatus.OK);
    }


    /**
     * This function will create a reservation
     * @param reservation
     * @return
     */
    @RequestMapping(method = RequestMethod.POST )
    @ApiOperation("creates Reservation")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    ResponseEntity createReservation(@RequestBody Reservation reservation)
    {
        System.out.println("in create reservation");
        Reservation reservationfromdb = reservationService.createReservation(reservation);
        return new ResponseEntity<Reservation>(reservationfromdb,HttpStatus.OK);
    }

    /**
     * This function will update the existing reservation
     * @param reservation
     * @return
     */

    @RequestMapping( method = RequestMethod.PUT)
    @ApiOperation("Updates Reservation")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    ResponseEntity updateReservation (@RequestBody Reservation reservation)
    {
        Reservation reservationfromdb = reservationService.updateReservation(reservation);
        return new ResponseEntity<Reservation>(reservationfromdb,HttpStatus.OK);
    }

    /**
     * this function will cancel the reservation
     * @param id
     * @return
     */

    //@RequestMapping( method = RequestMethod.DELETE)

    @ApiOperation("Cancel Reservation")
    @RequestMapping(value="/id/{id}",method = RequestMethod.DELETE)
    @CrossOrigin(origins = "http://localhost:4200")
    public  @ResponseBody
    ResponseEntity cancelReservation(@PathVariable("id") long id)
    {
        Reservation cancelledReservation = reservationService.cancelReservation(id);
        return new ResponseEntity<Reservation>(cancelledReservation,HttpStatus.OK);
    }

    /**
     * this method will fecth reservation for a given id
     * @param id
     * @return
     */

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/id/{id}",method = RequestMethod.GET)
    @ApiOperation("Get Reservations")
    public  @ResponseBody ResponseEntity getReservationForId(@PathVariable("id") long id)
    {
        Reservation fetchReservationForId = reservationService.fetchReservationForId(id);
        return new ResponseEntity<Reservation>(fetchReservationForId,HttpStatus.OK);
    }

    /**
     * this method will fetch all reservations
     * @return
     */


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("fetch all Reservations")
    public @ResponseBody ResponseEntity fetchAllReservations()
    {
        List<Reservation> allReservations = reservationService.listAllReservations();
        return new ResponseEntity<List<Reservation>>(allReservations,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "seating",method = RequestMethod.GET)
    @ApiOperation("Fetch the reservation other than cancel")
    public @ResponseBody ResponseEntity fetchReservationsForSeating()
    {
        List<Reservation> reservationsNotInCancelState = reservationService.listAllReservationsForSeating();
        return new ResponseEntity<List<Reservation>>(reservationsNotInCancelState,HttpStatus.OK);
    }



    /**
     * this function will assign a table to a existing reservation
     * @param id
     * @param tableNumber
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "assignTable/id/{id}/tableNumber/{tableNumber}" , method = RequestMethod.PUT)
    @ApiOperation("assign Table")
    public @ResponseBody ResponseEntity assignTableForReservation(@PathVariable("id") long id , @PathVariable("tableNumber") int tableNumber)
    {
        Reservation assignTable = reservationService.assignTable(id,tableNumber);
        return new ResponseEntity<Reservation>(assignTable,HttpStatus.OK);
    }

    /**
     * Get Reservation for a confirmation code
     * @param code
     * @return
     */

    @CrossOrigin(origins = "http://locahost:4200")
    @RequestMapping(value = "code/{code}" , method = RequestMethod.GET)
    @ApiOperation("Get Reservation Based on confirmationcode")
    public @ResponseBody ResponseEntity getReservationOnCode(@PathVariable("code") String code)
    {
        Reservation getReservationByCode = reservationService.fecthReservationForCode(code);
        List<Reservation> reservations = new ArrayList<Reservation>();
        reservations.add(getReservationByCode);
        return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
    }

    /**
     *this function will return the all customer details
     * @return
     */
    @CrossOrigin(origins = "http://locahost:4200")
    @RequestMapping(value = "customerDetails" , method = RequestMethod.GET)
    @ApiOperation("Get Customers Details")
    public @ResponseBody ResponseEntity customerList()
    {
        List<Reservation> allCustomerDetails = reservationService.getAllCustomerDetails();
        return new ResponseEntity<>(allCustomerDetails,HttpStatus.OK);
    }
}
