package com.example.boot.nosql.elasticsearch.repository;

import com.example.boot.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品ES操作类
 * Created by macro on 2018/6/19.
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {
    /**
     * 搜索查询
     *
     * @param name              商品名称
     * @param subTitle          商品标题
     * @param page              分页信息
     * @return
     */
    Page<EsProduct> findByNameOrSubTitle(String name, String subTitle, Pageable page);

}
