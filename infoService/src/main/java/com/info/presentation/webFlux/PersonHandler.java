package com.info.presentation.webFlux;

import com.info.application.person.PersonService;
import com.info.application.person.response.PersonJobResponse;
import com.info.presentation.shared.reponse.BadRequestResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class PersonHandler {

    private final PersonService personService;

    /**
     * 이름 정보로 조회하여 직업 정보 찾기
     * @param request : 이름
     * @return Mono<ServerResponse> : 전달된 이름으로 조회된 직업 정보 전송
     */
    public Mono<ServerResponse> getPersonJob(ServerRequest request) {

        String name = request.queryParam("name").orElse(null); // Request에서 name 필드 추출

        if (StringUtils.isBlank(name)) { // 이름 유효성 검사
            return badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(new BadRequestResponse("name", "전달된 이름이 없습니다.")), BadRequestResponse.class);
        }

        Mono<PersonJobResponse> response = personService.findPersonJobByPersonName(name);

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response, PersonJobResponse.class);
    }
}