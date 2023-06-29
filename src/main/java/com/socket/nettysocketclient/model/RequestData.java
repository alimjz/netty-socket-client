package com.socket.nettysocketclient.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RequestData implements Serializable {
    private int intInput;
    private String stringValue;
}
