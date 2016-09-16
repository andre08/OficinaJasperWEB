package com.oficinajasperweb;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author andre ulisses
 */
@ApplicationPath("rest")
public class ApplicationJasperWeb extends ResourceConfig {
    
    public ApplicationJasperWeb(){
        packages("com.oficinajasperweb.controller");
    }
    
}
