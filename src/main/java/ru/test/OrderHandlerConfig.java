package ru.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

@Configuration
public class OrderHandlerConfig {

	@Bean
	public PersistOrder persist() throws Exception {
		return new PersistOrder(persistOrderStateMachineHandler());
	}

	@Bean
	public PersistStateMachineHandler persistOrderStateMachineHandler() throws Exception {
		return new PersistStateMachineHandler(persistSm());
	}

	@Bean
	public StateMachine<String, String> persistSm() throws Exception {
		/*@formatter:off*/
    	StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();
        builder.configureStates()
            .withStates()
                .initial("PLACED")
                .state("PROCESSING")
                .state("SENT")
                .state("DELIVERED");

        builder.configureTransitions()
            .withExternal()
                .source("PLACED").target("PROCESSING")
                .event("PROCESS")
                .and()
            .withExternal()
                .source("PROCESSING").target("SENT")
                .event("SEND")
                .and()
            .withExternal()
                .source("SENT").target("DELIVERED")
                .event("DELIVER");

        return builder.build();
    }
}