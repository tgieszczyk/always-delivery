package com.ahold.alwaysdeliver.api.payload;

import org.springframework.data.domain.Sort;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
public class SearchCriteria {
	private PageRequestDefinition page;
	private String searchValue;

	public SearchCriteria() {
	}

	public SearchCriteria(int page, int size, Sort.Direction direction, String sortBy) {
		PageRequestDefinition pageRequestDefinition = new PageRequestDefinition();
		pageRequestDefinition.setPage(page);
		pageRequestDefinition.setSize(size);
		pageRequestDefinition.setSortDirection(direction);
		pageRequestDefinition.setSortProperty(sortBy);
		this.page = pageRequestDefinition;
	}

	public SearchCriteria(int page, int size, Sort.Direction direction, String sortBy, String searchValue) {
		PageRequestDefinition pageRequestDefinition = new PageRequestDefinition();
		pageRequestDefinition.setPage(page);
		pageRequestDefinition.setSize(size);
		pageRequestDefinition.setSortDirection(direction);
		pageRequestDefinition.setSortProperty(sortBy);
		this.searchValue = searchValue;
		this.page = pageRequestDefinition;
	}

	public PageRequestDefinition getPage() {
		return page;
	}

	public void setPage(PageRequestDefinition page) {
		this.page = page;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

}
