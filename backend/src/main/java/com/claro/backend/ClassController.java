package com.claro.backend;

import com.claro.core.ClassDescription;
import com.claro.core.ClassDescriptor;
import com.claro.core.DescriptionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/class")
public class ClassController {

    private ClassDescriptor classDescriptor = new ClassDescriptor();

    @PostMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getJsonDescription(@RequestParam(value = "source", required = false) String source, @RequestBody(required = false) String sourceBody) {

        Map<String, Object> response = new HashMap<>();

        String parsableSource = null;
        if(source != null) {
            parsableSource = new String(Base64.getDecoder().decode(source.getBytes()));
        }
        if(sourceBody != null) {
            parsableSource = sourceBody;
        }

        if(parsableSource == null) {
            response.put("error", "{ \"message\": \" use body request or source parameter\" }");
            return ResponseEntity.badRequest().body(response);
        }

        ClassDescription description = classDescriptor.describe(parsableSource);

        response.put("description", description);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/text", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getTextDescription(@RequestParam(value = "source", required = false) String source, @RequestBody(required = false) String sourceBody) throws JsonProcessingException {
        JsonMapper mapper = new JsonMapper();
        Map<String, Object> response = new HashMap<>();

        String parsableSource = null;
        if(source != null) {
            parsableSource = new String(Base64.getDecoder().decode(source.getBytes()));
        }
        if(sourceBody != null) {
            parsableSource = sourceBody;
        }

        if(parsableSource == null) {
            response.put("error", "{ \"message\": \" use body request or source parameter\" }");
            return ResponseEntity.badRequest().body(mapper.writeValueAsString(response));
        }

        ClassDescription description = classDescriptor.describe(parsableSource);

        return ResponseEntity.ok(DescriptionUtil.toString(description));
    }


}
