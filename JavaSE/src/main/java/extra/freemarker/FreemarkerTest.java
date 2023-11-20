package extra.freemarker;

import cn.hutool.core.bean.BeanUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * <p>
 * freemarker 字符串模板
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2019-07-16 00:34
 */
public class FreemarkerTest {

    private static final String TEMPLATE_1
            = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
            "                  xmlns:ws=\"http://ws.demo.hap.hand.com/\">\n" +
            "    <soapenv:Header/>\n" +
            "    <soapenv:Body>\n" +
            "        <ws:sayHello>\n" +
            "            <name>${name}</name>\n" +
            "            <author>${author}</author>\n" +
            "        </ws:sayHello>\n" +
            "    </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    private static final String TEMPLATE_2
            = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
            "                  xmlns:ws=\"http://ws.demo.hap.hand.com/\">\n" +
            "<soapenv:Header/>\n" +
            "<soapenv:Body>\n" +
            "    <ws:sayHello>\n" +
            "        <list>\n" +
            "            <#list books as book>\n" +
            "            <book>\n" +
            "                <name>${book.name}</name>\n" +
            "                <author>${book.author}</author>\n" +
            "            </book>\n" +
            "            </#list>\n" +
            "        </list>\n" +
            "    </ws:sayHello>\n" +
            "</soapenv:Body>\n" +
            "</soapenv:Envelope>";

    private final Configuration configuration = null;


    @BeforeAll
    public void init() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        configuration.setTemplateLoader(templateLoader);
        configuration.setDefaultEncoding("UTF-8");
    }


    @Test
    public void demo1() throws IOException, TemplateException {
        Template template = new Template("test", TEMPLATE_1, configuration);
        StringWriter out = new StringWriter();
        Book book = new Book("java8", "kmj");
        template.process(BeanUtil.beanToMap(book), out);
        System.out.println(out);
    }

    @Test
    public void demo2() throws IOException, TemplateException {
        Template template = new Template("test", TEMPLATE_2, configuration);
        StringWriter out = new StringWriter();
        List<Book> books = new ArrayList<>(Arrays.asList(
                new Book("java8", "kmj"),
                new Book("java7", "kmj_")
        ));
        Map<String, List> map = new HashMap<>(1);
        map.put("books", books);
        template.process(map, out);
        System.out.println(out);
        BookList bookList = new BookList(books);
        template.process(BeanUtil.beanToMap(bookList), out);
        System.out.println(out);
    }

}
