package com.flipkart.digital.restExpress;

import com.strategicgains.restexpress.*;
import com.strategicgains.restexpress.plugin.cors.CorsHeaderPlugin;
import com.sun.org.apache.bcel.internal.classfile.Constant;
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

        new CorsHeaderPlugin("*")                    // Array of domain strings.
                .register(server);
		defineRoutes(server);

		mapExceptions(server);
		server.bind(8081);
		server.awaitShutdown();
	}

    private void defineRoutes(RestExpress server) throws Exception {

        server.uri("/newclub/{"+Constants.CLUB_NAME+"}/{"+Constants.ACCOUNT_ID+"}/{"+Constants.FSN+"}.{format}", new Controller())
        .action("createNewClub", HttpMethod.POST).flag(Flags.Cache.DONT_CACHE);

        server.uri("/joinclub/{"+Constants.CLUB_NAME+"}/{"+Constants.ACCOUNT_ID+"}.{format}", new Controller())
        .action("joinClub", HttpMethod.POST).flag(Flags.Cache.DONT_CACHE);

        server.uri("/getmembers/{"+Constants.CLUB_NAME+"}.{format}", new Controller())
        .action("getMembers", HttpMethod.GET);

        server.uri("/getadmin/{"+Constants.CLUB_NAME+"}.{format}", new Controller())
        .action("getAdmin", HttpMethod.GET);
	}

	/**
     * @param server
     */
    private void mapExceptions(RestExpress server){}

    public static void main(String [] args) throws Exception {
        new RestExpressManager().initialize();
    }
}
