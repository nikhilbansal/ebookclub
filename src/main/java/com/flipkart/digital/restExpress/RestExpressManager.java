package com.flipkart.digital.restExpress;

import com.strategicgains.restexpress.*;
import org.jboss.netty.handler.codec.http.HttpMethod;

/**
 * Created by IntelliJ IDEA.
 * User: nikhil.bansal
 * Date: 12/09/13
 * Time: 11:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestExpressManager {

    public synchronized void initialize() throws Exception{
		RestExpress server = new RestExpress()
		    .setName("torque")
		    .setDefaultFormat("json")
		    .putResponseProcessor(Format.JSON, ResponseProcessors.json())
		    .putResponseProcessor(Format.XML, ResponseProcessors.xml())
		    .putResponseProcessor(Format.WRAPPED_JSON, ResponseProcessors.wrappedJson())
		    .putResponseProcessor(Format.WRAPPED_XML, ResponseProcessors.wrappedXml());

		defineRoutes(server);

		mapExceptions(server);
		server.bind(8081);
		server.awaitShutdown();
	}

    private void defineRoutes(RestExpress server) throws Exception {
        server.uri("/newClub/{organizer}/{fsn}.{format}", Controller.class)
        .action("newClub", HttpMethod.POST).flag(Flags.Cache.DONT_CACHE);

//        server.uri(Constants.GET_ALL_APPLICABLE_OFFERS_URI, RestExpressController.INSTANCE)
//        .action(Constants.GET_ALL_APPLICABLE_OFFERS, HttpMethod.POST).flag(Flags.Cache.DONT_CACHE);
	}

	/**
     * @param server
     */
    private void mapExceptions(RestExpress server){}
}
