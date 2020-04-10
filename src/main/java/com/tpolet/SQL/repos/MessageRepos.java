package com.tpolet.SQL.repos;

import com.tpolet.SQL.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepos extends CrudRepository<Message, Integer> {
}
