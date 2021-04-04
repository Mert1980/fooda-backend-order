package be.fooda.backend.order.service.impl;


import be.fooda.backend.order.dao.FoodaOrderRepositoryMock;
import be.fooda.backend.order.service.mapper.impl.FoodaOrderMapperMock;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@Import({FoodaOrderMapperMock.class, FoodaOrderRepositoryMock.class})
public class FoodaOrderServiceImplTest {
//
//    @Autowired
//    @Qualifier("repositoryMock")
//    private FoodaOrderRepository repository;
//
//    @Autowired
//    private FoodaOrderServiceImpl service;
//
//    @Autowired
//    private FoodaOrderMapper mapper;
//
//
//    @Test
//    void should_read_by_id() {
//        // given
//        Optional<FoodaOrder> expected = repository.findById(1L);
//
//        // when
//        Optional<FoodaOrder> actual = service.readById(1L);
//
//        // then
//        assertThat(expected)
//                .isPresent();
//
//        assertThat(actual)
//                .isPresent()
//                .get().isEqualToIgnoringGivenFields(expected.get(), "id", "customer","store", "products", "payments", "productsTotal", "taxTotal");
//    }
//
//    @Test
//    void should_read_all() {
//        // given
//        List<FoodaOrder> expected = repository.findAll();
//
//        //when
//        List<FoodaOrder> actual = service.readAll();
//
//        // then
//        assertThat(actual)
//                .hasAtLeastOneElementOfType(FoodaOrder.class)
//                .hasSameElementsAs(expected);
//    }
}
