package be.fooda.backend.order.view.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
//@WebMvcTest(FoodaOrderControllerV1.class)
//@Import(FoodaTestConfig.class)
class FoodaOrderControllerTest_oldVersion {
//
//    private final FoodaOrderMockito mockito = new FoodaOrderMockito();
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private FoodaOrderService<FoodaOrderDTO, FoodaOrder> service;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    @DisplayName("Testing readAll method with get mapping")
//    public void testReadAllSuccess() {
//
//        //given
//        FoodaOrder mockedOrder = mockito.mockEntity(true);
//        mockedOrder.setId(1L);
//        final List<FoodaOrder> expected = Collections.singletonList(mockedOrder);
//        when(service.readAll()).thenReturn(expected);
//
//        try {
//            //while
//            this.mockMvc
//                    .perform(get("/order/all"))
//                    .andDo(print())
//                    //then
//                    .andExpect(status().isOk())
//                    .andExpect(content().json(objectMapper.writeValueAsString(expected)))
//                    .andReturn();
//
//
//        } catch (Exception e) {
//            throw new RuntimeException("Could not perform HttpRequest");
//        }
//    }
//
//    @Test
//    @DisplayName("test Read All Failure")
//    void testReadAllFailure() {
//
//        // given
//        final List<FoodaOrder> expected = new ArrayList<>();
//        when(service.readAll()).thenReturn(expected);
//
//        try {
//            // while
//           this.mockMvc
//                    .perform(get("/order/all"))
//                    .andDo(print())
//                    // assert not found
//                    .andExpect(status().isNotFound())
//                    .andReturn();
//
//        } catch (Exception e) {
//            throw new RuntimeException("Could not perform HttpRequest");
//        }
//    }
//
//    @Test
//    @DisplayName("Testing readById method with get by id")
//    public void testReadByIdSuccess() {
//
//        //given
//        FoodaOrder expected = mockito.mockEntity(true);
//        expected.setId(1L);
//        when(service.readById(1L)).thenReturn(Optional.of(expected));
//
//        try {
//            // when
//            this.mockMvc
//                    .perform(get("/order/1"))
//                    .andDo(print())
//                    .andExpect(status().isOk())
//                    .andExpect(content().json(objectMapper.writeValueAsString(expected)));
//        } catch (Exception e) {
//            throw new RuntimeException("Could not perform HttpRequest");
//        }
//    }
//
//    @Test
//    @DisplayName("test Read By Id Failure")
//    void testReadByIdFailure() {
//
//        try {
//            // while
//            this.mockMvc
//                    .perform(get("/order/1"))
//                    .andDo(print())
//                    // assert not found
//                    .andExpect(status().isNotFound())
//                    .andReturn();
//
//        } catch (Exception e) {
//            throw new RuntimeException("Could not perform HttpRequest");
//        }
//    }
////
////    @Test
////    @DisplayName("Testing read method with get By Example")
////    public void testReadByExampleSuccess() {
////
////        Optional<FoodaOrder> expected = Optional.of(mockito.mockOrderEntity(123L));
////        Example<FoodaOrder> input = Example.of(expected.get());
////        FoodaOrderDTO dto = mockito.generateDTO(123L);
////
////        // initialize some mock (dummy) data
////        Mockito.when(service.readByExample(dto)).thenReturn(expected);
////
////        try {
////            // call the method
////            this.mockMvc
////                    .perform(get("/order/example")
////                            .contentType(MediaType.APPLICATION_JSON_VALUE)
////                            .content(objectMapper.writeValueAsString(input)))
////                    .andDo(print())
////                    .andExpect(status().isOk())
////                    .andExpect(content().json(objectMapper.writeValueAsString(expected)));
////        } catch (Exception e) {
////            throw new RuntimeException("Could not perform HttpRequest");
////        }
////    }
//
//    @Test
//    void should_create_one_order() {
//        // given
//        FoodaOrderDTO mockedOrderDTO= mockito.mockDTO(true);
//        mockedOrderDTO.setId(1L);
//        mockedOrderDTO.setEOrderId(111L);
//
//        FoodaOrder expected = mockito.mockEntity(true);
//        expected.setId(1L);
//        expected.setEOrderId(111L);
//
//        when(service.create(any(FoodaOrderDTO.class))).thenReturn(Optional.of(expected));
//
//        try {
//            // when
//            this.mockMvc
//                    .perform(post("/order")
//                            .contentType(MediaType.APPLICATION_JSON_VALUE)
//                            .content(objectMapper.writeValueAsString(mockedOrderDTO)))
//                    .andDo(print())
//                    // then
//                    .andExpect(status().isCreated())
//                    .andExpect(content().json(objectMapper.writeValueAsString(expected)));
//        } catch (Exception e) {
//            throw new RuntimeException("Could not perform HttpRequest");
//        }
//
//    }
//
//    @Test
//    @DisplayName("Testing update with putMapping")
//    public void testUpdateSuccess() {
//        FoodaOrderDTO mockedOrderDTO= mockito.mockDTO(true);
//        mockedOrderDTO.setId(1L);
//        mockedOrderDTO.setNote("Order note is updated and mocked..");
//
//        FoodaOrder expected = mockito.mockEntity(true);
//        expected.setId(1L);
//        expected.setNote("Order note is updated and mocked..");
//
//        when(service.updateById(anyObject(), any(FoodaOrderDTO.class))).thenReturn(Optional.of(expected));
//
//        try {
//            this.mockMvc
//                    .perform(put("/order/1")
//                            .contentType(MediaType.APPLICATION_JSON_VALUE)
//                            .content(objectMapper.writeValueAsString(mockedOrderDTO)))
//                    .andDo(print())
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.note", is("Order note is updated and mocked..")))
//                    .andExpect(content().json(objectMapper.writeValueAsString(expected)));
//        } catch (Exception e) {
//            throw new RuntimeException("Could not perform HttpRequest");
//        }
//
//    }
//
//    @Test
//    @DisplayName("Testing delete with putMapping")
//    public void testDeleteSuccess() {
//        FoodaOrder expected = mockito.mockEntity(true);
//        expected.setId(1L);
//
//        when(service.delete(1L)).thenReturn(Optional.of(expected));
//
//        try {
//            this.mockMvc
//                    .perform(delete("/order/1"))
//                    .andDo(print())
//                    .andExpect(status().isOk())
//                    .andExpect(content().json(objectMapper.writeValueAsString(expected)));
//        } catch (Exception e) {
//            throw new RuntimeException("Could not perform HttpRequest");
//        }
//
//    }
}