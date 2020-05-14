package com.diploma.REST;

import com.diploma.DAO.ConversationsEntityImpl;
import com.diploma.Entities.ConversationsEntity;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Path("/conversations")
public class Conversations {

    @Inject
    private ConversationsEntityImpl convoDao;

    @POST
    @RolesAllowed({"ADMIN", "USER"})
    @Path("/newConversation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newConversation(ConversationsEntity convo) {
        if (convoDao.newConversation(convo))
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @POST
    @RolesAllowed({"ADMIN", "USER"})
    @Path("/updateConversation")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateConversation(ConversationsEntity convo) {
        convoDao.updateConversation(convo);
    }

    @GET
    @RolesAllowed({"ADMIN", "USER"})
    @Path("/delConversation/{cid}")
    public Response delConversation(@PathParam("cid") UUID cid) {
        if (convoDao.removeConversation(cid))
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @GET
    @RolesAllowed({"ADMIN", "USER"})
    @Path("/getConversation/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ConversationsEntity getConvoById(@PathParam("cid") UUID cid) {
        ConversationsEntity convo = convoDao.getConvoById(cid);
//        return convo.getCid() + "; " + convo.getRequest() + "; " + convo.getReqDate() + "; " + convo.getPerformedBy();
        return convo;
    }

    @GET
//    @PermitAll
    @RolesAllowed({"ADMIN", "USER"})
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConversationsEntity> listConvos(@Context HttpServletRequest req) {
        List<ConversationsEntity> list;
        list = convoDao.listConvos();
        HttpSession s = req.getSession(true);

        return list;
    }

//    @GET
//    @PermitAll
//    @Path("/getConversationsByLogin/{login}")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getConvosByLogin(@PathParam("login") String login) {
//        ConversationsEntity list;
//        list = convoDao.getConvoByUser(login);
//
//        String out="";
//
//        for (ConversationsEntity convo : list) {
//            out += (convo.getCid() + "\t\t" + convo.getRequest() + "\t\t" + convo.getReqDate() + "\t\t" + convo.getPerformedBy() + "\n");
//        }
//
//        return out;
//    }

}
