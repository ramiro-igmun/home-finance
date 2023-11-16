package com.igmun.homefinance.graph.infrastructure.rest;

import com.igmun.homefinance.graph.domain.TimePeriod;

import java.util.List;

public record PeriodsResponse(List<TimePeriod> timePeriodList) {
}
