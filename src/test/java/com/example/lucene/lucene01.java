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

import java.io.File;
import java.io.IOException;

/**
 * @Author suosong
 * @Date 2018/6/9
 */
public class lucene01 {

    @Test
    public void test01() throws IOException {
        /**
         * 1,创建IndexWriter对象
         */
        Directory dir  = FSDirectory.open(new File("/Users/peter/suosong/lucene/index01"));//索引目录
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_3,new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(dir,conf);
        /**
         * 2,创建document对象
         */
        File srcDir = new File("/Users/peter/suosong/lucene/documents");
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

    @Test
    public void testSearch() throws IOException {
        Directory dir  = FSDirectory.open(new File("/Users/peter/suosong/lucene/index01"));//索引目录
        IndexReader reader =  DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        TopDocs topDocs = searcher.search(new TermQuery(new Term("content","hadoop")),2);

        for(ScoreDoc scoreDoc : topDocs.scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println(doc.get("content"));
        }

        reader.close();
    }

}
