package com.hotel.reservation.springjwt.services;


import com.hotel.reservation.springjwt.model.AdminUser;
import com.hotel.reservation.springjwt.model.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private AdminUserRepository adminUserRepository;

    public AdminUser createUser(AdminUser user)
    {
        AdminUser users = adminUserRepository.save(user);
        return user;
    }

    public  Boolean authenticate(String emailaddress, String password)
    {
        AdminUser userfromdb = adminUserRepository.findAdminUserByEmailAddress(emailaddress);
        if (userfromdb != null && userfromdb.getPassword().equals(password))
        {
            return true;
        }
        return false;

    }

    public  AdminUser profileDetails(String email)
    {
        AdminUser profileDetails = adminUserRepository.findAdminUserByEmailAddress(email);
        return  profileDetails;
    }
}
