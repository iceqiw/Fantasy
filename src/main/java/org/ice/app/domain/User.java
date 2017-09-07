package org.ice.app.domain;

import lombok.Data;

/**
 * Created by admin on 2017/7/10.
 */
@Data
public class User {
    private Long id;
    private String name;
    private boolean status;
    private String username;
    private String password;

}
