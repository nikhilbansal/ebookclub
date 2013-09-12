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
        new HelperMethods().createNewClub(clubname, organizer, fsn);
        response.setResponseStatus(HttpResponseStatus.OK);
    }

    public void joinClub(Request request, Response response){
        String clubname = request.getRawHeader(Constants.CLUB_NAME);
        String accountid = request.getRawHeader(Constants.ACCOUNT_ID);
        HelperMethods helperMethods = new HelperMethods();
        if(helperMethods.isClubIdPresent(clubname)){
            helperMethods.joinClub(accountid, clubname);
        }
        response.setResponseStatus(HttpResponseStatus.OK);
    }

    public void getMembers(Request request, Response response){
        String clubname = request.getRawHeader(Constants.CLUB_NAME);
        response.setResponseStatus(HttpResponseStatus.OK);
    }

    public void getAdmin(Request request, Response response){
        String clubname = request.getRawHeader(Constants.CLUB_NAME);
        response.setResponseStatus(HttpResponseStatus.OK);
    }
}
