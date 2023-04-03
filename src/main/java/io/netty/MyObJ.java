package io.netty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jd
 * @date 2023/3/7 15:02
 */
@Data
public class MyObJ implements Serializable {
    private static final long serialVersionUID = 1510326612440404416L;

    private String name;

    private String age;



}
