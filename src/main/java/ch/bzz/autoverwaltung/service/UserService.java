package ch.bzz.autoverwaltung.service;

import ch.bzz.autoverwaltung.data.UserData;
import ch.bzz.autoverwaltung.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * provides services for the user
 * <p>
 * M133: autoverwaltung
 *
 * @author Assvin
 */
@Path("user")
public class UserService {

    /**
     * login a user with username/password
     *
     * @param username the username
     * @param password the password
     * @return Response-object with the userrole
     */
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        User user = UserData.findUser(username, password);
        String userRole = user.getRole();
        int httpStatus = userRole.equals("guest") ? 401 : 200;

        return UserRole.createResponse(httpStatus, "", userRole);
    }

    /**
     * logout current user
     *
     * @return Response object with guest-Cookie
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout() {

        return UserRole.createResponse(200, "", "guest");
    }
}
