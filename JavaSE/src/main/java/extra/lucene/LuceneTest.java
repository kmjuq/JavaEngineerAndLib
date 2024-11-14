package extra.lucene;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

@Slf4j
public class LuceneTest {

    private static Directory indexDirectory1;
    private static Directory indexDirectory2;
    private static final SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();

    @BeforeAll
    public static void init() throws IOException {
        // 创建内存中的索引存储
        indexDirectory1 = new MMapDirectory(Path.of("/Users/kmj/WorkSpace/git/JavaEngineerAndLib/JavaSE/src/main/resources/index/index1"));
        indexDirectory2 = new MMapDirectory(Path.of("/Users/kmj/WorkSpace/git/JavaEngineerAndLib/JavaSE/src/main/resources/index/index2"));
    }

    /**
     * 插入文档
     */
    @Test
    public void demo1() throws Exception {
        // 创建索引写入器配置
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(indexDirectory2, config);
        // 添加文档到索引
        addDocument(writer, "1", """
                据报道，两国领导人达成了一项重要协议，旨在加强双边贸易关系，促进经济合作。
                """);
        writer.commit();
        addDocument(writer, "2", """
                一家领先的科技公司宣布推出一款创新型智能设备，将为用户提供更便捷的生活体验。
                """);
        writer.commit();
        addDocument(writer, "3", """
                全球股市经历了一轮波动，投资者对经济前景产生了担忧，导致市场出现了一些不确定性。
                """);
        writer.commit();
        addDocument(writer, "4", """
                一位知名艺术家的画作在拍卖会上创下天价，引起了艺术市场的广泛关注。
                """);
        writer.close();
    }

    /**
     * 搜索文档
     */
    @Test
    public void demo2() throws Exception {
        // 创建查询解析器
        QueryParser parser = new QueryParser("content", analyzer);
        // 创建查询
        Query query = parser.parse("艺术家的画作");
        // 读取索引
        DirectoryReader ireader = DirectoryReader.open(indexDirectory1);
        // 创建索引搜索器
        IndexSearcher searcher = new IndexSearcher(ireader);
        // 执行搜索
        ScoreDoc[] hits = searcher.search(query, 10).scoreDocs;
        // Iterate through the results:
        StoredFields storedFields = searcher.storedFields();
        for (ScoreDoc hit : hits) {
            int docId = hit.doc;
            Document hitDoc = storedFields.document(docId);
            Explanation explanation = searcher.explain(query, docId);
            log.info("doc {}", hitDoc);
            log.info("explain {}", explanation);
        }
    }

    /**
     * 查询全部文档
     */
    @Test
    public void demo3() throws IOException {
        MatchAllDocsQuery matchAllDocsQuery = new MatchAllDocsQuery();
        // 读取索引
        DirectoryReader ireader = DirectoryReader.open(indexDirectory2);
        // 创建索引搜索器
        IndexSearcher searcher = new IndexSearcher(ireader);
        // 执行搜索
        ScoreDoc[] hits = searcher.search(matchAllDocsQuery, 10).scoreDocs;
        // Iterate through the results:
        StoredFields storedFields = searcher.storedFields();
        for (ScoreDoc hit : hits) {
            int docId = hit.doc;
            Document hitDoc = storedFields.document(docId);
            Explanation explanation = searcher.explain(matchAllDocsQuery, docId);
            log.info("doc {}", hitDoc);
            log.info("explain {}", explanation);
        }
    }

    /**
     * 删除文档
     */
    @Test
    public void demo4() throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(indexDirectory2, config);
        writer.deleteDocuments(new Term("id", "3"));
        writer.deleteDocuments(new Term("id", "2"));
        writer.close();
    }

    /**
     * 强制合并
     */
    @Test
    public void demo5() throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(indexDirectory2, config);
        writer.forceMerge(10);
        writer.close();
    }

    private static void addDocument(IndexWriter writer, String id, String content) throws Exception {
        Document document = new Document();
        document.add(new Field("id", id, TextField.TYPE_STORED));
        document.add(new Field("content", content, TextField.TYPE_STORED));
        writer.addDocument(document);
    }

}
