package com.service.vedio.vedio_service;

import com.service.vedio.vedio_service.documents.Vedio;
import com.service.vedio.vedio_service.repositories.VideoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class VedioServiceApplication implements CommandLineRunner
{
   @Bean
	public ModelMapper modelMapper() {
	return new ModelMapper();
}

	public static void main(String[] args) {
		SpringApplication.run(VedioServiceApplication.class, args);
	}

	@Autowired
	private VideoRepo videoRepo;

	@Override
public void run(String... args) throws Exception

{
/*	Vedio vedio=new Vedio();
	vedio.setTitle("ooooooooooo");
	vedio.setDesc("oooooooooooooo");
    vedio.setContentType("vedio");

	Vedio save=videoRepo.save(vedio);
	System.out.println(save.getId());*/

videoRepo.findAll().forEach(System.out::println);
}
}
