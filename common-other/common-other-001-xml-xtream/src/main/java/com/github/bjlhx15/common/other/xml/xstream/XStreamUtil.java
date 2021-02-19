package com.github.bjlhx15.common.other.xml.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import com.thoughtworks.xstream.security.AnyTypePermission;

/**
 * @author lihongxu6
 * @version 1.0
 * @className XStreamUtil
 * @description TODO
 * @since 2020-08-07 14:19
 */
public class XStreamUtil {
    public static <T> T xmlToJavaBean(String xml, Class[] clazzs) {
        if (null == xml || 0 == xml.length()) {
            return null;
        }
        XStream xstream = new XStream() {
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };
        // 对象设置默认安全防护
        // 会导致com.thoughtworks.xstream.security.ForbiddenClassException
        xstream.setupDefaultSecurity(xstream);
        //xstream.setClassLoader(clazz.getClassLoader());
        //尽量限制所需的最低权限 这条语句解决该问题
        xstream.addPermission(AnyTypePermission.ANY);
        // 对类开启注解
        xstream.processAnnotations(clazzs);//将 mClass.class类上的注解将会使用

        return (T) xstream.fromXML(xml);
    }
}
