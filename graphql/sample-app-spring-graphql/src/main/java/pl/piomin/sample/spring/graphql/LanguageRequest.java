package pl.piomin.sample.spring.graphql;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageRequest {

    private String name;

    private String code;
}