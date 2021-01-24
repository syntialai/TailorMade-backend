package com.future.tailormade.model.entity.base;

import com.future.tailormade.constants.CollectionConstants;
import io.github.classgraph.json.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = CollectionConstants.SEQUENCE)
public class Sequence {

    @Id
    private String name;

    private Long count;
}
