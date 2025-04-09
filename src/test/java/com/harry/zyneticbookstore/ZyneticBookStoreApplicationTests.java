package com.harry.zyneticbookstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZyneticBookStoreApplicationTests {

    @Test
    void contextLoads() {
        // passes if Spring context loads
    }

    @Test
    void applicationStarts() {
        ZyneticBookStoreApplication.main(new String[] {});
    }
}
