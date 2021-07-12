package com.hotel.reservation.springjwt.controllers;

import com.hotel.reservation.springjwt.model.Reservation;
import com.hotel.reservation.springjwt.services.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = "Hello world",method = RequestMethod.GET)
    public ResponseEntity<String> helloWorld()
    {
        return  new ResponseEntity<String>("hello world", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST )
    @ApiOperation("creates Reservation")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    ResponseEntity createReservation(@RequestBody Reservation reservation)
    {
        System.out.println("*************************");
        System.out.println(" in create reservation ");
        System.out.println("*************************");
//        System.out.println("Customer Id"  + reservation.getCustomer_id());
//        System.out.println("Customer Name" + reservation.getCustomer_name());
//        System.out.println("Reservation Date" + reservation.getReservation_date());
//        System.out.println("Time" + reservation.getTime());
//        System.out.println("Party Size" + reservation.getParty_size());
//        System.out.println("Phone Number" + reservation.getPhone_number());
//        System.out.println("Address" + reservation.getAddress());

        Reservation reservationfromdb = reservationService.createReservation(reservation);
        return new ResponseEntity<Reservation>(reservationfromdb,HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.PUT)
    @ApiOperation("Updates Reservation")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    ResponseEntity updateReservation (@RequestBody Reservation reservation)
    {
        Reservation reservationfromdb = reservationService.updateReservation(reservation);
        return new ResponseEntity<Reservation>(reservationfromdb,HttpStatus.OK);
    }

    //@RequestMapping( method = RequestMethod.DELETE)
    @ApiOperation("Cancel Reservation")
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/id/{id}",method = RequestMethod.DELETE)
    public  @ResponseBody
    ResponseEntity cancelReservation(@PathVariable("id") long id)
    {
        Reservation cancelledReservation = reservationService.cancelReservation(id);
        return new ResponseEntity<Reservation>(cancelledReservation,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    //@RequestMapping( method = RequestMethod.GET)
    @RequestMapping(value="/id/{id}",method = RequestMethod.GET)
    @ApiOperation("Get Reservations")
    public  @ResponseBody ResponseEntity getindividualReservation(@PathVariable("id") long id)
    {
        Reservation fetchReservationForId = reservationService.fetchReservationForId(id);
        return new ResponseEntity<Reservation>(fetchReservationForId,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("fetch all Reservations")
    public @ResponseBody ResponseEntity fetchAllReservations()
    {
        List<Reservation> allReservations = reservationService.listAllReservations();
        return new ResponseEntity<List<Reservation>>(allReservations,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "assignTable/id/{id}/tableNumber/{tableNumber}" , method = RequestMethod.PUT)
    @ApiOperation("assign Table")
    public @ResponseBody ResponseEntity assignTable(@PathVariable("id") long id , @PathVariable("tableNumber") int tableNumber)
    {
        Reservation assignTable = reservationService.assignTable(id,tableNumber);
        return new ResponseEntity<Reservation>(assignTable,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://locahost:4200")
    @RequestMapping(value = "confirmationCode/{code}" , method = RequestMethod.GET)
    @ApiOperation("Get Reservation Based on confirmationcode")
    public @ResponseBody ResponseEntity getReservationOnCode(@PathVariable("code") String code)
    {
        System.out.println("*************************");
//        System.out.println(" in check Reservation ");
//        System.out.println("*************************");
        Reservation getReservationByCode = reservationService.fecthReservationForCode(code);
        return new ResponseEntity<Reservation>(getReservationByCode,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://locahost:4200")
    @RequestMapping(value = "customerDetails" , method = RequestMethod.GET)
    @ApiOperation("Get Phone Numbers of Customers")
    public @ResponseBody ResponseEntity PhoneNumberList()
    {
        List<Reservation> allCustomerDetails = reservationService.getAllCustomerDetails();
        return new ResponseEntity<>(allCustomerDetails,HttpStatus.OK);
    }
}
