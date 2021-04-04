package be.fooda.backend.order.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Log4j2
@SpringBootTest
@Transactional
class FoodaOrderIndexRepositoryTest {
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Test
//    void should_search_combined() {
//    }
//
//    @Test
//    void should_search_simple() {
//
//        //Get the FullTextEntityManager
//        FullTextEntityManager fullTextEntityManager
//                = Search.getFullTextEntityManager(entityManager);
//
//        //Create a Hibernate Search DSL query builder for the required entity
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder()
//                .forEntity(FoodaOrder.class)
//                .get();
//
//        //Generate a Lucene query using the builder
//        Query query = queryBuilder
//                .keyword()
//                .onField("note")
//                .matching("order")
//                .createQuery();
//
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
//
//        //returns JPA managed entities
//        List<FoodaOrder> orders = fullTextQuery.getResultList();
//
//        log.info("Found orders: " + orders
//                .stream().map(order -> order.getId() +
//                        " -> " +
//                        order.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(orders.size()).isPositive();
//    }
//
//    @Test
//    void should_search_fuzzy() {
//        //Get the FullTextEntityManager
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//
//        //Create a Hibernate Search DSL query builder for the required entity
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder()
//                .forEntity(FoodaOrder.class)
//                .get();
//
//        //Generate a Lucene query using the builder with fuzzyQuery
//        Query query = queryBuilder
//                .keyword()
//                .fuzzy()
//                .withEditDistanceUpTo(2)
//                .withPrefixLength(0)
//                .onField("note")
//                .matching("ordr")
//                .createQuery();
//
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
//
//        //returns JPA managed entities
//        List<FoodaOrder> orders = fullTextQuery.getResultList();
//
//        log.info("Found orders: " + orders
//                .stream().map(order -> order.getId() +
//                        " -> " +
//                        order.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(orders.size()).isPositive();
//    }
//
//    @Test
//    void should_search_by_range() {
//        //Get the FullTextEntityManager
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//
//        //Create a Hibernate Search DSL query builder for the required entity
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder()
//                .forEntity(FoodaOrder.class)
//                .get();
//
//        //Generate a Lucene range query using the builder
//        // Range queries search for a value in between given boundaries.
//        // This can be applied to numbers, dates, timestamps, and strings.
//        Query query = queryBuilder
//                .range()
//                .onField("id")
//                .from(1L)
//                .to(100L)
//                .createQuery();
//
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
//
//        //returns JPA managed entities
//        List<FoodaOrder> orders = fullTextQuery.getResultList();
//
//        log.info("Found orders: " + orders
//                .stream().map(order -> order.getId() +
//                        " -> " +
//                        order.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(orders.size()).isPositive();
//    }
//
//    @Test
//    void should_search_using_wildcard() {
//        //Get the FullTextEntityManager
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//
//        //Create a Hibernate Search DSL query builder for the required entity
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder()
//                .forEntity(FoodaOrder.class)
//                .get();
//
//        //Generate a Lucene wildcard query using the builder
//        Query query = queryBuilder
//                .keyword()
//                .wildcard()
//                .onField("note")
//                .matching("1*")
//                .createQuery();
//
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
//
//        //returns JPA managed entities
//        List<FoodaOrder> orders = fullTextQuery.getResultList();
//
//        log.info("Found orders: " + orders
//                .stream().map(order -> order.getId() +
//                        " -> " +
//                        order.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(orders.size()).isPositive();
//    }
//
//    @Test
//    void should_search_with_phrase() {
//        //Get the FullTextEntityManager
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//
//        //Create a Hibernate Search DSL query builder for the required entity
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder()
//                .forEntity(FoodaOrder.class)
//                .get();
//
//        //Generate a Lucene phrase query using the builder
//        Query query = queryBuilder
//                .phrase()
//                .withSlop(1)
//                .onField("note")
//                .sentence("Order Note 1")
//                .createQuery();
//
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
//
//        //returns JPA managed entities
//        List<FoodaOrder> orders = fullTextQuery.getResultList();
//
//        log.info("Found orders: " + orders
//                .stream().map(order -> order.getId() +
//                        " -> " +
//                        order.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(orders.size()).isPositive();
//    }
//
//    @Test
//    void should_search_structured() {
//        //Get the FullTextEntityManager
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//
//        //Create a Hibernate Search DSL query builder for the required entity
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder()
//                .forEntity(FoodaOrder.class)
//                .get();
//
//        //The following query types are supported:
//        //boolean (AND using “+”, OR using “|”, NOT using “-“)
//        //prefix (prefix*)
//        //phrase (“some phrase”)
//        //precedence (using parentheses)
//        //fuzzy (fuzy~2)
//        //near operator for phrase queries (“some phrase”~3)
//        Query query = queryBuilder
//                .simpleQueryString()
//                .onField("note")
//                .matching("Odr~2 (11 | 22)")
//                .createQuery();
//
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
//
//        //returns JPA managed entities
//        List<FoodaOrder> orders = fullTextQuery.getResultList();
//
//        log.info("Found orders: " + orders
//                .stream().map(order -> order.getId() +
//                        " -> " +
//                        order.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(orders.size()).isPositive();
//    }
//
//    @Test
//    void should_search_within_subclass() {
//        //Get the FullTextEntityManager
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//
//        //Create a Hibernate Search DSL query builder for the required entity
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder()
//                .forEntity(FoodaOrder.class)
//                .get();
//
//        //Generate a Lucene query using the builder
//        Query query = queryBuilder
//                .keyword()
//                .onFields("products.quantity","products.price")
//                .matching(9)
//                .createQuery();
//
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
//
//        //returns JPA managed entities
//        List<FoodaOrder> orders = fullTextQuery.getResultList();
//
//        log.info("Found orders: " + orders
//                .stream()
//                .map(order -> order.getId() +
//                        " -> " +
//                        order.getProducts().get(0).getQuantity() +
//                " " +
//                order.getProducts().get(0).getPrice().longValue())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(orders.size()).isPositive();
//    }
}