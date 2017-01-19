package ru.test;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import ru.test.Application.Order;

public class PersistTicket {

	private final PersistStateMachineHandler handler;

	private final PersistStateChangeListener listener = new LocalPersistStateChangeListener();

	public PersistTicket(PersistStateMachineHandler handler) {
		this.handler = handler;
		this.handler.addPersistStateChangeListener(listener);
	}

	public void change(int order, String event) {
		Order o = new Order(1, "PLACED");
		Message<String> msg = MessageBuilder.withPayload(event).setHeader("order", order).build();
		handler.handleEventWithState(msg, o.state);
	}

	private class LocalPersistStateChangeListener implements PersistStateChangeListener {

		@Override
		public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition,
				StateMachine<String, String> stateMachine) {
			System.out.println(stateMachine);
			if (message != null && message.getHeaders().containsKey("order")) {
				Integer order = message.getHeaders().get("order", Integer.class);
				System.out.println("ticketId=" + order);
			}
		}
	}
}