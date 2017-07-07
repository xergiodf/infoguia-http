package com.minicubic.infoguiahttp.util;

/**
 *
 * @author xergio
 */
public class Constants {
    
    public static final String SECRET_KEY = "INFOGUIA-VILO-SA";
    
    public static final String PUBLIC_SERVER_URL = "http://45.79.159.123";
    public static final String PUBLIC_API_URL = PUBLIC_SERVER_URL + ":8080/infoguia-rest/api/";
    public static final String PUBLIC_DOMAIN_URL = "http://www.infoguia.com.py";
    public static final String PUBLIC_API_CONFIRM_URL = PUBLIC_API_URL + "auth/confirm/";
    
    public static final String HOST_NAME_SMTP = "server1.hostipy.com";
    public static final String PUERT0_SMTP = "465";
    public static final String USER_NAME_SMTP = "noreply@enemedu.com";
    public static final String PASS_SMTP = ".NoReply.*";
    
    public static final String FILE_FORM_NAME = "fileData";
    public static final String UPLOAD_DIR = "/usr/share/nginx/html";
//    public static final String UPLOAD_DIR = "/home/xergio/Documents/Projects/InfoGuia/Dev/Backend/uploads";
    
    public static final String DB_USR_TIPO_ADMIN_ID = "1";
    public static final String DB_USR_TIPO_USUARIO_ID = "2";
    
    public static final Integer DB_USR_ESTADO_ACTIVO_ID = 1;
    public static final Integer DB_USR_ESTADO_INACTIVO_ID = 2;
    public static final Integer DB_USR_ESTADO_FALTA_ACTIVAR_ID = 3;
    
    public static final Integer DB_PUB_TIPO_NOVED_ID = 2;
    public static final Integer DB_PUB_TIPO_PROMO_ID = 1;
    
    public static final String VALIDATION_GENERIC_DESCRIPCION = "Descripcion es un campo requerido \n";
    
    public static final String VALIDATION_USUARIO_EMAIL_REQUIRED = "Email es un campo requerido \n";
    public static final String VALIDATION_USUARIO_USERNAME_REQUIRED = "Nombre de Usuario es un campo requerido \n";
    public static final String VALIDATION_USUARIO_PASSWORD_REQUIRED = "Contrase\u00f1a es un campo requerido \n";
    public static final String VALIDATION_USUARIO_ESTADOUSUARIO_REQUIRED = "Estado de Usuario es un campo requerido \n";
    public static final String VALIDATION_USUARIO_TIPOUSUARIO_REQUIRED = "Tipo de Usuario es un campo requerido \n";
    public static final String VALIDATION_USUARIO_EMAIL_UNIQUE = "Ya existe un usuario con el mismo email \n";
    public static final String VALIDATION_USUARIO_USERNAME_UNIQUE = "Ya existe un usuario con el mismo nombre de usuario \n";
    public static final String VALIDATION_USUARIO_SOCIAL_TOKEN = "El token de la red social es requerido. \n";
    public static final String VALIDATION_USUARIO_NOT_FOUND = "Usuario no encontrado \n";
    
    public static final String VALIDATION_CLIENTE_NOMBRECOMPLETO_REQUIRED = "Nombre Completo es un campo requerido\n";
    public static final String VALIDATION_CLIENTE_CODIGO_CLIENTE_REQUIRED = "Codigo de Cliente es un campo requerido\n";
    public static final String VALIDATION_CLIENTE_CODIGO_CLIENTE_UNIQUE = "Codigo de Cliente ya existe\n";
    
    public static final String VALIDATION_PUBLICACION_TIPOPUBLICACION_REQUIRED = "Tipo de Publicacion es un campo requerido\n";
    public static final String VALIDATION_PUBLICACION_CLIENTE_REQUIRED = "Cliente es un campo requerido\n";
    public static final String VALIDATION_PUBLICACION_ESTADO_REQUIRED = "Estado es un campo requerido\n";
    public static final String VALIDATION_PUBLICACION_FECHADESDE_REQUIRED = "Fecha Desde es un campo requerido\n";
    
    public static final String VALIDATION_SUCURSAL_NOMBRE_REQUIRED = "Nombre es un campo requerido \n";
    public static final String VALIDATION_SUCURSAL_DIRECCION_REQUIRED = "Direccion Fisica es un campo requerido \n";
    public static final String VALIDATION_SUCURSAL_COORDENADAS_REQUIRED = "Coordenadas es un campo requerido \n";
    public static final String VALIDATION_SUCURSAL_CLIENTE_REQUIRED = "Cliente es un campo requerido \n";
    
    public static final String VALIDATION_CATEGORIA_DESCRIPCION = "Descripcion es un campo requerido \n";
    public static final String VALIDATION_CATEGORIA_GRUPO = "Grupo es un campo requerido \n";
    
    public static final String VALIDATION_USUARIO_PERFIL_NOMBRES = "Nombres es un campo requerido \n";
    public static final String VALIDATION_USUARIO_PERFIL_APELLIDOS = "Apellidos es un campo requerido \n";
    public static final String VALIDATION_USUARIO_PERFIL_USUARIO = "Usuario es un campo requerido \n";
    
    public static final String VALIDATION_CIUDAD_DEPARTAMENTO = "Departamento es un campo requerido \n";
    
    public static final String VALIDATION_VALORACION_SUCURSAL = "Sucursal de Cliente es un campo requerido \n";
    public static final String VALIDATION_VALORACION_PUNTAJE = "Puntaje de Usuario es un campo requerido \n";
    
    public static final String VALIDATION_HORARIOCAB_TIPOHORARIO = "Tipo Horario es un campo requerido \n";
    public static final String VALIDATION_HORARIOCAB_SUCURSAL = "Sucursal es un campo requerido \n";
    
    public static final String MSG_ERROR_DEFAULT = "Ocurrio un error al procesar la peticion. Revise el log.";
    public static final String MSG_SUCCESS_USUARIO_REGISTRO = "Usuario creado correctamente";
}
