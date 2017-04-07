package com.minicubic.infoguiahttp.rest;

import com.wordnik.swagger.annotations.Api;
import javax.ejb.Singleton;
import javax.ws.rs.Path;

/**
 *
 * @author xergio
 * @version 1 - 07/04/2017
 */
@Singleton
@Path("upload")
@Api(value = "/upload", description = "REST Service para subida de archivos")
public class UploadRest {
    
}
