/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.mxep.web.common.persistence;

import com.mxep.core.utils.Collections3;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaBuilder.In;
import java.util.Collection;
import java.util.List;

public class DynamicSpecifications {


	private static List<Predicate> getPredicate(Collection<SearchFilter> filters, Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = Lists.newArrayList();
		for (SearchFilter filter : filters) {
			// nested path translate, 如Task的名为"user.name"的filedName,
			// 转换为Task.user.name属性
			String[] names = StringUtils.split(filter.fieldName, ".");
			Path expression = root.get(names[0]);
			for (int i = 1; i < names.length; i++) {
				expression = expression.get(names[i]);
			}

            /*订单列表添加商品搜索条件时修改*/
			if (expression.getJavaType().equals(Integer.class) && !filter.operator.equals(SearchFilter.Operator.IN)) {
				filter.value = Integer.parseInt(filter.value.toString());
			}

			// logic operator
			switch (filter.operator) {
				case EQ:
					predicates.add(builder.equal(expression, filter.value));
					break;
				case LIKE:
					predicates.add(builder.like(expression, "%" + filter.value + "%"));
					break;
				case GT:
					predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
					break;
				case LT:
					predicates.add(builder.lessThan(expression, (Comparable) filter.value));
					break;
				case GTE:
					predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
					break;
				case LTE:
					predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
					break;
				case NEQ:
					predicates.add(builder.notEqual(expression, filter.value));
					break;
				case IN:
					In in = builder.in(expression);
					String value = filter.value.toString();
					String[] values = StringUtils.split(value, ",");
					for (int i = 0; i < values.length; i++) {
                                /*订单列表添加商品搜索条件时修改*/
						if (expression.getJavaType().equals(Integer.class) || expression.getJavaType().equals(int.class)|| expression.getJavaType().equals(byte.class)) {
							in.value(Integer.valueOf(values[i]));
						} else {
							in.value(values[i]);
						}

					}
					predicates.add(in);
					break;
				case NULL:
					predicates.add(builder.isNull(expression));
					break;
				case NOTNULL:
					predicates.add(builder.isNotNull(expression));
					break;
			}
		}
		return predicates;
	}

	public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (Collections3.isNotEmpty(filters)) {
					List<Predicate> predicates = getPredicate(filters, root, query, builder);
					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty()) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}
				return builder.conjunction();
			}
		};
	}


	public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz, final SpecificationCallback<T> callback) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (Collections3.isNotEmpty(filters)) {

					List<Predicate> predicates = getPredicate(filters, root, query, builder);

					if (null != callback) {
						predicates.addAll(callback.addPredicate(root, query, builder));
					}

					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty()) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}

	public interface SpecificationCallback<T> {
		List<Predicate> addPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder);
	}
}
