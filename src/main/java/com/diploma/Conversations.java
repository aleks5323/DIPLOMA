package com.diploma;

import com.diploma.DAO.ConversationsEntityImpl;
import com.diploma.Entities.ConversationsEntity;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/conversations")
public class Conversations {

    private ConversationsEntityImpl convoDao = ConversationsEntityImpl.getInstance();

    @POST
    @Path("/newConversation")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public void newConversation(ConversationsEntity convo) {
        convoDao.newConversation(convo);
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
    public void delConversation(@PathParam("cid") Integer cid) {
        convoDao.removeConversation(cid);
    }

    @GET
    @Path("/getConversation/{cid}")
    @PermitAll
    public String getConvoById(@PathParam("cid") Integer cid) {
        ConversationsEntity convo = convoDao.getConvoById(cid);
        return convo.getCid() + "; " + convo.getRequest() + "; " + convo.getReqDate() + "; " + convo.getPerformedBy();
    }

    @GET
    @PermitAll
    @Path("/list")
    @Produces(MediaType.TEXT_PLAIN)
    public String listConvos() {
        List<ConversationsEntity> list;
        list = convoDao.listConvos();

        String out="";

        for (ConversationsEntity convo : list) {
            out += (convo.getCid() + "\t\t" + convo.getRequest() + "\t\t" + convo.getReqDate() + "\t\t" + convo.getPerformedBy() + "\n");
        }

        return out;
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
