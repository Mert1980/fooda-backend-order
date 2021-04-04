package be.fooda.backend.order.dao;

import be.fooda.backend.order.service.mapper.impl.FoodaOrderMapperMock;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(FoodaOrderMapperMock.class)
class FoodaOrderRepositoryTest {
//
//    @Qualifier("orderRepository")
//    @Autowired
//    private FoodaOrderRepository repository;
//
//
//    @Autowired
//    private TestEntityManager tem;
//
//    @Test
//    void should_find_no_orders_if_repository_is_empty() {
//        //given
//
//        //when
//        List<FoodaOrder> actual = repository.findAll();
//        log.info("Actual: " + "\n" + actual);
//
//        //then
//        assertThat(actual).size().isEqualTo(0);
//    }
//
//    @Test
//    void should_save_an_order() {
//        // given
//        FoodaOrder order = new FoodaOrderMock().withNullIds().build();
//        FoodaOrder expected = tem.merge(order);
//        order.setId(null);
//        order.setProducts(order.getProducts().stream().peek(product -> product.setId(null)).collect(Collectors.toList()));
//        order.setPayments(order.getPayments().stream().peek(payment -> payment.setId(null)).collect(Collectors.toList()));
//        order.getCustomer().setId(null);
//        order.getStore().setId(null);
//        log.info("Expected: " + "\n" + order);
//
//        // when
//        FoodaOrder actual = repository.save(order);
//        log.info("Actual: " + "\n" + actual);
//        actual.setId(expected.getId());
//        actual.getCustomer().setId(expected.getCustomer().getId());
//        actual.getStore().setId(expected.getStore().getId());
//
//        // then
//        assertThat(actual)
//                .isNotNull()
//                .isEqualToIgnoringGivenFields(expected, "id", "products", "payments");
//        assertThat(actual.getPayments())
//                .hasSize(expected.getPayments().size());
//        assertThat(actual.getProducts())
//                .hasSize(expected.getProducts().size());
//    }
//
//    @Test
//    void should_find_all_orders() {
//        // given
//        List<FoodaOrder> expected = IntStream.range(1, 10)
//                .mapToObj(index -> {
//                    FoodaOrder order = new FoodaOrderMock().withNullIds().build();
//                    return tem.merge(order);
//                })
//                .collect(Collectors.toList());
//
//        // when
//        List<FoodaOrder> actual = repository.findAll();
//        log.info("Actual: " + "\n" + actual);
//
//        //then
//        assertThat(actual)
//                .hasSameSizeAs(expected)
//                .containsExactlyElementsOf(expected);
//    }
//
//    @Test
//    void should_find_order_by_id() {
//        FoodaOrder order = new FoodaOrderMock().withNullIds().build();
//        final FoodaOrder expected = tem.merge(order);
//
//        Optional<FoodaOrder> actual = repository.findById(expected.getId());
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual)
//                .isPresent()
//                .get()
//                .isEqualToComparingFieldByField(expected);
//    }
//
//    @Test
//    void should_find_order_by_external_store_id() {
//        Long givenExternalStoreId = 11L;
//
//        List<FoodaOrder> expected = IntStream.range(1, 3)
//                .mapToObj(index -> {
//                    FoodaOrder order = new FoodaOrderMock()
//                            .withNullIds()
//                            .withStoreId(null, givenExternalStoreId)
//                            .build();
//
//                    return tem.merge(order);
//                })
//                .collect(Collectors.toList());
//
//        List<FoodaOrder> actual = repository.findByStore_ExternalStoreId(givenExternalStoreId);
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).hasSize(expected.size());
//    }
//
//    @Test
//    void should_find_order_by_external_customer_id() {
//        Long givenExternalcustomerId = 11L;
//
//        List<FoodaOrder> expected = IntStream.range(1, 3)
//                .mapToObj(index -> {
//                    FoodaOrder order = new FoodaOrderMock()
//                            .withNullIds()
//                            .withCustomerId(null, givenExternalcustomerId)
//                            .build();
//
//                    return tem.merge(order);
//                })
//                .collect(Collectors.toList());
//
//        List<FoodaOrder> actual = repository.findByCustomer_ExternalCustomerId(givenExternalcustomerId);
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).hasSize(expected.size());
//    }
//
//    @Test
//    void should_find_order_by_external_order_id() {
//        Long givenExternalOrderId = 11L;
//
//        List<FoodaOrder> expected = IntStream.range(1, 3)
//                .mapToObj(index -> {
//                    FoodaOrder order = new FoodaOrderMock()
//                            .withNullIds()
//                            .withExternalOrderId(null, givenExternalOrderId)
//                            .build();
//
//                    return tem.merge(order);
//                })
//                .collect(Collectors.toList());
//
//        List<FoodaOrder> actual = repository.findByOrderTrackingId(givenExternalOrderId);
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).hasSize(expected.size());
//    }
//
//    @Test
//    void should_find_order_by_status() {
//        FoodaOrder expected = new FoodaOrderMock()
//                .withNullIds()
//                .build();
//        tem.merge(expected);
//
//        List<FoodaOrder> actual = repository.findByStatus(expected.getStatus());
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).isNotEmpty();
//    }
//
//    @Test
//    void should_find_order_by_required_time() {
//        FoodaOrder expected = new FoodaOrderMock()
//                .withNullIds()
//                .build();
//        tem.merge(expected);
//
//        List<FoodaOrder> actual = repository.findByRequiredTime(expected.getRequiredTime());
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).isNotEmpty();
//    }
//
//    @Test
//    void should_find_order_by_delivery_time() {
//        FoodaOrder expected = new FoodaOrderMock()
//                .withNullIds()
//                .build();
//        tem.merge(expected);
//
//        List<FoodaOrder> actual = repository.findByDeliveryTime(expected.getDeliveryTime());
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).isNotEmpty();
//    }
//
//    @Test
//    void should_find_order_by_payment_time() {
//        FoodaOrder expected = new FoodaOrderMock()
//                .withNullIds()
//                .build();
//        tem.merge(expected);
//
//        List<FoodaOrder> actual = repository.findByPaymentDateTime(expected.getPaymentDateTime());
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).isNotEmpty();
//    }
//
//    @Test
//    void should_find_order_by_payment() {
//        FoodaOrder expected = new FoodaOrderMock()
//                .withNullIds()
//                .build();
//        Long givenExternalCustomerId = 20L;
//        Long givenExternalPaymentId = 11L;
//        expected.getCustomer().setExternalCustomerId(givenExternalCustomerId);
//        expected.getPayments().forEach(payment -> payment.setExternalPaymentId(givenExternalPaymentId));
//        tem.merge(expected);
//
//        List<FoodaOrder> actual = repository.findByPayment(givenExternalCustomerId, givenExternalPaymentId);
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).isNotEmpty();
//    }
//
//    @Test
//    void should_find_order_by_price_min() {
//        FoodaOrder expected = new FoodaOrderMock()
//                .withNullIds()
//                .build();
//        BigDecimal givenPriceMin = BigDecimal.ONE;
//        expected.getPayments().forEach(payment -> payment.setAmount(BigDecimal.valueOf(45)));
//        tem.merge(expected);
//
//        List<FoodaOrder> actual = repository.findByPriceMin(givenPriceMin);
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).isNotEmpty();
//    }
//
//    @Test
//    void should_find_order_by_price_range() {
//        FoodaOrder expected = new FoodaOrderMock()
//                .withNullIds()
//                .build();
//        BigDecimal givenPriceMin = BigDecimal.ONE;
//        BigDecimal givenPriceMax = BigDecimal.valueOf(50);
//
//        expected.getPayments().forEach(payment -> payment.setAmount(BigDecimal.valueOf(45)));
//        tem.merge(expected);
//
//        List<FoodaOrder> actual = repository.findByPriceRange(givenPriceMin, givenPriceMax);
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).isNotEmpty();
//    }
//
//    @Test
//    void should_delete_order_by_id() {
//        List<FoodaOrder> expected = IntStream.range(1, 3)
//                .mapToObj(index -> {
//                    FoodaOrder order = new FoodaOrderMock()
//                            .withId((long) index)
//                            .build();
//
//                    return tem.merge(order);
//                })
//                .collect(Collectors.toList());
//
//        repository.deleteById(expected.stream().findFirst().get().getId());
//        List<FoodaOrder> actual = repository.findAll();
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).isNotEmpty()
//                .hasSize(expected.size() - 1);
//    }
//
//    @Test
//    void should_delete_all() {
//        IntStream.range(1, 3)
//                .mapToObj(index -> {
//                    FoodaOrder order = new FoodaOrderMock()
//                            .withId((long) index)
//                            .build();
//
//                    return tem.merge(order);
//                })
//                .collect(Collectors.toList());
//
//        repository.deleteAll();
//        List<FoodaOrder> actual = repository.findAll();
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).isEmpty();
//    }
//
//    @Test
//    void should_delete_order_by_externalOrderId_and_externalStoreId() {
//        List<FoodaOrder> expected = LongStream.range(1, 3)
//                .mapToObj(index -> {
//                    FoodaOrder order = new FoodaOrderMock()
//                            .withExternalOrderId(index, index)
//                            .build();
//
//                    return tem.merge(order);
//                })
//                .collect(Collectors.toList());
//
//        Long expectedExternalOrderId = expected.stream().findFirst().get().getOrderTrackingId();
//        Long expectedExternalStoreId = expected.stream().findFirst().get().getStore().getExternalStoreId();
//        repository.deleteByOrderTrackingIdAndStore_ExternalStoreId(expectedExternalOrderId,expectedExternalStoreId);
//
//        repository.deleteByOrderTrackingIdAndStore_ExternalStoreId(expectedExternalOrderId, expectedExternalStoreId);
//        List<FoodaOrder> actual = repository.findAll();
//        log.info("Actual: " + "\n" + actual);
//
//        assertThat(actual).isNotEmpty()
//                .hasSize(expected.size() - 1);
//    }
//
//    @Test
//    void should_update_an_order() {//TODO not sure
//        // given
//        FoodaOrder order = new FoodaOrderMock().withNullIds().build();
//        order.setNote("noteUpdated");
//        order.getCustomer().setExternalCustomerId(11L);
//        FoodaOrder expected = tem.merge(order);
//        order.setId(null);
//        order.setProducts(order.getProducts().stream().peek(product -> product.setId(null)).collect(Collectors.toList()));
//        order.setPayments(order.getPayments().stream().peek(payment -> payment.setId(null)).collect(Collectors.toList()));
//        order.getCustomer().setId(null);
//        order.getStore().setId(null);
//
//        log.info("Expected: " + "\n" + order);
//
//        // when
//        FoodaOrder actual = repository.save(order);
//        log.info("Actual: " + "\n" + actual);
//        actual.setId(expected.getId());
//        actual.getCustomer().setId(expected.getCustomer().getId());
//        actual.getStore().setId(expected.getStore().getId());
//
//        // then
//        assertThat(actual.getNote()).isEqualTo(expected.getNote());
//        assertThat(actual.getCustomer().getExternalCustomerId()).isEqualTo(expected.getCustomer().getExternalCustomerId());
//    }

}
