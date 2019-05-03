package com.softweb.util;

public interface Constant {


    //Request Type
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_JSON_PATCH_PLUS_JSON = "application/json-patch+json";
    public static final String APPLICATION_STAR_PLUS_JSON = "application/*+json";
    public static final String TEXT_JSON = "text/json";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String IMAGE_JPEG = "image/jpeg";
    public static final String MULTIPART_FORMDATA = "multipart/form-data";

    //Response Type
    public static final String SUCCESS = "200 - SUCCESS";
    public static final String CREATED = "201 - CREATED";
    public static final String ACCEPTED = "202 - ACCEPTED";
    public static final String BAD_REQUEST = "400 - BAD REQUEST";
    public static final String UNAUTHORIZED = "401 - UNAUTHORIZED";
    public static final String FORBIDDEN = "403 - FORBIDDEN";
    public static final String NOT_FOUND = "404 - NOT FOUND";
    public static final String METHOD_NOT_ALLOWED = "405 - METHOD NOT ALLOWED";
    public static final String NOT_ACCEPTABLE = "406 - NOT ACCEPTABLE";
    public static final String PRECONDITION_FAILED = "412 - PRECONDITION FAILED";
    public static final String UNSUPPORTED_MEDIA_TYPE = "415 - UNSUPPORTED MEDIA TYPE";
    public static final String MOVED_PERMANENTLY = "301 - MOVED PERMANENTLY";
    public static final String INTERNAL_SERVER_ERROR = "500 - INTERNAL SERVER ERROR";
    public static final String NOT_IMPLEMENTED = "501 - NOT IMPLEMENTED";

    //Project Data
    public static final String URI = "https://www.siyanainfo.com/SmartAnganwadiAPI/v1";
}
