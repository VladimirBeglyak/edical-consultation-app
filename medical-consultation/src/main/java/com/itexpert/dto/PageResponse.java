package com.itexpert.dto;

import java.util.List;
import org.springframework.data.domain.Page;

public class PageResponse<T> {

  private List<T> content;
  private MetaData metaData;

  public PageResponse(List<T> content, MetaData metaData) {
    this.content = content;
    this.metaData = metaData;
  }

  public static <T> PageResponse<T> of(Page<T> page) {
    MetaData data = new MetaData(page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    return new PageResponse<>(page.getContent(), data);
  }

  public List<T> getContent() {
    return content;
  }

  public MetaData getMetaData() {
    return metaData;
  }

  public static class MetaData {

    int page;
    int size;
    int totalPages;
    long totalElements;

    public MetaData(int page, int size, int totalPages, long totalElements) {
      this.page = page;
      this.size = size;
      this.totalPages = totalPages;
      this.totalElements = totalElements;
    }

    public int getPage() {
      return page;
    }

    public int getSize() {
      return size;
    }

    public long getTotalElements() {
      return totalElements;
    }

    public int getTotalPages() {
      return totalPages;
    }
  }
}
