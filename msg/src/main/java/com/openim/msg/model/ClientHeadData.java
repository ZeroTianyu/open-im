package com.openim.msg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientHeadData implements Serializable {

    private static final long serialVersionUID = 238790253873656662L;

    private UUID sessionId;

    private Map<String, List<String>> headers;
    private InetSocketAddress address;
    private LocalDateTime time;
    private InetSocketAddress local;
    private String url;
    private Map<String, List<String>> urlParams;
    private boolean xdomain;

    private String transport;
}
