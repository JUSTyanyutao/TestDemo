package com.mxep.model.repositories;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public <T> List<T> queryAsList(String sql, Class<T> cls) {
        Query query;
        if (cls.getName().equalsIgnoreCase("java.util.Map")) {
            query = entityManager.createNativeQuery(sql);
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        } else {
            query = entityManager.createNativeQuery(sql, cls);
        }
        return query.getResultList();
    }

    public <T> Page<T> queryAsPage(int pageNum, String sql, Class<T> cls) {
        PageRequest request = new PageRequest(pageNum-1, 20);
        Query query = null;
        if (cls.getName().equalsIgnoreCase("java.util.Map")) {
            query = entityManager.createNativeQuery(sql);
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        } else {
            query = entityManager.createNativeQuery(sql, cls);
        }
        query.setFirstResult(request.getPageNumber()  * request.getPageSize());
        query.setMaxResults(request.getPageSize());

        // retrieve a count first
        String countSql = sql.substring(sql.toLowerCase().indexOf("from"));
        String distinctField = "";
        if (countSql.toLowerCase().lastIndexOf("group by") != -1) {
            distinctField = countSql.substring(countSql.toLowerCase().lastIndexOf("group by") + 8);
            distinctField = distinctField.substring(0,distinctField.toLowerCase().indexOf("order by")-1);
            countSql = countSql.substring(0, countSql.toLowerCase().lastIndexOf("group by"));
            countSql = "SELECT COUNT(distinct " + distinctField + ") " + countSql;
        } else {
            countSql = "SELECT COUNT(1) " + countSql;
        }

        Query countQuery = entityManager.createNativeQuery(countSql);
        Long count = Long.valueOf(countQuery.getSingleResult().toString());
        Page<T> page = new PageImpl<T>(query.getResultList(), request, count);
        return page;

    }
}
