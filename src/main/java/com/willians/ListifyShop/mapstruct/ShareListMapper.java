package com.willians.ListifyShop.mapstruct;

import com.willians.ListifyShop.dto.ShareListResponse;
import com.willians.ListifyShop.entety.SharedList;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShareListMapper {
    ShareListResponse shareToResponse(SharedList share);
}
