package com.diploma.REST;

import com.diploma.DAO.ConversationsEntityImpl;
import com.diploma.Entities.ConversationsEntity;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/conversations")
public class Conversations {

    @Inject
    private ConversationsEntityImpl convoDao;

    @POST
    @Path("/newConversation")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newConversation(ConversationsEntity convo) {
        if (convoDao.newConversation(convo))
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @POST
    @Path("/updateConversation")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateConversation(ConversationsEntity convo) {
        convoDao.updateConversation(convo);
    }

    @GET
    @Path("/delConversation/{cid}")
    @PermitAll
    public Response delConversation(@PathParam("cid") Integer cid) {
        if (convoDao.removeConversation(cid))
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @GET
    @PermitAll
    @Path("/getConversation/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ConversationsEntity getConvoById(@PathParam("cid") Integer cid) {
        ConversationsEntity convo = convoDao.getConvoById(cid);
//        return convo.getCid() + "; " + convo.getRequest() + "; " + convo.getReqDate() + "; " + convo.getPerformedBy();
        return convo;
    }

    @GET
    @PermitAll
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConversationsEntity> listConvos() {
        List<ConversationsEntity> list;
        list = convoDao.listConvos();

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
