package be.fooda.backend.order.model;

public class FoodaOrderMock {

//    private final Faker faker = new Faker(new Locale("be-NL"));
//    private final FoodaOrder order;
//
//    public FoodaOrderMock() {
//        this.order = mock();
//    }
//
//    private FoodaOrder mock() {
//
//        FoodaOrder order = new FoodaOrder();
//
//        order.setStatus(nextStatus());
//        order.setDeliveryTime(nextTime());
//        order.setDeliveryDate(nextDate());
//        order.setDeliveryTotal(nextAmount());
//        order.setOrderTrackingId(nextId());
//        order.setPriceTotal(nextAmount());
//        order.setNote(nextLorem());
//        order.setRequiredTime(nextTime());
//        order.setRequiredDate(nextDate());
//        order.setPaymentDateTime(nextDateTime());
//        order.setProductsTotal(nextAmount());
//        order.setTaxTotal(nextAmount());
//        order.setCustomer(mockCustomer());
//        order.setStore(mockStore());
//        order.setProducts(mockProducts());
//        order.setPayments(mockPayments());
//
//        return order;
//    }
//
//    public FoodaOrderMock withNullIds() {
//        return withId(null)
//                .withCustomerId(null, nextId())
//                .withStoreId(null, nextId())
//                .withProductId(null)
//                .withPaymentId(null);
//    }
//
//    public FoodaOrderMock withId(Long orderId) {
//        if (orderId != null)
//            order.setId(orderId);
//        return this;
//    }
//
//    public FoodaOrderMock withCustomerId(Long customerId, Long externalCustomerId) {
//        if (customerId != null) order.getCustomer().setId(customerId);
//        order.getCustomer().setExternalCustomerId(externalCustomerId);
//        return this;
//    }
//
//    public FoodaOrderMock withStoreId(Long storeId, Long externalStoreId) {
//        if (storeId != null) order.getStore().setId(storeId);
//        order.getStore().setExternalStoreId(externalStoreId);
//        return this;
//    }
//
//    public FoodaOrderMock withExternalOrderId(Long orderId, Long trackingOrderId) {
//        if (orderId != null) order.setId(orderId);
//        order.setOrderTrackingId(trackingOrderId);
//        return this;
//    }
//
//    public FoodaOrderMock withRequiredTime(Long orderId, LocalTime requiredTime) {
//        if (orderId != null) order.setId(orderId);
//        order.setRequiredTime(requiredTime);
//        return this;
//    }
//
//    public FoodaOrderMock withRequiredDate(Long orderId, LocalDate requiredDate) {
//        if (orderId != null) order.setId(orderId);
//        order.setRequiredDate(requiredDate);
//        return this;
//    }
//
//    public FoodaOrderMock withPaymentDateTime(Long orderId, LocalDateTime paymentDateTime) {
//        if (orderId != null) order.setId(orderId);
//        order.setPaymentDateTime(paymentDateTime);
//        return this;
//    }
//
//    public FoodaOrderMock withProductId(Long productId) {
//        if (productId != null)
//            order.getProducts()
//                    .get(faker.number().numberBetween(0, order.getProducts().size() - 1))
//                    .setId(productId);
//        return this;
//    }
//
//    public FoodaOrderMock withPaymentId(Long paymentId) {
//        if (paymentId != null)
//            order.getPayments()
//                    .get(faker.number().numberBetween(0, order.getPayments().size() - 1))
//                    .setId(paymentId);
//        return this;
//    }
//
//    public FoodaOrderMock withPaymentId(Long paymentId, Long externalPaymentId) {
//        if (paymentId != null)
//            order.getPayments().stream()
//                    .findAny()
//                    .map(payment -> {
//                        payment.setId((long) faker.number().numberBetween(0, order.getPayments().size() - 1));
//                        payment.setExternalPaymentId(externalPaymentId);
//                        return payment;
//                    });
//        return this;
//    }
//
//    public FoodaOrderMock withPaymentAmount(Long paymentId, BigDecimal amount) {
//        if (paymentId != null) {
//            order.getPayments().stream()
//                    .findAny()
//                    .map(payment -> {
//                        payment.setId((long) faker.number().numberBetween(0, order.getPayments().size() - 1));
//                        payment.setAmount(BigDecimal.valueOf(
//                                faker.number().randomDouble(3,amount.intValue(),amount.add(BigDecimal.valueOf(2)).intValue())));
//                        return payment;
//                    });
//        }
//        return this;
//    }
//
//    public FoodaOrderMock withPaymentAmount(Long paymentId, BigDecimal min, BigDecimal max) {
//        if (paymentId != null) {
//            order.getPayments().stream()
//                    .findAny()
//                    .map(payment -> {
//                        payment.setId((long) faker.number().numberBetween(0, order.getPayments().size() - 1));
//                        payment.setAmount(BigDecimal.valueOf(
//                                faker.number().randomDouble(3,min.intValue()+1,max.intValue()-1)));
//                        return payment;
//                    });
//        }
//        return this;
//    }
//
//    public FoodaOrderCustomer mockCustomer() {
//        FoodaOrderCustomer customer = new FoodaOrderCustomer();
//        customer.setId(nextId());
//        customer.setFirstName(nextFirstName());
//        customer.setFamilyName(nextFamilyName());
//        customer.setExternalCustomerId(nextId());
//        return customer;
//    }
//
//
//    public FoodaOrderStore mockStore() {
//        FoodaOrderStore store = new FoodaOrderStore();
//        store.setId(nextId());
//        store.setExternalStoreId(nextId());
//        store.setName(nextStoreName());
//        return store;
//    }
//
//    public List<FoodaOrderProduct> mockProducts() {
//        return IntStream.range(1, nextNumber())
//                .mapToObj(index -> mockProduct())
//                .collect(Collectors.toList());
//    }
//
//    public FoodaOrderProduct mockProduct() {
//        FoodaOrderProduct product = new FoodaOrderProduct();
//        product.setId(nextId());
//        product.setTax(BigDecimal.valueOf(faker.number().numberBetween(0, 100), 2));
//        product.setQuantity(nextNumber());
//        product.setPrice(nextAmount());
//        product.setEProductId(nextId());
//        return product;
//    }
//
//    public List<FoodaOrderPayment> mockPayments() {
//        return IntStream.range(1, nextNumber())
//                .mapToObj(index -> mockPayment())
//                .collect(Collectors.toList());
//    }
//
//    public FoodaOrderPayment mockPayment() {
//        FoodaOrderPayment payment = new FoodaOrderPayment();
//        payment.setId(nextId());
//        payment.setExternalPaymentId(nextId());
//        payment.setAmount(nextAmount());
//        return payment;
//    }
//
//    public FoodaOrder build() {
//        return order;
//    }
//
//    private BigDecimal nextAmount() {
//        return BigDecimal.valueOf(faker.number().randomDouble(3, 1, 500));
//    }
//
//    private FoodaOrderStatus nextStatus() {
//        return FoodaOrderStatus.values()[faker.number().numberBetween(0, 3)];
//    }
//
//    private int nextNumber() {
//        return faker.number().numberBetween(1, 12);
//    }
//
//    private LocalDateTime nextDateTime() {
//        return LocalDateTime.now().plusSeconds(60 * 24 * (long) faker.number().numberBetween(1, 365));
//    }
//
//    private LocalTime nextTime() {
//        return LocalTime.now().plusSeconds(60 * 24 * (long) faker.number().numberBetween(1, 365));
//    }
//
//    private LocalDate nextDate() {
//        return LocalDate.now();
//    }
//
//    private String nextStoreName() {
//        return faker.company().name();
//    }
//
//    private String nextFirstName() {
//        return faker.name().firstName();
//    }
//
//    private String nextFamilyName() {
//        return faker.name().lastName();
//    }
//
//    private String nextLorem() {
//        return faker.lorem().characters(100, 500);
//    }
//
//    private long nextId() {
//        return faker.random().nextLong(10_000);
//    }

}
