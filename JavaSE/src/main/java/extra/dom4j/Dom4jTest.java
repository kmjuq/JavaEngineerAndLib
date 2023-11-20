package extra.dom4j;

import cn.hutool.core.codec.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Dom4jTest {

    @Test
    public void demo1() {
        Document document = DocumentHelper.createDocument();
    }

    @Test
    public void demo2() throws FileNotFoundException {
        final String str3 = "2019-12 折旧 CNY";
        final String str4 = "结转社保201910";
        final String str5 = "1529计提工资201912";
        final String str6 = "冲销 \"结转社保201910\"";
        final String str7 = "2019-11 折旧 CNY";
//        File file = new File("/Users/kmj/Git/study_/se/src/main/java/com/extra/dom4j/log.xml");
        File file = new File("/Users/kmj/Downloads/log");
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.lines().forEach(b -> {
            Document d = null;
            try {
                b = b.replace("esb:", "").replace("\\n", "");
                d = DocumentHelper.parseText(b);
                String returnCode = d.selectSingleNode("//RETURN_DATA").getText();
                String re = Base64.decodeStr(returnCode);
//                CostCollectionResponseData ccrd = JaxbUtils.xml2bean(re, CostCollectionResponseData.class);
//                List<CostCollectionResponseData.Collection> cs = ccrd.getCollections();
//                if(CollectionUtil.isNotEmpty(cs)){
//                    List<CostCollectionResponseData.Collection> css = cs.stream()
//                            .filter(m -> m.getJournalName().contains(str7))
//                            //.filter(m -> "2019-12".equals(m.getPeriodName()))
//                            .collect(Collectors.toList());
//                    if (css.size()!=0) {
//                        css.forEach(System.out::println);
//                    }
//                    System.out.println(css.size());
//                }
                System.out.println(re);
                System.out.println("###################################################################################");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
