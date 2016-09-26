package com.tictac.service;

import lombok.*;

/**
 * Created by petar on 9/27/2016.
 */

@Getter
@Setter
public class Response<T> {
    private T object;
    private Status status;

    public Response(T object, Status status){
        this.object = object;
        this.status = status;
    }

    public enum Status{
        CREATED,
        NOT_CREATED,
        RECEIVED,
        NOT_RECEIVED
    }

}
