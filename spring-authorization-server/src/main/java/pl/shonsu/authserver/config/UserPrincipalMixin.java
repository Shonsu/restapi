package pl.shonsu.authserver.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.shonsu.authserver.user.entity.Role;
import pl.shonsu.authserver.user.entity.User;
import pl.shonsu.authserver.user.model.UserPrincipal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonDeserialize(using = UserPrincipalDeserializer.class)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class UserPrincipalMixin {
}

class UserPrincipalDeserializer extends JsonDeserializer<UserPrincipal> {

    @Override
    public UserPrincipal deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        JsonNode root = mapper.readTree(parser);
        return deserialize(parser, mapper, root);
    }

    private UserPrincipal deserialize(JsonParser parser, ObjectMapper mapper, JsonNode root)
            throws JsonParseException {

        JsonNode principal = JsonNodeUtils.findObjectNode(root, "user");
        if (!Objects.isNull(principal)) {
            long id = principal.get("id").longValue();
            String username = principal.get("username").textValue();
            String password = principal.get("password").textValue();
            String status = principal.get("status").textValue();
            ArrayList<SimpleGrantedAuthority> authoritiesArray= JsonNodeUtils.findValue(
                    root, "authorities", JsonNodeUtils.SIMPLEGRANTEDAUTHORITY_ARRAY, mapper);
            Set<Role> roleSet = authoritiesArray.stream().map(authority->new Role(null, authority.getAuthority())).collect(Collectors.toSet());
            User user = new User(id,username,password,status, roleSet);
            return new UserPrincipal(user);
        }
        return null;
    }
}

abstract class JsonNodeUtils {

    static final TypeReference<Set<String>> STRING_SET = new TypeReference<Set<String>>() {
    };
    static final TypeReference<Set<Role>> ROLE_SET = new TypeReference<Set<Role>>() {
    };
    static final TypeReference<ArrayList<SimpleGrantedAuthority>> SIMPLEGRANTEDAUTHORITY_ARRAY = new TypeReference<ArrayList<SimpleGrantedAuthority>>() {
    };

    static final TypeReference<Map<String, Object>> STRING_OBJECT_MAP = new TypeReference<Map<String, Object>>() {
    };

    static String findStringValue(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return (value != null && value.isTextual()) ? value.asText() : null;
    }

    static <T> T findValue(JsonNode jsonNode, String fieldName, TypeReference<T> valueTypeReference,
                           ObjectMapper mapper) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return (value != null && value.isContainerNode()) ? mapper.convertValue(value, valueTypeReference) : null;
    }

    static JsonNode findObjectNode(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.findValue(fieldName);
        return (value != null && value.isObject()) ? value : null;
    }

}