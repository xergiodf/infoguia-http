package com.minicubic.infoguiahttp.dto;

import javax.ws.rs.FormParam;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 *
 * @author xergio
 * @version 1
 */
public class FileUploadDto {
    
    private byte[] fileData;
    private String referenceId;
 
    public String getReferenceId() {
        return referenceId;
    }
 
    @FormParam("fileName")
    public void setFileName(String referenceId) {
        this.referenceId = referenceId;
    }
 
    public byte[] getFileData() {
        return fileData;
    }
 
    @FormParam("selectedFile")
    @PartType("application/octet-stream")
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
