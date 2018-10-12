package com.ahold.alwaysdeliver.api.payload;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

/**
 * @author TMGSOFTWARE
 *
 */
public interface PageableCommons {
	PageRequest LAST_CONVERSATION_RESULT_REQUEST = new PageRequest(0, 1, Direction.DESC, "sentDate");
}
