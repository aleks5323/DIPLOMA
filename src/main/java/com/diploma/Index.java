package com.diploma;

import com.diploma.DAO.UsersEntityImpl;
import com.diploma.Entities.UsersEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/users")
public class Index {
//    private static final UsersEntityImpl userDao = new UsersEntityImpl();

//    @Inject
    private UsersEntityImpl userDao = UsersEntityImpl.getInstance();

    @POST
    @Path("/regUser")
    @Consumes(MediaType.APPLICATION_JSON)
//    public void regUesr(@FormParam("uname") String uname, @FormParam("uemail") String uemail, @FormParam("upassword") String upassword) throws NoSuchAlgorithmException {
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String authUser(UsersEntity user) throws NoSuchAlgorithmException {
        UsersEntity userCheck = userDao.getUserByLogin(user.getUname());
//        String out="";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(user.getUpassword().getBytes());
        String pwdFromDb = DatatypeConverter.printHexBinary(md.digest());

//        out += ("Login provided: " + user.getUname() + "\nPassword provided: " + pwdFromDb + "\n");
//        out += ("Password in DB: " + userCheck.getUpassword() + "\n");
        if (pwdFromDb.equals(userCheck.getUpassword()))
//            out += "true";
            return "true";
        else
//            out += "false";
        return "false";
//        return out;
    }
}