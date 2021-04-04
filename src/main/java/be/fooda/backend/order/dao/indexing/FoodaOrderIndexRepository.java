package be.fooda.backend.order.dao.indexing;

import be.fooda.backend.order.model.entity.FoodaOrder;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class FoodaOrderIndexRepository {

    private final EntityManager entityManager;

    private FullTextEntityManager entityManager() {
        return Search.getFullTextEntityManager(entityManager);
    }

    @Transactional
    public List<FoodaOrder> combined(Set<String> keywords, Pageable pageable) {
        return Collections.EMPTY_LIST;
    }

    @Transactional
    public List<FoodaOrder> simple(Set<String> keywords, Pageable pageable, boolean isActive) {

        final FullTextEntityManager fullTextEntityManager = entityManager();
        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene query using the builder
                    Query query = qb
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onFields("customer.firstName",
                                    "customer.familyName",
                                    "store.storeName",
                                    "product.productName")
                            .matching(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<FoodaOrder>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .filter(order -> order.getIsActive().equals(isActive))
                .distinct()
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FoodaOrder> fuzzy(Set<String> keywords, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene query using the builder with fuzzyQuery
                    Query query = qb
                            .keyword()
                            .fuzzy()
                            .withEditDistanceUpTo(2)
                            .withPrefixLength(0)
                            .onField("note")
                            .matching(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, FoodaOrder.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("creationDateTime")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<FoodaOrder>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FoodaOrder> searchByCustomerFullName(String firstName, String familyName, int pageNo, int pageSize, boolean isActive) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);


        //Generate a Lucene query using the builder with fuzzyQuery
        Query firstNameQuery = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("customer.firstName")
                .matching(firstName)
                .createQuery();

        //Generate a Lucene query using the builder with fuzzyQuery
        Query lastNameQuery = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("customer.familyName")
                .matching(familyName)
                .createQuery();

        Query fullNameQuery = qb.bool()
                .should(firstNameQuery)
                .should(lastNameQuery)
                .createQuery();

        FullTextQuery fullTextQuery = ftem.createFullTextQuery(fullNameQuery, FoodaOrder.class);
        fullTextQuery.setFirstResult(pageNo);
        fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        final List<FoodaOrder> resultList = (List<FoodaOrder>)fullTextQuery.getResultList();
        return resultList
                .stream()
                .filter(order -> order.getIsActive().equals(isActive))
                .distinct()
                .collect(Collectors.toList());

    }

    @Transactional
    public List<FoodaOrder> searchByStoreName(String storeName, int pageNo, int pageSize, boolean isActive) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);


        //Generate a Lucene query using the builder with fuzzyQuery
        Query storeNameQuery = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("store.storeName")
                .matching(storeName)
                .createQuery();

        Query storeQuery = qb.bool()
                .should(storeNameQuery)
                .createQuery();

        FullTextQuery fullTextQuery = ftem.createFullTextQuery(storeQuery, FoodaOrder.class);
        fullTextQuery.setFirstResult(pageNo);
        fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        final List<FoodaOrder> resultList = (List<FoodaOrder>)fullTextQuery.getResultList();
        return resultList
                .stream()
                .filter(order -> order.getIsActive().equals(isActive))
                .distinct()
                .collect(Collectors.toList());

    }

    @Transactional
    public List<FoodaOrder> searchByProductName(String productName, int pageNo, int pageSize, boolean isActive) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);


        //Generate a Lucene query using the builder with fuzzyQuery
        Query productNameQuery = qb
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("products.productName")
                .matching(productName)
                .createQuery();

        Query productQuery = qb.bool()
                .should(productNameQuery)
                .createQuery();

        FullTextQuery fullTextQuery = ftem.createFullTextQuery(productQuery, FoodaOrder.class);
        fullTextQuery.setFirstResult(pageNo);
        fullTextQuery.setMaxResults(pageSize);

        //returns JPA managed entities
        final List<FoodaOrder> resultList = (List<FoodaOrder>)fullTextQuery.getResultList();
        return resultList
                .stream()
                .filter(order -> order.getIsActive().equals(isActive))
                .distinct()
                .collect(Collectors.toList());

    }

    @Transactional
    public List<FoodaOrder> range(Long start, Long end, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        //Generate a Lucene query using the builder with fuzzyQuery
        //Generate a Lucene range query using the builder
        // Range queries search for a value in between given boundaries.
        // This can be applied to numbers, dates, timestamps, and strings.
        Query query = qb
                .range()
                .onField("id")
                .from(start)
                .to(end)
                .createQuery();

        FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, FoodaOrder.class);

        fullTextQuery.setSort(qb.sort()
                .byField("creationDateTime")
                .desc()
                .andByScore()
                .createSort());
        fullTextQuery.setMaxResults(pageable.getPageSize());
        fullTextQuery.setFirstResult(pageable.getPageNumber());

        //returns JPA managed entities
        return (List<FoodaOrder>) fullTextQuery.getResultList();
    }

    @Transactional
    public List<FoodaOrder> wildcard(Set<String> keywords, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene wildcard query using the builder
                    Query query = qb
                            .keyword()
                            .wildcard()
                            .onField("note")
                            .matching("1*")
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, FoodaOrder.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("creationDateTime")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<FoodaOrder>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FoodaOrder> phrase(Set<String> keywords, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene phrase query using the builder
                    Query query = qb
                            .phrase()
                            .withSlop(1)
                            .onField("note")
                            .sentence(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, FoodaOrder.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("creationDateTime")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<FoodaOrder>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FoodaOrder> structured(String queryString, Pageable pageable) {
        //Get the FullTextEntityManager
        FullTextEntityManager fullTextEntityManager = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(fullTextEntityManager);

        //The following query types are supported:
        //boolean (AND using “+”, OR using “|”, NOT using “-“)
        //prefix (prefix*)
        //phrase (“some phrase”)
        //precedence (using parentheses)
        //fuzzy (fuzy~2)
        //near operator for phrase queries (“some phrase”~3)
        Query query = qb
                .simpleQueryString()
                .onField("note")
                .matching(queryString)
                .createQuery();


        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);

        fullTextQuery.setSort(qb.sort()
                .byField("creationDateTime")
                .desc()
                .andByScore()
                .createSort());
        fullTextQuery.setMaxResults(pageable.getPageSize());
        fullTextQuery.setFirstResult(pageable.getPageNumber());

        //returns JPA managed entities
        return (List<FoodaOrder>) fullTextQuery.getResultList();
    }

    @Transactional
    public List<FoodaOrder> subclass(Set<String> keywords, Pageable pageable) {

        //Get the FullTextEntityManager
        FullTextEntityManager ftem = entityManager();

        //Create a Hibernate Search DSL query builder for the required entity
        QueryBuilder qb = qBuilder(ftem);

        return keywords.stream()
                .map(keyword -> {
                    //Generate a Lucene query using the builder
                    Query query = qb
                            .keyword()
                            .onFields(
                                    "store.name",
                                    "customer.firstName",
                                    "customer.familyName",
                                    "delivery.status",
                                    "delivery.cost",
                                    "payments.amount",
                                    "products.quantity",
                                    "products.price",
                                    "products.tax"
                            )
                            .matching(keyword)
                            .createQuery();

                    FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, FoodaOrder.class);

                    fullTextQuery.setSort(qb.sort()
                            .byField("creationDateTime")
                            .desc()
                            .andByScore()
                            .createSort());
                    fullTextQuery.setMaxResults(pageable.getPageSize());
                    fullTextQuery.setFirstResult(pageable.getPageNumber());

                    //returns JPA managed entities
                    return (List<FoodaOrder>) fullTextQuery.getResultList();
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private QueryBuilder qBuilder(FullTextEntityManager fullTextEntityManager) {
        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(FoodaOrder.class)
                .get();
    }
}
