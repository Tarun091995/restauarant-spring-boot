package com.hotel.reservation.springjwt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.reservation.springjwt.common.ReservationStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reservation")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservation {

    @Id
    @Column(name="id",nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;





    @Column(name = "customerName")
    private String customerName;

    private static final String MY_TIME_ZONE="Asia/Kolkata";
    @Column(name = "reservationTime")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd HH:mm:ss",timezone = MY_TIME_ZONE)
    private Timestamp reservationTime;

    @Column(name = "partySize")
    private int partySize;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name = "confirmationCode")
    private String confirmationCode;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status ;

    @Column(name = "tableNumber" )
    private int tableNumber = -1;

    private String formattedReservationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int gettableNumber() {
        return tableNumber;
    }

    public void settableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public String getFormattedReservationTime() {
        LocalDateTime localDateTime =
                Instant.ofEpochMilli(reservationTime.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customerName='" + customerName + '\'' +
                ", reservationTime='" + reservationTime + '\'' +
                ", partySize='" + partySize + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", confirmationCode='" + confirmationCode + '\'' +
                ", status=" + status + '\'' +
                ", TableNumber" + tableNumber +
                '}';
    }
}

