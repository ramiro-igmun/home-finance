package com.igmun.homefinance.position.infrastructure.rest;

import java.util.List;

public record ListPositionsResponse(List<RestPositionDto> positions) {
}
