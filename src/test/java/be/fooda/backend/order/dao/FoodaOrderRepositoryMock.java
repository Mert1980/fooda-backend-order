package be.fooda.backend.order.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.TestConfiguration;

@Log4j2
@TestConfiguration
public class FoodaOrderRepositoryMock {

//
//    @Autowired
//    @Qualifier("orderMapper")
//    public FoodaOrderMapper orderMapper;
//
//    @Autowired
//    public FoodaOrderIndexRepository indexRepository;
//
//    @Bean
//    public FoodaOrderService getServiceClassToTest() {
//        return new FoodaOrderServiceImpl(repositoryMock(), orderMapper, indexRepository);
//    }
//
//    @Qualifier("repositoryMock")
//    @Bean
//    public FoodaOrderRepository repositoryMock() {
//        return new FoodaOrderRepository() {
//
//            @Override
//            public List<FoodaOrder> findByStatus(FoodaOrderStatus status) {
//                return null;
//            }
//
//            @Override
//            public List<FoodaOrder> findByRequiredTime(LocalTime requiredTime) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withRequiredTime((long) index, requiredTime)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> findByRequiredDate(LocalDate requiredDate) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withRequiredDate((long) index, requiredDate)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> findByDeliveryTime(LocalTime deliveryTime) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withRequiredTime((long) index, deliveryTime)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> findByDeliveryDate(LocalDate deliveryDate) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withRequiredDate((long) index, deliveryDate)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> findByPaymentDateTime(LocalDateTime paymentDateTime) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withPaymentDateTime((long) index, paymentDateTime)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> findByPayment(Long externalCustomerId, Long externalPaymentId) {
//                return null;
//            }
//
//            @Override
//            public List<FoodaOrder> findByPriceMin(BigDecimal amount) {
//                return null;
//            }
//
//            @Override
//            public List<FoodaOrder> findByPriceRange(BigDecimal minAmount, BigDecimal maxAmount) {
//                return null;
//            }
//
//            @Override
//            public void deleteByOrderTrackingIdAndStore_ExternalStoreId(Long orderTrackingId, Long externalStoreId) {
//            }
//
//            @Override
//            public Optional<FoodaOrder> findOne(Specification<FoodaOrder> spec) {
//                return findById(1L);
//            }
//
//            @Override
//            public List<FoodaOrder> findAll(Specification<FoodaOrder> spec) {
//                return findAll();
//            }
//
//            @Override
//            public Page<FoodaOrder> findAll(Specification<FoodaOrder> spec, Pageable pageable) {
//                return findAll(pageable);
//            }
//
//            @Override
//            public List<FoodaOrder> findAll(Specification<FoodaOrder> spec, Sort sort) {
//                return findAll();
//            }
//
//            @Override
//            public long count(Specification<FoodaOrder> spec) {
//                return 1;
//            }
//
//            @Override
//            public List<FoodaOrder> findByStore_ExternalStoreId(Long externalStoreId) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withStoreId((long) index, externalStoreId)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> findByOrderTrackingId(Long orderTrackingId) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withExternalOrderId((long) index, orderTrackingId)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> findByCustomer_ExternalCustomerId(Long externalCustomerId) {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock()
//                                .withCustomerId((long) index, externalCustomerId)
//                                .build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> findAll() {
//                return IntStream.range(1, 3)
//                        .mapToObj(index -> new FoodaOrderMock().build())
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public List<FoodaOrder> findAll(Sort sort) {
//                List<FoodaOrder> payments = findAll();
//                if (sort.equals(Sort.by(Sort.Direction.ASC, "id")))
//                    payments.sort(Comparator.comparing(FoodaOrder::getId));
//                return payments;
//            }
//
//            @Override
//            public List<FoodaOrder> findAllById(Iterable<Long> longs) {
//                return Stream
//                        .generate(longs.iterator()::next)
//                        .map(this::findById)
//                        .filter(Optional::isPresent)
//                        .map(Optional::get)
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public <S extends FoodaOrder> List<S> saveAll(Iterable<S> entities) {
//                return Stream
//                        .generate(entities.iterator()::next)
//                        .map(this::save)
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public void flush() {
//            }
//
//            @Override
//            public <S extends FoodaOrder> S saveAndFlush(S entity) {
//                entity.setId(1L);
//                return entity;
//            }
//
//            @Override
//            public void deleteInBatch(Iterable<FoodaOrder> entities) {
//            }
//
//            @Override
//            public void deleteAllInBatch() {
//            }
//
//            @Override
//            public FoodaOrder getOne(Long id) {
//                return new FoodaOrderMock().withId(id).build();
//            }
//
//            @Override
//            public <S extends FoodaOrder> List<S> findAll(Example<S> example) {
//                return findAll()
//                        .stream()
//                        .map(order -> {
//                            final S probe = example.getProbe();
//                            order.setId(probe.getId() != null ? probe.getId() : order.getId());
//                            order.setPayments(probe.getPayments() != null ? probe.getPayments() : order.getPayments());
//                            order.setProducts(probe.getProducts() != null ? probe.getProducts() : order.getProducts());
//                            order.setCustomer(probe.getCustomer() != null ? probe.getCustomer() : order.getCustomer());
//                            order.setStore(probe.getStore() != null ? probe.getStore() : order.getStore());
//                            return (S) order;
//                        })
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public <S extends FoodaOrder> List<S> findAll(Example<S> example, Sort sort) {
//                return findAll(example)
//                        .stream()
//                        .sorted(Comparator.comparingLong(FoodaOrder::getId))
//                        .collect(Collectors.toList());
//            }
//
//            @Override
//            public Page<FoodaOrder> findAll(Pageable pageable) {
//                return new PageImpl<>(findAll(), pageable, 100);
//            }
//
//            @Override
//            public <S extends FoodaOrder> S save(S entity) {
//                entity.setId(1L);
//                return entity;
//            }
//
//            @Override
//            public Optional<FoodaOrder> findById(Long id) {
//                return Optional.of(new FoodaOrderMock().withId(id).build());
//            }
//
//            @Override
//            public boolean existsById(Long id) {
//                return true;
//            }
//
//            @Override
//            public long count() {
//                return 1;
//            }
//
//            @Override
//            public void deleteById(Long id) {
//            }
//
//            @Override
//            public void delete(FoodaOrder entity) {
//            }
//
//            @Override
//            public void deleteAll(Iterable<? extends FoodaOrder> entities) {
//            }
//
//            @Override
//            public void deleteAll() {
//            }
//
//            @Override
//            public <S extends FoodaOrder> Optional<S> findOne(Example<S> example) {
//                FoodaOrder order = new FoodaOrderMock().build();
//                final S probe = example.getProbe();
//                order.setId(probe.getId() != null ? probe.getId() : order.getId());
//                order.setPayments(probe.getPayments() != null ? probe.getPayments() : order.getPayments());
//                order.setProducts(probe.getProducts() != null ? probe.getProducts() : order.getProducts());
//                order.setCustomer(probe.getCustomer() != null ? probe.getCustomer() : order.getCustomer());
//                order.setStore(probe.getStore() != null ? probe.getStore() : order.getStore());
//                return Optional.of((S) order);
//            }
//
//            @Override
//            public <S extends FoodaOrder> Page<S> findAll(Example<S> example, Pageable pageable) {
//                return new PageImpl<>(findAll(example), pageable, 100);
//            }
//
//            @Override
//            public <S extends FoodaOrder> long count(Example<S> example) {
//                return 1;
//            }
//
//            @Override
//            public <S extends FoodaOrder> boolean exists(Example<S> example) {
//                return true;
//            }
//        };
//    }

}
