package be.fooda.backend.order.view.controller;

public class FoodaOrderControllerTest {

//    private MockMvc mvc;
//
//    @Mock
//    private FoodaOrderService orderService;
//
//    @InjectMocks
//    private FoodaOrderControllerV1 orderController;
//
//    private JacksonTester<FoodaOrderCreate> orderToCreateJson;
//    private JacksonTester<List<FoodaOrderCreate>> orderToCreateListJson;
//    private JacksonTester<FoodaOrder> orderJson;
//    private JacksonTester<List<FoodaOrder>> orderListJson;
//    private JacksonTester<List<FoodaOrderMatch>> orderMatchListJson;
//
//
//    private final FoodaOrderMapperImpl orderMapper = new FoodaOrderMapperImpl();
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        JacksonTester.initFields(this, objectMapper);
//        // MockMvc standalone approach
//        mvc = MockMvcBuilders.standaloneSetup(orderController).build();
//    }
//
//    private List<FoodaOrder> mockList() {
//        return IntStream
//                .range(0, 100)
//                .mapToObj(index -> mock((long) index))
//                .collect(Collectors.toList());
//    }
//
//    private FoodaOrder mock(long index) {
//        FoodaOrder order = new FoodaOrder();
//        order.setId(index);
//        order.setStatus(FoodaOrderStatus.PROCESSING_PARENT);
//        order.setDeliveryTime(LocalTime.now().plusSeconds(60 * 60 * 45 + index));
//        order.setDeliveryDate(LocalDate.now());
//        order.setDeliveryTotal(BigDecimal.valueOf(10).add(BigDecimal.valueOf(index)));
//        order.setOrderTrackingId(321L + index);
//        order.setPriceTotal(BigDecimal.valueOf(60).add(BigDecimal.valueOf(index)));
//        order.setNote("Order Note 1" + index);
//        order.setRequiredTime(LocalTime.now().plusSeconds(60 * 60 * 45 + index));
//        order.setRequiredDate(LocalDate.now());
//
//        order.setPaymentDateTime(LocalDateTime.now().plusSeconds(60 * 60 * 45 + index));
//        order.setProductsTotal(BigDecimal.valueOf(30).add(BigDecimal.valueOf(index)));
//        order.setTaxTotal(BigDecimal.valueOf(0.21));
//
//
//        FoodaOrderCustomer customer = new FoodaOrderCustomer();
//        customer.setExternalCustomerId(1986L + index);
//        customer.setFirstName("FirstName" + index);
//        customer.setFamilyName("FamilyName" + index);
//        order.setCustomer(customer);
//
//        FoodaOrderStore store = new FoodaOrderStore();
//        store.setExternalStoreId(11L + index);
//        store.setName("Name" + index);
//        order.setStore(store);
//
//        order.setDeliveryTime(LocalTime.now().plusSeconds(60 * 60 * 45 + index));
//        order.setDeliveryDate(LocalDate.now());
//        order.setNote("Order Note " + index);
//
//        List<FoodaOrderPayment> payments =
//                IntStream
//                        .range(1, 10)
//                        .mapToObj(paymentIndex -> {
//                            FoodaOrderPayment payment1 = new FoodaOrderPayment();
//                            payment1.setExternalPaymentId(10L + (long) paymentIndex * index);
//                            payment1.setAmount(BigDecimal.valueOf(index).add(BigDecimal.valueOf(10)));
//                            return payment1;
//                        }).collect(Collectors.toList());
//
//        order.setPayments(payments);
//
//
//        List<FoodaOrderProduct> products =
//                IntStream
//                        .range(1, 10)
//                        .mapToObj(productIndex -> {
//                            FoodaOrderProduct product = new FoodaOrderProduct();
//                            product.setEProductId(10L + (long) productIndex * index);
//                            product.setTax(BigDecimal.valueOf(index).add(BigDecimal.valueOf(10)));
//                            return product;
//                        }).collect(Collectors.toList());
//
//        order.setProducts(products);
//
//        return order;
//    }
//
//    @SneakyThrows
//    @Test
//    void match() {
//        // given
//        List<FoodaOrder> expected = mockList();
//        when(orderService.match(anySet(), anyInt(), anyInt())).thenReturn(expected);
//
//        // when
//        final MockHttpServletResponse actual = this.mvc
//                .perform(get("/order/match")
//                        .param("keywords", "ttttt")
//                        .param("pageSize", "1")
//                        .param("pageNo", "1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andReturn().getResponse();
//
//        // then
//        assertThat(actual.getStatus()).isEqualTo(HttpStatus.FOUND.value());
//        assertThat(actual.getContentAsString()).isEqualTo(orderListJson.write(expected).getJson());
//    }
//
//    @SneakyThrows
//    @Test
//    void readAll() {
//        // given
//        List<FoodaOrder> expected = mockList();
//        when(orderService.readAll()).thenReturn(expected);
//
//        // when
//        final MockHttpServletResponse actual = this.mvc
//                .perform(get("/order/all").accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andReturn().getResponse();
//
//        // then
//        assertThat(actual.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(actual.getContentAsString()).isEqualTo(orderListJson.write(expected).getJson());
//    }
//
//    @SneakyThrows
//    @Test
//    void readById() {
//
//        // given
//        Long givenId = 1L;
//        FoodaOrder expected = mock(givenId);
//        when(orderService.readById(givenId)).thenReturn(Optional.of(expected));
//
//        // when
//        final MockHttpServletResponse actual = this.mvc
//                .perform(get("/order/" + givenId).accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andReturn().getResponse();
//
//        // then
//        assertThat(actual.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(actual.getContentAsString()).isEqualTo(orderJson.write(expected).getJson());
//    }
}
