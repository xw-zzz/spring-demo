package io.github.xw.ioc;

import java.util.Map;

/**
 * @author xw
 * @date 2023/10/10
 */
public class JsonObjectDefine {

    private String id;


    private String className;


    private Map<String, Object> properties;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Map<String, Object> getProperties() {
        return properties;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
