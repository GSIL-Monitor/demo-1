package com.example.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * @Author suosong
 * @Date 2018/6/9
 */
public class Lucene01 {

    private String documentPath = "D:\\吃饭的家伙\\lucene\\documents";
    private String indexPath = "D:\\吃饭的家伙\\lucene\\index";

    /**
     * 创建索引
     * @throws IOException
     */
    @Test
    public void testCreate() throws IOException {
        /**
         * 1,创建IndexWriter对象
         */
        Directory dir  = FSDirectory.open(new File(indexPath));//索引目录
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_3,new IKAnalyzer(true));
        IndexWriter writer = new IndexWriter(dir,conf);
        /**
         * 2,创建document对象
         */
        File srcDir = new File(documentPath);
        if(srcDir.isDirectory()){
            for(File file : srcDir.listFiles()){
                String content = FileUtils.readFileToString(file);
                Field contentField = new TextField("content",content, Field.Store.YES);
                Document document = new Document();
                document.add(contentField);
                writer.addDocument(document);
            }
        }
        /**
         * 3,关闭索引写入器
         */
        writer.close();
    }

    /**
     * 查询索引
     * @throws IOException
     */
    @Test
    public void testSearch() throws IOException {
        Directory dir  = FSDirectory.open(new File(indexPath));//索引目录
        IndexReader reader =  DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        TopDocs topDocs = searcher.search(new TermQuery(new Term("content","的")),2);

        for(ScoreDoc scoreDoc : topDocs.scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println(doc.get("content"));
        }

        reader.close();
    }

}
