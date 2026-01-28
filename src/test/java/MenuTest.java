import org.example.menu.Menu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    @Test
    void testMenuCreation(){
        Menu menu = new Menu();
        assertNotNull(menu);
    }
    @Test
    void testMainMenuDoesNotCrash(){
        Menu menu = new Menu();
        assertDoesNotThrow(() -> menu.mainMenu());
    }
}
