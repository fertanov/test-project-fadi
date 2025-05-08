package org.machinestalk;

import org.machinestalk.api.dto.AddressDto;
import org.machinestalk.domain.Address;
import org.machinestalk.domain.Department;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    modelMapper.addConverter(new Converter<String, Department>() {
      @Override
      public Department convert(MappingContext<String, Department> context) {
        // You will need to write the logic to fetch Department from its name or code
        return new Department(context.getSource());
      }
    });
    modelMapper.addConverter(new Converter<AddressDto, Address>() {
      @Override
      public Address convert(MappingContext<AddressDto, Address> context) {
        AddressDto source = context.getSource();
        Address address = new Address();
        address.setStreetName(source.getStreetName());
        address.setStreetNumber(source.getStreetNumber());
        address.setPostalCode(source.getPostalCode());
        address.setCity(source.getCity());
        address.setCountry(source.getCountry());
        return address;
      }
    });
    return modelMapper;
  }

}
