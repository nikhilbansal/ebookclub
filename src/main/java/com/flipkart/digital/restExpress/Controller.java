package com.flipkart.digital.restExpress;

import com.strategicgains.restexpress.Request;
import com.strategicgains.restexpress.Response;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: nikhil.bansal
 * Date: 12/09/13
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Controller {

    public void createNewClub(Request request, Response response) {

        String clubname = request.getRawHeader(Constants.CLUB_NAME);
        String organizer = request.getRawHeader(Constants.ACCOUNT_ID);
        String fsn = request.getRawHeader(Constants.FSN);
        new HelperMethods().createNewClub(clubname, fsn, "public", organizer);
        response.setBody("You successfully created the club " + clubname);
        response.setResponseStatus(HttpResponseStatus.OK);
    }

    public void joinClub(Request request, Response response){
        String clubname = request.getRawHeader(Constants.CLUB_NAME);
        String accountid = request.getRawHeader(Constants.ACCOUNT_ID);
        HelperMethods helperMethods = new HelperMethods();
        helperMethods.joinClub(accountid, clubname, "member");
        response.setBody("You successfully joined the club " + clubname);
        response.setResponseStatus(HttpResponseStatus.OK);
    }

    public void getMembers(Request request, Response response){
        String clubname = request.getRawHeader(Constants.CLUB_NAME);
        HelperMethods helperMethods = new HelperMethods();
        int clubId = helperMethods.getClubId(clubname);
        //System.out.println(helperMethods.getMembers(clubId+""));
        response.setBody(helperMethods.getMembers(clubId+""));
        response.setResponseStatus(HttpResponseStatus.OK);
    }

    public void getAdmin(Request request, Response response){
        String clubname = request.getRawHeader(Constants.CLUB_NAME);
        HelperMethods helperMethods = new HelperMethods();
        int clubId = helperMethods.getClubId(clubname);
        //System.out.println(helperMethods.getOrganiser(clubId+""));
        response.setBody(helperMethods.getOrganiser(clubId+""));
        response.setResponseStatus(HttpResponseStatus.OK);
    }
}
