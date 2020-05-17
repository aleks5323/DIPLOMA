package com.diploma.REST;

import com.diploma.DAO.UsersEntityImpl;
import com.diploma.Entities.UsersEntity;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Inject
    private UsersEntityImpl userDao;

    @POST
    @RolesAllowed({"ADMIN"})
    @Path("/regUser")
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
    @RolesAllowed({"ADMIN"})
    @Path("/delUser/{uid}")
    public void delUser(@PathParam("uid") Integer uid) {
        userDao.removeUser(uid);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsersEntity> listUsers() {
        List<UsersEntity> list = new ArrayList();
        list = userDao.listUsers();

        return list;
    }

    @POST
    @Path("/authUser")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authUser(@Context HttpServletRequest req, UsersEntity user) throws NoSuchAlgorithmException {
        HttpSession session = req.getSession(true);

        ///////!!!!!!!!
        if (userDao.validateUser(user)) {
//            return Response.status(Response.Status.OK).cookie(new NewCookie("login", user.getUname(), "/", "localhost", "", 24*60*60, false)).cookie(new NewCookie("pass", userCheck.getUpassword(), "/", "localhost", "", 24*60*60, false)).build();
            UsersEntity genuineUser = userDao.getUserByLogin(user.getUname());
            session.setAttribute("login", user.getUname());
            session.setAttribute("pass", user.getUpassword());
            session.setAttribute("id", genuineUser.getUid());
            return Response.status(Response.Status.OK).build();
        }
        else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}