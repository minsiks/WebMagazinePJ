//package com.aza.config;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RequiredArgsConstructor
//@Configuration
//public class SimpleBatchTest {
//	
//	private final JobBuilderFactory jobBuilderFactory;
//	private final StepBuilderFactory stepBuilderFactory;
//	
//	@Bean
//    public Job nextSimpleJob() {
//        return jobBuilderFactory.get("nextSimpleJob")
//            .start(nextSimpleStep1())
//            .next(nextSimpleStep2())
//            .build();
//    }
//	@Bean
//	public Step nextSimpleStep1() {
//		return stepBuilderFactory.get("nextSimpleStep1")
//				.tasklet((contribution,chunkContext)->{
//					for(int i= 10; i>0; i--) {
//						TimeUnit.SECONDS.sleep(1);
//						System.out.println(i);
//					}
//					log.info(">>>>>>>>>>>>>> This is nextSimpleStep1");
//					return RepeatStatus.FINISHED;
//				})
//				.build();
//	}
//	@Bean
//	public Step nextSimpleStep2() {
//		return stepBuilderFactory.get("nextSimpleStep2")
//				.tasklet((contribution,chunkContext)->{
//					log.info(">>>>>>>>>>>>>> This is nextSimpleStep2");
//					return RepeatStatus.FINISHED;
//				})
//				.build();
//	}
//}
