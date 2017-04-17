package com.minicubic.infoguiacore.util;

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
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;

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

    // save uploaded file to a defined location on the server
    public static void saveFileToDisk(InputStream uploadedInputStream, String fileName) {

        try {
            OutputStream outpuStream = new FileOutputStream(new File(Constants.UPLOAD_DIR + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];

            outpuStream = new FileOutputStream(new File(fileName));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
