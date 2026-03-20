package bot;

import core.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {
    private String standardUser = "standard_user";
    private String lockedOutUser = "locked_out_user";
    private String validPassword = "secret_sauce";
    private String errorMessageContainer = ".error-message-container";
    private String lockedUserErrorMessage = "Epic sadface: Sorry, this user has been locked out.";

    @Test
    @DisplayName("Login with Valid credentials")
    public void Verify_correct_login() {
        bot.login(standardUser, validPassword);

        Assertions.assertEquals(bot.getURL(), baseURL + "/inventory.html",
                "Incorrect URL, user not logged in");
    }

    @Test
    @DisplayName("Login with locked user")
    public void Verify_locked_user_should_not_have_access() {
        bot.login(lockedOutUser, password);

        Assertions.assertEquals(bot.getTextString(errorMessageContainer), lockedUserErrorMessage,
                "Error message not displayed. User might have forbiden access");

    }




}

/*
Login
1.
Logowanie poprawne: standard_user / secret_sauce → przejście na listę produktów. (slqa.ru)
2.
Logowanie zablokowane: locked_out_user / secret_sauce → komunikat o blokadzie. (slqa.ru)
3.
Logowanie z innymi userami testowymi: problem_user, performance_glitch_user, error_user, visual_user → wejście do aplikacji lub specyficzne zachowanie (warto asercje dopasować do faktycznego zachowania). (slqa.ru)
4.
Błędne hasło dla poprawnego loginu → błąd walidacji.
5.
Puste pola login/hasło → błąd walidacji.
6.
Logout z menu → powrót na ekran logowania.
 */