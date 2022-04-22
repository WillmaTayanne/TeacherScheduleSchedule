package com.pdist.schedule.Repository;

import com.pdist.schedule.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
