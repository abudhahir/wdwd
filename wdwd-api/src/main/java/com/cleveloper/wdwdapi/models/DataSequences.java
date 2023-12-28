package com.cleveloper.wdwdapi.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dataSequences")
@Getter
@Setter
public class DataSequences {

    @Id
    private String id;

    private long sequence;

    public DataSequences() {
    }

}
