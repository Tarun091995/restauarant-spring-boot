package com.hotel.reservation.springjwt.controllers;

import com.hotel.reservation.springjwt.model.AdminUser;
import com.hotel.reservation.springjwt.services.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "Admin Controller")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rest/login")
public class LoginController {


    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("Authenticate the user")
    @CrossOrigin(origins = "http://localhost:4200")

    public @ResponseBody
    ResponseEntity authentication(@RequestBody AdminUser adminUser )
    {
        System.out.println("in authentication Controller");
        String emailAddress = adminUser.getEmailaddress();
        String password = adminUser.getPassword();
        boolean isValid =  usersService.authenticate(emailAddress,password);
        if (isValid)
        {
            return new ResponseEntity<Boolean>(isValid,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Boolean>(isValid,HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping( method = RequestMethod.GET)
    @ApiOperation("Profile Details")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    ResponseEntity profile(String email)
    {
        System.out.println("in profile");
        AdminUser adminUser = usersService.profileDetails(email);
        return new ResponseEntity<AdminUser>(adminUser,HttpStatus.OK);
    }
}
