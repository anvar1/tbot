package uz.softclub.telegram;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bot{token}")
@RegisterRestClient
public interface TelegramClient {

    @GET
    @Path("/sendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject sendMessage(
            @PathParam("token") String token,
            @QueryParam("chat_id") String chatId,
            @QueryParam("text") String text
    );
//    {
//        "token": "1079189132:AAE1eZQg-UludqqFlkbhe3UqhAp8sjEdsKc",
//            "chat_id":"111",
//            "text":"3333"
//    }
    @POST
    @Path("/sendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject sendMessageWithBody(
            @PathParam("token") String token,
            @QueryParam("chat_id") String chatId,
            @QueryParam("text") String text,
            String body
    );

    @GET
    @Path("/sendPhoto")
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject sendPhoto(
            @PathParam("token") String token,
            @QueryParam("chat_id") String chatId,
            @QueryParam("photo") String urlOfFoto
    );

}
