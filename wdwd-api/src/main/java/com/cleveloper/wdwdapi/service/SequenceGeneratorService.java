package com.cleveloper.wdwdapi.service;

import com.cleveloper.wdwdapi.models.DataSequences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGeneratorService {
    private final MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seqName) {
        DataSequences counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("sequence", 1), options().returnNew(true).upsert(true),
                DataSequences.class);
        return !Objects.isNull(counter) ? counter.getSequence() : 1;
    }

}
