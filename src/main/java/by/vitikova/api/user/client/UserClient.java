package by.vitikova.api.user.client;

import by.vitikova.api.user.model.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс {@code UserClient} является Feign клиентом для взаимодействия с API пользователей.
 * <p>
 * Предоставляет методы для получения списка пользователей, создания, обновления и удаления пользователей.
 */
@FeignClient(contextId = "userClient", value = "${feign.user-service.value}", url = "${feign.user-service.url}")
public interface UserClient {

    /**
     * Получает список всех пользователей.
     *
     * @return ResponseEntity со списком пользователей.
     */
    @GetMapping
    ResponseEntity<List<User>> getAllUsers();

    /**
     * Создает нового пользователя.
     *
     * @param sessionId идентификатор сессии для поддержки сессии.
     * @param user      объект пользователя, который нужно создать.
     * @return ResponseEntity строку с ответом от сервера.
     */
    @PostMapping
    ResponseEntity<String> create(@RequestHeader(HttpHeaders.COOKIE) String sessionId, @RequestBody User user);

    /**
     * Обновляет существующего пользователя.
     *
     * @param sessionId идентификатор сессии для поддержки сессии.
     * @param user      объект пользователя с обновленными данными.
     * @return ResponseEntity строку с ответом от сервера.
     */
    @PutMapping
    ResponseEntity<String> update(@RequestHeader(HttpHeaders.COOKIE) String sessionId, @RequestBody User user);

    /**
     * Удаляет пользователя по его идентификатору.
     *
     * @param sessionId идентификатор сессии для поддержки сессии.
     * @param id        идентификатор пользователя, которого нужно удалить.
     * @return ResponseEntity строку с ответом от сервера.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@RequestHeader(HttpHeaders.COOKIE) String sessionId, @PathVariable("id") Long id);
}