package com.example.boot.nosql.elasticsearch.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 搜索中的商品信息
 * Created by macro on 2018/6/19.
 */
@Data
@Document(indexName = "pms", shards = 1, replicas = 0)
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    private Long id;
    private Long brandId;
    @Field(type = FieldType.Keyword)  //不需要中文分词
    private String brandName;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)  //需要中文分词
    private String name;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String subTitle;
    private BigDecimal price;
    @Field(type = FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;
}
