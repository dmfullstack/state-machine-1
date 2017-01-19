package ru.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

@Configuration
public class TicketsHandlerConfig {

	@Bean
	public PersistTicket ticket() throws Exception {
		return new PersistTicket(persistStateMachineHandler());
	}

	@Bean
	public PersistStateMachineHandler persistStateMachineHandler() throws Exception {
		return new PersistStateMachineHandler(buildTicketMachine());
	}

	@Bean
	public StateMachine<String, String> buildTicketMachine() throws Exception {
		/*@formatter:off*/
    	StateMachineBuilder.Builder<String, String> builder = StateMachineBuilder.builder();
        builder.configureStates()
            .withStates()
                .initial("T_PLACED")
                .state("T_PROCESSING")
                .state("T_SENT")
                .state("T_DELIVERED");

        builder.configureTransitions()
            .withExternal()
                .source("T_PLACED").target("T_PROCESSING")
                .event("T_PROCESS")
                .and()
            .withExternal()
                .source("T_PROCESSING").target("T_SENT")
                .event("T_SEND")
                .and()
            .withExternal()
                .source("T_SENT").target("T_DELIVERED")
                .event("T_DELIVER");

        return builder.build();
    }
}