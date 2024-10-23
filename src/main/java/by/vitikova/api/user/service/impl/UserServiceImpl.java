package by.vitikova.api.user.service.impl;

import by.vitikova.api.user.client.UserClient;
import by.vitikova.api.user.model.entity.User;
import by.vitikova.api.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;

    /**
     * Получает код, выполняя последовательные операции с пользователями.
     *
     * @return строку с конкатенированными результатами.
     */
    @Override
    public String getCode() {
        var sessionId = getAllUsers();

        var onePart = create(sessionId, User.builder().id(3L).name("James").lastName("Brown").age((byte) 30).build());
        var twoPart = update(sessionId, User.builder().id(3L).name("Thomas").lastName("Shelby").age((byte) 30).build());
        var threePart = delete(sessionId, 3L);
        return onePart + twoPart + threePart;
    }

    /**
     * Получает идентификатор сессии от запроса на получение всех пользователей.
     *
     * @return идентификатор сессии.
     */
    private String getAllUsers() {
        return userClient.getAllUsers().getHeaders().getFirst("Set-Cookie");
    }

    /**
     * Создает нового пользователя через клиент.
     *
     * @param sessionId идентификатор сессии для поддержки сессии.
     * @param user      объект пользователя для создания.
     * @return строку с ответом от сервера.
     */
    private String create(String sessionId, User user) {
        return userClient.create(sessionId, user).getBody();
    }

    /**
     * Обновляет существующего пользователя через клиент.
     *
     * @param sessionId идентификатор сессии для поддержки сессии.
     * @param user      объект пользователя с новыми данными.
     * @return строку с ответом от сервера.
     */
    private String update(String sessionId, User user) {
        return userClient.update(sessionId, user).getBody();
    }

    /**
     * Удаляет пользователя через клиент.
     *
     * @param sessionId идентификатор сессии для поддержки сессии.
     * @param id        идентификатор пользователя для удаления.
     * @return строку с ответом от сервера.
     */
    private String delete(String sessionId, Long id) {
        return userClient.delete(sessionId, id).getBody();
    }
}