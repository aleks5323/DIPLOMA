package com.diploma;

import com.diploma.DAO.UsersEntityImpl;
import com.diploma.Entities.UsersEntity;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/users")
public class Users {
//    private static final UsersEntityImpl userDao = new UsersEntityImpl();

//    @Inject
    private UsersEntityImpl userDao = UsersEntityImpl.getInstance();

    @POST
    @Path("/regUser")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public void regUesr(UsersEntity user) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(user.getUpassword().getBytes());

        user.setUname(user.getUname());
        user.setUemail(user.getUemail());
        user.setUpassword(DatatypeConverter.printHexBinary(md.digest()));
        userDao.addUser(user);
    }

    @POST
    @Path("/test")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response test(UsersEntity user) {

        String result = "Data received: " + user.getUname() + "; " + user.getUemail() + "; " + user.getUpassword();
        return Response.status(201).entity(result).build();
    }

    @GET
    @Path("/delUser/{uid}")
    public void delUser(@PathParam("uid") Integer uid) {
        userDao.removeUser(uid);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listUsers() {
        List<UsersEntity> list = new ArrayList();
        list = userDao.listUsers();

        String out="";

        for (UsersEntity user : list) {
            out += (user.getUname() + "\t\t" + user.getUemail() + "\t\t\n");
        }

        return out;
    }

    @POST
    @Path("/authUser")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authUser(UsersEntity user) throws NoSuchAlgorithmException {
        UsersEntity userCheck = userDao.getUserByLogin(user.getUname());
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(user.getUpassword().getBytes());
        String pwdFromDb = DatatypeConverter.printHexBinary(md.digest());

        if (pwdFromDb.equals(userCheck.getUpassword()))
            return Response.accepted().cookie(new NewCookie("login", user.getUname(), "/", "localhost", "", 1, false)).cookie(new NewCookie("pass", userCheck.getUpassword(), "/", "localhost", "", 1, false)).build();
        else
            return Response.status(Response.Status.FORBIDDEN).build();
    }
}