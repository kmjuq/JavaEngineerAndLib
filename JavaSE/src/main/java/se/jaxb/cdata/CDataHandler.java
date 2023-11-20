package se.jaxb.cdata;

import net.sf.cglib.proxy.InvocationHandler;

import javax.xml.stream.XMLStreamWriter;
import java.lang.reflect.Method;

/**
 * <p>
 * 处理 <![CDATA[  ]]> 两边的 < > 实体
 * </p>
 *
 * @author mengjian.ke@hand-china.com
 * @date 2019/5/13 13:21
 */
public class CDataHandler implements InvocationHandler {

    private static final String CDATA_PREFIX = "<![CDATA[";
    private static final String CDATA_SUFFIX = "]]>";

    private static Method gWriteCharactersMethod = null;
    private static final String HANDLER_METHOD_NAME = "writeCharacters";

    static {
        try {
            gWriteCharactersMethod = XMLStreamWriter.class
                    .getDeclaredMethod(HANDLER_METHOD_NAME, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private final XMLStreamWriter writer;

    CDataHandler(XMLStreamWriter writer) {
        this.writer = writer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (gWriteCharactersMethod.equals(method)) {
            String text = (String) args[0];
            if (text != null && text.startsWith(CDATA_PREFIX) && text.endsWith(CDATA_SUFFIX)) {
                writer.writeCData(text.substring(9, text.length() - 3));
                return null;
            }
        }
        return method.invoke(writer, args);
    }

}
