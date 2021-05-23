package ch.bzz.bookshelf.service;

import ch.bzz.bookshelf.data.DataHandler;
import ch.bzz.bookshelf.model.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

/**
 * provides services for the bookshelf
 * <p>
 * M133: Bookshelf
 *
 * @author Marcel Suter
 */
@Path("book")
public class BookService {

    /**
     * produces a map of all books
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBooks(
    ) {
        Map<String, Book> bookMap = DataHandler.getBookMap();
        Response response = Response
                .status(200)
                .entity(bookMap)
                .build();
        return response;

    }

    /**
     * reads a single book identified by the bookId
     *
     * @param bookUUID the bookUUID in the URL
     * @return Response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBook(
            @QueryParam("uuid") String bookUUID
    ) {
        Book book = null;
        int httpStatus;

        try {
            UUID bookKey = UUID.fromString(bookUUID);
            book = DataHandler.readBook(bookUUID);
            if (book.getTitle() != null) {
                httpStatus = 200;
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(book)
                .build();
        return response;
    }
}
