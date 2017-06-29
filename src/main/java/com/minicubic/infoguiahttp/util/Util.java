package com.minicubic.infoguiahttp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 *
 * @author xergio
 */
public final class Util {

    public static boolean isEmpty(Object o) {
        return (o == null) || ("".equals(o));
    }

    /**
     *
     * @param id
     * @return
     */
    public static String createToken(Long id) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(id.toString())
                //.setExpiration(new Date(Constants.EXP_TOKEN))
                .signWith(SignatureAlgorithm.HS256, Constants.SECRET_KEY);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * Verifica el token
     *
     * @param token
     * @return
     * @throws SignatureException
     */
    public static boolean verifyToken(String token) {

        boolean valid = true;

        try {
            Util.getClaims(token);
        } catch (Exception e) {
            //e.printStackTrace();
            valid = false;
        }

        return valid;
    }

    /**
     * Obtiene los claims del token
     *
     * @param token
     * @return
     * @throws SignatureException
     */
    public static Claims getClaims(String token) throws SignatureException, ExpiredJwtException {

         Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Constants.SECRET_KEY))
                .parseClaimsJws(token).getBody();

        return claims;
    }

    /**
     * save uploaded file to a defined location on the server
     * @param input
     * @param dir
     * @param idRef
     * @return 
     */
    public static Map<String, String> saveFileToDisk(MultipartFormDataInput input, String dir, String idRef) {
        Map<String, String> map = new HashMap<>();
        try {

            // Guardamos la imagen en disco            
            Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
            InputPart inputPart = uploadForm.get(Constants.FILE_FORM_NAME).get(0);

            String mimeType = inputPart.getHeaders().getFirst("Content-Type");
            String fileName = UUID.randomUUID().toString() + "_" + idRef + "." + mimeType.split("/")[1];

            map.put("mimeType", mimeType);
            map.put("fileName", fileName);

            byte[] bytes = IOUtils.toByteArray(inputPart.getBody(InputStream.class, null));

            //Save the file
            File file = new File(dir + fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);

            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
    
    /**
     * 
     * @param fileLocation
     * @param fileName 
     */
    public static void deleteFileFromDisk(String fileLocation, String fileName) {
        File file = new File(fileLocation + fileName);
        file.delete();
    }
}
