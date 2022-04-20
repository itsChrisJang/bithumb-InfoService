package com.info.application.person;

import com.info.application.person.response.PersonJobResponse;
import com.info.domain.model.Person;
import com.info.domain.model.PersonFindSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonFindSpecification personFindSpecification;

    /**
     * 이름으로 조회된 정보에서 직업 정보 찾기
     * @param personName : 이름
     * @return Mono<PersonJobResponse> : 직업 정보
     */
    public Mono<PersonJobResponse> findPersonJobByPersonName(String personName) {

        Mono<Person> personMono = personFindSpecification.findFirstByPersonName(personName); // 이름으로 정보 조회 검증

        return personMono.flatMap(person -> Mono.just(
                PersonJobResponse.builder()
                        .job(person.getPersonJob()) // 직업 정보 반영
                        .build()
        ));
    }
}
