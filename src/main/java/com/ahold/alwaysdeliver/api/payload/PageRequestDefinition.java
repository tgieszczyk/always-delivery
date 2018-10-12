package com.ahold.alwaysdeliver.api.payload;

import com.ahold.alwaysdeliver.utils.GenericBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
public class PageRequestDefinition {
	private int page;
	private int size;
	private Direction sortDirection;
	private String sortProperty;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Direction getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(Direction sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

	@JsonIgnore
	public Pageable toPageRequest() {
		Sort sort = toSort();
		if (sort == null) {
			return new PageRequest(page, size);
		}
		return new PageRequest(page, size, sort);
	}

	@JsonIgnore
	public Sort toSort() {
		if (sortDirection != null) {
			return new Sort(sortDirection, sortProperty);
		}

		return null;
	}
	
	public PageRequestDefinition nextPage() {
		return GenericBuilder.of(PageRequestDefinition::new).with(PageRequestDefinition::setPage, page + 1)
				.with(PageRequestDefinition::setSize, getSize())
				.with(PageRequestDefinition::setSortProperty, getSortProperty())
				.with(PageRequestDefinition::setSortDirection, getSortDirection()).build();
	}
}
