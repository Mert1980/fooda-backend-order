package be.fooda.backend.order.service.match;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@Log4j2
@SpringBootTest
@Transactional
public class FoodaOrderIndexTest {

//    @Autowired
//    private EntityManager entityManager;
//
//    @Test
//    public void testQueryIndex() {
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
//    public void testFuzzyQueryIndex() {
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
//                .matching("delvry")
//                .createQuery();
//
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
//
//        //returns JPA managed entities
//        List<FoodaOrder> deliveries = fullTextQuery.getResultList();
//
//        log.info("Found deliveries: " + deliveries
//                .stream().map(delivery -> delivery.getId() +
//                        " -> " +
//                        delivery.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(deliveries.size()).isPositive();
//    }
//
//    @Test
//    public void testRangeQueryIndex() {
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
//        List<FoodaOrder> deliveries = fullTextQuery.getResultList();
//
//        log.info("Found deliveries: " + deliveries
//                .stream().map(delivery -> delivery.getId() +
//                        " -> " +
//                        delivery.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(deliveries.size()).isPositive();
//    }
//
//    @Test
//    public void testWildcardQueryIndex() {
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
//        List<FoodaOrder> deliveries = fullTextQuery.getResultList();
//
//        log.info("Found deliveries: " + deliveries
//                .stream().map(delivery -> delivery.getId() +
//                        " -> " +
//                        delivery.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(deliveries.size()).isPositive();
//    }
//
//    @Test
//    public void testPhraseQueryIndex() {
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
//                .sentence("Delivery Note 1")
//                .createQuery();
//
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
//
//        //returns JPA managed entities
//        List<FoodaOrder> deliveries = fullTextQuery.getResultList();
//
//        log.info("Found deliveries: " + deliveries
//                .stream().map(delivery -> delivery.getId() +
//                        " -> " +
//                        delivery.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(deliveries.size()).isPositive();
//    }
//
//    @Test
//    public void testStructuredStringQueryIndex() {
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
//                .matching("Delvry~2 (11 | 22)")
//                .createQuery();
//
//
//        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, FoodaOrder.class);
//
//        //returns JPA managed entities
//        List<FoodaOrder> deliveries = fullTextQuery.getResultList();
//
//        log.info("Found deliveries: " + deliveries
//                .stream().map(delivery -> delivery.getId() +
//                        " -> " +
//                        delivery.getNote())
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(deliveries.size()).isPositive();
//    }
//
//    @Test
//    public void testQueryIndexInSubclass() {
//
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
//                .onField("customer.firstName").matching("nozha")
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
//                        order.getCustomer().getFirstName() +
//                        " ")
//                .collect(Collectors.joining(" | ")));
//
//        assertThat(orders.size()).isPositive();
//    }

}