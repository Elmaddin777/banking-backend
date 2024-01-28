package com.gwpublic.demo.mapper;

import com.gwpublic.demo.dto.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GwMapper {

    GwMapper INSTANCE =  Mappers.getMapper(GwMapper.class);

    Customer toDto(com.gwpublic.demo.client.customer.model.Customer customer);

}
