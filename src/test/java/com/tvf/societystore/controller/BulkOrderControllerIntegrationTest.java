import com.tvf.societystore.SocietyStoreApiApplication;
import com.tvf.societystore.entity.*;
import com.tvf.societystore.repository.CartItemRepository;
import com.tvf.societystore.repository.ProductRepository;
import com.tvf.societystore.repository.SocietyRepository;
import com.tvf.societystore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SocietyStoreApiApplication.class
)
@AutoConfigureMockMvc
@Testcontainers
public class BulkOrderControllerIntegrationTest {

//    @Container
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");
//
//    @DynamicPropertySource
//    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgres::getJdbcUrl);
//        registry.add("spring.datasource.username", postgres::getUsername);
//        registry.add("spring.datasource.password", postgres::getPassword);
//    }
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private SocietyRepository societyRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//    @Test
//    @WithMockUser(authorities = "ADMIN")
//    void testCreateBulkOrder_Success() throws Exception {
//        // Arrange: Create test data in the test database
//        User admin = new User();
//        admin.setName("Admin");
//        admin.setEmail("admin@test.com");
//        admin.setPassword("pass");
//        admin.setRole(Role.ADMIN);
//        userRepository.save(admin);
//
//        Society society = new Society();
//        society.setName("Test Society");
//        society.setAdmin(admin);
//        societyRepository.save(society);
//
//        User resident = new User();
//        resident.setName("Resident");
//        resident.setEmail("resident@test.com");
//        resident.setPassword("pass");
//        resident.setRole(Role.RESIDENT);
//        resident.setSociety(society);
//        userRepository.save(resident);
//
//        Product product = new Product();
//        product.setName("Milk");
//        product.setPrice(new BigDecimal("1.50"));
//        product.setWholesaler(admin); // Simplified for test
//        productRepository.save(product);
//
//        CartItem cartItem = new CartItem();
//        cartItem.setUser(resident);
//        cartItem.setProduct(product);
//        cartItem.setQuantity(5);
//        cartItemRepository.save(cartItem);
//
//        // Act & Assert
//        mockMvc.perform(post("/api/orders/society/{societyId}", society.getId()))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.societyId").value(society.getId()))
//                .andExpect(jsonPath("$.totalPrice").value(7.50))
//                .andExpect(jsonPath("$.items[0].productName").value("Milk"))
//                .andExpect(jsonPath("$.items[0].totalQuantity").value(5));
//    }
}