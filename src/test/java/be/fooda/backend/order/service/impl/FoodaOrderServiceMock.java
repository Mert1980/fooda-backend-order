package be.fooda.backend.order.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.TestConfiguration;

@Log4j2
@TestConfiguration
public class FoodaOrderServiceMock {

//    @Bean
//    public FoodaOrderService serviceMock() {
//        return new FoodaOrderService() {
//
//            @Autowired
//            private FoodaOrderMapper mapper;
//
//            @Override
//            public Optional<FoodaOrder> readById(Long id) {
//                FoodaOrder order = new FoodaOrderMock()
//                        .withId(id)
//                        .build();
//                return Optional.of(order);
//            }
//
//            @Override
//            public List<FoodaOrder> readAll() {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock().build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByExample(FoodaOrderExample toFind) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock().build())
//                        .peek(order -> {
//                            FoodaOrder probe = mapper.fromExampleToEntity(toFind);
//                            order.setId(probe.getId() != null ? probe.getId() : order.getId());
//                            order.setCustomer(probe.getCustomer() != null ? probe.getCustomer() : order.getCustomer());
//                            order.setStore(probe.getStore() != null ? probe.getStore() : order.getStore());
//                            order.setProducts(probe.getProducts() != null ? probe.getProducts() : order.getProducts());
//                            order.setPayments(probe.getPayments() != null ? probe.getPayments() : order.getPayments());
//                        })
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByStatus(FoodaOrderStatus status) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withId((long) index)
//                                .build())
//                        .peek(order -> order.setStatus(status))
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByRequiredTime(LocalTime requiredTime) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withId((long) index)
//                                .build())
//                        .peek(order -> order.setRequiredTime(requiredTime))
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByRequiredDate(LocalDate requiredDate) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withId((long) index)
//                                .build())
//                        .peek(order -> order.setRequiredDate(requiredDate))
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByDeliveryTime(LocalTime deliveryTime) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withId((long) index)
//                                .build())
//                        .peek(order -> order.setDeliveryTime(deliveryTime))
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByDeliveryDate(LocalDate deliveryDate) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withId((long) index)
//                                .build())
//                        .peek(order -> order.setDeliveryDate(deliveryDate))
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByPaymentDateTime(LocalDateTime paymentTime) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withId((long) index)
//                                .build())
//                        .peek(order -> order.setPaymentDateTime(paymentTime))
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByPaymentId(Long externalCustomerId, Long externalPaymentId) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withCustomerId((long) index, externalCustomerId)
//                                .withPaymentId((long)index, externalPaymentId )
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByPaymentAmount(BigDecimal amount) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withPaymentAmount((long) index, amount)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByPaymentAmount(BigDecimal min, BigDecimal max) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withPaymentAmount((long) index, min, max)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByStoreId(Long externalStoreId) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withStoreId((long) index, externalStoreId)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> readByCustomerId(Long externalcustomerId) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withCustomerId((long) index, externalcustomerId)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> deleteByCustomerId(Long customerId) {
//                return null;
//            }
//
//            @Override
//            public List<FoodaOrder> match(Set<String> keyword, Integer pageSize, Integer pageNo) {
//                return null;
//            }
//
//            @Override
//            public Optional<FoodaOrder> create(FoodaOrderCreate toCreate) {
//                FoodaOrder orderCreated = mapper.fromCreateToEntity(toCreate);
//                orderCreated.setId(1L);
//                orderCreated.getCustomer().setId(2L);
//                orderCreated.getStore().setId(3L);
//                IntStream
//                        .range(0, orderCreated.getProducts().size())
//                        .forEach(index ->
//                                orderCreated.getProducts().get(index).setId(index + 1L));
//                IntStream
//                        .range(0, orderCreated.getPayments().size())
//                        .forEach(index -> orderCreated.getPayments().get(index).setId(index + 1L));
//
//                return Optional.of(orderCreated);
//            }
//
//            @Override
//            public List<FoodaOrder> create(List<FoodaOrderCreate> toCreateList) {
//                AtomicLong index = new AtomicLong(1L);
//                return toCreateList.stream()
//                        .map(mapper::fromCreateToEntity)
//                        .peek(orderSaved -> orderSaved.setId(index.incrementAndGet()))
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public Optional<FoodaOrder> update(Long id, FoodaOrderUpdate toUpdate) {
//                Optional<FoodaOrder> oFound = readById(id);
//                FoodaOrder updatedPayment = mapper.fromUpdateToEntity(toUpdate, oFound.orElse(new FoodaOrder()));
//                log.info("Updated object: " + updatedPayment);
//                return Optional.of(updatedPayment);
//            }
//
//            @Override
//            public List<FoodaOrder> update(FoodaOrderExample toFind, FoodaOrderUpdate toUpdate) {
//                return readByExample(toFind)
//                        .stream()
//                        .map(found -> mapper.fromUpdateToEntity(toUpdate, found))
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public Optional<FoodaOrder> delete(Long id) {
//                FoodaOrder order = new FoodaOrderMock().withId(id).build();
//                return Optional.of(order);
//            }
//
//            @Override
//            public List<FoodaOrder> delete(FoodaOrderDelete toDelete) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> {
//                            FoodaOrder order = new FoodaOrderMock().build();
//                            FoodaOrder probe = mapper.fromDeleteToEntity(toDelete);
//                            order.setId(probe.getId() != null ? probe.getId() : order.getId());
//                            order.setProducts(probe.getProducts() != null ? probe.getProducts() : order.getProducts());
//                            order.setPayments(probe.getPayments() != null ? probe.getPayments() : order.getPayments());
//                            order.setCustomer(probe.getCustomer() != null ? probe.getCustomer() : order.getCustomer());
//                            order.setStore(probe.getStore() != null ? probe.getStore() : order.getStore());
//                            return order;
//                        })
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public Boolean exists(Long id) {
//                return Boolean.TRUE;
//            }
//
//            @Override
//            public Boolean exists(FoodaOrderExample toFind) {
//                return Boolean.TRUE;
//            }
//
//            @Override
//            public List<FoodaOrder> match(FoodaSpecification<FoodaOrder> specification) {
//                return null;
//            }
//
//            @Override
//            public List<FoodaOrder> match(List<FoodaSpecification<FoodaOrder>> foodaSpecifications) {
//                return null;
//            }
//        };
//    }

}
